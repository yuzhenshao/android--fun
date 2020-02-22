package com.mfzn.deepuses.model.brick;

import java.util.List;

public class CompanyInfoModel {

    /**
     * data_id : 14
     * companyName : 啦啦啦
     * shortName : 麦麸智能（常州）有限公司
     * logo : /uploads/companyLogo/14/20191120/bc03afb5b61e9ab3c5f879e2c4130533.jpg
     * zhuan : 1680.00
     * giftZhuan : 188.00
     * companyLevel : 1
     * levelName : 普通会员
     * levelRights : {"data_id":1,"name":"普通会员","min":0,"max":0,"discount":"1.00","modulePrice":[{"data_id":1,"moduleType":1,"buyPrice":200,"buyOutPrice":0}]}
     * sumRecharge : 0.01
     * nextLevel : 黄金会员
     * leftMoney : 0.99
     */

    private int data_id;
    private String companyName;
    private String shortName;
    private String logo;
    private String zhuan;
    private String giftZhuan;
    private int companyLevel;
    private String levelName;
    private LevelRightsBean levelRights;
    private double sumRecharge;
    private String nextLevel;
    private double leftMoney;

    public int getData_id() {
        return data_id;
    }

    public void setData_id(int data_id) {
        this.data_id = data_id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getZhuan() {
        return zhuan;
    }

    public void setZhuan(String zhuan) {
        this.zhuan = zhuan;
    }

    public String getGiftZhuan() {
        return giftZhuan;
    }

    public void setGiftZhuan(String giftZhuan) {
        this.giftZhuan = giftZhuan;
    }

    public int getCompanyLevel() {
        return companyLevel;
    }

    public void setCompanyLevel(int companyLevel) {
        this.companyLevel = companyLevel;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public LevelRightsBean getLevelRights() {
        return levelRights;
    }

    public void setLevelRights(LevelRightsBean levelRights) {
        this.levelRights = levelRights;
    }

    public double getSumRecharge() {
        return sumRecharge;
    }

    public void setSumRecharge(double sumRecharge) {
        this.sumRecharge = sumRecharge;
    }

    public String getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(String nextLevel) {
        this.nextLevel = nextLevel;
    }

    public double getLeftMoney() {
        return leftMoney;
    }

    public void setLeftMoney(double leftMoney) {
        this.leftMoney = leftMoney;
    }

    public static class LevelRightsBean {
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
}
