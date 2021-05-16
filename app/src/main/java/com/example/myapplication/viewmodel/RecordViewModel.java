package com.example.myapplication.viewmodel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myapplication.entity.Record;
import com.example.myapplication.repository.RecordRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RecordViewModel extends AndroidViewModel {
    private RecordRepository recordRepository;
    private LiveData<List<Record>> allRecords;

    public RecordViewModel (Application application){
        super(application);
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

    public void deleteAll(){recordRepository.deleteAll();}

    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<List<Record>> getAllList(){
        return recordRepository.findAllList();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<Record> findByDate(final String date){
        return recordRepository.findByDate(date);
    }
}
