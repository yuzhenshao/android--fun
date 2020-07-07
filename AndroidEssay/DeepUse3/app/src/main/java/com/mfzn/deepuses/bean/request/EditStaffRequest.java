package com.mfzn.deepuses.bean.request;

/**
 * @author yz @date 2020-04-25
 */
public class EditStaffRequest {
    private String staffUID;
    private String oldDepartmentID;
    private String newDepartmentID;
    private String staffName;
    private String joinTime;
    private String companyID;

    public String getStaffUID() {
        return staffUID;
    }

    public void setStaffUID(String staffUID) {
        this.staffUID = staffUID;
    }

    public String getOldDepartmentID() {
        return oldDepartmentID;
    }

    public void setOldDepartmentID(String oldDepartmentID) {
        this.oldDepartmentID = oldDepartmentID;
    }

    public String getNewDepartmentID() {
        return newDepartmentID;
    }

    public void setNewDepartmentID(String newDepartmentID) {
        this.newDepartmentID = newDepartmentID;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }
}
