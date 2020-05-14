package com.libcommon.table;

import android.content.Context;
import android.view.View;


import com.libcommon.R;

import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

import java.util.List;

/**
 * @author yz @date 2020-05-04
 */

public class TabAdapter<T extends TabItem> extends CommonNavigatorAdapter {

    protected final List<T> tabList;

    protected boolean adjustItemSize;

    private static final int MAX_TAB_COUNT = 4;

    public TabAdapter(List<T> tabList) {
        this.tabList = tabList;
    }

    @Override
    public int getCount() {
        return tabList.size();
    }

    public void enableAdjustSize(boolean adjustItemSize) {
        this.adjustItemSize = adjustItemSize;
    }

    @Override
    public IPagerTitleView getTitleView(Context context, final int i) {
        BoldFontSimplePagerTitleView titleView = new BoldFontSimplePagerTitleView(context);
        titleView.setText(tabList.get(i).getName());
        titleView.setNormalColor(
                context.getResources().getColor(R.color.color_606266));
        titleView.setSelectedColor(
                context.getResources().getColor(R.color.color_3D7EFF));
        titleView.setDPTextSize(15, 15);
        titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentItem(i);
            }
        });
//        if (adjustItemSize) {
//            //非adjust模式下保证每个tab长度一样
//            titleView.setWidth(DisplayUtil.getDeviceWidth(context) / MAX_TAB_COUNT);
//        }
        return titleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        LinePagerIndicator indicator = new LinePagerIndicator(context);
        indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
        indicator.setLineWidth(UIUtil.dip2px(context, 30f));
        indicator.setYOffset(UIUtil.dip2px(context, 0));
        indicator.setColors(context.getResources().getColor(R.color.color_3D7EFF));
        return indicator;
    }

    public void setCurrentItem(int index) {

    }
}
