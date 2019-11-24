package com.geekbrains.a1l1_intro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn_name;
    private Button btn_eat;
    private TextView textView;
    private EditText editText;
    private RadioGroup radioGroup;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setOnChangeTextBtnClick();
        setOnRadioBtnClick();
    }

    private void initViews () {
        btn_name = findViewById(R.id.button_1);
        btn_eat = findViewById(R.id.button_2);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.edit_txt);
        radioGroup = findViewById(R.id.radioGroup);
        imageView = findViewById(R.id.imageView1);
    }

    private void setOnChangeTextBtnClick () {
        btn_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editTextStr = editText.getText().toString();
                textView.setText(editTextStr);
            }
        });

        btn_eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.cat_2);
            }
        });
    }

    private void setOnRadioBtnClick() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioBtnOption1) {
                    Toast.makeText(getApplicationContext(), "Вы выбрали рыбу", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(getApplicationContext(), "Вы выбрали мясо", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }
}
