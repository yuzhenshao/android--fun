package com.mfzn.deepuses.bean.request;

/**
 * @author yz @date 2020-04-05
 */
public class AddFollowRecordRequest {
    private String companyID;
    private String communicationTypeID;
    private String followStatusID;
    private String customerID;
    private String content;
    private String imageUrls;
    private String followTime;
    private String telephone;
    private String phone;

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }


    public String getFollowStatusID() {
        return followStatusID;
    }

    public void setFollowStatusID(String followStatusID) {
        this.followStatusID = followStatusID;
    }

    public String getCommunicationTypeID() {
        return communicationTypeID;
    }

    public void setCommunicationTypeID(String communicationTypeID) {
        this.communicationTypeID = communicationTypeID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getFollowTime() {
        return followTime;
    }

    public void setFollowTime(String followTime) {
        this.followTime = followTime;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
