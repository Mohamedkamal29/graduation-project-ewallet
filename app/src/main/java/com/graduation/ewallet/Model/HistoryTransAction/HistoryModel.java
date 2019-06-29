package com.graduation.ewallet.Model.HistoryTransAction;

import java.io.Serializable;
import java.util.ArrayList;

public class HistoryModel implements Serializable {
    private ArrayList<Sent> send;

    private ArrayList<Sent> receiver;


    private double total_send_amount;
    private double total_receive_amount;

    public ArrayList<Sent> getSend() {
        return send;
    }

    public void setSend(ArrayList<Sent> send) {
        this.send = send;
    }

    public ArrayList<Sent> getReceiver() {
        return receiver;
    }

    public void setReceiver(ArrayList<Sent> receiver) {
        this.receiver = receiver;
    }

    public double getTotal_send_amount() {
        return total_send_amount;
    }

    public void setTotal_send_amount(double total_send_amount) {
        this.total_send_amount = total_send_amount;
    }

    public double getTotal_receive_amount() {
        return total_receive_amount;
    }

    public void setTotal_receive_amount(double total_receive_amount) {
        this.total_receive_amount = total_receive_amount;
    }
}
