package com.wevey.selector.dialog.bean;

import java.util.List;

public class ObtainProcessModel {

    /**
     * addTime : 0
     * companyID : 0
     * data_en_id : FahBBddd4
     * data_id : 1
     * is_del : 0
     * mainNodes : [{"addTime":0,"data_en_id":"FahBBddd4","data_id":1,"is_del":0,"name":"施工管理","orderNum":0,"type":1,"updateTime":0,"updateUserID":0},{"addTime":0,"data_en_id":"FXvddSSfk","data_id":2,"is_del":0,"name":"衔接配合","orderNum":0,"type":1,"updateTime":0,"updateUserID":0},{"addTime":0,"data_en_id":"F1QaSSf1U","data_id":3,"is_del":0,"name":"安装调试","orderNum":0,"type":1,"updateTime":0,"updateUserID":0},{"addTime":0,"data_en_id":"FQQdSSXXP","data_id":4,"is_del":0,"name":"售后服务","orderNum":0,"type":1,"updateTime":0,"updateUserID":0}]
     * orderNum : 0
     * typeName : 智能家居
     * updateTime : 0
     * updateUserID : 0
     * userID : 0
     */

    private int addTime;
    private int companyID;
    private String data_en_id;
    private int data_id;
    private int is_del;
    private int orderNum;
    private String typeName;
    private int updateTime;
    private int updateUserID;
    private int userID;
    private List<MainNodesBean> mainNodes;

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getData_en_id() {
        return data_en_id;
    }

    public void setData_en_id(String data_en_id) {
        this.data_en_id = data_en_id;
    }

    public int getData_id() {
        return data_id;
    }

    public void setData_id(int data_id) {
        this.data_id = data_id;
    }

    public int getIs_del() {
        return is_del;
    }

    public void setIs_del(int is_del) {
        this.is_del = is_del;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }

    public int getUpdateUserID() {
        return updateUserID;
    }

    public void setUpdateUserID(int updateUserID) {
        this.updateUserID = updateUserID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public List<MainNodesBean> getMainNodes() {
        return mainNodes;
    }

    public void setMainNodes(List<MainNodesBean> mainNodes) {
        this.mainNodes = mainNodes;
    }

    public static class MainNodesBean {
        /**
         * addTime : 0
         * data_en_id : FahBBddd4
         * data_id : 1
         * is_del : 0
         * name : 施工管理
         * orderNum : 0
         * type : 1
         * updateTime : 0
         * updateUserID : 0
         */

        private int addTime;
        private String data_en_id;
        private int data_id;
        private int is_del;
        private String name;
        private int orderNum;
        private int type;
        private int updateTime;
        private int updateUserID;
        private boolean isChoise = false;

        public boolean getIsChoise() {
            return isChoise;
        }

        public void setIsChoise(boolean isChoise) {
            this.isChoise = isChoise;
        }

        public int getAddTime() {
            return addTime;
        }

        public void setAddTime(int addTime) {
            this.addTime = addTime;
        }

        public String getData_en_id() {
            return data_en_id;
        }

        public void setData_en_id(String data_en_id) {
            this.data_en_id = data_en_id;
        }

        public int getData_id() {
            return data_id;
        }

        public void setData_id(int data_id) {
            this.data_id = data_id;
        }

        public int getIs_del() {
            return is_del;
        }

        public void setIs_del(int is_del) {
            this.is_del = is_del;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(int updateTime) {
            this.updateTime = updateTime;
        }

        public int getUpdateUserID() {
            return updateUserID;
        }

        public void setUpdateUserID(int updateUserID) {
            this.updateUserID = updateUserID;
        }
    }
}
