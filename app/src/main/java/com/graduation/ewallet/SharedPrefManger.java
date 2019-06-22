package com.graduation.ewallet;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.graduation.ewallet.Model.Auth.RegisterModel;
import com.graduation.ewallet.Model.BusinessCard.Contact;
import com.graduation.ewallet.Model.Identificationaninfo.Data;

import java.util.List;

public class SharedPrefManger {

    Context mContext;

    SharedPreferences mSharedPreferences;

    SharedPreferences.Editor mEditor;

    public SharedPrefManger(Context mContext) {
        this.mContext = mContext;
        mSharedPreferences = mContext.getSharedPreferences(Constant.SharedPrefKey.SHARED_PREF_NAME, mContext.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public Boolean getLoginStatus() {
        Boolean value = mSharedPreferences.getBoolean(Constant.SharedPrefKey.LOGIN_STATUS, false);
        return value;
    }

    public void setLoginStatus(Boolean status) {
        mEditor.putBoolean(Constant.SharedPrefKey.LOGIN_STATUS, status);
        mEditor.commit();
    }

    public void setNotifcationStatus(Boolean status) {
        mEditor.putBoolean(Constant.SharedPrefKey.NOTIFCATION_STATUS, status);
        mEditor.commit();
    }


    public void setNotifcationCounter(int counter) {
        mEditor.putInt(Constant.SharedPrefKey.NOTIFCATION_COUNTER, counter);
        mEditor.commit();
    }


    public void setNotifcationCounterChate(int counter) {
        mEditor.putInt(Constant.SharedPrefKey.NOTIFCATION_COUNTER_CHAT, counter);
        mEditor.commit();
    }


    public Boolean getNotifcationStatus() {
        Boolean value = mSharedPreferences.getBoolean(Constant.SharedPrefKey.NOTIFCATION_STATUS, false);
        return value;
    }


    public int getNotifcationCounter() {
        int value = mSharedPreferences.getInt(Constant.SharedPrefKey.NOTIFCATION_COUNTER, 0);
        return value;
    }

    public int getNotifcationCounterChat() {
        int value = mSharedPreferences.getInt(Constant.SharedPrefKey.NOTIFCATION_COUNTER_CHAT, 0);
        return value;
    }

    public void setUserData(RegisterModel userModel) {
        mEditor.putString(Constant.SharedPrefKey.USER, new Gson().toJson(userModel));
        mEditor.apply();
    }

    public RegisterModel getUserData() {
        Gson gson = new Gson();
        return gson.fromJson(mSharedPreferences.getString(Constant.SharedPrefKey.USER, null), RegisterModel.class);
    }

    public void setUserIdentity(Data userModel) {
        mEditor.putString("id", new Gson().toJson(userModel));
        mEditor.apply();
    }

    public Data getUserIdentity() {
        Gson gson = new Gson();
        return gson.fromJson(mSharedPreferences.getString("id", null), Data.class);
    }

    public void Logout() {
        mEditor.clear();
        mEditor.apply();
    }
}