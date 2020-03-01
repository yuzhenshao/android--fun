package com.mfzn.deepuses.bean.request;

/**
 * @author yz @date 2020-03-01
 */
public class NewsListRequest {
    private int per;
    private int page;
    private String kw;
    private String catID;


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

    public String getKw() {
        return kw;
    }

    public void setKw(String kw) {
        this.kw = kw;
    }

    public String getCatID() {
        return catID;
    }

    public void setCatID(String catID) {
        this.catID = catID;
    }
}
