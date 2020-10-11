package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.khgl.CustomerMangerActivity;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.settings.CustomerListResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.SettingCustomerAdapter;
import com.mfzn.deepuses.utils.PhoneUtils;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class SettingCustomerMangerActivity extends BasicListActivity<CustomerListResponse.CustomerResponse> {

    public static int CUSTOMER_CREATED = 101;
    public static int CUSTOMER_EDIT = 102;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("客户", R.mipmap.icon_titlebar_add);
        initSearch("搜索客户编号、名称、联系人、电话");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_base_search_list;
    }


    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.getCustomerList("")
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<CustomerListResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<CustomerListResponse> reuslt) {
                        CustomerListResponse response = reuslt.getRes();
                        if (response != null) {
                            if (!ListUtil.isEmpty(response.getData())) {
                                refreshSource(response.getData());
                                return;
                            }
                        }
                        showNoDataView();
                    }
                });
    }


    @Override
    protected void searchAction(String keyword) {
        showDialog();
        ApiServiceManager.getCustomerList(keyword)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<CustomerListResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showToast(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<CustomerListResponse> reuslt) {
                        CustomerListResponse response = reuslt.getRes();
                        if (response != null) {
                            if (!ListUtil.isEmpty(response.getData())) {
                                refreshSearchSource(response.getData());
                                return;
                            }
                        }
                        showToast("没有相关搜索内容");
                    }
                });
    }


    @Override
    protected void rightPressedAction() {
        Intent intent = new Intent(this, CreateSetCustomerActivity.class);
        startActivityForResult(intent, CUSTOMER_CREATED);
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        SettingCustomerAdapter mAdapter = new SettingCustomerAdapter(mSourceList);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int i) {
                if (view.getId() == R.id.icon_phone) {
                    String phone = mSourceList.get(i).getCustomerPhone();
                    if (TextUtils.isEmpty(phone)) {
                        showToast("号码为空");
                    } else {
                        PhoneUtils.dialogPhone2(SettingCustomerMangerActivity.this, "拨打", phone, phone);
                    }
                } else {
                    Intent intent = new Intent(SettingCustomerMangerActivity.this, CustomerDetailActivity.class);
                    intent.putExtra(ParameterConstant.CUSTOMER, mSourceList.get(i));
                    startActivityForResult(intent, CUSTOMER_EDIT);
                }
            }
        });
        return mAdapter;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            getResourceList();
        }
    }
}
