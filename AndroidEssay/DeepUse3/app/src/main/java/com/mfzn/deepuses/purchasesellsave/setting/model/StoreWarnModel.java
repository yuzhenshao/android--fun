package com.mfzn.deepuses.purchasesellsave.setting.model;

import android.text.TextUtils;

public class StoreWarnModel {
    private String id;
    private String max;
    private String min;
    private String number;

    public StoreWarnModel(String id) {
        this.id = id;
    }


    public StoreWarnModel(String id, String max, String min) {
        this.id = id;
        this.max = max;
        this.min = min;
    }

    public StoreWarnModel(String id, String number) {
        this.id = id;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public int hashCode() {
        return TextUtils.isEmpty(getId()) ? 0 : getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StoreWarnModel) {
            return !TextUtils.isEmpty(getId()) && getId().equals(((StoreWarnModel) obj).getId());
        }
        return false;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
