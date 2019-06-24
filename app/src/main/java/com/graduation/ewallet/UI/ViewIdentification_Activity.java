package com.graduation.ewallet.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.graduation.ewallet.R;
import com.graduation.ewallet.SharedPrefManger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewIdentification_Activity extends AppCompatActivity {

    @BindView(R.id.llName)
    LinearLayout llName;
    @BindView(R.id.tvNameValue)
    TextView tvNameValue;

    @BindView(R.id.llAdress)
    LinearLayout llAdress;
    @BindView(R.id.tvAdressvalue)
    TextView tvAdressvalue;

    @BindView(R.id.llNationalid)
    LinearLayout llNationalid;
    @BindView(R.id.tvNationalidvalue)
    TextView tvNationalidvalue;

    @BindView(R.id.llProfession)
    LinearLayout llProfession;
    @BindView(R.id.tvProfessionvalue)
    TextView tvProfessionvalue;

    @BindView(R.id.llGender)
    LinearLayout llGender;
    @BindView(R.id.tvGendervalue)
    TextView tvGendervalue;

    @BindView(R.id.llRelegion)
    LinearLayout llRelegion;
    @BindView(R.id.tvRelegionvalue)
    TextView tvRelegionvalue;

    @BindView(R.id.llStatus)
    LinearLayout llStatus;
    @BindView(R.id.tvStatusvalue)
    TextView tvStatusvalue;

    @BindView(R.id.llRelationship)
    LinearLayout llRelationship;
    @BindView(R.id.tvRelationshipvalue)
    TextView tvRelationshipvalue;

    @BindView(R.id.llBirthdate)
    LinearLayout llBirthdate;
    @BindView(R.id.tvBirthdatevalue)
    TextView tvBirthdatevalue;


    @BindView(R.id.llLicense)
    LinearLayout llLicense;
    @BindView(R.id.tvLicensevalue)
    TextView tvLicensevalue;

    @BindView(R.id.llBlood)
    LinearLayout llBlood;
    @BindView(R.id.tvBloodvalue)
    TextView tvBloodvalue;
    @BindView(R.id.ivPerson)
    ImageView ivPerson;
    @BindView(R.id.llAllinfo)
            LinearLayout llAllInfo;
    @BindView(R.id.tvloading)
            TextView tvloading;
    @BindView(R.id.progressBar1)
    ProgressBar progressBar;

    SharedPrefManger sharedPrefManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_identification);
        ButterKnife.bind(this);
        sharedPrefManger = new SharedPrefManger(this);
        getIdentityInformation();

        llAllInfo.setVisibility(View.GONE);
        ivPerson.setVisibility(View.GONE);
        tvloading.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void getIdentityInformation() {
//        Log.e("TAG", "onGetIdentityInformation: true");
//        RetroWeb.getClient().create(ServiceApi.class)
//                .getIdentityInformation(Urls.Bearer + sharedPrefManger.getUserData().getToken())
//                .enqueue(new Callback<IdentityModel>() {
//                    @Override
//                    public void onResponse(Call<IdentityModel> call, Response<IdentityModel> response) {
//                        Log.e("TAG", "onResponse: true");
//                        if (response.isSuccessful() && response.body().getStatus()) {

                            tvloading.setVisibility(View.GONE);
                            progressBar.setVisibility(View.GONE);
                            ivPerson.setVisibility(View.VISIBLE);
                            llAllInfo.setVisibility(View.VISIBLE);

                            if (sharedPrefManger.getUserIdentity().getImage() != null)
                                Glide.with(this).load(sharedPrefManger.getUserIdentity().getImage().toString()).into(ivPerson);

                            if (sharedPrefManger.getUserIdentity().getName() == null)
                                llName.setVisibility(View.GONE);

                            else
                                tvNameValue.setText(sharedPrefManger.getUserIdentity().getName().toString());


                            if (sharedPrefManger.getUserIdentity().getAddress() == null)
                                llAdress.setVisibility(View.GONE);

                            else
                                tvAdressvalue.setText(sharedPrefManger.getUserIdentity().getAddress().toString());

                            if (sharedPrefManger.getUserIdentity().getNationalId() == null)
                                llNationalid.setVisibility(View.GONE);

                            else
                                tvNationalidvalue.setText(sharedPrefManger.getUserIdentity().getNationalId().toString());

                            if (sharedPrefManger.getUserIdentity().getJob() == null)
                                llProfession.setVisibility(View.GONE);
                            else
                                tvProfessionvalue.setText(sharedPrefManger.getUserIdentity().getJob().toString());

                            if (sharedPrefManger.getUserIdentity().getGender() == null)
                                llGender.setVisibility(View.GONE);
                            else {
                                if (sharedPrefManger.getUserIdentity().getGender().toString() == "male")
                                    tvRelationshipvalue.setVisibility(View.GONE);
                                    tvGendervalue.setText(sharedPrefManger.getUserIdentity().getGender().toString());
                            }
                            if (sharedPrefManger.getUserIdentity().getReligion() == null)
                                llRelegion.setVisibility(View.GONE);
                            else
                                tvRelegionvalue.setText(sharedPrefManger.getUserIdentity().getReligion().toString());
                            if (sharedPrefManger.getUserIdentity().getRelationshipType() == null)
                                llStatus.setVisibility(View.GONE);
                            else
                                tvStatusvalue.setText(sharedPrefManger.getUserIdentity().getRelationshipType().toString());
                            if (sharedPrefManger.getUserIdentity().getRelationshipWith() == null)
                                llRelationship.setVisibility(View.GONE);
                            else
                                tvRelationshipvalue.setText(sharedPrefManger.getUserIdentity().getRelationshipWith().toString());

                            if (sharedPrefManger.getUserIdentity().getBirthDate() == null)
                                llBirthdate.setVisibility(View.GONE);
                            else
                                tvBirthdatevalue.setText(sharedPrefManger.getUserIdentity().getBirthDate().toString());

                            if (sharedPrefManger.getUserIdentity().getLicenceType() == null)
                                llLicense.setVisibility(View.GONE);

                            else
                                tvLicensevalue.setText(sharedPrefManger.getUserIdentity().getLicenceType().toString());
                            if (sharedPrefManger.getUserIdentity().getBloodType() == null)
                                llBlood.setVisibility(View.GONE);
                            else
                                tvBloodvalue.setText(sharedPrefManger.getUserIdentity().getBloodType().toString());

//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<IdentityModel> call, Throwable t) {
//                        Log.e("TAG", t.getMessage());
//                    }
//                });
    }
}
