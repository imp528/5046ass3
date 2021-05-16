package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.ReportFragmentBinding;
import com.example.myapplication.entity.Record;
import com.example.myapplication.viewmodel.RecordViewModel;
import com.example.myapplication.viewmodel.SharedViewModel;

import java.util.List;

public class ReportFragment extends Fragment {
    private SharedViewModel model;
    ReportFragmentBinding addBinding;
    private RecordViewModel recordViewModel;

    public ReportFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the View for this fragment
        addBinding = ReportFragmentBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();
        recordViewModel = new ViewModelProvider(getActivity()).get(RecordViewModel.class);

        recordViewModel.getAllRecords().observe(getViewLifecycleOwner(), new Observer<List<Record>>() {
            @Override
            public void onChanged(List<Record> records) {
                RecyclerView recyclerView = addBinding.recyclerView;
//                String allRecords = "";
//                for (Record temp: records){
//                    String recordDetails = (temp.rid+"Date: "+temp.date+"    Pain Location: "+temp.painLocation+"    Pain Intensity Level: "+temp.painIntensityLevel+"    Steps taken: "+temp.steps
//                    + "    Temperature: " + temp.temperature + "°C  Humidity: "+ temp.humidity + "% Pressure: "+ (double)temp.pressure/10 + "kp");
//                    allRecords = allRecords + System.getProperty("line.separator") + recordDetails;
//                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                RecyclerView.Adapter adapter = new RecordAdaptor(records);
                recyclerView.setAdapter(adapter);

            }
        });

        addBinding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordViewModel.deleteAll();
            }
        });


        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addBinding = null;
    }
}
