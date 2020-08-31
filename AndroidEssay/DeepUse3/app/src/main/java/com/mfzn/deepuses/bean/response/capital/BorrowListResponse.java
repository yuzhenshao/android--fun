package com.mfzn.deepuses.bean.response.capital;

import java.util.List;

public class BorrowListResponse {

    /**
     * sumIncome : 1800
     * sumExpense : 0
     * list : {"total":2,"per_page":10,"current_page":1,"last_page":1,"data":[{"borrowID":2,"companyID":2,"shopID":1,"dataType":1,"orderNum":"RWIJ_JD_00000002","money":"1000.00","stillNeed":"0.00","hasDone":"1000.00","moneyAccountID":1,"accountName":"账户1","accountType":2,"borrowUser":"李四","dataTime":1584022279,"dataUserID":1,"dataUserName":"ewenXing","remark":"哈哈哈","isFinished":2,"addTime":1584084425,"handleLog":[{"logID":3,"borrowID":2,"money":"100.00","moneyAccountID":1,"accountName":"账户1","accountType":2,"dataTime":1584084260,"dataUserID":1,"userName":"ewenXing","addTime":1584084443},{"logID":4,"borrowID":2,"money":"500.00","moneyAccountID":1,"accountName":"账户1","accountType":2,"dataTime":1584084260,"dataUserID":1,"userName":"ewenXing","addTime":1584084481},{"logID":5,"borrowID":2,"money":"500.00","moneyAccountID":1,"accountName":"账户1","accountType":2,"dataTime":1584084260,"dataUserID":1,"userName":"ewenXing","addTime":1584084553}]},{"borrowID":1,"companyID":2,"shopID":1,"dataType":1,"orderNum":"RWIJ_JD_00000001","money":"800.00","stillNeed":"0.00","hasDone":"800.00","moneyAccountID":1,"accountName":"账户1","accountType":2,"borrowUser":"张三","dataTime":1584022279,"dataUserID":1,"dataUserName":"ewenXing","remark":"找张三借的","isFinished":2,"addTime":1584022287,"handleLog":[{"logID":2,"borrowID":1,"money":"100.00","moneyAccountID":1,"accountName":"账户1","accountType":2,"dataTime":1584084260,"dataUserID":1,"userName":"ewenXing","addTime":1584084329}]}]}
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
         * total : 2
         * per_page : 10
         * current_page : 1
         * last_page : 1
         * data : [{"borrowID":2,"companyID":2,"shopID":1,"dataType":1,"orderNum":"RWIJ_JD_00000002","money":"1000.00","stillNeed":"0.00","hasDone":"1000.00","moneyAccountID":1,"accountName":"账户1","accountType":2,"borrowUser":"李四","dataTime":1584022279,"dataUserID":1,"dataUserName":"ewenXing","remark":"哈哈哈","isFinished":2,"addTime":1584084425,"handleLog":[{"logID":3,"borrowID":2,"money":"100.00","moneyAccountID":1,"accountName":"账户1","accountType":2,"dataTime":1584084260,"dataUserID":1,"userName":"ewenXing","addTime":1584084443},{"logID":4,"borrowID":2,"money":"500.00","moneyAccountID":1,"accountName":"账户1","accountType":2,"dataTime":1584084260,"dataUserID":1,"userName":"ewenXing","addTime":1584084481},{"logID":5,"borrowID":2,"money":"500.00","moneyAccountID":1,"accountName":"账户1","accountType":2,"dataTime":1584084260,"dataUserID":1,"userName":"ewenXing","addTime":1584084553}]},{"borrowID":1,"companyID":2,"shopID":1,"dataType":1,"orderNum":"RWIJ_JD_00000001","money":"800.00","stillNeed":"0.00","hasDone":"800.00","moneyAccountID":1,"accountName":"账户1","accountType":2,"borrowUser":"张三","dataTime":1584022279,"dataUserID":1,"dataUserName":"ewenXing","remark":"找张三借的","isFinished":2,"addTime":1584022287,"handleLog":[{"logID":2,"borrowID":1,"money":"100.00","moneyAccountID":1,"accountName":"账户1","accountType":2,"dataTime":1584084260,"dataUserID":1,"userName":"ewenXing","addTime":1584084329}]}]
         */

        private int total;
        private int per_page;
        private int current_page;
        private int last_page;
        private List<BorrowResponse> data;

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

        public List<BorrowResponse> getData() {
            return data;
        }

        public void setData(List<BorrowResponse> data) {
            this.data = data;
        }

        public static class BorrowResponse {
            /**
             * borrowID : 2
             * companyID : 2
             * shopID : 1
             * dataType : 1
             * orderNum : RWIJ_JD_00000002
             * money : 1000.00
             * stillNeed : 0.00
             * hasDone : 1000.00
             * moneyAccountID : 1
             * accountName : 账户1
             * accountType : 2
             * borrowUser : 李四
             * dataTime : 1584022279
             * dataUserID : 1
             * dataUserName : ewenXing
             * remark : 哈哈哈
             * isFinished : 2
             * addTime : 1584084425
             * handleLog : [{"logID":3,"borrowID":2,"money":"100.00","moneyAccountID":1,"accountName":"账户1","accountType":2,"dataTime":1584084260,"dataUserID":1,"userName":"ewenXing","addTime":1584084443},{"logID":4,"borrowID":2,"money":"500.00","moneyAccountID":1,"accountName":"账户1","accountType":2,"dataTime":1584084260,"dataUserID":1,"userName":"ewenXing","addTime":1584084481},{"logID":5,"borrowID":2,"money":"500.00","moneyAccountID":1,"accountName":"账户1","accountType":2,"dataTime":1584084260,"dataUserID":1,"userName":"ewenXing","addTime":1584084553}]
             */

            private String borrowID;
            private String companyID;
            private String shopID;
            private int dataType;
            private String orderNum;
            private String money;
            private String stillNeed;
            private double hasDone;
            private String moneyAccountID;
            private String accountName;
            private int accountType;
            private String borrowUser;
            private long dataTime;
            private String dataUserID;
            private String dataUserName;
            private String remark;
            private int isFinished;
            private long addTime;
           // private List<HandleLogResponse> handleLog;

            public String getBorrowID() {
                return borrowID;
            }

            public void setBorrowID(String borrowID) {
                this.borrowID = borrowID;
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

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getStillNeed() {
                return stillNeed;
            }

            public void setStillNeed(String stillNeed) {
                this.stillNeed = stillNeed;
            }

            public double getHasDone() {
                return hasDone;
            }

            public void setHasDone(double hasDone) {
                this.hasDone = hasDone;
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

            public String getBorrowUser() {
                return borrowUser;
            }

            public void setBorrowUser(String borrowUser) {
                this.borrowUser = borrowUser;
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

            public int getIsFinished() {
                return isFinished;
            }

            public void setIsFinished(int isFinished) {
                this.isFinished = isFinished;
            }

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

//            public List<HandleLogResponse> getHandleLog() {
//                return handleLog;
//            }
//
//            public void setHandleLog(List<HandleLogResponse> handleLog) {
//                this.handleLog = handleLog;
//            }

            public static class HandleLogResponse {
                /**
                 * logID : 3
                 * borrowID : 2
                 * money : 100.00
                 * moneyAccountID : 1
                 * accountName : 账户1
                 * accountType : 2
                 * dataTime : 1584084260
                 * dataUserID : 1
                 * userName : ewenXing
                 * addTime : 1584084443
                 */

                private String logID;
                private String borrowID;
                private String money;
                private String moneyAccountID;
                private String accountName;
                private int accountType;
                private long dataTime;
                private String dataUserID;
                private String userName;
                private long addTime;

                public String getLogID() {
                    return logID;
                }

                public void setLogID(String logID) {
                    this.logID = logID;
                }

                public String getBorrowID() {
                    return borrowID;
                }

                public void setBorrowID(String borrowID) {
                    this.borrowID = borrowID;
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

                public String getUserName() {
                    return userName;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }

                public long getAddTime() {
                    return addTime;
                }

                public void setAddTime(long addTime) {
                    this.addTime = addTime;
                }
            }
        }
    }
}
