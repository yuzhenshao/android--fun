package com.mfzn.deepuses.bean.response.capital;

import java.util.List;

public class MoneyAccountFinancialLogListResponse {

    /**
     * leftMoney : 12600.00
     * accountType : 2
     * list : {"total":17,"per_page":10,"current_page":1,"last_page":2,"data":[{"logID":21,"showName":"借贷：归还借贷人\"张三\"款项","orderType":15,"orderID":1,"type":2,"money":"100.00","nowMoney":"12600.00","dataUserID":1,"addTime":1589336805,"orderTypeName":"借入借出","orderNum":"RWIJ_JD_00000001","dataUserName":"ewenXing"},{"logID":20,"showName":"借贷：借贷人\"张三\"","orderType":15,"orderID":1,"type":1,"money":"800.00","nowMoney":"12700.00","dataUserID":1,"addTime":1589336429,"orderTypeName":"借入借出","orderNum":"RWIJ_JD_00000001","dataUserName":"ewenXing"},{"logID":19,"showName":"新增收支记录","orderType":14,"orderID":1,"type":2,"money":"100.00","nowMoney":"11900.00","dataUserID":1,"addTime":1589164032,"orderTypeName":"收支单","orderNum":"RWIJ_SZ_00000001","dataUserName":"ewenXing"},{"logID":18,"showName":"李四","orderType":13,"orderID":7,"type":1,"money":"1000.00","nowMoney":"12000.00","dataUserID":1,"addTime":1587796795,"orderTypeName":"零售退货单","orderNum":"RWIJ_LSTH_00000004","dataUserName":"ewenXing"},{"logID":17,"showName":"李四","orderType":13,"orderID":7,"type":2,"money":"1000.00","nowMoney":"11000.00","dataUserID":1,"addTime":1587643307,"orderTypeName":"零售退货单","orderNum":"RWIJ_LSTH_00000004","dataUserName":"ewenXing"},{"logID":16,"showName":"李四","orderType":5,"orderID":6,"type":2,"money":"1000.00","nowMoney":"12000.00","dataUserID":1,"addTime":1587635897,"orderTypeName":"零售单","orderNum":"RWIJ_LS_00000003","dataUserName":"ewenXing"},{"logID":14,"showName":"李四","orderType":5,"orderID":6,"type":1,"money":"1000.00","nowMoney":"13000.00","dataUserID":1,"addTime":1587632108,"orderTypeName":"零售单","orderNum":"RWIJ_LS_00000003","dataUserName":"ewenXing"},{"logID":13,"showName":"李四","orderType":5,"orderID":5,"type":1,"money":"1000.00","nowMoney":"12000.00","dataUserID":1,"addTime":1587632031,"orderTypeName":"零售单","orderNum":"RWIJ_LS_00000002","dataUserName":"ewenXing"},{"logID":12,"showName":"李四","orderType":5,"orderID":4,"type":1,"money":"1000.00","nowMoney":"11000.00","dataUserID":1,"addTime":1587623380,"orderTypeName":"零售单","orderNum":"RWIJ_LS_00000001","dataUserName":"ewenXing"},{"logID":10,"showName":"账户1","orderType":17,"orderID":0,"type":2,"money":"500.00","nowMoney":"10000.00","dataUserID":1,"addTime":1587138803,"orderTypeName":"转账","orderNum":"暂无单据","dataUserName":"ewenXing"}]}
     */

    private String leftMoney;
    private int accountType;
    private ListBean list;

    public String getLeftMoney() {
        return leftMoney;
    }

    public void setLeftMoney(String leftMoney) {
        this.leftMoney = leftMoney;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * total : 17
         * per_page : 10
         * current_page : 1
         * last_page : 2
         * data : [{"logID":21,"showName":"借贷：归还借贷人\"张三\"款项","orderType":15,"orderID":1,"type":2,"money":"100.00","nowMoney":"12600.00","dataUserID":1,"addTime":1589336805,"orderTypeName":"借入借出","orderNum":"RWIJ_JD_00000001","dataUserName":"ewenXing"},{"logID":20,"showName":"借贷：借贷人\"张三\"","orderType":15,"orderID":1,"type":1,"money":"800.00","nowMoney":"12700.00","dataUserID":1,"addTime":1589336429,"orderTypeName":"借入借出","orderNum":"RWIJ_JD_00000001","dataUserName":"ewenXing"},{"logID":19,"showName":"新增收支记录","orderType":14,"orderID":1,"type":2,"money":"100.00","nowMoney":"11900.00","dataUserID":1,"addTime":1589164032,"orderTypeName":"收支单","orderNum":"RWIJ_SZ_00000001","dataUserName":"ewenXing"},{"logID":18,"showName":"李四","orderType":13,"orderID":7,"type":1,"money":"1000.00","nowMoney":"12000.00","dataUserID":1,"addTime":1587796795,"orderTypeName":"零售退货单","orderNum":"RWIJ_LSTH_00000004","dataUserName":"ewenXing"},{"logID":17,"showName":"李四","orderType":13,"orderID":7,"type":2,"money":"1000.00","nowMoney":"11000.00","dataUserID":1,"addTime":1587643307,"orderTypeName":"零售退货单","orderNum":"RWIJ_LSTH_00000004","dataUserName":"ewenXing"},{"logID":16,"showName":"李四","orderType":5,"orderID":6,"type":2,"money":"1000.00","nowMoney":"12000.00","dataUserID":1,"addTime":1587635897,"orderTypeName":"零售单","orderNum":"RWIJ_LS_00000003","dataUserName":"ewenXing"},{"logID":14,"showName":"李四","orderType":5,"orderID":6,"type":1,"money":"1000.00","nowMoney":"13000.00","dataUserID":1,"addTime":1587632108,"orderTypeName":"零售单","orderNum":"RWIJ_LS_00000003","dataUserName":"ewenXing"},{"logID":13,"showName":"李四","orderType":5,"orderID":5,"type":1,"money":"1000.00","nowMoney":"12000.00","dataUserID":1,"addTime":1587632031,"orderTypeName":"零售单","orderNum":"RWIJ_LS_00000002","dataUserName":"ewenXing"},{"logID":12,"showName":"李四","orderType":5,"orderID":4,"type":1,"money":"1000.00","nowMoney":"11000.00","dataUserID":1,"addTime":1587623380,"orderTypeName":"零售单","orderNum":"RWIJ_LS_00000001","dataUserName":"ewenXing"},{"logID":10,"showName":"账户1","orderType":17,"orderID":0,"type":2,"money":"500.00","nowMoney":"10000.00","dataUserID":1,"addTime":1587138803,"orderTypeName":"转账","orderNum":"暂无单据","dataUserName":"ewenXing"}]
         */

        private int total;
        private int per_page;
        private int current_page;
        private int last_page;
        private List<MoneyAccountFinancialLogResponse> data;

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

        public List<MoneyAccountFinancialLogResponse> getData() {
            return data;
        }

        public void setData(List<MoneyAccountFinancialLogResponse> data) {
            this.data = data;
        }

        public static class MoneyAccountFinancialLogResponse {
            /**
             * logID : 21
             * showName : 借贷：归还借贷人"张三"款项
             * orderType : 15
             * orderID : 1
             * type : 2
             * money : 100.00
             * nowMoney : 12600.00
             * dataUserID : 1
             * addTime : 1589336805
             * orderTypeName : 借入借出
             * orderNum : RWIJ_JD_00000001
             * dataUserName : ewenXing
             */

            private String logID;
            private String showName;
            private String orderType;
            private String orderID;
            private int type;
            private String money;
            private String nowMoney;
            private String dataUserID;
            private long addTime;
            private String orderTypeName;
            private String orderNum;
            private String dataUserName;

            public String getLogID() {
                return logID;
            }

            public void setLogID(String logID) {
                this.logID = logID;
            }

            public String getShowName() {
                return showName;
            }

            public void setShowName(String showName) {
                this.showName = showName;
            }

            public String getOrderType() {
                return orderType;
            }

            public void setOrderType(String orderType) {
                this.orderType = orderType;
            }

            public String getOrderID() {
                return orderID;
            }

            public void setOrderID(String orderID) {
                this.orderID = orderID;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getNowMoney() {
                return nowMoney;
            }

            public void setNowMoney(String nowMoney) {
                this.nowMoney = nowMoney;
            }

            public String getDataUserID() {
                return dataUserID;
            }

            public void setDataUserID(String dataUserID) {
                this.dataUserID = dataUserID;
            }

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public String getOrderTypeName() {
                return orderTypeName;
            }

            public void setOrderTypeName(String orderTypeName) {
                this.orderTypeName = orderTypeName;
            }

            public String getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(String orderNum) {
                this.orderNum = orderNum;
            }

            public String getDataUserName() {
                return dataUserName;
            }

            public void setDataUserName(String dataUserName) {
                this.dataUserName = dataUserName;
            }
        }
    }
}
