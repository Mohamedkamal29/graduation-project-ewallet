
package com.graduation.ewallet.Model.BusinessCard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusinessCardModel {

    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("data")
    @Expose
    private Data data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
