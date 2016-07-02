package com.example.asus.geoquiz;

/**
 * Created by asus on 2016/6/30.
 */
public class TrueFalse {
    private  int mQuestion;
    private  boolean mTrueQuestion;

    public void setmTrueQuestion(boolean mTrueQuestion) {
        this.mTrueQuestion = mTrueQuestion;
    }

    public void setmQuestion(int mQuestion) {

        this.mQuestion = mQuestion;
    }

    public int getmQuestion() {

        return mQuestion;
    }

    public boolean ismTrueQuestion() {

        return mTrueQuestion;
    }

    public TrueFalse(int mQuestion, boolean mTrueQuestion)
    {
        this.mQuestion=mQuestion;
        this.mTrueQuestion=mTrueQuestion;
    }
}
