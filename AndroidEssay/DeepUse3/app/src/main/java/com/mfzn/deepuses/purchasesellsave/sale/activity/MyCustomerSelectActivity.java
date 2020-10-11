package com.mfzn.deepuses.purchasesellsave.sale.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.response.settings.CustomerListResponse;
import com.mfzn.deepuses.model.khgl.WholeCustomerModel;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.sale.adapter.MyCustomerSelectAdapter;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class MyCustomerSelectActivity extends BasicListActivity<CustomerListResponse.CustomerResponse> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("选择客户");
    }


    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.getCustomerList("")
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .subscribe(new ApiSubscriber<HttpResult<CustomerListResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<CustomerListResponse> reuslt) {
                        if (reuslt.getRes() != null && !ListUtil.isEmpty(reuslt.getRes().getData())) {
                            refreshSource(reuslt.getRes().getData());
                            return;
                        }
                        showNoDataView();
                    }
                });
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        MyCustomerSelectAdapter adapter = new MyCustomerSelectAdapter(mSourceList);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                CustomerListResponse.CustomerResponse user = mSourceList.get(i);
                if (user != null) {
                    Intent intent = new Intent();
                    intent.putExtra("Id", user.getData_id() + "");
                    intent.putExtra("Name", user.getCustomerName());
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
        return adapter;
    }
}