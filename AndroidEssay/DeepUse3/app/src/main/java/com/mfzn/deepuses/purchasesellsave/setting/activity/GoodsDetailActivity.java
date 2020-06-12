package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.libcommon.dialog.DialogUtils;
import com.libcommon.dialog.fragment.BaseDialogFragment;
import com.libcommon.dialog.fragment.CustomDialog;
import com.libcommon.dialog.listener.OnBindViewListener;
import com.libcommon.dialog.listener.OnViewClickListener;
import com.libcommon.dialog.view.BindViewHolder;
import com.mfzn.deepuses.common.tab.TabAdapter;
import com.libcommon.table.TabLabel;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.settings.GoodsDetailResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.fragment.BasicAttrFragment;
import com.mfzn.deepuses.purchasesellsave.setting.fragment.SalesRecordFragment;
import com.mfzn.deepuses.purchasesellsave.setting.fragment.StoresFragment;
import com.mfzn.deepuses.purchasesellsave.setting.view.BannerIndicator;
import com.mfzn.deepuses.purchasesellsave.store.model.GoodsImage;
import com.mfzn.deepuses.utils.ToastUtil;
import com.stx.xhb.xbanner.XBanner;

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

    @BindView(R.id.xbanner)
    XBanner banner;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.parameter_name)
    TextView parameterName;
    @BindView(R.id.icon_next)
    ImageView iconNext;
    @BindView(R.id.magic_indicator)
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
        mTitleBar.updateTitleBar("商品详情", R.mipmap.icon_edit);
        showDialog();
        ApiServiceManager.getGoodsInfo(getIntent().getStringExtra(ParameterConstant.GOODS_ID))
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
                        initImagePager();
                        initDetailPager();
                    }
                });
    }

    private void initImagePager() {
        banner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(GoodsDetailActivity.this).load(((GoodsImage) model).getXBannerUrl())
                        .into((ImageView) view);
            }
        });

        banner.setBannerData(mGoodsDetailResponse.getGoodsImgsUrl());
    }

    private void initDetailPager() {
        mTabLabelList.add(new TabLabel(1, "基础属性"));
        mTabLabelList.add(new TabLabel(2, "库存流水"));
        mTabLabelList.add(new TabLabel(3, "销售记录"));
        DetailAdapter detailAdapter = new DetailAdapter(getSupportFragmentManager());
        detailPager.setAdapter(detailAdapter);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setScrollPivotX(0.25f);
        commonNavigator.setAdapter(new TabAdapter(mTabLabelList) {
            public void setCurrentItem(int index) {
                detailPager.setCurrentItem(index);
            }
        });
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

    protected void rightPressedAction() {
        startActivity(new Intent(GoodsDetailActivity.this, CommodityCreateActivity.class));
    }


    @OnClick(R.id.icon_next)
    public void showGoodsParam() {
        new CustomDialog.Builder().setLayoutRes(R.layout.dialog_goods_info)
                .setHeight((int) (0.8 * DialogUtils.getDisplayMetrics(this).heightPixels))
                .setWidth(WindowManager.LayoutParams.MATCH_PARENT)
                .setGravity(Gravity.BOTTOM)
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(BindViewHolder holder) {
                        GoodsDetailResponse.GoodsInfoResponse goods = mGoodsDetailResponse.getGoodsInfo();
                        holder.setText(R.id.goods_name, goods.getGoodsName());
                        holder.setText(R.id.sale_price, goods.getSalePrice());
                        holder.setText(R.id.store_container, goods.getGoodsSumStockNum() + "");
                        holder.setText(R.id.goods_bar_code, goods.getGoodsBarCode());
                        holder.setText(R.id.goods_brand, goods.getGoodsBrand());
                        holder.setText(R.id.goods_attr, goods.getGoodsAttr());
                        holder.setText(R.id.goods_unit, goods.getUnitName());
                        holder.setText(R.id.goods_category, goods.getCatName());
                        holder.setText(R.id.goods_position, goods.getPositionName());
                    }
                })
                .addOnClickListener(com.libcommon.R.id.confirm_btn)
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BaseDialogFragment customDialog, BindViewHolder bindViewHolder, View view) {
                        if (customDialog != null) {
                            customDialog.dismiss();
                        }
                    }
                }).create().show(getSupportFragmentManager(), getClass().getName());
    }
}
