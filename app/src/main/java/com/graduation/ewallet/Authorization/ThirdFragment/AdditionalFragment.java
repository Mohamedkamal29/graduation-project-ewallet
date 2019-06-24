package com.graduation.ewallet.Authorization.ThirdFragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
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
import com.graduation.ewallet.Authorization.RegistrationActivity;
import com.graduation.ewallet.Main.MainActivity;
import com.graduation.ewallet.Model.Auth.RegisterResponse;
import com.graduation.ewallet.Model.Singleton;
import com.graduation.ewallet.Network.RetroWeb;
import com.graduation.ewallet.Network.Urls;
import com.graduation.ewallet.R;
import com.graduation.ewallet.SharedPrefManger;
import com.graduation.ewallet.Utiles.DialogUtil;
import com.graduation.ewallet.Utiles.ValidationUtile;

import retrofit2.Callback;
import retrofit2.Response;

public class AdditionalFragment extends Fragment {

    EditText phoneEditText, professionEditText;

    private ProgressDialog progressDialog;

    private SharedPrefManger mSharedPrefManager;


    Singleton finalStep=Singleton.getInstance();
    String mDeviceId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registration_additional_fragment, container, false);
        mDeviceId= FirebaseInstanceId.getInstance().getToken();
        mSharedPrefManager = new SharedPrefManger(getContext());

        phoneEditText = view.findViewById(R.id.phoneEditText);
        professionEditText = view.findViewById(R.id.professionEditText);
        Button finishButton = view.findViewById(R.id.finishButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign();
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


    private void sign(){


        if (Validation()) {
            String phone = phoneEditText.getText().toString();
            String profession = professionEditText.getText().toString();

            finalStep.userForm.setPhone(phone);
            finalStep.userForm.setJob(profession);
            Log.e("finalStepName",finalStep.userForm.getName()+"");
            Log.e("finalStepName",finalStep.userForm.getEmail()+"");
            Log.e("finalStepName",finalStep.userForm.getJob()+"");
            Log.e("finalStepName",finalStep.userForm.getPassword()+"");
            Log.e("finalStepName",finalStep.userForm.getPhone()+"");
            Log.e("finalStepName",finalStep.userForm.getPin()+"");
            Log.e("finalStepName",mDeviceId+"");

            register(finalStep.userForm.getName(),finalStep.userForm.getPhone(),finalStep.userForm.getPassword(),mDeviceId,finalStep.userForm.getEmail(),finalStep.userForm.getJob(),finalStep.userForm.getPin());

        }

    }


    public void register(String name, String phone, String password , String deviceId, String email, String jop, String pin){
        if (ValidationUtile.isNetworkAvailable(getContext())) {
            showProgressDialog(getString(R.string.please_wait));
            RetroWeb.getClient()
                    .create(ServiceApi.class)
                    .register(name,phone,email,password,jop,pin,deviceId)
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
                                    //Util.makeToast(RegisterActivity.this,response.body().getMsg());
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
        }else {
           // Util.makeToast(this,getString(R.string.toast_internet));
        }

    }


    private void register(){




    }

    private boolean Validation() {




        //Some Code Here

        boolean flagPhone,flagProfession;

        flagPhone= ValidationUtile.emptyValidation(phoneEditText,getString(R.string.emptyValidationPhone));
        flagProfession= ValidationUtile.emptyValidation(professionEditText,getString(R.string.emptyValidationProfession));



        return flagPhone && flagProfession ;


        //Some Code Here


//        Intent intent = new Intent(getContext(),MainActivity.class);
//        startActivity(intent);
//        getActivity().finish();
    }

}
