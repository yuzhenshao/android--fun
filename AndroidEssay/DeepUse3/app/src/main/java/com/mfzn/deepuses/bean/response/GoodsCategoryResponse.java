package com.mfzn.deepuses.bean.response;

import com.libcommon.tree.NodeId;

import java.util.List;

/**
 * @author yz @date 2020-03-30
 */
public class GoodsCategoryResponse implements NodeId {

    /**
     * catID : 2
     * companyID : 2
     * catName : 上衣
     * pID : 0
     * addTime : 1583754864
     * sons : []
     */

    private String catID;
    private int companyID;
    private String catName;
    private int pID;
    private int addTime;
    private boolean isSelected;
    private List<GoodsCategoryResponse> sons;

    public String getCatID() {
        return catID;
    }

    public void setCatID(String catID) {
        this.catID = catID;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public int getPID() {
        return pID;
    }

    public void setPID(int pID) {
        this.pID = pID;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public List<GoodsCategoryResponse> getSons() {
        return sons;
    }

    public void setSons(List<GoodsCategoryResponse> sons) {
        this.sons = sons;
    }

    @Override
    public String getNodeId() {
        return catID;
    }

    @Override
    public String getNodePId() {
        return null;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
