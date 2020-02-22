package com.mfzn.deepuses.model.faxian;

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
        /**
         * rowNum : 1321
         * NewsID : 0
         * Title : 测试视频
         * TitleImage :
         * Summary : 测试视频
         * Content : /uploads/brandImage/11.mp4
         * ClassName :
         * DateAndTime : 0
         * Source :
         * LastPage : 0
         * newsType : 1
         * isShow : 1
         * isDel : 0
         * addTime : 0
         * addUser : 0
         * updateTime : 0
         * updateuser : 0
         * categoryType : 0
         * hits : 0
         * likeNums : 0
         * commentsNums : 0
         * transNums : 0
         * label :
         * type : 2
         */

        private int rowNum;
        private int NewsID;
        private String Title;
        private String TitleImage;
        private String Summary;
        private String Content;
        private String ClassName;
        private int DateAndTime;
        private String Source;
        private int LastPage;
        private int newsType;
        private int isShow;
        private int isDel;
        private int addTime;
        private int addUser;
        private int updateTime;
        private int updateuser;
        private int categoryType;
        private int hits;
        private int likeNums;
        private int commentsNums;
        private int transNums;
        private String label;
        private int type;

        public int getRowNum() {
            return rowNum;
        }

        public void setRowNum(int rowNum) {
            this.rowNum = rowNum;
        }

        public int getNewsID() {
            return NewsID;
        }

        public void setNewsID(int NewsID) {
            this.NewsID = NewsID;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getTitleImage() {
            return TitleImage;
        }

        public void setTitleImage(String TitleImage) {
            this.TitleImage = TitleImage;
        }

        public String getSummary() {
            return Summary;
        }

        public void setSummary(String Summary) {
            this.Summary = Summary;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public String getClassName() {
            return ClassName;
        }

        public void setClassName(String ClassName) {
            this.ClassName = ClassName;
        }

        public int getDateAndTime() {
            return DateAndTime;
        }

        public void setDateAndTime(int DateAndTime) {
            this.DateAndTime = DateAndTime;
        }

        public String getSource() {
            return Source;
        }

        public void setSource(String Source) {
            this.Source = Source;
        }

        public int getLastPage() {
            return LastPage;
        }

        public void setLastPage(int LastPage) {
            this.LastPage = LastPage;
        }

        public int getNewsType() {
            return newsType;
        }

        public void setNewsType(int newsType) {
            this.newsType = newsType;
        }

        public int getIsShow() {
            return isShow;
        }

        public void setIsShow(int isShow) {
            this.isShow = isShow;
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

        public int getAddUser() {
            return addUser;
        }

        public void setAddUser(int addUser) {
            this.addUser = addUser;
        }

        public int getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(int updateTime) {
            this.updateTime = updateTime;
        }

        public int getUpdateuser() {
            return updateuser;
        }

        public void setUpdateuser(int updateuser) {
            this.updateuser = updateuser;
        }

        public int getCategoryType() {
            return categoryType;
        }

        public void setCategoryType(int categoryType) {
            this.categoryType = categoryType;
        }

        public int getHits() {
            return hits;
        }

        public void setHits(int hits) {
            this.hits = hits;
        }

        public int getLikeNums() {
            return likeNums;
        }

        public void setLikeNums(int likeNums) {
            this.likeNums = likeNums;
        }

        public int getCommentsNums() {
            return commentsNums;
        }

        public void setCommentsNums(int commentsNums) {
            this.commentsNums = commentsNums;
        }

        public int getTransNums() {
            return transNums;
        }

        public void setTransNums(int transNums) {
            this.transNums = transNums;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
