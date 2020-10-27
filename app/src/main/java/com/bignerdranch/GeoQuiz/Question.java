package com.bignerdranch.GeoQuiz;

public class Question {
    int mTextResId;
    boolean mAnswer;
    //Constructor uses same name as class
    Question(int TextResId, boolean Answer) {
        this.mTextResId = TextResId;
        this.mAnswer = Answer;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }
}
