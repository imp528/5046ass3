package com.example.myapplication.fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.FrequencyFragmentBinding;
import com.example.myapplication.databinding.ViewFragmentBinding;
import com.example.myapplication.entity.Record;
import com.example.myapplication.viewmodel.RecordViewModel;
import com.example.myapplication.viewmodel.SharedViewModel;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FrequencyFragment extends Fragment {
    private FrequencyFragmentBinding binding;
    public FrequencyFragment(){}
    private PieChart pieChart;
    private RecordViewModel recordViewModel;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the View for this fragment using the binding
        binding = FrequencyFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        pieChart = binding.chart1;
        binding.chart1.setHoleRadius(0f);
        binding.chart1.setTransparentCircleRadius(0f);
        setupPieChart();
        loadPieChartData();


        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setupPieChart(){
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
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
    private void loadPieChartData(){
        ArrayList<PieEntry> entries = new ArrayList<>();

        recordViewModel = new ViewModelProvider(getActivity()).get(RecordViewModel.class);
        CompletableFuture<List<Record>> list = recordViewModel.getAllList();
        list.thenApply(l -> {
            Hashtable<String, Integer> pain_dic = new Hashtable<String, Integer>();
            pain_dic.put("back",0);
            pain_dic.put("neck",0);
            pain_dic.put("head",0);
            pain_dic.put("knees",0);
            pain_dic.put("hips",0);
            pain_dic.put("abdomen",0);
            pain_dic.put("elbows",0);
            pain_dic.put("shoulders",0);
            pain_dic.put("shins",0);
            pain_dic.put("jaw",0);
            pain_dic.put("facial",0);
            for (Record r: l){
                if (r.painLocation.equals("back")){
                    pain_dic.put("back", pain_dic.get("back") +1);
                }
                if (r.painLocation.equals("neck")){
                    pain_dic.put("neck", pain_dic.get("neck") +1);
                }
                if (r.painLocation.equals("head")){
                    pain_dic.put("head", pain_dic.get("head") +1);
                }
                if (r.painLocation.equals("knees")){
                    pain_dic.put("knees", pain_dic.get("knees") +1);
                }
                if (r.painLocation.equals("hips")){
                    pain_dic.put("hips", pain_dic.get("hips") +1);
                }
                if (r.painLocation.equals("abdomen")){
                    pain_dic.put("abdomen", pain_dic.get("abdomen") +1);
                }
                if (r.painLocation.equals("shoulders")){
                    pain_dic.put("shoulders", pain_dic.get("shoulders") +1);
                }
                if (r.painLocation.equals("shins")){
                    pain_dic.put("shins", pain_dic.get("shins") +1);
                }
                if (r.painLocation.equals("jaw")){
                    pain_dic.put("jaw", pain_dic.get("jaw") +1);
                }
                if (r.painLocation.equals("facial")){
                    pain_dic.put("facial", pain_dic.get("facial") +1);
                }
            }
            if (pain_dic.get("back")!=0)
                entries.add(new PieEntry((float)pain_dic.get("back")/pain_dic.size(), "back"));
            if (pain_dic.get("neck")!=0)
                entries.add(new PieEntry((float)pain_dic.get("neck")/pain_dic.size(), "neck"));
            if (pain_dic.get("head")!=0)
                entries.add(new PieEntry((float)pain_dic.get("head")/pain_dic.size(), "head"));
            if (pain_dic.get("knees")!=0)
                entries.add(new PieEntry((float)pain_dic.get("knees")/pain_dic.size(), "knees"));
            if (pain_dic.get("hips")!=0)
                entries.add(new PieEntry((float)pain_dic.get("hips")/pain_dic.size(), "hips"));
            if (pain_dic.get("abdomen")!=0)
                entries.add(new PieEntry((float)pain_dic.get("abdomen")/pain_dic.size(), "abdomen"));
            if (pain_dic.get("shoulders")!=0)
                entries.add(new PieEntry((float)pain_dic.get("shoulders")/pain_dic.size(), "shoulders"));
            if (pain_dic.get("shins")!=0)
                entries.add(new PieEntry((float)pain_dic.get("shins")/pain_dic.size(), "shins"));
            if (pain_dic.get("jaw")!=0)
                entries.add(new PieEntry((float)pain_dic.get("jaw")/pain_dic.size(), "jaw"));
            if (pain_dic.get("facial")!=0)
                entries.add(new PieEntry((float)pain_dic.get("facial")/pain_dic.size(), "facial"));

            //add colors
            ArrayList<Integer> colors = new ArrayList<>();
//        for (int color: ColorTemplate.MATERIAL_COLORS){
//            colors.add(color);
//        }
//        for (int color: ColorTemplate.VORDIPLOM_COLORS){
//            colors.add(color);
//        }

            for (int c : ColorTemplate.VORDIPLOM_COLORS)
                colors.add(c);

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
            data.setDrawValues(true);
            data.setValueFormatter(new PercentFormatter(pieChart));
            data.setValueTextSize(12f);
            data.setValueTextColor(Color.BLACK);

            pieChart.setData(data);
            pieChart.invalidate();

            return null;
        });


    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}