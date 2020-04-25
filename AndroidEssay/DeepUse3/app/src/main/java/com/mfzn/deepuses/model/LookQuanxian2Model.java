package com.mfzn.deepuses.model;

public class LookQuanxian2Model {

    /**
     * companyModule : {"afterSale":{"data_id":1,"companyID":1,"proID":2,"moduleType":1,"status":2,"startTime":1575534370,"endTime":1576830370,"leftDays":14},"process":{"data_id":2,"companyID":1,"proID":2,"moduleType":2,"status":2,"startTime":1575534370,"endTime":1576830370,"leftDays":14},"purchaseSaleStock":{"data_id":3,"companyID":1,"proID":2,"moduleType":3,"status":2,"startTime":1575534370,"endTime":1576830370,"leftDays":14}}
     * projectModule : {"afterSale":{"data_id":1,"companyID":1,"proID":2,"moduleType":1,"status":2,"startTime":1575534370,"endTime":1576830370,"leftDays":14},"process":{"data_id":2,"companyID":1,"proID":2,"moduleType":2,"status":2,"startTime":1575534370,"endTime":1576830370,"leftDays":14},"purchaseSaleStock":{"data_id":3,"companyID":1,"proID":2,"moduleType":3,"status":2,"startTime":1575534370,"endTime":1576830370,"leftDays":14}}
     */

    private CompanyModuleBean companyModule;

    public CompanyModuleBean getCompanyModule() {
        return companyModule;
    }

    public void setCompanyModule(CompanyModuleBean companyModule) {
        this.companyModule = companyModule;
    }

    public static class CompanyModuleBean {
        /**
         * afterSale : {"data_id":1,"companyID":1,"proID":2,"moduleType":1,"status":2,"startTime":1575534370,"endTime":1576830370,"leftDays":14}
         * process : {"data_id":2,"companyID":1,"proID":2,"moduleType":2,"status":2,"startTime":1575534370,"endTime":1576830370,"leftDays":14}
         * purchaseSaleStock : {"data_id":3,"companyID":1,"proID":2,"moduleType":3,"status":2,"startTime":1575534370,"endTime":1576830370,"leftDays":14}
         */

        private AfterSaleBean afterSale;
        private ProcessBean process;
        private PurchaseSaleStockBean purchaseSaleStock;

        public AfterSaleBean getAfterSale() {
            return afterSale;
        }

        public void setAfterSale(AfterSaleBean afterSale) {
            this.afterSale = afterSale;
        }

        public ProcessBean getProcess() {
            return process;
        }

        public void setProcess(ProcessBean process) {
            this.process = process;
        }

        public PurchaseSaleStockBean getPurchaseSaleStock() {
            return purchaseSaleStock;
        }

        public void setPurchaseSaleStock(PurchaseSaleStockBean purchaseSaleStock) {
            this.purchaseSaleStock = purchaseSaleStock;
        }

        public static class AfterSaleBean {
            /**
             * data_id : 1
             * companyID : 1
             * proID : 2
             * moduleType : 1
             * status : 2
             * startTime : 1575534370
             * endTime : 1576830370
             * leftDays : 14
             */

            private int data_id;
            private int companyID;
            private int proID;
            private int moduleType;
            private int status;
            private String startTime;
            private String endTime;
            private int leftDays;

            public int getData_id() {
                return data_id;
            }

            public void setData_id(int data_id) {
                this.data_id = data_id;
            }

            public int getCompanyID() {
                return companyID;
            }

            public void setCompanyID(int companyID) {
                this.companyID = companyID;
            }

            public int getProID() {
                return proID;
            }

            public void setProID(int proID) {
                this.proID = proID;
            }

            public int getModuleType() {
                return moduleType;
            }

            public void setModuleType(int moduleType) {
                this.moduleType = moduleType;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public int getLeftDays() {
                return leftDays;
            }

            public void setLeftDays(int leftDays) {
                this.leftDays = leftDays;
            }
        }

        public static class ProcessBean {
            /**
             * data_id : 2
             * companyID : 1
             * proID : 2
             * moduleType : 2
             * status : 2
             * startTime : 1575534370
             * endTime : 1576830370
             * leftDays : 14
             */

            private int data_id;
            private int companyID;
            private int proID;
            private int moduleType;
            private int status;
            private int startTime;
            private int endTime;
            private int leftDays;

            public int getData_id() {
                return data_id;
            }

            public void setData_id(int data_id) {
                this.data_id = data_id;
            }

            public int getCompanyID() {
                return companyID;
            }

            public void setCompanyID(int companyID) {
                this.companyID = companyID;
            }

            public int getProID() {
                return proID;
            }

            public void setProID(int proID) {
                this.proID = proID;
            }

            public int getModuleType() {
                return moduleType;
            }

            public void setModuleType(int moduleType) {
                this.moduleType = moduleType;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getStartTime() {
                return startTime;
            }

            public void setStartTime(int startTime) {
                this.startTime = startTime;
            }

            public int getEndTime() {
                return endTime;
            }

            public void setEndTime(int endTime) {
                this.endTime = endTime;
            }

            public int getLeftDays() {
                return leftDays;
            }

            public void setLeftDays(int leftDays) {
                this.leftDays = leftDays;
            }
        }

        public static class PurchaseSaleStockBean {
            /**
             * data_id : 3
             * companyID : 1
             * proID : 2
             * moduleType : 3
             * status : 2
             * startTime : 1575534370
             * endTime : 1576830370
             * leftDays : 14
             */

            private int data_id;
            private int companyID;
            private int proID;
            private int moduleType;
            private int status;
            private int startTime;
            private int endTime;
            private int leftDays;

            public int getData_id() {
                return data_id;
            }

            public void setData_id(int data_id) {
                this.data_id = data_id;
            }

            public int getCompanyID() {
                return companyID;
            }

            public void setCompanyID(int companyID) {
                this.companyID = companyID;
            }

            public int getProID() {
                return proID;
            }

            public void setProID(int proID) {
                this.proID = proID;
            }

            public int getModuleType() {
                return moduleType;
            }

            public void setModuleType(int moduleType) {
                this.moduleType = moduleType;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getStartTime() {
                return startTime;
            }

            public void setStartTime(int startTime) {
                this.startTime = startTime;
            }

            public int getEndTime() {
                return endTime;
            }

            public void setEndTime(int endTime) {
                this.endTime = endTime;
            }

            public int getLeftDays() {
                return leftDays;
            }

            public void setLeftDays(int leftDays) {
                this.leftDays = leftDays;
            }
        }
    }
}
