package com.example.interviewprep;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {QuestionEntity.class}, version = 1, exportSchema = false)
public abstract class QuestionDatabase extends RoomDatabase {
    public abstract QuestionDao questionDao();

    private static QuestionDatabase instance;

    public static synchronized QuestionDatabase getInstance(Application context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            QuestionDatabase.class, "question_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
