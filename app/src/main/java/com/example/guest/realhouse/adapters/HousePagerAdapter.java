package com.example.guest.realhouse.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.example.guest.realhouse.models.House;
import com.example.guest.realhouse.ui.MyHousesFragment;

import java.util.ArrayList;

public class HousePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<House> mHouses;

    public HousePagerAdapter(FragmentManager fm, ArrayList<House> houses) {
        super(fm);
        mHouses = houses;
    }

    @Override
    public Fragment getItem(int position) {
        return MyHousesFragment.newInstance(mHouses.get(position));
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