package com.mfzn.deepuses.bean.request;

import com.mfzn.deepuses.utils.UserHelper;

import retrofit2.http.Query;

/**
 * @author yz @date 2020-03-01
 */
public class AddCustomRequest {
    private String companyID;
    private String kfTypeID;
    private String name;
    private String phone;

    public AddCustomRequest(String kfTypeID, String name, String phone) {
        this.companyID = UserHelper.getCompanyId();
        this.kfTypeID = kfTypeID;
        this.name = name;
        this.phone = phone;

    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
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
}
