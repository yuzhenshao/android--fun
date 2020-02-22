package com.mfzn.deepuses.model.xx;

import java.util.List;

public class MsgMainModel {

    /**
     * data : [{"type":1,"notReadCount":0,"text":"\"邵阳阳\"已经成功创建项目【白金汉宫27幢701室】","time":"2019/11/15"},{"type":2,"notReadCount":0,"text":"\"测\"已被邢康取消任命普通管理员","time":"2019/11/14"},{"type":3,"notReadCount":0,"text":"暂无消息","time":""},{"type":4,"notReadCount":0,"text":"暂无新申请","time":""},{"type":5,"notReadCount":0,"text":"暂无新申请","time":""}]
     * showRedPoint : 0
     */

    private int showRedPoint;
    private List<DataBean> data;

    public int getShowRedPoint() {
        return showRedPoint;
    }

    public void setShowRedPoint(int showRedPoint) {
        this.showRedPoint = showRedPoint;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * type : 1
         * notReadCount : 0
         * text : "邵阳阳"已经成功创建项目【白金汉宫27幢701室】
         * time : 2019/11/15
         */

        private int type;
        private int notReadCount;
        private String text;
        private String time;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getNotReadCount() {
            return notReadCount;
        }

        public void setNotReadCount(int notReadCount) {
            this.notReadCount = notReadCount;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
