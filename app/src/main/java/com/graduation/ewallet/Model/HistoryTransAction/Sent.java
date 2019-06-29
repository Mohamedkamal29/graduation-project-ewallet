package com.graduation.ewallet.Model.HistoryTransAction;

import java.io.Serializable;

public class Sent implements Serializable {

    private String send ;
    private String name;
    private String amount;
    private String date;

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
