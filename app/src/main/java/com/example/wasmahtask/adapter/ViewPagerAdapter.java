package com.example.wasmahtask.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.wasmahtask.ui.JeffKellyFragment;
import com.example.wasmahtask.ui.LocationMapFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return  new JeffKellyFragment();

            case 1:
                return new LocationMapFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return "tab1";

            case 1:
                return "tab2";
                default:
                    return null;

        }
    }
}
