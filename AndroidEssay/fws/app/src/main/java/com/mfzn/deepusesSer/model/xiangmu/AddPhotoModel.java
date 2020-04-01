package com.mfzn.deepusesSer.model.xiangmu;

import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * Created by sun on 2018/5/25.
 */

public class AddPhotoModel implements Serializable {

    private List<BmpBean> bmp;
    private List<DrrBean> drr;
    private String exlpain;
    private String photo_id;
    private File bitmap;


    public File getBitmap() {
        return bitmap;
    }

    public void setBitmap(File bitmap) {
        this.bitmap = bitmap;
    }


    public List<BmpBean> getBmp() {
        return bmp;
    }

    public void setBmp(List<BmpBean> bmp) {
        this.bmp = bmp;
    }

    public List<DrrBean> getDrr() {
        return drr;
    }

    public void setDrr(List<DrrBean> drr) {
        this.drr = drr;
    }

    public String getExlpain() {
        return exlpain;
    }

    public void setExlpain(String exlpain) {
        this.exlpain = exlpain;
    }

    public String getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(String photo_id) {
        this.photo_id = photo_id;
    }

    public static class BmpBean  implements Serializable{

        private File bitmap;


        public File getBitmap() {
            return bitmap;
        }

        public void setBitmap(File bitmap) {
            this.bitmap = bitmap;
        }
    }

    public static class DrrBean  implements Serializable{

        private String drrs;


        public String getDrrs() {
            return drrs;
        }

        public void setDrrs(String drrs) {
            this.drrs = drrs;
        }
    }
}
