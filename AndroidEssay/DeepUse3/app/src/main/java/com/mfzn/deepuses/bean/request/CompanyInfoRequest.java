package com.mfzn.deepuses.bean.request;

import com.mfzn.deepuses.utils.UserHelper;

/**
 * @author yz @date 2020-02-27
 */
public class CompanyInfoRequest {
    private String uid;
    private String token;
    private String companyID;

    public CompanyInfoRequest() {
        this.token = UserHelper.getToken();
        this.uid = UserHelper.getUid();
        this.companyID = UserHelper.getCompanyId();
    }

    public CompanyInfoRequest(String companyId) {
        this.uid = UserHelper.getUid();
        this.token = UserHelper.getToken();
        this.companyID = companyId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }
}
