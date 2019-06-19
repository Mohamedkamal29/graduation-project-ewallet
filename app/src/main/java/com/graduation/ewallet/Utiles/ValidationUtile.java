package com.graduation.ewallet.Utiles;

import android.content.Context;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import android.widget.EditText;

public class ValidationUtile {

    public static final boolean emptyValidation(EditText editText, String error) {
        editText.setError(null);
        if (editText.getText().toString().trim().isEmpty()) {
            editText.setError(error);
            return false;
        }
        return true;
    }


    public static final boolean checkPassSize(EditText editText, String message, int size) {
        if (editText.getText().toString().length() < size) {
            editText.setError(message);
            return false;
        } else {
            editText.setError(null);
            return true;
        }
    }


    public static boolean validateConfirmPassword(EditText et_user_password, EditText et_confirm_password , String error) {
        if (!et_confirm_password.getText().toString().equals(et_user_password.getText().toString().trim())) {
            et_confirm_password.setError(error);
            return false;
        }
        return true;
    }


    public static boolean validateConfirmPin(EditText et_user_pin, EditText et_confirm_pin , String error) {
        if (et_confirm_pin.getText().toString().trim().isEmpty()) {
            et_confirm_pin.setError("Please Enter Your Confirm PIN");
            return false;
        } else if (!et_confirm_pin.getText().toString().equals(et_user_pin.getText().toString().trim())) {
            et_confirm_pin.setError(error);
            return false;
        }
        return true;
    }

    public static boolean checkSize(EditText editText, String message, int size) {
        if (editText.getText().toString().trim().length() < size) {
            editText.setError(message);
            return false;
        } else {
            editText.setError(null);
            return true;
        }

    }

    public static boolean checkPinSize(EditText editText, String message, int size) {

        if (editText.getText().toString().trim().isEmpty()) {
            editText.setError("Enter your PIN");
            return false;
        }else  if (editText.getText().toString().trim().length() < size) {
            editText.setError(message);
            return false;
        }
        else {
            editText.setError(null);
            return true;
        }

    }

    public static boolean validateEmail(EditText editText, String error) {
        if (editText.getText().toString().trim().isEmpty()) {
            editText.setError("Enter your Email");
            return false;
        } else if (!isValidEmail(editText.getText().toString().trim())) {
            editText.setError(error);
            return false;
        }
        return true;
    }

    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    public static boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }



}
