package com.mfzn.deepuses.bean.request;

/**
 * @author yz @date 2020-02-25
 */
public class LoginRequest {
    private String userPhone;
    private String userPwd;

    public LoginRequest(String userPhone,String userPwd){
        this.userPhone=userPhone;
        this.userPwd=userPwd;
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
}
