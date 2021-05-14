package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.AddFragmentBinding;
import com.example.myapplication.viewmodel.SharedViewModel;

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
        addBinding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = addBinding.editText.getText().toString();
                if (!message.isEmpty() ) {
                    model.setMessage(message);
                }
            }
        });
        addBinding.clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBinding.editText.setText("");
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
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addBinding = null;
    }
}
