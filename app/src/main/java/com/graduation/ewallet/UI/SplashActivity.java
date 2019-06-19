package com.graduation.ewallet.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.graduation.ewallet.Authorization.RegistrationActivity;
import com.graduation.ewallet.Main.MainActivity;
import com.graduation.ewallet.R;
import com.graduation.ewallet.SharedPrefManger;

public class SplashActivity extends AppCompatActivity {


    private SharedPrefManger mSharedPrefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        mSharedPrefManager = new SharedPrefManger(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mSharedPrefManager.getLoginStatus()) {

                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(SplashActivity.this, RegistrationActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 1500);
    }
}
