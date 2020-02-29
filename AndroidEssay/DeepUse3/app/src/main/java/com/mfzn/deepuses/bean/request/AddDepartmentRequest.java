package com.mfzn.deepuses.bean.request;

import com.mfzn.deepuses.utils.UserHelper;

/**
 * @author yz @date 2020-02-29
 */
public class AddDepartmentRequest {
    private String companyID;
    private String departmentName;
    private String departmentID;

    public AddDepartmentRequest(String departmentID,String departmentName){
        this.companyID= UserHelper.getCompanyId();
        this.departmentID=departmentID;
        this.departmentName=departmentName;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }
}
