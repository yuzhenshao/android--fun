package com.mfzn.deepuses.bean.response.sale;

import java.util.List;

public class OrderOfferListResponse {


    /**
     * total : 1
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"orderID":1,"companyID":2,"shopID":1,"customerID":1,"customerName":"李四","customerPhone":"15300000003","salesPersonUserID":1,"salesPersonUserName":"ewenXing","orderTime":1585130651,"orderMakerUserID":1,"orderMakerUserName":"ewenXing","outNum":"out101010","orderNum":"RWIJ_BJ_00000001","totalMoney":"10998.90","discountAmount":"998.90","realMoney":"10000.00","remark":"这个是备注","recName":"小秦","recPhone":"15300000000","recAreaID":"320412","provinceName":"江苏省","cityName":"常州市","areaName":"武进区","recAddress":"安通大厦","recCode":"000000","recTelephone":"0519-0000000","isCheck":0,"checkTime":0,"checkNote":"","checkUserID":1,"checkUserName":"ewenXing","addTime":1587961546,"goodsAllCount":1,"goodsInfo":[{"goodsID":1,"goodsCount":1,"goodsName":"MacBook Pro","goodsCatName":"Apple","goodsAttr":"最新的","goodsNum":"SP0001","goodsUnitName":"台","goodsBarCode":"111111","goodsBrand":"Apple","goodsPosition":1,"goodsMainImage":"1.jpg","uniformSalePrice":"9999.00","salePrice":"9999.00","salePriceWithTax":"10998.90","costPrice":"0.00","taxRate":"0.10","money":"10998.90"}],"otherCost":[{"otherCostID":11,"otherCostTypeName":"运费","costMoney":"100.00","hasTax":0,"taxRate":"0.00"}]}]
     */

    private int total;
    private int per_page;
    private int current_page;
    private int last_page;
    private List<DataBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * orderID : 1
         * companyID : 2
         * shopID : 1
         * customerID : 1
         * customerName : 李四
         * customerPhone : 15300000003
         * salesPersonUserID : 1
         * salesPersonUserName : ewenXing
         * orderTime : 1585130651
         * orderMakerUserID : 1
         * orderMakerUserName : ewenXing
         * outNum : out101010
         * orderNum : RWIJ_BJ_00000001
         * totalMoney : 10998.90
         * discountAmount : 998.90
         * realMoney : 10000.00
         * remark : 这个是备注
         * recName : 小秦
         * recPhone : 15300000000
         * recAreaID : 320412
         * provinceName : 江苏省
         * cityName : 常州市
         * areaName : 武进区
         * recAddress : 安通大厦
         * recCode : 000000
         * recTelephone : 0519-0000000
         * isCheck : 0
         * checkTime : 0
         * checkNote :
         * checkUserID : 1
         * checkUserName : ewenXing
         * addTime : 1587961546
         * goodsAllCount : 1
         * goodsInfo : [{"goodsID":1,"goodsCount":1,"goodsName":"MacBook Pro","goodsCatName":"Apple","goodsAttr":"最新的","goodsNum":"SP0001","goodsUnitName":"台","goodsBarCode":"111111","goodsBrand":"Apple","goodsPosition":1,"goodsMainImage":"1.jpg","uniformSalePrice":"9999.00","salePrice":"9999.00","salePriceWithTax":"10998.90","costPrice":"0.00","taxRate":"0.10","money":"10998.90"}]
         * otherCost : [{"otherCostID":11,"otherCostTypeName":"运费","costMoney":"100.00","hasTax":0,"taxRate":"0.00"}]
         */

        private String orderID;
        private String companyID;
        private String shopID;
        private String customerID;
        private String customerName;
        private String customerPhone;
        private String salesPersonUserID;
        private String salesPersonUserName;
        private long orderTime;
        private String orderMakerUserID;
        private String orderMakerUserName;
        private String outNum;
        private String orderNum;
        private String totalMoney;
        private String discountAmount;
        private String realMoney;
        private String remark;
        private String recName;
        private String recPhone;
        private String recAreaID;
        private String provinceName;
        private String cityName;
        private String areaName;
        private String recAddress;
        private String recCode;
        private String recTelephone;
        private int isCheck;
        private long checkTime;
        private String checkNote;
        private String checkUserID;
        private String checkUserName;
        private long addTime;
        private int goodsAllCount;
        private List<GoodsInfoBean> goodsInfo;
        private List<OtherCostBean> otherCost;

        public String getOrderID() {
            return orderID;
        }

        public void setOrderID(String orderID) {
            this.orderID = orderID;
        }

        public String getCompanyID() {
            return companyID;
        }

        public void setCompanyID(String companyID) {
            this.companyID = companyID;
        }

        public String getShopID() {
            return shopID;
        }

        public void setShopID(String shopID) {
            this.shopID = shopID;
        }

        public String getCustomerID() {
            return customerID;
        }

        public void setCustomerID(String customerID) {
            this.customerID = customerID;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getCustomerPhone() {
            return customerPhone;
        }

        public void setCustomerPhone(String customerPhone) {
            this.customerPhone = customerPhone;
        }

        public String getSalesPersonUserID() {
            return salesPersonUserID;
        }

        public void setSalesPersonUserID(String salesPersonUserID) {
            this.salesPersonUserID = salesPersonUserID;
        }

        public String getSalesPersonUserName() {
            return salesPersonUserName;
        }

        public void setSalesPersonUserName(String salesPersonUserName) {
            this.salesPersonUserName = salesPersonUserName;
        }

        public long getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(long orderTime) {
            this.orderTime = orderTime;
        }

        public String getOrderMakerUserID() {
            return orderMakerUserID;
        }

        public void setOrderMakerUserID(String orderMakerUserID) {
            this.orderMakerUserID = orderMakerUserID;
        }

        public String getOrderMakerUserName() {
            return orderMakerUserName;
        }

        public void setOrderMakerUserName(String orderMakerUserName) {
            this.orderMakerUserName = orderMakerUserName;
        }

        public String getOutNum() {
            return outNum;
        }

        public void setOutNum(String outNum) {
            this.outNum = outNum;
        }

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }

        public String getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(String totalMoney) {
            this.totalMoney = totalMoney;
        }

        public String getDiscountAmount() {
            return discountAmount;
        }

        public void setDiscountAmount(String discountAmount) {
            this.discountAmount = discountAmount;
        }

        public String getRealMoney() {
            return realMoney;
        }

        public void setRealMoney(String realMoney) {
            this.realMoney = realMoney;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getRecName() {
            return recName;
        }

        public void setRecName(String recName) {
            this.recName = recName;
        }

        public String getRecPhone() {
            return recPhone;
        }

        public void setRecPhone(String recPhone) {
            this.recPhone = recPhone;
        }

        public String getRecAreaID() {
            return recAreaID;
        }

        public void setRecAreaID(String recAreaID) {
            this.recAreaID = recAreaID;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getRecAddress() {
            return recAddress;
        }

        public void setRecAddress(String recAddress) {
            this.recAddress = recAddress;
        }

        public String getRecCode() {
            return recCode;
        }

        public void setRecCode(String recCode) {
            this.recCode = recCode;
        }

        public String getRecTelephone() {
            return recTelephone;
        }

        public void setRecTelephone(String recTelephone) {
            this.recTelephone = recTelephone;
        }

        public int getIsCheck() {
            return isCheck;
        }

        public void setIsCheck(int isCheck) {
            this.isCheck = isCheck;
        }

        public long getCheckTime() {
            return checkTime;
        }

        public void setCheckTime(long checkTime) {
            this.checkTime = checkTime;
        }

        public String getCheckNote() {
            return checkNote;
        }

        public void setCheckNote(String checkNote) {
            this.checkNote = checkNote;
        }

        public String getCheckUserID() {
            return checkUserID;
        }

        public void setCheckUserID(String checkUserID) {
            this.checkUserID = checkUserID;
        }

        public String getCheckUserName() {
            return checkUserName;
        }

        public void setCheckUserName(String checkUserName) {
            this.checkUserName = checkUserName;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public int getGoodsAllCount() {
            return goodsAllCount;
        }

        public void setGoodsAllCount(int goodsAllCount) {
            this.goodsAllCount = goodsAllCount;
        }

        public List<GoodsInfoBean> getGoodsInfo() {
            return goodsInfo;
        }

        public void setGoodsInfo(List<GoodsInfoBean> goodsInfo) {
            this.goodsInfo = goodsInfo;
        }

        public List<OtherCostBean> getOtherCost() {
            return otherCost;
        }

        public void setOtherCost(List<OtherCostBean> otherCost) {
            this.otherCost = otherCost;
        }

        public static class GoodsInfoBean {
            /**
             * goodsID : 1
             * goodsCount : 1
             * goodsName : MacBook Pro
             * goodsCatName : Apple
             * goodsAttr : 最新的
             * goodsNum : SP0001
             * goodsUnitName : 台
             * goodsBarCode : 111111
             * goodsBrand : Apple
             * goodsPosition : 1
             * goodsMainImage : 1.jpg
             * uniformSalePrice : 9999.00
             * salePrice : 9999.00
             * salePriceWithTax : 10998.90
             * costPrice : 0.00
             * taxRate : 0.10
             * money : 10998.90
             */

            private String goodsID;
            private int goodsCount;
            private String goodsName;
            private String goodsCatName;
            private String goodsAttr;
            private String goodsNum;
            private String goodsUnitName;
            private String goodsBarCode;
            private String goodsBrand;
            private String goodsPosition;
            private String goodsMainImage;
            private String uniformSalePrice;
            private String salePrice;
            private String salePriceWithTax;
            private String costPrice;
            private String taxRate;
            private String money;

            public String getGoodsID() {
                return goodsID;
            }

            public void setGoodsID(String goodsID) {
                this.goodsID = goodsID;
            }

            public int getGoodsCount() {
                return goodsCount;
            }

            public void setGoodsCount(int goodsCount) {
                this.goodsCount = goodsCount;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public String getGoodsCatName() {
                return goodsCatName;
            }

            public void setGoodsCatName(String goodsCatName) {
                this.goodsCatName = goodsCatName;
            }

            public String getGoodsAttr() {
                return goodsAttr;
            }

            public void setGoodsAttr(String goodsAttr) {
                this.goodsAttr = goodsAttr;
            }

            public String getGoodsNum() {
                return goodsNum;
            }

            public void setGoodsNum(String goodsNum) {
                this.goodsNum = goodsNum;
            }

            public String getGoodsUnitName() {
                return goodsUnitName;
            }

            public void setGoodsUnitName(String goodsUnitName) {
                this.goodsUnitName = goodsUnitName;
            }

            public String getGoodsBarCode() {
                return goodsBarCode;
            }

            public void setGoodsBarCode(String goodsBarCode) {
                this.goodsBarCode = goodsBarCode;
            }

            public String getGoodsBrand() {
                return goodsBrand;
            }

            public void setGoodsBrand(String goodsBrand) {
                this.goodsBrand = goodsBrand;
            }

            public String getGoodsPosition() {
                return goodsPosition;
            }

            public void setGoodsPosition(String goodsPosition) {
                this.goodsPosition = goodsPosition;
            }

            public String getGoodsMainImage() {
                return goodsMainImage;
            }

            public void setGoodsMainImage(String goodsMainImage) {
                this.goodsMainImage = goodsMainImage;
            }

            public String getUniformSalePrice() {
                return uniformSalePrice;
            }

            public void setUniformSalePrice(String uniformSalePrice) {
                this.uniformSalePrice = uniformSalePrice;
            }

            public String getSalePrice() {
                return salePrice;
            }

            public void setSalePrice(String salePrice) {
                this.salePrice = salePrice;
            }

            public String getSalePriceWithTax() {
                return salePriceWithTax;
            }

            public void setSalePriceWithTax(String salePriceWithTax) {
                this.salePriceWithTax = salePriceWithTax;
            }

            public String getCostPrice() {
                return costPrice;
            }

            public void setCostPrice(String costPrice) {
                this.costPrice = costPrice;
            }

            public String getTaxRate() {
                return taxRate;
            }

            public void setTaxRate(String taxRate) {
                this.taxRate = taxRate;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }
        }

        public static class OtherCostBean {
            /**
             * otherCostID : 11
             * otherCostTypeName : 运费
             * costMoney : 100.00
             * hasTax : 0
             * taxRate : 0.00
             */

            private String otherCostID;
            private String otherCostTypeName;
            private String costMoney;
            private int hasTax;
            private double taxRate;

            public String getOtherCostID() {
                return otherCostID;
            }

            public void setOtherCostID(String otherCostID) {
                this.otherCostID = otherCostID;
            }

            public String getOtherCostTypeName() {
                return otherCostTypeName;
            }

            public void setOtherCostTypeName(String otherCostTypeName) {
                this.otherCostTypeName = otherCostTypeName;
            }

            public String getCostMoney() {
                return costMoney;
            }

            public void setCostMoney(String costMoney) {
                this.costMoney = costMoney;
            }

            public int getHasTax() {
                return hasTax;
            }

            public void setHasTax(int hasTax) {
                this.hasTax = hasTax;
            }

            public double getTaxRate() {
                return taxRate;
            }

            public void setTaxRate(double taxRate) {
                this.taxRate = taxRate;
            }
        }
    }
}
