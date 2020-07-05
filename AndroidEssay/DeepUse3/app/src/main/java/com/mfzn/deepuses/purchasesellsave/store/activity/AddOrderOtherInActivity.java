package com.mfzn.deepuses.purchasesellsave.store.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.khgl.MyCustomerActivity;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.request.sale.OrderOtherInRequest;
import com.mfzn.deepuses.bean.request.sale.OrderSalesRequest;
import com.mfzn.deepuses.bean.request.store.OrderAllotAddRequest;
import com.mfzn.deepuses.bean.response.settings.GoodsInfoResponse;
import com.mfzn.deepuses.bean.response.settings.StoreResponse;
import com.mfzn.deepuses.common.PickerDialogView;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.sale.activity.BaseAddCustomerAndGoodsActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.CommodityCreateActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.GoodsSelectListActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.SetCostActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.StoreListActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.SupplierListActivity;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsAdapter;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class AddOrderOtherInActivity extends BaseAddCustomerAndGoodsActivity {

    @BindView(R.id.supplier)
    EditText supplierEdit;
    @BindView(R.id.store_in)
    EditText storeInEdit;

    @BindView(R.id.discount_price)
    EditText discountPrice;
    @BindView(R.id.total_price)
    EditText totalPrice;


    private static int SUPPLIER_CODE = 101;
    private static int STORE_IN = 102;

    private OrderOtherInRequest orderOtherInRequest = new OrderOtherInRequest();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("新建其他入库单");
    }

    @OnClick({R.id.supplier_select, R.id.store_in_select, R.id.order_time_select, R.id.btn_commit})
    public void viewClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.supplier_select:
                intent.setClass(this, SupplierListActivity.class);
                startActivityForResult(intent, SUPPLIER_CODE);
                break;
            case R.id.store_in_select:
                intent.setClass(this, StoreListActivity.class);
                intent.putExtra(ParameterConstant.IS_SELECTED, true);
                startActivityForResult(intent, STORE_IN);
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
        orderOtherInRequest.setOrderTime(orderTime);
        orderOtherInRequest.setOrderMakerUserID(UserHelper.getUserId());
        orderOtherInRequest.setOrderGoodsStr(getOrderGoodsStr6());
        orderOtherInRequest.setDiscountAmount(mdiscountPrice);
        orderOtherInRequest.setTotalMoney(mTotalPrice);
        orderOtherInRequest.setRealMoney(Integer.parseInt(mTotalPrice) - Integer.parseInt(mdiscountPrice) + "");

        if (TextUtils.isEmpty(orderOtherInRequest.getOrderGoodsStr())) {
            showToast("请输入商品信息");
            return;
        }

        if (TextUtils.isEmpty(orderOtherInRequest.getStoreID())) {
            showToast("请输入入货仓库");
            return;
        }

        if (orderOtherInRequest.getOrderTime() == 0) {
            showToast("请输入单据日期");
            return;
        }

        ApiServiceManager.addOrderOtherIn(orderOtherInRequest)
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
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SUPPLIER_CODE) {
                orderOtherInRequest.setSupplierID(data.getStringExtra("Id"));
                supplierEdit.setText(data.getStringExtra("Name"));
            } else if (requestCode == STORE_IN) {
                StoreResponse inStore = (StoreResponse) data.getSerializableExtra(ParameterConstant.STORE);
                orderOtherInRequest.setStoreID(inStore.getStoreID());
                storeInEdit.setText(inStore.getStoreName());
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_other_in_create;
    }

}