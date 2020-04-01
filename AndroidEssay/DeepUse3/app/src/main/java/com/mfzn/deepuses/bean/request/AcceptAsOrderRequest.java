package com.mfzn.deepuses.bean.request;

import com.mfzn.deepuses.utils.UserHelper;

/**
 * @author yz @date 2020-03-01
 */
public class AcceptAsOrderRequest {
    private String orderNo;
    private int isAccept;//是否受理，1受理2不受理
    private String result;
    private String recommendContact;

    public AcceptAsOrderRequest(String orderNo, int isAccept, String result, String recommendContact) {
        this.orderNo = orderNo;
        this.isAccept = isAccept;
        this.result = result;
        this.recommendContact = recommendContact;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getIsAccept() {
        return isAccept;
    }

    public void setIsAccept(int isAccept) {
        this.isAccept = isAccept;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRecommendContact() {
        return recommendContact;
    }

    public void setRecommendContact(String recommendContact) {
        this.recommendContact = recommendContact;
    }
}
