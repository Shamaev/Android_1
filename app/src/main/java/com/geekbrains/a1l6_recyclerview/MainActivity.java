package com.geekbrains.a1l6_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button addItemBtn, removeItemBtn, moveBtn;

    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initRecyclerView();
        initBtnsOnClick();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        addItemBtn = findViewById(R.id.addBtn);
        removeItemBtn = findViewById(R.id.removeBtn);
    }

    private void initRecyclerView() {
        DataClass[] data = new DataClass[] {
                new DataClass("ВС -5"),
                new DataClass("СБ -5"),
                new DataClass("ПТ -5"),
                new DataClass("ЧТ -5"),
                new DataClass("СР -5"),
                new DataClass("ВТ -5"),
                new DataClass("ПН -4")};

        ArrayList<DataClass> list = new ArrayList<>(data.length);
        list.addAll(Arrays.asList(data));

        //GridLayoutManager layoutManager = new GridLayoutManager(getBaseContext(), 3);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        adapter = new RecyclerViewAdapter(list);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.setNestedScrollingEnabled(false);

    }

    private void initBtnsOnClick() {
        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataClass dataClass = new DataClass("Добавили");
                adapter.addItem(dataClass);
            }
        });

        removeItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.removeItem();
            }
        });
    }



}
