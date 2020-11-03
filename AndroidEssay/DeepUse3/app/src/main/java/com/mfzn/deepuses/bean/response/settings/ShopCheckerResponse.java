package com.mfzn.deepuses.bean.response.settings;

public class ShopCheckerResponse {


    /**
     * data_id : 2
     * orderName : 采购单
     * orderType : 1
     * checkPersonUserID : 1
     * userName : ewenXing
     */

    private String data_id;
    private String orderName;
    //0.无；1.采购单；2.采购退货单；3.报价单；4.销售单；5.零售单；6.销售退货单；
    // 7.个人领货单；8.领货归还单；9.其他出库单；10.其他入库单；11.调拨单；12.盘点单；13.零售退货单；14.收支单；
    private int orderType;
    private String checkPersonUserID;
    private String userName;

    public String getData_id() {
        return data_id;
    }

    public void setData_id(String data_id) {
        this.data_id = data_id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public String getCheckPersonUserID() {
        return checkPersonUserID;
    }

    public void setCheckPersonUserID(String checkPersonUserID) {
        this.checkPersonUserID = checkPersonUserID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
