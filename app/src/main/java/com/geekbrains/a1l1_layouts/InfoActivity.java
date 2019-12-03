package com.geekbrains.a1l1_layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Vector;

public class InfoActivity extends AppCompatActivity {
    private ImageView imageGerb;
    private TextView nameCity, temperature, humidity, cloud;
    private String nameCityStr, infoHumStr, infoCloudStr;
    private int infoCounterCitys;
    private Button browserBtn;

    private Vector<Integer> citiesListImage = new Vector<>();
    private Vector<String> temperatureList = new Vector<>();
    private Vector<String> urlList = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        getParcelFromFirstActivity();
        initViews();
        openBrowserClick();
    }

    private void getParcelFromFirstActivity() {
        Parcel parcel = (Parcel)getIntent().getSerializableExtra(MainActivity.KEY_TO_DATA);
        nameCityStr = parcel.nameCity;
        infoHumStr = parcel.infoHum;
        infoCloudStr = parcel.infoCloud;
        infoCounterCitys = parcel.infoCounterCity;
    }

    private void initViews() {
        nameCity = findViewById(R.id.name_city);
        temperature = findViewById(R.id.temperature);
        imageGerb = findViewById(R.id.gerbImg);
        humidity = findViewById(R.id.humidity);
        cloud = findViewById(R.id.cloud);
        browserBtn = findViewById(R.id.bottomBrowser);

        citiesListImage.add(0, R.drawable.iconivanovo);
        citiesListImage.add(1, R.drawable.iconyaroslavl);
        citiesListImage.add(2, R.drawable.iconmoscow);

        temperatureList.add(0, getString(R.string.t_ivanovo));
        temperatureList.add(1, getString(R.string.t_yaroslavl));
        temperatureList.add(2, getString(R.string.t_moscow));

        urlList.add(0, getString(R.string.urlIvanovo));
        urlList.add(1, getString(R.string.urlYaroslavl));
        urlList.add(2, getString(R.string.urlMoscow));

        nameCity.setText(nameCityStr);
        humidity.setText(infoHumStr);
        cloud.setText(infoCloudStr);
        temperature.setText(temperatureList.get(infoCounterCitys));
        imageGerb.setImageResource(citiesListImage.get(infoCounterCitys));
    }

    private void openBrowserClick() {
        browserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlList.get(infoCounterCitys);
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}
