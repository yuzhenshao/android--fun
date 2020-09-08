package com.mfzn.deepuses.bean.request.capital;

public class AddIncomeExpenseRequest {
    private int type;
    private String money;
    private String typeID;
    private String moneyAccountID;
    private long dataTime;
    private String dataUserID;
    private String proID;
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

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public String getProID() {
        return proID;
    }

    public void setProID(String proID) {
        this.proID = proID;
    }
}
