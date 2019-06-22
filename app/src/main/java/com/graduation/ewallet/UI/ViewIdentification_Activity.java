package com.graduation.ewallet.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.graduation.ewallet.Api.ServiceApi;
import com.graduation.ewallet.Model.Identificationaninfo.IdentityModel;
import com.graduation.ewallet.Network.RetroWeb;
import com.graduation.ewallet.Network.Urls;
import com.graduation.ewallet.R;
import com.graduation.ewallet.SharedPrefManger;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewIdentification_Activity extends AppCompatActivity {

    @BindView(R.id.llName)
    LinearLayout llName;
    @BindView(R.id.tvNameValue)
    TextView tvNameValue;
    @BindView(R.id.tvAdressvalue)
    TextView tvAdressvalue;
    @BindView(R.id.tvNationalidvalue)
    TextView tvNationalidvalue;
    @BindView(R.id.tvProfessionvalue)
    TextView tvProfessionvalue;
    @BindView(R.id.tvGendervalue)
    TextView tvGendervalue;
    @BindView(R.id.tvRelegionvalue)
    TextView tvRelegionvalue;
    @BindView(R.id.tvStatusvalue)
    TextView tvStatusvalue;
    @BindView(R.id.tvRelationshipvalue)
    TextView tvRelationshipvalue;
    @BindView(R.id.tvBirthdatevalue)
    TextView tvBirthdatevalue;
    @BindView(R.id.tvLicense)
    TextView tvLicense;
    @BindView(R.id.tvLicensevalue)
    TextView tvLicensevalue;
    @BindView(R.id.tvBloodvalue)
    TextView tvBloodvalue;

    ImageView ivPerson;

    SharedPrefManger sharedPrefManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_identification_);
        ButterKnife.bind(this);
        sharedPrefManger = new SharedPrefManger(this);
        getIdentityInformation();


    }

    private void getIdentityInformation() {
        Log.e("TAG", "onGetIdentityInformation: true" );
        RetroWeb.getClient().create(ServiceApi.class)
                .getIdentityInformation(Urls.Bearer + sharedPrefManger.getUserData().getToken())
                .enqueue(new Callback<IdentityModel>() {
                    @Override
                    public void onResponse(Call<IdentityModel> call, Response<IdentityModel> response) {
                        Log.e("TAG", "onResponse: true" );
                        if (response.isSuccessful() && response.body().getStatus()) {
                            if (response.body().getData().getName().equals(null))
                                llName.setVisibility(View.GONE);
                            else
                            tvNameValue.setText(response.body().getData().getName());

                            tvAdressvalue.setText(response.body().getData().getAddress());
                            tvNationalidvalue.setText(response.body().getData().getNationalId());
                            tvProfessionvalue.setText(response.body().getData().getJob());
                            tvGendervalue.setText(response.body().getData().getGender());
                            tvRelegionvalue.setText(response.body().getData().getReligion());
                            tvStatusvalue.setText(response.body().getData().getRelationshipType());
                            tvRelationshipvalue.setText(response.body().getData().getRelationshipWith());
                            tvBirthdatevalue.setText(response.body().getData().getBirthDate());
                            if (response.body().getData().getLicenceType().equals(null)){
                                tvLicense.setVisibility(View.GONE);
                                tvLicensevalue.setVisibility(View.GONE);
                            }
                            else
                            tvLicensevalue.setText(response.body().getData().getLicenceType());
                            tvBloodvalue.setText(response.body().getData().getBloodType());

                        }
                    }

                    @Override
                    public void onFailure(Call<IdentityModel> call, Throwable t) {
                        Log.e("TAG",t.getMessage());
                    }
                });
    }
}
