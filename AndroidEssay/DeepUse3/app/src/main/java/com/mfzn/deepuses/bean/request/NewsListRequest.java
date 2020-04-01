package com.mfzn.deepuses.bean.request;

import java.util.List;

/**
 * @author yz @date 2020-03-01
 */
public class NewsListRequest {

    /**
     * newsID : 1750
     * newsTitle : 百度免费开放AI算法：可将新冠病毒RNA分析提速120 倍
     * titleImage : http://img.qjsmartech.com/Topic/Images/2020-02/2020020219490772112.jpg
     * summary : 1月28日，1月30日，百度研究院宣布，将向各基因检测机构、防疫中心及全世界科学研究中心免费开放线性时间算法 LinearFold 以及世界上现有最快的 RNA 结构预测网站，以提升新型冠状病毒RNA空间结构预测速度，从而助力疫情防控。
     * cmsLabelIDs : 16
     * newsCategory : 1
     * newsCategoryName : 行业
     * hits : 0
     * likeCount : 0
     * commentCount : 0
     * transmitCount : 0
     * isShow : 1
     * sourceName : 千家网
     * companyID : 0
     * addTime : 1580644211
     * addUserID : 0
     * cmsLabels : [{"labelID":16,"labelName":"人工智能"}]
     */

    private int newsID;
    private String newsTitle;
    private String titleImage;
    private String summary;
    private String cmsLabelIDs;
    private int newsCategory;
    private String newsCategoryName;
    private int hits;
    private int likeCount;
    private int commentCount;
    private int transmitCount;
    private int isShow;
    private String sourceName;
    private int companyID;
    private int addTime;
    private int addUserID;
    private List<CmsLabelsBean> cmsLabels;

    public int getNewsID() {
        return newsID;
    }

    public void setNewsID(int newsID) {
        this.newsID = newsID;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCmsLabelIDs() {
        return cmsLabelIDs;
    }

    public void setCmsLabelIDs(String cmsLabelIDs) {
        this.cmsLabelIDs = cmsLabelIDs;
    }

    public int getNewsCategory() {
        return newsCategory;
    }

    public void setNewsCategory(int newsCategory) {
        this.newsCategory = newsCategory;
    }

    public String getNewsCategoryName() {
        return newsCategoryName;
    }

    public void setNewsCategoryName(String newsCategoryName) {
        this.newsCategoryName = newsCategoryName;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getTransmitCount() {
        return transmitCount;
    }

    public void setTransmitCount(int transmitCount) {
        this.transmitCount = transmitCount;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public int getAddUserID() {
        return addUserID;
    }

    public void setAddUserID(int addUserID) {
        this.addUserID = addUserID;
    }

    public List<CmsLabelsBean> getCmsLabels() {
        return cmsLabels;
    }

    public void setCmsLabels(List<CmsLabelsBean> cmsLabels) {
        this.cmsLabels = cmsLabels;
    }

    public static class CmsLabelsBean {
        /**
         * labelID : 16
         * labelName : 人工智能
         */

        private int labelID;
        private String labelName;

        public int getLabelID() {
            return labelID;
        }

        public void setLabelID(int labelID) {
            this.labelID = labelID;
        }

        public String getLabelName() {
            return labelName;
        }

        public void setLabelName(String labelName) {
            this.labelName = labelName;
        }
    }
}
