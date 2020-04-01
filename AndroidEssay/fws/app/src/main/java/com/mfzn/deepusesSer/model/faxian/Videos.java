package com.mfzn.deepusesSer.model.faxian;

import java.util.List;

public class Videos {


    /**
     * total : 1
     * per_page : 20
     * current_page : 1
     * last_page : 1
     * data : [{"rowNum":1321,"NewsID":0,"Title":"测试视频","TitleImage":"","Summary":"测试视频","Content":"/uploads/brandImage/11.mp4","ClassName":"","DateAndTime":0,"Source":"","LastPage":0,"newsType":1,"isShow":1,"isDel":0,"addTime":0,"addUser":0,"updateTime":0,"updateuser":0,"categoryType":0,"hits":0,"likeNums":0,"commentsNums":0,"transNums":0,"label":"","type":2}]
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
        private String Title;
        private String videoUrl;
        private String summary;
        private int addTime;
        private int likeCount;
        private int commentCount;
        private int transmitCount;

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getContent() {
            return videoUrl;
        }

        public void setContent(String Content) {
            this.videoUrl = Content;
        }

        public String getSource() {
            return summary;
        }

        public void setSource(String Source) {
            this.summary = Source;
        }

        public int getAddTime() {
            return addTime;
        }

        public void setAddTime(int addTime) {
            this.addTime = addTime;
        }

        public int getLikeNums() {
            return likeCount;
        }

        public void setLikeNums(int likeNums) {
            this.likeCount = likeNums;
        }

        public int getCommentsNums() {
            return commentCount;
        }

        public void setCommentsNums(int commentsNums) {
            this.commentCount = commentsNums;
        }

        public int getTransNums() {
            return transmitCount;
        }

        public void setTransNums(int transNums) {
            this.transmitCount = transNums;
        }

    }
}
