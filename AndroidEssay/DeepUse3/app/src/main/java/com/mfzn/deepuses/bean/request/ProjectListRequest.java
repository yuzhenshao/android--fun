package com.mfzn.deepuses.bean.request;

import com.mfzn.deepuses.utils.UserHelper;

/**
 * @author yz @date 2020-02-27
 */
public class ProjectListRequest {
    public static int MY_PROJECTS=1;
    public static int COMPANY_PROJECTS=2;

    private String uid;
    private String token;
    private String companyID;
    private String keywords;
    private int per;
    private int page;
    private int type;


   public ProjectListRequest(int page,int type){
       this.token = UserHelper.getToken();
       this.uid = UserHelper.getUid();
       this.companyID = UserHelper.getCompanyId();
       this.per=10;
       this.type=type;
       this.page=page;
   }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getPer() {
        return per;
    }

    public void setPer(int per) {
        this.per = per;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
