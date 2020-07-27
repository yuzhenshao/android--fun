package com.mfzn.deepuses.purchasesellsave.sale.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.myteam.SelectManageActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.request.sale.OrderTakeGoodsRequest;
import com.mfzn.deepuses.bean.response.settings.StoreResponse;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.activity.StoreListActivity;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.UserHelper;
import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class AddOrderTakeActivity extends BaseAddCustomerAndGoodsActivity {

    @BindView(R.id.customer)
    EditText customerEdit;
    @BindView(R.id.store)
    EditText storeEdit;

    @BindView(R.id.out_num)
    EditText outNumEdit;
    @BindView(R.id.remark)
    EditText remarkEdit;
    private final static int STORE = 4;
    private OrderTakeGoodsRequest request = new OrderTakeGoodsRequest();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("新建个人领货单");
    }

    @OnClick({R.id.customer_select, R.id.store_select})
    public void viewClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.customer_select:
                intent.setClass(this, SelectManageActivity.class);
                intent.putExtra(Constants.SINGLE, true);
                startActivityForResult(intent, USER);
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
        request.setOrderGoodsStr(getOrderGoodsIdAndNum());
        request.setOrderTime(orderTime);
        request.setOutNum(outNumEdit.getText().toString());
        request.setOrderMakerUserID(UserHelper.getUserId());
        request.setRemark(remarkEdit.getText().toString());

        if (TextUtils.isEmpty(request.getOrderMakerUserID())) {
            showToast("请输入领货人");
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

        ApiServiceManager.addOrderTakeGoods(request)
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
        if (data != null) {
            if (requestCode == STORE) {
                StoreResponse storeResponse = (StoreResponse) data.getSerializableExtra(ParameterConstant.STORE);
                request.setStoreID(storeResponse.getStoreID());
                storeEdit.setText(storeResponse.getStoreName());
            } else if (requestCode == USER) {
                ZuzhiJiagouModel.StaffBean staffBean = (ZuzhiJiagouModel.StaffBean) data.getSerializableExtra(Constants.STAFFBEAN);
                if (staffBean != null) {
                    request.setTakerUserID(staffBean.getUserID());
                    customerEdit.setText(staffBean.getStaffName());
                }
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_take_create;
    }

}
