package com.mfzn.deepuses.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * @author yz @date 2020-02-22
 */
public class BusinessCardResponse implements Serializable {

    /**
     * businessCardID : 1
     * userID : 1
     * cardPhone : 15312555080
     * cardCompanyID : 2
     * companyName : 冲浪网络
     * companyLogo : 111.jpg
     * showCompany : 0
     * showProNum : 0
     * userAvatar :
     * userName : ewen
     * userEmail :
     * userPosition :
     * workYear :
     * jzNum : 0
     * gzNum : 0
     * proMemberLabels : [{"labelID":2,"labelName":"工程师"}]
     * proNum : 1
     * myQrCode : uploads/recImages/15312555080_recImage.png
     */

    private int businessCardID;
    private int userID;
    private String cardPhone;
    private int cardCompanyID;
    private String companyName;
    private String companyLogo;
    private int showCompany;
    private int showProNum;
    private String userAvatar;
    private String userName;
    private String userEmail;
    private String userPosition;
    private String workYear;
    private String jzNum;
    private String gzNum;
    private String proNum;
    private String myQrCode;
    private List<ProMemberLabelsBean> proMemberLabels;

    public int getBusinessCardID() {
        return businessCardID;
    }

    public void setBusinessCardID(int businessCardID) {
        this.businessCardID = businessCardID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getCardPhone() {
        return cardPhone;
    }

    public void setCardPhone(String cardPhone) {
        this.cardPhone = cardPhone;
    }

    public int getCardCompanyID() {
        return cardCompanyID;
    }

    public void setCardCompanyID(int cardCompanyID) {
        this.cardCompanyID = cardCompanyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public int getShowCompany() {
        return showCompany;
    }

    public void setShowCompany(int showCompany) {
        this.showCompany = showCompany;
    }

    public int getShowProNum() {
        return showProNum;
    }

    public void setShowProNum(int showProNum) {
        this.showProNum = showProNum;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

    public String getJzNum() {
        return jzNum;
    }

    public void setJzNum(String jzNum) {
        this.jzNum = jzNum;
    }

    public String getGzNum() {
        return gzNum;
    }

    public void setGzNum(String gzNum) {
        this.gzNum = gzNum;
    }

    public String getProNum() {
        return proNum;
    }

    public void setProNum(String proNum) {
        this.proNum = proNum;
    }

    public String getMyQrCode() {
        return myQrCode;
    }

    public void setMyQrCode(String myQrCode) {
        this.myQrCode = myQrCode;
    }

    public List<ProMemberLabelsBean> getProMemberLabels() {
        return proMemberLabels;
    }

    public void setProMemberLabels(List<ProMemberLabelsBean> proMemberLabels) {
        this.proMemberLabels = proMemberLabels;
    }

    public static class ProMemberLabelsBean {
        /**
         * labelID : 2
         * labelName : 工程师
         */

        private int labelID;
        private String labelName;

        public int getLabelID() {
            return labelID;
        }

        public void setLabelID(int labelID) {
            this.labelID = labelID;
        }

        public String getLabelName() {
            return labelName;
        }

        public void setLabelName(String labelName) {
            this.labelName = labelName;
        }
    }

}
