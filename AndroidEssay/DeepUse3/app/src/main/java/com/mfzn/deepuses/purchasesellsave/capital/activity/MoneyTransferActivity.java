package com.mfzn.deepuses.purchasesellsave.capital.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.request.capital.MoneyTransferRequest;
import com.mfzn.deepuses.common.PickerDialogView;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.activity.MoneyAccountListActivity;
import com.mfzn.deepuses.utils.DateUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class MoneyTransferActivity extends BasicActivity {

    @BindView(R.id.money_out_account)
    TextView moneyOutAccountView;
    @BindView(R.id.out_account_money)
    TextView outAccountMoneyView;
    @BindView(R.id.transfer_money)
    EditText transferMoneyEdit;
    @BindView(R.id.money_in_account)
    EditText moneyInAccountEdit;
    @BindView(R.id.in_account_money)
    TextView inAccountMoneyView;
    @BindView(R.id.transfer_money_date)
    EditText transferMoneyDateEdit;
    @BindView(R.id.remark)
    EditText remarkEdit;
    private static int ACCOUNT=101;
    private MoneyTransferRequest mRequest=new MoneyTransferRequest();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("转账");
        mRequest.setFromAccountID(getIntent().getStringExtra(ParameterConstant.ACCOUNT_ID));
        moneyOutAccountView.setText(getIntent().getStringExtra(ParameterConstant.ACCOUNT_NAME));
        outAccountMoneyView.setText(getIntent().getStringExtra(ParameterConstant.ACCOUNT_MONEY)+"元");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_money_transfer;
    }

    @OnClick({R.id.money_in_select, R.id.transfer_money_date_select,R.id.btn_commit})
    public void viewClick(View v) {
        switch (v.getId()) {
            case R.id.transfer_money_date_select:
                PickerDialogView.showTimeSelect(this, new OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mRequest.setTransferDate((date.getTime() / 1000));
                        transferMoneyDateEdit.setText(DateUtils.dateFormat("yyyy/MM/dd", date));
                    }
                });

                break;
            case R.id.money_in_select:
                Intent intent = new Intent();
                intent.setClass(this, MoneyAccountListActivity.class);
                intent.putExtra(ParameterConstant.IS_SELECTED, true);
                startActivityForResult(intent, ACCOUNT);
                break;
            case R.id.btn_commit:
                onCommitAction();
                break;
        }
    }

    private void onCommitAction() {
        mRequest.setMoney(transferMoneyEdit.getText().toString());
        mRequest.setRemark(remarkEdit.getText().toString());

        if (TextUtils.isEmpty(mRequest.getMoney())) {
            showToast("请输入转账金额");
            return;
        }
        if (mRequest.getTransferDate()==0) {
            showToast("请输入转账日期");
            return;
        }
        if (TextUtils.isEmpty(mRequest.getToAccountID())) {
            showToast("请输入转入账户");
            return;
        }
        ApiServiceManager.moneyTransfer(mRequest)
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
                        showToast("转账成功");
                        finish();
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            if (requestCode == ACCOUNT) {
                mRequest.setToAccountID(data.getStringExtra("Id"));
                moneyInAccountEdit.setText(data.getStringExtra("Name"));
                inAccountMoneyView.setText(data.getStringExtra("Money")+"元");
            }
        }
    }
}
