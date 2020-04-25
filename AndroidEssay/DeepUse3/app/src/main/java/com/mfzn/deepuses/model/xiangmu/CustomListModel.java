package com.mfzn.deepuses.model.xiangmu;

import java.io.Serializable;

public class CustomListModel implements Serializable {

    /**
     * companyID : 1
     * kfTypeID : 2
     * name : 啧啧啧
     * phone : 116
     * addTime : 1573087695
     * addUserID : 312
     * updateTime : 0
     * updateUserID : 0
     * is_del : 0
     * typeName : 影音
     * data_id : 1
     * data_en_id : FahBBddd4
     */

    private int companyID;
    private int spTypeID;
    private String name;
    private String phone;
    //    private int addTime;
//    private int addUserID;
//    private int updateTime;
//    private int updateUserID;
    private int isDel;
    private String typeName;
    private int data_id;
    //private String data_en_id;
    private Boolean typeSelect = false;

    public Boolean getTypeSelect() {
        return typeSelect;
    }

    public void setTypeSelect(Boolean typeSelect) {
        this.typeSelect = typeSelect;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public int getKfTypeID() {
        return spTypeID;
    }

    public void setKfTypeID(int kfTypeID) {
        this.spTypeID = kfTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIs_del() {
        return isDel;
    }

    public void setIs_del(int is_del) {
        this.isDel = is_del;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getData_id() {
        return data_id;
    }

    public void setData_id(int data_id) {
        this.data_id = data_id;
    }
}
