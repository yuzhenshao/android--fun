package com.mfzn.deepuses.model.xiangmu;

import android.net.Uri;
import android.text.TextUtils;

import com.mfzn.deepuses.net.ApiHelper;

import java.util.ArrayList;

public class ClzGongdanDetailModel {
    private String orderNo;
    private String faultText;
    private String faultFileUrls;
    private String content;
    private String contentFileUrls;
    private int status;
    private String sign;
    private int createUserId;
    private int updateUserId;
    private int addTime;
    private int updateTime;
    private int isDel;
    private int orderNum;
    private int data_id;
    private String data_en_id;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getFaultText() {
        return faultText;
    }

    public void setFaultText(String faultText) {
        this.faultText = faultText;
    }

    public String getFaultFileId() {
        return faultFileUrls;
    }

    public void setFaultFileId(String faultFileId) {
        this.faultFileUrls = faultFileId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentFileId() {
        return contentFileUrls;
    }

    public void setContentFileId(String contentFileId) {
        this.contentFileUrls = contentFileId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }

    public int getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(int updateUserId) {
        this.updateUserId = updateUserId;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }

    public int getIs_del() {
        return isDel;
    }

    public void setIs_del(int isDel) {
        this.isDel = isDel;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getData_id() {
        return data_id;
    }

    public void setData_id(int data_id) {
        this.data_id = data_id;
    }

    public String getData_en_id() {
        return data_en_id;
    }

    public void setData_en_id(String data_en_id) {
        this.data_en_id = data_en_id;
    }

    public ArrayList<Uri> getFaultFileIdUrl() {
        ArrayList<Uri> urls = new ArrayList<>();
        if (!TextUtils.isEmpty(faultFileUrls)) {
            String[] files = faultFileUrls.split(",");
            if (files.length > 0) {
                for (int i = 0; i < files.length; i++) {
                    if (!TextUtils.isEmpty(files[i])) {
                        urls.add(Uri.parse(ApiHelper.BASE_URL + files[i]));
                    }
                }
            } else {
                urls.add(Uri.parse(ApiHelper.BASE_URL + faultFileUrls));
            }
        }
        return urls;
    }


    public ArrayList<Uri> getContentFileIdUrl() {
        ArrayList<Uri> urls = new ArrayList<>();
        if (!TextUtils.isEmpty(contentFileUrls)) {
            String[] files = contentFileUrls.split(",");
            if (files.length > 0) {
                for (int i = 0; i < files.length; i++) {
                    if (!TextUtils.isEmpty(files[i])) {
                        urls.add(Uri.parse(ApiHelper.BASE_URL + files[i]));
                    }
                }
            } else {
                urls.add(Uri.parse(ApiHelper.BASE_URL + contentFileUrls));
            }
        }
        return urls;
    }
}
