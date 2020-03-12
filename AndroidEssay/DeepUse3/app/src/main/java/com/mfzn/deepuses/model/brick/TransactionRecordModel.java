package com.mfzn.deepuses.model.brick;

import java.util.List;

public class TransactionRecordModel {

    /**
     * sumRechargeMoney : 286
     * sumRechargeZhuan : 2860
     * sumCostZhuan : 0
     * financialLog : {"total":4,"per_page":10,"current_page":1,"last_page":1,"data":[{"id":10,"orderID":0,"userID":0,"toCompany":0,"companyID":1,"fromUser":27,"type":2,"isZhuan":2,"money":"198.00","nowMoney":"0.00","note":"用户买砖消耗198元","isDel":0,"addTime":1576053585,"updateTime":0},{"id":9,"orderID":0,"userID":0,"toCompany":0,"companyID":1,"fromUser":27,"type":1,"isZhuan":1,"money":"1980.00","nowMoney":"0.00","note":"用户给公司充砖","isDel":0,"addTime":1576053585,"updateTime":0},{"id":5,"orderID":0,"userID":0,"toCompany":0,"companyID":1,"fromUser":27,"type":2,"isZhuan":2,"money":"88.00","nowMoney":"0.00","note":"ID27的用户买砖消耗88元","isDel":0,"addTime":1576047542,"updateTime":0},{"id":4,"orderID":0,"userID":0,"toCompany":0,"companyID":1,"fromUser":27,"type":1,"isZhuan":1,"money":"880.00","nowMoney":"0.00","note":"ID27的用户给公司买砖","isDel":0,"addTime":1576047542,"updateTime":0}]}
     */

    private String sumRechargeMoney;
    private String sumRechargeBrick;
    private String sumCostBrick;
    private FinancialLogBean financialLog;

    public String getSumRechargeMoney() {
        return sumRechargeMoney;
    }

    public void setSumRechargeMoney(String sumRechargeMoney) {
        this.sumRechargeMoney = sumRechargeMoney;
    }

    public String getSumRechargeZhuan() {
        return sumRechargeBrick;
    }

    public void setSumRechargeZhuan(String sumRechargeZhuan) {
        this.sumRechargeBrick = sumRechargeZhuan;
    }

    public String getSumCostZhuan() {
        return sumCostBrick;
    }

    public void setSumCostZhuan(String sumCostZhuan) {
        this.sumCostBrick = sumCostZhuan;
    }

    public FinancialLogBean getFinancialLog() {
        return financialLog;
    }

    public void setFinancialLog(FinancialLogBean financialLog) {
        this.financialLog = financialLog;
    }

    public static class FinancialLogBean {
        /**
         * total : 4
         * per_page : 10
         * current_page : 1
         * last_page : 1
         * data : [{"id":10,"orderID":0,"userID":0,"toCompany":0,"companyID":1,"fromUser":27,"type":2,"isZhuan":2,"money":"198.00","nowMoney":"0.00","note":"用户买砖消耗198元","isDel":0,"addTime":1576053585,"updateTime":0},{"id":9,"orderID":0,"userID":0,"toCompany":0,"companyID":1,"fromUser":27,"type":1,"isZhuan":1,"money":"1980.00","nowMoney":"0.00","note":"用户给公司充砖","isDel":0,"addTime":1576053585,"updateTime":0},{"id":5,"orderID":0,"userID":0,"toCompany":0,"companyID":1,"fromUser":27,"type":2,"isZhuan":2,"money":"88.00","nowMoney":"0.00","note":"ID27的用户买砖消耗88元","isDel":0,"addTime":1576047542,"updateTime":0},{"id":4,"orderID":0,"userID":0,"toCompany":0,"companyID":1,"fromUser":27,"type":1,"isZhuan":1,"money":"880.00","nowMoney":"0.00","note":"ID27的用户给公司买砖","isDel":0,"addTime":1576047542,"updateTime":0}]
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
             * id : 10
             * orderID : 0
             * userID : 0
             * toCompany : 0
             * companyID : 1
             * fromUser : 27
             * type : 2
             * isZhuan : 2
             * money : 198.00
             * nowMoney : 0.00
             * note : 用户买砖消耗198元
             * isDel : 0
             * addTime : 1576053585
             * updateTime : 0
             */

            private int id;
            private int userID;
            private int companyID;
            private int type;
            private int isBrick;
            private String money;
            private String note;
            private int addTime;
            private int updateTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserID() {
                return userID;
            }

            public void setUserID(int userID) {
                this.userID = userID;
            }


            public int getCompanyID() {
                return companyID;
            }

            public void setCompanyID(int companyID) {
                this.companyID = companyID;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getIsZhuan() {
                return isBrick;
            }

            public void setIsZhuan(int isZhuan) {
                this.isBrick = isZhuan;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }


            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }

            public int getAddTime() {
                return addTime;
            }

            public void setAddTime(int addTime) {
                this.addTime = addTime;
            }

        }
    }
}
