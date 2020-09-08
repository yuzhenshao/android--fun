package com.mfzn.deepuses.purchasesellsave.capital.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.capital.MoneyAccountFinancialLogListResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.capital.adapter.MoneyAccountFinancialLogAdapter;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class MoneyAccountFinancialLogActivity extends BasicListActivity<MoneyAccountFinancialLogListResponse.ListBean.MoneyAccountFinancialLogResponse> {

    @BindView(R.id.account_name)
    TextView accountNameView;

    @BindView(R.id.left_money)
    TextView leftMoneyView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("账户流水");
        accountNameView.setText(getIntent().getStringExtra(ParameterConstant.ACCOUNT_NAME));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_financial_log_list;
    }

    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.moneyAccountFinancialLog(getIntent().getStringExtra(ParameterConstant.ACCOUNT_ID))
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<MoneyAccountFinancialLogListResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<MoneyAccountFinancialLogListResponse> reuslt) {
                        MoneyAccountFinancialLogListResponse logListResponse = reuslt.getRes();
                        if (logListResponse != null) {
                            leftMoneyView.setText("余额："+logListResponse.getLeftMoney()+" 元");
                            if (logListResponse.getList() != null) {
                                refreshSource(logListResponse.getList().getData());
                                return;
                            }
                        }
                        showNoDataView();
                    }
                });
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new MoneyAccountFinancialLogAdapter(mSourceList);
    }
}
