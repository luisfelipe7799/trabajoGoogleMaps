package com.example.trabajogooglemaps;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.trabajogooglemaps.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    LocationManager manejadorLocacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //String userZoom = getIntent().getStringExtra("keyZoom");

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }

    //aqui iba el escuchacambios

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap ) {
        mMap = googleMap;
        Double userLatitud = Double.valueOf(getIntent().getStringExtra("keyLatitud"));
        Double userLongitud = Double.valueOf(getIntent().getStringExtra("keyLongitud"));
        Float userZoom = Float.valueOf(getIntent().getStringExtra("keyZoom"));
        String userChecked = (getIntent().getStringExtra("keyChecked"));
        //Bundle userChecked = getIntent().getExtras();

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(userLatitud, userLongitud);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Seleccion"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(userLatitud, userLongitud),userZoom ));

        if (userChecked != null) {

            LatLngBounds.Builder constructor = new LatLngBounds.Builder();

            constructor.include(sydney);
            LatLngBounds limites = constructor.build();

            int ancho = getResources().getDisplayMetrics().widthPixels;
            int alto = getResources().getDisplayMetrics().heightPixels;
            int padding = (int) (alto * 0.25); //25% de padding superior e inferior

            CameraUpdate centrarmarcador = CameraUpdateFactory.newLatLngBounds(limites, ancho, alto, padding);

            mMap.animateCamera(centrarmarcador);



        }




    }


}