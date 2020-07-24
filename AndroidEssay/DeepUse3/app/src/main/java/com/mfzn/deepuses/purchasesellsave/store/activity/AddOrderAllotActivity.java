package com.mfzn.deepuses.purchasesellsave.store.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.request.store.OrderAllotAddRequest;
import com.mfzn.deepuses.bean.response.settings.StoreResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.sale.activity.BaseAddCustomerAndGoodsActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.StoreListActivity;
import com.mfzn.deepuses.utils.UserHelper;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class AddOrderAllotActivity extends BaseAddCustomerAndGoodsActivity {

    private final static int STORE_IN = 4;
    private final static int STORE_OUT = 5;

    @BindView(R.id.store_out)
    EditText storeOutEdit;
    @BindView(R.id.store_in)
    EditText storeInEdit;
    @BindView(R.id.out_num)
    EditText outNumEdit;

    private OrderAllotAddRequest orderAllotAddRequest = new OrderAllotAddRequest();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("新建调拨单");
    }

    @OnClick({R.id.store_out_select, R.id.store_in_select})
    public void viewClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.store_out_select:
                intent.setClass(this, StoreListActivity.class);
                intent.putExtra(ParameterConstant.IS_SELECTED, true);
                startActivityForResult(intent, STORE_OUT);
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
        orderAllotAddRequest.setOrderGoodsStr(getOrderGoodsIdAndNum());
        orderAllotAddRequest.setOrderTime(orderTime);
        orderAllotAddRequest.setOutNum(outNumEdit.getText().toString());
        orderAllotAddRequest.setOrderMakerUserID(UserHelper.getUserId());

        if (TextUtils.isEmpty(orderAllotAddRequest.getOrderGoodsStr())) {
            showToast("请输入商品信息");
            return;
        }

        if (TextUtils.isEmpty(orderAllotAddRequest.getFromStoreID())) {
            showToast("请输入调出仓库");
            return;
        }

        if (TextUtils.isEmpty(orderAllotAddRequest.getToStoreID())) {
            showToast("请输入调用仓库");
            return;
        }

        ApiServiceManager.addOrderAllot(orderAllotAddRequest)
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
            if (requestCode == STORE_IN) {
                StoreResponse inStore = (StoreResponse) data.getSerializableExtra(ParameterConstant.STORE);
                orderAllotAddRequest.setToStoreID(inStore.getStoreID());
                storeInEdit.setText(inStore.getStoreName());
            } else if (requestCode == STORE_OUT) {
                StoreResponse outStore = (StoreResponse) data.getSerializableExtra(ParameterConstant.STORE);
                orderAllotAddRequest.setFromStoreID(outStore.getStoreID());
                storeOutEdit.setText(outStore.getStoreName());
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_allot_create;
    }

}
