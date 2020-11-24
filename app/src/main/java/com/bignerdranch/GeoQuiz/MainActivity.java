package com.bignerdranch.GeoQuiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import java.time.DayOfWeek;

public class MainActivity extends AppCompatActivity {
    //add a TAG variable to use as the tag when logging messages to the Logcat
    private final String TAG = "MainActivity";
    private final String KEY_INDEX = "index";
    //Below are the 6 variables that are going to be used in this project

    private Button mTrueButton; //1
    private Button mFalseButton; //2

    private int mCurrentIndex = 0; //4
    private Button mNextButton; //5
    private TextView mQuestionTextView; //6
    private QuizViewModel mQuizViewModel; //7 //  Associate the activity with an instance of QuizViewModel.

    private Button mCheatButton; //8
    private final int REQUEST_CODE_CHEAT = 0; //9


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");

        setContentView(R.layout.activity_main);
        //associate the activity with an instance of QuizViewModel
        ViewModelProvider provider = ViewModelProviders.of(this);
        mQuizViewModel = provider.get(QuizViewModel.class);
        Log.d(TAG, "Got a QuizViewModel: $quizViewModel");
        if (savedInstanceState != null) {
            mQuizViewModel.mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        } else {
            mQuizViewModel.mCurrentIndex = 0;
        }

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
        // Hook up the cheat button and set it’s onClickListener
        mCheatButton = findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start activity
                //Intent intent = new Intent(MainActivity.this, CheatActivity.class);
                boolean answerIsTrue = mQuizViewModel.currentQuestionAnswer();
                Intent intent = CheatActivity.newIntent(MainActivity.this, answerIsTrue);
                //startActivity(intent);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });

        //Next button onClick event method
        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mQuizViewModel.moveToNext();
                updateQuestion();
            }
        });
        //This is to being used by the other two methods both TRUE and FALSE
        updateQuestion();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mQuizViewModel.mCurrentIndex);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart called.");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called.");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called.");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called.");
    }

    ////Update method to show next question
    private void updateQuestion() {
        //int questionTextResId = mQuestionBank[mCurrentIndex].getTextResId();
        int questionTextResId = mQuizViewModel.currentQuestionText();
        mQuestionTextView.setText(questionTextResId);
    }

    //checkAnswer method that will accept a boolean (this will be the answer the user pressed, e.g. true of false)
    private void checkAnswer(boolean answer) {
        //boolean correctAnswer = mQuestionBank[mCurrentIndex].isAnswer();
        boolean correctAnswer = mQuizViewModel.currentQuestionAnswer();
        if (mQuizViewModel.isCheater()) {
            Toast.makeText(this, R.string.judgment_toast, Toast.LENGTH_SHORT).show();
        } else if (answer == correctAnswer) {
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data != null) {
                mQuizViewModel.setIsCheater(data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false));
            }
        }
    }
}