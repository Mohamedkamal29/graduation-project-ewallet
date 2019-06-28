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
        setContentView(R.layout.activity_splash);

        mSharedPrefManager = new SharedPrefManger(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mSharedPrefManager.getLoginStatus()) {
                    openHome();

                }else if (!mSharedPrefManager.getLoginStatus()){
                    openRegistration();
                }

            }
        }, 1500);
    }

    private void openRegistration(){
        Intent intent = new Intent(SplashActivity.this, RegistrationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    private void openHome() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
}
