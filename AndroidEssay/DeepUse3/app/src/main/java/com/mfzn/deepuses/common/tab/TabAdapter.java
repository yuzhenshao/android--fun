package com.mfzn.deepuses.common.tab;

import android.content.Context;
import android.view.View;


import com.libcommon.R;
import com.libcommon.table.TabItem;
import com.mfzn.deepuses.utils.CommonUtil;

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

    public TabAdapter(List<T> tabList) {
        this.tabList = tabList;
    }

    @Override
    public int getCount() {
        return tabList.size();
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
        if (tabList.size() > 0) {
            titleView.setWidth(CommonUtil.getDisplayMetrics(context).widthPixels / tabList.size());
        }
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
