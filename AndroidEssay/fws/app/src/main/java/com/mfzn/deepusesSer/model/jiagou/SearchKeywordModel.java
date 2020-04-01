package com.mfzn.deepusesSer.model.jiagou;

public class SearchKeywordModel {

    /**
     * staffRecordID : 19
     * departmentID : 22
     * userID : 1
     * positionName : 市场专员2
     * u_head : /public/images/u_head/1/20190505/2cff4ca7ae864420fda9ee9e3cdbb2a7.jpeg
     * departmentName : 默认
     */

    private int staffRecordID;
    private int departmentID;
    private int userID;
    private String positionName;
    private String u_head;
    private String departmentName;

    public int getStaffRecordID() {
        return staffRecordID;
    }

    public void setStaffRecordID(int staffRecordID) {
        this.staffRecordID = staffRecordID;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getU_head() {
        return u_head;
    }

    public void setU_head(String u_head) {
        this.u_head = u_head;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
