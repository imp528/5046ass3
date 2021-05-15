package com.example.myapplication.fragment;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.Alarm;
import com.example.myapplication.RegisterActivity;
import com.example.myapplication.dao.RecordDao;
import com.example.myapplication.database.PainRecord;
import com.example.myapplication.databinding.AddFragmentBinding;
import com.example.myapplication.viewmodel.SharedViewModel;

import java.util.Calendar;

public class AddFragment extends Fragment {
    private AddFragmentBinding addBinding;
    public AddFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        addBinding = AddFragmentBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();
        SharedViewModel model = new
                ViewModelProvider(getActivity()).get(SharedViewModel.class);


        addBinding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The edit text only allow numbers 0-10000, here we use regex to judge whether it is int, and use parseInt to judge whether it is less than 10000
                if (TextUtils.isEmpty(addBinding.editText1.getText().toString())){
                    addBinding.editText1.setError("Empty data!");
                    return;
                }
                if (addBinding.editText1.getText().toString().matches("[0-9]*")){
                    if (Integer.parseInt(addBinding.editText1.getText().toString())>10000) {
                        addBinding.editText1.setError("You can only enter numbers[0-10000]!");
                        return;
                    }
                }
                else{
                    addBinding.editText1.setError("You can only enter numbers[0-10000]!");
                    return;
                }
                if (TextUtils.isEmpty(addBinding.editText.getText().toString())) {
                    addBinding.editText.setError("Empty data!");
                    return;
                }
                if (addBinding.editText.getText().toString().matches("[0-9]*")){
                    if (Integer.parseInt(addBinding.editText.getText().toString())>10000) {
                        addBinding.editText.setError("You can only enter numbers[0-10000]!");
                        return;
                    }
                }
                else{
                    addBinding.editText.setError("You can only enter numbers[0-10000]!");
                    return;
                }
                // After saving, only edit button is allowed to use.
                addBinding.saveBtn.setEnabled(false);
                addBinding.seekBar.setEnabled(false);
                addBinding.editText.setEnabled(false);
                addBinding.spinner.setEnabled(false);
                addBinding.spinner2.setEnabled(false);
                addBinding.editText.setEnabled(false);
                addBinding.editText1.setEnabled(false);

                Toast.makeText(getContext(),"Save succseeful", Toast.LENGTH_LONG).show();
            }
        });
        addBinding.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBinding.saveBtn.setEnabled(true);
                addBinding.seekBar.setEnabled(true);
                addBinding.editText.setEnabled(true);
                addBinding.spinner.setEnabled(true);
                addBinding.spinner2.setEnabled(true);
                addBinding.editText.setEnabled(true);
                addBinding.editText1.setEnabled(true);
            }
        });

        //When user slide the seekbar, the app should display the pain level
        addBinding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                addBinding.textView3.setText("Please choose pain level:" + progress+ "\n");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // The button to set alarm, using time picker
        addBinding.setAlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hourOfDay  = calendar.get(Calendar.HOUR_OF_DAY);
                int minute  = calendar.get(Calendar.MINUTE);
                new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        addBinding.textView8.setText("The alarm you set is: "+hourOfDay+":"+minute);
                        setAlarm(calendar.getTimeInMillis());
                    }
                }, hourOfDay, minute, true).show();
            }
        });
        return view;
    }

    //Using alarm manager to set alarm
    private void setAlarm(long timeInMillis) {
        AlarmManager alarmManager =
                (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getActivity(), Alarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent,0);
        alarmManager.setRepeating(AlarmManager.RTC,timeInMillis,AlarmManager.INTERVAL_DAY,pendingIntent);
        Toast.makeText(getContext(), "Alarm is set.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addBinding = null;
    }
}
