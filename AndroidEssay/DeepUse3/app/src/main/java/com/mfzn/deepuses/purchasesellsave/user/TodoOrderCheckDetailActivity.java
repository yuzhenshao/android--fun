package com.mfzn.deepuses.purchasesellsave.user;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.user.WaitingCheckListResponse.WaitingCheckOrderPurchaseResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsCheckAdapter;
import com.mfzn.deepuses.utils.DateUtils;


import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class TodoOrderCheckDetailActivity extends BasicActivity {

    @BindView(R.id.user_name)
    TextView userNameView;
    @BindView(R.id.user_phone)
    TextView userPhoneView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.status_check)
    TextView statusCheckView;
    @BindView(R.id.status_check_finish)
    TextView statusCheckFinishView;
    @BindView(R.id.check_container)
    LinearLayout checkContainerView;

    @BindView(R.id.total_money)
    TextView totalMoneyView;
    @BindView(R.id.num)
    TextView numView;
    @BindView(R.id.date)
    TextView dateView;
    @BindView(R.id.out_num)
    TextView outNumView;
    @BindView(R.id.order_user_name)
    TextView orderUserNameView;

    private WaitingCheckOrderPurchaseResponse orderPurchaseResponse;

    private GoodsCheckAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_todo_check_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("审核单据");
        orderPurchaseResponse = (WaitingCheckOrderPurchaseResponse) getIntent().getSerializableExtra(ParameterConstant.ORDER_PURCHASE_RESPONSE);

        if (orderPurchaseResponse.getCheckStatus() != 0) {
            statusCheckView.setTextColor(Color.parseColor("#ff909399"));
            statusCheckFinishView.setTextColor(Color.parseColor("#3D7EFF"));
            statusCheckFinishView.setText("已完成");
            checkContainerView.setVisibility(View.GONE);
        }
        userNameView.setText(orderPurchaseResponse.getSupplierName());
        userPhoneView.setText("无");
        numView.setText(orderPurchaseResponse.getOrderNum());
        dateView.setText(DateUtils.longToString(orderPurchaseResponse.getOrderTime()));
        outNumView.setText("无");
        orderUserNameView.setText(orderPurchaseResponse.getOrderMakerUserName());
        totalMoneyView.setText(orderPurchaseResponse.getRealMoney());
        adapter = new GoodsCheckAdapter(this, orderPurchaseResponse.getGoodsInfo());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @OnClick({R.id.btn_pass, R.id.btn_unpass})
    public void clickView(View view) {
        int checkStatus = 0;
        switch (view.getId()) {
            case R.id.btn_pass:
                checkStatus = 1;
                break;
            case R.id.btn_unpass:
                checkStatus = 2;
                break;
        }
        if (checkStatus != 0) {
            ApiServiceManager.doOrderCheck(orderPurchaseResponse.getApplyID(), checkStatus)
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
                            showToast("操作成功");
                            Intent intent = new Intent();
                            setResult(Activity.RESULT_OK, intent);
                            finish();
                        }
                    });
        }
    }
}
