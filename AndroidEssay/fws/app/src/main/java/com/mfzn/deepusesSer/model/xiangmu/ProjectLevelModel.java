package com.mfzn.deepusesSer.model.xiangmu;

public class ProjectLevelModel {

    /**
     * levelName : 一星级
     * is_del : 0
     * orderNum : 0
     * fileUrl :
     * data_id : 4
     * data_en_id : FQQdSSXXP
     */

    private String levelName;
    private int is_del;
    private int orderNum;
    private String fileUrl;
    private String data_id;
    private String data_en_id;

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getIs_del() {
        return is_del;
    }

    public void setIs_del(int is_del) {
        this.is_del = is_del;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getData_id() {
        return data_id;
    }

    public void setData_id(String data_id) {
        this.data_id = data_id;
    }

    public String getData_en_id() {
        return data_en_id;
    }

    public void setData_en_id(String data_en_id) {
        this.data_en_id = data_en_id;
    }
}
