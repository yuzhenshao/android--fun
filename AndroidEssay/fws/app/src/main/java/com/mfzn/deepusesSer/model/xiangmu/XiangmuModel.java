package com.mfzn.deepusesSer.model.xiangmu;

import java.io.Serializable;
import java.util.List;

public class XiangmuModel implements Serializable {

    /**
     * total : 2
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"pro_num":"YUHJ1911001","pro_name":"好反馈反馈","contacter":"","contacter_phone":"","address":"","latitude":31.728059,"longitude":119.915861,"start_time":"","end_time":"","contractMoney":0,"advanceMoney":0,"pay_status":1,"pro_uid":847,"sales_people":"0","design_people":"","detailAddress":"湖塘镇人民西路3号安通大厦2号楼常州麦麸智能工程有限公司","customName":"附近","customLevel":0,"customTel":"110","contractFileUrl":"","mainNodeID":"","status":0,"is_del":0,"addtime":1572600206,"updateTime":0,"updateUser":0,"currentStep":0,"currentNode":0,"mobanID":0,"isBuy":0,"isForever":0,"startPoint":1,"companyID":171,"areaName":"江苏省-常州市-武进区","levelName":"","salesID":"","salesName":"","custom":{"levelName":"","customLevel":0},"salesPeople":{"salesID":"","salesName":""},"data_id":967,"data_en_id":"pXvQ1Xoge"}]
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
         * pro_num : YUHJ1911001
         * pro_name : 好反馈反馈
         * contacter :
         * contacter_phone :
         * address :
         * latitude : 31.728059
         * longitude : 119.915861
         * start_time :
         * end_time :
         * contractMoney : 0
         * advanceMoney : 0
         * pay_status : 1
         * pro_uid : 847
         * sales_people : 0
         * design_people :
         * detailAddress : 湖塘镇人民西路3号安通大厦2号楼常州麦麸智能工程有限公司
         * customName : 附近
         * customLevel : 0
         * customTel : 110
         * contractFileUrl :
         * mainNodeID :
         * status : 0
         * is_del : 0
         * addtime : 1572600206
         * updateTime : 0
         * updateUser : 0
         * currentStep : 0
         * currentNode : 0
         * mobanID : 0
         * isBuy : 0
         * isForever : 0
         * startPoint : 1
         * companyID : 171
         * areaName : 江苏省-常州市-武进区
         * levelName :
         * salesID :
         * salesName :
         * custom : {"levelName":"","customLevel":0}
         * salesPeople : {"salesID":"","salesName":""}
         * data_id : 967
         * data_en_id : pXvQ1Xoge
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
        private String levelName;
        private String salesID;
        private String salesName;
        private CustomBean custom;
        private SalesPeopleBean salesPeople;
        private int data_id;
        private String data_en_id;

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

        public String getLevelName() {
            return levelName;
        }

        public void setLevelName(String levelName) {
            this.levelName = levelName;
        }

        public String getSalesID() {
            return salesID;
        }

        public void setSalesID(String salesID) {
            this.salesID = salesID;
        }

        public String getSalesName() {
            return salesName;
        }

        public void setSalesName(String salesName) {
            this.salesName = salesName;
        }

        public CustomBean getCustom() {
            return custom;
        }

        public void setCustom(CustomBean custom) {
            this.custom = custom;
        }

        public SalesPeopleBean getSalesPeople() {
            return salesPeople;
        }

        public void setSalesPeople(SalesPeopleBean salesPeople) {
            this.salesPeople = salesPeople;
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

        public static class CustomBean implements Serializable {
            /**
             * levelName :
             * customLevel : 0
             */

            private String levelName;
            private int customLevel;

            public String getLevelName() {
                return levelName;
            }

            public void setLevelName(String levelName) {
                this.levelName = levelName;
            }

            public int getCustomLevel() {
                return customLevel;
            }

            public void setCustomLevel(int customLevel) {
                this.customLevel = customLevel;
            }
        }

        public static class SalesPeopleBean implements Serializable {
            /**
             * salesID :
             * salesName :
             */

            private String salesID;
            private String salesName;

            public String getSalesID() {
                return salesID;
            }

            public void setSalesID(String salesID) {
                this.salesID = salesID;
            }

            public String getSalesName() {
                return salesName;
            }

            public void setSalesName(String salesName) {
                this.salesName = salesName;
            }
        }
    }
}
