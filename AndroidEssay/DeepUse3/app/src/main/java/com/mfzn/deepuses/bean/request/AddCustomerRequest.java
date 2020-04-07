package com.mfzn.deepuses.bean.request;

/**
 * @author yz @date 2020-04-05
 */
public class AddCustomerRequest {
    private String companyID;
    private String customerName;
    private String customerPhone;
    private String followStatusID;
    private String customerLevelID;
    private String customerSourceID;
    private String remark;

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
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
}
