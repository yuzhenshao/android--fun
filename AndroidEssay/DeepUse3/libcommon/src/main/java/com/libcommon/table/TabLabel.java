package com.libcommon.table;

import java.io.Serializable;

/**
 * @author yz @date 2020-05-04
 */
public class TabLabel implements TabItem, Serializable {
    private int id;

    private String name;

    public TabLabel(String name){
        this.name = name;
    }

    public TabLabel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
