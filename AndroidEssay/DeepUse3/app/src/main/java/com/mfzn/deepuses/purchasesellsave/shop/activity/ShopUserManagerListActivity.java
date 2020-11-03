package com.mfzn.deepuses.purchasesellsave.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.myteam.SelectManageActivity;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.shop.ShopUserListResponse;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.shop.adapter.ShopStaffAdapter;
import com.mfzn.deepuses.utils.Constants;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ShopUserManagerListActivity extends BasicListActivity<ShopUserListResponse.ShopUserResponse> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("门店员工", R.mipmap.icon_titlebar_add);
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
        ShopStaffAdapter mAdapter = new ShopStaffAdapter(this, mSourceList);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                Intent intent = new Intent(ShopUserManagerListActivity.this, ShopStaffInfoActivity.class);
                intent.putExtra(ParameterConstant.SHOP_STAFF, mSourceList.get(i));
                startActivity(intent);
            }
        });
        return mAdapter;
    }

    protected void rightPressedAction() {
        Intent intent = new Intent(this, SelectManageActivity.class);
        intent.putExtra(Constants.SINGLE, true);
        startActivityForResult(intent, Constants.ADD_MANAGE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.ADD_MANAGE == requestCode) {
            if (data != null) {
                ZuzhiJiagouModel.StaffBean staffBean = (ZuzhiJiagouModel.StaffBean) data.getSerializableExtra(Constants.STAFFBEAN);
                addShopUser(staffBean.getUserID());
            }
        }
    }

    private void addShopUser(String userId) {
        ApiServiceManager.shopUserAdd(userId)
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
                        getResourceList();
                    }
                });
    }
}
