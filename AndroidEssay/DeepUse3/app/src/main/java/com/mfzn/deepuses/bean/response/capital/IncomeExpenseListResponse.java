package com.mfzn.deepuses.bean.response.capital;

import java.io.Serializable;
import java.util.List;

public class IncomeExpenseListResponse {

    public static int INCOME=1;
    public static int EXPENSE=2;


    /**
     * sumIncome : 0
     * sumExpense : 100
     * list : {"total":1,"per_page":10,"current_page":1,"last_page":1,"data":[{"data_id":1,"companyID":2,"shopID":1,"dataType":2,"orderNum":"RWIJ_SZ_00000001","typeID":2,"typeName":"房租","money":"100.00","moneyAccountID":1,"accountName":"账户1","accountType":2,"dataTime":1583996862,"dataUserID":1,"dataUserName":"ewenXing","remark":"收入100元","checkStatus":0,"checkNote":"","checkerUserName":"ewenXing","addTime":1589164031,"proInfo":{"proID":3,"proName":"项目2"}}]}
     */

    private int sumIncome;
    private int sumExpense;
    private ListBean list;

    public int getSumIncome() {
        return sumIncome;
    }

    public void setSumIncome(int sumIncome) {
        this.sumIncome = sumIncome;
    }

    public int getSumExpense() {
        return sumExpense;
    }

    public void setSumExpense(int sumExpense) {
        this.sumExpense = sumExpense;
    }

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * total : 1
         * per_page : 10
         * current_page : 1
         * last_page : 1
         * data : [{"data_id":1,"companyID":2,"shopID":1,"dataType":2,"orderNum":"RWIJ_SZ_00000001","typeID":2,"typeName":"房租","money":"100.00","moneyAccountID":1,"accountName":"账户1","accountType":2,"dataTime":1583996862,"dataUserID":1,"dataUserName":"ewenXing","remark":"收入100元","checkStatus":0,"checkNote":"","checkerUserName":"ewenXing","addTime":1589164031,"proInfo":{"proID":3,"proName":"项目2"}}]
         */

        private int total;
        private int per_page;
        private int current_page;
        private int last_page;
        private List<IncomeExpenseResponse> data;

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

        public List<IncomeExpenseResponse> getData() {
            return data;
        }

        public void setData(List<IncomeExpenseResponse> data) {
            this.data = data;
        }

        public static class IncomeExpenseResponse implements Serializable {
            /**
             * data_id : 1
             * companyID : 2
             * shopID : 1
             * dataType : 2
             * orderNum : RWIJ_SZ_00000001
             * typeID : 2
             * typeName : 房租
             * money : 100.00
             * moneyAccountID : 1
             * accountName : 账户1
             * accountType : 2
             * dataTime : 1583996862
             * dataUserID : 1
             * dataUserName : ewenXing
             * remark : 收入100元
             * checkStatus : 0
             * checkNote :
             * checkerUserName : ewenXing
             * addTime : 1589164031
             * proInfo : {"proID":3,"proName":"项目2"}
             */

            private int data_id;
            private String companyID;
            private String shopID;
            private int dataType;
            private String orderNum;
            private String typeID;
            private String typeName;
            private String money;
            private String moneyAccountID;
            private String accountName;
            private int accountType;
            private long dataTime;
            private String dataUserID;
            private String dataUserName;
            private String remark;
            private int checkStatus;//0待审核 1通过 2拒绝
            private String checkNote;
            private String checkerUserName;
            private long addTime;
            private ProInfoBean proInfo;

            public int getData_id() {
                return data_id;
            }

            public void setData_id(int data_id) {
                this.data_id = data_id;
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

            public int getDataType() {
                return dataType;
            }

            public void setDataType(int dataType) {
                this.dataType = dataType;
            }

            public String getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(String orderNum) {
                this.orderNum = orderNum;
            }

            public String getTypeID() {
                return typeID;
            }

            public void setTypeID(String typeID) {
                this.typeID = typeID;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getMoneyAccountID() {
                return moneyAccountID;
            }

            public void setMoneyAccountID(String moneyAccountID) {
                this.moneyAccountID = moneyAccountID;
            }

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

            public long getDataTime() {
                return dataTime;
            }

            public void setDataTime(long dataTime) {
                this.dataTime = dataTime;
            }

            public String getDataUserID() {
                return dataUserID;
            }

            public void setDataUserID(String dataUserID) {
                this.dataUserID = dataUserID;
            }

            public String getDataUserName() {
                return dataUserName;
            }

            public void setDataUserName(String dataUserName) {
                this.dataUserName = dataUserName;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getCheckStatus() {
                return checkStatus;
            }

            public void setCheckStatus(int checkStatus) {
                this.checkStatus = checkStatus;
            }

            public String getCheckNote() {
                return checkNote;
            }

            public void setCheckNote(String checkNote) {
                this.checkNote = checkNote;
            }

            public String getCheckerUserName() {
                return checkerUserName;
            }

            public void setCheckerUserName(String checkerUserName) {
                this.checkerUserName = checkerUserName;
            }

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public ProInfoBean getProInfo() {
                return proInfo;
            }

            public void setProInfo(ProInfoBean proInfo) {
                this.proInfo = proInfo;
            }

            public static class ProInfoBean implements Serializable{
                /**
                 * proID : 3
                 * proName : 项目2
                 */

                private String proID;
                private String proName;

                public String getProID() {
                    return proID;
                }

                public void setProID(String proID) {
                    this.proID = proID;
                }

                public String getProName() {
                    return proName;
                }

                public void setProName(String proName) {
                    this.proName = proName;
                }
            }
        }
    }
}
