package com.mfzn.deepuses.bean.response.store;

import java.util.List;

public class OrderStockCheckListResponse {


    /**
     * total : 2
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"orderID":3,"companyID":2,"shopID":1,"orderTime":1586442493,"orderMakerUserID":1,"orderMakerUserName":"ewenXing","orderNum":"RWIJ_PD_00000002","outNum":"wb001","storeID":1,"storeName":"总仓","remark":"老鼠咬坏了一个","isCheck":1,"checkTime":1586443698,"checkUserID":1,"status":4,"checkUserName":"ewenXing","checkNote":"通过","addTime":1586443678,"statusText":"无盈亏","systemSumCount":100,"checkSumCount":100},{"orderID":1,"companyID":2,"shopID":1,"orderTime":1586442493,"orderMakerUserID":1,"orderMakerUserName":"ewenXing","orderNum":"RWIJ_PD_00000001","outNum":"wb001","storeID":1,"storeName":"总仓","remark":"老鼠咬坏了一个","isCheck":1,"checkTime":1586443293,"checkUserID":1,"status":3,"checkUserName":"ewenXing","checkNote":"通过","addTime":1586442775,"statusText":"盈亏处理完成","systemSumCount":100,"checkSumCount":99}]
     */

    private int total;
    private int per_page;
    private int current_page;
    private int last_page;
    private List<OrderStockCheckResponse> data;

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

    public List<OrderStockCheckResponse> getData() {
        return data;
    }

    public void setData(List<OrderStockCheckResponse> data) {
        this.data = data;
    }
}
