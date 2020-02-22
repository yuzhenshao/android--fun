package com.wevey.selector.dialog.bean;

import java.io.Serializable;
import java.util.List;

public class DetailsModel implements Serializable {

    /**
     * data_id : 279
     * customerUserID : 1069
     * customerName : 橙子
     * customerPhone : 15875862587
     * addtime : 1579484225
     * followStatusID : 1
     * followStatusName : 意向
     * customerLevelID : 5
     * levelName : 二星级
     * customerSourceID : 1
     * customerSourceName : 扫楼
     * note : 安通大厦
     * salesPersonUserID : 28
     * salesPersonUserName : 哈哈哈
     * pros : [{"pro_num":"KHJD2001005","pro_name":"空军建军节","contacter":"","contacter_phone":"","address":"","latitude":31.727388,"longitude":119.91585,"start_time":"","end_time":"","contractMoney":555,"advanceMoney":0,"pay_status":1,"pro_uid":328,"sales_people":"371","design_people":"","detailAddress":"人民西路3号附近安通大厦","customName":"橙子","customLevel":7,"customTel":"15875862587","contractFileUrl":"","mainNodeID":"","status":0,"is_del":0,"addtime":1579494313,"updateTime":1579494313,"updateUser":0,"currentStep":0,"currentNode":0,"mobanID":0,"isBuy":0,"isForever":0,"startPoint":1,"companyID":283,"areaName":"江苏省-常州市-武进区","customerIDs":"1069","qualityBegin":1579449600,"qualityEnd":1724083200,"qualityRing":1,"qualityTime":55,"ybEnd":"","afterSaleInDate":1,"afterSaleStatus":2,"otherCustomers":[{"customerUserID":1070,"customerName":"他现在","customerPhone":"89665512121"}],"data_id":1091,"data_en_id":"7SfQ145o4"}]
     */

    private int data_id;
    private int customerUserID;
    private String customerName;
    private String customerPhone;
    private String addtime;
    private int followStatusID;
    private String followStatusName;
    private int customerLevelID;
    private String levelName;
    private int customerSourceID;
    private String customerSourceName;
    private String note;
    private int salesPersonUserID;
    private String salesPersonUserName;
    private List<ProsBean> pros;

    public int getData_id() {
        return data_id;
    }

    public void setData_id(int data_id) {
        this.data_id = data_id;
    }

    public int getCustomerUserID() {
        return customerUserID;
    }

    public void setCustomerUserID(int customerUserID) {
        this.customerUserID = customerUserID;
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

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getSalesPersonUserID() {
        return salesPersonUserID;
    }

    public void setSalesPersonUserID(int salesPersonUserID) {
        this.salesPersonUserID = salesPersonUserID;
    }

    public String getSalesPersonUserName() {
        return salesPersonUserName;
    }

    public void setSalesPersonUserName(String salesPersonUserName) {
        this.salesPersonUserName = salesPersonUserName;
    }

    public List<ProsBean> getPros() {
        return pros;
    }

    public void setPros(List<ProsBean> pros) {
        this.pros = pros;
    }

    public static class ProsBean implements Serializable{
        /**
         * pro_num : KHJD2001005
         * pro_name : 空军建军节
         * contacter :
         * contacter_phone :
         * address :
         * latitude : 31.727388
         * longitude : 119.91585
         * start_time :
         * end_time :
         * contractMoney : 555
         * advanceMoney : 0
         * pay_status : 1
         * pro_uid : 328
         * sales_people : 371
         * design_people :
         * detailAddress : 人民西路3号附近安通大厦
         * customName : 橙子
         * customLevel : 7
         * customTel : 15875862587
         * contractFileUrl :
         * mainNodeID :
         * status : 0
         * is_del : 0
         * addtime : 1579494313
         * updateTime : 1579494313
         * updateUser : 0
         * currentStep : 0
         * currentNode : 0
         * mobanID : 0
         * isBuy : 0
         * isForever : 0
         * startPoint : 1
         * companyID : 283
         * areaName : 江苏省-常州市-武进区
         * customerIDs : 1069
         * qualityBegin : 1579449600
         * qualityEnd : 1724083200
         * qualityRing : 1
         * qualityTime : 55
         * ybEnd :
         * afterSaleInDate : 1
         * afterSaleStatus : 2
         * otherCustomers : [{"customerUserID":1070,"customerName":"他现在","customerPhone":"89665512121"}]
         * data_id : 1091
         * data_en_id : 7SfQ145o4
         */

        private String pro_num;
        private String pro_name;
        private String contacter;
        private String contacter_phone;
        private String address;
        private double latitude;
        private double longitude;
        private String start_time;
        private String end_time;
        private int contractMoney;
        private int advanceMoney;
        private int pay_status;
        private int pro_uid;
        private String sales_people;
        private String design_people;
        private String detailAddress;
        private String customName;
        private int customLevel;
        private String customTel;
        private String contractFileUrl;
        private String mainNodeID;
        private int status;
        private int is_del;
        private int addtime;
        private int updateTime;
        private int updateUser;
        private int currentStep;
        private int currentNode;
        private int mobanID;
        private int isBuy;
        private int isForever;
        private int startPoint;
        private int companyID;
        private String areaName;
        private String customerIDs;
        private String qualityBegin;
        private String qualityEnd;
        private String qualityRing;
        private String qualityTime;
        private String ybEnd;
        private int afterSaleInDate;
        private int afterSaleStatus;
        private int data_id;
        private String data_en_id;
        private List<OtherCustomersBean> otherCustomers;

        public String getPro_num() {
            return pro_num;
        }

        public void setPro_num(String pro_num) {
            this.pro_num = pro_num;
        }

        public String getPro_name() {
            return pro_name;
        }

        public void setPro_name(String pro_name) {
            this.pro_name = pro_name;
        }

        public String getContacter() {
            return contacter;
        }

        public void setContacter(String contacter) {
            this.contacter = contacter;
        }

        public String getContacter_phone() {
            return contacter_phone;
        }

        public void setContacter_phone(String contacter_phone) {
            this.contacter_phone = contacter_phone;
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

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public int getContractMoney() {
            return contractMoney;
        }

        public void setContractMoney(int contractMoney) {
            this.contractMoney = contractMoney;
        }

        public int getAdvanceMoney() {
            return advanceMoney;
        }

        public void setAdvanceMoney(int advanceMoney) {
            this.advanceMoney = advanceMoney;
        }

        public int getPay_status() {
            return pay_status;
        }

        public void setPay_status(int pay_status) {
            this.pay_status = pay_status;
        }

        public int getPro_uid() {
            return pro_uid;
        }

        public void setPro_uid(int pro_uid) {
            this.pro_uid = pro_uid;
        }

        public String getSales_people() {
            return sales_people;
        }

        public void setSales_people(String sales_people) {
            this.sales_people = sales_people;
        }

        public String getDesign_people() {
            return design_people;
        }

        public void setDesign_people(String design_people) {
            this.design_people = design_people;
        }

        public String getDetailAddress() {
            return detailAddress;
        }

        public void setDetailAddress(String detailAddress) {
            this.detailAddress = detailAddress;
        }

        public String getCustomName() {
            return customName;
        }

        public void setCustomName(String customName) {
            this.customName = customName;
        }

        public int getCustomLevel() {
            return customLevel;
        }

        public void setCustomLevel(int customLevel) {
            this.customLevel = customLevel;
        }

        public String getCustomTel() {
            return customTel;
        }

        public void setCustomTel(String customTel) {
            this.customTel = customTel;
        }

        public String getContractFileUrl() {
            return contractFileUrl;
        }

        public void setContractFileUrl(String contractFileUrl) {
            this.contractFileUrl = contractFileUrl;
        }

        public String getMainNodeID() {
            return mainNodeID;
        }

        public void setMainNodeID(String mainNodeID) {
            this.mainNodeID = mainNodeID;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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

        public int getCurrentStep() {
            return currentStep;
        }

        public void setCurrentStep(int currentStep) {
            this.currentStep = currentStep;
        }

        public int getCurrentNode() {
            return currentNode;
        }

        public void setCurrentNode(int currentNode) {
            this.currentNode = currentNode;
        }

        public int getMobanID() {
            return mobanID;
        }

        public void setMobanID(int mobanID) {
            this.mobanID = mobanID;
        }

        public int getIsBuy() {
            return isBuy;
        }

        public void setIsBuy(int isBuy) {
            this.isBuy = isBuy;
        }

        public int getIsForever() {
            return isForever;
        }

        public void setIsForever(int isForever) {
            this.isForever = isForever;
        }

        public int getStartPoint() {
            return startPoint;
        }

        public void setStartPoint(int startPoint) {
            this.startPoint = startPoint;
        }

        public int getCompanyID() {
            return companyID;
        }

        public void setCompanyID(int companyID) {
            this.companyID = companyID;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getCustomerIDs() {
            return customerIDs;
        }

        public void setCustomerIDs(String customerIDs) {
            this.customerIDs = customerIDs;
        }

        public String getQualityBegin() {
            return qualityBegin;
        }

        public void setQualityBegin(String qualityBegin) {
            this.qualityBegin = qualityBegin;
        }

        public String getQualityEnd() {
            return qualityEnd;
        }

        public void setQualityEnd(String qualityEnd) {
            this.qualityEnd = qualityEnd;
        }

        public String getQualityRing() {
            return qualityRing;
        }

        public void setQualityRing(String qualityRing) {
            this.qualityRing = qualityRing;
        }

        public String getQualityTime() {
            return qualityTime;
        }

        public void setQualityTime(String qualityTime) {
            this.qualityTime = qualityTime;
        }

        public String getYbEnd() {
            return ybEnd;
        }

        public void setYbEnd(String ybEnd) {
            this.ybEnd = ybEnd;
        }

        public int getAfterSaleInDate() {
            return afterSaleInDate;
        }

        public void setAfterSaleInDate(int afterSaleInDate) {
            this.afterSaleInDate = afterSaleInDate;
        }

        public int getAfterSaleStatus() {
            return afterSaleStatus;
        }

        public void setAfterSaleStatus(int afterSaleStatus) {
            this.afterSaleStatus = afterSaleStatus;
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

        public List<OtherCustomersBean> getOtherCustomers() {
            return otherCustomers;
        }

        public void setOtherCustomers(List<OtherCustomersBean> otherCustomers) {
            this.otherCustomers = otherCustomers;
        }

        public static class OtherCustomersBean implements Serializable{
            /**
             * customerUserID : 1070
             * customerName : 他现在
             * customerPhone : 89665512121
             */

            private int customerUserID;
            private String customerName;
            private String customerPhone;

            public int getCustomerUserID() {
                return customerUserID;
            }

            public void setCustomerUserID(int customerUserID) {
                this.customerUserID = customerUserID;
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
        }
    }
}
