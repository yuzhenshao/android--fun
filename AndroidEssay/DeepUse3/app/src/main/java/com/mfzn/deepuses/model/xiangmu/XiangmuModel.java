package com.mfzn.deepuses.model.xiangmu;

import java.io.Serializable;
import java.util.List;

public class XiangmuModel implements Serializable {

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
        private String proName;
        private String contacter;
        private String contacterPhone;
        private String address;
        private double latitude;
        private double longitude;
        private int contractMoney;
        private String detailAddress;
        private String customName;
        private int customLevel;
        private String customTel;
        private int status;
        private int addtime;
        private int updateTime;
        private int companyID;
        private String areaName;
        private String levelName;
        private String qualityBegin;
        private String qualityEnd;
        private String qualityRing;
        private String qualityTime;
        private SalesPeopleBean proSalesPersonInfo;
        private int qualityIsGB;
        private int ybIsGB;
        private int afterSaleInDate;
        private int afterSaleStatus;
        private int data_id;
        private String data_en_id;
        private List<CustomersBean> customersInfo;

        public String getProName() {
            return proName;
        }

        public void setProName(String proName) {
            this.proName = proName;
        }

        public String getContacter() {
            return contacter;
        }

        public void setContacter(String contacter) {
            this.contacter = contacter;
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

        public int getContractMoney() {
            return contractMoney;
        }

        public void setContractMoney(int contractMoney) {
            this.contractMoney = contractMoney;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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

        public SalesPeopleBean getProSalesPersonInfo() {
            return proSalesPersonInfo;
        }

        public void setProSalesPersonInfo(SalesPeopleBean proSalesPersonInfo) {
            this.proSalesPersonInfo = proSalesPersonInfo;
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

        public List<CustomersBean> getCustomersInfo() {
            return customersInfo;
        }

        public void setCustomersInfo(List<CustomersBean> customersInfo) {
            this.customersInfo = customersInfo;
        }

        public String getContacterPhone() {
            return contacterPhone;
        }

        public void setContacterPhone(String contacterPhone) {
            this.contacterPhone = contacterPhone;
        }

        public static class SalesPeopleBean implements Serializable {
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

        public static class CustomersBean implements Serializable {

            private String customerID;
            private String customerName;

            public String getCustomerID() {
                return customerID;
            }

            public void setCustomerID(String customerID) {
                this.customerID = customerID;
            }

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }
        }
    }
}
