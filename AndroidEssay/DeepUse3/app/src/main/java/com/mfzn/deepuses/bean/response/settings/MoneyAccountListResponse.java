package com.mfzn.deepuses.bean.response.settings;

import java.util.List;

public class MoneyAccountListResponse {

    /**
     * statisticsData : {"sumMoney":19500,"income":12300,"outcome":69300,"borrowIn":0,"borrowOut":0}
     * list : [{"accountID":1,"accountNum":"RWIJ_JSZH_000001","accountName":"账户","accountType":2,"createMoney":"100.00","nowMoney":"9000.00","addTime":1583804923,"shopID":1,"shopNum":"RWIJ_MD_000001","shopName":"总门店"},{"accountID":2,"accountNum":"RWIJ_JSZH_000002","accountName":"账户2","accountType":2,"createMoney":"10000.00","nowMoney":"10500.00","addTime":1587136268,"shopID":1,"shopNum":"RWIJ_MD_000001","shopName":"总门店"}]
     */

    public static final int ACCOUNT_CASH=1;
    public static final int ACCOUNT_ACCOUNT=2;
    public static final int ACCOUNT_WEIXIN=3;
    public static final int ACCOUNT_ALI=4;


    private StatisticsDataBean statisticsData;
    private List<AccountResponse> list;

    public StatisticsDataBean getStatisticsData() {
        return statisticsData;
    }

    public void setStatisticsData(StatisticsDataBean statisticsData) {
        this.statisticsData = statisticsData;
    }

    public List<AccountResponse> getList() {
        return list;
    }

    public void setList(List<AccountResponse> list) {
        this.list = list;
    }

    public static class StatisticsDataBean {
        /**
         * sumMoney : 19500
         * income : 12300
         * outcome : 69300
         * borrowIn : 0
         * borrowOut : 0
         */

        private String sumMoney;
        private String income;
        private String outcome;
        private String borrowIn;
        private String borrowOut;


        public String getSumMoney() {
            return sumMoney;
        }

        public void setSumMoney(String sumMoney) {
            this.sumMoney = sumMoney;
        }

        public String getIncome() {
            return income;
        }

        public void setIncome(String income) {
            this.income = income;
        }

        public String getOutcome() {
            return outcome;
        }

        public void setOutcome(String outcome) {
            this.outcome = outcome;
        }

        public String getBorrowIn() {
            return borrowIn;
        }

        public void setBorrowIn(String borrowIn) {
            this.borrowIn = borrowIn;
        }

        public String getBorrowOut() {
            return borrowOut;
        }

        public void setBorrowOut(String borrowOut) {
            this.borrowOut = borrowOut;
        }
    }

    public static class AccountResponse {
        /**
         * accountID : 1
         * accountNum : RWIJ_JSZH_000001
         * accountName : 账户
         * accountType : 2
         * createMoney : 100.00
         * nowMoney : 9000.00
         * addTime : 1583804923
         * shopID : 1
         * shopNum : RWIJ_MD_000001
         * shopName : 总门店
         */

        private String accountID;
        private String accountNum;
        private String accountName;
        private int accountType;//账户类型：1.现金；2.银行账户；3.微信；4.支付宝；
        private String createMoney;
        private String nowMoney;
        private long addTime;
        private long shopID;
        private String shopNum;
        private String shopName;

        public String getAccountID() {
            return accountID;
        }

        public void setAccountID(String accountID) {
            this.accountID = accountID;
        }

        public String getAccountNum() {
            return accountNum;
        }

        public void setAccountNum(String accountNum) {
            this.accountNum = accountNum;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public int getAccountType() {
            return accountType;
        }

        public void setAccountType(int accountType) {
            this.accountType = accountType;
        }

        public String getCreateMoney() {
            return createMoney;
        }

        public void setCreateMoney(String createMoney) {
            this.createMoney = createMoney;
        }

        public String getNowMoney() {
            return nowMoney;
        }

        public void setNowMoney(String nowMoney) {
            this.nowMoney = nowMoney;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public long getShopID() {
            return shopID;
        }

        public void setShopID(long shopID) {
            this.shopID = shopID;
        }

        public String getShopNum() {
            return shopNum;
        }

        public void setShopNum(String shopNum) {
            this.shopNum = shopNum;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }
    }
}
