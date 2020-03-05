package com.mfzn.deepuses.bean.request;

/**
 * @author yz @date 2020-03-05
 */
public class AddManagerRequest extends UpdateManagerRequest{

    private String managerRoleID;

    public String getManagerRoleID() {
        return managerRoleID;
    }

    public void setManagerRoleID(String managerRoleID) {
        this.managerRoleID = managerRoleID;
    }
}
