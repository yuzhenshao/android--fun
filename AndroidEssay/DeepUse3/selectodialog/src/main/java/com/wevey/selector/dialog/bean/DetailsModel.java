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


        private String proID;
        private String proName;
        private double latitude;
        private double longitude;
        private String areaName;
        private String detailAddress;

        private List<OtherCustomersBean> otherCustomers;


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



        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public List<OtherCustomersBean> getOtherCustomers() {
            return otherCustomers;
        }

        public void setOtherCustomers(List<OtherCustomersBean> otherCustomers) {
            this.otherCustomers = otherCustomers;
        }

        public String getProName() {
            return proName;
        }

        public void setProName(String proName) {
            this.proName = proName;
        }

        public String getDetailAddress() {
            return detailAddress;
        }

        public void setDetailAddress(String detailAddress) {
            this.detailAddress = detailAddress;
        }

        public String getProID() {
            return proID;
        }

        public void setProID(String proID) {
            this.proID = proID;
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
