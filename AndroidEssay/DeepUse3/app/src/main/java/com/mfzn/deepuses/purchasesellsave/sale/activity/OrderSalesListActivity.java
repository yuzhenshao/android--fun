package com.mfzn.deepuses.purchasesellsave.sale.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.khgl.SelectTypeActivity;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.response.sale.OrderOfferListResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.sale.adapter.OrderSalesAdapter;
import com.mfzn.deepuses.purchasesellsave.store.activity.OrderStockCheckAddActivity;
import com.mfzn.deepuses.utils.Constants;

import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import cn.droidlover.xdroidmvp.router.Router;

public class OrderSalesListActivity extends BasicListActivity<OrderOfferListResponse.OrderOfferResponse> {
    private static int REQUESTCODE = 1001;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("单据中心", R.mipmap.icon_titlebar_add);
        initSearch("搜索单据编号、客户名称、联系人、电话");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_sales_list;
    }


    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.getOrderOfferList()
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
        OrderSalesAdapter mAdapter = new OrderSalesAdapter(this, mSourceList);
        return mAdapter;
    }

    @OnClick({R.id.filter, R.id.search_container})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.filter:
                Router.newIntent(this).to(SelectTypeActivity.class)
                        .requestCode(Constants.SELECT_BC)
                        .anim(R.anim.pay_dialog_enter, R.anim.pay_dialog_exit)
                        .launch();
                break;
            case R.id.search_container:
                break;
        }
    }

    protected void rightPressedAction() {
        startActivityForResult(new Intent(this, OrderStockCheckAddActivity.class), REQUESTCODE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE && requestCode == RESULT_OK) {
            getResourceList();
        }
    }
}