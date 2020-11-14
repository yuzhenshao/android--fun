package com.mfzn.deepuses.purchasesellsave.capital.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.capital.BorrowInfoResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.DateUtils;

import java.io.Serializable;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ActivityBorrowDetail extends BasicActivity implements View.OnClickListener {
    private Button btnCommit;
    private EditText orderNum;
    private EditText borrowType;
    private EditText price;
    private EditText account;
    private EditText borrowUser;
    private EditText dataTime;
    private EditText dataUserName;
    private TextView remark;
    private EditText stillNeed;
    private EditText hasDone;
    private LinearLayout handleLog;

    private String ordrerId;
    private BorrowInfoResponse mBorrowInfoResponse;
    public final static int REFRESH = 101;

    @Override
    public int getLayoutId() {
        return R.layout.activity_borrow_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("借贷详情");
        initView();
        initData();
    }

    private void initView() {
        btnCommit = (Button) findViewById(R.id.btn_commit);
        handleLog=(LinearLayout)findViewById(R.id.handle_log);
        orderNum = (EditText) findViewById(R.id.order_num);
        borrowType = (EditText) findViewById(R.id.borrow_type);
        price = (EditText) findViewById(R.id.price);
        account = (EditText) findViewById(R.id.account);
        borrowUser = (EditText) findViewById(R.id.borrow_user);
        dataTime = (EditText) findViewById(R.id.data_time);
        dataUserName = (EditText) findViewById(R.id.data_user_name);
        remark = (TextView) findViewById(R.id.remark);
        stillNeed = (EditText) findViewById(R.id.still_need);
        hasDone = (EditText) findViewById(R.id.has_done);
        btnCommit.setOnClickListener(this);
        handleLog.setOnClickListener(this);
    }


    private void initData() {
        ordrerId = getIntent().getStringExtra(ParameterConstant.ORDER_ID);
        ApiServiceManager.borrowInfo(ordrerId)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<BorrowInfoResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showToast(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<BorrowInfoResponse> reuslt) {
                        mBorrowInfoResponse = reuslt.getRes();
                        if (mBorrowInfoResponse != null) {
                            initBorrowDetail();
                        }
                    }
                });
    }

    private void initBorrowDetail() {
        orderNum.setText(mBorrowInfoResponse.getOrderNum());
        borrowType.setText(mBorrowInfoResponse.getDataType() == 1 ? "借入" : "借出");
        price.setText(mBorrowInfoResponse.getMoney());
        account.setText(mBorrowInfoResponse.getAccountTypeName());
        borrowUser.setText(mBorrowInfoResponse.getBorrowUser());
        dataTime.setText(DateUtils.longToString(mBorrowInfoResponse.getDataTime()));
        dataUserName.setText(mBorrowInfoResponse.getDataUserName());
        remark.setText(TextUtils.isEmpty(mBorrowInfoResponse.getRemark())?"无":mBorrowInfoResponse.getRemark());
        stillNeed.setText(mBorrowInfoResponse.getStillNeed());
        hasDone.setText(mBorrowInfoResponse.getHasDone());
    }

    @Override
    public void onClick(View v) {
        if (mBorrowInfoResponse == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.btn_commit:
                Intent intent = new Intent(ActivityBorrowDetail.this, BorrowActivity.class);
                intent.putExtra(ParameterConstant.SUPPLIER, mBorrowInfoResponse.getDataUserName());
                intent.putExtra(ParameterConstant.BORROW_ID, mBorrowInfoResponse.getBorrowID());
                startActivityForResult(intent, REFRESH);
                break;
            case R.id.handle_log:
                Intent handleIntent = new Intent(ActivityBorrowDetail.this, HandleLogListActivity.class);
                handleIntent.putExtra(ParameterConstant.HANDLE_LOGS, (Serializable) mBorrowInfoResponse.getHandleLog());
                startActivity(handleIntent);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REFRESH) {
            initData();
        }
    }
}
