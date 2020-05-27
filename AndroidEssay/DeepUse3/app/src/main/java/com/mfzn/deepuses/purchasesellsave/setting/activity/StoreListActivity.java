package com.mfzn.deepuses.purchasesellsave.setting.activity;

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
    @BindView(R.id.serach_edit)
    EditText serachEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serachEdit.setHint("搜索仓库名称、编码、联系人、电话");
        mTitleBar.updateTitleBar("仓库", R.mipmap.icon_titlebar_add);
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
                Intent intent=  new Intent(StoreListActivity.this, StoreCreateEditActivity.class);
                intent.putExtra(ParameterConstant.STORE,mSourceList.get(i));
                startActivityForResult(intent,REQUESTCODE);
            }
        });
        return mAdapter;
    }

    @OnClick(R.id.search_container)
    public void turnToSearch() {

    }

    protected void rightPressedAction() {
        startActivityForResult(new Intent(this, StoreCreateEditActivity.class), REQUESTCODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE && requestCode == RESULT_OK) {
            getResourceList();
        }
    }
}
