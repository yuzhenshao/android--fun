package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.response.settings.MoneyAccountListResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.MoneyAccountAdapter;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class MoneyAccountListActivity extends BasicListActivity<MoneyAccountListResponse.AccountResponse> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("结算账户");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_account_list_view;
    }


    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.getMoneyAccountList("")
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<MoneyAccountListResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<MoneyAccountListResponse> reuslt) {
                        MoneyAccountListResponse response = reuslt.getRes();
                        if (response != null) {
                            if (!ListUtil.isEmpty(response.getList())) {
                                refreshSource(response.getList());
                                return;
                            }
                        }
                        showNoDataView();
                    }
                });
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        MoneyAccountAdapter mAdapter = new MoneyAccountAdapter(mSourceList);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                MoneyAccountListResponse.AccountResponse accountResponse = mSourceList.get(i);
                Intent intent = new Intent();
                intent.putExtra("Id", accountResponse.getAccountID());
                intent.putExtra("Name", accountResponse.getAccountName());
                setResult(Activity.RESULT_OK, intent);
                finish();

            }
        });
        return mAdapter;
    }
}
