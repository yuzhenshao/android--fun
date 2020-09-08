package com.mfzn.deepuses.bean.request.setting;

public class AddMoneyAccountRequest {

    private String accountName;
    private int accountType;
    private String createMoney;

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
}
