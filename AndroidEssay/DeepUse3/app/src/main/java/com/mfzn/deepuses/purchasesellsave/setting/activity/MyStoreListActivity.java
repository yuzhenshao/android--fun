package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.sale.PersonalStoreListResponse;
import com.mfzn.deepuses.bean.response.settings.MyStoreResponse;
import com.mfzn.deepuses.bean.response.settings.StoreResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.sale.adapter.MyStoreAdapter;
import com.mfzn.deepuses.purchasesellsave.sale.adapter.PersonStoreAdapter;

import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class MyStoreListActivity extends BasicListActivity<MyStoreResponse> {

    private boolean isSelected;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("选择仓库");
        isSelected = getIntent().getBooleanExtra(ParameterConstant.IS_SELECTED, false);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_store_list;
    }


    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.storeListWithMy()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<MyStoreResponse>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<List<MyStoreResponse>> result) {
                        if (!ListUtil.isEmpty(result.getRes())) {
                            refreshSource(result.getRes());
                            return;
                        }
                        showNoDataView();
                    }
                });
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        MyStoreAdapter mAdapter = new MyStoreAdapter(mSourceList);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                if (isSelected) {
                    Intent storeIntent = new Intent();
                    storeIntent.putExtra("Id", mSourceList.get(i).getStoreID());
                    storeIntent.putExtra("Name", mSourceList.get(i).getStoreName());
                    storeIntent.putExtra("Type", mSourceList.get(i).getStoreType());
                    setResult(Activity.RESULT_OK, storeIntent);
                    finish();
                }
            }
        });
        return mAdapter;
    }
}
