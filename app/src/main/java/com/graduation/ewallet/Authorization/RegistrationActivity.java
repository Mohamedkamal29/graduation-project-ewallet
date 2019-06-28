package com.graduation.ewallet.Authorization;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.graduation.ewallet.Authorization.FirstFragment.ChoiceFragment;
import com.graduation.ewallet.Authorization.SecondFragment.PinFragment;
import com.graduation.ewallet.Authorization.ThirdFragment.AdditionalFragment;
import com.graduation.ewallet.Customiztation.NonSwipeableViewPager;
import com.graduation.ewallet.R;

public class RegistrationActivity extends AppCompatActivity {

    MyPagerAdapter adapter;
    NonSwipeableViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        viewPager = findViewById(R.id.RegistrationContainer);
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.setPageTransformer();
    }


    public void Pin (View view) {
        viewPager.setCurrentItem(1);
    }
    public void Additional(View view){
        viewPager.setCurrentItem(2);
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {
        private static final int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fm) {
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
                    return new ChoiceFragment();
                case 1:
                    return new PinFragment();
                case 2:
                    return new AdditionalFragment();
                default:
                    return null;
            }
        }
    }

}
