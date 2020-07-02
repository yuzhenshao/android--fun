package com.mfzn.deepuses.bean.response.shop;

public class ShopDataResponse {

    /**
     * shopData : {"shopID":1,"shopName":"总门店"}
     * salesData : {"monthSumSalesMoney":0,"yearSumSalesMoney":30000}
     * storeData : {"storeCount":2,"storeSumStockNum":1690}
     * capitalData : {"moneyAccountCount":2,"sumIncome":34500,"sumOutcome":79800,"sumShouldGathering":70000,"sumShouldPay":80000}
     * customerData : {"sumCustomerCount":4,"cooperativeCustomersCount":1,"normalCustomersCount":3}
     */

    private ShopDataBean shopData;
    private SalesDataBean salesData;
    private StoreDataBean storeData;
    private CapitalDataBean capitalData;
    private CustomerDataBean customerData;

    public ShopDataBean getShopData() {
        return shopData;
    }

    public void setShopData(ShopDataBean shopData) {
        this.shopData = shopData;
    }

    public SalesDataBean getSalesData() {
        return salesData;
    }

    public void setSalesData(SalesDataBean salesData) {
        this.salesData = salesData;
    }

    public StoreDataBean getStoreData() {
        return storeData;
    }

    public void setStoreData(StoreDataBean storeData) {
        this.storeData = storeData;
    }

    public CapitalDataBean getCapitalData() {
        return capitalData;
    }

    public void setCapitalData(CapitalDataBean capitalData) {
        this.capitalData = capitalData;
    }

    public CustomerDataBean getCustomerData() {
        return customerData;
    }

    public void setCustomerData(CustomerDataBean customerData) {
        this.customerData = customerData;
    }

    public static class ShopDataBean {
        /**
         * shopID : 1
         * shopName : 总门店
         */

        private String shopID;
        private String shopName;

        public String getShopID() {
            return shopID;
        }

        public void setShopID(String shopID) {
            this.shopID = shopID;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }
    }

    public static class SalesDataBean {
        /**
         * monthSumSalesMoney : 0
         * yearSumSalesMoney : 30000
         */

        private String monthSumSalesMoney;
        private String yearSumSalesMoney;

        public String getMonthSumSalesMoney() {
            return monthSumSalesMoney;
        }

        public void setMonthSumSalesMoney(String monthSumSalesMoney) {
            this.monthSumSalesMoney = monthSumSalesMoney;
        }

        public String getYearSumSalesMoney() {
            return yearSumSalesMoney;
        }

        public void setYearSumSalesMoney(String yearSumSalesMoney) {
            this.yearSumSalesMoney = yearSumSalesMoney;
        }
    }

    public static class StoreDataBean {
        /**
         * storeCount : 2
         * storeSumStockNum : 1690
         */

        private String storeCount;
        private String storeSumStockNum;

        public String getStoreCount() {
            return storeCount;
        }

        public void setStoreCount(String storeCount) {
            this.storeCount = storeCount;
        }

        public String getStoreSumStockNum() {
            return storeSumStockNum;
        }

        public void setStoreSumStockNum(String storeSumStockNum) {
            this.storeSumStockNum = storeSumStockNum;
        }
    }

    public static class CapitalDataBean {
        /**
         * moneyAccountCount : 2
         * sumIncome : 34500
         * sumOutcome : 79800
         * sumShouldGathering : 70000
         * sumShouldPay : 80000
         */

        private String moneyAccountCount;
        private String sumIncome;
        private String sumOutcome;
        private String sumShouldGathering;
        private String sumShouldPay;

        public String getMoneyAccountCount() {
            return moneyAccountCount;
        }

        public void setMoneyAccountCount(String moneyAccountCount) {
            this.moneyAccountCount = moneyAccountCount;
        }

        public String getSumIncome() {
            return sumIncome;
        }

        public void setSumIncome(String sumIncome) {
            this.sumIncome = sumIncome;
        }

        public String getSumOutcome() {
            return sumOutcome;
        }

        public void setSumOutcome(String sumOutcome) {
            this.sumOutcome = sumOutcome;
        }

        public String getSumShouldGathering() {
            return sumShouldGathering;
        }

        public void setSumShouldGathering(String sumShouldGathering) {
            this.sumShouldGathering = sumShouldGathering;
        }

        public String getSumShouldPay() {
            return sumShouldPay;
        }

        public void setSumShouldPay(String sumShouldPay) {
            this.sumShouldPay = sumShouldPay;
        }
    }

    public static class CustomerDataBean {
        /**
         * sumCustomerCount : 4
         * cooperativeCustomersCount : 1
         * normalCustomersCount : 3
         */

        private String sumCustomerCount;
        private String cooperativeCustomersCount;
        private String normalCustomersCount;

        public String getSumCustomerCount() {
            return sumCustomerCount;
        }

        public void setSumCustomerCount(String sumCustomerCount) {
            this.sumCustomerCount = sumCustomerCount;
        }

        public String getCooperativeCustomersCount() {
            return cooperativeCustomersCount;
        }

        public void setCooperativeCustomersCount(String cooperativeCustomersCount) {
            this.cooperativeCustomersCount = cooperativeCustomersCount;
        }

        public String getNormalCustomersCount() {
            return normalCustomersCount;
        }

        public void setNormalCustomersCount(String normalCustomersCount) {
            this.normalCustomersCount = normalCustomersCount;
        }
    }
}
