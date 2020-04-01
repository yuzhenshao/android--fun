package com.wevey.selector.dialog.bean;

import java.util.List;

public class BottomListviewModel {

    /**
     * addTime : 0
     * companyID : 0
     * data_en_id : FahBBddd4
     * data_id : 1
     * is_del : 0
     * mainNodes : [{"addTime":0,"data_en_id":"FahBBddd4","data_id":1,"is_del":0,"name":"施工管理","orderNum":0,"type":1,"updateTime":0,"updateUserID":0},{"addTime":0,"data_en_id":"FXvddSSfk","data_id":2,"is_del":0,"name":"衔接配合","orderNum":0,"type":1,"updateTime":0,"updateUserID":0},{"addTime":0,"data_en_id":"F1QaSSf1U","data_id":3,"is_del":0,"name":"安装调试","orderNum":0,"type":1,"updateTime":0,"updateUserID":0},{"addTime":0,"data_en_id":"FQQdSSXXP","data_id":4,"is_del":0,"name":"售后服务","orderNum":0,"type":1,"updateTime":0,"updateUserID":0}]
     * orderNum : 0
     * typeName : 智能家居
     * updateTime : 0
     * updateUserID : 0
     * userID : 0
     */

    private int type;
    private String name;

    public BottomListviewModel(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public BottomListviewModel() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
