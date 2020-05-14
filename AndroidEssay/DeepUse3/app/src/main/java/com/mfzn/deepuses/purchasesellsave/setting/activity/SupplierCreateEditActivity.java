package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.request.SupplierRequest;
import com.mfzn.deepuses.bean.response.settings.SupplierListResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class SupplierCreateEditActivity extends BasicActivity {
    private String shopId;

    @BindView(R.id.supplier_name)
    EditText mSupplierName;

    @BindView(R.id.contact_name)
    EditText mContactName;

    @BindView(R.id.contact_phone)
    EditText mContactPhone;
    @BindView(R.id.address)
    EditText mAddress;
    @BindView(R.id.remark)
    EditText mRemark;
    @BindView(R.id.submit)
    TextView mSubmit;
    private boolean isCreate;

    private SupplierRequest mSupplier;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        mSupplier = (SupplierRequest) getIntent().getSerializableExtra(ParameterConstant.SUPPLIER);
        shopId = getIntent().getStringExtra(ParameterConstant.SHOP_ID);
        isCreate = mSupplier == null;
        if (mSupplier != null) {
            mTitleBar.updateTitleBar("新增供应商", R.mipmap.work_delete2);
            mSupplierName.setText(mSupplier.getSupplierName());
            mContactName.setText(mSupplier.getChargePerson());
            mContactPhone.setText(mSupplier.getChargePersonPhone());
            mAddress.setText(mSupplier.getContactAddress());
            //  mRemark.setText(mStoreResponse.getRemark());
        } else {
            mTitleBar.updateTitleBar("新增供应商");
            mSupplier = new SupplierRequest();
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_store_create;
    }

    protected void rightPressed() {
        ApiServiceManager.delSupplier(shopId, mSupplier.getSupplierID())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtil.showToast(SupplierCreateEditActivity.this, error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult reuslt) {
                        ToastUtil.showToast(SupplierCreateEditActivity.this, "成功");
                        setResult(RESULT_OK);
                        finish();
                    }
                });
    }

    @OnClick(R.id.submit)
    public void onClick(View v) {
        if (isCreate) {
            ApiServiceManager.addSupplier(shopId, mSupplier)
                    .compose(XApi.getApiTransformer())
                    .compose(XApi.getScheduler())
                    .compose(bindToLifecycle())
                    .subscribe(new ApiSubscriber<HttpResult>() {
                        @Override
                        protected void onFail(NetError error) {
                            ToastUtil.showToast(SupplierCreateEditActivity.this, error.getMessage());
                        }

                        @Override
                        public void onNext(HttpResult reuslt) {
                            ToastUtil.showToast(SupplierCreateEditActivity.this, "成功");
                            setResult(RESULT_OK);
                            finish();
                        }
                    });
        } else {
            ApiServiceManager.editSupplier(shopId, mSupplier)
                    .compose(XApi.getApiTransformer())
                    .compose(XApi.getScheduler())
                    .compose(bindToLifecycle())
                    .subscribe(new ApiSubscriber<HttpResult>() {
                        @Override
                        protected void onFail(NetError error) {
                            ToastUtil.showToast(SupplierCreateEditActivity.this, error.getMessage());
                        }

                        @Override
                        public void onNext(HttpResult reuslt) {
                            ToastUtil.showToast(SupplierCreateEditActivity.this, "成功");
                            setResult(RESULT_OK);
                            finish();
                        }
                    });

        }
    }
}
