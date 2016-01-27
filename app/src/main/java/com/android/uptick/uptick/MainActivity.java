package com.android.uptick.uptick;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final int RightToLeft = 1;
    private static final int LeftToRight = 2;
    private static final int DURATION = 15000;

    private static ValueAnimator mCurrentAnimator;
    private final Matrix mMatrix = new Matrix();
    private MatrixImageView mMatrixImageView;
    private ImageView mImageView;
    private float mScaleFactor;
    private int mDirection = RightToLeft;
    private RectF mDisplayRect = new RectF();
    private static boolean RunAnimator = true;

    ViewPager mPager;
    ViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.background_image);
        mImageView.post(new Runnable() {
            @Override
            public void run() {
                mScaleFactor = (float) mImageView.getHeight() / (float) mImageView.getDrawable().getIntrinsicHeight();
                mMatrix.postScale(mScaleFactor, mScaleFactor);
                mImageView.setImageMatrix(mMatrix);
                animate();
            }
        });

        /*
        Fragment currFragment = new AppWelcomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, currFragment).commit();
        */

        mPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
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
        mMatrixImageView = new MatrixImageView(mImageView, mScaleFactor);
        mCurrentAnimator = ObjectAnimator.ofFloat(mMatrixImageView, "matrixTranslateX", from, to);
        mCurrentAnimator.setDuration(DURATION);
        mCurrentAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
            if (RunAnimator) {
                if (mDirection == RightToLeft) {
                    mDirection = LeftToRight;
                } else {
                    mDirection = RightToLeft;
                }
                animate();
            }
            }
        });
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
        mDisplayRect.set(0, 0, mImageView.getDrawable().getIntrinsicWidth(), mImageView.getDrawable().getIntrinsicHeight());
        mMatrix.mapRect(mDisplayRect);
    }

    public static void StartAnimator() {
        RunAnimator = true;
        mCurrentAnimator.start();
    }

    public static void StopAnimator() {
        RunAnimator = false;
        mCurrentAnimator.end();
    }
}
