package com.mfzn.deepuses.bean.request;

/**
 * @author yz @date 2020-03-05
 */
public class ReSendAsOrderRequest extends SendAsOrderRequest{
    private String shJobID;

    public String getShJobID() {
        return shJobID;
    }

    public void setShJobID(String shJobID) {
        this.shJobID = shJobID;
    }
}
