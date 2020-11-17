package com.mfzn.deepuses.purchasesellsave.store.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.store.OrderAllotInfoResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsAddedAdapter;
import com.mfzn.deepuses.utils.DateUtils;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class OrderAllotDetailActivity extends BasicActivity {
    private TextView mStatus;
    private TextView mCheck;
    private TextView mOutStoreName;
    private TextView mInStoreName;
    private RecyclerView mRecyclerView;
    private TextView mOrderTotalPrice;
    private TextView mNum;
    private TextView mDate;
    private TextView mOutNum;
    private TextView mOrderUserName;
    private Button mBtnDelete;

    private String orderId;
    private OrderAllotInfoResponse mOrderAllotResponse;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_allot_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderId = getIntent().getStringExtra(ParameterConstant.ORDER_ID);
        mTitleBar.updateTitleBar("调拨单详情");
        initView();
        initData();
    }

    private void initView() {
        mStatus = (TextView) findViewById(R.id.status);
        mCheck = (TextView) findViewById(R.id.check);
        mOutStoreName = (TextView) findViewById(R.id.out_store_name);
        mInStoreName = (TextView) findViewById(R.id.in_store_name);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mOrderTotalPrice = (TextView) findViewById(R.id.order_total_price);
        mNum = (TextView) findViewById(R.id.num);
        mDate = (TextView) findViewById(R.id.date);
        mOutNum = (TextView) findViewById(R.id.out_num);
        mOrderUserName = (TextView) findViewById(R.id.order_user_name);
        mBtnDelete = (Button) findViewById(R.id.btn_delete);

        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteOrder();
            }
        });
    }

    private void initData() {
        ApiServiceManager.orderAllotInfo(orderId)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<OrderAllotInfoResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showToast(error.getMessage());
                        finish();
                    }

                    @Override
                    public void onNext(HttpResult<OrderAllotInfoResponse> reuslt) {
                        mOrderAllotResponse = reuslt.getRes();
                        if (mOrderAllotResponse == null) {
                            showToast("暂无数据");
                        } else {
                            initDetailInfo();
                        }
                    }
                });
    }

    private void initDetailInfo() {

        mStatus.setText(mOrderAllotResponse.getIsCheck() == 0 ? "待审核" : (mOrderAllotResponse.getIsCheck() == 1 ? "审核通过" : "审核不通过"));
        mCheck.setText(mOrderAllotResponse.getStatus() == 0 ? "未完成" : (mOrderAllotResponse.getStatus() == 1 ? "进行中" : "已完成"));
        mCheck.setTextColor(Color.parseColor(mOrderAllotResponse.getStatus() == 0 ? "#ff909399" : "#3D7EFF"));

        mOutStoreName.setText(mOrderAllotResponse.getFromStoreName());
        mInStoreName.setText(mOrderAllotResponse.getToStoreName());

        mOrderTotalPrice.setText("buzhidao");

        mNum.setText(mOrderAllotResponse.getOrderNum());
        mOutNum.setText(mOrderAllotResponse.getOutNum());
        mDate.setText(DateUtils.longToString(mOrderAllotResponse.getOrderTime()));
        mOrderUserName.setText(mOrderAllotResponse.getOrderMakerUserName());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        GoodsAddedAdapter adapter = new GoodsAddedAdapter(this, mOrderAllotResponse.getGoodsInfo());
        mRecyclerView.setAdapter(adapter);

    }

    public int getStatusResId(int status) {//0.待审核1通过2拒绝
        switch (status) {
            case 0:
                return R.mipmap.order_examine_pending;
            case 1:
                return R.mipmap.order_examine_pass;
            case 2:
                return R.mipmap.order_examine_unpass;
        }
        return R.mipmap.order_examine_pending;
    }

    private void deleteOrder() {
        ApiServiceManager.orderAllotDelBatch(orderId)
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
                        setResult(Activity.RESULT_OK);
                        finish();
                    }
                });
    }
}
