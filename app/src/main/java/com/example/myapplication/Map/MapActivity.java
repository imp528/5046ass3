package com.example.myapplication.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.ui.login.LoginActivity;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.annotation.Symbol;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions;
import com.mapbox.mapboxsdk.plugins.markerview.MarkerView;
import com.mapbox.mapboxsdk.plugins.markerview.MarkerViewManager;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MapActivity extends AppCompatActivity {

    private MapView mapView;
    private Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String token = getString(R.string.mapbox_access_token);
        Mapbox.getInstance(this, token);
        setContentView(R.layout.activity_map); //lat and long are hardcoded here but could be provided at run time
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        searchBtn = findViewById(R.id.search);
        Button backBtn = findViewById(R.id.back);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LatLng latLng1 = getLocationFromAddress(MapActivity.this, ((EditText)findViewById(R.id.address)).getText().toString());
                if (latLng1 != null){
                    mapView.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull final MapboxMap mapboxMap) {

                            mapboxMap.setStyle(Style.MAPBOX_STREETS, style -> {
                                CameraPosition position = new CameraPosition.Builder().target(latLng1).zoom(13).build();
                                mapboxMap.setCameraPosition(position);
                                MarkerOptions markerOptions = new MarkerOptions();
                                markerOptions.position(latLng1);
                                markerOptions.title("Here");
                                mapboxMap.addMarker(markerOptions);
                            });

                        }
                    });
            }
                else {
                    Toast.makeText(MapActivity.this, "invalid address", Toast.LENGTH_LONG).show();
                }
        }});
        final LatLng latLng = new LatLng(-37.876823, 145.140213);
        //set map
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull final MapboxMap mapboxMap) {

                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                          CameraPosition position = new CameraPosition.Builder().target(latLng).zoom(13).build();
                          mapboxMap.setCameraPosition(position);
                          MarkerOptions markerOptions  = new MarkerOptions();
                          markerOptions.position(latLng);
                          markerOptions.title("Here");
                          mapboxMap.addMarker(markerOptions);
                    }
                });
            }
        });
    }
// Use geocoder to convert the input address to location
    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 1);
            if (address.size() == 0) {
                return null;
            }

            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return p1;
    }


    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}