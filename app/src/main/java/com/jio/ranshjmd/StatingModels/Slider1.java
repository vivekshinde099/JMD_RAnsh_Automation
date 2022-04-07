package com.jio.ranshjmd.StatingModels;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jio.ranshjmd.R;

public class Slider1 extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.activity_slider1, container, false);
        return rootview;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}