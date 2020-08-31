package com.mfzn.deepuses.purchasesellsave.capital.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.request.capital.AddBorrowRequest;
import com.mfzn.deepuses.common.PickerDialogView;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.activity.MoneyAccountListActivity;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class AddBorrowActivity extends BasicActivity {

    @BindView(R.id.borrow_type)
    EditText borrowTypeEdit;
    @BindView(R.id.price)
    EditText priceEdit;
    @BindView(R.id.account)
    EditText accountEdit;
    @BindView(R.id.borrow_user)
    EditText borrowUserEdit;
    @BindView(R.id.order_time)
    EditText orderTimeEdit;
    @BindView(R.id.data_user_name)
    EditText dataUserNameEdit;
    @BindView(R.id.remark)
    EditText remarkEdit;

    private final static int ACCOUNT = 101;
    private AddBorrowRequest mAddBorrowRequest = new AddBorrowRequest();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("新增借贷");
        dataUserNameEdit.setText(UserHelper.getU_name());
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_add_borrow;
    }


    @OnClick({R.id.borrow_type_select, R.id.account_select, R.id.order_time_select, R.id.btn_commit})
    public void viewClick(View v) {
        switch (v.getId()) {
            case R.id.borrow_type_select:
                List<String> typeList = new ArrayList<>();
                typeList.add("借入");
                typeList.add("借出");
                PickerDialogView.showGoodSPosition(this,
                        typeList, new OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int options2, int options3, View v1) {
                                borrowTypeEdit.setText(typeList.get(options1));
                                mAddBorrowRequest.setType(options1 + 1);
                            }
                        });
                break;
            case R.id.account_select:
                Intent intent = new Intent();
                intent.setClass(this, MoneyAccountListActivity.class);
                intent.putExtra(ParameterConstant.IS_SELECTED, true);
                startActivityForResult(intent, ACCOUNT);
                break;
            case R.id.order_time_select:
                PickerDialogView.showTimeSelect(this, new OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mAddBorrowRequest.setDataTime((date.getTime() / 1000));
                        orderTimeEdit.setText(DateUtils.dateFormat("yyyy/MM/dd", date));
                    }
                });
                break;
            case R.id.btn_commit:
                onCommitAction();
                break;
        }
    }

    private void onCommitAction() {
        mAddBorrowRequest.setMoney(priceEdit.getText().toString());
        mAddBorrowRequest.setBorrowUser(borrowUserEdit.getText().toString());
        mAddBorrowRequest.setDataUserID(UserHelper.getUserId());
        mAddBorrowRequest.setRemark(remarkEdit.getText().toString());

        if (mAddBorrowRequest.getType() == 0) {
            showToast("请输入借贷类型");
            return;
        }
        if (TextUtils.isEmpty(mAddBorrowRequest.getMoney())) {
            showToast("请输入借贷金额");
            return;
        }
        if (TextUtils.isEmpty(mAddBorrowRequest.getMoneyAccountID())) {
            showToast("请输入借贷账户");
            return;
        }
        if (TextUtils.isEmpty(mAddBorrowRequest.getBorrowUser())) {
            showToast("请输入借贷人");
            return;
        }
        if (mAddBorrowRequest.getDataTime() == 0) {
            showToast("请输入单据日期");
            return;
        }
        ApiServiceManager.addBorrow(mAddBorrowRequest)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        showToast(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult reuslt) {
                        showToast("新增成功");
                        finish();
                    }
                });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            if (requestCode == ACCOUNT) {
                mAddBorrowRequest.setMoneyAccountID(data.getStringExtra("Id"));
                accountEdit.setText(data.getStringExtra("Name"));
            }
        }
    }
}
