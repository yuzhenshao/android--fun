package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.request.setting.AddSetCustomerRequest;
import com.mfzn.deepuses.bean.response.settings.CustomerListResponse;
import com.mfzn.deepuses.common.PickerDialogView;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;
import com.wevey.selector.dialog.bean.SelectModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class CreateSetCustomerActivity extends BasicActivity {

    @BindView(R.id.customer_name)
    EditText customerNameEdit;
    @BindView(R.id.customer_phone)
    EditText customerPhoneEdit;
    @BindView(R.id.charge_person_name)
    EditText chargePersonNameEdit;
    @BindView(R.id.charge_person_phone)
    EditText chargePersonPhoneEdit;
    @BindView(R.id.level_name)
    EditText levelNameEdit;
    @BindView(R.id.customer_type)
    EditText customerTypeEdit;
    @BindView(R.id.address)
    EditText addressEdit;
    @BindView(R.id.remark)
    EditText remarkEdit;
    private AddSetCustomerRequest mCustomerRequest = new AddSetCustomerRequest();
    private CustomerListResponse.CustomerResponse mCustomerResponse;
    private SelectModel mSelectModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        showDialog();
        mCustomerResponse = (CustomerListResponse.CustomerResponse) getIntent().getSerializableExtra(ParameterConstant.CUSTOMER);
        if (mCustomerResponse == null) {
            mTitleBar.updateTitleBar("添加客户");
        } else {
            mTitleBar.updateTitleBar("编辑客户", R.mipmap.work_delete2);
        }
        if (mCustomerResponse != null) {
            mCustomerRequest.setCompanyCustomerID(mCustomerResponse.getData_id());
            mCustomerRequest.setCustomerLevelID(mCustomerResponse.getCustomerLevelID());
            mCustomerRequest.setCustomerType(mCustomerResponse.getCustomerType()+"");

            customerNameEdit.setText(mCustomerResponse.getCustomerName());
            customerPhoneEdit.setText(mCustomerResponse.getCustomerPhone());
            chargePersonNameEdit.setText(mCustomerResponse.getChargePersonName());
            chargePersonPhoneEdit.setText(mCustomerResponse.getChargePersonPhone());
            levelNameEdit.setText(mCustomerResponse.getLevelName());
            customerTypeEdit.setText(mCustomerResponse.getCustomerTypeName());
            addressEdit.setText(mCustomerResponse.getAddress());
            remarkEdit.setText(mCustomerResponse.getNote());
        }

        ApiHelper.getApiService().getSelect(UserHelper.getToken(), UserHelper.getUid(), UserHelper.getCompanyId())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .subscribe(new ApiSubscriber<HttpResult<SelectModel>>() {
                    @Override
                    protected void onFail(NetError error) {
                        hideDialog();
                    }

                    @Override
                    public void onNext(HttpResult<SelectModel> reuslt) {
                        hideDialog();
                        mSelectModel = reuslt.getRes();
                    }
                });

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_setting_customer;
    }

    @OnClick({R.id.level_name_selected, R.id.customer_type_selected, R.id.btn_commit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.level_name_selected:
                List<String> leavelList = new ArrayList<>();
                if (mSelectModel == null || ListUtil.isEmpty(mSelectModel.getCustomerLevel())) {
                    showToast("没有相关等级数据");
                    return;
                }
                for (SelectModel.CustomerLevelBean levelBean : mSelectModel.getCustomerLevel()) {
                    leavelList.add(levelBean.getLevelName());
                }
                PickerDialogView.showGoodSPosition(this,
                        leavelList, new OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int options2, int options3, View v1) {
                                SelectModel.CustomerLevelBean levelBean = mSelectModel.getCustomerLevel().get(options1);
                                levelNameEdit.setText(levelBean.getLevelName());
                                mCustomerRequest.setCustomerLevelID(levelBean.getData_id() + "");
                            }
                        });
                break;
            case R.id.customer_type_selected:
                List<String> typeList = new ArrayList<>();
                typeList.add("普通客户");
                typeList.add("合作客户");

                PickerDialogView.showGoodSPosition(this,
                        typeList, new OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int options2, int options3, View v1) {
                                customerTypeEdit.setText(typeList.get(options1));
                                mCustomerRequest.setCustomerType(options1 + 1 + "");
                            }
                        });
                break;
            case R.id.btn_commit:
                updateRequest();
                if (TextUtils.isEmpty(mCustomerRequest.getCustomerName())) {
                    showToast("请输入客户名称");
                    return;
                }
                if (TextUtils.isEmpty(mCustomerRequest.getCustomerPhone())) {
                    showToast("请输入客户电话");
                    return;
                }
                if (TextUtils.isEmpty(mCustomerRequest.getCustomerLevelID())) {
                    showToast("请输入客户等级");
                    return;
                }
                if (mCustomerResponse == null) {
                    addSetCustomer();
                } else {
                    editSetCustomer();
                }
                break;
        }
    }

    private void addSetCustomer() {
        ApiServiceManager.addSetCustomer(mCustomerRequest)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtil.showToast(CreateSetCustomerActivity.this, error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult reuslt) {
                        ToastUtil.showToast(CreateSetCustomerActivity.this, "成功");
                        setResult(RESULT_OK);
                        finish();
                    }
                });
    }

    private void editSetCustomer() {
        ApiServiceManager.editSetCustomer(mCustomerRequest)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtil.showToast(CreateSetCustomerActivity.this, error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult reuslt) {
                        ToastUtil.showToast(CreateSetCustomerActivity.this, "成功");
                        Intent intent=new Intent();
                        intent.putExtra(ParameterConstant.CUSTOMER,mCustomerRequest);
                        intent.putExtra("Name",levelNameEdit.getText().toString());
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                });
    }

    protected void rightPressedAction() {
        ApiServiceManager.delSetCustomer(mCustomerRequest.getCompanyCustomerID())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtil.showToast(CreateSetCustomerActivity.this, error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult reuslt) {
                        ToastUtil.showToast(CreateSetCustomerActivity.this, "成功");
                        Intent intent=new Intent();
                        intent.putExtra("IsDeleted",true);
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                });
    }

    private void updateRequest() {
        mCustomerRequest.setCustomerName(customerNameEdit.getText().toString());
        mCustomerRequest.setCustomerPhone(customerPhoneEdit.getText().toString());
        mCustomerRequest.setChargePersonName(chargePersonNameEdit.getText().toString());
        mCustomerRequest.setChargePersonPhone(chargePersonPhoneEdit.getText().toString());
        mCustomerRequest.setAddress(addressEdit.getText().toString());
        mCustomerRequest.setRemark(remarkEdit.getText().toString());
    }
}
