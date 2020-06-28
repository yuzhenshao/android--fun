package com.mfzn.deepuses.purchasesellsave.sale.Module;

import java.io.Serializable;

public class OtherCostModule implements Serializable {

    private String costName;
    private String costType;
    private String costMoney;
    private double taxRate;
    private boolean isTaxRate;

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public String getCostMoney() {
        return costMoney;
    }

    public void setCostMoney(String costMoney) {
        this.costMoney = costMoney;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public boolean isTaxRate() {
        return isTaxRate;
    }

    public void setTaxRate(boolean taxRate) {
        isTaxRate = taxRate;
    }

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }
}
