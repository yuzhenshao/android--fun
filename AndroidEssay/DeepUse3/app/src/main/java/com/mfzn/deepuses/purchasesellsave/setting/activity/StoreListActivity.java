package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.settings.StoreResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.StoreAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class StoreListActivity extends BasicListActivity<StoreResponse> {

    private static int REQUESTCODE = 2000;
    private boolean isSelected;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("仓库", R.mipmap.icon_titlebar_add);
        isSelected = getIntent().getBooleanExtra(ParameterConstant.IS_SELECTED, false);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_store_list;
    }


    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.getStoreList()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<StoreResponse>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<List<StoreResponse>> reuslt) {
                        refreshSource(reuslt.getRes());
                    }
                });
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        StoreAdapter mAdapter = new StoreAdapter(this, mSourceList);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                if (isSelected) {
                    Intent storeIntent = new Intent();
                    storeIntent.putExtra(ParameterConstant.STORE, mSourceList.get(i));
                    setResult(Activity.RESULT_OK, storeIntent);
                    finish();
                } else {
                    Intent intent = new Intent(StoreListActivity.this, StoreCreateEditActivity.class);
                    intent.putExtra(ParameterConstant.STORE, mSourceList.get(i));
                    startActivityForResult(intent, REQUESTCODE);
                }
            }
        });
        return mAdapter;
    }

    protected void rightPressedAction() {
        startActivityForResult(new Intent(this, StoreCreateEditActivity.class), REQUESTCODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE && resultCode == RESULT_OK) {
            getResourceList();
        }
    }
}
