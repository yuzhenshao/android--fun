package com.mfzn.deepuses.bean.request;

/**
 * @author yz @date 2020-03-05
 */
public class UpdateManagerRequest {
    private String companyID;
    private String managerID;
    private String proCreateAuth;
    private String adminCreateAuth;
    private String rechargeAuth;
    private String crmAuth;


    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }


    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    public String getProCreateAuth() {
        return proCreateAuth;
    }

    public void setProCreateAuth(String proCreateAuth) {
        this.proCreateAuth = proCreateAuth;
    }

    public String getAdminCreateAuth() {
        return adminCreateAuth;
    }

    public void setAdminCreateAuth(String adminCreateAuth) {
        this.adminCreateAuth = adminCreateAuth;
    }

    public String getRechargeAuth() {
        return rechargeAuth;
    }

    public void setRechargeAuth(String rechargeAuth) {
        this.rechargeAuth = rechargeAuth;
    }

    public String getCrmAuth() {
        return crmAuth;
    }

    public void setCrmAuth(String crmAuth) {
        this.crmAuth = crmAuth;
    }
}
