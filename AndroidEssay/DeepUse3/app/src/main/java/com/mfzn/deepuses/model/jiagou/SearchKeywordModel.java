package com.mfzn.deepuses.model.jiagou;

import java.io.Serializable;

public class SearchKeywordModel implements Serializable {


    /**
     * staffRecordID : 205
     * departmentID : 91
     * uid : 377
     * positionName :
     * staffName : 南京
     * u_head : /uploads/u_head/377/20190619/6173646edefb529330e8881c5e344f6a.jpg
     * u_name : 姜利杰
     * userno : 27275427
     * u_phone : 13921067307
     * roleID : 3
     * departmentName : 默认
     */

    private int staffRecordID;
    private int departmentID;
    private int uid;
    private String positionName;
    private String staffName;
    private String u_head;
    private String u_name;
    private String userno;
    private String u_phone;
    private int roleID;
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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getU_head() {
        return u_head;
    }

    public void setU_head(String u_head) {
        this.u_head = u_head;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getUserno() {
        return userno;
    }

    public void setUserno(String userno) {
        this.userno = userno;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
