package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.myteam.SelectManageActivity;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.settings.StoreResponse;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class StoreCreateEditActivity extends BasicActivity {
    @BindView(R.id.edit_container)
    LinearLayout editBtnContainer;
    @BindView(R.id.edit_btn)
    TextView mEditBtn;
    @BindView(R.id.delete_btn)
    TextView mDeleteBtn;

    @BindView(R.id.store_name)
    EditText mStoreName;
    @BindView(R.id.contact_name_select)
    LinearLayout mContactNameSelect;
    @BindView(R.id.contact_name)
    EditText mContactName;
    @BindView(R.id.contact_phone_select)
    LinearLayout mContactPhoneSelect;
    @BindView(R.id.contact_phone)
    EditText mContactPhone;
    @BindView(R.id.address)
    EditText mAddress;
    @BindView(R.id.remark)
    EditText mRemark;
    @BindView(R.id.submit)
    TextView mSubmit;

    private StoreResponse mStoreResponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        mStoreResponse = (StoreResponse) getIntent().getSerializableExtra(ParameterConstant.STORE);
        mTitleBar.updateTitleBar(mStoreResponse == null ? "新增仓库" : "编辑仓库");
        editBtnContainer.setVisibility(mStoreResponse == null ? View.GONE : View.VISIBLE);
        mSubmit.setVisibility(mStoreResponse == null ? View.VISIBLE : View.GONE);
        if (mStoreResponse != null) {
            mStoreName.setText(mStoreResponse.getStoreName());
            mStoreName.setEnabled(false);
            mContactName.setText(mStoreResponse.getUserName());
            mContactPhone.setText(mStoreResponse.getContactPhone());
            mAddress.setText(mStoreResponse.getStoreAddress());
            mRemark.setText(mStoreResponse.getRemark());
        } else {
            mStoreResponse = new StoreResponse();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.ADD_MANAGE == requestCode) {
            if (data != null) {
                ZuzhiJiagouModel.StaffBean staffBean = (ZuzhiJiagouModel.StaffBean) data.getSerializableExtra(Constants.STAFFBEAN);
                mStoreResponse.setChargePersonUserID(staffBean.getUserID());
                mStoreResponse.setContactPhone(staffBean.getUserPhone());
                mContactName.setText(staffBean.getStaffName());
                mContactPhone.setText(staffBean.getUserPhone());
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_store_create;
    }

    @OnClick({R.id.contact_name_select, R.id.contact_phone_select, R.id.submit, R.id.edit_btn, R.id.delete_btn})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contact_name_select:
            case R.id.contact_phone_select:
                Intent intent = new Intent(this, SelectManageActivity.class);
                intent.putExtra(Constants.SINGLE, true);
                startActivityForResult(intent, Constants.ADD_MANAGE);
                break;
            case R.id.submit:
                updateStore();
                if (TextUtils.isEmpty(mStoreResponse.getStoreName())) {
                    showToast("请输入仓库名称");
                    return;
                }
                ApiServiceManager.addStore(mStoreResponse)
                        .compose(XApi.getApiTransformer())
                        .compose(XApi.getScheduler())
                        .compose(bindToLifecycle())
                        .subscribe(new ApiSubscriber<HttpResult>() {
                            @Override
                            protected void onFail(NetError error) {
                                ToastUtil.showToast(StoreCreateEditActivity.this, error.getMessage());
                            }

                            @Override
                            public void onNext(HttpResult reuslt) {
                                ToastUtil.showToast(StoreCreateEditActivity.this, "成功");
                                setResult(RESULT_OK);
                                finish();
                            }
                        });
                break;
            case R.id.edit_btn:
                updateStore();
                if (TextUtils.isEmpty(mStoreResponse.getStoreName())) {
                    showToast("请输入仓库名称");
                    return;
                }
                ApiServiceManager.editStore(mStoreResponse)
                        .compose(XApi.getApiTransformer())
                        .compose(XApi.getScheduler())
                        .compose(bindToLifecycle())
                        .subscribe(new ApiSubscriber<HttpResult>() {
                            @Override
                            protected void onFail(NetError error) {
                                ToastUtil.showToast(StoreCreateEditActivity.this, error.getMessage());
                            }

                            @Override
                            public void onNext(HttpResult reuslt) {
                                ToastUtil.showToast(StoreCreateEditActivity.this, "成功");
                                setResult(RESULT_OK);
                                finish();
                            }
                        });
                break;
            case R.id.delete_btn:
                updateStore();
                if (TextUtils.isEmpty(mStoreResponse.getStoreName())) {
                    showToast("请输入仓库名称");
                    return;
                }
                ApiServiceManager.delStore(mStoreResponse.getStoreID())
                        .compose(XApi.getApiTransformer())
                        .compose(XApi.getScheduler())
                        .compose(bindToLifecycle())
                        .subscribe(new ApiSubscriber<HttpResult>() {
                            @Override
                            protected void onFail(NetError error) {
                                ToastUtil.showToast(StoreCreateEditActivity.this, error.getMessage());
                            }

                            @Override
                            public void onNext(HttpResult reuslt) {
                                ToastUtil.showToast(StoreCreateEditActivity.this, "成功");
                                setResult(RESULT_OK);
                                finish();
                            }
                        });
                break;
        }
    }

    private void updateStore() {
        mStoreResponse.setStoreName(mStoreName.getText().toString());
        //mStoreResponse.setChargePersonUserID("");//TODO
        mStoreResponse.setContactPhone(mContactPhone.getText().toString());
        mStoreResponse.setStoreAddress(mAddress.getText().toString());
        mStoreResponse.setRemark(mRemark.getText().toString());
    }
}
