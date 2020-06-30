package com.mfzn.deepuses.purchasesellsave.store.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.mfzn.deepuses.bean.response.settings.StoreResponse;
import com.mfzn.deepuses.common.PickerDialogView;
import com.mfzn.deepuses.model.company.CityModel;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.sale.activity.AddOrderSalesActivity;
import com.mfzn.deepuses.purchasesellsave.sale.activity.BaseAddCustomerAndGoodsActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.GoodsSelectListActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.SetCostActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.StoreListActivity;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class AddOrderOtherOutActivity extends BaseAddCustomerAndGoodsActivity {

    private final static int STORE = 1;

    @BindView(R.id.customer)
    EditText customer;
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

    private OrderSalesRequest request = new OrderSalesRequest();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("新建其他出货单");
    }

    @OnClick({R.id.customer_select, R.id.store_select, R.id.rec_area_select,})
    public void viewClick(View v) {

        switch (v.getId()) {
            case R.id.customer_select:
                turnToCustomer();
                break;
            case R.id.store_select:
                Intent intent = new Intent();
                intent.setClass(this, StoreListActivity.class);
                intent.putExtra(ParameterConstant.IS_SELECTED, true);
                startActivityForResult(intent, STORE);
                break;
            case R.id.rec_area_select:
                PickerDialogView.showAddress(this, new AddOrderSalesActivity.onCityCallBack() {
                    @Override
                    public void cityModelSelected(CityModel citySelected) {
                        if (citySelected != null) {
                            recAreaEdit.setText(citySelected.getProvincename() + " " + citySelected.getCityname() + " " + citySelected.getAreaname());
                            request.setRecAreaID(citySelected.getAreaid());
                        }
                    }

                });
                break;
        }
    }

    @Override
    protected void commitAction() {
        request.setCompanyCustomerID(companyCustomerID);
        request.setOrderGoodsStr(getOrderGoodsStr());
        request.setOrderTime(System.currentTimeMillis());
        request.setOrderMakerUserID(UserHelper.getUid());
        request.setRecName(recNameEdit.getText().toString());
        request.setRecPhone(recPhoneEdit.getText().toString());
        request.setRecAddress(recAddressEdit.getText().toString());

        if (TextUtils.isEmpty(request.getOrderMakerUserID())) {
            showToast("请输入客户");
            return;
        }

        if (TextUtils.isEmpty(request.getOrderGoodsStr())) {
            showToast("请输入商品信息");
            return;
        }

        if (TextUtils.isEmpty(request.getStoreID())) {
            showToast("请输入出货仓库");
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

        ApiServiceManager.addOrderOtherOut(request)
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
                StoreResponse inStore = (StoreResponse) data.getSerializableExtra(ParameterConstant.STORE);
                request.setStoreID(inStore.getStoreID());
                storeEdit.setText(inStore.getStoreName());
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_other_out_create;
    }

}
