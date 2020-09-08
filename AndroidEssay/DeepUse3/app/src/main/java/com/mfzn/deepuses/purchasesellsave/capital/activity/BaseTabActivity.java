package com.mfzn.deepuses.purchasesellsave.capital.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import com.libcommon.table.TabLabel;
import com.libcommon.utils.CustomViewPager;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.common.tab.TabAdapter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public abstract class BaseTabActivity extends BasicActivity {

    protected List<TabLabel> mTabLabelList = new ArrayList<>();
    private BaseTabAdapter pagerAdapter;
    @BindView(R.id.magic_indicator)
    MagicIndicator mIndicator;
    @BindView(R.id.detail_view_pager)
    CustomViewPager detailPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTabLabelList();
        initDetailPager();
    }

    protected abstract void initTabLabelList();

    private void initDetailPager() {
        pagerAdapter = getAdapter();
        detailPager.setAdapter(pagerAdapter);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setScrollPivotX(0.25f);
        commonNavigator.setAdapter(new TabAdapter(mTabLabelList) {
            public void setCurrentItem(int index) {
                detailPager.setCurrentItem(index);
            }
        });
        mIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mIndicator, detailPager);
        pagerAdapter.notifyDataSetChanged();
    }


    protected abstract BaseTabAdapter getAdapter();


    public abstract class BaseTabAdapter extends FragmentPagerAdapter {
        public BaseTabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public String getPageTitle(int position) {
            return mTabLabelList.get(position).getName();
        }

        @Override
        public int getCount() {
            return mTabLabelList.size();
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_base_tab;
    }
}
