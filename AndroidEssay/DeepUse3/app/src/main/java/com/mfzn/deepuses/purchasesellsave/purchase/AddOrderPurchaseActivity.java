package com.mfzn.deepuses.purchasesellsave.purchase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.request.purchase.OrderPurchaseAddRequest;
import com.mfzn.deepuses.bean.response.purchase.OrderPurchaseListResponse;
import com.mfzn.deepuses.bean.response.sale.OrderSalesListResponse;
import com.mfzn.deepuses.bean.response.settings.GoodsInfoResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.sale.activity.AddOrderSalesActivity;
import com.mfzn.deepuses.purchasesellsave.sale.activity.BaseAddCustomerAndGoodsActivity;
import com.mfzn.deepuses.purchasesellsave.sale.activity.OrderInputListActivity;
import com.mfzn.deepuses.purchasesellsave.sale.activity.OrderOfferListActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.StoreListActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.SupplierListActivity;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.utils.OnInputChangeListener;
import com.mfzn.deepuses.utils.UserHelper;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class AddOrderPurchaseActivity extends BaseAddCustomerAndGoodsActivity {

    private final static int STORE = 4;
    private final static int SALES_INPUT = 100;
    private final static int PURCHASE_INPUT = 101;
    private static int SUPPLIER_CODE = 102;
    @BindView(R.id.customer)
    EditText customerEdit;
    @BindView(R.id.other_cost)
    EditText otherCostEdit;
    @BindView(R.id.discount_price)
    EditText discountPrice;
    @BindView(R.id.total_price)
    EditText totalPrice;

    @BindView(R.id.store)
    EditText storeEdit;
    @BindView(R.id.out_num)
    EditText outNumEdit;
    @BindView(R.id.remark)
    EditText remarkEdit;

    @BindView(R.id.store_name)
    TextView storeNameView;

    private OrderPurchaseAddRequest request = new OrderPurchaseAddRequest();
    private boolean isPurchaseAdd = true;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isPurchaseAdd = getIntent().getBooleanExtra(ParameterConstant.IS_PURCHASE_CREATE, true);
        mTitleBar.updateTitleBar(isPurchaseAdd ? "新建采购单" : "新建采购退货单","导入");
        storeNameView.setText(isPurchaseAdd ? "入库仓库" : "出库仓库");
        discountPrice.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                setTotalPriceView();
            }
        });
    }

    @OnClick({R.id.customer_select, R.id.other_cost_select, R.id.store_select})
    public void viewClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.customer_select:
                intent.setClass(this, SupplierListActivity.class);
                startActivityForResult(intent, SUPPLIER_CODE);
                break;
            case R.id.other_cost_select:
                turnToCostSelect();
                break;
            case R.id.store_select:
                intent.setClass(this, StoreListActivity.class);
                intent.putExtra(ParameterConstant.IS_SELECTED, true);
                startActivityForResult(intent, STORE);
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
        request.setOrderGoodsStr(getOrderGoodsStr6());
        request.setDiscountAmount(mdiscountPrice);
        request.setTotalMoney(mTotalPrice);
        request.setRealMoney(Double.parseDouble(mTotalPrice) - (TextUtils.isEmpty(mdiscountPrice) ? 0 : Double.parseDouble(mdiscountPrice)) + "");
        request.setOrderTime(orderTime);
        request.setOutNum(outNumEdit.getText().toString());
        request.setOrderMakerUserID(UserHelper.getUserId());
        request.setRemark(remarkEdit.getText().toString());

        if (TextUtils.isEmpty(request.getSupplierID())) {
            showToast("请输入供应商");
            return;
        }
        if (TextUtils.isEmpty(request.getOrderGoodsStr())) {
            showToast("请输入商品信息");
            return;
        }
        if (isPurchaseAdd) {
            addOrderPurchase();
        } else {
            addOrderPurchaseBack();
        }
    }

    private void addOrderPurchase() {
        ApiServiceManager.orderPurchaseAdd(request)
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
                        showToast("创建成功");
                        finish();
                    }
                });
    }

    private void addOrderPurchaseBack() {
        ApiServiceManager.orderPurchaseBackAdd(request)
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
                        showToast("创建成功");
                        finish();
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            if (requestCode == STORE) {
                request.setStoreID(data.getStringExtra("Id"));
                storeEdit.setText(data.getStringExtra("Name"));
            } else if (requestCode == SUPPLIER_CODE) {
                request.setSupplierID(data.getStringExtra("Id"));
                customerEdit.setText(data.getStringExtra("Name"));
            } else if (requestCode == COST) {
                String otherCostStr = data.getStringExtra("data");
                request.setOtherCostStr(otherCostStr);
                otherCostEdit.setText(TextUtils.isEmpty(otherCostStr) ? "" : "已填写");
                setTotalPriceView();
            } else if (requestCode == GOODS) {
                setTotalPriceView();
            } else if (requestCode == PURCHASE_INPUT) {
                OrderPurchaseListResponse.OrderPurchaseResponse orderResponse =
                        (OrderPurchaseListResponse.OrderPurchaseResponse) data.getSerializableExtra(ParameterConstant.INPUT_DATA);
                if (orderResponse != null) {
                    request.setSupplierID(orderResponse.getSupplierID());
                    customerEdit.setText(orderResponse.getSupplierName());
                    setGoodsContainer(orderResponse);
                    orderTime = (int) orderResponse.getOrderTime();
                    orderTimeEdit.setText(DateUtils.longToString("yyyy/MM/dd", orderResponse.getOrderTime()));

                    userNameView.setText(orderResponse.getOrderMakerUserName());
                    remarkEdit.setText(orderResponse.getRemark());
                    setTotalPriceView();
                }
            } else if (requestCode == SALES_INPUT) {
                OrderSalesListResponse.OrderSalesResponse orderSalesResponse =
                        (OrderSalesListResponse.OrderSalesResponse) data.getSerializableExtra(ParameterConstant.INPUT_DATA);
                if (orderSalesResponse != null) {
                    customerEdit.setText(orderSalesResponse.getCustomerName());
                    setGoodsPriceContainer(orderSalesResponse.getGoodsInfo());
                    discountPrice.setText(orderSalesResponse.getOrderMakerDiscount());

                    request.setStoreID(orderSalesResponse.getStoreID());
                    storeEdit.setText(orderSalesResponse.getStoreName());

                    orderTime = (int) orderSalesResponse.getOrderTime();
                    orderTimeEdit.setText(DateUtils.longToString("yyyy/MM/dd", orderSalesResponse.getOrderTime()));

                    outNumEdit.setText(orderSalesResponse.getOutNum());
                    userNameView.setText(orderSalesResponse.getOrderMakerUserName());
                    remarkEdit.setText(orderSalesResponse.getRemark());
                    setTotalPriceView();
                }
            }
        }
    }

    protected void setGoodsContainer(OrderPurchaseListResponse.OrderPurchaseResponse orderPurchaseResponse) {
        number.setText("数量：" + orderPurchaseResponse.getGoodsAllCount());
        if (!ListUtil.isEmpty(orderPurchaseResponse.getGoodsInfo())) {
            totalMoney = 0;
            goodsPriceContainer.setVisibility(View.VISIBLE);
            goodsSelectedList.clear();
            goodsSelectedList.addAll(orderPurchaseResponse.getGoodsInfo());
            adapter.notifyDataSetChanged();
            for (GoodsInfoResponse goodsInfoResponse : orderPurchaseResponse.getGoodsInfo()) {
                totalMoney += getPrice(goodsInfoResponse.getMoney());
            }
            price.setText("总价：" + totalMoney);
        }
    }

    private void setTotalPriceView() {
        String disconunt = discountPrice.getText().toString();
        double disPtice = 0;
        if (!TextUtils.isEmpty(disconunt)) {
            disPtice = Double.parseDouble(disconunt);
        }
        totalPrice.setText((totalMoney - disPtice + getOtherCost()) + "");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_purchase_add_create;
    }


    @Override
    protected void rightPressedAction() {
        if (isPurchaseAdd) {//采购单导入是销售单、零售单
            View contentView = LayoutInflater.from(this).inflate(R.layout.order_popupwindow, null, false);
            TextView orderOffer = contentView.findViewById(R.id.order_offer);
            orderOffer.setText("零售单");
            orderOffer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tureToInputActivity(1);
                    popupWindow.dismiss();
                }
            });
            TextView orderSales = contentView.findViewById(R.id.order_sales);
            orderSales.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tureToInputActivity(2);
                    popupWindow.dismiss();
                }
            });

            popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, true);
            Display display = getWindowManager().getDefaultDisplay();
            popupWindow.setOutsideTouchable(true);
            popupWindow.setAnimationStyle(R.style.popup_window_anim_style);
            popupWindow.showAtLocation(mTitleBar, Gravity.TOP, display.getWidth() - 140,
                    mTitleBar.getHeight());

        } else {
            Intent intent = new Intent(this, OrderPurchaseListActivity.class);
            intent.putExtra(ParameterConstant.IS_SELECTED, true);
            startActivityForResult(intent, PURCHASE_INPUT);
        }
    }

    private void tureToInputActivity(int inputType){
        Intent intent = new Intent(this, OrderInputListActivity.class);
        intent.putExtra(ParameterConstant.INPUT_TYPE, inputType);//1 零售 2销售
        startActivityForResult(intent, SALES_INPUT);
    }

}
