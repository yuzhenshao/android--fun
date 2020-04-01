package com.mfzn.deepuses.bean.request;

import com.mfzn.deepuses.utils.UserHelper;

/**
 * @author yz @date 2020-03-01
 */
public class EditAsServicePeopleRequest {
    private String kfID;
    private String kfTypeID;
    private String name;
    private String phone;

    public EditAsServicePeopleRequest(String kfID,String kfTypeID, String name, String phone) {
        this.kfID=kfID;
        this.kfTypeID = kfTypeID;
        this.name = name;
        this.phone = phone;

    }


    public String getKfTypeID() {
        return kfTypeID;
    }

    public void setKfTypeID(String kfTypeID) {
        this.kfTypeID = kfTypeID;
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

    public String getKfID() {
        return kfID;
    }

    public void setKfID(String kfID) {
        this.kfID = kfID;
    }
}
