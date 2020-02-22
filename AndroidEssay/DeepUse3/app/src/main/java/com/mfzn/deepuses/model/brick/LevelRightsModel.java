package com.mfzn.deepuses.model.brick;

import java.util.List;

public class LevelRightsModel {

    /**
     * data_id : 1
     * name : 普通会员
     * min : 0
     * max : 0
     * discount : 1.00
     * modulePrice : [{"data_id":1,"moduleType":1,"buyPrice":200,"buyOutPrice":0}]
     */

    private int data_id;
    private String name;
    private int min;
    private int max;
    private String discount;
    private List<ModulePriceBean> modulePrice;

    public int getData_id() {
        return data_id;
    }

    public void setData_id(int data_id) {
        this.data_id = data_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public List<ModulePriceBean> getModulePrice() {
        return modulePrice;
    }

    public void setModulePrice(List<ModulePriceBean> modulePrice) {
        this.modulePrice = modulePrice;
    }

    public static class ModulePriceBean {
        /**
         * data_id : 1
         * moduleType : 1
         * buyPrice : 200
         * buyOutPrice : 0
         */

        private int data_id;
        private int moduleType;
        private int buyPrice;
        private int buyOutPrice;

        public int getData_id() {
            return data_id;
        }

        public void setData_id(int data_id) {
            this.data_id = data_id;
        }

        public int getModuleType() {
            return moduleType;
        }

        public void setModuleType(int moduleType) {
            this.moduleType = moduleType;
        }

        public int getBuyPrice() {
            return buyPrice;
        }

        public void setBuyPrice(int buyPrice) {
            this.buyPrice = buyPrice;
        }

        public int getBuyOutPrice() {
            return buyOutPrice;
        }

        public void setBuyOutPrice(int buyOutPrice) {
            this.buyOutPrice = buyOutPrice;
        }
    }
}
