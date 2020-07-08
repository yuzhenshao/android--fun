package com.mfzn.deepuses.purchasesellsave.sale.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.project.ProjectManageActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.request.sale.OrderSalesRequest;
import com.mfzn.deepuses.bean.response.settings.StoreResponse;
import com.mfzn.deepuses.common.PickerDialogView;
import com.mfzn.deepuses.model.company.CityModel;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.activity.MoneyAccountListActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.StoreListActivity;
import com.mfzn.deepuses.utils.OnInputChangeListener;
import com.mfzn.deepuses.utils.UserHelper;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class AddOrderSalesActivity extends BaseAddCustomerAndGoodsActivity {

    private final static int STORE = 4;
    private final static int PROJECT = 5;//非必填
    private final static int ACCOUNT = 6;


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
    @BindView(R.id.rec_name)
    EditText recNameEdit;
    @BindView(R.id.rec_phone)
    EditText recPhoneEdit;
    @BindView(R.id.rec_area)
    EditText recAreaEdit;
    @BindView(R.id.rec_address)
    EditText recAddressEdit;
    @BindView(R.id.project)
    EditText projectEdit;

    @BindView(R.id.out_num)
    EditText outNumEdit;
    @BindView(R.id.remark)
    EditText remarkEdit;
    @BindView(R.id.money_account)
    EditText moneyAccountEdit;
    @BindView(R.id.account_container)
    LinearLayout accountContainer;

    private boolean isRetail;
    private OrderSalesRequest request = new OrderSalesRequest();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isRetail = getIntent().getBooleanExtra(ParameterConstant.IS_RETAIL_CREATE, false);
        mTitleBar.updateTitleBar(isRetail ? "新建零售订单" : "新建销售订单");
        accountContainer.setVisibility(isRetail ? View.VISIBLE : View.GONE);
        discountPrice.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                setTotalPriceView();
            }
        });
    }

    @OnClick({R.id.customer_select, R.id.other_cost_select, R.id.store_select, R.id.rec_area_select, R.id.project_select, R.id.money_account_select})
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
                intent.setClass(AddOrderSalesActivity.this, StoreListActivity.class);
                intent.putExtra(ParameterConstant.IS_SELECTED, true);
                startActivityForResult(intent, STORE);
                break;
            case R.id.rec_area_select:
                PickerDialogView.showAddress(this, new onCityCallBack() {
                    @Override
                    public void cityModelSelected(CityModel citySelected) {
                        if (citySelected != null) {
                            recAreaEdit.setText(citySelected.getProvincename() + " " + citySelected.getCityname() + " " + citySelected.getAreaname());
                            request.setRecAreaID(citySelected.getAreaid());
                        }
                    }
                });
                break;
            case R.id.project_select:
                intent.setClass(AddOrderSalesActivity.this, ProjectManageActivity.class);
                intent.putExtra(ParameterConstant.IS_SELECTED, true);
                startActivityForResult(intent, PROJECT);
                break;
            case R.id.money_account_select:
                intent.setClass(AddOrderSalesActivity.this, MoneyAccountListActivity.class);
                startActivityForResult(intent, ACCOUNT);
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
        request.setOrderGoodsStr(getOrderGoodsStr7());
        request.setDiscountAmount(mdiscountPrice);
        request.setTotalMoney(mTotalPrice);
        request.setRealMoney(Integer.parseInt(mTotalPrice) - Integer.parseInt(mdiscountPrice) + "");
        request.setOrderTime(orderTime);
        request.setOutNum(outNumEdit.getText().toString());
        request.setOrderMakerUserID(UserHelper.getUserId());
        request.setRemark(remarkEdit.getText().toString());

        request.setRecName(recNameEdit.getText().toString());
        request.setRecPhone(recPhoneEdit.getText().toString());
        request.setRecAddress(recAddressEdit.getText().toString());

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

        if (TextUtils.isEmpty(request.getRecName())) {
            showToast("请输入收货人姓名");
            return;
        }

        if (TextUtils.isEmpty(request.getRecPhone())) {
            showToast("请输入联系方式");
            return;
        }

        if (TextUtils.isEmpty(request.getRecAreaID())) {
            showToast("请输入区域地址");
            return;
        }
        if (TextUtils.isEmpty(request.getRecAddress())) {
            showToast("请输入详细地址");
            return;
        }
        if (isRetail) {
            if (TextUtils.isEmpty(request.getMoneyAccountID())) {
                showToast("请输入账户");
                return;
            }
            addOrderRetail();
        } else {
            addOrderSales();
        }
    }

    private void addOrderRetail() {
        ApiServiceManager.addOrderRetail(request)
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

    private void addOrderSales() {
        ApiServiceManager.addOrderSales(request)
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
                if (isRetail) {
                    request.setStoreType(1);
                }
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
            } else if (requestCode == GOODS) {
                setTotalPriceView();
            } else if (requestCode == ACCOUNT) {
                request.setMoneyAccountID(data.getStringExtra("Id"));
                moneyAccountEdit.setText(data.getStringExtra("Name"));
            }
        }
    }

    private void setTotalPriceView() {
        String disconunt = discountPrice.getText().toString();
        int disPtice = 0;
        if (!TextUtils.isEmpty(disconunt)) {
            disPtice = Integer.parseInt(disconunt);
        }
        totalPrice.setText((totalMoney - disPtice) + "");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_sales_create;
    }


    public interface onCityCallBack {
        void cityModelSelected(CityModel citySelected);
    }
}
