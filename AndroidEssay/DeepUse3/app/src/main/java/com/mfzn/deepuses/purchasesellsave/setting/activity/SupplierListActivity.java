package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.response.settings.SupplierListResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.SupplierAdapter;
import butterknife.BindView;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class SupplierListActivity extends BasicListActivity<SupplierListResponse.SupplierResponse> {

    @BindView(R.id.search_container)
    LinearLayout searchContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("供应商");
        searchContainer.setVisibility(View.GONE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_supplier_list;
    }


    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.getSupplierList()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<SupplierListResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<SupplierListResponse> reuslt) {
                        SupplierListResponse response = reuslt.getRes();
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
        SupplierAdapter mAdapter = new SupplierAdapter(this, mSourceList);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int i) {
                SupplierListResponse.SupplierResponse supplierResponse=mSourceList.get(i);
                Intent intent = new Intent();
                intent.putExtra("Id", supplierResponse.getSupplierID());
                intent.putExtra("Name", supplierResponse.getSupplierName());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        return mAdapter;
    }
}
