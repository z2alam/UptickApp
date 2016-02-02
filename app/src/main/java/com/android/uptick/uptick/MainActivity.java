package com.android.uptick.uptick;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final int RightToLeft = 1;
    private static final int LeftToRight = 2;
    private static final int DURATION = 15000;

    private ValueAnimator mCurrentAnimator = null;
    private final Matrix mMatrix = new Matrix();
    private ImageView mImageView;
    private float mScaleFactor;
    private int mDirection = RightToLeft;
    private RectF mDisplayRect = new RectF();
    private static boolean mResumeBackgroundAnimation = true;

    private static ViewPager mPager;
    MainPagerAdapter mAdapter;

    public static ViewPager getViewPager() {
        return mPager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.background_image);
        mImageView.setImageResource(R.drawable.uptick_wallpaper);
        mImageView.post(new Runnable() {
            @Override
            public void run() {
                mScaleFactor = (float) mImageView.getHeight() / (float) mImageView.getDrawable().getIntrinsicHeight();
                mMatrix.postScale(mScaleFactor, mScaleFactor);
                mImageView.setImageMatrix(mMatrix);
                animate();
            }
        });

        mPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
    }

    private void animate() {
        updateDisplayRect();
        if(mDirection == RightToLeft) {
            animate(mDisplayRect.left, mDisplayRect.left - (mDisplayRect.right - mImageView.getWidth()));
        } else {
            animate(mDisplayRect.left - (mDisplayRect.right - mImageView.getWidth()), 0.0f);
        }
    }

    private void animate(float from, float to) {
        MatrixImageView matrixImageView = new MatrixImageView(mImageView, mScaleFactor);
        mCurrentAnimator = ObjectAnimator.ofFloat(matrixImageView, "matrixTranslateX", from, to);
        mCurrentAnimator.setDuration(DURATION);
        mCurrentAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (mDirection == RightToLeft) {
                    mDirection = LeftToRight;
                } else {
                    mDirection = RightToLeft;
                }
                animate();
            }
        });
        if (mResumeBackgroundAnimation)
            mCurrentAnimator.start();
    }

    class MatrixImageView {
        private final ImageView mImageView;
        private float mScaleFactor;
        private final Matrix mMatrix = new Matrix();

        public MatrixImageView(ImageView imageView, float scaleFactor) {
            this.mImageView = imageView;
            this.mScaleFactor = scaleFactor;
        }

        public void setMatrixTranslateX(float dx) {
            mMatrix.reset();
            mMatrix.postScale(mScaleFactor, mScaleFactor);
            mMatrix.postTranslate(dx, 0);
            mImageView.setImageMatrix(mMatrix);
        }
    }

    private void updateDisplayRect() {
        if (mImageView.getDrawable() != null) {
            mDisplayRect.set(0, 0, mImageView.getDrawable().getIntrinsicWidth(), mImageView.getDrawable().getIntrinsicHeight());
            mMatrix.mapRect(mDisplayRect);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mImageView.setImageDrawable(null);
    }

    @Override
    public void onPause() {
        super.onPause();
        mResumeBackgroundAnimation = false;
        if (mCurrentAnimator != null)
            mCurrentAnimator.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mResumeBackgroundAnimation = true;

        if (mCurrentAnimator != null)
            mCurrentAnimator.start();

        if (mPager != null)
            mPager.setCurrentItem(0, true);
    }

    @Override
    public void onBackPressed() {
        int currPosition = mPager.getCurrentItem();

        if (currPosition == 0) {
            NewUserInfo.reset();
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(currPosition - 1, true);
        }
    }
}
