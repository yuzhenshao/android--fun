package com.mfzn.deepuses.model.xiangmu;

import java.io.Serializable;
import java.util.List;

public class WorkorderListModel implements Serializable {

    /**
     * total : 28
     * per_page : 10
     * current_page : 1
     * last_page : 3
     * data : [{"orderNo":"yzs2019112200000","shType":1,"proId":1008,"isUnderWarranty":"无","status":3,"createUserId":834,"contactName":"123","contactPhone":"13312345678","wishTime":"2019/11/22 8:00-9:00","content":"456","fileId":"","isAccept":1,"result":"受理","recommendContact":"","isCancel":0,"cancelNote":"","addTime":1574382695,"updateTime":1574501817,"updateUserId":340,"is_del":0,"delNote":"","orderNum":0,"isPay":1,"couponNumber":0,"sumPrice":0,"realPrice":0,"payTime":0,"backTime":0,"addUserID":340,"serviceType":"","serviceTime":"","couponID":0,"shTypeName":"故障报修","statusTypeName":"待接单","u_name":"Gavin","detailAddress":"人民西路3号瑞森工厂集成店","companyID":206,"shouLiRen":{"u_name":"许海豹","data_id":340,"data_en_id":"phv1QBUP5"},"enginerInfo":{"shJobID":39,"enginerID":900,"u_name":"苹果","u_head":"/uploads/u_head/900/20191119/5e19cdda7989b321c7f4516e944146a5.jpg","u_phone":"15012345678","note":"Ghh "},"afterSaleInDate":1}]
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

    public static class DataBean implements Serializable {
        /**
         * orderNo : yzs2019112200000
         * shType : 1
         * proId : 1008
         * isUnderWarranty : 无
         * status : 3
         * createUserId : 834
         * contactName : 123
         * contactPhone : 13312345678
         * wishTime : 2019/11/22 8:00-9:00
         * content : 456
         * fileId :
         * isAccept : 1
         * result : 受理
         * recommendContact :
         * isCancel : 0
         * cancelNote :
         * addTime : 1574382695
         * updateTime : 1574501817
         * updateUserId : 340
         * is_del : 0
         * delNote :
         * orderNum : 0
         * isPay : 1
         * couponNumber : 0
         * sumPrice : 0
         * realPrice : 0
         * payTime : 0
         * backTime : 0
         * addUserID : 340
         * serviceType :
         * serviceTime :
         * couponID : 0
         * shTypeName : 故障报修
         * statusTypeName : 待接单
         * u_name : Gavin
         * detailAddress : 人民西路3号瑞森工厂集成店
         * companyID : 206
         * shouLiRen : {"u_name":"许海豹","data_id":340,"data_en_id":"phv1QBUP5"}
         * enginerInfo : {"shJobID":39,"enginerID":900,"u_name":"苹果","u_head":"/uploads/u_head/900/20191119/5e19cdda7989b321c7f4516e944146a5.jpg","u_phone":"15012345678","note":"Ghh "}
         * afterSaleInDate : 1
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
        private String delNote;
        private int orderNum;
        private int isPay;
        private int couponNumber;
        private int sumPrice;
        private int realPrice;
        private int payTime;
        private int backTime;
        private int addUserID;
        private String serviceType;
        private String serviceTime;
        private int couponID;
        private String shTypeName;
        private String statusTypeName;
        private String u_name;
        private String detailAddress;
        private int companyID;
        private ShouLiRenBean shouLiRen;
        private EnginerInfoBean enginerInfo;
        private int afterSaleInDate;

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

        public int getCouponID() {
            return couponID;
        }

        public void setCouponID(int couponID) {
            this.couponID = couponID;
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

        public String getU_name() {
            return u_name;
        }

        public void setU_name(String u_name) {
            this.u_name = u_name;
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

        public ShouLiRenBean getShouLiRen() {
            return shouLiRen;
        }

        public void setShouLiRen(ShouLiRenBean shouLiRen) {
            this.shouLiRen = shouLiRen;
        }

        public EnginerInfoBean getEnginerInfo() {
            return enginerInfo;
        }

        public void setEnginerInfo(EnginerInfoBean enginerInfo) {
            this.enginerInfo = enginerInfo;
        }

        public int getAfterSaleInDate() {
            return afterSaleInDate;
        }

        public void setAfterSaleInDate(int afterSaleInDate) {
            this.afterSaleInDate = afterSaleInDate;
        }

        public static class ShouLiRenBean implements Serializable {
            /**
             * u_name : 许海豹
             * data_id : 340
             * data_en_id : phv1QBUP5
             */

            private String u_name;
            private int data_id;
            private String data_en_id;

            public String getU_name() {
                return u_name;
            }

            public void setU_name(String u_name) {
                this.u_name = u_name;
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

        public static class EnginerInfoBean implements Serializable {
            /**
             * shJobID : 39
             * enginerID : 900
             * u_name : 苹果
             * u_head : /uploads/u_head/900/20191119/5e19cdda7989b321c7f4516e944146a5.jpg
             * u_phone : 15012345678
             * note : Ghh
             */

            private int shJobID;
            private int enginerID;
            private String u_name;
            private String u_head;
            private String u_phone;
            private String note;

            public int getShJobID() {
                return shJobID;
            }

            public void setShJobID(int shJobID) {
                this.shJobID = shJobID;
            }

            public int getEnginerID() {
                return enginerID;
            }

            public void setEnginerID(int enginerID) {
                this.enginerID = enginerID;
            }

            public String getU_name() {
                return u_name;
            }

            public void setU_name(String u_name) {
                this.u_name = u_name;
            }

            public String getU_head() {
                return u_head;
            }

            public void setU_head(String u_head) {
                this.u_head = u_head;
            }

            public String getU_phone() {
                return u_phone;
            }

            public void setU_phone(String u_phone) {
                this.u_phone = u_phone;
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
