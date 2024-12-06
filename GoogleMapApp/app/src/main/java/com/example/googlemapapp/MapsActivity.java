package com.example.googlemapapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.googlemapapp.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        SupportMapFragment mapFragment= (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap=googleMap;

        //Circle

        /*mMap.addCircle(new CircleOptions()
                .center(latLng)
                    .radius(1000f)
                        .fillColor(R.color.lightBlue)
                            .strokeColor(R.color.blue));*/
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        //GEOCODER
        mMap.setOnMapClickListener(latLng -> {

            Geocoder geocoder=new Geocoder(MapsActivity.this);
            MarkerOptions markerOptions=new MarkerOptions().position(latLng).title("Clicked here");
            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,16f));
            try {
                ArrayList<Address> locationInfo=(ArrayList<Address>) geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                Log.d("Info",locationInfo.get(0).getAddressLine(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        
        

    }
}