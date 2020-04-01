package com.mfzn.deepuses.home.application.model;

import java.util.List;

/**
 * @author yz @date 2020-03-27
 */
public class ApplicationModel {
    private String title;
    private List<TabModel> tabModelList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TabModel> getTabModelList() {
        return tabModelList;
    }

    public void setTabModelList(List<TabModel> tabModelList) {
        this.tabModelList = tabModelList;
    }
}
