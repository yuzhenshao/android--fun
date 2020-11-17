package com.mfzn.deepuses.purchasesellsave.store.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.sale.OrderOfferListResponse;
import com.mfzn.deepuses.bean.response.store.OrderAllotListResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.store.adapter.OrderAllotAdapter;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class OrderAllotListActivity extends BasicListActivity<OrderAllotListResponse.OrderAllotResponse> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("调拨列表");
        initSearch("搜索单据编号、客户供应商、联系人、电话");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_sales_list;
    }


    @Override
    protected void getResourceList() {
        loadOrderAllot("");
    }

    protected void searchAction(String keyword) {
        loadOrderAllot(keyword);
    }

    private void loadOrderAllot(String keyword){
        showDialog();
        ApiServiceManager.getOrderAllotList(keyword)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<OrderAllotListResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<OrderAllotListResponse> reuslt) {
                        OrderAllotListResponse response = reuslt.getRes();
                        if (response != null) {
                            if (response.getData() != null) {
                                if(TextUtils.isEmpty(keyword)){
                                    refreshSource(response.getData());
                                }else {
                                    refreshSearchSource(response.getData());
                                }
                                return;
                            }
                        }
                        showNoDataView();
                    }
                });

    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        OrderAllotAdapter mAdapter = new OrderAllotAdapter(this, mSourceList);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                Intent intent=new Intent(OrderAllotListActivity.this,OrderAllotDetailActivity.class);
                intent.putExtra(ParameterConstant.ORDER_ID,mSourceList.get(i).getOrderID());

                startActivityForResult(intent,101);
            }
        });
        return mAdapter;
    }
}
