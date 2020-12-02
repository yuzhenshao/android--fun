package com.mfzn.deepuses.purchasesellsave.capital.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.capital.PayerPayeeDetailResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.activity.MoneyAccountListActivity;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import io.reactivex.Flowable;

public class GatherePayActivity extends BasicActivity implements View.OnClickListener {
    private TextView mPayType;
    private EditText mRecordNum;
    private EditText mMoney;
    private TextView mHasMoneyName;
    private EditText mHasMoney;
    private TextView mNeedMoneyName;
    private EditText mNeedMoney;
    private EditText mAccount;
    private ImageView mOrderTimeSelect;
    private Button mBtnCommit;
    private final static int ACCOUNT = 101;
    private int capitalType;
    private String orderNum;
    private String money;
    private String hasDownMoney;
    private String dataID;
    private String accountId;
    private String showName;

    @Override
    public int getLayoutId() {
        return R.layout.activity_gathere_pay;
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        showName = getIntent().getStringExtra(ParameterConstant.SUPPLIER);
        orderNum=getIntent().getStringExtra(ParameterConstant.ORDER_NUM);
        money=getIntent().getStringExtra(ParameterConstant.MONEY);
        hasDownMoney=getIntent().getStringExtra(ParameterConstant.HAS_DOWN_MONEY);
        dataID=getIntent().getStringExtra(ParameterConstant.ORDER_ID);
        capitalType = getIntent().getIntExtra(ParameterConstant.CAPITAL_TYPE, 0);
        initView();
    }

    private void initView() {
        mPayType = (TextView) findViewById(R.id.pay_type);
        mRecordNum = (EditText) findViewById(R.id.record_num);
        mMoney = (EditText) findViewById(R.id.money);
        mHasMoneyName = (TextView) findViewById(R.id.has_money_name);
        mHasMoney = (EditText) findViewById(R.id.has_money);
        mNeedMoneyName = (TextView) findViewById(R.id.need_money_name);
        mNeedMoney = (EditText) findViewById(R.id.need_money);
        mAccount = (EditText) findViewById(R.id.account);
        mOrderTimeSelect = (ImageView) findViewById(R.id.order_time_select);
        mBtnCommit = (Button) findViewById(R.id.btn_commit);
        mBtnCommit.setOnClickListener(this);
        mOrderTimeSelect.setOnClickListener(this);
        mPayType.setText(capitalType == 0 ? "收款" : "付款");
        mRecordNum.setText(orderNum);
        mMoney.setText(money);
        mHasMoneyName.setText(capitalType == 0 ? "已收金额" : "已付金额");
        mHasMoney.setText(hasDownMoney);
        mNeedMoneyName.setText(capitalType == 0 ? "收款金额" : "付款金额");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_commit:
                commitAction();
                break;
            case R.id.order_time_select:
                Intent intent = new Intent(this, MoneyAccountListActivity.class);
                intent.putExtra(ParameterConstant.IS_SELECTED, true);
                startActivityForResult(intent, ACCOUNT);
                break;
        }
    }

    private void commitAction() {
        if (TextUtils.isEmpty(accountId)) {
            showToast(" 请选择账号");
            return;
        }
        String money = mNeedMoney.getText().toString();
        if (TextUtils.isEmpty(money)) {
            showToast(" 请输入金额");
            return;
        }
        Flowable<HttpResult> todoFloeable;
        if (capitalType == 0) {
            todoFloeable = ApiServiceManager.shouldGatheringDoGathering(dataID, accountId, showName, money);
        } else {
            todoFloeable = ApiServiceManager.shouldPayDoPay(dataID, accountId, showName, money);
        }
        todoFloeable.compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        showToast(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult reuslt) {
                        showToast(reuslt.getMsg());
                        setResult(RESULT_OK);
                        finish();
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            if (requestCode == ACCOUNT) {
                accountId = data.getStringExtra("Id");
                mAccount.setText(data.getStringExtra("Name"));
            }
        }
    }
}
