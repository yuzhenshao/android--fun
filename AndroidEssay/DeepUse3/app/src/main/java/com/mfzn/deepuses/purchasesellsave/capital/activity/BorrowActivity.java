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

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.request.purchase.AddBorrowHandleLogRequest;
import com.mfzn.deepuses.bean.response.capital.PayerPayeeDetailResponse;
import com.mfzn.deepuses.common.PickerDialogView;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.activity.MoneyAccountListActivity;
import com.mfzn.deepuses.utils.DateUtils;

import java.util.Date;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import io.reactivex.Flowable;

public class BorrowActivity extends BasicActivity implements View.OnClickListener {

    private final static int ACCOUNT = 101;
    private String accountId;
    private String showName;
    private EditText mMoney;
    private EditText mAccount;
    private ImageView mAccountSelect;
    private EditText mOrderTime;
    private ImageView mOrderTimeSelect;
    private EditText mUserName;
    private Button mBtnCommit;

    private AddBorrowHandleLogRequest mAddBorrowHandleLogRequest=new AddBorrowHandleLogRequest();

    @Override
    public int getLayoutId() {
        return R.layout.activity_borrow;
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        showName = getIntent().getStringExtra(ParameterConstant.SUPPLIER);
        mAddBorrowHandleLogRequest.setBorrowID(getIntent().getStringExtra(ParameterConstant.BORROW_ID));
        initView();
    }

    private void initView() {
        mMoney = (EditText) findViewById(R.id.money);
        mAccount = (EditText) findViewById(R.id.account);
        mAccountSelect = (ImageView) findViewById(R.id.account_select);
        mOrderTime = (EditText) findViewById(R.id.order_time);
        mOrderTimeSelect = (ImageView) findViewById(R.id.order_time_select);
        mUserName = (EditText) findViewById(R.id.user_name);
        mBtnCommit = (Button) findViewById(R.id.btn_commit);
        mAccountSelect.setOnClickListener(this);
        mOrderTimeSelect.setOnClickListener(this);
        mBtnCommit.setOnClickListener(this);
        mUserName.setText(showName);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_commit:
                commitAction();
                break;
            case R.id.account_select:
                Intent intent = new Intent(this, MoneyAccountListActivity.class);
                intent.putExtra(ParameterConstant.IS_SELECTED, true);
                startActivityForResult(intent, ACCOUNT);
                break;
            case R.id.order_time_select:
                PickerDialogView.showTimeSelect(this, new OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mAddBorrowHandleLogRequest.setDataTime((date.getTime() / 1000));
                        mOrderTime.setText(DateUtils.dateFormat("yyyy/MM/dd", date));
                    }
                });
                break;

        }
    }

    private void commitAction() {
        if (TextUtils.isEmpty(accountId)) {
            showToast("请选择账号");
            return;
        }


        String money = mMoney.getText().toString();
        if (TextUtils.isEmpty(money)) {
            showToast(" 请输入金额");
            return;
        }

        mAddBorrowHandleLogRequest.setMoneyAccountID(accountId);
        mAddBorrowHandleLogRequest.setMoney(money);
        ApiServiceManager.addBorrowHandleLog(mAddBorrowHandleLogRequest)
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
