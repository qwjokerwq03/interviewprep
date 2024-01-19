package com.example.interviewprep;

//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//}
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private QuestionGenerator questionGenerator;
    private QuestionViewModel questionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionGenerator = new QuestionGenerator(getApplication());
        questionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);

        Button generateQuestionsButton = findViewById(R.id.generateQuestionsButton);
        generateQuestionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateQuestions();
            }
        });

        final TextView scoreTextView = findViewById(R.id.scoreTextView);
        questionViewModel.getAllQuestions().observe(this, new Observer<List<QuestionEntity>>() {
            @Override
            public void onChanged(List<QuestionEntity> questionEntities) {
                int score = questionEntities.size();
                scoreTextView.setText("Score: " + score);
            }
        });
    }

    private void generateQuestions() {
        String newQuestion = questionGenerator.generateQuestion();
        questionViewModel.insert(new QuestionEntity(newQuestion));
    }
}
