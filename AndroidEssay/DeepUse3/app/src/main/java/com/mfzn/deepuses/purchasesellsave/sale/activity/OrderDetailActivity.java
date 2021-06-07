package com.mfzn.deepuses.purchasesellsave.sale.activity;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.libcommon.dialog.DialogUtils;
import com.libcommon.dialog.fragment.BaseDialogFragment;
import com.libcommon.dialog.fragment.CustomDialog;
import com.libcommon.dialog.listener.OnBindViewListener;
import com.libcommon.dialog.listener.OnViewClickListener;
import com.libcommon.dialog.view.BindViewHolder;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.sale.OrderOfferListResponse;
import com.mfzn.deepuses.bean.response.settings.GoodsDetailResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsCostAdapter;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsOrderAdapter;
import com.mfzn.deepuses.utils.DateUtils;


import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class OrderDetailActivity extends BasicActivity {
    private ImageView mCheckStatus;
    private EditText mCustomerName;
    private EditText mContactPhone;
    private RecyclerView mGoodsRecyleview;
    private RecyclerView mGoodsCostRecyleview;
    private TextView mDiscountPriceView;
    private TextView mOrderTotalPrice;
    private TextView mOrderNum;
    private TextView mOrderTime;
    private TextView mOutNum;
    private TextView mUserName;
    private TextView mBeiZhuView;
    private Button deleteOrder;
    private Button cancelOrder;

    private int orderType;
    private String orderId;
    int cancelType=0;
    private OrderOfferListResponse.OrderOfferResponse mOrderOfferResponse;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_offer_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderId = getIntent().getStringExtra(ParameterConstant.ORDER_ID);
        orderType = getIntent().getIntExtra(ParameterConstant.ORDER_TYPE, 0);
        mTitleBar.updateTitleBar("报价单详情");
        initView();
        initData();
    }

    private void initView() {
        mCheckStatus = (ImageView) findViewById(R.id.check_status);
        mCustomerName = (EditText) findViewById(R.id.customer_name);
        mContactPhone = (EditText) findViewById(R.id.contact_phone);
        mGoodsRecyleview = (RecyclerView) findViewById(R.id.goods_recyleview);
        mGoodsCostRecyleview = (RecyclerView) findViewById(R.id.goods_cost_recyleview);
        mDiscountPriceView = (TextView) findViewById(R.id.discount_price);
        mOrderTotalPrice = (TextView) findViewById(R.id.order_total_price);
        mOrderNum = (TextView) findViewById(R.id.order_num);
        mOrderTime = (TextView) findViewById(R.id.order_time);
        mOutNum = (TextView) findViewById(R.id.out_num);
        mUserName = (TextView) findViewById(R.id.user_name);
        mBeiZhuView = (TextView) findViewById(R.id.bei_zhu);
        deleteOrder = findViewById(R.id.btn_commit);
        deleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteOrder();
            }
        });

        cancelOrder = findViewById(R.id.btn_cancel);
        cancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCancelDialog();
            }
        });

        mCustomerName.setText(getIntent().getStringExtra(ParameterConstant.NAME));
        mContactPhone.setText(getIntent().getStringExtra(ParameterConstant.PHONE));
    }

    private void showCancelDialog() {

        new CustomDialog.Builder().setLayoutRes(R.layout.dialog_goods_cancel)
                .setWidth(WindowManager.LayoutParams.MATCH_PARENT)
                .setGravity(Gravity.BOTTOM)
                .addOnClickListener(R.id.confirm_btn, R.id.cancel_money, R.id.cancel_goods, R.id.cancel_money_goods, R.id.no_cancel)
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BaseDialogFragment customDialog, BindViewHolder bindViewHolder, View view) {
                        if (view.getId() == R.id.confirm_btn) {
                            if (customDialog != null) {
                                customDialog.dismiss();
                            }
                            cancelType=0;
                            doCancelAction(cancelType);
                            return;
                        }
                        ImageView cancelMoney = bindViewHolder.getView(R.id.cancel_money);
                        ImageView cancelGoods = bindViewHolder.getView(R.id.cancel_goods);
                        ImageView cancelMoneyGoods = bindViewHolder.getView(R.id.cancel_money_goods);
                        ImageView noCancel = bindViewHolder.getView(R.id.no_cancel);
                        cancelMoney.setImageResource(R.mipmap.icon_unselected);
                        cancelGoods.setImageResource(R.mipmap.icon_unselected);
                        cancelMoneyGoods.setImageResource(R.mipmap.icon_unselected);
                        noCancel.setImageResource(R.mipmap.icon_unselected);


                        if (view.getId() == R.id.cancel_money) {
                            cancelType=1;
                            cancelMoney.setImageResource(R.mipmap.icon_selected);
                        } else if (view.getId() == R.id.cancel_goods) {
                            cancelType=2;
                            cancelGoods.setImageResource(R.mipmap.icon_selected);
                        } else if (view.getId() == R.id.cancel_money_goods) {
                            cancelType=3;
                            cancelMoneyGoods.setImageResource(R.mipmap.icon_selected);
                        } else if (view.getId() == R.id.no_cancel) {
                            cancelType=4;
                            noCancel.setImageResource(R.mipmap.icon_selected);
                        }
                    }
                }).create().show(getSupportFragmentManager(), getClass().getName());

    }

    private void doCancelAction(int type) {
        switch (orderType){
            //d调用不同的接口，进行取消
        }
    }

    private void initData() {
        ApiServiceManager.getOrderInfo(orderType + "", orderId)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<OrderOfferListResponse.OrderOfferResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showToast(error.getMessage());
                        finish();
                    }

                    @Override
                    public void onNext(HttpResult<OrderOfferListResponse.OrderOfferResponse> reuslt) {
                        mOrderOfferResponse = reuslt.getRes();
                        if (mOrderOfferResponse == null) {
                            showToast("没有数据");
                            finish();
                        } else {
                            initDetailInfo();
                        }
                    }
                });
    }

    private void initDetailInfo() {
        mCheckStatus.setImageResource(getStatusResId(mOrderOfferResponse.getIsCheck()));
        mOrderTotalPrice.setText(mOrderOfferResponse.getTotalMoney());
        mOrderNum.setText(mOrderOfferResponse.getOrderNum());
        mOrderTime.setText(DateUtils.longToString(mOrderOfferResponse.getOrderTime()));
        mOutNum.setText(mOrderOfferResponse.getOutNum());
        mUserName.setText(mOrderOfferResponse.getOrderMakerUserName());
        mDiscountPriceView.setText("-" + mOrderOfferResponse.getDiscountAmount());
        mBeiZhuView.setText(mOrderOfferResponse.getRemark());
        mGoodsRecyleview.setLayoutManager(new LinearLayoutManager(this));
        mGoodsRecyleview.setAdapter(new GoodsOrderAdapter(this, mOrderOfferResponse.getGoodsInfo()));

        if (ListUtil.isEmpty(mOrderOfferResponse.getOtherCost())) {
            mGoodsCostRecyleview.setVisibility(View.GONE);
        } else {
            mGoodsCostRecyleview.setVisibility(View.VISIBLE);
            GoodsCostAdapter costAdapter = new GoodsCostAdapter(mOrderOfferResponse.getOtherCost());
            mGoodsCostRecyleview.setLayoutManager(new LinearLayoutManager(this));
            mGoodsCostRecyleview.setAdapter(costAdapter);
        }
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
        ApiServiceManager.orderOfferDel(orderId)
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
