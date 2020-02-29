package com.mfzn.deepuses.bean.request;

/**
 * @author yz @date 2020-02-26
 */
public class RegisterRequest {
    private String userPhone;
    private String smsCode;
    private String userName;
    private String userPwd;
    private String userRePwd;
    private String userType;

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserRePwd() {
        return userRePwd;
    }

    public void setUserRePwd(String userRePwd) {
        this.userRePwd = userRePwd;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
