package com.example.myapplication.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.myapplication.entity.Record;
import com.example.myapplication.repository.RecordRepository;

import java.util.List;

public class RecordViewModel {
    private RecordRepository recordRepository;
    private LiveData<List<Record>> allRecords;

    public RecordViewModel (Application application){
        super();
        recordRepository = new RecordRepository(application);
        allRecords = recordRepository.getAllRecords();
    }

    public LiveData<List<Record>> getAllRecords(){
        return allRecords;
    }

    public void insert(Record record){
        recordRepository.insert(record);
    }

    public void update(Record record){
        recordRepository.updateRecord(record);
    }
}
