package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.request.setting.AddMoneyAccountRequest;
import com.mfzn.deepuses.bean.response.capital.IncomeExpenseListResponse;
import com.mfzn.deepuses.common.PickerDialogView;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class AddMoneyAccountActivity extends BasicActivity {

    @BindView(R.id.account_name)
    EditText accountNameEdit;
    @BindView(R.id.account_type)
    EditText accountTypeEdit;
    @BindView(R.id.account_money)
    EditText aaccountMoneyEdit;

    private AddMoneyAccountRequest mRequest = new AddMoneyAccountRequest();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("新增账户");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_money_account;
    }

    @OnClick({R.id.account_type_select, R.id.btn_commit})
    public void viewClick(View v) {
        switch (v.getId()) {
            case R.id.account_type_select:
                List<String> typeList = new ArrayList<>();
                //1.现金；2.银行账户；3.微信；4.支付宝；
                typeList.add("现金");
                typeList.add("银行账户");
                typeList.add("微信");
                typeList.add("支付宝");
                PickerDialogView.showGoodSPosition(this,
                        typeList, new OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int options2, int options3, View v1) {
                                accountTypeEdit.setText(typeList.get(options1));
                                mRequest.setAccountType(options1 + 1);
                            }
                        });
                break;
            case R.id.btn_commit:
                onCommitAction();
                break;
        }
    }

    private void onCommitAction() {
        mRequest.setAccountName(accountNameEdit.getText().toString());
        mRequest.setCreateMoney(aaccountMoneyEdit.getText().toString());

        if (mRequest.getAccountType() == 0) {
            showToast("请输入账户类型");
            return;
        }
        if (TextUtils.isEmpty(mRequest.getAccountName())) {
            showToast("请输入账户名称");
            return;
        }

        ApiServiceManager.addMoneyAccount(mRequest)
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
                        setResult(Activity.RESULT_OK);
                        finish();
                    }
                });
    }


}
