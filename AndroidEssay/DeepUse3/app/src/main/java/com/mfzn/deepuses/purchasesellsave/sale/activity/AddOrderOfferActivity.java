package com.mfzn.deepuses.purchasesellsave.sale.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.request.sale.OrderOfferRequest;
import com.mfzn.deepuses.bean.response.sale.OrderOfferListResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.manager.JXCDataManager;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.utils.OnInputChangeListener;
import com.mfzn.deepuses.utils.UserHelper;

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
    EditText discountPriceEdit;
    @BindView(R.id.total_price)
    EditText totalPriceEdit;

    @BindView(R.id.out_num)
    EditText outNum;
    @BindView(R.id.remark)
    EditText remark;

    private OrderOfferRequest orderOfferRequest = new OrderOfferRequest();
    private final static int INPUT = 101;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("新建报价单", "导入");
        discountPriceEdit.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                setTotalPriceView();
            }
        });
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
        String mTotalPrice = totalPriceEdit.getText().toString();
        String mdiscountPrice = discountPriceEdit.getText().toString();
        if (TextUtils.isEmpty(mTotalPrice)) {
            showToast("请输入单据总价格");
            return;
        }
        orderOfferRequest.setOrderGoodsStr(getOrderGoodsStr7());
        orderOfferRequest.setDiscountAmount(mdiscountPrice);
        orderOfferRequest.setTotalMoney(mTotalPrice);
        orderOfferRequest.setRealMoney(Double.parseDouble(mTotalPrice) - (TextUtils.isEmpty(mdiscountPrice)?0:Double.parseDouble(mdiscountPrice)) + "");
        orderOfferRequest.setOrderTime(orderTime);
        orderOfferRequest.setOutNum(outNum.getText().toString());
        orderOfferRequest.setOrderMakerUserID(UserHelper.getUserId());
        orderOfferRequest.setRemark(remark.getText().toString());

        if (TextUtils.isEmpty(orderOfferRequest.getOrderMakerUserID())) {
            showToast("请输入公司客户");
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
                setTotalPriceView();
            } else if (requestCode == GOODS) {
                setTotalPriceView();
            } else if (requestCode == INPUT) {
                OrderOfferListResponse.OrderOfferResponse orderSalesResponse =
                        (OrderOfferListResponse.OrderOfferResponse) data.getSerializableExtra(ParameterConstant.INPUT_DATA);
                if (orderSalesResponse != null) {
                    JXCDataManager.getInstance().addOtherCostModule(orderSalesResponse.getOtherCost());
                    orderOfferRequest.setCompanyCustomerID(orderSalesResponse.getCustomerID());
                    customerEdit.setText(orderSalesResponse.getCustomerName());
                    setGoodsPriceContainer(orderSalesResponse.getGoodsInfo());
                    discountPriceEdit.setText(orderSalesResponse.getDiscountAmount());
                    orderTime = (int) orderSalesResponse.getOrderTime();
                    orderTimeEdit.setText(DateUtils.longToString("yyyy/MM/dd", orderSalesResponse.getOrderTime()));
                    outNum.setText(orderSalesResponse.getOutNum());
                    userNameView.setText(orderSalesResponse.getOrderMakerUserName());
                    remark.setText(orderSalesResponse.getRemark());
                    setTotalPriceView();
                }
            }
        }
    }

    private void setTotalPriceView() {
        String disconunt = discountPriceEdit.getText().toString();
        double disPtice = 0;
        if (!TextUtils.isEmpty(disconunt)) {
            disPtice = Double.parseDouble(disconunt);
        }
        totalPriceEdit.setText((totalMoney - disPtice + getOtherCost()) + "");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_offer_create;
    }

    @Override
    protected void rightPressedAction() {
        Intent intent = new Intent(this, OrderOfferListActivity.class);
        intent.putExtra(ParameterConstant.IS_SELECTED, true);
        startActivityForResult(intent, INPUT);
    }
}
