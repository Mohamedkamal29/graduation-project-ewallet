package com.graduation.ewallet.Authorization.FirstFragment.LogIn;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.iid.FirebaseInstanceId;
import com.graduation.ewallet.Api.ServiceApi;
import com.graduation.ewallet.Main.MainActivity;
import com.graduation.ewallet.Model.Auth.RegisterResponse;
import com.graduation.ewallet.Model.Identificationaninfo.IdentityModel;
import com.graduation.ewallet.Network.RetroWeb;
import com.graduation.ewallet.Network.Urls;
import com.graduation.ewallet.R;
import com.graduation.ewallet.SharedPrefManger;
import com.graduation.ewallet.Utiles.DialogUtil;
import com.graduation.ewallet.Utiles.ValidationUtile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInFragment extends Fragment {

    EditText emailEditText, passwordEditText;
    private ProgressDialog progressDialog;
    private SharedPrefManger mSharedPrefManager;
    String mDeviceId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration_log_in, container, false);

        mDeviceId= FirebaseInstanceId.getInstance().getToken();
        mSharedPrefManager = new SharedPrefManger(getContext());

        emailEditText = view.findViewById(R.id.emailEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        Button logInButton = view.findViewById(R.id.logInButton);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogInClick();
                Log.e("fsdf","fsdf");
            }
        });

        return view;
    }

    protected void showProgressDialog(String message) {
        progressDialog = DialogUtil.showProgressDialog(getContext(), message, false);
    }

    protected void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    private void LogInClick() {



        if (Validation()) {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();


                logIn(password,mDeviceId,email);
        }
        //Some Code Here


//        Intent intent = new Intent(getContext(),MainActivity.class);
//        startActivity(intent);
//        getActivity().finish();
    }


    private boolean Validation() {


        //Some Code Here

        boolean flagPass,flagEmail;

        flagPass= ValidationUtile.emptyValidation(passwordEditText,getString(R.string.emptyValidationPass));
        flagEmail= ValidationUtile.validateEmail(emailEditText,getString(R.string.validEmail));

        if (flagPass){
            flagPass= ValidationUtile.checkSize(passwordEditText,getString(R.string.passSize),6);
        }


        return flagEmail && flagPass;


    }

    public void logIn( String password , String deviceId, String email){
        if (ValidationUtile.isNetworkAvailable(getContext())) {
            showProgressDialog(getString(R.string.please_wait));
            RetroWeb.getClient()
                    .create(ServiceApi.class)
                    .logIn(email,password,deviceId)
                    .enqueue(new Callback<RegisterResponse>() {
                        @Override
                        public void onResponse(retrofit2.Call<RegisterResponse> call, Response<RegisterResponse> response) {
                            hideProgressDialog();
                            if (response.isSuccessful()){
                                if (response.body().isStatus())
                                {
                                    mSharedPrefManager.setLoginStatus(true);
                                    mSharedPrefManager.setNotifcationStatus(true);
                                    mSharedPrefManager.setUserData(response.body().getData());

                                    Intent intent = new Intent(getContext(), MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                    startActivity(intent);

                                }else {
                                    Log.e("TAG", "onResponse : else" );
                                }
                            }
                        }

                        @Override
                        public void onFailure(retrofit2.Call<RegisterResponse> call, Throwable t) {
                            hideProgressDialog();
                            //Util.handleException(RegisterActivity.this, t);
                            Log.e(">>>>>>>>>", t.getMessage());

                        }
                    });

            RetroWeb.getClient().create(ServiceApi.class)
                    .getIdentityInformation(Urls.Bearer + mSharedPrefManager.getUserData().getToken())
                    .enqueue(new Callback<IdentityModel>() {
                        @Override
                        public void onResponse(Call<IdentityModel> call, Response<IdentityModel> response) {
                            Log.e("TAG", "onResponse: " + response.isSuccessful());
                            if (response.isSuccessful()) {
                                if (response.body().getStatus()) {
                                    mSharedPrefManager.setUserIdentity(response.body().getData());
                                }
                            }
                        }
                        @Override
                        public void onFailure(Call<IdentityModel> call, Throwable t) {
                            Log.e("TAG", "onFailure: login");
                        }
                    });

        }else {
            // Util.makeToast(this,getString(R.string.toast_internet));
        }

    }


}
