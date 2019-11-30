package com.geekbrains.a1l1_layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    private ImageView imageGerb;
    private TextView nameCity, temperature, humidity, cloud;
    private int counterNext = 0;
    private int counterPrev = 0;
    private Spinner spinner;
    private CheckBox checkHum, checkCloud;
    private String humidityStr, cloudStr;
    private Vector<Integer> citiesListImage = new Vector<>();
    private Vector<String> temperatureList = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String instanceState;
        if (savedInstanceState == null){
            instanceState = "Первый запуск!";
        }
        else{
            instanceState = "Повторный запуск!";
        }
        Toast.makeText(getApplicationContext(), instanceState + " - onCreate()", Toast.LENGTH_SHORT).show();


        initViews();
        spinnerClick();
        setOnRadioBtnClick();
    }

    private void initViews() {
        nameCity = findViewById(R.id.name_city);
        temperature = findViewById(R.id.temperature);
        imageGerb = findViewById(R.id.gerbImg);
        humidity = findViewById(R.id.humidity);
        cloud = findViewById(R.id.cloud);

        spinner = findViewById(R.id.spinner_city);

        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(this, R.array.cityslist, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        citiesListImage.add(0, R.drawable.iconivanovo);
        citiesListImage.add(1, R.drawable.iconyaroslavl);
        citiesListImage.add(2, R.drawable.iconmoscow);

        temperatureList.add(0, getString(R.string.t_ivanovo));
        temperatureList.add(1, getString(R.string.t_yaroslavl));
        temperatureList.add(2, getString(R.string.t_moscow));

        checkHum = findViewById(R.id.humidityCheck);
        checkCloud = findViewById(R.id.cloudCheck);
    }


    private void spinnerClick() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                String[] choose = getResources().getStringArray(R.array.cityslist);
                String nameCitySpinner = choose[selectedItemPosition];
                nameCity.setText(nameCitySpinner);

                int number_position_city = spinner.getSelectedItemPosition();
                imageGerb.setImageResource(citiesListImage.get(number_position_city));
                String temperatureStr = temperatureList.get(number_position_city);
                temperature.setText(temperatureStr);

                counterNext = number_position_city;
                counterPrev = number_position_city;
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
                    humidityStr = getString(R.string.humidity_43);
                 else
                    humidityStr = "";
                humidity.setText(humidityStr);
            }
        });
        checkCloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkCloud.isChecked())
                    cloudStr = getString(R.string.cloud_80);
                else
                    cloudStr = "";
                cloud.setText(cloudStr);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
        Toast.makeText(getApplicationContext(), "Повторный запуск!! - onRestoreInstanceState()",
                Toast.LENGTH_SHORT).show();
        humidityStr = saveInstanceState.getString("HumidityStr");
        cloudStr = saveInstanceState.getString("CloudStr");
        humidity.setText(humidityStr);
        cloud.setText(cloudStr);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState){
        Toast.makeText(getApplicationContext(), "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        saveInstanceState.putString("HumidityStr", humidityStr);
        saveInstanceState.putString("CloudStr", cloudStr);
        super.onSaveInstanceState(saveInstanceState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "onRestart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_SHORT).show();
    }

}
