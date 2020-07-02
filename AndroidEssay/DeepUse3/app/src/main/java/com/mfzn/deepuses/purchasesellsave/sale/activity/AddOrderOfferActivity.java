package com.mfzn.deepuses.purchasesellsave.sale.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.request.sale.OrderOfferRequest;
import com.mfzn.deepuses.bean.response.settings.GoodsInfoResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class AddOrderOfferActivity extends BaseAddCustomerAndGoodsActivity {

    @BindView(R.id.customer)
    EditText customerEdit;
    @BindView(R.id.other_cost)
    EditText otherCostEdit;
    @BindView(R.id.discount_price)
    EditText discountPrice;
    @BindView(R.id.total_price)
    EditText totalPrice;

    @BindView(R.id.out_num)
    EditText outNum;
    @BindView(R.id.remark)
    EditText remark;


    private OrderOfferRequest orderOfferRequest = new OrderOfferRequest();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("新建报价单");
    }

    @OnClick({R.id.customer_select, R.id.other_cost_select})
    public void viewClick(View v) {
        switch (v.getId()) {
            case R.id.customer_select:
                turnToCustomer();
                break;
            case R.id.other_cost_select:
                turnToCostSelect();
                break;
        }
    }

    @Override
    protected void commitAction() {
        String mTotalPrice = totalPrice.getText().toString();
        String mdiscountPrice = discountPrice.getText().toString();
        if (TextUtils.isEmpty(mTotalPrice)) {
            showToast("请输入单据总价格");
            return;
        }
        if (TextUtils.isEmpty(mdiscountPrice)) {
            showToast("请输入优惠金额");
            return;
        }
        orderOfferRequest.setOrderGoodsStr(getOrderGoodsStr());
        orderOfferRequest.setDiscountAmount(mdiscountPrice);
        orderOfferRequest.setTotalMoney(mTotalPrice);
        orderOfferRequest.setRealMoney(Integer.parseInt(mTotalPrice) - Integer.parseInt(mdiscountPrice) + "");
        orderOfferRequest.setOrderTime(orderTime);
        orderOfferRequest.setOutNum(outNum.getText().toString());
        orderOfferRequest.setOrderMakerUserID(UserHelper.getUserId());
        orderOfferRequest.setRemark(remark.getText().toString());
        if (TextUtils.isEmpty(outNum.getText().toString())) {
            showToast("请输入外部报价单号");
            return;
        }

        if (TextUtils.isEmpty(orderOfferRequest.getOrderMakerUserID())) {
            showToast("请输入公司客户");
            return;
        }
        if (TextUtils.isEmpty(orderOfferRequest.getOtherCostStr())) {
            showToast("请输入其他费用信息");
            return;
        }
        if (TextUtils.isEmpty(orderOfferRequest.getOrderGoodsStr())) {
            showToast("请输入商品信息");
            return;
        }
        ApiServiceManager.addOrderOffer(orderOfferRequest)
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
                        showToast("新建成功");
                        finish();
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            if (requestCode == USER) {
                orderOfferRequest.setCompanyCustomerID(data.getStringExtra("Id"));
                customerEdit.setText(data.getStringExtra("Name"));
            } else if (requestCode == COST) {
                String otherCostStr = data.getStringExtra("data");
                orderOfferRequest.setOtherCostStr(otherCostStr);
                otherCostEdit.setText(TextUtils.isEmpty(otherCostStr) ? "" : "已填写");
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_offer_create;
    }
}
