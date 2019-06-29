package com.graduation.ewallet.Model;

import com.graduation.ewallet.Model.Base.BaseResponse;

public class ItemRespons extends BaseResponse {


    private ItemResponseModel data;


    public ItemResponseModel getData() {
        return data;
    }

    public void setData(ItemResponseModel data) {
        this.data = data;
    }
}
