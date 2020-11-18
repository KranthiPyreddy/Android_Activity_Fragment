package com.bignerdranch.GeoQuiz;
import android.util.Log;
import androidx.lifecycle.ViewModel;
public class QuizViewModel extends ViewModel {
    private static String TAG = "QuizViewModel";
    //Constructor
    int mCurrentIndex = 0;
    private Question[] mQuestionBank = {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };
    public boolean currentQuestionAnswer() {
        return mQuestionBank[mCurrentIndex].mAnswer;
    }
    public int currentQuestionText() {
        return mQuestionBank[mCurrentIndex].mTextResId;
    }
    public void moveToNext() {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
    }
}
