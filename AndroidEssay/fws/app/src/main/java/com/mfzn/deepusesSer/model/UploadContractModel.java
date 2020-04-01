package com.mfzn.deepusesSer.model;

import cn.droidlover.xdroidmvp.net.IModel;

public class UploadContractModel implements IModel {


    /**
     * status : 1
     * msg : ok
     * res : {"fileID":"1","imgUrl":"/uploads/1/procheck/20190308094007-dog.jpg,/uploads/1/procheck/20190308094007-default_head.jpg","imageNote":"这个是说明"}
     */

    private int status;
    private String msg;
    private ResBean res;

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isAuthError() {
        return status == -1;
    }

    @Override
    public boolean isBizError() {
        return status == 0;
    }

    @Override
    public String getErrorMsg() {
        return msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResBean getRes() {
        return res;
    }

    public void setRes(ResBean res) {
        this.res = res;
    }

    public static class ResBean {
        /**
         * fileID : 1
         * imgUrl : /uploads/1/procheck/20190308094007-dog.jpg,/uploads/1/procheck/20190308094007-default_head.jpg
         * imageNote : 这个是说明
         */

        private String fileID;
        private String imgUrl;
        private String imageNote;

        public String getFileID() {
            return fileID;
        }

        public void setFileID(String fileID) {
            this.fileID = fileID;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getImageNote() {
            return imageNote;
        }

        public void setImageNote(String imageNote) {
            this.imageNote = imageNote;
        }
    }
}
