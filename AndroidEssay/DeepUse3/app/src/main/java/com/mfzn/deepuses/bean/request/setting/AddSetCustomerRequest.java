package com.mfzn.deepuses.bean.request.setting;

import java.io.Serializable;

public class AddSetCustomerRequest implements Serializable {
   private String customerType;
   private String customerName;
   private String customerPhone;
   private String followStatusID;
   private String customerLevelID;
   private String customerSourceID;
   private String remark;
   private String companyCustomerID;
   private String salesPersonUserID;
   private String areaID;
   private String address;
   private String chargePersonName;
   private String chargePersonPhone;

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getFollowStatusID() {
        return followStatusID;
    }

    public void setFollowStatusID(String followStatusID) {
        this.followStatusID = followStatusID;
    }

    public String getCustomerLevelID() {
        return customerLevelID;
    }

    public void setCustomerLevelID(String customerLevelID) {
        this.customerLevelID = customerLevelID;
    }

    public String getCustomerSourceID() {
        return customerSourceID;
    }

    public void setCustomerSourceID(String customerSourceID) {
        this.customerSourceID = customerSourceID;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSalesPersonUserID() {
        return salesPersonUserID;
    }

    public void setSalesPersonUserID(String salesPersonUserID) {
        this.salesPersonUserID = salesPersonUserID;
    }

    public String getAreaID() {
        return areaID;
    }

    public void setAreaID(String areaID) {
        this.areaID = areaID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getChargePersonName() {
        return chargePersonName;
    }

    public void setChargePersonName(String chargePersonName) {
        this.chargePersonName = chargePersonName;
    }

    public String getChargePersonPhone() {
        return chargePersonPhone;
    }

    public void setChargePersonPhone(String chargePersonPhone) {
        this.chargePersonPhone = chargePersonPhone;
    }

    public String getCompanyCustomerID() {
        return companyCustomerID;
    }

    public void setCompanyCustomerID(String companyCustomerID) {
        this.companyCustomerID = companyCustomerID;
    }
}
