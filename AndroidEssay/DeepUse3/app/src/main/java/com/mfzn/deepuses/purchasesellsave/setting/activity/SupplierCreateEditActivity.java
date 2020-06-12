package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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

    private SupplierListResponse.SupplierResponse supplier;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        supplier = (SupplierListResponse.SupplierResponse) getIntent().getSerializableExtra(ParameterConstant.SUPPLIER);
        isCreate = supplier == null;
        if (supplier != null) {
            mTitleBar.updateTitleBar("新增供应商", R.mipmap.work_delete2);
            mSupplierName.setText(supplier.getSupplierName());
            mContactName.setText(supplier.getChargePerson());
            mContactPhone.setText(supplier.getChargePersonPhone());
            mAddress.setText(supplier.getContactAddress());
            mRemark.setText(supplier.getRemark());
        } else {
            mTitleBar.updateTitleBar("新增供应商");
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_supplier_create;
    }

    protected void rightPressedAction() {
        ApiServiceManager.delSupplier(supplier.getSupplierID())
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
        SupplierRequest mSupplier = new SupplierRequest();
        mSupplier.setSupplierName(mSupplierName.getText().toString());
        if(TextUtils.isEmpty(mSupplier.getSupplierName())){
            showToast("请输入供应商名称");
            return;
        }
        mSupplier.setChargePerson(mContactName.getText().toString());
        mSupplier.setChargePersonPhone(mContactPhone.getText().toString());
        mSupplier.setContactAddress(mAddress.getText().toString());
        mSupplier.setRemark(mRemark.getText().toString());
        if (isCreate) {
            ApiServiceManager.addSupplier(mSupplier)
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
            mSupplier.setSupplierID(supplier.getSupplierID());
            ApiServiceManager.editSupplier(mSupplier)
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
