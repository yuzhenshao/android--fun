package com.mfzn.deepuses.bean.response.settings;

import java.io.Serializable;

/**
 * @author yz @date 2020-05-03
 */
public class OtherCostResponse implements Serializable {

    /**
     * otherCostTypeID : 1
     * otherCostTypeName : 运费
     * addTime : 1583764786
     */

    private String  otherCostTypeID;
    private String otherCostTypeName;
    private long addTime;

    public String getOtherCostTypeID() {
        return otherCostTypeID;
    }

    public void setOtherCostTypeID(String otherCostTypeID) {
        this.otherCostTypeID = otherCostTypeID;
    }

    public String getOtherCostTypeName() {
        return otherCostTypeName;
    }

    public void setOtherCostTypeName(String otherCostTypeName) {
        this.otherCostTypeName = otherCostTypeName;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }
}
