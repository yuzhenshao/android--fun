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
import com.mfzn.deepuses.bean.response.sale.OrderOfferListResponse;
import com.mfzn.deepuses.bean.response.sale.OrderSalesListResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.sale.adapter.OrderOfferAdapter;

import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import cn.droidlover.xdroidmvp.router.Router;

public class OrderOfferListActivity extends BasicListActivity<OrderOfferListResponse.OrderOfferResponse> {
    private boolean isSelected;
    private static int REFRESH_TAG = 101;
    private static int CHECK_TAG = 102;
    private String isCheck;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isSelected = getIntent().getBooleanExtra(ParameterConstant.IS_SELECTED, false);
        mTitleBar.updateTitleBar(isSelected ? "选择导入数据" : "单据中心");
        initSearch("搜索单据编号、客户名称、联系人、电话");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_sales_list;
    }


    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.getOrderOfferList(isCheck)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<OrderOfferListResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<OrderOfferListResponse> reuslt) {
                        OrderOfferListResponse response = reuslt.getRes();
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

    @Override
    protected BaseQuickAdapter getAdapter() {
        OrderOfferAdapter mAdapter = new OrderOfferAdapter(this, mSourceList);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int i) {
                if (isSelected) {

                    OrderSalesListResponse.OrderSalesResponse orderSalesResponse=new OrderSalesListResponse.OrderSalesResponse();
                    OrderOfferListResponse.OrderOfferResponse offerResponse=mSourceList.get(i);


                    Intent intent = new Intent();
                    intent.putExtra(ParameterConstant.INPUT_DATA, mSourceList.get(i));
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } else {
                    Intent intent = new Intent(OrderOfferListActivity.this, OrderOfferDetailActivity.class);
                    intent.putExtra(ParameterConstant.ORDER_ID, mSourceList.get(i).getOrderID());
                    startActivityForResult(intent, REFRESH_TAG);
                }
            }
        });
        return mAdapter;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REFRESH_TAG) {
            if (requestCode == REFRESH_TAG) {
                getResourceList();
            } else if (requestCode == CHECK_TAG) {
                if (data != null) {
                    isCheck = data.getStringExtra(ParameterConstant.IS_SELECTED);
                    getResourceList();
                }
            }
        }
    }

    protected void searchAction(String keyword) {
        showDialog();
        ApiServiceManager.searchOrderOfferList(keyword, null)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<OrderOfferListResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<OrderOfferListResponse> reuslt) {
                        OrderOfferListResponse response = reuslt.getRes();
                        if (response != null) {
                            if (response.getData() != null) {
                                refreshSearchSource(response.getData());
                                return;
                            }
                        }
                        showNoDataView();
                    }
                });
    }


    @OnClick(R.id.filter)
    public void filter() {
        Router.newIntent(this).to(OrderOfferFilterActivity.class)
                .requestCode(CHECK_TAG)
                .anim(R.anim.pay_dialog_enter, R.anim.pay_dialog_exit)
                .launch();
    }

}