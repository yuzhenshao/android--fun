package com.mfzn.deepuses.model.xx;

import java.util.List;

public class MsgTdxxModel {


    /**
     * total : 10
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"userID":28,"title":"团队消息","content":"您已被\"王科磊\"任命为【云知枢2.0内测】的普通管理员","isRead":0,"isDel":0,"type":2,"extra":{"type":2,"pageType":0},"pageType":0,"addTime":1573797982,"updateTime":0,"data_id":390,"data_en_id":"pXXShSUo5"},{"userID":28,"title":"团队消息","content":"您在【云知枢2.0内测】已被\"邢康\"取消任命普通管理员","isRead":0,"isDel":0,"type":2,"extra":{"type":2,"pageType":0},"pageType":0,"addTime":1573787451,"updateTime":0,"data_id":229,"data_en_id":"pQQXdSkko"},{"userID":28,"title":"团队消息","content":"您在【云知枢2.0内测】的管理权限已被\"王科磊\"修改","isRead":0,"isDel":0,"type":2,"extra":{"type":2,"pageType":0},"pageType":0,"addTime":1573779649,"updateTime":0,"data_id":148,"data_en_id":"p1aafX4PW"},{"userID":28,"title":"团队消息","content":"您已被\"王科磊\"任命为【云知枢2.0内测】的普通管理员","isRead":0,"isDel":0,"type":2,"extra":{"type":2,"pageType":0},"pageType":0,"addTime":1573779637,"updateTime":0,"data_id":147,"data_en_id":"pQaSXf4Pe"},{"userID":28,"title":"团队消息","content":"您已被\"王科磊\"取消任命普通管理员","isRead":0,"isDel":0,"type":2,"extra":{"type":2,"pageType":0},"pageType":0,"addTime":1573735247,"updateTime":0,"data_id":63,"data_en_id":"lSfBaQQgU"},{"userID":28,"title":"团队消息","content":"您已被\"王科磊\"任命为普通管理员","isRead":0,"isDel":0,"type":2,"extra":{"type":2,"pageType":0},"pageType":0,"addTime":1573735239,"updateTime":0,"data_id":62,"data_en_id":"ldXah1vgk"},{"userID":28,"title":"团队消息","content":"您已被\"邢康\"取消任命普通管理员","isRead":0,"isDel":0,"type":2,"extra":{"type":2,"pageType":0},"pageType":0,"addTime":1573735203,"updateTime":0,"data_id":60,"data_en_id":"lXdXhvBg5"},{"userID":28,"title":"团队消息","content":"您已被\"王科磊\"任命为普通管理员","isRead":0,"isDel":0,"type":2,"extra":{"type":2,"pageType":0},"pageType":0,"addTime":1573735190,"updateTime":0,"data_id":59,"data_en_id":"lfaQXXXVo"},{"userID":28,"title":"团队消息","content":"欢迎你加入云知枢2.0内测","isRead":0,"isDel":0,"type":2,"extra":{"type":2,"pageType":1,"companyID":206},"pageType":1,"addTime":1573735167,"updateTime":0,"data_id":54,"data_en_id":"lSSffdSVP"},{"userID":28,"title":"团队消息","content":"你被拒绝加入云知枢2.0内测","isRead":0,"isDel":0,"type":2,"extra":{"type":2,"pageType":1,"companyID":206},"pageType":1,"addTime":1573735046,"updateTime":0,"data_id":53,"data_en_id":"ldBf1fdVU"}]
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
         * userID : 28
         * title : 团队消息
         * content : 您已被"王科磊"任命为【云知枢2.0内测】的普通管理员
         * isRead : 0
         * isDel : 0
         * type : 2
         * extra : {"type":2,"pageType":0}
         * pageType : 0
         * addTime : 1573797982
         * updateTime : 0
         * data_id : 390
         * data_en_id : pXXShSUo5
         */

        private int userID;
        private String title;
        private String content;
        private int isRead;
        private int isDel;
        private int type;
        private ExtraBean extra;
        private int pageType;
        private int addTime;
        private int updateTime;
        private int data_id;
        private String data_en_id;

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getIsRead() {
            return isRead;
        }

        public void setIsRead(int isRead) {
            this.isRead = isRead;
        }

        public int getIsDel() {
            return isDel;
        }

        public void setIsDel(int isDel) {
            this.isDel = isDel;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public ExtraBean getExtra() {
            return extra;
        }

        public void setExtra(ExtraBean extra) {
            this.extra = extra;
        }

        public int getPageType() {
            return pageType;
        }

        public void setPageType(int pageType) {
            this.pageType = pageType;
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

        public static class ExtraBean {
            /**
             * type : 2
             * pageType : 0
             */

            private int type;
            private int pageType;
            private String orderNo;
            private int companyID;

            public int getFromCompany() {
                return fromCompany;
            }

            public void setFromCompany(int fromCompany) {
                this.fromCompany = fromCompany;
            }

            private int fromCompany;
            public String getShareRecordID() {
                return shareRecordID;
            }

            public void setShareRecordID(String shareRecordID) {
                this.shareRecordID = shareRecordID;
            }

            private String shareRecordID;

            public int getCompanyID() {
                return companyID;
            }

            public void setCompanyID(int companyID) {
                this.companyID = companyID;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getPageType() {
                return pageType;
            }

            public void setPageType(int pageType) {
                this.pageType = pageType;
            }
        }
    }
}
