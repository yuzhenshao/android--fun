package com.mfzn.deepuses.purchasesellsave.store.model;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

public class GoodsImage extends SimpleBannerInfo {

    private String imageUrl;

    public GoodsImage(String url) {
        imageUrl = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public Object getXBannerUrl() {
        return imageUrl;
    }
}
