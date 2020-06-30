package com.mfzn.deepuses.purchasesellsave.store.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.request.store.OrderStockCheckRequest;
import com.mfzn.deepuses.bean.response.settings.GoodsInfoResponse;
import com.mfzn.deepuses.bean.response.settings.StoreResponse;
import com.mfzn.deepuses.bean.response.store.StoreCheckResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.sale.activity.BaseAddCustomerAndGoodsActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.StoreListActivity;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class OrderStockCheckAddActivity extends BaseAddCustomerAndGoodsActivity {

    @BindView(R.id.store_name)
    EditText storeEdit;

    @BindView(R.id.out_num)
    EditText outNum;
    @BindView(R.id.remark)
    EditText remark;

    private static int STORE_CODE = 100;

    private StoreCheckResponse storeCheckResponse;
    private OrderStockCheckRequest request = new OrderStockCheckRequest();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        mTitleBar.updateTitleBar("新建盘点单");
    }

    private void initData() {
        storeCheckResponse = (StoreCheckResponse) getIntent().getSerializableExtra(ParameterConstant.STORE);
        mTitleBar.updateTitleBar(storeCheckResponse == null ? "新建盘点单" : "编辑盘点单");
        if (storeCheckResponse != null) {
            List<GoodsInfoResponse> goods = storeCheckResponse.getGoodsInfo();
            if (!ListUtil.isEmpty(goods)) {
                goodsSelectedList.clear();
                goodsSelectedList.addAll(goods);
                adapter.notifyDataSetChanged();
                setGoodsSelected();
            }
            storeEdit.setText(storeCheckResponse.getStoreName());
            orderTimeEdit.setText(DateUtils.getDateFromMillsec(storeCheckResponse.getAddTime()));
            outNum.setText(storeCheckResponse.getOutNum());
            userNameView.setText(storeCheckResponse.getOrderMakerUserName());
            remark.setText(storeCheckResponse.getRemark());
            request.setOrderID(storeCheckResponse.getOrderID());
            request.setOrderMakerUserID(storeCheckResponse.getOrderMakerUserID());
            request.setOrderTime(storeCheckResponse.getAddTime());
            request.setStoreID(storeCheckResponse.getStoreID());

        } else {
            request.setOrderMakerUserID(UserHelper.getUid());
        }
    }

    private void setGoodsSelected() {
        goodsPriceContainer.setVisibility(View.VISIBLE);
        int totalPrice = 0;
        number.setText("数量：" + goodsSelectedList.size());
        if (!ListUtil.isEmpty(goodsSelectedList)) {
            for (GoodsInfoResponse store : goodsSelectedList) {
                if (store.isHasTaxRate()) {
                    totalPrice += getPrice(store.getSalePriceWithTax());
                } else {
                    totalPrice += getPrice(store.getSalePrice());
                }
            }
        }
        price.setText("总价：" + totalPrice);
    }

    @OnClick({R.id.select_store})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_store:
                Intent storeIntent = new Intent();
                storeIntent.setClass(OrderStockCheckAddActivity.this, StoreListActivity.class);
                startActivityForResult(storeIntent, STORE_CODE);
                break;

        }

    }

    @Override
    protected void commitAction() {
        request.setOutNum(outNum.getText().toString());
        request.setRemark(remark.getText().toString());
        request.setOrderGoodsStr(getOrderGoodsStr());
        if (storeCheckResponse == null) {
            addOrderStockChec();
        } else {
            editOrderStockCheck();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_stock_check_create;
    }

    private void addOrderStockChec() {
        ApiServiceManager.addOrderStockCheck(request)
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
                        showToast("成功");
                    }
                });

    }

    private void editOrderStockCheck() {
        ApiServiceManager.editOrderStockCheck(request)
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
                        showToast("成功");
                    }
                });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == STORE_CODE) {
                StoreResponse store = (StoreResponse) data.getSerializableExtra(ParameterConstant.STORE);
                request.setStoreID(store.getStoreID());
                storeEdit.setText(store.getStoreName());

            }
        }
    }
}
