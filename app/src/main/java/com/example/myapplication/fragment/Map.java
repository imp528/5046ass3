package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.HomeFragmentBinding;
import com.example.myapplication.databinding.MapBinding;
import com.example.myapplication.viewmodel.SharedViewModel;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;

public class Map extends Fragment {
    private MapView mapView;
    private SharedViewModel model;
    private MapBinding binding;
    public Map(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the View for this fragment
        binding = MapBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        String token = getString(R.string.mapbox_access_token);
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

    }
}
