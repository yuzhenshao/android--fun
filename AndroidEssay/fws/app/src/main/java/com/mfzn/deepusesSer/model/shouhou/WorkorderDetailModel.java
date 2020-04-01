package com.mfzn.deepusesSer.model.shouhou;

import java.util.List;

public class WorkorderDetailModel {

    /**
     * orderNo : yzs2019110600002
     * shType : 1
     * proId : 960
     * isUnderWarranty : 无
     * status : 3
     * createUserId : 844
     * contactName : 测试3
     * contactPhone : 15512345678
     * wishTime : 2019/11/06 11:09-2019/11/06 11:09
     * content : 测试文本3
     * fileId : 634,635
     * isAccept : 1
     * result : 受理
     * recommendContact :
     * isCancel : 0
     * cancelNote :
     * addTime : 1573009732
     * updateTime : 1573263573
     * updateUserId : 28
     * is_del : 0
     * orderNum : 0
     * isPay : 1
     * couponNumber : 0
     * sumPrice : 0
     * realPrice : 0
     * payTime : 0
     * backTime : 0
     * addUserID : 28
     * address :
     * latitude : 31.728059
     * longitude : 119.915861
     * pro_name : 新项目10.29
     * customName : 邵总
     * customTel : 15156855558
     * fileInfo : [{"imgUrl":"/uploads/960/解决方案/20191106110851-wx4310ffe4c9f7f6be.o6zAJswA4YJt_BmV6MObR3eJdK2o.YbyWJtYDAJF020c32cb68d02e81cfb3f97796bb22bc4","imgNote":"","fileUrl":"","fileNote":"","otherUrl":"","otherNote":"","is_del":0,"addtime":1573009731,"updateTime":0,"updateUser":0,"data_id":634,"data_en_id":"pBBavXgUP"},{"imgUrl":"/uploads/960/解决方案/20191106110852-wx4310ffe4c9f7f6be.o6zAJswA4YJt_BmV6MObR3eJdK2o.KMOGCeJYIGThf44fe54689b7cc60e2bb321038eab6e7","imgNote":"","fileUrl":"","fileNote":"","otherUrl":"","otherNote":"","is_del":0,"addtime":1573009732,"updateTime":0,"updateUser":0,"data_id":635,"data_en_id":"phf1BfgUV"}]
     */

    private String orderNo;
    private int shType;
    private int proId;
    private String isUnderWarranty;
    private int status;
    private int createUserId;
    private String contactName;
    private String contactPhone;
    private String wishTime;
    private String content;
    private String fileId;
    private int isAccept;
    private String result;
    private String recommendContact;
    private int isCancel;
    private String cancelNote;
    private int addTime;
    private int updateTime;
    private int updateUserId;
    private int is_del;
    private int orderNum;
    private int isPay;
    private int couponNumber;
    private int sumPrice;
    private int realPrice;
    private int payTime;
    private int backTime;
    private int addUserID;
    private String address;
    private double latitude;
    private double longitude;
    private String pro_name;
    private String customName;
    private String customTel;
    private List<FileInfoBean> fileInfo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getShType() {
        return shType;
    }

    public void setShType(int shType) {
        this.shType = shType;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
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
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
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

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
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

    public int getIs_del() {
        return is_del;
    }

    public void setIs_del(int is_del) {
        this.is_del = is_del;
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

    public int getCouponNumber() {
        return couponNumber;
    }

    public void setCouponNumber(int couponNumber) {
        this.couponNumber = couponNumber;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
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

    public List<FileInfoBean> getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(List<FileInfoBean> fileInfo) {
        this.fileInfo = fileInfo;
    }

    public static class FileInfoBean {
        /**
         * imgUrl : /uploads/960/解决方案/20191106110851-wx4310ffe4c9f7f6be.o6zAJswA4YJt_BmV6MObR3eJdK2o.YbyWJtYDAJF020c32cb68d02e81cfb3f97796bb22bc4
         * imgNote :
         * fileUrl :
         * fileNote :
         * otherUrl :
         * otherNote :
         * is_del : 0
         * addtime : 1573009731
         * updateTime : 0
         * updateUser : 0
         * data_id : 634
         * data_en_id : pBBavXgUP
         */

        private String imgUrl;
        private String imgNote;
        private String fileUrl;
        private String fileNote;
        private String otherUrl;
        private String otherNote;
        private int is_del;
        private int addtime;
        private int updateTime;
        private int updateUser;
        private int data_id;
        private String data_en_id;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getImgNote() {
            return imgNote;
        }

        public void setImgNote(String imgNote) {
            this.imgNote = imgNote;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        public String getFileNote() {
            return fileNote;
        }

        public void setFileNote(String fileNote) {
            this.fileNote = fileNote;
        }

        public String getOtherUrl() {
            return otherUrl;
        }

        public void setOtherUrl(String otherUrl) {
            this.otherUrl = otherUrl;
        }

        public String getOtherNote() {
            return otherNote;
        }

        public void setOtherNote(String otherNote) {
            this.otherNote = otherNote;
        }

        public int getIs_del() {
            return is_del;
        }

        public void setIs_del(int is_del) {
            this.is_del = is_del;
        }

        public int getAddtime() {
            return addtime;
        }

        public void setAddtime(int addtime) {
            this.addtime = addtime;
        }

        public int getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(int updateTime) {
            this.updateTime = updateTime;
        }

        public int getUpdateUser() {
            return updateUser;
        }

        public void setUpdateUser(int updateUser) {
            this.updateUser = updateUser;
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
    }
}