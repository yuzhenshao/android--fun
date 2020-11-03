package com.mfzn.deepuses.purchasesellsave.shop.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.shop.ShopUserListResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.shop.adapter.ShopCheckerAdapter;

import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ShopCheckerListActivity extends BasicListActivity<ShopUserListResponse.ShopUserResponse> {

    private ShopUserListResponse.ShopUserResponse curShopUserResponse;
    private String dataId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("选择审核人", "取消");
        dataId = getIntent().getStringExtra(ParameterConstant.ORDER_ID);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_checker_list_view;
    }


    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.getShopUserList()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<ShopUserListResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<ShopUserListResponse> result) {
                        if (result == null || result.getRes() == null) {
                            showNoDataView();
                        } else {
                            refreshSource(result.getRes().getData());
                        }
                    }
                });
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        ShopCheckerAdapter mAdapter = new ShopCheckerAdapter(this, mSourceList);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                curShopUserResponse = mSourceList.get(i);
                mAdapter.setCurShopUserResponse(curShopUserResponse);
                mAdapter.notifyDataSetChanged();
            }
        });
        return mAdapter;
    }

    protected void rightPressedAction() {
        finish();
    }

    @OnClick(R.id.confirm_btn)
    public void confirmChecker() {
        if (curShopUserResponse == null) {
            showToast("请选择审核人");
            return;
        }
        ApiServiceManager.setShopChecker(dataId, curShopUserResponse.getUserID())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        showToast(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult result) {
                        setResult(Activity.RESULT_OK);
                        finish();
                    }
                });
    }
}
