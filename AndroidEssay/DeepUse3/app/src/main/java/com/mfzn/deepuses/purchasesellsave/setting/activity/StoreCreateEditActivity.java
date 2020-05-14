package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.settings.StoreResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class StoreCreateEditActivity extends BasicActivity {
    private String shopId;
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
        shopId = getIntent().getStringExtra(ParameterConstant.SHOP_ID);
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
        //TODO 获得选择姓名和手机
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_store_create;
    }

    //TODO
    @OnClick({R.id.contact_name_select, R.id.contact_phone_select, R.id.submit, R.id.edit_btn, R.id.delete_btn})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contact_name_select:
                //startActivity(new Intent(this, GoodsCategoryActivity.class));
                break;
            case R.id.contact_phone_select:
                // startActivity(new Intent(this, GoodsUnitListActivity.class));
                break;
            case R.id.submit:
                ApiServiceManager.addStore(shopId, mStoreResponse)
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
                ApiServiceManager.editStore(shopId, mStoreResponse)
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
                ApiServiceManager.delStore(shopId, mStoreResponse.getStoreID())
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
}
