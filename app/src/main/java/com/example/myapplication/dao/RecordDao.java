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

    @Query("Select * from record Order by rid asc")
    List<Record> getAllList();

    @Insert
    void insert(Record record);

    @Delete
    void delete(Record record);

    @Update
    void updateRecord(Record record);

    @Query("Delete from record")
    void deleteAll();

    @Query("Select * from record where date = :Date LIMIT 1")
    Record findDate(String Date);
}
