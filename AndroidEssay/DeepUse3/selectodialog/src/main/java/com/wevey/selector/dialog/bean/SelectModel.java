package com.wevey.selector.dialog.bean;

import java.util.List;

public class SelectModel {

    private List<FollowStatusBean> followStatus;
    private List<CustomerLevelBean> customerLevel;
    private List<CustomerSourceBean> customerSource;
    private List<CommunicationTypeBean> communicationType;

    public List<FollowStatusBean> getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(List<FollowStatusBean> followStatus) {
        this.followStatus = followStatus;
    }

    public List<CustomerLevelBean> getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(List<CustomerLevelBean> customerLevel) {
        this.customerLevel = customerLevel;
    }

    public List<CustomerSourceBean> getCustomerSource() {
        return customerSource;
    }

    public void setCustomerSource(List<CustomerSourceBean> customerSource) {
        this.customerSource = customerSource;
    }

    public List<CommunicationTypeBean> getCommunicationType() {
        return communicationType;
    }

    public void setCommunicationType(List<CommunicationTypeBean> communicationType) {
        this.communicationType = communicationType;
    }

    public static class FollowStatusBean {
        /**
         * data_id : 1
         * name : 意向中
         */

        private int data_id;
        private String name;
        private Boolean select = false;

        public Boolean getSelect() {
            return select;
        }

        public void setSelect(Boolean select) {
            this.select = select;
        }

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
    }

    public static class CustomerLevelBean {
        /**
         * data_id : 4
         * levelName : 一星级
         */

        private int data_id;
        private String levelName;
        private Boolean select = false;

        public Boolean getSelect() {
            return select;
        }

        public void setSelect(Boolean select) {
            this.select = select;
        }

        public int getData_id() {
            return data_id;
        }

        public void setData_id(int data_id) {
            this.data_id = data_id;
        }

        public String getLevelName() {
            return levelName;
        }

        public void setLevelName(String levelName) {
            this.levelName = levelName;
        }
    }

    public static class CustomerSourceBean {
        /**
         * data_id : 1
         * name : 扫楼
         */

        private int data_id;
        private String name;

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
    }

    public static class CommunicationTypeBean {
        /**
         * data_id : 1
         * name : 微信沟通
         */

        private int data_id;
        private String name;

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
    }
}
