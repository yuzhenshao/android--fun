package com.mfzn.deepusesSer.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.activity.fx.SearchChangjingActivity;
import com.mfzn.deepusesSer.activity.fx.SearchZixunActivity;
import com.mfzn.deepusesSer.bass.BaseMvpFragment;
import com.mfzn.deepusesSer.fragment.fx.ChangjingFragment;
import com.mfzn.deepusesSer.fragment.fx.VideoFragmentNew;
import com.mfzn.deepusesSer.fragment.fx.ZixunFragment;
import com.mfzn.deepusesSer.fragment.fx.ZixunFragmentNew;
import com.mfzn.deepusesSer.present.fragment.FaxianPresnet;
import com.mfzn.deepusesSer.utils.ScaleTransitionPagerTitleView;
import com.mfzn.deepusesSer.view.MyDuiZhangPagerAdapter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class BaikeFragment extends BaseMvpFragment<FaxianPresnet> {

    @BindView(R.id.magic_indicator1)
    MagicIndicator magic_indicator1;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_baike;
    }

    @Override
    public FaxianPresnet newP() {
        return new FaxianPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        List<String> mDataList = new ArrayList<>();
        mDataList.add("资讯");
        mDataList.add("场景");

        List<Fragment> list = new ArrayList<>();
        list.add(new ZixunFragmentNew());
        list.add(new VideoFragmentNew());

        viewPager.setAdapter(new MyDuiZhangPagerAdapter(getActivity().getSupportFragmentManager(), list));

        initMagicIndicator1(mDataList);
    }

    @OnClick(R.id.ll_fx_search)
    public void onViewClicked() {
        int currentItem = viewPager.getCurrentItem();
        if(currentItem == 0) {
            startActivity(new Intent(getActivity(), SearchZixunActivity.class));
        }else if(currentItem == 1) {
            startActivity(new Intent(getActivity(), SearchChangjingActivity.class));
        }
    }

    private void initMagicIndicator1(List<String> mDataList) {
        magic_indicator1.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setTextSize(20);
                simplePagerTitleView.getPaint().setFakeBoldText(true);
                simplePagerTitleView.setNormalColor(getActivity().getResources().getColor(R.color.color_909399));
                simplePagerTitleView.setSelectedColor(getActivity().getResources().getColor(R.color.color_303133));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        });
        magic_indicator1.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magic_indicator1, viewPager);
    }
}
