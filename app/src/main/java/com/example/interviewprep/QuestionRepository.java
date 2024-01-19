package com.example.interviewprep;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class QuestionRepository {

    private QuestionDao questionDao;
    private LiveData<List<QuestionEntity>> allQuestions;

    public QuestionRepository(Application application) {
        QuestionDatabase database = QuestionDatabase.getInstance(application);
        questionDao = database.questionDao();
        allQuestions = questionDao.getAllQuestions();
    }

    public void insert(QuestionEntity questionEntity) {
        new InsertQuestionAsyncTask(questionDao).execute(questionEntity);
    }

    public LiveData<List<QuestionEntity>> getAllQuestions() {
        return allQuestions;
    }

    private static class InsertQuestionAsyncTask extends AsyncTask<QuestionEntity, Void, Void> {
        private QuestionDao questionDao;

        private InsertQuestionAsyncTask(QuestionDao questionDao) {
            this.questionDao = questionDao;
        }

        @Override
        protected Void doInBackground(QuestionEntity... questionEntities) {
            questionDao.insert(questionEntities[0]);
            return null;
        }
    }
}

