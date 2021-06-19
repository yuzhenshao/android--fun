package com.mfzn.deepuses.purchasesellsave.sale.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.sale.OrderOfferListResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.sale.adapter.OrderOfferAdapter;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import cn.droidlover.xdroidmvp.router.Router;

public class SaleOrderCenterListActivity extends BasicListActivity<OrderOfferListResponse.OrderOfferResponse> {
    private static int REFRESH_TAG = 101;
    private static int CHECK_TAG = 102;
    private int isCheck=-1;
    private int isCanceled=0;
    private int orderType=3;
    @BindView(R.id.filter)
    ImageView filterView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar(getTypeName());
        initSearch("搜索单据编号、客户名称、联系人、电话");
        filterView.setVisibility(View.VISIBLE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_sales_list;
    }


    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.getOrderList("", isCheck, isCanceled, orderType)
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
                Intent intent = new Intent(SaleOrderCenterListActivity.this, OrderDetailActivity.class);
                intent.putExtra(ParameterConstant.ORDER_ID, mSourceList.get(i).getOrderID());
                intent.putExtra(ParameterConstant.ORDER_TYPE, orderType);
                intent.putExtra(ParameterConstant.NAME, mSourceList.get(i).getCustomerName());
                intent.putExtra(ParameterConstant.PHONE, mSourceList.get(i).getCustomerPhone());
                startActivityForResult(intent, REFRESH_TAG);
            }
        });
        return mAdapter;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REFRESH_TAG) {
                getResourceList();
            } else if (requestCode == CHECK_TAG) {
                if (data != null) {
                    orderType = data.getIntExtra(ParameterConstant.ORDER_TYPE,3);
                    isCanceled = data.getIntExtra(ParameterConstant.ORDER_CACNEL,0);
                    isCheck = data.getIntExtra(ParameterConstant.ORDER_CHECK,-1);
                    mTitleBar.updateTitleBar(getTypeName());
                    getResourceList();
                }
            }
        }
    }

    protected void searchAction(String keyword) {
        showDialog();
        ApiServiceManager.getOrderList(keyword, isCheck, isCanceled, orderType)
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
        Router.newIntent(this).to(SalesOrderOfferFilterActivity.class)
                .putInt(ParameterConstant.ORDER_TYPE,orderType)
                .putInt(ParameterConstant.ORDER_CACNEL,isCanceled)
                .putInt(ParameterConstant.ORDER_CHECK,isCheck)
                .requestCode(CHECK_TAG)
                .anim(R.anim.pay_dialog_enter, R.anim.pay_dialog_exit)
                .launch();
    }

    private String getTypeName(){
        switch (orderType) {
            case 3:
                return "报价单";
            case 4:
                return "销售订单";
            case 5:
                return "零售单";
            case 6:
                return "销售退货单";
            case 13:
                return "零售退货单";
            case 7:
                return "个人领货单";
            case 8:
                return "个人归还单";
        }
       return "";
    }
}