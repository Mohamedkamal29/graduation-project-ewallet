package com.graduation.ewallet.Main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.graduation.ewallet.Main.Adapter.MainPagerAdapter;
import com.graduation.ewallet.R;
import com.graduation.ewallet.SharedPrefManger;

public class MainActivity extends AppCompatActivity {

    Fragment fragment;
    MainPagerAdapter adapter;
    ImageButton ContactImageButton;
    ImageButton HomeImageButton;
    ImageButton CreditImageButton;
    private SharedPrefManger mSharedPrefManager;

    ViewPager viewPager;
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
    LinearLayout.LayoutParams selectedParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,2);




    public void SwitchFragment(View view) {

        ContactImageButton.setLayoutParams(params);
        HomeImageButton.setLayoutParams(params);
        CreditImageButton.setLayoutParams(params);
        ContactImageButton.setImageResource(R.drawable.main_contact_image);
        HomeImageButton.setImageResource(R.drawable.main_home_image);
        CreditImageButton.setImageResource(R.drawable.main_credit_image);

        switch (view.getId()){
            case R.id.ContactImageButton:
                ContactImageButton.setLayoutParams(selectedParams);
                ContactImageButton.setImageResource(R.drawable.main_contact_image_selected);
                viewPager.setCurrentItem(0);
                break;
            case R.id.HomeImageButton:
                HomeImageButton.setLayoutParams(selectedParams);
                HomeImageButton.setImageResource(R.drawable.main_home_image_selected);
                viewPager.setCurrentItem(1);
                break;
            case R.id.CreditImageButton:
                CreditImageButton.setLayoutParams(selectedParams);
                CreditImageButton.setImageResource(R.drawable.main_credit_image_selected);
                viewPager.setCurrentItem(2);
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        viewPager = findViewById(R.id.mainViewPager);
        adapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        ContactImageButton.performClick();
                        break;
                    case 1:
                        HomeImageButton.performClick();
                        break;
                    case 2:
                        CreditImageButton.performClick();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        ContactImageButton = findViewById(R.id.ContactImageButton);
        HomeImageButton =findViewById(R.id.HomeImageButton);
        CreditImageButton =findViewById(R.id.CreditImageButton);
    }

}
