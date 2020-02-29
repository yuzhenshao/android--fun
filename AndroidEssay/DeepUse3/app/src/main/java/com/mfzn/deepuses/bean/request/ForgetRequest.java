package com.mfzn.deepuses.bean.request;

/**
 * @author yz @date 2020-02-26
 */
public class ForgetRequest {
    private String smsCode;
    private String userPhone;
    private String userPwd;
    private String userRePwd;

    public ForgetRequest(String smsCode,String userPhone,String userPwd){
        this.smsCode=smsCode;
        this.userPhone=userPhone;
        this.userPwd=userPwd;
        this.userRePwd=userPwd;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
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
}
