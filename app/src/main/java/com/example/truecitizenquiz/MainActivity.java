package com.example.truecitizenquiz;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button falseButton;
    private Button trueButton;
    private TextView question_text;
    private ImageButton nextButton;
    private ImageButton previousButton;
    private TextView Answer;
    private int currentQuestionIndex = 0;
    private Question[] questionBank = new Question[]{
        new Question(R.string.question_declaration,true),
            new Question(R.string.question_amendments, false), //correct: 27
            new Question(R.string.question_constitution, true),
            new Question(R.string.question_independence_rights, true),
            new Question(R.string.question_religion, true),
            new Question(R.string.question_government, false),
            new Question(R.string.question_government_feds, false),
            new Question(R.string.question_government_senators, true),
            //and add more!
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        falseButton = findViewById(R.id.FalseButton);
        trueButton = findViewById(R.id.TrueButton);
        question_text = findViewById(R.id.text_question);
        nextButton = findViewById(R.id.next_button);
        previousButton = findViewById(R.id.previous_button);
        Answer = findViewById(R.id.answer);

        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        previousButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.FalseButton:
//                Toast.makeText(MainActivity.this,"FALSE",Toast.LENGTH_LONG).show();
                isAnswerCorrect(false);
                break;
            case R.id.TrueButton:
//                Toast.makeText(MainActivity.this,"TRUE",Toast.LENGTH_LONG).show();
                isAnswerCorrect(true);
                break;
            case R.id.next_button:
                currentQuestionIndex = (currentQuestionIndex + 1)% questionBank.length;
                updateQuestion();
                break;
            case R.id.previous_button:
                if(currentQuestionIndex>=1)
                {
                    currentQuestionIndex--;
                    updateQuestion();
                }
        }
    }
    public void updateQuestion()
    {
        Log.d("Next", "onClick: " + currentQuestionIndex);
        Answer.setText("");
        trueButton.setBackgroundColor(getResources().getColor(R.color.white));
        falseButton.setBackgroundColor(getResources().getColor(R.color.white));
        question_text.setText(questionBank[currentQuestionIndex].getAnswerResId());
    }

    public void isAnswerCorrect(boolean selectedAnswer)
    {
        boolean realAnswer = questionBank[currentQuestionIndex].isAnswerTrue();
        if(realAnswer == selectedAnswer)
        {
            Answer.setText(R.string.correct_answer);
            Answer.setTextColor(getResources().getColor(R.color.green));
        }
        else
        {
            Answer.setText(R.string.wrong_answer);
            Answer.setTextColor(getResources().getColor(R.color.red));
        }
        if(realAnswer)
        {
            trueButton.setBackgroundColor(getResources().getColor(R.color.green));
            falseButton.setBackgroundColor(getResources().getColor(R.color.red));
        }
        else
        {
            trueButton.setBackgroundColor(getResources().getColor(R.color.red));
            falseButton.setBackgroundColor(getResources().getColor(R.color.green));
        }
    }


}