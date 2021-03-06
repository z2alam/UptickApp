package com.android.uptick.uptick;

import java.util.Date;

public class NewUserInfo {

    enum KindOfInvestor {
        NONE(-1, "None"),
        CONSERVATIVE(0, "Conservative"),
        MODERATE(1, "Moderate"),
        AGGRESSIVE(2, "Aggressive");

        private final int value;
        private final String answer;
        KindOfInvestor(int value, String answer) {
            this.value = value;
            this.answer = answer;
        }

        public int getValue() {
            return value;
        }

        public String getString() {
            return answer;
        }

        public static KindOfInvestor getEnum(int value) {
            switch (value) {
                case 0:
                    return CONSERVATIVE;
                case 1:
                    return MODERATE;
                case 2:
                    return AGGRESSIVE;
                default:
                    return NONE;
            }
        }
    }

    enum Status {
        NONE(-1, "None"),
        STUDENT(0, "Student"),
        UNEMPLOYED(1, "Unemployed"),
        RETIRED(2, "Retired"),
        EMPLOYED(3, "Employed");

        private final int value;
        private final String answer;
        Status(int value, String answer) {
            this.value = value;
            this.answer = answer;
        }

        public int getValue() {
            return value;
        }

        public String getString() {
            return answer;
        }

        public static Status getEnum(int value) {
            switch (value) {
                case 0:
                    return STUDENT;
                case 1:
                    return UNEMPLOYED;
                case 2:
                    return RETIRED;
                case 3:
                    return EMPLOYED;
                default:
                    return NONE;
            }
        }
    }

    enum IncomeAmountRange {
        NONE(-1, "None"),
        UNDER_25K(0, "Under $25K"),
        BW_25K_50K(1, "Between $25K and $50K"),
        BW_50K_100K(2, "Between $50K and $100K"),
        OVER_100K(3, "Over $100K");

        private final int value;
        private final String answer;
        IncomeAmountRange(int value, String answer) {
            this.value = value;
            this.answer = answer;
        }

        public int getValue() {
            return value;
        }

        public String getString() {
            return answer;
        }

        public static IncomeAmountRange getEnum(int value) {
            switch (value) {
                case 0:
                    return UNDER_25K;
                case 1:
                    return BW_25K_50K;
                case 2:
                    return BW_50K_100K;
                case 3:
                    return OVER_100K;
                default:
                    return NONE;
            }
        }
    }

    enum ValueAmountRange {
        NONE(-1, "None"),
        UNDER_5K(0, "Under $5K"),
        BW_5K_25K(1, "Between $5K and $25K"),
        BW_25K_50K(2, "Between $25K and $50K"),
        BW_50K_100K(3, "Between $50K and $100K"),
        OVER_100K(4, "Over $100K");

        private final int value;
        private final String answer;
        ValueAmountRange(int value, String answer) {
            this.value = value;
            this.answer = answer;
        }

        public int getValue() {
            return value;
        }

        public String getString() {
            return answer;
        }

        public static ValueAmountRange getEnum(int value) {
            switch (value) {
                case 0:
                    return UNDER_5K;
                case 1:
                    return BW_5K_25K;
                case 2:
                    return BW_25K_50K;
                case 3:
                    return BW_50K_100K;
                case 4:
                    return OVER_100K;
                default:
                    return NONE;
            }
        }
    }

    enum Period {
        NONE(-1, "None"),
        LESS_THAN_1_YEAR(0, "Less than 1 year"),
        BW_2_TO_5_YEARS(1, "Between 2 to 5 years"),
        OVER_5_YEARS(2, "Over 5 years");

        private final int value;
        private final String answer;
        Period(int value, String answer) {
            this.value = value;
            this.answer = answer;
        }

        public int getValue() {
            return value;
        }

        public String getString() {
            return answer;
        }

        public static Period getEnum(int value) {
            switch (value) {
                case 0:
                    return LESS_THAN_1_YEAR;
                case 1:
                    return BW_2_TO_5_YEARS;
                case 2:
                    return OVER_5_YEARS;
                default:
                    return NONE;
            }
        }
    }

    private static String mFirstName = null;
    private static String mLastName = null;
    private static String mEmailAddress = null;
    private static Date mDateOfBirth = null;
    private static String mPassword = null;

    private static KindOfInvestor mInvestorType = KindOfInvestor.NONE;
    private static Status mEmpStatus = Status.NONE;
    private static IncomeAmountRange mYearIncome = IncomeAmountRange.NONE;
    private static ValueAmountRange mValue = ValueAmountRange.NONE;
    private static Period mTimeline = Period.NONE;

    static void setFirstName(String name) {
        mFirstName = name;
    }

    static String getFirstName() {
        return mFirstName;
    }

    static void setLastName(String name) {
        mLastName = name;
    }

    static String getLastName() {
        return mLastName;
    }

    static void setEmailAddress(String email) {
        mEmailAddress = email;
    }

    static String getEmailAddress() {
        return mEmailAddress;
    }

    static void setDateOfBirth(Date date) {
        mDateOfBirth = date;
    }

    static Date getDateOfBirth() {
        return mDateOfBirth;
    }

    static void setPassword(String password) {
        mPassword = password;
    }

    static String getPassword() {
        return mPassword;
    }

    static void setInvestorType(KindOfInvestor type) {
        mInvestorType = type;
    }

    static KindOfInvestor getInvestorType() {
        return mInvestorType;
    }

    static void setEmpStatus(Status status) {
        mEmpStatus = status;
    }

    static Status getEmpStatus() {
        return mEmpStatus;
    }

    static void setYearIncome(IncomeAmountRange income) {
        mYearIncome = income;
    }

    static IncomeAmountRange getYearIncome() {
        return mYearIncome;
    }

    static void setValue(ValueAmountRange value) {
        mValue = value;
    }

    static ValueAmountRange getValue() {
        return mValue;
    }

    static void setTimeline(Period timeline) {
        mTimeline = timeline;
    }

    static Period getTimeline() {
        return mTimeline;
    }

    static void reset() {
        mFirstName = null;
        mLastName = null;
        mEmailAddress = null;
        mDateOfBirth = null;
        mPassword = null;
        mInvestorType = KindOfInvestor.NONE;
        mEmpStatus = Status.NONE;
        mYearIncome = IncomeAmountRange.NONE;
        mValue = ValueAmountRange.NONE;
        mTimeline = Period.NONE;
    }
}
