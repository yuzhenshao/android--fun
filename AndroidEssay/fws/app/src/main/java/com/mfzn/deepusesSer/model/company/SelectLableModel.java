package com.mfzn.deepusesSer.model.company;

import java.util.List;

public class SelectLableModel {

    /**
     * data_id : 12
     * name : 智能家居
     * pid : 0
     * level : 0
     * son : []
     */

    private int data_id;
    private String name;
    private int pid;
    private int level;
    private Boolean click = false;
    private List<?> son;

    public Boolean getClick() {
        return click;
    }

    public void setClick(Boolean click) {
        this.click = click;
    }

    public int getData_id() {
        return data_id;
    }

    public void setData_id(int data_id) {
        this.data_id = data_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<?> getSon() {
        return son;
    }

    public void setSon(List<?> son) {
        this.son = son;
    }
}
