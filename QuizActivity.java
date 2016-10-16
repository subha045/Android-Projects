package com.sdg.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionText;
    //Creating a array of questions and answers
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_one, true),
            new Question(R.string.question_two, true),
            new Question(R.string.question_three, true),
            new Question(R.string.question_four, false),
            new Question(R.string.question_five, false)
    };
    int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Getting references to the text view widgets
        mQuestionText = (TextView) findViewById(R.id.question_text);
        updateQuestion();

        //Getting references to the button widgets
        mTrueButton  =  (Button) findViewById(R.id.true_button);
        mFalseButton =  (Button) findViewById(R.id.false_button);
        mNextButton  =  (Button) findViewById(R.id.next_button);

        //Setting listener for the true button
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        //Setting listener for the false button
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        //Setting listener for the false button
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex+1)%mQuestionText.length();
                updateQuestion();
            }
        });
    }

    private void checkAnswer(boolean userPressedTrue){

        boolean answerIsTrue = mQuestionBank[mCurrentIndex].ismAnswerTrue();
        int messageResId = 0;

        if(userPressedTrue == answerIsTrue){
            messageResId = R.string.correct_toast;
        }else{
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(QuizActivity.this,messageResId,Toast.LENGTH_SHORT).show();
    }

    private void updateQuestion(){

        mQuestionText.setText(mQuestionBank[mCurrentIndex].getmTextResId());

    }
}
