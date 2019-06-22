package com.graduation.ewallet.Model;

import com.graduation.ewallet.Model.Base.BaseResponse;

public class WallerQrResponse extends BaseResponse {

    private String wallet_qr;


    public String getWallet_qr() {
        return wallet_qr;
    }

    public void setWallet_qr(String wallet_qr) {
        this.wallet_qr = wallet_qr;
    }
}
