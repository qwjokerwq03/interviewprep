package com.example.interviewprep;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "question_table")
public class QuestionEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    // Constructors


    public void setId(int id) {
        this.id = id;
    }
    private String question;

    public QuestionEntity(String question) {
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }
}

