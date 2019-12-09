package com.geekbrains.a1l5_fr_hw;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class CoatOfArmsFragment extends Fragment {

    public static CoatOfArmsFragment create(int index) {
        CoatOfArmsFragment f = new CoatOfArmsFragment();

        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    public int getIndex() {
        int index = getArguments().getInt("index", 0);
        return index;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ImageView coatOfArms = new ImageView(getContext());

        TypedArray imgs = getResources().obtainTypedArray(R.array.coatofarms_imgs);
        coatOfArms.setImageResource(imgs.getResourceId(getIndex(), -1));
        return coatOfArms;
    }

}
