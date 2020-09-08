package com.mfzn.deepuses.bean.request.capital;

public class MoneyTransferRequest {

    private String fromAccountID;
    private String toAccountID;
    private String money;
    private String remark;
    private long transferDate;

    public String getFromAccountID() {
        return fromAccountID;
    }

    public void setFromAccountID(String fromAccountID) {
        this.fromAccountID = fromAccountID;
    }

    public String getToAccountID() {
        return toAccountID;
    }

    public void setToAccountID(String toAccountID) {
        this.toAccountID = toAccountID;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(long transferDate) {
        this.transferDate = transferDate;
    }
}
