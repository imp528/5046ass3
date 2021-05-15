package com.example.myapplication.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.myapplication.WeatherApi.WeatherApiClient;
import com.example.myapplication.WeatherApi.WeatherInterface;
import com.example.myapplication.WeatherApi.WeatherModel;
import com.example.myapplication.databinding.HomeFragmentBinding;
import com.example.myapplication.viewmodel.SharedViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private SharedViewModel model;
    private HomeFragmentBinding addBinding;
    public static String location;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the View for this fragment
        addBinding = HomeFragmentBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();
        WeatherInterface weatherApi = WeatherApiClient.getWeatherService();

        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());
        if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }

        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(requireActivity(), loc ->
                {
                    getActivity().runOnUiThread(() -> {
                        location = loc.getLatitude()+","+loc.getLongitude();
                        Call<WeatherModel> callAsync = weatherApi.getWeather(location, "today", "json", "5043e688390b4cc0a8683136211305");
                        callAsync.enqueue(new Callback<WeatherModel>(){

                            @Override
                            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                                if (response.isSuccessful()){
                                    getActivity().runOnUiThread(() -> {
                                        if (isAdded()) {
                                            Calendar c = Calendar.getInstance();
                                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                                            addBinding.date.setText("The date of today is:" + simpleDateFormat.format(c.getTime()));
                                            addBinding.temperature.setText("Temperature is: " + response.body().data.current_condition.get(0).temp_C + "°C");
                                            addBinding.humidity.setText("Humidity is:" + response.body().data.current_condition.get(0).humidity + "%");
                                            addBinding.pressure.setText("Pressure is:" + (double) response.body().data.current_condition.get(0).pressure / 10 + "kilopascals");
                                        }
                                    });
                                }

                            }

                            @Override
                            public void onFailure(Call<WeatherModel> call, Throwable t) {

                            }
                        });
                    });

                });

        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addBinding = null;
    }
}
