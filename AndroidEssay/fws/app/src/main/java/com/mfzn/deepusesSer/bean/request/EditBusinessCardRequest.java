package com.mfzn.deepusesSer.bean.request;

/**
 * @author yz @date 2020-02-23
 */
public class EditBusinessCardRequest {
    private int cardCompanyID;
    private String cardPhone;
    private int showCompany;
    private int showProNum;

    private String userEmail;
    private String userPosition;
    private String workYear;
    private String jzNum;
    private String gzNum;


    public int getCardCompanyID() {
        return cardCompanyID;
    }

    public void setCardCompanyID(int cardCompanyID) {
        this.cardCompanyID = cardCompanyID;
    }

    public String getCardPhone() {
        return cardPhone;
    }

    public void setCardPhone(String cardPhone) {
        this.cardPhone = cardPhone;
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
}
