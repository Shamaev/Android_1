package com.geekbrains.a1l1_layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private Button prev_button, next_button;
    private ImageView imageGerb;
    private TextView nameCity, temperature;
    private int counterNext = 0;
    private int counterPrev = 0;
    private Spinner spinner;

    private Vector<String> citiesListName = new Vector<>();
    private Vector<Integer> citiesListImage = new Vector<>();
    private Vector<String> temperatureList = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        spinnerClick();
        clickNext ();
    }

    private void initViews() {
        prev_button = findViewById(R.id.prev_click);
        next_button = findViewById(R.id.next_click);
        nameCity = findViewById(R.id.name_city);
        temperature = findViewById(R.id.temperature);
        imageGerb = findViewById(R.id.gerbImg);

        spinner = findViewById(R.id.spinner_city);

        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(this, R.array.cityslist, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);


        citiesListName.add(0, getString(R.string.ivanovo));
        citiesListName.add(1, getString(R.string.yaroslavl));
        citiesListName.add(2, getString(R.string.moscow));

        citiesListImage.add(0, R.drawable.iconivanovo);
        citiesListImage.add(1, R.drawable.iconyaroslavl);
        citiesListImage.add(2, R.drawable.iconmoscow);

        temperatureList.add(0, getString(R.string.t_ivanovo));
        temperatureList.add(1, getString(R.string.t_yaroslavl));
        temperatureList.add(2, getString(R.string.t_moscow));
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

                // Тут я задаю цвет и размер шрифта. И это срабатывает, но при
                // повороте экрана приложение падает. Пишет, что из-за этого
//                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
//                ((TextView) parent.getChildAt(0)).setTextSize(18);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private int sourceNext () {
        counterNext++;
        if (counterNext >2) {
           return counterNext = 0;
        }
        return counterNext;
    }

    private int sourcePrev () {
        counterPrev--;
        if (counterPrev <0) {
            return counterPrev = 2;
        }
        return counterPrev;
    }

    private void clickNext () {
        prev_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int source = sourcePrev();
                String nameCityPrev = String.valueOf(citiesListName.get(source));
                nameCity.setText(nameCityPrev);
                imageGerb.setImageResource(citiesListImage.get(source));

                String temperatureStr = temperatureList.get(source);
                temperature.setText(temperatureStr);
            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int source = sourceNext();
                String nameCityNext = String.valueOf(citiesListName.get(source));
                nameCity.setText(nameCityNext);
                imageGerb.setImageResource(citiesListImage.get(source));

                String temperatureStr = temperatureList.get(source);
                temperature.setText(temperatureStr);
            }
        });
    }
}
