package com.graduation.ewallet.Model;

import com.graduation.ewallet.Model.Base.BaseResponse;

public class StorItemResponse extends BaseResponse {

    private StoreItemModel data;

    public StoreItemModel getData() {
        return data;
    }

    public void setData(StoreItemModel data) {
        this.data = data;
    }


}
