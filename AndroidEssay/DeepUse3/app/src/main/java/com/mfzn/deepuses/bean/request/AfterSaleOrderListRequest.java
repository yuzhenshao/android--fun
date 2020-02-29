package com.mfzn.deepuses.bean.request;

import com.mfzn.deepuses.utils.UserHelper;

import retrofit2.http.Query;

/**
 * @author yz @date 2020-02-29
 */
public class AfterSaleOrderListRequest {
    private String uid;
    private String token;
    private String asType;
    private String proID;
    private String keywords;
    private int per;
    private int page;

//    @Query("proID") String proID, @Query("shType") String shType,
//    @Query("per") String per, @Query("page") Integer page,
//    @Query("keyword") String keyword


    public AfterSaleOrderListRequest(){
        this.uid=UserHelper.getUid();
        this.token=UserHelper.getToken();
    }

    public AfterSaleOrderListRequest(int per,int page){
        this.uid=UserHelper.getUid();
        this.token=UserHelper.getToken();
        this.proID="";
        this.asType="0";
        this.per=per;
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

    public String getAsType() {
        return asType;
    }

    public void setAsType(String asType) {
        this.asType = asType;
    }

    public String getProID() {
        return proID;
    }

    public void setProID(String proID) {
        this.proID = proID;
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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
