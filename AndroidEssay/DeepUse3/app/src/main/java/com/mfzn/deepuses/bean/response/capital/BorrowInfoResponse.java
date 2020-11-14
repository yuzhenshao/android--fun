package com.mfzn.deepuses.bean.response.capital;

import java.io.Serializable;
import java.util.List;

public class BorrowInfoResponse implements Serializable{


    /**
     * borrowID : 1
     * companyID : 2
     * shopID : 1
     * dataType : 1
     * orderNum : RWIJ_JD_00000001
     * money : 800.00
     * stillNeed : 700.00
     * hasDone : 100.00
     * moneyAccountID : 1
     * accountName : 账户1
     * accountType : 2
     * borrowUser : 张三
     * dataTime : 1584022279
     * dataUserID : 1
     * dataUserName : ewenXing
     * remark : 哈哈哈
     * isFinished : 1
     * addTime : 1589336429
     * handleLog : [{"logID":1,"borrowID":1,"money":"100.00","moneyAccountID":1,"accountName":"账户1","accountType":2,"dataTime":1584084260,"dataUserID":1,"userName":"ewenXing","addTime":1589336805}]
     */

    private String borrowID;
    private String companyID;
    private String shopID;
    private int dataType;
    private String orderNum;
    private String money;
    private String stillNeed;
    private String hasDone;
    private String moneyAccountID;
    private String accountName;
    private int accountType;
    private String borrowUser;
    private long dataTime;
    private String dataUserID;
    private String dataUserName;
    private String remark;
    private int isFinished;
    private long addTime;
    private List<HandleLogResponse> handleLog;

    public String getBorrowID() {
        return borrowID;
    }

    public void setBorrowID(String borrowID) {
        this.borrowID = borrowID;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getStillNeed() {
        return stillNeed;
    }

    public void setStillNeed(String stillNeed) {
        this.stillNeed = stillNeed;
    }

    public String getHasDone() {
        return hasDone;
    }

    public void setHasDone(String hasDone) {
        this.hasDone = hasDone;
    }

    public String getMoneyAccountID() {
        return moneyAccountID;
    }

    public void setMoneyAccountID(String moneyAccountID) {
        this.moneyAccountID = moneyAccountID;
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

    public String getBorrowUser() {
        return borrowUser;
    }

    public void setBorrowUser(String borrowUser) {
        this.borrowUser = borrowUser;
    }

    public long getDataTime() {
        return dataTime;
    }

    public void setDataTime(long dataTime) {
        this.dataTime = dataTime;
    }

    public String getDataUserID() {
        return dataUserID;
    }

    public void setDataUserID(String dataUserID) {
        this.dataUserID = dataUserID;
    }

    public String getDataUserName() {
        return dataUserName;
    }

    public void setDataUserName(String dataUserName) {
        this.dataUserName = dataUserName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(int isFinished) {
        this.isFinished = isFinished;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public List<HandleLogResponse> getHandleLog() {
        return handleLog;
    }

    public void setHandleLog(List<HandleLogResponse> handleLog) {
        this.handleLog = handleLog;
    }

    //资金账户类型：1.现金；2.银行账户；3.微信；4.支付宝；
    public String getAccountTypeName() {
        switch (accountType){
            case 1:
                return "现金";
            case 2:
                return "银行账户";
            case 3:
                return "微信";
            case 4:
                return "支付宝";
        }
        return "";
    }


    public static class HandleLogResponse implements Serializable {
        /**
         * logID : 1
         * borrowID : 1
         * money : 100.00
         * moneyAccountID : 1
         * accountName : 账户1
         * accountType : 2
         * dataTime : 1584084260
         * dataUserID : 1
         * userName : ewenXing
         * addTime : 1589336805
         */

        private String logID;
        private String borrowID;
        private String money;
        private String moneyAccountID;
        private String accountName;
        private int accountType;
        private long dataTime;
        private String dataUserID;
        private String userName;
        private long addTime;

        public String getLogID() {
            return logID;
        }

        public void setLogID(String logID) {
            this.logID = logID;
        }

        public String getBorrowID() {
            return borrowID;
        }

        public void setBorrowID(String borrowID) {
            this.borrowID = borrowID;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getMoneyAccountID() {
            return moneyAccountID;
        }

        public void setMoneyAccountID(String moneyAccountID) {
            this.moneyAccountID = moneyAccountID;
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

        public long getDataTime() {
            return dataTime;
        }

        public void setDataTime(long dataTime) {
            this.dataTime = dataTime;
        }

        public String getDataUserID() {
            return dataUserID;
        }

        public void setDataUserID(String dataUserID) {
            this.dataUserID = dataUserID;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }
    }
}
