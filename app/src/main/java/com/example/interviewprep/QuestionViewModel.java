package com.example.interviewprep;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class QuestionViewModel extends AndroidViewModel {

    private QuestionRepository questionRepository;
    private LiveData<List<QuestionEntity>> allQuestions;

    public QuestionViewModel(@NonNull Application application) {
        super(application);
        questionRepository = new QuestionRepository(application);
        allQuestions = questionRepository.getAllQuestions();
    }

    public void insert(QuestionEntity questionEntity) {
        questionRepository.insert(questionEntity);
    }

    public LiveData<List<QuestionEntity>> getAllQuestions() {
        return allQuestions;
    }
}


