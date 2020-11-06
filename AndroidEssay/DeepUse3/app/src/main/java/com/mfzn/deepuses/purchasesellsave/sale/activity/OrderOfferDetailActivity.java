package com.mfzn.deepuses.purchasesellsave.sale.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.sale.OrderOfferListResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsAddedAdapter;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsCostAdapter;
import com.mfzn.deepuses.utils.DateUtils;

import javax.xml.transform.Result;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class OrderOfferDetailActivity extends BasicActivity {
    private ImageView mCheckStatus;
    private EditText mCustomerName;
    private EditText mContactName;
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

    private String orderId;
    private OrderOfferListResponse.OrderOfferResponse mOrderOfferResponse;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_offer_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderId = getIntent().getStringExtra(ParameterConstant.ORDER_ID);
        mTitleBar.updateTitleBar("报价单详情");
        initView();
        initData();
    }

    private void initView() {
        mCheckStatus = (ImageView) findViewById(R.id.check_status);
        mCustomerName = (EditText) findViewById(R.id.customer_name);
        mContactName = (EditText) findViewById(R.id.contact_name);
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
        deleteOrder=findViewById(R.id.btn_commit);
        deleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteOrder();
            }
        });
    }

    private void initData() {
        ApiServiceManager.orderOfferInfo(orderId)
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
                        initDetailInfo();
                    }
                });
    }

    private void initDetailInfo() {
        mCheckStatus.setImageResource(getStatusResId(mOrderOfferResponse.getIsCheck()));
        mCustomerName.setText(mOrderOfferResponse.getSalesPersonUserName());//这个是什么
        mContactName.setText(mOrderOfferResponse.getCustomerName());
        mContactPhone.setText(mOrderOfferResponse.getCustomerPhone());
        mOrderTotalPrice.setText(mOrderOfferResponse.getTotalMoney());
        mOrderNum.setText(mOrderOfferResponse.getOrderNum());
        mOrderTime.setText(DateUtils.longToString(mOrderOfferResponse.getOrderTime()));
        mOutNum.setText(mOrderOfferResponse.getOutNum());
        mUserName.setText(mOrderOfferResponse.getOrderMakerUserName());
        mDiscountPriceView.setText("-" + mOrderOfferResponse.getDiscountAmount());
        mBeiZhuView.setText(mOrderOfferResponse.getRemark());
        mGoodsRecyleview.setLayoutManager(new LinearLayoutManager(this));
        GoodsAddedAdapter adapter = new GoodsAddedAdapter(this, mOrderOfferResponse.getGoodsInfo());
        mGoodsRecyleview.setAdapter(adapter);

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

    private void deleteOrder(){
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
