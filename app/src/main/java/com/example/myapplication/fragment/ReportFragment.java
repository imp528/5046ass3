package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.databinding.MapBinding;
import com.example.myapplication.databinding.ReportFragmentBinding;
import com.example.myapplication.viewmodel.SharedViewModel;

public class ReportFragment extends Fragment {
    private SharedViewModel model;
    ReportFragmentBinding addBinding;
    public ReportFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the View for this fragment
        addBinding = ReportFragmentBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addBinding = null;
    }
}
