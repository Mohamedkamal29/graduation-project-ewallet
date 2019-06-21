package com.graduation.ewallet.Main.HomeFragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.graduation.ewallet.Api.ServiceApi;
import com.graduation.ewallet.Main.MainActivity;
import com.graduation.ewallet.Model.Auth.RegisterModel;
import com.graduation.ewallet.Model.BusinessCard.BusinessCardModel;
import com.graduation.ewallet.Network.RetroWeb;
import com.graduation.ewallet.Network.Urls;
import com.graduation.ewallet.R;
import com.graduation.ewallet.SharedPrefManger;
import com.graduation.ewallet.Utiles.DialogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditBusinessCardActivity extends AppCompatActivity {

    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etProfession)
    EditText etProfession;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etEmail)
    EditText etEmail;

    ProgressDialog progressDialog;
    SharedPrefManger sharedPrefManger;
    RegisterModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_business_card_activity);
        ButterKnife.bind(this);
        sharedPrefManger = new SharedPrefManger(this);
        user = sharedPrefManger.getUserData();

        etName.setText(user.getName());
        etProfession.setText(user.getJob());
        etPhone.setText(user.getPhone());
        etEmail.setText(user.getEmail());
    }

    @OnClick(R.id.tvCancel)
    void goToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    protected void showProgressDialog(String message) {
        progressDialog = DialogUtil.showProgressDialog(this, message, false);
    }

    protected void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @OnClick(R.id.btnSave)
    void updateUserContact() {
        showProgressDialog("Please wait...");
        boolean valid = false;
        final String newName = etName.getText().toString();
        final String newJob = etProfession.getText().toString();
        final String newPhone = etPhone.getText().toString().trim();
        final String newEmail = etEmail.getText().toString().trim();

        if ((newEmail.equals(user.getEmail()) || newEmail.length() == 0
                || !newEmail.contains("@") || !newEmail.endsWith(".com"))
                && (newPhone.equals(user.getPhone()) || newPhone.length() != 11)
                && (newJob.trim().equals(user.getJob().trim()) || newJob.trim().length() == 0)
                && (newName.trim().toLowerCase().equals(user.getName().trim().toLowerCase()) || newName.trim().length() == 0)) {

            if (newEmail.length() == 0 || newPhone.length() == 0
                    || newJob.trim().length() == 0 || newName.trim().length() == 0) {
                Toast.makeText(this,"All Fields are Required",Toast.LENGTH_LONG).show();
            }
            else if (newEmail.equals(user.getEmail()) || newPhone.equals(user.getPhone())
                    || newJob.equals(user.getJob()) || newName.equals(user.getName())){
                Toast.makeText(this,"Must change at less one field",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this,"Email or Phone are Not valid",Toast.LENGTH_LONG).show();
            }
        }
        else {
            valid = true;
        }

        if (valid) {
            RetroWeb.getClient().create(ServiceApi.class)
                    .updateContact(Urls.Bearer + user.getToken(), newName, newJob,
                            newPhone, newEmail).enqueue(new Callback<BusinessCardModel>() {
                @Override
                public void onResponse(Call<BusinessCardModel> call, Response<BusinessCardModel> response) {
                    hideProgressDialog();
                    if (response.isSuccessful() && response.body().isStatus()) {
                        user.setName(newName);
                        user.setJob(newJob);
                        user.setPhone(newPhone);
                        user.setEmail(newEmail);
                        Log.e("TAG", "onResponse: "+response.body().getData().getContact_qr() );
                        user.setContact_qr(response.body().getData().getContact_qr());
                        sharedPrefManger.setUserData(user);
                        goToHome();
                    }
                }

                @Override
                public void onFailure(Call<BusinessCardModel> call, Throwable t) {
                    hideProgressDialog();
                    Log.e("TAG", "onFailure: "+t.getMessage());
                }
            });
        }

    }
}
