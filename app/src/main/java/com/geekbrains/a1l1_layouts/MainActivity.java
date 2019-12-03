package com.geekbrains.a1l1_layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private CheckBox checkHum, checkCloud;
    private Button buttonShow;
    private String parcelNameCity, parcelHum, parcelCloud;
    private int counterCity;
    final static String KEY_TO_DATA = "keyToData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        switchActivityClick();
        spinnerClick();
        setOnRadioBtnClick();
    }

    private void switchActivityClick() {
        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Parcel parcel = new Parcel();
                parcel.nameCity = parcelNameCity;
                parcel.infoHum = parcelHum;
                parcel.infoCloud = parcelCloud;
                parcel.infoCounterCity = counterCity;

                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                intent.putExtra(KEY_TO_DATA, parcel);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        spinner = findViewById(R.id.spinner_city);

        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(this, R.array.cityslist, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        checkHum = findViewById(R.id.humidityCheck);
        checkCloud = findViewById(R.id.cloudCheck);
        buttonShow = findViewById(R.id.bottomShow);
    }


    private void spinnerClick() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                String[] choose = getResources().getStringArray(R.array.cityslist);
                parcelNameCity = choose[selectedItemPosition];
                counterCity = spinner.getSelectedItemPosition();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setOnRadioBtnClick() {
        checkHum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkHum.isChecked())
                    parcelHum = getString(R.string.humidity_43);
                 else
                    parcelHum = "";
            }
        });
        checkCloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkCloud.isChecked())
                    parcelCloud = getString(R.string.cloud_80);
                else
                    parcelCloud = "";
            }
        });
    }

}
