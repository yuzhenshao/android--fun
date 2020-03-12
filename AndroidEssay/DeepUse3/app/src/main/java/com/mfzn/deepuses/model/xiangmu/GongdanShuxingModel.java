package com.mfzn.deepuses.model.xiangmu;

import android.net.Uri;
import android.text.TextUtils;

import com.mfzn.deepuses.net.ApiHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GongdanShuxingModel {
    private String orderNo;
    private int asType;
    private int proID;
    private String isUnderWarranty;
    private int status;
    private int createUserID;
    private String contactName;
    private String contactPhone;
    private String wishTime;
    private String content;
    private int isAccept;
    private String result;
    private String recommendContact;
    private int isCancel;
    private String cancelNote;
    private int addTime;
    private int updateTime;
    private int updateUserId;
    private int isDel;
    private int orderNum;
    private int isPay;
    private int sumPrice;
    private int realPrice;
    private int payTime;
    private int backTime;
    private int addUserID;
    private String serviceType;
    private String serviceTime;
    private String shTypeName;
    private String statusTypeName;
    private String userName;
    private String detailAddress;
    private int companyID;
    private String address;
    private double latitude;
    private double longitude;
    private String proName;
    //
    private String customName;
    private String customTel;
    //
    private int qualityIsGB;
    private int ybIsGB;
    private EnginerInfoBean engineerInfo;
    private String fileUrls;
    private List<CustomersInfo> customersInfo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }


    public int getAsType() {
        return asType;
    }

    public void setAsType(int asType) {
        this.asType = asType;
    }


    public int getProId() {
        return proID;
    }

    public void setProId(int proID) {
        this.proID = proID;
    }

    public String getIsUnderWarranty() {
        return isUnderWarranty;
    }

    public void setIsUnderWarranty(String isUnderWarranty) {
        this.isUnderWarranty = isUnderWarranty;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCreateUserId() {
        return createUserID;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserID = createUserId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getWishTime() {
        return wishTime;
    }

    public void setWishTime(String wishTime) {
        this.wishTime = wishTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(int isCancel) {
        this.isCancel = isCancel;
    }

    public String getCancelNote() {
        return cancelNote;
    }

    public void setCancelNote(String cancelNote) {
        this.cancelNote = cancelNote;
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

    public int getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(int updateUserId) {
        this.updateUserId = updateUserId;
    }


    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getIsPay() {
        return isPay;
    }

    public void setIsPay(int isPay) {
        this.isPay = isPay;
    }

    public int getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(int sumPrice) {
        this.sumPrice = sumPrice;
    }

    public int getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(int realPrice) {
        this.realPrice = realPrice;
    }

    public int getPayTime() {
        return payTime;
    }

    public void setPayTime(int payTime) {
        this.payTime = payTime;
    }

    public int getBackTime() {
        return backTime;
    }

    public void setBackTime(int backTime) {
        this.backTime = backTime;
    }

    public int getAddUserID() {
        return addUserID;
    }

    public void setAddUserID(int addUserID) {
        this.addUserID = addUserID;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getShTypeName() {
        return shTypeName;
    }

    public void setShTypeName(String shTypeName) {
        this.shTypeName = shTypeName;
    }

    public String getStatusTypeName() {
        return statusTypeName;
    }

    public void setStatusTypeName(String statusTypeName) {
        this.statusTypeName = statusTypeName;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getCustomTel() {
        return customTel;
    }

    public void setCustomTel(String customTel) {
        this.customTel = customTel;
    }

    public int getQualityIsGB() {
        return qualityIsGB;
    }

    public void setQualityIsGB(int qualityIsGB) {
        this.qualityIsGB = qualityIsGB;
    }

    public int getYbIsGB() {
        return ybIsGB;
    }

    public void setYbIsGB(int ybIsGB) {
        this.ybIsGB = ybIsGB;
    }

    public EnginerInfoBean getEnginerInfo() {
        return engineerInfo;
    }

    public void setEnginerInfo(EnginerInfoBean enginerInfo) {
        this.engineerInfo = enginerInfo;
    }

    public ArrayList<Uri> getFileInfo() {
        ArrayList<Uri> urls=new ArrayList<>();
        if(!TextUtils.isEmpty(fileUrls)){
            String[] files=fileUrls.split(",");
            if(files.length>0){
                for(int i=0;i<files.length;i++){
                    if(!TextUtils.isEmpty(files[i])){
                        urls.add(Uri.parse(ApiHelper.BASE_URL + files[i]));
                    }
                }
            }else{
                urls.add(Uri.parse(ApiHelper.BASE_URL + fileUrls));
            }
        }
        return urls;
    }

    public List<CustomersInfo> getCustomersInfo() {
        return customersInfo;
    }

    public void setCustomersInfo(List<CustomersInfo> customersInfo) {
        this.customersInfo = customersInfo;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getFileUrls() {
        return fileUrls;
    }

    public void setFileUrls(String fileUrls) {
        this.fileUrls = fileUrls;
    }

    public static class EnginerInfoBean {

        private int engineerID;
        private String userName;
        private String userAvatar;
        private String userPhone;
        private String note;

        public int getEngineerID() {
            return engineerID;
        }

        public void setEngineerID(int engineerID) {
            this.engineerID = engineerID;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserAvatar() {
            return userAvatar;
        }

        public void setUserAvatar(String userAvatar) {
            this.userAvatar = userAvatar;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

    }

    public static class CustomersInfo {

        /**
         * companyCustomerID : 3
         * customerID : 5
         * customerAvatar :
         * customerName : 张三
         * customerPhone : 15300000002
         * note :
         * addTime : 1578409852
         * followStatusID : 0
         * followStatusName :
         * customerLevelID : 0
         * levelName :
         * customerSourceID : 0
         * customerSourceName :
         */

        private int companyCustomerID;
        private int customerID;
        private String customerAvatar;
        private String customerName;
        private String customerPhone;
        private String note;
        private String addTime;
        private int followStatusID;
        private String followStatusName;
        private int customerLevelID;
        private String levelName;
        private int customerSourceID;
        private String customerSourceName;

        public int getCompanyCustomerID() {
            return companyCustomerID;
        }

        public void setCompanyCustomerID(int companyCustomerID) {
            this.companyCustomerID = companyCustomerID;
        }

        public int getCustomerID() {
            return customerID;
        }

        public void setCustomerID(int customerID) {
            this.customerID = customerID;
        }

        public String getCustomerAvatar() {
            return customerAvatar;
        }

        public void setCustomerAvatar(String customerAvatar) {
            this.customerAvatar = customerAvatar;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getCustomerPhone() {
            return customerPhone;
        }

        public void setCustomerPhone(String customerPhone) {
            this.customerPhone = customerPhone;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public int getFollowStatusID() {
            return followStatusID;
        }

        public void setFollowStatusID(int followStatusID) {
            this.followStatusID = followStatusID;
        }

        public String getFollowStatusName() {
            return followStatusName;
        }

        public void setFollowStatusName(String followStatusName) {
            this.followStatusName = followStatusName;
        }

        public int getCustomerLevelID() {
            return customerLevelID;
        }

        public void setCustomerLevelID(int customerLevelID) {
            this.customerLevelID = customerLevelID;
        }

        public String getLevelName() {
            return levelName;
        }

        public void setLevelName(String levelName) {
            this.levelName = levelName;
        }

        public int getCustomerSourceID() {
            return customerSourceID;
        }

        public void setCustomerSourceID(int customerSourceID) {
            this.customerSourceID = customerSourceID;
        }

        public String getCustomerSourceName() {
            return customerSourceName;
        }

        public void setCustomerSourceName(String customerSourceName) {
            this.customerSourceName = customerSourceName;
        }
    }
}
