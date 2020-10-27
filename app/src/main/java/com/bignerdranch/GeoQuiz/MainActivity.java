package com.bignerdranch.GeoQuiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Below are the 6 variables that are going to be used in this project

    private Button mTrueButton; //1
    private Button mFalseButton; //2

    private Question[] mQuestionBank = {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    }; //3

    private int mCurrentIndex = 0; //4
    private Button mNextButton; //5
    private TextView mQuestionTextView; //6

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTextView = findViewById(R.id.question_text_view);

        //True button onClick event method
        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();

                // Calling checkAnswer method and passed true boolean value, to avoid writing code in multiple places.
                checkAnswer(true);
            }

        });

        //False button onClick event method
        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT.show();
                // Calling checkAnswer method and passed true boolean value, to avoid writing code in multiple places.
                checkAnswer(true);
            }
        });

        //Next button onClick event method
        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        //This is to being used by the other two methods both TRUE and FALSE
        updateQuestion();
        }

        ////Update method to show next question
        private void updateQuestion () {
        int questionTextResId = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(questionTextResId);
    }

    //checkAnswer method that will accept a boolean (this will be the answer the user pressed, e.g. true of false)
    private void checkAnswer(boolean answer) {
        boolean correctAnswer = mQuestionBank[mCurrentIndex].isAnswer();
        if (answer == correctAnswer) {
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }
    }
}
