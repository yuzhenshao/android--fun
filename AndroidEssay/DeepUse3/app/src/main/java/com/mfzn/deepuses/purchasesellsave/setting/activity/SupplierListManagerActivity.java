package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.settings.SupplierListResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.SupplierAdapter;
import com.mfzn.deepuses.utils.PhoneUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class SupplierListManagerActivity extends BasicListActivity<SupplierListResponse.SupplierResponse> {

    private static int REQUESTCODE = 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("供应商", R.mipmap.icon_titlebar_add);
        initSearch("搜索供应商编号、名称、联系人、电话");
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
                if (view.getId() == R.id.phone) {
                    String phone = mSourceList.get(i).getChargePersonPhone();
                    if (TextUtils.isEmpty(phone)) {
                        showToast("号码为空");
                    } else {
                        PhoneUtils.dialogPhone2(SupplierListManagerActivity.this, "拨打", phone, phone);
                    }
                } else {
                    Intent intent = new Intent(SupplierListManagerActivity.this, SupplierCreateEditActivity.class);
                    intent.putExtra(ParameterConstant.SUPPLIER, mSourceList.get(i));
                    startActivityForResult(intent, REQUESTCODE);
                }
            }
        });
        return mAdapter;
    }

    protected void searchAction(String keyword) {
        ApiServiceManager.searchSupplierList(keyword)
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
                                refreshSearchSource(response.getData());
                                return;
                            }
                        }
                        showToast("搜索失败");
                    }
                });
    }


    protected void rightPressedAction() {
        startActivityForResult(new Intent(SupplierListManagerActivity.this, SupplierCreateEditActivity.class), REQUESTCODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE && resultCode == RESULT_OK) {
            getResourceList();
        }
    }
}
