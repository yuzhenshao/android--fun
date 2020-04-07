package com.mfzn.deepuses.model.brick;

import java.util.List;

public class BrickRecordModel {

    /**
     * forCompanyMoney : {"companyList":[{"toCompany":1,"sumMoney":"286.00","companyName":"常州冲浪网络科技有限公司"},{"toCompany":2,"sumMoney":"88.00","companyName":""}],"sumMoney":374}
     * forCompanyZhuan : {"companyList":[{"toCompany":1,"sumMoney":"2860.00","companyName":"常州冲浪网络科技有限公司"},{"toCompany":2,"sumMoney":"880.00","companyName":""}],"sumMoney":3740}
     * financialLog : {"total":9,"per_page":10,"current_page":1,"last_page":1,"data":[{"data_id":13,"userID":27,"toCompany":2,"type":2,"isZhuan":1,"money":"880.00","note":"砖自动转入公司","addTime":1576054887},{"data_id":12,"userID":27,"toCompany":2,"type":1,"isZhuan":1,"money":"880.00","note":"heihei","addTime":1576054887},{"data_id":11,"userID":27,"toCompany":2,"type":2,"isZhuan":2,"money":"88.00","note":"heihei","addTime":1576054887},{"data_id":8,"userID":27,"toCompany":1,"type":2,"isZhuan":1,"money":"1980.00","note":"砖自动转入公司","addTime":1576053585},{"data_id":7,"userID":27,"toCompany":1,"type":1,"isZhuan":1,"money":"1980.00","note":"heihei","addTime":1576053585},{"data_id":6,"userID":27,"toCompany":1,"type":2,"isZhuan":2,"money":"198.00","note":"hahah","addTime":1576053585},{"data_id":3,"userID":27,"toCompany":1,"type":2,"isZhuan":1,"money":"880.00","note":"砖自动转入公司","addTime":1576047542},{"data_id":2,"userID":27,"toCompany":1,"type":1,"isZhuan":1,"money":"880.00","note":"用户ID27的人买砖获得880砖","addTime":1576047542},{"data_id":1,"userID":27,"toCompany":1,"type":2,"isZhuan":2,"money":"88.00","note":"用户ID27的用户给消耗88元买砖","addTime":1576047542}]}
     */

    private ForCompanyBean forCompanyMoney;
    private ForCompanyBean forCompanyBrick;
    private FinancialLogBean financialLog;

    public ForCompanyBean getForCompanyMoney() {
        return forCompanyMoney;
    }

    public void setForCompanyMoney(ForCompanyBean forCompanyMoney) {
        this.forCompanyMoney = forCompanyMoney;
    }

    public ForCompanyBean getForCompanyZhuan() {
        return forCompanyBrick;
    }

    public void setForCompanyZhuan(ForCompanyBean forCompanyZhuan) {
        this.forCompanyBrick = forCompanyZhuan;
    }

    public FinancialLogBean getFinancialLog() {
        return financialLog;
    }

    public void setFinancialLog(FinancialLogBean financialLog) {
        this.financialLog = financialLog;
    }

    public String getBrickMoney(){
        if(forCompanyBrick!=null){
            forCompanyBrick.getSumMoney();
        }
        return "0";
    }

    public String getSumMoney(){
        if(forCompanyMoney!=null){
            forCompanyMoney.getSumMoney();
        }
        return "0";
    }


    public static class ForCompanyBean {

        private String sumMoney;
        private List<CompanyListBean> companyList;

        public String getSumMoney() {
            return sumMoney;
        }

        public void setSumMoney(String sumMoney) {
            this.sumMoney = sumMoney;
        }

        public List<CompanyListBean> getCompanyList() {
            return companyList;
        }

        public void setCompanyList(List<CompanyListBean> companyList) {
            this.companyList = companyList;
        }

        public static class CompanyListBean {
            /**
             * toCompany : 1
             * sumMoney : 286.00
             * companyName : 常州冲浪网络科技有限公司
             */

            private int toCompany;
            private String sumMoney;
            private String companyName;

            public int getToCompany() {
                return toCompany;
            }

            public void setToCompany(int toCompany) {
                this.toCompany = toCompany;
            }

            public String getSumMoney() {
                return sumMoney;
            }

            public void setSumMoney(String sumMoney) {
                this.sumMoney = sumMoney;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }
        }
    }

    public static class FinancialLogBean {
        /**
         * total : 9
         * per_page : 10
         * current_page : 1
         * last_page : 1
         * data : [{"data_id":13,"userID":27,"toCompany":2,"type":2,"isZhuan":1,"money":"880.00","note":"砖自动转入公司","addTime":1576054887},{"data_id":12,"userID":27,"toCompany":2,"type":1,"isZhuan":1,"money":"880.00","note":"heihei","addTime":1576054887},{"data_id":11,"userID":27,"toCompany":2,"type":2,"isZhuan":2,"money":"88.00","note":"heihei","addTime":1576054887},{"data_id":8,"userID":27,"toCompany":1,"type":2,"isZhuan":1,"money":"1980.00","note":"砖自动转入公司","addTime":1576053585},{"data_id":7,"userID":27,"toCompany":1,"type":1,"isZhuan":1,"money":"1980.00","note":"heihei","addTime":1576053585},{"data_id":6,"userID":27,"toCompany":1,"type":2,"isZhuan":2,"money":"198.00","note":"hahah","addTime":1576053585},{"data_id":3,"userID":27,"toCompany":1,"type":2,"isZhuan":1,"money":"880.00","note":"砖自动转入公司","addTime":1576047542},{"data_id":2,"userID":27,"toCompany":1,"type":1,"isZhuan":1,"money":"880.00","note":"用户ID27的人买砖获得880砖","addTime":1576047542},{"data_id":1,"userID":27,"toCompany":1,"type":2,"isZhuan":2,"money":"88.00","note":"用户ID27的用户给消耗88元买砖","addTime":1576047542}]
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
             * data_id : 13
             * userID : 27
             * toCompany : 2
             * type : 2
             * isZhuan : 1
             * money : 880.00
             * note : 砖自动转入公司
             * addTime : 1576054887
             */

            private int data_id;
            private int userID;
            private int toCompany;
            private int type;
            private int isBrick;
            private String money;
            private String note;
            private int addTime;

            public int getData_id() {
                return data_id;
            }

            public void setData_id(int data_id) {
                this.data_id = data_id;
            }

            public int getUserID() {
                return userID;
            }

            public void setUserID(int userID) {
                this.userID = userID;
            }

            public int getToCompany() {
                return toCompany;
            }

            public void setToCompany(int toCompany) {
                this.toCompany = toCompany;
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
