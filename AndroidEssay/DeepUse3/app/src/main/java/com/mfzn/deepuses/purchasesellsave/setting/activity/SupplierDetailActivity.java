package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.mfzn.deepuses.common.tab.TabAdapter;
import com.libcommon.table.TabLabel;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.settings.SupplierCustomerInfoResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.fragment.SupplierOrderFragment;
import com.mfzn.deepuses.purchasesellsave.setting.fragment.SupplierPayLogFragment;
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

public class SupplierDetailActivity extends BasicActivity {

    private static int EDIT_CODE = 1001;

    @BindView(R.id.supplier_name)
    TextView supplierName;

    @BindView(R.id.magic_indicator)
    MagicIndicator mIndicator;
    @BindView(R.id.detail_view_pager)
    ViewPager detailPager;

    List<TabLabel> mTabLabelList = new ArrayList<>();
    private final static int SUPPLIER_ORDER_LIST = 1;
    private final static int SUPPLIER_PAY_LOG_LIST = 2;
    private boolean isRefresh;
    private SupplierCustomerInfoResponse mSupplierCustomerInfoResponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("供应商详情", R.mipmap.work_xie);
        initData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_supplier_detail;
    }

    private void initData() {
        showDialog();

    }

    private void initSupplier() {


    }

    private void initDetailPager() {
        DetailAdapter detailAdapter = new DetailAdapter(getSupportFragmentManager());
        detailPager.setAdapter(detailAdapter);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setScrollPivotX(0.25f);
        mTabLabelList.add(new TabLabel(SUPPLIER_ORDER_LIST, "采购历史"));
        mTabLabelList.add(new TabLabel(SUPPLIER_PAY_LOG_LIST, "收付款历史"));

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
                case SUPPLIER_PAY_LOG_LIST:
                    SupplierPayLogFragment attrFragment = new SupplierPayLogFragment();
                    Bundle attrBundle = new Bundle();
                    attrBundle.putSerializable(ParameterConstant.SUPPLIER_PAY_LOG_LIST, (Serializable) mSupplierCustomerInfoResponse.getPayLogList());
                    attrFragment.setArguments(attrBundle);
                    return attrFragment;
                case SUPPLIER_ORDER_LIST:
                    SupplierOrderFragment storeFragment = new SupplierOrderFragment();
                    Bundle storeBundle = new Bundle();
                    storeBundle.putSerializable(ParameterConstant.SUPPLIER_ORDER_LIST, (Serializable) mSupplierCustomerInfoResponse.getOrderList());
                    storeFragment.setArguments(storeBundle);
                    return storeFragment;
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
        startActivityForResult(new Intent(this, SupplierCreateEditActivity.class), EDIT_CODE);
    }

    @OnClick(R.id.icon_detail)
    public void showSupplierDetail() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_CODE && requestCode == RESULT_OK) {
            isRefresh = true;
            initData();
        }
    }
}
