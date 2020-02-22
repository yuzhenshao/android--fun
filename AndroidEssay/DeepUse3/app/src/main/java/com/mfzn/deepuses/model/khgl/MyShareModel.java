package com.mfzn.deepuses.model.khgl;

import java.util.List;

public class MyShareModel {

    /**
     * total : 1
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"data_id":14,"companyID":14,"toCompany":1,"addTime":1579080328,"isHandle":0,"companyName":"冲浪网络1","shortName":"冲浪网络1","logo":"/uploads/brandImage/323/20191227133848-holder.png","companyLevel":1,"levelName":"普通会员","customerInfo":[{"shareRecordID":14,"customerID":28,"customerName":"胡爱国派","customerPhone":"19941640525","customerLevelID":6,"customerLevelName":"三星级","customerPros":[{"pro_name":"御城166-A","latitude":31.73331,"longitude":119.94744,"areaName":"江苏省-常州市-武进区","detailAddress":"湖塘镇万达广场B2栋13楼常州缯彩纺织品有限公司"}],"isAccept":0,"isDel":0,"addTime":1579080328,"updateTime":0,"data_id":7,"data_en_id":"FvBhh1XXe"}]}]
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
         * data_id : 14
         * companyID : 14
         * toCompany : 1
         * addTime : 1579080328
         * isHandle : 0
         * companyName : 冲浪网络1
         * shortName : 冲浪网络1
         * logo : /uploads/brandImage/323/20191227133848-holder.png
         * companyLevel : 1
         * levelName : 普通会员
         * customerInfo : [{"shareRecordID":14,"customerID":28,"customerName":"胡爱国派","customerPhone":"19941640525","customerLevelID":6,"customerLevelName":"三星级","customerPros":[{"pro_name":"御城166-A","latitude":31.73331,"longitude":119.94744,"areaName":"江苏省-常州市-武进区","detailAddress":"湖塘镇万达广场B2栋13楼常州缯彩纺织品有限公司"}],"isAccept":0,"isDel":0,"addTime":1579080328,"updateTime":0,"data_id":7,"data_en_id":"FvBhh1XXe"}]
         */

        private int data_id;
        private int companyID;
        private int toCompany;
        private int addTime;
        private int isHandle;
        private String companyName;
        private String shortName;
        private String logo;
        private int companyLevel;
        private String levelName;
        private List<CustomerInfoBean> customerInfo;

        public Boolean getShowType() {
            return showType;
        }

        public void setShowType(Boolean showType) {
            this.showType = showType;
        }

        private Boolean showType = false;

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

        public int getToCompany() {
            return toCompany;
        }

        public void setToCompany(int toCompany) {
            this.toCompany = toCompany;
        }

        public int getAddTime() {
            return addTime;
        }

        public void setAddTime(int addTime) {
            this.addTime = addTime;
        }

        public int getIsHandle() {
            return isHandle;
        }

        public void setIsHandle(int isHandle) {
            this.isHandle = isHandle;
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

        public List<CustomerInfoBean> getCustomerInfo() {
            return customerInfo;
        }

        public void setCustomerInfo(List<CustomerInfoBean> customerInfo) {
            this.customerInfo = customerInfo;
        }

        public static class CustomerInfoBean {
            /**
             * shareRecordID : 14
             * customerID : 28
             * customerName : 胡爱国派
             * customerPhone : 19941640525
             * customerLevelID : 6
             * customerLevelName : 三星级
             * customerPros : [{"pro_name":"御城166-A","latitude":31.73331,"longitude":119.94744,"areaName":"江苏省-常州市-武进区","detailAddress":"湖塘镇万达广场B2栋13楼常州缯彩纺织品有限公司"}]
             * isAccept : 0
             * isDel : 0
             * addTime : 1579080328
             * updateTime : 0
             * data_id : 7
             * data_en_id : FvBhh1XXe
             */

            private int shareRecordID;
            private int customerID;
            private String customerName;
            private String customerPhone;
            private int customerLevelID;
            private String customerLevelName;
            private int isAccept;
            private int isDel;
            private int addTime;
            private int updateTime;
            private int data_id;
            private String data_en_id;
            private List<CustomerProsBean> customerPros;

            public boolean getSelectType() {
                return selectType;
            }

            public void setSelectType(boolean selectType) {
                this.selectType = selectType;
            }

            private boolean selectType = false;

            public int getShareRecordID() {
                return shareRecordID;
            }

            public void setShareRecordID(int shareRecordID) {
                this.shareRecordID = shareRecordID;
            }

            public int getCustomerID() {
                return customerID;
            }

            public void setCustomerID(int customerID) {
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

            public int getCustomerLevelID() {
                return customerLevelID;
            }

            public void setCustomerLevelID(int customerLevelID) {
                this.customerLevelID = customerLevelID;
            }

            public String getCustomerLevelName() {
                return customerLevelName;
            }

            public void setCustomerLevelName(String customerLevelName) {
                this.customerLevelName = customerLevelName;
            }

            public int getIsAccept() {
                return isAccept;
            }

            public void setIsAccept(int isAccept) {
                this.isAccept = isAccept;
            }

            public int getIsDel() {
                return isDel;
            }

            public void setIsDel(int isDel) {
                this.isDel = isDel;
            }

            public int getAddTime() {
                return addTime;
            }

            public void setAddTime(int addTime) {
                this.addTime = addTime;
            }

            public int getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(int updateTime) {
                this.updateTime = updateTime;
            }

            public int getData_id() {
                return data_id;
            }

            public void setData_id(int data_id) {
                this.data_id = data_id;
            }

            public String getData_en_id() {
                return data_en_id;
            }

            public void setData_en_id(String data_en_id) {
                this.data_en_id = data_en_id;
            }

            public List<CustomerProsBean> getCustomerPros() {
                return customerPros;
            }

            public void setCustomerPros(List<CustomerProsBean> customerPros) {
                this.customerPros = customerPros;
            }

            public static class CustomerProsBean {
                /**
                 * pro_name : 御城166-A
                 * latitude : 31.73331
                 * longitude : 119.94744
                 * areaName : 江苏省-常州市-武进区
                 * detailAddress : 湖塘镇万达广场B2栋13楼常州缯彩纺织品有限公司
                 */

                private String pro_name;
                private double latitude;
                private double longitude;
                private String areaName;
                private String detailAddress;

                public String getPro_name() {
                    return pro_name;
                }

                public void setPro_name(String pro_name) {
                    this.pro_name = pro_name;
                }

                public double getLatitude() {
                    return latitude;
                }

                public void setLatitude(double latitude) {
                    this.latitude = latitude;
                }

                public double getLongitude() {
                    return longitude;
                }

                public void setLongitude(double longitude) {
                    this.longitude = longitude;
                }

                public String getAreaName() {
                    return areaName;
                }

                public void setAreaName(String areaName) {
                    this.areaName = areaName;
                }

                public String getDetailAddress() {
                    return detailAddress;
                }

                public void setDetailAddress(String detailAddress) {
                    this.detailAddress = detailAddress;
                }
            }
        }
    }
}
