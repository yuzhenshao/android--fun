package com.mfzn.deepuses.model.xiangmu;

import java.io.Serializable;
import java.util.List;

public class WorkorderListModel implements Serializable {
    /**
     * total : 2
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"orderNo":"yzs2020011000000","asType":1,"proID":1,"isUnderWarranty":"无","status":4,"createUserID":1,"contactName":"阿刁","contactPhone":"15300000001","wishTime":1578980710,"content":"来看一下","fileUrls":"","isAccept":1,"result":"哈哈哈","recommendContact":"1","isCancel":0,"cancelNote":"","addTime":1578624314,"updateTime":1578625050,"updateUserID":1,"isDel":0,"delNote":"","orderNum":0,"isPay":1,"sumPrice":"0.00","realPrice":"0.00","couponID":0,"payTime":0,"backTime":0,"addUserID":1,"serviceType":1,"serviceTime":1578927604,"shTypeName":"故障报修","statusTypeName":"服务中","userName":"ewen","detailAddress":"安通大厦","companyID":2,"receiverInfo":{"userName":"ewen","data_id":1,"data_en_id":"Fh1SQdvX4"},"engineerInfo":{"asJobID":1,"engineerID":2,"userName":"augus","userAvatar":"","userPhone":"15300000000","note":"你去做一下"}},{"orderNo":"yzs2020010900000","asType":1,"proID":1,"isUnderWarranty":"无","status":7,"createUserID":1,"contactName":"阿刁","contactPhone":"15300000002","wishTime":1578980710,"content":"编辑一下","fileUrls":"","isAccept":1,"result":"哈哈哈","recommendContact":"1,2","isCancel":1,"cancelNote":"不想做了","addTime":1578580637,"updateTime":1578623646,"updateUserID":1,"isDel":0,"delNote":"","orderNum":0,"isPay":1,"sumPrice":"0.00","realPrice":"0.00","couponID":0,"payTime":0,"backTime":0,"addUserID":1,"serviceType":0,"serviceTime":0,"shTypeName":"故障报修","statusTypeName":"已取消","userName":"ewen","detailAddress":"安通大厦","companyID":2,"receiverInfo":{"userName":"ewen","data_id":1,"data_en_id":"Fh1SQdvX4"},"engineerInfo":{}}]
     */

    private int total;
    private int per_page;
    private int current_page;
    private int last_page;
    private List<DataBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * orderNo : yzs2020011000000
         * asType : 1
         * proID : 1
         * isUnderWarranty : 无
         * status : 4
         * createUserID : 1
         * contactName : 阿刁
         * contactPhone : 15300000001
         * wishTime : 1578980710
         * content : 来看一下
         * fileUrls :
         * isAccept : 1
         * result : 哈哈哈
         * recommendContact : 1
         * isCancel : 0
         * cancelNote :
         * addTime : 1578624314
         * updateTime : 1578625050
         * updateUserID : 1
         * isDel : 0
         * delNote :
         * orderNum : 0
         * isPay : 1
         * sumPrice : 0.00
         * realPrice : 0.00
         * couponID : 0
         * payTime : 0
         * backTime : 0
         * addUserID : 1
         * serviceType : 1
         * serviceTime : 1578927604
         * shTypeName : 故障报修
         * statusTypeName : 服务中
         * userName : ewen
         * detailAddress : 安通大厦
         * companyID : 2
         * receiverInfo : {"userName":"ewen","data_id":1,"data_en_id":"Fh1SQdvX4"}
         * engineerInfo : {"asJobID":1,"engineerID":2,"userName":"augus","userAvatar":"","userPhone":"15300000000","note":"你去做一下"}
         */

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
        private String fileUrls;
        private int isAccept;
        private String result;
        private String recommendContact;
        private int isCancel;
        private String cancelNote;
        private int addTime;
        private int updateTime;
        private int updateUserID;
        private int isDel;
        private String delNote;
        private int orderNum;
        private int isPay;
        private String sumPrice;
        private String realPrice;
        private int couponID;
        private int payTime;
        private int backTime;
        private int addUserID;
        private int serviceType;
        private int serviceTime;
        private String shTypeName;
        private String statusTypeName;
        private String userName;
        private String detailAddress;
        private int companyID;
        private ReceiverInfoBean receiverInfo;
        private EngineerInfoBean engineerInfo;
        private int afterSaleInDate;

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

        public int getProID() {
            return proID;
        }

        public void setProID(int proID) {
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

        public int getCreateUserID() {
            return createUserID;
        }

        public void setCreateUserID(int createUserID) {
            this.createUserID = createUserID;
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

        public String getFileUrls() {
            return fileUrls;
        }

        public void setFileUrls(String fileUrls) {
            this.fileUrls = fileUrls;
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

        public int getUpdateUserID() {
            return updateUserID;
        }

        public void setUpdateUserID(int updateUserID) {
            this.updateUserID = updateUserID;
        }

        public int getIsDel() {
            return isDel;
        }

        public void setIsDel(int isDel) {
            this.isDel = isDel;
        }

        public String getDelNote() {
            return delNote;
        }

        public void setDelNote(String delNote) {
            this.delNote = delNote;
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

        public String getSumPrice() {
            return sumPrice;
        }

        public void setSumPrice(String sumPrice) {
            this.sumPrice = sumPrice;
        }

        public String getRealPrice() {
            return realPrice;
        }

        public void setRealPrice(String realPrice) {
            this.realPrice = realPrice;
        }

        public int getCouponID() {
            return couponID;
        }

        public void setCouponID(int couponID) {
            this.couponID = couponID;
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

        public int getServiceType() {
            return serviceType;
        }

        public void setServiceType(int serviceType) {
            this.serviceType = serviceType;
        }

        public int getServiceTime() {
            return serviceTime;
        }

        public void setServiceTime(int serviceTime) {
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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
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

        public ReceiverInfoBean getReceiverInfo() {
            return receiverInfo;
        }

        public void setReceiverInfo(ReceiverInfoBean receiverInfo) {
            this.receiverInfo = receiverInfo;
        }

        public EngineerInfoBean getEngineerInfo() {
            return engineerInfo;
        }

        public void setEngineerInfo(EngineerInfoBean engineerInfo) {
            this.engineerInfo = engineerInfo;
        }

        public int getAfterSaleInDate() {
            return afterSaleInDate;
        }

        public void setAfterSaleInDate(int afterSaleInDate) {
            this.afterSaleInDate = afterSaleInDate;
        }

        public static class ReceiverInfoBean implements Serializable{
            /**
             * userName : ewen
             * data_id : 1
             * data_en_id : Fh1SQdvX4
             */

            private String userName;
            private int data_id;
            private String data_en_id;

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
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

        public static class EngineerInfoBean implements Serializable{
            /**
             * asJobID : 1
             * engineerID : 2
             * userName : augus
             * userAvatar :
             * userPhone : 15300000000
             * note : 你去做一下
             */

            private int asJobID;
            private int engineerID;
            private String userName;
            private String userAvatar;
            private String userPhone;
            private String note;

            public int getAsJobID() {
                return asJobID;
            }

            public void setAsJobID(int asJobID) {
                this.asJobID = asJobID;
            }

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
    }
}
