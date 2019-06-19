package com.graduation.ewallet.Model.Auth;

import com.graduation.ewallet.Model.Base.BaseResponse;

public class RegisterResponse extends BaseResponse {


    private RegisterModel data;

    public RegisterModel getData() {
        return data;
    }

    public void setData(RegisterModel data) {
        this.data = data;
    }
}
