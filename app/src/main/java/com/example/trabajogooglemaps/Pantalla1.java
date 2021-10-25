package com.example.trabajogooglemaps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class Pantalla1 extends AppCompatActivity {

    Button sgte;
    EditText zoom1;
    EditText longitud1;
    EditText latitud1;
    CheckBox asCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla1);

        zoom1 = (EditText) findViewById(R.id.zoom);
        longitud1 = (EditText) findViewById(R.id.longitud);
        latitud1 = (EditText) findViewById(R.id.latitud);
        asCheckBox = (CheckBox)findViewById(R.id.checkBox);

        sgte = (Button) findViewById(R.id.buscar);

        sgte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userZoom = zoom1.getText().toString();
                String userLatitud = latitud1.getText().toString();
                String userLongitud = longitud1.getText().toString();
                CheckBox userCheckbox = asCheckBox;


                Intent intent = new Intent(Pantalla1.this, MapsActivity.class);
                intent.putExtra("keyZoom",userZoom);
                intent.putExtra("keyLatitud",userLatitud);
                intent.putExtra("keyLongitud",userLongitud);

                if(asCheckBox.isChecked()){
                    String si;
                    intent.putExtra("keyChecked", String.valueOf(userCheckbox));
                }
                startActivity(intent);
            }
        });


    }
}