package com.mfzn.deepuses.purchasesellsave.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.settings.ShopCheckerResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.shop.adapter.ShopAuthSetAdapter;

import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ShopAuthSetActivity extends BasicListActivity<ShopCheckerResponse> {

    private static int REQUESTCODE=101;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("单据审核设计");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_set_auth;
    }


    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.getShopCheckerList()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<ShopCheckerResponse>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<List<ShopCheckerResponse>> result) {
                        refreshSource(result.getRes());
                    }
                });
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        ShopAuthSetAdapter mAdapter = new ShopAuthSetAdapter(this, mSourceList);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                Intent intent = new Intent(ShopAuthSetActivity.this, ShopCheckerListActivity.class);
                intent.putExtra(ParameterConstant.ORDER_ID, mSourceList.get(i).getData_id());
                startActivityForResult(intent, REQUESTCODE);
            }
        });
        return mAdapter;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE && resultCode == RESULT_OK) {
            getResourceList();
        }
    }
}
