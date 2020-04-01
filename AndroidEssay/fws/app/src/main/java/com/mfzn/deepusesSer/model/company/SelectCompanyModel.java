package com.mfzn.deepusesSer.model.company;

public class SelectCompanyModel {

    /**
     * companyID : 1
     * companyName : 麦麸智能工程有限公司
     * createUserID : 312
     */

    private String companyID;
    private String companyName;
    private int createUserID;

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCreateUserID() {
        return createUserID;
    }

    public void setCreateUserID(int createUserID) {
        this.createUserID = createUserID;
    }
}
