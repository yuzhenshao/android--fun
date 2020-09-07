package com.mfzn.deepuses.purchasesellsave.sale.Module;

import android.text.TextUtils;

import java.io.Serializable;

public class OtherCostModule implements Serializable {

    private String otherCostID;
    private String otherCostTypeName;
    private String costMoney;
    private int hasTax;//0,是否含税：1是0否 <number>
    private double taxRate;


    public String getCostType() {
        return otherCostID;
    }

    public void setCostType(String costType) {
        this.otherCostID = costType;
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
        return hasTax==1;
    }

    public void setTaxRate(boolean taxRate) {
        hasTax=taxRate?1:0;
    }

    public String getCostName() {
        return otherCostTypeName;
    }

    public void setCostName(String costName) {
        this.otherCostTypeName = costName;
    }

    public double getCost() {
        if (TextUtils.isEmpty(costMoney)) {
            return 0;
        }
        try {
            double costPrice = Double.parseDouble(costMoney);
            if (costPrice > 0) {
                return taxRate > 0 ? costPrice * (1 + taxRate) : costPrice;
            }
        } catch (Exception e) {
        }
        return 0;
    }
}
