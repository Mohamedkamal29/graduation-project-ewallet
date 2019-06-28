package com.graduation.ewallet.Main.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.graduation.ewallet.Main.ContactFragment.MainContactFragment;
import com.graduation.ewallet.Main.PropertyFragment.MainPropertyFragment;
import com.graduation.ewallet.Main.HomeFragment.MainHomeFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {
    private static final int NUM_ITEMS = 3;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MainContactFragment();
            case 1:
                return new MainHomeFragment();
            case 2:
                return new MainPropertyFragment();
            default:
                return null;
        }
    }
}
