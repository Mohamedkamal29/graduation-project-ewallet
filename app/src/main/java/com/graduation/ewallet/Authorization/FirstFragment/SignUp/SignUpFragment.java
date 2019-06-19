package com.graduation.ewallet.Authorization.FirstFragment.SignUp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.graduation.ewallet.Authorization.RegistrationActivity;
import com.graduation.ewallet.Model.Singleton;
import com.graduation.ewallet.R;
import com.graduation.ewallet.Utiles.ValidationUtile;

import okhttp3.internal.Util;


public class SignUpFragment extends Fragment {

    EditText nameEditText, emailEditText, passwordEditText, confirmPasswordEditText;

    Singleton firstStepRegister=Singleton.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registration_sign_up_fragment, container, false);

        nameEditText = view.findViewById(R.id.nameEditText);
        emailEditText = view.findViewById(R.id.emailEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        confirmPasswordEditText = view.findViewById(R.id.confirmPasswordEditText);
        Button signUpButton = view.findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToPin(v);
            }
        });

        return view;
    }


    private void moveToPin(View v){
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();


        if (Validation()) {

            firstStepRegister.userForm.setName(name);
            firstStepRegister.userForm.setEmail(email);
            firstStepRegister.userForm.setPassword(password);



            ((RegistrationActivity) getActivity()).Pin(v);
        }

    }

    private boolean Validation() {


        //Some Code Here

        boolean flagPass,flagName,flagEmail,flagConfirmPassword;

        flagPass= ValidationUtile.emptyValidation(passwordEditText,getString(R.string.emptyValidationPass));
        flagName= ValidationUtile.emptyValidation(nameEditText,getString(R.string.emptyValidationName));
        flagEmail= ValidationUtile.validateEmail(emailEditText,getString(R.string.validEmail));
        flagConfirmPassword= ValidationUtile.emptyValidation(confirmPasswordEditText,getString(R.string.emptyValidationConfirmPassword));

        if (flagPass){
            flagPass= ValidationUtile.checkSize(passwordEditText,getString(R.string.passSize),6);
        }


        if (flagPass && flagConfirmPassword){
            flagConfirmPassword=   ValidationUtile.validateConfirmPassword(passwordEditText,confirmPasswordEditText,getString(R.string.validationConfirmSize));
        }
        return flagConfirmPassword && flagEmail && flagName && flagEmail && flagPass;


    }

}
