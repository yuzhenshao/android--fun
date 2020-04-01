package com.mfzn.deepuses.bean.request;

import retrofit2.http.Field;

/**
 * @author yz @date 2020-03-05
 */
public class SendAsOrderRequest {
    private String orderNo;
    private String name;
    private String phone;
    private String note;
    private String engineerID;
    private int proID;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getEngineerID() {
        return engineerID;
    }

    public void setEngineerID(String engineerID) {
        this.engineerID = engineerID;
    }

    public int getProID() {
        return proID;
    }

    public void setProID(int proID) {
        this.proID = proID;
    }
}
