package com.mfzn.deepuses.bean.request;

/**
 * @author yz @date 2020-02-27
 */
public class ChangePwdRequest {
    private String oldPwd;
    private String userPwd;
    private String userRePwd;

    public ChangePwdRequest(String oldPwd,String userPwd,String userRePwd){
        this.oldPwd=oldPwd;
        this.userPwd=userPwd;
        this.userRePwd=userPwd;
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

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }
}
