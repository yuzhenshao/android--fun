package com.mfzn.deepuses.purchasesellsave.sale.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.sale.OrderSalesListResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.sale.adapter.OrderInputAdapter;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import io.reactivex.Flowable;

public class OrderInputListActivity extends BasicListActivity<OrderSalesListResponse.OrderSalesResponse> {

    private int input;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("选择导入数据");
        input = getIntent().getIntExtra(ParameterConstant.INPUT_TYPE, 0);
        if (input == 0) {
            showToast("没有可导入数据");
            finish();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_sales_list;
    }


    @Override
    protected void getResourceList() {
        showDialog();
        getInputList()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<OrderSalesListResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<OrderSalesListResponse> reuslt) {
                        OrderSalesListResponse response = reuslt.getRes();
                        if (response != null) {
                            if (response.getData() != null) {
                                refreshSource(response.getData());
                                return;
                            }
                        }
                        showNoDataView();
                    }
                });
    }


    private Flowable<HttpResult<OrderSalesListResponse>> getInputList() {
        switch (input) {
            case 1:
                return ApiServiceManager.getOrderRetailList();
            case 2:
                return ApiServiceManager.getOrderSalesList();
            case 3:
                return ApiServiceManager.getOrderTakeGoodsList();

        }
        return ApiServiceManager.getOrderSalesList();
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        OrderInputAdapter mAdapter = new OrderInputAdapter(this, mSourceList);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int i) {
                Intent intent = new Intent();
                intent.putExtra(ParameterConstant.INPUT_DATA, mSourceList.get(i));
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        return mAdapter;
    }


}