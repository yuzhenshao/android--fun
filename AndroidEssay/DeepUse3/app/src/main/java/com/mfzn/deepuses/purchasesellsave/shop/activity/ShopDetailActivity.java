package com.mfzn.deepuses.purchasesellsave.shop.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.shop.ShopDataResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import butterknife.BindView;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ShopDetailActivity extends BasicActivity {

    @BindView(R.id.sale_person)
    TextView salePersonEView;
    @BindView(R.id.month_sum_sales_money)
    TextView monthSumSalesMoneyEView;
    @BindView(R.id.year_sum_sales_money)
    TextView yearSumSalesMoneyView;

    @BindView(R.id.store_count)
    TextView storeCountView;
    @BindView(R.id.store_person)
    TextView rstorePersonView;
    @BindView(R.id.store_sum_stock_num)
    TextView storeSumStockNumView;


    @BindView(R.id.money_account_count)
    TextView moneyAccountCountView;
    @BindView(R.id.capital_person)
    TextView capitalPersonView;
    @BindView(R.id.sum_income)
    TextView sumIncomeView;
    @BindView(R.id.sum_outcome)
    TextView sumOutcomeView;
    @BindView(R.id.sum_should_gathering)
    TextView sumShouldGatheringView;
    @BindView(R.id.sum_should_pay)
    TextView sumShouldPayView;

    @BindView(R.id.sum_customer_count)
    TextView sumCustomerCountView;
    @BindView(R.id.normal_customers_count)
    TextView snormalCustomersCountView;
    @BindView(R.id.cooperative_customers_count)
    TextView cooperativeCustomersCountView;

    private String mapShopID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("新增门店");
        mapShopID = getIntent().getStringExtra(ParameterConstant.MAP_SHOP_ID);
        ApiServiceManager.getShopData(mapShopID)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<ShopDataResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        hideDialog();
                    }

                    @Override
                    public void onNext(HttpResult<ShopDataResponse> result) {
                        setShopDetailData(result.getRes());
                    }
                });
    }

    private void setShopDetailData(ShopDataResponse res) {
       // salePersonEView.setText(res.getSalesData(). ());
        monthSumSalesMoneyEView.setText(res.getSalesData().getMonthSumSalesMoney());
        yearSumSalesMoneyView.setText(res.getSalesData().getYearSumSalesMoney());

        storeCountView.setText(res.getStoreData().getStoreCount());
       // rstorePersonView.setText(res.getStoreData() ());
        storeSumStockNumView.setText(res.getStoreData().getStoreSumStockNum());

        moneyAccountCountView.setText(res.getCapitalData().getMoneyAccountCount());
       // capitalPersonView.setText(res.getCapitalData(). ());
        sumIncomeView.setText(res.getCapitalData().getSumIncome());
        sumOutcomeView.setText(res.getCapitalData().getSumOutcome());
        sumShouldGatheringView.setText(res.getCapitalData().getSumShouldGathering());
        sumShouldPayView.setText(res.getCapitalData().getSumShouldPay());

        sumCustomerCountView.setText(res.getCustomerData().getSumCustomerCount());
        snormalCustomersCountView.setText(res.getCustomerData().getNormalCustomersCount());
        cooperativeCustomersCountView.setText(res.getCustomerData().getCooperativeCustomersCount());

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_detail;
    }

}