package com.example.myapplication.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.entity.Record;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

class RecordAdaptor extends RecyclerView.Adapter<RecordAdaptor.ViewHolder>{
    List<Record> recordList;

    public RecordAdaptor(List<Record> recordList) {
        this.recordList = recordList;
    }

    @Override
    public RecordAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_row,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecordAdaptor.ViewHolder holder, int position) {
        if (FirebaseAuth.getInstance().getCurrentUser().getEmail().equals(recordList.get(position).email)){
            holder.date.setText("date: " + recordList.get(position).date);
            holder.pressure.setText("pressure:" + (double)recordList.get(position).pressure/10+"kp");
            holder.humidity.setText("humidity:"+recordList.get(position).humidity+"%");
            holder.temperature.setText("temperature:"+recordList.get(position).temperature+"°C");
            holder.painIntensityLevel.setText("pain intensity level:"+recordList.get(position).painIntensityLevel);
            holder.steps.setText("steps"+recordList.get(position).steps);
            holder.painLocation.setText("pain location:"+recordList.get(position).painLocation);
            holder.mood.setText("mood:"+recordList.get(position).mood);
        }
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView date;
        public TextView painLocation;
        public TextView painIntensityLevel;
        public TextView steps;
        public TextView temperature;
        public TextView humidity;
        public TextView pressure;
        public TextView mood;

        public ViewHolder(View itemView){
            super(itemView);
            date = itemView.findViewById(R.id.date);
            painLocation = itemView.findViewById(R.id.pain_location);
            painIntensityLevel = itemView.findViewById(R.id.pain_intensity_level);
            steps = itemView.findViewById(R.id.steps);
            temperature = itemView.findViewById(R.id.temperature);
            humidity = itemView.findViewById(R.id.humidity);
            pressure = itemView.findViewById(R.id.pressure);
            mood = itemView.findViewById(R.id.mood);
        }
    }
}
