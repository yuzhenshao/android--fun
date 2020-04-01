package com.mfzn.deepusesSer.model.msg;

import java.io.Serializable;
import java.util.List;

public class Message implements Serializable {


    /**
     * data : {"total":4,"per_page":1,"current_page":1,"last_page":4,"data":[{"id":488,"userID":900,"title":"项目消息","content":"Gavin提交给你一份新的售后单","isRead":0,"isDel":0,"type":1,"extra":{"type":1,"pageType":1,"orderNo":"yzs2019111800012","proID":1003,"enginerID":900},"pageType":1,"addTime":1574058619,"updateTime":0,"orderInfo":{"orderNo":"yzs2019111800012","shType":2,"proId":1003,"isUnderWarranty":"无","status":4,"createUserId":340,"contactName":"橘子","contactPhone":"15879633158","wishTime":"2019/11/18 16:00~17:00","content":"净水器快到期了","fileId":"","isAccept":1,"result":"受理","recommendContact":"","isCancel":0,"cancelNote":"","addTime":1574058420,"updateTime":1574058447,"updateUserId":340,"is_del":0,"orderNum":0,"isPay":1,"couponNumber":0,"sumPrice":0,"realPrice":0,"payTime":0,"backTime":0,"addUserID":340,"serviceType":"远程指导","serviceTime":"2019/11/18","shTypeName":"维护升级","statusTypeName":"服务中","u_name":"Gavin","detailAddress":"湖塘花园街301","companyID":206,"shouLiRen":{"id":340,"u_name":"Gavin"},"enginerInfo":{"enginerID":900,"u_name":"苹果","u_head":"","u_phone":"15012345678","note":"升级维护"}}}]}
     * notReadCount : 4
     */

    private DataBeanX data;
    private int notReadCount;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public int getNotReadCount() {
        return notReadCount;
    }

    public void setNotReadCount(int notReadCount) {
        this.notReadCount = notReadCount;
    }

    public static class DataBeanX implements Serializable{
        /**
         * total : 4
         * per_page : 1
         * current_page : 1
         * last_page : 4
         * data : [{"id":488,"userID":900,"title":"项目消息","content":"Gavin提交给你一份新的售后单","isRead":0,"isDel":0,"type":1,"extra":{"type":1,"pageType":1,"orderNo":"yzs2019111800012","proID":1003,"enginerID":900},"pageType":1,"addTime":1574058619,"updateTime":0,"orderInfo":{"orderNo":"yzs2019111800012","shType":2,"proId":1003,"isUnderWarranty":"无","status":4,"createUserId":340,"contactName":"橘子","contactPhone":"15879633158","wishTime":"2019/11/18 16:00~17:00","content":"净水器快到期了","fileId":"","isAccept":1,"result":"受理","recommendContact":"","isCancel":0,"cancelNote":"","addTime":1574058420,"updateTime":1574058447,"updateUserId":340,"is_del":0,"orderNum":0,"isPay":1,"couponNumber":0,"sumPrice":0,"realPrice":0,"payTime":0,"backTime":0,"addUserID":340,"serviceType":"远程指导","serviceTime":"2019/11/18","shTypeName":"维护升级","statusTypeName":"服务中","u_name":"Gavin","detailAddress":"湖塘花园街301","companyID":206,"shouLiRen":{"id":340,"u_name":"Gavin"},"enginerInfo":{"enginerID":900,"u_name":"苹果","u_head":"","u_phone":"15012345678","note":"升级维护"}}}]
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
             * id : 488
             * userID : 900
             * title : 项目消息
             * content : Gavin提交给你一份新的售后单
             * isRead : 0
             * isDel : 0
             * type : 1
             * extra : {"type":1,"pageType":1,"orderNo":"yzs2019111800012","proID":1003,"enginerID":900}
             * pageType : 1
             * addTime : 1574058619
             * updateTime : 0
             * orderInfo : {"orderNo":"yzs2019111800012","shType":2,"proId":1003,"isUnderWarranty":"无","status":4,"createUserId":340,"contactName":"橘子","contactPhone":"15879633158","wishTime":"2019/11/18 16:00~17:00","content":"净水器快到期了","fileId":"","isAccept":1,"result":"受理","recommendContact":"","isCancel":0,"cancelNote":"","addTime":1574058420,"updateTime":1574058447,"updateUserId":340,"is_del":0,"orderNum":0,"isPay":1,"couponNumber":0,"sumPrice":0,"realPrice":0,"payTime":0,"backTime":0,"addUserID":340,"serviceType":"远程指导","serviceTime":"2019/11/18","shTypeName":"维护升级","statusTypeName":"服务中","u_name":"Gavin","detailAddress":"湖塘花园街301","companyID":206,"shouLiRen":{"id":340,"u_name":"Gavin"},"enginerInfo":{"enginerID":900,"u_name":"苹果","u_head":"","u_phone":"15012345678","note":"升级维护"}}
             */

            private int id;
            private int userID;
            private String title;
            private String content;
            private int isRead;
            private int isDel;
            private int type;
            private ExtraBean extra;
            private int pageType;
            private int addTime;
            private int updateTime;
            private OrderInfoBean orderInfo;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserID() {
                return userID;
            }

            public void setUserID(int userID) {
                this.userID = userID;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getIsRead() {
                return isRead;
            }

            public void setIsRead(int isRead) {
                this.isRead = isRead;
            }

            public int getIsDel() {
                return isDel;
            }

            public void setIsDel(int isDel) {
                this.isDel = isDel;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public ExtraBean getExtra() {
                return extra;
            }

            public void setExtra(ExtraBean extra) {
                this.extra = extra;
            }

            public int getPageType() {
                return pageType;
            }

            public void setPageType(int pageType) {
                this.pageType = pageType;
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

            public OrderInfoBean getOrderInfo() {
                return orderInfo;
            }

            public void setOrderInfo(OrderInfoBean orderInfo) {
                this.orderInfo = orderInfo;
            }

            public static class ExtraBean implements Serializable{
                /**
                 * type : 1
                 * pageType : 1
                 * orderNo : yzs2019111800012
                 * proID : 1003
                 * enginerID : 900
                 */

                private int type;
                private int pageType;
                private String orderNo;
                private int proID;
                private int enginerID;

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public int getPageType() {
                    return pageType;
                }

                public void setPageType(int pageType) {
                    this.pageType = pageType;
                }

                public String getOrderNo() {
                    return orderNo;
                }

                public void setOrderNo(String orderNo) {
                    this.orderNo = orderNo;
                }

                public int getProID() {
                    return proID;
                }

                public void setProID(int proID) {
                    this.proID = proID;
                }

                public int getEnginerID() {
                    return enginerID;
                }

                public void setEnginerID(int enginerID) {
                    this.enginerID = enginerID;
                }
            }

            public static class OrderInfoBean implements Serializable{
                /**
                 * orderNo : yzs2019111800012
                 * shType : 2
                 * proId : 1003
                 * isUnderWarranty : 无
                 * status : 4
                 * createUserId : 340
                 * contactName : 橘子
                 * contactPhone : 15879633158
                 * wishTime : 2019/11/18 16:00~17:00
                 * content : 净水器快到期了
                 * fileId :
                 * isAccept : 1
                 * result : 受理
                 * recommendContact :
                 * isCancel : 0
                 * cancelNote :
                 * addTime : 1574058420
                 * updateTime : 1574058447
                 * updateUserId : 340
                 * is_del : 0
                 * orderNum : 0
                 * isPay : 1
                 * couponNumber : 0
                 * sumPrice : 0
                 * realPrice : 0
                 * payTime : 0
                 * backTime : 0
                 * addUserID : 340
                 * serviceType : 远程指导
                 * serviceTime : 2019/11/18
                 * shTypeName : 维护升级
                 * statusTypeName : 服务中
                 * u_name : Gavin
                 * detailAddress : 湖塘花园街301
                 * companyID : 206
                 * shouLiRen : {"id":340,"u_name":"Gavin"}
                 * enginerInfo : {"enginerID":900,"u_name":"苹果","u_head":"","u_phone":"15012345678","note":"升级维护"}
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
                private String serviceType;
                private String serviceTime;
                private String shTypeName;
                private String statusTypeName;
                private String u_name;
                private String detailAddress;
                private int companyID;
                private ShouLiRenBean shouLiRen;
                private EnginerInfoBean enginerInfo;

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

                public static class ShouLiRenBean implements Serializable{
                    /**
                     * id : 340
                     * u_name : Gavin
                     */

                    private int id;
                    private String u_name;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getU_name() {
                        return u_name;
                    }

                    public void setU_name(String u_name) {
                        this.u_name = u_name;
                    }
                }

                public static class EnginerInfoBean implements Serializable{
                    /**
                     * enginerID : 900
                     * u_name : 苹果
                     * u_head :
                     * u_phone : 15012345678
                     * note : 升级维护
                     */

                    private int enginerID;
                    private String u_name;
                    private String u_head;
                    private String u_phone;
                    private String note;

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
    }
}
