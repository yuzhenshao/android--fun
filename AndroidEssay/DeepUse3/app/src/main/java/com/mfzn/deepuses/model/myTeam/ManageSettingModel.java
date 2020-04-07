package com.mfzn.deepuses.model.myTeam;

import java.io.Serializable;
import java.util.List;

public class ManageSettingModel implements Serializable {

    /* "data_id": 1,
            "userID": 1,
            "roleID": 1,
            "companyID": 2,
            "proCreateAuth": 1,
            "adminCreateAuth": 1,
            "rechargeAuth": 1,
            "crmAuth": 1,
            "userName": "ewen",
            "staffName": "ewen"
            */

    private int userID;
    private int roleID;
    private int companyID;
    private int proCreateAuth;//项目
    private int adminCreateAuth;
    private int rechargeAuth;
    //private int authManage;
    private int crmAuth;
    private String userName;
    private String staffName;
    private int data_id;

    public int getRechargeAuth() {
        return rechargeAuth;
    }

    public void setRechargeAuth(int rechargeAuth) {
        this.rechargeAuth = rechargeAuth;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public int getProCreateAuth() {
        return proCreateAuth;
    }

    public void setProCreateAuth(int proCreateAuth) {
        this.proCreateAuth = proCreateAuth;
    }

    public int getCrmAuth() {
        return crmAuth;
    }

    public void setCrmAuth(int crmAuth) {
        this.crmAuth = crmAuth;
    }

    public String getU_name() {
        return userName;
    }

    public void setU_name(String u_name) {
        this.userName = u_name;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public int getData_id() {
        return data_id;
    }

    public void setData_id(int data_id) {
        this.data_id = data_id;
    }

    public int getAdminCreateAuth() {
        return adminCreateAuth;
    }

    public void setAdminCreateAuth(int adminCreateAuth) {
        this.adminCreateAuth = adminCreateAuth;
    }
}
