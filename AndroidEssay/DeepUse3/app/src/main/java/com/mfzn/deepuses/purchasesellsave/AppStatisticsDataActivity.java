package com.mfzn.deepuses.purchasesellsave;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.response.AppStatisticsDataResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class AppStatisticsDataActivity extends BasicActivity {

    private TextView mTodayInStoreView;
    private TextView mTodayOutStoreView;
    private TextView mSumStoreNumView;
    private TextView mTodaySalesIncomeView;
    private TextView mTodayMoneyView;
    private TextView mCustomerShouldGatheringView;
    private TextView mTodayCustomerNumView;
    private TextView mSumCustomerNumView;
    private TextView mYearEarnView;
    private TextView mSumCapitalView;

    private AppStatisticsDataResponse mAppStatisticsDataResponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("数据看板");
        initView();
        initData();
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_app_statistics;
    }

    private void initView() {
        mTodayInStoreView = (TextView) findViewById(R.id.today_in_store_view);
        mTodayOutStoreView = (TextView) findViewById(R.id.today_out_store_view);
        mSumStoreNumView = (TextView) findViewById(R.id.sum_store_num_view);
        mTodaySalesIncomeView = (TextView) findViewById(R.id.today_sales_income_view);
        mTodayMoneyView = (TextView) findViewById(R.id.today_money_view);
        mCustomerShouldGatheringView = (TextView) findViewById(R.id.customer_should_gathering_view);
        mTodayCustomerNumView = (TextView) findViewById(R.id.today_customer_num_view);
        mSumCustomerNumView = (TextView) findViewById(R.id.sum_customer_num_view);
        mYearEarnView = (TextView) findViewById(R.id.year_earn_view);
        mSumCapitalView = (TextView) findViewById(R.id.sum_capital_view);
    }


    private void initData() {
        ApiServiceManager.getAppData()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<AppStatisticsDataResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showToast(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<AppStatisticsDataResponse> reuslt) {
                        mAppStatisticsDataResponse = reuslt.getRes();
                        if (mAppStatisticsDataResponse != null) {
                            mTodayInStoreView.setText(mAppStatisticsDataResponse.getTodayInStore()+"");
                            mTodayOutStoreView.setText(mAppStatisticsDataResponse.getTodayOutStore()+"");
                            mSumStoreNumView.setText(mAppStatisticsDataResponse.getSumStoreNum()+"");
                            mTodaySalesIncomeView.setText(mAppStatisticsDataResponse.getTodaySalesIncome()+"");
                            mTodayMoneyView.setText(mAppStatisticsDataResponse.getTodayMoney()+"");
                            mCustomerShouldGatheringView.setText(mAppStatisticsDataResponse.getCustomerShouldGathering()+"");
                            mTodayCustomerNumView.setText(mAppStatisticsDataResponse.getTodayCustomerNum()+"");
                            mSumCustomerNumView.setText(mAppStatisticsDataResponse.getSumCustomerNum()+"");
                            mYearEarnView.setText(mAppStatisticsDataResponse.getYearEarn()+"");
                            mSumCapitalView.setText(mAppStatisticsDataResponse.getSumCapital()+"");
                        }else {
                            showToast(reuslt.getErrorMsg());
                        }
                    }
                });
    }
}
