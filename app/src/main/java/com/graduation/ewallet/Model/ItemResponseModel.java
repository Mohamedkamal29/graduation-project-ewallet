package com.graduation.ewallet.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class ItemResponseModel implements Serializable {

    private ArrayList<ItemModel> item;


    public ArrayList<ItemModel> getItem() {
        return item;
    }

    public void setItem(ArrayList<ItemModel> item) {
        this.item = item;
    }
}
