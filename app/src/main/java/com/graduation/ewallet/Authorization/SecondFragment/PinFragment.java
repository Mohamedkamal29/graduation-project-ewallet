package com.graduation.ewallet.Authorization.SecondFragment;

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


public class PinFragment extends Fragment {

    EditText pinEditText, confirmPinEditText;

    Singleton secondStepRegisterPin=Singleton.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registration_pin_fragment, container, false);
        pinEditText = view.findViewById(R.id.pinEditText);
        confirmPinEditText = view.findViewById(R.id.confirmPinEditText);
        Button nextButton = view.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToAdditional(v);
            }
        });
        return view;
    }


    private void moveToAdditional(View v){


        if (Validation()) {
            String pin = pinEditText.getText().toString();

            secondStepRegisterPin.userForm.setPin(pin);

            ((RegistrationActivity)getActivity()).Additional(v);
        }

    }


    private boolean Validation() {

        /* Some Conditions
         * Confirm PIN didn't Match your PIN
         * PIN must contain 4 digit number
         */

        //Some Code Here

        boolean flagPin,flagConfirmPin;

        flagPin= ValidationUtile.checkPinSize(pinEditText,getString(R.string.pinSize),6);
        flagConfirmPin= ValidationUtile.validateConfirmPin(pinEditText,confirmPinEditText,getString(R.string.emptyValidationConfirmPassword));



        return flagPin && flagConfirmPin ;


    }

}
