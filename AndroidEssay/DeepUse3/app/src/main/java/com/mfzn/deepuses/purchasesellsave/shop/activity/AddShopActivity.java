package com.mfzn.deepuses.purchasesellsave.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.myteam.SelectManageActivity;
import com.mfzn.deepuses.bass.BasicActivity;
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

public class AddShopActivity extends BasicActivity {

    @BindView(R.id.shop_name)
    EditText shopNameEdit;
    @BindView(R.id.contact_name)
    EditText contactNameEdit;
    @BindView(R.id.contact_phone)
    EditText contactPhoneEdit;
    @BindView(R.id.address)
    EditText addressEdit;
    @BindView(R.id.remark)
    EditText remarkEdit;
    @BindView(R.id.submit)
    TextView mSubmit;

    private String chargePersonUserID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("新增门店");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.ADD_MANAGE == requestCode) {
            if (data != null) {
                ZuzhiJiagouModel.StaffBean staffBean = (ZuzhiJiagouModel.StaffBean) data.getSerializableExtra(Constants.STAFFBEAN);
                chargePersonUserID = staffBean.getUserID();
                contactNameEdit.setText(staffBean.getStaffName());
                contactPhoneEdit.setText(staffBean.getUserPhone());
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_create;
    }

    @OnClick({R.id.contact_name_select, R.id.contact_phone_select, R.id.submit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contact_name_select:
            case R.id.contact_phone_select:
                Intent intent = new Intent(this, SelectManageActivity.class);
                intent.putExtra(Constants.SINGLE, true);
                startActivityForResult(intent, Constants.ADD_MANAGE);
                break;
            case R.id.submit:
                String shopName = shopNameEdit.getText().toString();
                String contactPhone = contactPhoneEdit.getText().toString();
                String shopAddress = addressEdit.getText().toString();
                String remark = remarkEdit.getText().toString();
                if (TextUtils.isEmpty(shopName)) {
                    showToast("请输入门店名称");
                    return;
                }
                if (TextUtils.isEmpty(chargePersonUserID)) {
                    showToast("请输入联系人");
                    return;
                }
                if (TextUtils.isEmpty(contactPhone)) {
                    showToast("请输入联系电话");
                    return;
                }
                if (TextUtils.isEmpty(shopAddress)) {
                    showToast("请输入门店地址");
                    return;
                }
                ApiServiceManager.addShop(shopName, chargePersonUserID, contactPhone, shopAddress, remark)
                        .compose(XApi.getApiTransformer())
                        .compose(XApi.getScheduler())
                        .compose(bindToLifecycle())
                        .subscribe(new ApiSubscriber<HttpResult>() {
                            @Override
                            protected void onFail(NetError error) {
                                ToastUtil.showToast(AddShopActivity.this, error.getMessage());
                            }

                            @Override
                            public void onNext(HttpResult reuslt) {
                                ToastUtil.showToast(AddShopActivity.this, "成功");
                                setResult(RESULT_OK);
                                finish();
                            }
                        });
                break;
        }
    }
}