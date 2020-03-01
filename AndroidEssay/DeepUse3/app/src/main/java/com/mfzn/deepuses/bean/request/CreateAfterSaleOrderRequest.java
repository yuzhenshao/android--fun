package com.mfzn.deepuses.bean.request;

/**
 * @author yz @date 2020-03-01
 */
public class CreateAfterSaleOrderRequest {
    private String proID;
    private String asType;
    private String contactName;
    private String contactPhone;
    private String wishTime;
    private String content;
    private String fileUrls;

    public String getProID() {
        return proID;
    }

    public void setProID(String proID) {
        this.proID = proID;
    }

    public String getAsType() {
        return asType;
    }

    public void setAsType(String asType) {
        this.asType = asType;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getWishTime() {
        return wishTime;
    }

    public void setWishTime(String wishTime) {
        this.wishTime = wishTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileUrls() {
        return fileUrls;
    }

    public void setFileUrls(String fileUrls) {
        this.fileUrls = fileUrls;
    }
}
