package com.mfzn.deepuses.purchasesellsave.setting.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * @author syz @date 2020-05-04
 */
public class GoodsImageAdapter extends PagerAdapter {

    private List<String>  mBannerUrlList=new ArrayList<>();
    private Context context;

    public GoodsImageAdapter(Context context) {
        this.context=context;

    }


    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }


    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }



    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        position %= mBannerUrlList.size();

        if (position < 0) {
            position = mBannerUrlList.size() + position;
        }

        ImageView bannerIv = new ImageView(context);

        bannerIv.setScaleType(ImageView.ScaleType.CENTER_CROP);

        bannerIv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        Glide.with(context).load(mBannerUrlList.get(position)).into(bannerIv);

        container.addView(bannerIv);

        //bannerIv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        return bannerIv;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }
}
