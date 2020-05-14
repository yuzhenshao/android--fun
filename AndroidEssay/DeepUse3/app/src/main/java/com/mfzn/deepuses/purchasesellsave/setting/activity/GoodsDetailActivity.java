package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.table.TabAdapter;
import com.libcommon.table.TabLabel;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.request.CommodityRequest;
import com.mfzn.deepuses.bean.response.settings.GoodsDetailResponse;
import com.mfzn.deepuses.bean.response.settings.GoodsListResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsAdapter;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsImageAdapter;
import com.mfzn.deepuses.purchasesellsave.setting.fragment.BasicAttrFragment;
import com.mfzn.deepuses.purchasesellsave.setting.fragment.SalesRecordFragment;
import com.mfzn.deepuses.purchasesellsave.setting.fragment.StoresFragment;
import com.mfzn.deepuses.purchasesellsave.setting.view.BannerIndicator;
import com.mfzn.deepuses.utils.ToastUtil;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * @author yz @date 2020-05-04
 */
public class GoodsDetailActivity extends BasicActivity {

    private String shopId;


    @BindView(R.id.image_view_pager)
    ViewPager imagePager;
    @BindView(R.id.banner_indicator)
    BannerIndicator mImageIndicator;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.parameter_name)
    TextView parameterName;

    @BindView(R.id.icon_next)
    ImageView iconNext;

    @BindView(R.id.banner_indicator)
    MagicIndicator mIndicator;
    @BindView(R.id.detail_view_pager)
    ViewPager detailPager;

    List<TabLabel> mTabLabelList = new ArrayList<>();
    private final static int BASIC_ATTR = 1;
    private final static int STORES = 2;
    private final static int SALES_RECORD = 3;
    private GoodsDetailResponse mGoodsDetailResponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_detail;
    }

    private void initData() {
        mTitleBar.updateTitleBar("商品详情", R.mipmap.icon_titlebar_add);
        shopId = getIntent().getStringExtra(ParameterConstant.SHOP_ID);
        showDialog();
        ApiServiceManager.getGoodsInfo(shopId, getIntent().getStringExtra(ParameterConstant.GOODS_ID))
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<GoodsDetailResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        hideDialog();
                        ToastUtil.showToast(GoodsDetailActivity.this, error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<GoodsDetailResponse> reuslt) {
                        hideDialog();
                        mGoodsDetailResponse = reuslt.getRes();
                        initDetailPager();
                    }
                });
    }

    private void initImagePager() {

    }

    private void initDetailPager() {
        DetailAdapter detailAdapter = new DetailAdapter(getSupportFragmentManager());
        detailPager.setAdapter(detailAdapter);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setScrollPivotX(0.25f);
        mTabLabelList.add(new TabLabel(1, "基础属性"));
        mTabLabelList.add(new TabLabel(2, "库存流水"));
        mTabLabelList.add(new TabLabel(3, "销售记录"));

        commonNavigator.setAdapter(new TabAdapter(mTabLabelList));
        mIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mIndicator, detailPager);
    }

    public class DetailAdapter extends FragmentPagerAdapter {
        public DetailAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (mTabLabelList.get(position).getId()) {
                case BASIC_ATTR:
                    BasicAttrFragment attrFragment = new BasicAttrFragment();
                    Bundle attrBundle = new Bundle();
                    attrBundle.putSerializable(ParameterConstant.BASIC_ATTR, (Serializable) mGoodsDetailResponse.getGoodsInfo());
                    attrFragment.setArguments(attrBundle);
                    return attrFragment;
                case STORES:
                    StoresFragment storeFragment = new StoresFragment();
                    Bundle storeBundle = new Bundle();
                    storeBundle.putSerializable(ParameterConstant.STORES, (Serializable) mGoodsDetailResponse.getStoresInfo());
                    storeFragment.setArguments(storeBundle);
                    return storeFragment;
                case SALES_RECORD:
                    SalesRecordFragment salesRecordFragment = new SalesRecordFragment();
                    Bundle salesBundle = new Bundle();
                    salesBundle.putSerializable(ParameterConstant.BASIC_ATTR, (Serializable) mGoodsDetailResponse.getSalesRecord());
                    salesRecordFragment.setArguments(salesBundle);
                    return salesRecordFragment;
            }
            return null;
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

    protected void rightPressed() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.goods_popupwindow, null, false);
        TextView goodsPhotoEntry = contentView.findViewById(R.id.goods_photo_entry);
        goodsPhotoEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GoodsDetailActivity.this, CommodityPhotoCreateActivity.class));
            }
        });
        TextView goodsFormEntry = contentView.findViewById(R.id.goods_form_entry);
        goodsFormEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GoodsDetailActivity.this, CommodityCreateActivity.class));
            }
        });

        PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.popup_window_anim_style);
        popupWindow.showAtLocation(mTitleBar, Gravity.TOP, 0,
                mTitleBar.getHeight());
    }
}
