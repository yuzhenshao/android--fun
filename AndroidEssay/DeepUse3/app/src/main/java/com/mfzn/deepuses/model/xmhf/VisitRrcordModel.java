package com.mfzn.deepuses.model.xmhf;

import java.util.List;

public class VisitRrcordModel {

    /**
     * total : 2
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"proId":147,"title":"312","nowDate":"f11","content":"","nextDate":"","createUserId":0,"addTime":0,"is_del":0,"orderNum":0,"u_name":"","data_id":1,"data_en_id":"FahBBddd4"},{"proId":147,"title":"321","nowDate":"fdf","content":"","nextDate":"","createUserId":0,"addTime":0,"is_del":0,"orderNum":0,"u_name":"","data_id":2,"data_en_id":"FXvddSSfk"}]
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
         * proId : 147
         * title : 312
         * nowDate : f11
         * content :
         * nextDate :
         * createUserId : 0
         * addTime : 0
         * is_del : 0
         * orderNum : 0
         * u_name :
         * data_id : 1
         * data_en_id : FahBBddd4
         */

        private int proId;
        private String title;
        private String nowDate;
        private String content;
        private String nextDate;
        private int createUserId;
        private int addTime;
        private int is_del;
        private int orderNum;
        private String u_name;
        private int data_id;
        private String data_en_id;

        public int getProId() {
            return proId;
        }

        public void setProId(int proId) {
            this.proId = proId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNowDate() {
            return nowDate;
        }

        public void setNowDate(String nowDate) {
            this.nowDate = nowDate;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getNextDate() {
            return nextDate;
        }

        public void setNextDate(String nextDate) {
            this.nextDate = nextDate;
        }

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public int getAddTime() {
            return addTime;
        }

        public void setAddTime(int addTime) {
            this.addTime = addTime;
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

        public String getU_name() {
            return u_name;
        }

        public void setU_name(String u_name) {
            this.u_name = u_name;
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
    }
}
