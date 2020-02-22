package com.mfzn.deepuses.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.MainActivity;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.company.SelectCompanyActivity;
import com.mfzn.deepuses.activityxm.FoundProjectActivity;
import com.mfzn.deepuses.activityxm.SearchOrderActivity;
import com.mfzn.deepuses.activityxm.SearchProjectActivity;
import com.mfzn.deepuses.activityxm.staging.ProjectStagingActivity;
import com.mfzn.deepuses.adapter.fg.XiangmuAdapter;
import com.mfzn.deepuses.bass.BaseMvpFragment;
import com.mfzn.deepuses.fragment.fx.ChangjingFragment;
import com.mfzn.deepuses.fragment.fx.ZixunFragment;
import com.mfzn.deepuses.fragment.xm.ShouhouGongdanFragment;
import com.mfzn.deepuses.fragment.xm.ShouhouXmFragment;
import com.mfzn.deepuses.model.login.UserModel;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.present.fragment.XiangmuPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ScaleTransitionPagerTitleView;
import com.mfzn.deepuses.utils.UserHelper;
import com.mfzn.deepuses.view.MyDuiZhangPagerAdapter;

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
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


public class XiangmuFragment extends BaseMvpFragment<XiangmuPresnet> {

    @BindView(R.id.xm_indicator)
    MagicIndicator xmIndicator;
    @BindView(R.id.xm_viewpager)
    ViewPager xmViewpager;
    private List<Fragment> list;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_xiangmu;
    }

    @Override
    public XiangmuPresnet newP() {
        return new XiangmuPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        List<String> mDataList = new ArrayList<>();
        mDataList.add("我的项目");
        mDataList.add("售后工单");

        list = new ArrayList<>();
        list.add(new ShouhouXmFragment());
        list.add(new ShouhouGongdanFragment());

        xmViewpager.setAdapter(new MyDuiZhangPagerAdapter(getActivity().getSupportFragmentManager(), list));

        initMagicIndicator1(mDataList);
    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if(!hidden){
//            xmViewpager.setAdapter(null);
//            xmViewpager.setAdapter(new MyDuiZhangPagerAdapter(getActivity().getSupportFragmentManager(), list));
//        }
//    }

    @OnClick({R.id.ll_xm_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_xm_search:
                int currentItem = xmViewpager.getCurrentItem();
                if(currentItem == 0) {
                    startActivity(new Intent(getActivity(), SearchProjectActivity.class));
                }else if(currentItem == 1) {
                    startActivity(new Intent(getActivity(), SearchOrderActivity.class));
                }
                break;
        }
    }

    private void initMagicIndicator1(List<String> mDataList) {
        xmIndicator.setBackgroundColor(Color.WHITE);
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
                        xmViewpager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        });
        xmIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(xmIndicator, xmViewpager);
    }
}
