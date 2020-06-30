package com.mfzn.deepuses.purchasesellsave.sale.activity;

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
import com.mfzn.deepuses.bean.request.sale.OrderTakeGoodsBackRequest;
import com.mfzn.deepuses.bean.response.settings.StoreResponse;
import com.mfzn.deepuses.common.PickerDialogView;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.activity.GoodsSelectListActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.StoreListActivity;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class AddOrderTakeBackActivity extends BaseAddCustomerAndGoodsActivity {

    @BindView(R.id.customer)
    EditText customerEdit;
    @BindView(R.id.store)
    EditText storeEdit;
    @BindView(R.id.out_num)
    EditText outNumEdit;

    @BindView(R.id.remark)
    EditText remarkEdit;
    private final static int STORE = 4;
    private OrderTakeGoodsBackRequest request = new OrderTakeGoodsBackRequest();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("新建领货归还单");
    }

    @OnClick({R.id.customer_select, R.id.store_select})
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
        }
    }

    @Override
    protected void commitAction() {
        request.setTakerUserID(companyCustomerID);
        request.setOrderGoodsStr(getOrderGoodsStr());
        request.setOrderTime(System.currentTimeMillis());
        request.setOutNum(outNumEdit.getText().toString());
        request.setOrderMakerUserID(UserHelper.getUid());
        request.setRemark(remarkEdit.getText().toString());

        if (TextUtils.isEmpty(request.getOrderMakerUserID())) {
            showToast("请输入归还人");
            return;
        }

        if (TextUtils.isEmpty(request.getOrderGoodsStr())) {
            showToast("请输入商品信息");
            return;
        }

        if (TextUtils.isEmpty(request.getStoreID())) {
            showToast("请输入入货仓库");
            return;
        }

        if (TextUtils.isEmpty(outNumEdit.getText().toString())) {
            showToast("请输入外部单号");
            return;
        }

        ApiServiceManager.addOrderTakeGoodsBack(request)
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
            } else if (requestCode == USER) {
                request.setTakerUserID(data.getStringExtra("Id"));
                customerEdit.setText(data.getStringExtra("Name"));
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_take_back_create;
    }

}
