package com.mfzn.deepuses.model.khgl;

import java.util.List;

public class FollowProModel {

    /**
     * companyID : 1
     * followStatusID : 1
     * userID : 2
     * customerID : 2
     * communicationTypeID : 1
     * content : 哈哈哈
     * imageUrls : ["/uoloads/1.jpg","/uoloads/2.jpg"]
     * isDel : 0
     * addTime : 1577104476
     * updateTime : 0
     * salesPersonName : 791
     * salesPersonAvatar : /uploads/u_head/20190115134258-7.jpg
     * followStatus : 意向中
     * communicationType : 微信沟通
     * data_id : 1
     * data_en_id : Fh1SQdvX4
     */

    private int companyID;
    private int followStatusID;
    private int userID;
    private int customerID;
    private int communicationTypeID;
    private String content;
    private int isDel;
    private int addTime;
    private int updateTime;
    private String salesPersonName;
    private String salesPersonAvatar;
    private String followStatus;
    private String communicationType;
    private int data_id;
    private String data_en_id;
    private List<String> imageUrls;

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public int getFollowStatusID() {
        return followStatusID;
    }

    public void setFollowStatusID(int followStatusID) {
        this.followStatusID = followStatusID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getCommunicationTypeID() {
        return communicationTypeID;
    }

    public void setCommunicationTypeID(int communicationTypeID) {
        this.communicationTypeID = communicationTypeID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }

    public String getSalesPersonName() {
        return salesPersonName;
    }

    public void setSalesPersonName(String salesPersonName) {
        this.salesPersonName = salesPersonName;
    }

    public String getSalesPersonAvatar() {
        return salesPersonAvatar;
    }

    public void setSalesPersonAvatar(String salesPersonAvatar) {
        this.salesPersonAvatar = salesPersonAvatar;
    }

    public String getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(String followStatus) {
        this.followStatus = followStatus;
    }

    public String getCommunicationType() {
        return communicationType;
    }

    public void setCommunicationType(String communicationType) {
        this.communicationType = communicationType;
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

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
