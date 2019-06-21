package com.graduation.ewallet.Model.Base;

public class BaseResponse {


    private boolean status ;

    private double new_balance;

    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getNew_balance() {
        return new_balance;
    }

    public void setNew_balance(double new_balance) {
        this.new_balance = new_balance;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
