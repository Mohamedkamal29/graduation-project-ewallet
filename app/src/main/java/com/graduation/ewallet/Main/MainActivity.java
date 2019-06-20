package com.graduation.ewallet.Main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.graduation.ewallet.Api.ServiceApi;
import com.graduation.ewallet.Lisner.ConfirmRequest;
import com.graduation.ewallet.Main.Adapter.MainPagerAdapter;
import com.graduation.ewallet.Main.HomeFragment.MainHomeFragment;
import com.graduation.ewallet.Model.ConfirmSendMonyRespons;
import com.graduation.ewallet.Network.RetroWeb;
import com.graduation.ewallet.Network.Urls;
import com.graduation.ewallet.R;
import com.graduation.ewallet.SharedPrefManger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("inActivity","true");

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        //check for null
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Scan Cancelled", Toast.LENGTH_LONG).show();
            } else {
                //show dialogue with result
                // loadFragmentAdsDetail(new AdsDetailFragment(),result.getContents());
                showResultDialogue(result.getContents());

            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

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

    public void showResultDialogue(final String result) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle(getString(R.string.Scanned_result))
                .setMessage(getString(R.string.Scanned_result_is) + result)
                .setPositiveButton(getString(R.string.Show_ad), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
//                        // continue with delete
//                        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
//                        ClipData clip = ClipData.newPlainText("Scan Result", result);
//                        clipboard.setPrimaryClip(clip);
//                        Toast.makeText(MainActivity.this, "Result copied to clipboard", Toast.LENGTH_SHORT).show();

                        //       loadFragmentAdsDetail(new AdsDetailFragment(),result);

                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        dialog.dismiss();
                    }
                })
                .show();
    }
    private  void  sendConfirmRequest(String qr){
        RetroWeb.getClient()
                .create(ServiceApi.class)
                .confirmSend(qr, Urls.Bearer+mSharedPrefManager.getUserData().getToken())
                .enqueue(new Callback<ConfirmSendMonyRespons>() {
                    @Override
                    public void onResponse(Call<ConfirmSendMonyRespons> call, Response<ConfirmSendMonyRespons> response) {
                        if (response.isSuccessful()) {
                            if (response.body().isStatus()) {

                            }else {
                            }
                        } else {
                        }
                    }

                    @Override
                    public void onFailure(Call<ConfirmSendMonyRespons> call, Throwable t) {
                      //  CommonUtil.handleException(mContext, t);
                        Log.e(">>>>>>>>>", t.getMessage());
                    }
                });
    }


}
