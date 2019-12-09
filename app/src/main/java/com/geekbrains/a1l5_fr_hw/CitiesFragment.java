package com.geekbrains.a1l5_fr_hw;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class CitiesFragment extends Fragment {
    boolean isExistCoatOfArms;
    int currentPosition = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
        initList(view);
    }

    private void initView() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isExistCoatOfArms = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;

        if(savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("CurrentPos", 0);
        }

        if (isExistCoatOfArms) {
            showCoatOfArms();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("CurrentCity", currentPosition);
        super.onSaveInstanceState(outState);
    }

    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout)view;
        String[] cities = getResources().getStringArray(R.array.cities);

        for(int i=0; i < cities.length; i++){
            String city = cities[i];
            TextView tv = new TextView(getContext());
            tv.setText(city);
            tv.setTextSize(30);
            layoutView.addView(tv);
            final int fi = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentPosition = fi;
                    showCoatOfArms();
                }
            });
        }
    }

    private void showCoatOfArms() {
        if (isExistCoatOfArms) {
            CoatOfArmsFragment detail = (CoatOfArmsFragment)
                    getFragmentManager().findFragmentById(R.id.coat_of_arms);
            if (detail == null || detail.getIndex() != currentPosition) {
                detail = CoatOfArmsFragment.create(currentPosition);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.coat_of_arms, detail);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        } else {
            Intent intent = new Intent();
            intent.setClass(getActivity(), CoatOfArmsActivity.class);
            intent.putExtra("index", currentPosition);
            startActivity(intent);
        }
    }

}
