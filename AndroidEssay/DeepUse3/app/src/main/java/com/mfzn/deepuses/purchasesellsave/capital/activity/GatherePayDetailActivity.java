package com.mfzn.deepuses.purchasesellsave.capital.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.libcommon.table.TabLabel;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.capital.PayerPayeeDetailResponse;
import com.mfzn.deepuses.common.tab.TabAdapter;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.capital.fragment.GatherePayLogListFragment;
import com.mfzn.deepuses.purchasesellsave.capital.fragment.GatherePayOrderListFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import io.reactivex.Flowable;

public class GatherePayDetailActivity extends BasicActivity {
    private TextView mName;
    private TextView mOrderNum;
    private TextView mUserName;
    private TextView mAdress;
    private TextView mSumGathering;
    private TextView mSumNeedGathering;
    private MagicIndicator mMagicIndicator;
    private ViewPager mViewPager;

    private int capitalType;
    List<TabLabel> mTabLabelList = new ArrayList<>();
    private final static int ORDER = 1;
    private final static int PAY = 2;
    public final static int REFRESH = 101;
    private PayerPayeeDetailResponse mPayeeDetailResponse;
    private String customerOrSupplier;
    private String customerOrSupplierID;
    private GatherePayOrderListFragment orderLogFragment;
    private GatherePayLogListFragment payLogFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_payer_payee_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTabLabelList.add(new TabLabel(ORDER, "销售历史"));
        mTabLabelList.add(new TabLabel(PAY, "收付历史"));
        customerOrSupplier = getIntent().getStringExtra(ParameterConstant.CUTOMER_OR_SUPPLIER);
        customerOrSupplierID = getIntent().getStringExtra(ParameterConstant.CUTOMER_OR_SUPPLIER_ID);
        capitalType = getIntent().getIntExtra(ParameterConstant.CAPITAL_TYPE, 0);
        initView();
        initData(false);
    }

    private void initView() {
        mName = (TextView) findViewById(R.id.name);
        mOrderNum = (TextView) findViewById(R.id.order_num);
        mUserName = (TextView) findViewById(R.id.user_name);
        mAdress = (TextView) findViewById(R.id.adress);
        mSumGathering = (TextView) findViewById(R.id.sum_gathering);
        mSumNeedGathering = (TextView) findViewById(R.id.sum_need_gathering);
        mMagicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
    }

    private void initData(boolean isRefresh) {
        showDialog();
        Flowable<HttpResult<PayerPayeeDetailResponse>> todoFloeable;
        if (capitalType == 0) {
            todoFloeable = ApiServiceManager.shouldGatheringInfo(customerOrSupplier, customerOrSupplierID);
        } else {
            todoFloeable = ApiServiceManager.shouldPayInfo(customerOrSupplier, customerOrSupplierID);
        }
        todoFloeable.compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<PayerPayeeDetailResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        hideDialog();
                        if (!isRefresh) {
                            showToast(error.getMessage());
                        }
                    }

                    @Override
                    public void onNext(HttpResult<PayerPayeeDetailResponse> reuslt) {
                        hideDialog();
                        mPayeeDetailResponse = reuslt.getRes();
                        if (mPayeeDetailResponse != null) {
                            PayerPayeeDetailResponse.BaseInfoResponse baseInfoResponse = mPayeeDetailResponse.getBaseInfo();
                            if (baseInfoResponse != null) {
                                mName.setText(baseInfoResponse.getPayerName());
                                mOrderNum.setText(baseInfoResponse.getChargePerson());
                                mUserName.setText(baseInfoResponse.getChargePerson());
                                mAdress.setText(baseInfoResponse.getChargePersonPhone());
                                mSumGathering.setText(baseInfoResponse.getSumGathering() + "元");
                                mSumNeedGathering.setText(baseInfoResponse.getSumNeedGathering() + "元");
                            }
                            if (isRefresh) {
                                if (orderLogFragment != null) {
                                    orderLogFragment.refresh(mPayeeDetailResponse.getOrderList());
                                }
                                if (payLogFragment != null) {
                                    payLogFragment.refresh(mPayeeDetailResponse.getPayLogList());
                                }
                            } else {
                                initDetailPager();
                            }
                        }
                    }
                });
    }


    private void initDetailPager() {
        DetailAdapter detailAdapter = new DetailAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(detailAdapter);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setScrollPivotX(0.25f);
        commonNavigator.setAdapter(new TabAdapter(mTabLabelList) {
            public void setCurrentItem(int index) {
                mViewPager.setCurrentItem(index);
            }
        });
        mMagicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
        detailAdapter.notifyDataSetChanged();
    }

    public class DetailAdapter extends FragmentPagerAdapter {
        public DetailAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (mTabLabelList.get(position).getId()) {
                case ORDER:
                    orderLogFragment = new GatherePayOrderListFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(ParameterConstant.SUPPLIER_ORDER_LIST, (Serializable) mPayeeDetailResponse.getOrderList());
                    bundle.putInt(ParameterConstant.CAPITAL_TYPE, capitalType);
                    bundle.putString(ParameterConstant.SUPPLIER, mPayeeDetailResponse.getBaseInfo().getPayerName());
                    orderLogFragment.setArguments(bundle);
                    return orderLogFragment;
                case PAY:
                    payLogFragment = new GatherePayLogListFragment();
                    Bundle payLogBundle = new Bundle();
                    payLogBundle.putSerializable(ParameterConstant.SUPPLIER_PAY_LOG_LIST, (Serializable) mPayeeDetailResponse.getPayLogList());
                    payLogFragment.setArguments(payLogBundle);
                    return payLogFragment;
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK&&requestCode == REFRESH) {
            initData(true);
        }
    }
}
