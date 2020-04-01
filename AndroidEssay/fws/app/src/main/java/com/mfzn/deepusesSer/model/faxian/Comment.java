package com.mfzn.deepusesSer.model.faxian;

public class Comment {


    /**
     * newsID : 1488
     * userID : 882
     * comment : 11111
     * addTime : 1574844767
     * is_del : 0
     * u_name : 刘总
     * u_head : /uploads/u_head/882/20191114/8385f328e11e58b5647680f8b8e89496.jpg
     * data_id : 2
     * data_en_id : Fv1aSfhXk
     */

    private int newsID;
    private int userID;
    private String comment;
    private int addTime;
    private int is_del;
    private String u_name;
    private String u_head;
    private int data_id;
    private String data_en_id;

    public int getNewsID() {
        return newsID;
    }

    public void setNewsID(int newsID) {
        this.newsID = newsID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public int getIs_del() {
        return is_del;
    }

    public void setIs_del(int is_del) {
        this.is_del = is_del;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_head() {
        return u_head;
    }

    public void setU_head(String u_head) {
        this.u_head = u_head;
    }

    public int getData_id() {
        return data_id;
    }

    public void setData_id(int data_id) {
        this.data_id = data_id;
    }

    public String getData_en_id() {
        return data_en_id;
    }

    public void setData_en_id(String data_en_id) {
        this.data_en_id = data_en_id;
    }
}
