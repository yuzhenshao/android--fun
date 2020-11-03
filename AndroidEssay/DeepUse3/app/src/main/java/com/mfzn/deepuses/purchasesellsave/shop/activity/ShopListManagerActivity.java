package com.mfzn.deepuses.purchasesellsave.shop.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.shop.ShopListResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.shop.adapter.ShopAdapter;

import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ShopListManagerActivity extends BasicListActivity<ShopListResponse> {

    private static int REQUESTCODE = 2000;
    private boolean isSelected;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("门店管理", R.mipmap.icon_titlebar_add);
        isSelected=getIntent().getBooleanExtra(ParameterConstant.IS_SELECTED,false);
    }

    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.getShopList()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<ShopListResponse>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<List<ShopListResponse>> result) {
                        refreshSource(result.getRes());
                    }
                });
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        ShopAdapter mAdapter = new ShopAdapter(this, mSourceList);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                if(isSelected){
                    Intent storeIntent = new Intent();
                    storeIntent.putExtra(ParameterConstant.SHOP, mSourceList.get(i));
                    setResult(Activity.RESULT_OK, storeIntent);
                    finish();
                }else{
                    Intent intent = new Intent(ShopListManagerActivity.this, ShopDetailActivity.class);
                    intent.putExtra(ParameterConstant.MAP_SHOP_ID, mSourceList.get(i).getShopID());
                    intent.putExtra(ParameterConstant.SHOP_NAME, mSourceList.get(i).getShopName());
                    startActivityForResult(intent, REQUESTCODE);
                }
            }
        });
        return mAdapter;
    }

    protected void rightPressedAction() {
        startActivityForResult(new Intent(this, AddShopActivity.class), REQUESTCODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE && resultCode == RESULT_OK) {
            getResourceList();
        }
    }
}