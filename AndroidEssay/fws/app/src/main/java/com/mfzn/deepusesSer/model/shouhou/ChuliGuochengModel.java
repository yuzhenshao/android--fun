package com.mfzn.deepusesSer.model.shouhou;

public class ChuliGuochengModel {

    /**
     * orderNo : yzs2017082600001
     * addTime : 1563869286
     * typeName : 回单
     * content : <1>点此查看处理中回单
     * is_del : 0
     * orderNum : 0
     * data_id : 177
     * data_en_id : pahaXd4ee
     */

    private String orderNo;
    private int addTime;
    private String typeName;
    private String content;
    private int data_id;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getData_id() {
        return data_id;
    }

    public void setData_id(int data_id) {
        this.data_id = data_id;
    }
}
