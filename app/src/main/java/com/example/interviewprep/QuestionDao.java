package com.example.interviewprep;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(QuestionEntity question);

    @Query("SELECT * FROM question_table")
    LiveData<List<QuestionEntity>> getAllQuestions(); // Update the return type


}
