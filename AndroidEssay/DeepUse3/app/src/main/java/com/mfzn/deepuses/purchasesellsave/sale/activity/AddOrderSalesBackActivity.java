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
import com.mfzn.deepuses.activity.project.ProjectManageActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.request.sale.OrderSalesBackRequest;
import com.mfzn.deepuses.bean.response.settings.StoreResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.activity.StoreListActivity;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.utils.UserHelper;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class AddOrderSalesBackActivity extends BaseAddCustomerAndGoodsActivity {

    private final static int STORE = 4;
    private final static int PROJECT = 5;//非必填
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
    @BindView(R.id.project)
    EditText projectEdit;
    @BindView(R.id.out_num)
    EditText outNumEdit;
    @BindView(R.id.remark)
    EditText remarkEdit;

    private boolean isRetail;
    private OrderSalesBackRequest request = new OrderSalesBackRequest();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isRetail = getIntent().getBooleanExtra(ParameterConstant.IS_RETAIL_CREATE, false);
        mTitleBar.updateTitleBar(isRetail ? "新建零售退货单" : "新建销售退货单");
    }

    @OnClick({R.id.customer_select, R.id.other_cost_select,R.id.store_select, R.id.project_select})
    public void viewClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.customer_select:
                turnToCustomer();
                break;
            case R.id.other_cost_select:
                turnToCostSelect();
                break;
            case R.id.store_select:
                intent.setClass(this, StoreListActivity.class);
                intent.putExtra(ParameterConstant.IS_SELECTED, true);
                startActivityForResult(intent, STORE);
                break;
            case R.id.project_select:
                intent.setClass(this, ProjectManageActivity.class);
                intent.putExtra(ParameterConstant.IS_SELECTED, true);
                startActivityForResult(intent, PROJECT);
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
        request.setCompanyCustomerID(companyCustomerID);
        request.setOrderGoodsStr(getOrderGoodsStr());
        request.setOtherCostStr(otherCostStr);
        request.setDiscountAmount(mdiscountPrice);
        request.setTotalMoney(mTotalPrice);
        request.setRealMoney(Integer.parseInt(mTotalPrice) - Integer.parseInt(mdiscountPrice) + "");
        request.setOrderTime(System.currentTimeMillis());
        request.setOutNum(outNumEdit.getText().toString());
        request.setOrderMakerUserID(UserHelper.getUid());
        request.setRemark(remarkEdit.getText().toString());

        if (TextUtils.isEmpty(outNumEdit.getText().toString())) {
            showToast("请输入外部报价单号");
            return;
        }

        if (TextUtils.isEmpty(request.getOrderMakerUserID())) {
            showToast("请输入公司客户");
            return;
        }
        if (TextUtils.isEmpty(request.getOtherCostStr())) {
            showToast("请输入其他费用信息");
            return;
        }
        if (TextUtils.isEmpty(request.getOrderGoodsStr())) {
            showToast("请输入商品信息");
            return;
        }
        if (isRetail) {
            addOrderRetailBack();
        } else {
            addOrderSalesBack();
        }
    }

    private void addOrderRetailBack() {
        ApiServiceManager.addOrderRetailBack(request)
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

    private void addOrderSalesBack() {
        ApiServiceManager.addOrderSalesBack(request)
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
                StoreResponse storeResponse = (StoreResponse) data.getSerializableExtra(ParameterConstant.STORE);
                request.setStoreID(storeResponse.getStoreID());
                storeEdit.setText(storeResponse.getStoreName());
            } else if (requestCode == PROJECT) {
                request.setProID(data.getStringExtra("Id"));
                projectEdit.setText(data.getStringExtra("Name"));
            } else if (requestCode == USER) {
                request.setCompanyCustomerID(data.getStringExtra("Id"));
                customerEdit.setText(data.getStringExtra("Name"));
            } else if (requestCode == COST) {
                String otherCostStr = data.getStringExtra("data");
                request.setOtherCostStr(otherCostStr);
                otherCostEdit.setText(TextUtils.isEmpty(otherCostStr) ? "" : "已填写");
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_sales_back_create;
    }

}
