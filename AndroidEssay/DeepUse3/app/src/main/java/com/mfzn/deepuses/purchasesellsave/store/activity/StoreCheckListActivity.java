package com.mfzn.deepuses.purchasesellsave.store.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.response.store.OrderStockCheckListResponse;
import com.mfzn.deepuses.bean.response.store.OrderStockCheckResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.store.adapter.StoreCheckAdapter;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class StoreCheckListActivity extends BasicListActivity<OrderStockCheckResponse> {
    private static int REQUESTCODE = 1001;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSearch("搜索单据编号、商品名称、货号、编码");
        mTitleBar.updateTitleBar("盘点", R.mipmap.icon_titlebar_add);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_store_check_list;
    }


    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.getOrderStockCheckList("", "0", -1)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<OrderStockCheckListResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<OrderStockCheckListResponse> reuslt) {
                        OrderStockCheckListResponse response = reuslt.getRes();
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
        StoreCheckAdapter mAdapter = new StoreCheckAdapter(this, mSourceList);
        return mAdapter;
    }

//    @OnClick({R.id.filter, R.id.search_container})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.filter:
//                Router.newIntent(this).to(SelectTypeActivity.class)
//                        .requestCode(Constants.SELECT_BC)
//                        .anim(R.anim.pay_dialog_enter, R.anim.pay_dialog_exit)
//                        .launch();
//                break;
//            case R.id.search_container:
//                break;
//        }
//    }

    protected void rightPressedAction() {
        startActivityForResult(new Intent(this, AddOrderStockCheckActivity.class), REQUESTCODE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE && requestCode == RESULT_OK) {
            getResourceList();
        }
    }
}
