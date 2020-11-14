package com.mfzn.deepuses.bean.request.purchase;

public class AddBorrowHandleLogRequest {
private String borrowID;
private String money;
private String moneyAccountID;
private long dataTime;

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

    public long getDataTime() {
        return dataTime;
    }

    public void setDataTime(long dataTime) {
        this.dataTime = dataTime;
    }
}
