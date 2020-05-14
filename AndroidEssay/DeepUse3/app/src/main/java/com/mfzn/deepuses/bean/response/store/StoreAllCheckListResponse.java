package com.mfzn.deepuses.bean.response.store;

import java.util.List;

public class StoreAllCheckListResponse {


    /**
     * total : 1
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"orderID":1,"companyID":2,"shopID":1,"startTime":1586448000,"endTime":1586634400,"orderMakerUserID":1,"orderMakerUserName":"ewenXing","orderNum":"RWIJ_QKPD_00000001","storeID":1,"storeName":"总仓","remark":"自动创建","status":2,"addTime":1586594721,"statusText":"待盈亏处理","systemStockNum":99,"checkStockNum":100}]
     */

    private int total;
    private int per_page;
    private int current_page;
    private int last_page;
    private List<StoreCheckResponse> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public List<StoreCheckResponse> getData() {
        return data;
    }

    public void setData(List<StoreCheckResponse> data) {
        this.data = data;
    }

}
