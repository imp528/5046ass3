package com.example.myapplication.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.StepFragmentBinding;
import com.example.myapplication.databinding.ViewFragmentBinding;
import com.example.myapplication.entity.Record;
import com.example.myapplication.viewmodel.RecordViewModel;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class StepFragment extends Fragment {
    private StepFragmentBinding binding;
    private PieChart pieChart;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the View for this fragment using the binding
        binding = StepFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        pieChart = binding.chart1;
        setupPieChart();
        loadPieChartData();


        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setEntryLabelTextSize(Color.BLACK);
        pieChart.setCenterText("Pain Location");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadPieChartData() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("STEP", Context.MODE_PRIVATE);
        int step = sharedPreferences.getInt("step_today", 0);
        int goal = sharedPreferences.getInt("goal",0);
        entries.add(new PieEntry(step, "Today's step"));
        entries.add(new PieEntry(goal-step, "The step you still need reach"));

            //add colors
        ArrayList<Integer> colors = new ArrayList<>();
//        for (int color: ColorTemplate.MATERIAL_COLORS){
//            colors.add(color);
//        }
//        for (int color: ColorTemplate.VORDIPLOM_COLORS){
//            colors.add(color);
//        }


        for (int c : ColorTemplate.JOYFUL_COLORS)
                colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
                colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
                colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
                colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        PieDataSet dataSet = new PieDataSet(entries, "Pain Location Record");
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

            pieChart.setData(data);
            pieChart.invalidate();



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}