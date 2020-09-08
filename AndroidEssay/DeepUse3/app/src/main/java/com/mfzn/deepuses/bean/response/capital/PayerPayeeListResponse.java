package com.mfzn.deepuses.bean.response.capital;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PayerPayeeListResponse {
    /**
     * payeeNum : 1
     * sumMoney : 20000
     * list : {"total":1,"per_page":10,"current_page":1,"last_page":1,"data":[{"customerOrSupplier":2,"customerOrSupplierID":1,"payee":"供应商1","payeeSumMoney":20000,"chargePerson":"王先生","chargePersonPhone":"15311111111"}]}
     */

    private double payeeNum;
    private double sumMoney;
    private ListBean list;

    public double getPayeeNum() {
        return payeeNum;
    }

    public void setPayeeNum(double payeeNum) {
        this.payeeNum = payeeNum;
    }

    public double getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(double sumMoney) {
        this.sumMoney = sumMoney;
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
         * data : [{"customerOrSupplier":2,"customerOrSupplierID":1,"payee":"供应商1","payeeSumMoney":20000,"chargePerson":"王先生","chargePersonPhone":"15311111111"}]
         */

        private int total;
        private int per_page;
        private int current_page;
        private int last_page;
        private List<PayerPayeeResponse> data;

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

        public List<PayerPayeeResponse> getData() {
            return data;
        }

        public void setData(List<PayerPayeeResponse> data) {
            this.data = data;
        }

        public static class PayerPayeeResponse {
            /**
             * customerOrSupplier : 2
             * customerOrSupplierID : 1
             * payee : 供应商1
             * payeeSumMoney : 20000
             * chargePerson : 王先生
             * chargePersonPhone : 15311111111
             */

            private String customerOrSupplier;
            private String customerOrSupplierID;
            @SerializedName(value = "payee", alternate = {"payer"})
            private String payee;

            @SerializedName(value = "payeeSumMoney", alternate = {"payerSumMoney"})
            private String payeeSumMoney;
            private String chargePerson;
            private String chargePersonPhone;

            public String getCustomerOrSupplier() {
                return customerOrSupplier;
            }

            public void setCustomerOrSupplier(String customerOrSupplier) {
                this.customerOrSupplier = customerOrSupplier;
            }

            public String getCustomerOrSupplierID() {
                return customerOrSupplierID;
            }

            public void setCustomerOrSupplierID(String customerOrSupplierID) {
                this.customerOrSupplierID = customerOrSupplierID;
            }

            public String getPayee() {
                return payee;
            }

            public void setPayee(String payee) {
                this.payee = payee;
            }

            public String getPayeeSumMoney() {
                return payeeSumMoney;
            }

            public void setPayeeSumMoney(String payeeSumMoney) {
                this.payeeSumMoney = payeeSumMoney;
            }

            public String getChargePerson() {
                return chargePerson;
            }

            public void setChargePerson(String chargePerson) {
                this.chargePerson = chargePerson;
            }

            public String getChargePersonPhone() {
                return chargePersonPhone;
            }

            public void setChargePersonPhone(String chargePersonPhone) {
                this.chargePersonPhone = chargePersonPhone;
            }
        }
    }
}
