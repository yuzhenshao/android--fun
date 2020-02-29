package com.mfzn.deepuses.model.myTeam;

import java.io.Serializable;
import java.util.List;

public class TeamManageModel implements Serializable {

    /**
     * companyID : 14
     * companyName : 麦麸智能科技（常州）公司
     * logo : /uploads/companyLogo/14/20191120/bc03afb5b61e9ab3c5f879e2c4130533.jpg
     * companyTel : 18306118609
     * companyLicence : /uploads/businessLicense/20190412/594cb772149a9e8a930c20cae961b118.jpg
     * companyCode : PQRJ
     * addTime : 1555038522
     * companyWebsite : www.mfzn.com
     * businessScope : 3,6,9,12
     * shortName : 麦麸智能（常州）有限公司
     * companyAddress : 常州武进安通大厦1ddd
     * scaleID : 4
     * latitude : 31.727844
     * longitude : 119.916451
     * businessScopeName : 智能弱电,智慧酒店,智能遮阳,照明灯具
     * scaleName : 51~100人
     * isCheck : 1
     */

    private int companyID;
    private String companyName;
    private String companyLogo;
    private String companyTel;
    private String companyLicence;
    private String companyCode;
    private int addTime;
    private String companyWebsite;
    private String businessScope;
    private String shortName;
    private String companyAddress;
    private int scaleID;
    private double latitude;
    private double longitude;
    private String businessScopeName;
    private String scaleName;
    private int isCheck;

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
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

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public String getCompanyLicence() {
        return companyLicence;
    }

    public void setCompanyLicence(String companyLicence) {
        this.companyLicence = companyLicence;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public int getScaleID() {
        return scaleID;
    }

    public void setScaleID(int scaleID) {
        this.scaleID = scaleID;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getBusinessScopeName() {
        return businessScopeName;
    }

    public void setBusinessScopeName(String businessScopeName) {
        this.businessScopeName = businessScopeName;
    }

    public String getScaleName() {
        return scaleName;
    }

    public void setScaleName(String scaleName) {
        this.scaleName = scaleName;
    }

    public int getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(int isCheck) {
        this.isCheck = isCheck;
    }
}
