package com.graduation.ewallet.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.graduation.ewallet.Api.ServiceApi;
import com.graduation.ewallet.Authorization.RegistrationActivity;
import com.graduation.ewallet.Main.MainActivity;
import com.graduation.ewallet.Model.Identificationaninfo.IdentityModel;
import com.graduation.ewallet.Network.RetroWeb;
import com.graduation.ewallet.Network.Urls;
import com.graduation.ewallet.R;
import com.graduation.ewallet.SharedPrefManger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {


    private SharedPrefManger mSharedPrefManager;

    boolean valid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        mSharedPrefManager = new SharedPrefManger(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mSharedPrefManager.getLoginStatus()) {
                    if (getUserIdentity());
                    openHome();
                }else {
                    Intent intent = new Intent(SplashActivity.this, RegistrationActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 1500);
    }

    private void openHome() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean getUserIdentity() {
        RetroWeb.getClient().create(ServiceApi.class)
                .getIdentityInformation(Urls.Bearer + mSharedPrefManager.getUserData().getToken())
                .enqueue(new Callback<IdentityModel>() {
                    @Override
                    public void onResponse(Call<IdentityModel> call, Response<IdentityModel> response) {
                        if (response.isSuccessful() && response.body().getStatus()) {
                            mSharedPrefManager.setUserIdentity(response.body().getData());
                            valid = true;
                        }
                    }
                    @Override
                    public void onFailure(Call<IdentityModel> call, Throwable t) {
                        valid = false;
                    }
                });
        return valid;
    }
}
