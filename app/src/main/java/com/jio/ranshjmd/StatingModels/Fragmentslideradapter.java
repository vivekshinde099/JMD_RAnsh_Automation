package com.jio.ranshjmd.StatingModels;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

public class Fragmentslideradapter extends FragmentPagerAdapter {
    public Fragmentslideradapter(@NonNull @NotNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {

            case 0:
                fragment = new Slider1();
                break;
            case 1:
                fragment = new Slider2();
                break;
            case 2:
                fragment = new Slider3();
                break;
            case 3:
                fragment = new Slider4();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
