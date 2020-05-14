package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.settings.GoodsListResponse;
import com.mfzn.deepuses.bean.response.settings.StoreResponse;
import com.mfzn.deepuses.bean.response.settings.SupplierListResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.StoreAdapter;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.SupplierAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class SupplierListActivity extends BasicListActivity<SupplierListResponse.SupplierResponse> {

    private String shopId;
    private static int REQUESTCODE = 1000;
    @BindView(R.id.serach_edit)
    EditText serachEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serachEdit.setHint("搜索供应商编号、名称、联系人、电话");
        mTitleBar.updateTitleBar("供应商", R.mipmap.icon_titlebar_add);
        shopId = getIntent().getStringExtra(ParameterConstant.SHOP_ID);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_supplier_list;
    }


    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.getSupplierList(shopId)
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
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                Intent intent=  new Intent(SupplierListActivity.this, SupplierCreateEditActivity.class);
                intent.putExtra(ParameterConstant.SUPPLIER,mSourceList.get(i));
                startActivityForResult(new Intent(SupplierListActivity.this, SupplierCreateEditActivity.class), REQUESTCODE);
            }
        });
        return mAdapter;
    }

    @OnClick(R.id.search_container)
    public void turnToSearch() {

    }

    protected void rightPressed() {
        startActivityForResult(new Intent(SupplierListActivity.this, SupplierCreateEditActivity.class), REQUESTCODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE && requestCode == RESULT_OK) {
            getResourceList();
        }
    }
}
