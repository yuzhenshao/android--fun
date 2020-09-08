package com.mfzn.deepuses.bean.request.capital;

public class AddBorrowRequest {

    private int type;
    private String money;
    private String moneyAccountID;
    private String borrowUser;
    private long dataTime;
    private String dataUserID;
    private String remark;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
