package com.mfzn.deepuses.purchasesellsave.store.activity;

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
import com.mfzn.deepuses.bean.response.store.OtherWaitingInOutDetailResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsCheckAdapter;
import com.mfzn.deepuses.utils.DateUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class OtherWaitingInOutDetailActivity extends BasicActivity {

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
    @BindView(R.id.discount_amount)
    TextView discountAmountView;

    @BindView(R.id.store_name)
    TextView storeNameView;

    @BindView(R.id.store_address_container)
    LinearLayout storeAddressContainer;
    @BindView(R.id.address)
    TextView addressView;

    @BindView(R.id.num)
    TextView numView;
    @BindView(R.id.date)
    TextView dateView;
    @BindView(R.id.out_num)
    TextView outNumView;
    @BindView(R.id.order_user_name)
    TextView orderUserNameView;

    private OtherWaitingInOutDetailResponse waitingInOutDetailResponse;

    private GoodsCheckAdapter adapter;
    private String orderId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_other_in_out_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("其他出入库单");
        orderId = getIntent().getStringExtra(ParameterConstant.ORDER_ID);
        loadOtherWaitingInOutDetail();
    }

    private void loadOtherWaitingInOutDetail() {
        ApiServiceManager.orderOtherInOutInfo(orderId)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<OtherWaitingInOutDetailResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showToast(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<OtherWaitingInOutDetailResponse> reuslt) {
                        waitingInOutDetailResponse = reuslt.getRes();
                        if (waitingInOutDetailResponse != null) {
                            initData();
                        }
                    }
                });
    }


    private void initData() {
        if (waitingInOutDetailResponse.getIsCheck() != 0) {//0待审核 1通过 2拒绝
            statusCheckView.setTextColor(Color.parseColor("#ff909399"));
            statusCheckFinishView.setTextColor(Color.parseColor("#3D7EFF"));
            statusCheckFinishView.setText(waitingInOutDetailResponse.getIsCheck() == 1 ? "审核通过" : "审核拒绝");
        }
        userNameView.setText(waitingInOutDetailResponse.getOrderMakerUserName());
        userPhoneView.setText("无");
        numView.setText(waitingInOutDetailResponse.getOrderNum());
        dateView.setText(DateUtils.longToString(waitingInOutDetailResponse.getOrderTime()));
        outNumView.setText("无");
        orderUserNameView.setText(waitingInOutDetailResponse.getOrderMakerUserName());
        totalMoneyView.setText(waitingInOutDetailResponse.getTotalMoney());
        discountAmountView.setText("-" + waitingInOutDetailResponse.getDiscountAmount());
        adapter = new GoodsCheckAdapter(this, waitingInOutDetailResponse.getGoodsInfo());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


    @OnClick({R.id.btn_pass})
    public void orderOtherOutDoOutAction() {
        ApiServiceManager.orderOtherOutDoOutAction(orderId)
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
