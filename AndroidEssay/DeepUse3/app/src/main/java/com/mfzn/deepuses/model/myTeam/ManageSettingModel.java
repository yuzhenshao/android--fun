package com.mfzn.deepuses.model.myTeam;

import java.io.Serializable;
import java.util.List;

public class ManageSettingModel implements Serializable {

    /**
     * userID : 28
     * roleID : 1
     * companyID : 14
     * departIDs : ,236,237,238,393,394,395,482
     * authCreate : 1
     * authData : 1
     * is_del : 0
     * addTime : 0
     * addUser : 0
     * updateTime : 0
     * updateUser : 0
     * authManage : 1
     * u_name : 我是好人
     * departName : [{"departID":236,"departmentName":"哈哈哈"},{"departID":237,"departmentName":"UI设计"},{"departID":238,"departmentName":"不急"},{"departID":393,"departmentName":"来来来"},{"departID":394,"departmentName":"哦哦哦"},{"departID":395,"departmentName":"啊啊啊"},{"departID":482,"departmentName":"市场2部"}]
     * staffName : 老板
     * data_id : 7
     * data_en_id : FvBhh1XXe
     */

    private int userID;
    private int roleID;
    private int companyID;
    private String departIDs;
    private int proCreateAuth;
    private int authData;
    private int is_del;
    private int addTime;
    private int addUser;
    private int updateTime;
    private int updateUser;
    private int authManage;
    private int rechargeAuth;

    public int getCrmAuth() {
        return crmAuth;
    }

    public void setCrmAuth(int crmAuth) {
        this.crmAuth = crmAuth;
    }

    private int crmAuth;
    private String u_name;
    private String staffName;
    private int data_id;
    private String data_en_id;
    private List<DepartNameBean> departName;

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

    public String getDepartIDs() {
        return departIDs;
    }

    public void setDepartIDs(String departIDs) {
        this.departIDs = departIDs;
    }


    public int getProCreateAuth() {
        return proCreateAuth;
    }

    public void setProCreateAuth(int proCreateAuth) {
        this.proCreateAuth = proCreateAuth;
    }

    public int getAuthData() {
        return authData;
    }

    public void setAuthData(int authData) {
        this.authData = authData;
    }

    public int getIs_del() {
        return is_del;
    }

    public void setIs_del(int is_del) {
        this.is_del = is_del;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public int getAddUser() {
        return addUser;
    }

    public void setAddUser(int addUser) {
        this.addUser = addUser;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }

    public int getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }

    public int getAuthManage() {
        return authManage;
    }

    public void setAuthManage(int authManage) {
        this.authManage = authManage;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
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

    public String getData_en_id() {
        return data_en_id;
    }

    public void setData_en_id(String data_en_id) {
        this.data_en_id = data_en_id;
    }

    public List<DepartNameBean> getDepartName() {
        return departName;
    }

    public void setDepartName(List<DepartNameBean> departName) {
        this.departName = departName;
    }

    public static class DepartNameBean implements Serializable {
        /**
         * departID : 236
         * departmentName : 哈哈哈
         */

        private int departID;
        private String departmentName;

        public int getDepartID() {
            return departID;
        }

        public void setDepartID(int departID) {
            this.departID = departID;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }
    }
}
