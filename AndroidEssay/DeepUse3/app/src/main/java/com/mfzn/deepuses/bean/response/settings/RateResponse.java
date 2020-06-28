package com.mfzn.deepuses.bean.response.settings;

public class RateResponse {

    /**
     * taxRateID : 1
     * rate : 5.00
     */

    private String taxRateID;
    private String rate;

    public String getTaxRateID() {
        return taxRateID;
    }

    public void setTaxRateID(String taxRateID) {
        this.taxRateID = taxRateID;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
