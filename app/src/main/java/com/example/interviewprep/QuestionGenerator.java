package com.example.interviewprep;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
public class QuestionGenerator {
    private List<String> questions;
    private QuestionDao questionDao;

    private QuestionViewModel questionViewModel;

    public QuestionGenerator(Application application) {
        new Thread(new FetchQuestionsTask()).start();
        QuestionDatabase database = Room.databaseBuilder(application, QuestionDatabase.class, "question_database")
                .build();
        questionDao = database.questionDao();

    }
    private class FetchQuestionsTask implements Runnable {
        @Override
        public void run() {
            List<String> result = fetchQuestionsFromChatGPT();

            // Update UI or store questions as needed.
            questions = result;
        }
    }

    private List<String> fetchQuestionsFromChatGPT() {
        List<String> fetchedQuestions = new ArrayList<>();

//        try {
//            OkHttpClient client = new OkHttpClient();
//            String gpt3ApiKey = "your API_KEY";
//            String prompt = "Generate 20 Java Full Stack developer/angular concepts Interview Questions.";
//            String url = "https://api.openai.com/v1/engines/gpt-3.5-turbo/completions";
//
//            MediaType mediaType = MediaType.parse("application/json");
//            String requestBody = "{ \"prompt\": \"" + prompt + "\", \"max_tokens\": 200 }";
//
//            Request request = new Request.Builder()
//                    .url(url)
//                    .post(RequestBody.create(mediaType, requestBody))
//                    .addHeader("Authorization", "Bearer " + gpt3ApiKey)
//                    .build();
//
//            Response response = client.newCall(request).execute();
//            String responseBody = response.body().string();
//
//            // Extract questions from the API response. You may need to adjust this based on the actual response format.
//            // For example, if the response is in JSON format, you might need to use a JSON parsing library.
//            // For simplicity, assuming each line in the response is a question.
//            String[] lines = responseBody.split("\n");
//            for (String line : lines) {
//                fetchedQuestions.add(line.trim());
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return fetchedQuestions;
    }

    public String generateQuestion() {
        Random random = new Random();
        int index = random.nextInt(questions.size());
        String newQuestion = questions.get(index);
        List<QuestionEntity> allQuestions = questionDao.getAllQuestions().getValue();
        boolean isUnique = true;
        for (QuestionEntity entity : allQuestions) {
            if (entity.getQuestion().equals(newQuestion)) {
                isUnique = false;
                break;
            }
        }

        if (isUnique) {
            questionDao.insert(new QuestionEntity(newQuestion));
        }

        return newQuestion;
    }

}

