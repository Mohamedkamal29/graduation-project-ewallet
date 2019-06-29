package com.graduation.ewallet.Model.HistoryTransAction;

import com.graduation.ewallet.Model.Base.BaseResponse;

import java.util.ArrayList;

public class HistoryResponse extends BaseResponse {



  private HistoryModel data;

    public HistoryModel getData() {
        return data;
    }

    public void setData(HistoryModel data) {
        this.data = data;
    }
}
