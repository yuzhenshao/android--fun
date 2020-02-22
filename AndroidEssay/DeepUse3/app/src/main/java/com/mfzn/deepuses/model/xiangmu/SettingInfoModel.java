package com.mfzn.deepuses.model.xiangmu;

import java.util.List;

public class SettingInfoModel {

    /**
     * proId : 1
     * qualityTime : 223
     * qualityBegin : 质保起始日期
     * qualityEnd : 质保截止日期
     * nextVisitTime : 下次回访时间
     * visitSpace : 32
     * qualityRing : 0
     * wbRing : 0
     * visitRing : 0
     * ybEnd : 延保截止日期
     * createUserId : 312
     * addTime : 1564538954
     * updateUserId : 312
     * updateTime : 1564539177
     * is_del : 0
     * orderNum : 0
     * wbset : [{"proId":1,"title":"维保项目名称","nextDate":"下次维保时间","spaceDate":"维保间隔，天","createUserId":312,"addTime":1564539820,"updateUserId":312,"updateTime":1564540000,"is_del":0,"orderNum":0,"data_id":2,"data_en_id":"FXvddSSfk"}]
     * data_id : 2
     * data_en_id : FXvddSSfk
     */

    private int proId;
    private int qualityTime;
    private String qualityBegin;
    private String qualityEnd;
    private String nextVisitTime;
    private int visitSpace;
    private int qualityRing;
    private int wbRing;
    private int visitRing;
    private String ybEnd;
    private int createUserId;
    private int addTime;
    private int updateUserId;
    private int updateTime;
    private int is_del;
    private int orderNum;
    private int data_id;
    private String data_en_id;
    private List<WbsetBean> wbset;
    private int ybTime;
    private int ybRing;

    public int getYbRing() {
        return ybRing;
    }

    public void setYbRing(int ybRing) {
        this.ybRing = ybRing;
    }

    public int getYbTime() {
        return ybTime;
    }

    public void setYbTime(int ybTime) {
        this.ybTime = ybTime;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getQualityTime() {
        return qualityTime;
    }

    public void setQualityTime(int qualityTime) {
        this.qualityTime = qualityTime;
    }

    public String getQualityBegin() {
        return qualityBegin;
    }

    public void setQualityBegin(String qualityBegin) {
        this.qualityBegin = qualityBegin;
    }

    public String getQualityEnd() {
        return qualityEnd;
    }

    public void setQualityEnd(String qualityEnd) {
        this.qualityEnd = qualityEnd;
    }

    public String getNextVisitTime() {
        return nextVisitTime;
    }

    public void setNextVisitTime(String nextVisitTime) {
        this.nextVisitTime = nextVisitTime;
    }

    public int getVisitSpace() {
        return visitSpace;
    }

    public void setVisitSpace(int visitSpace) {
        this.visitSpace = visitSpace;
    }

    public int getQualityRing() {
        return qualityRing;
    }

    public void setQualityRing(int qualityRing) {
        this.qualityRing = qualityRing;
    }

    public int getWbRing() {
        return wbRing;
    }

    public void setWbRing(int wbRing) {
        this.wbRing = wbRing;
    }

    public int getVisitRing() {
        return visitRing;
    }

    public void setVisitRing(int visitRing) {
        this.visitRing = visitRing;
    }

    public String getYbEnd() {
        return ybEnd;
    }

    public void setYbEnd(String ybEnd) {
        this.ybEnd = ybEnd;
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

    public int getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(int updateUserId) {
        this.updateUserId = updateUserId;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
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

    public List<WbsetBean> getWbset() {
        return wbset;
    }

    public void setWbset(List<WbsetBean> wbset) {
        this.wbset = wbset;
    }

    public static class WbsetBean {
        /**
         * proId : 1
         * title : 维保项目名称
         * nextDate : 下次维保时间
         * spaceDate : 维保间隔，天
         * createUserId : 312
         * addTime : 1564539820
         * updateUserId : 312
         * updateTime : 1564540000
         * is_del : 0
         * orderNum : 0
         * data_id : 2
         * data_en_id : FXvddSSfk
         */

        private int proId;
        private String title;
        private String nextDate;
        private String spaceDate;
        private int createUserId;
        private int addTime;
        private int updateUserId;
        private int updateTime;
        private int is_del;
        private int orderNum;
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

        public String getNextDate() {
            return nextDate;
        }

        public void setNextDate(String nextDate) {
            this.nextDate = nextDate;
        }

        public String getSpaceDate() {
            return spaceDate;
        }

        public void setSpaceDate(String spaceDate) {
            this.spaceDate = spaceDate;
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

        public int getUpdateUserId() {
            return updateUserId;
        }

        public void setUpdateUserId(int updateUserId) {
            this.updateUserId = updateUserId;
        }

        public int getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(int updateTime) {
            this.updateTime = updateTime;
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
