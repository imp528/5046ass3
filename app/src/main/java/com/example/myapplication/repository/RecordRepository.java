package com.example.myapplication.repository;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.myapplication.dao.RecordDao;
import com.example.myapplication.database.PainRecord;
import com.example.myapplication.entity.Record;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class RecordRepository {
    private RecordDao recordDao;
    private LiveData<List<Record>> allRecords;

    public RecordRepository(Application application){
        PainRecord painRecord = PainRecord.getInstance(application);
        recordDao = painRecord.recordDao();
        allRecords = recordDao.getAll();
    }

    public LiveData<List<Record>> getAllRecords(){
        return allRecords;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<List<Record>> findAllList(){
        return CompletableFuture.supplyAsync(new Supplier<List<Record>>() {
            @Override
            public List<Record> get() {
                return recordDao.getAllList();
            }
        }, PainRecord.databaseWriteExecutor);
    }

    public void insert(final Record record){
        PainRecord.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                recordDao.insert(record);
            }
        });
    }

    public void delete(final Record record){
        PainRecord.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                recordDao.delete(record);
            }
        });
    }

    public void updateRecord(final Record record){
        PainRecord.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                recordDao.updateRecord(record);
            }
        });
    }

    public void deleteAll(){
        PainRecord.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                recordDao.deleteAll();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<Record> findByDate(final String date){
        return CompletableFuture.supplyAsync(new Supplier<Record>() {
            @Override
            public Record get() {
                return recordDao.findDate(date);
            }
        }, PainRecord.databaseWriteExecutor);
    }
}

