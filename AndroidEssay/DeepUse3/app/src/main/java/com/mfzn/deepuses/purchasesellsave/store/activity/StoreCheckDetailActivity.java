package com.mfzn.deepuses.purchasesellsave.store.activity;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.store.OrderStockCheckInfoResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.store.adapter.StoreGoodsCheckAdapter;
import com.mfzn.deepuses.utils.DateUtils;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class StoreCheckDetailActivity extends BasicActivity implements View.OnClickListener {
    private TextView mCheck;
    private TextView mStoreName;
    private RecyclerView mRecyclerView;
    private TextView mNum;
    private TextView mDate;
    private TextView mOutNum;
    private TextView mOrderUserName;
    private Button mBtnHandle;
    private Button mBtnDelete;

    private String orderId;
    private OrderStockCheckInfoResponse mCheckInfoResponse;

    @Override
    public int getLayoutId() {
        return R.layout.activity_store_check_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("盘点详情");
        orderId=getIntent().getStringExtra(ParameterConstant.ORDER_ID);
        initView();
        initData();
    }

    private void initView() {
        mCheck = (TextView) findViewById(R.id.check);
        mStoreName = (TextView) findViewById(R.id.store_name);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mNum = (TextView) findViewById(R.id.num);
        mDate = (TextView) findViewById(R.id.date);
        mOutNum = (TextView) findViewById(R.id.out_num);
        mOrderUserName = (TextView) findViewById(R.id.order_user_name);
        mBtnHandle = (Button) findViewById(R.id.btn_handle);
        mBtnDelete = (Button) findViewById(R.id.btn_delete);

        mBtnHandle.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
    }

    private void initData() {
        showDialog();
        ApiServiceManager.orderStockCheckInfo(orderId)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<OrderStockCheckInfoResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        hideDialog();
                        showToast(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<OrderStockCheckInfoResponse> reuslt) {
                        hideDialog();
                        if (reuslt != null && reuslt.getRes()!=null) {
                            mCheckInfoResponse=reuslt.getRes();
                            initStoreCheck();
                        }
                    }
                });
    }

    private void initStoreCheck() {
        mCheck.setText(mCheckInfoResponse.getIsCheck()==0?"待审核":(mCheckInfoResponse.getIsCheck()==1?"审核通过":"审核不通过"));//审核状态：0待审核 1通过 2拒绝
        mCheck.setTextColor(Color.parseColor(mCheckInfoResponse.getIsCheck() == 0 ? "#909399" : "#3D7EFF"));


        mStoreName.setText(mCheckInfoResponse.getStoreName());
        if (ListUtil.isEmpty(mCheckInfoResponse.getGoodsInfo())) {
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            StoreGoodsCheckAdapter adapter = new StoreGoodsCheckAdapter(this,mCheckInfoResponse.getGoodsInfo());
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(adapter);
        }

        mNum.setText(mCheckInfoResponse.getOrderNum());
        mDate.setText(DateUtils.longToString(mCheckInfoResponse.getOrderTime()));
        mOutNum.setText(mCheckInfoResponse.getOutNum());
        mOrderUserName.setText( mCheckInfoResponse.getOrderMakerUserName());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_handle:
                onHandleAction();
                break;
            case R.id.btn_delete:
                onDeleteAction();
                break;
        }
    }

    private void onHandleAction(){
        ApiServiceManager.orderStockCheckHandle(orderId)
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
                    }
                });
    }

    private void onDeleteAction(){
        ApiServiceManager.delOrderStockCheck(orderId)
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
