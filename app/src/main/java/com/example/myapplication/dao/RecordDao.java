package com.example.myapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.entity.Record;

import java.util.List;

@Dao
public interface RecordDao {
    @Query("Select * from record Order by rid asc")
    LiveData<List<Record>> getAll();

    @Insert
    void insert(Record record);

    @Delete
    void delete(Record record);

    @Update
    void updateRecord(Record record);
}
