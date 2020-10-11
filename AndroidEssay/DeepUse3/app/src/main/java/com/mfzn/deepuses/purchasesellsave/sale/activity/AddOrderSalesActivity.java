package com.mfzn.deepuses.purchasesellsave.sale.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.project.ProjectManageActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.request.sale.OrderSalesRequest;
import com.mfzn.deepuses.bean.response.sale.OrderSalesListResponse;
import com.mfzn.deepuses.common.PickerDialogView;
import com.mfzn.deepuses.model.company.CityModel;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.activity.CommodityCreateActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.CommodityPhotoCreateActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.GoodsListActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.GoodsSelectListActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.MoneyAccountListActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.MyStoreListActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.StoreListActivity;
import com.mfzn.deepuses.utils.DateUtils;
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
    private final static int INPUT = 101;

    @BindView(R.id.customer)
    EditText customerEdit;
    @BindView(R.id.other_cost)
    EditText otherCostEdit;
    @BindView(R.id.discount_price)
    EditText discountPriceEdit;
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
        mTitleBar.updateTitleBar(isRetail ? "新建零售订单" : "新建销售订单", "导入");
        accountContainer.setVisibility(isRetail ? View.VISIBLE : View.GONE);
        discountPriceEdit.addTextChangedListener(new OnInputChangeListener() {
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
                intent.setClass(AddOrderSalesActivity.this, isRetail ? MyStoreListActivity.class : StoreListActivity.class);
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
                intent.putExtra(ParameterConstant.IS_SELECTED, true);
                startActivityForResult(intent, ACCOUNT);
                break;
        }
    }

    @Override
    protected void commitAction() {
        String mTotalPrice = totalPrice.getText().toString();
        String mdiscountPrice = discountPriceEdit.getText().toString();
        if (TextUtils.isEmpty(mTotalPrice)) {
            showToast("请输入单据总价格");
            return;
        }
        request.setOrderGoodsStr(getOrderGoodsStr7());
        request.setDiscountAmount(mdiscountPrice);
        request.setTotalMoney(mTotalPrice);
        request.setRealMoney(getRealMoney(mTotalPrice, mdiscountPrice));
        request.setOrderTime(orderTime);
        request.setOutNum(outNumEdit.getText().toString());
        request.setOrderMakerUserID(UserHelper.getUserId());
        request.setRemark(remarkEdit.getText().toString());

        request.setRecName(recNameEdit.getText().toString());
        request.setRecPhone(recPhoneEdit.getText().toString());
        request.setRecAddress(recAddressEdit.getText().toString());

        if (TextUtils.isEmpty(request.getOrderMakerUserID())) {
            showToast("请输入公司客户");
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
    protected void turnToGoodsSelected() {
        if (isRetail && request.getStoreID() == null) {
            showToast("请先选择出货仓库");
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, GoodsSelectListActivity.class);
        intent.putExtra(ParameterConstant.IS_PERSONAL_STORE_GOODS, isPersonalStoreGoods);
        startActivityForResult(intent, GOODS);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            if (requestCode == STORE) {
                request.setStoreID(data.getStringExtra("Id"));
                if (isRetail) {
                    int storeType = data.getIntExtra("Type", 0);
                    request.setStoreType(storeType);
                    isPersonalStoreGoods = storeType == 1 ? 0 : 1;
                    clearGoods();
                }
                storeEdit.setText(data.getStringExtra("Name"));
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
                setTotalPriceView();
            } else if (requestCode == GOODS) {
                setTotalPriceView();
            } else if (requestCode == ACCOUNT) {
                request.setMoneyAccountID(data.getStringExtra("Id"));
                moneyAccountEdit.setText(data.getStringExtra("Name"));
            } else if (requestCode == INPUT) {
                OrderSalesListResponse.OrderSalesResponse orderSalesResponse =
                        (OrderSalesListResponse.OrderSalesResponse) data.getSerializableExtra(ParameterConstant.INPUT_DATA);
                if (orderSalesResponse != null) {
                    request.setCompanyCustomerID(orderSalesResponse.getCustomerID());
                    customerEdit.setText(orderSalesResponse.getCustomerName());
                    setGoodsPriceContainer(orderSalesResponse.getGoodsInfo());
                    discountPriceEdit.setText(orderSalesResponse.getOrderMakerDiscount());

                    request.setStoreID(orderSalesResponse.getStoreID());
                    if (isRetail) {
                        request.setStoreType(1);
                    }
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

    private void setTotalPriceView() {
        String disconunt = discountPriceEdit.getText().toString();
        double disPtice = 0;
        if (!TextUtils.isEmpty(disconunt)) {
            disPtice = Double.parseDouble(disconunt);
        }
        totalPrice.setText((totalMoney - disPtice + getOtherCost()) + "");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_sales_create;
    }


    public interface onCityCallBack {
        void cityModelSelected(CityModel citySelected);
    }

    @Override
    protected void rightPressedAction() {
        Intent intent = new Intent(this, OrderInputListActivity.class);
        intent.putExtra(ParameterConstant.INPUT_TYPE, isRetail ? 1 : 2);//1 零售 2销售
        startActivityForResult(intent, INPUT);

//TODO 
//        View contentView = LayoutInflater.from(this).inflate(R.layout.goods_popupwindow, null, false);
//        TextView goodsPhotoEntry = contentView.findViewById(R.id.goods_photo_entry);
//        goodsPhotoEntry.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(AddOrderSalesActivity.this, CommodityPhotoCreateActivity.class));
//            }
//        });
//        TextView goodsFormEntry = contentView.findViewById(R.id.goods_form_entry);
//        goodsFormEntry.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(AddOrderSalesActivity.this, CommodityCreateActivity.class));
//            }
//        });
//
//        PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT, true);
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setAnimationStyle(R.style.popup_window_anim_style);
//        popupWindow.showAtLocation(mTitleBar, Gravity.TOP, 0,
//                mTitleBar.getHeight());

    }
}
