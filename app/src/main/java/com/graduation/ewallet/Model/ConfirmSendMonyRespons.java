package com.graduation.ewallet.Model;

import com.graduation.ewallet.Model.Base.BaseResponse;

public class ConfirmSendMonyRespons  extends BaseResponse {


    private ConfirmSendMonyModel data ;

    public ConfirmSendMonyModel getData() {
        return data;
    }

    public void setData(ConfirmSendMonyModel data) {
        this.data = data;
    }
}
