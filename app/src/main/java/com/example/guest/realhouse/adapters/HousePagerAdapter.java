package com.example.guest.realhouse.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.example.guest.realhouse.models.House;
import com.example.guest.realhouse.ui.MyHousesFragment;

import java.util.ArrayList;

public class HousePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<House> mHouses;
    private String mSource;

    public HousePagerAdapter(FragmentManager fm, ArrayList<House> houses, String source) {
        super(fm);
        mHouses = houses;
        mSource = source;
    }

    @Override
    public Fragment getItem(int position) {
        return new MyHousesFragment(mHouses, position, mSource);
    }

    @Override
    public int getCount() {
        return mHouses.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mHouses.get(position).getAgentFirstName();
    }
}