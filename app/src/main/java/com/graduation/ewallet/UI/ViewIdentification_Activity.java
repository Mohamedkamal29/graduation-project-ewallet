package com.graduation.ewallet.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.graduation.ewallet.Api.ServiceApi;
import com.graduation.ewallet.Model.Identificationaninfo.IdentityModel;
import com.graduation.ewallet.Network.RetroWeb;
import com.graduation.ewallet.Network.Urls;
import com.graduation.ewallet.R;
import com.graduation.ewallet.SharedPrefManger;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewIdentification_Activity extends AppCompatActivity {

    @BindView(R.id.tvFNvalue)
    TextView tvFNvalue;
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
        sharedPrefManger = new SharedPrefManger(this);
        getIdentityInformation();


    }



    private void getIdentityInformation() {


        RetroWeb.getClient().create(ServiceApi.class)
                .getIdentityInformation(Urls.Bearer+sharedPrefManger.getUserData().getToken())
                .enqueue(new Callback<IdentityModel>() {
                    @Override
                    public void onResponse(Call<IdentityModel> call, Response<IdentityModel> response) {
                        if (response.body().getStatus()) {

                            tvFNvalue.setText(response.body().getData().getAddress());
                            tvAdressvalue.setText(response.body().getData().getAddress());
                            tvNationalidvalue.setText(response.body().getData().getAddress());
                            tvProfessionvalue.setText(response.body().getData().getAddress());
                            tvGendervalue.setText(response.body().getData().getAddress());
                            tvRelegionvalue.setText(response.body().getData().getAddress());
                            tvStatusvalue.setText(response.body().getData().getAddress());
                            tvRelationshipvalue.setText(response.body().getData().getAddress());
                            tvBirthdatevalue.setText(response.body().getData().getAddress());
                            tvLicensevalue.setText(response.body().getData().getAddress());
                            tvBloodvalue.setText(response.body().getData().getAddress());

                        }else {
                            Toast.makeText(ViewIdentification_Activity.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<IdentityModel> call, Throwable t) {

                        Log.e("",t.getMessage());
                    }
                });
    }
}
