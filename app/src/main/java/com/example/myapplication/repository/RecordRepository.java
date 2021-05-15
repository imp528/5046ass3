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
}
