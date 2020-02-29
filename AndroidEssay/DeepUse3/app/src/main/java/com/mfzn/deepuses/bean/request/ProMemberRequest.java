package com.mfzn.deepuses.bean.request;

import com.mfzn.deepuses.utils.UserHelper;

/**
 * @author yz @date 2020-02-27
 */
public class ProMemberRequest {
    private String companyID;
    private String proID;
    private String memberUserID;
    private String memberLabelID;

    public ProMemberRequest(String proID, String memberUserID, String memberLabelID) {
        this.companyID = UserHelper.getCompanyId();
        this.proID = proID;
        this.memberUserID = memberUserID;
        this.memberLabelID = memberLabelID;

    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getProID() {
        return proID;
    }

    public void setProID(String proID) {
        this.proID = proID;
    }

    public String getMemberUserID() {
        return memberUserID;
    }

    public void setMemberUserID(String memberUserID) {
        this.memberUserID = memberUserID;
    }

    public String getMemberLabelID() {
        return memberLabelID;
    }

    public void setMemberLabelID(String memberLabelID) {
        this.memberLabelID = memberLabelID;
    }
}
