package com.mfzn.deepuses.model.faxian;

import java.io.Serializable;
import java.util.List;

public class News implements Serializable {


    /**
     * total : 1442
     * per_page : 1
     * current_page : 10
     * last_page : 1442
     * data : [{"rowNum":1251,"NewsID":0,"Title":"弱电综合布线的流程是怎么样的？","TitleImage":"/uploads/brandImage/18661128271-titlePic-724e021b763a76d943917d1a4348535c.jpg","Summary":"弱电综合布线的流程是怎么样的？","Content":"  弱电综合布线的流程是怎么样的？<\/span>　　1、首先是测量需要多长的线。<\/span>根据就是决定好放配线架的位置，然后结合线从外边进入机柜的距离等等计算出把配线架打上并且安装上机柜需要多长的网线，以方便后面的整体理线，如果线缆放置在机柜里过长容易造成不美观的情况，而且后期理线比较麻烦，要不停的往后扯方能整齐美观的把线缆整理。　　其次还要综合考虑到线缆从机柜上部或者下部进入机柜后要怎么走，尽量不要弯曲或者绕过一些东西，能从贴着机柜走就贴着机柜走，要考虑到走线的方向不能够影响后来的网络设备的进入，这需要工作人员有一定得可预见能力。<\/p>  \"2019101014131580040.jpg\"/<\/p>  　　2、根据第一步测算出来的长度进行裁剪，把多余的剪去，注意要尽量一致避免影响美观，而且要比计算的留出一部分冗余，最好能留上十到十五厘米，其中包括剪去包皮打上配线架的长度。<\/span>此时还不需要把线缆从外部放入机柜，因为此时放入会难以理线。　　3、接下来开始理线，理线的步骤如下：<\/span>　　A、在线缆根部开始外露的地方开始用扎带绑好，如果线缆过多就暗中网线的编号从中间分成两份或者多份，但是需要保证分出来的网线每一部分都是连续的，因为这样下步工作就容易进行。　　B、根据需要把大部分网线扎好就开始用配线板理线，配线板的使用方法就是按照一个约定把线缆从配线板的背部一条条的穿过配线板，在配线板背部按照两根连续的网线一起用扎带扎起来，每两个扎一块儿都要求有一个错位，就是不要并排的扎太多，每两个扎带上下最好有点距离，两根连续的扎起来以后就开始六根网线一块儿扎一起，平均需要每半米重新扎一次，注意扎的时候注意整齐，不要胡乱扎一起给人不和谐的感受。　　C、使用理线板一一直顺着往下移，在理线板的背部也一直用扎带缠好（半米一扎）。最后一次六根一乍\r\n \r\n的位置在离线缆尾部一米左右，扎好了以后就在附近距离开始两根一扎，当然还是按照顺序扎好。每两根扎好后往下半米左右再次使用同样的方法再扎一次，有利于下一步打配线架。　　4、下面就是开始打配线架这一环节了，由于上面的工作做完了以后线缆已经非常明确了，把线缆甩到机柜的外边开始放置在一张工作台上三人一组开始进行压线的工作。<\/span>按照事先安排好的位置进行压线就不容易出错了。压线的具体工艺步骤在这里不再赘述。<\/p>  <\/p>","ClassName":"综合布线,数据中心","DateAndTime":0,"Source":"58监控网 ","LastPage":0,"newsType":5,"isShow":1,"isDel":0,"addTime":1571213682,"addUser":21,"updateTime":0,"updateuser":0,"categoryType":0,"hits":0,"likeNums":0,"commentsNums":0,"transNums":0,"label":"","type":1,"u_name":"黄成伟","u_head":"/uploads/stark.jpg"}]
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

    public static class DataBean implements Serializable {
        /**
         * rowNum : 1251
         * NewsID : 0
         * Title : 弱电综合布线的流程是怎么样的？
         * TitleImage : /uploads/brandImage/18661128271-titlePic-724e021b763a76d943917d1a4348535c.jpg
         * Summary : 弱电综合布线的流程是怎么样的？
         * Content :   弱电综合布线的流程是怎么样的？</span>　　1、首先是测量需要多长的线。</span>根据就是决定好放配线架的位置，然后结合线从外边进入机柜的距离等等计算出把配线架打上并且安装上机柜需要多长的网线，以方便后面的整体理线，如果线缆放置在机柜里过长容易造成不美观的情况，而且后期理线比较麻烦，要不停的往后扯方能整齐美观的把线缆整理。　　其次还要综合考虑到线缆从机柜上部或者下部进入机柜后要怎么走，尽量不要弯曲或者绕过一些东西，能从贴着机柜走就贴着机柜走，要考虑到走线的方向不能够影响后来的网络设备的进入，这需要工作人员有一定得可预见能力。</p>  "2019101014131580040.jpg"/</p>  　　2、根据第一步测算出来的长度进行裁剪，把多余的剪去，注意要尽量一致避免影响美观，而且要比计算的留出一部分冗余，最好能留上十到十五厘米，其中包括剪去包皮打上配线架的长度。</span>此时还不需要把线缆从外部放入机柜，因为此时放入会难以理线。　　3、接下来开始理线，理线的步骤如下：</span>　　A、在线缆根部开始外露的地方开始用扎带绑好，如果线缆过多就暗中网线的编号从中间分成两份或者多份，但是需要保证分出来的网线每一部分都是连续的，因为这样下步工作就容易进行。　　B、根据需要把大部分网线扎好就开始用配线板理线，配线板的使用方法就是按照一个约定把线缆从配线板的背部一条条的穿过配线板，在配线板背部按照两根连续的网线一起用扎带扎起来，每两个扎一块儿都要求有一个错位，就是不要并排的扎太多，每两个扎带上下最好有点距离，两根连续的扎起来以后就开始六根网线一块儿扎一起，平均需要每半米重新扎一次，注意扎的时候注意整齐，不要胡乱扎一起给人不和谐的感受。　　C、使用理线板一一直顺着往下移，在理线板的背部也一直用扎带缠好（半米一扎）。最后一次六根一乍

         的位置在离线缆尾部一米左右，扎好了以后就在附近距离开始两根一扎，当然还是按照顺序扎好。每两根扎好后往下半米左右再次使用同样的方法再扎一次，有利于下一步打配线架。　　4、下面就是开始打配线架这一环节了，由于上面的工作做完了以后线缆已经非常明确了，把线缆甩到机柜的外边开始放置在一张工作台上三人一组开始进行压线的工作。</span>按照事先安排好的位置进行压线就不容易出错了。压线的具体工艺步骤在这里不再赘述。</p>  </p>
         * ClassName : 综合布线,数据中心
         * DateAndTime : 0
         * Source : 58监控网
         * LastPage : 0
         * newsType : 5
         * isShow : 1
         * isDel : 0
         * addTime : 1571213682
         * addUser : 21
         * updateTime : 0
         * updateuser : 0
         * categoryType : 0
         * hits : 0
         * likeNums : 0
         * commentsNums : 0
         * transNums : 0
         * label :
         * type : 1
         * u_name : 黄成伟
         * u_head : /uploads/stark.jpg
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
        private String u_name;
        private String u_head;

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

        public String getU_name() {
            return u_name;
        }

        public void setU_name(String u_name) {
            this.u_name = u_name;
        }

        public String getU_head() {
            return u_head;
        }

        public void setU_head(String u_head) {
            this.u_head = u_head;
        }
    }
}
