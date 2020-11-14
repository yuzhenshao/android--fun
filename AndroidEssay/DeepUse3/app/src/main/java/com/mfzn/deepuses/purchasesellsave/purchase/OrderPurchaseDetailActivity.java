package com.mfzn.deepuses.purchasesellsave.purchase;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.purchase.OrderPurchaseDetailResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsCheckAdapter;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsCostAdapter;
import com.mfzn.deepuses.utils.DateUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import io.reactivex.Flowable;

public class OrderPurchaseDetailActivity extends BasicActivity {

    @BindView(R.id.status_check)
    TextView statusCheckView;
    @BindView(R.id.status_check_store)
    TextView statusCheckStoreView;
    @BindView(R.id.status_check_pay)
    TextView statusCheckPayView;

    @BindView(R.id.supplier_name)
    TextView supplierNameView;
    @BindView(R.id.user_name)
    TextView userNameView;
    @BindView(R.id.user_phone)
    TextView userPhoneView;


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.recycler_cost_view)
    RecyclerView costRecyclerView;
    @BindView(R.id.discount_price)
    TextView discountPriceView;
    @BindView(R.id.total_money)
    TextView totalMoneyView;

    @BindView(R.id.store_name)
    TextView storeNameView;
    @BindView(R.id.num)
    TextView numView;
    @BindView(R.id.date)
    TextView dateView;
    @BindView(R.id.out_num)
    TextView outNumView;
    @BindView(R.id.order_user_name)
    TextView orderUserNameView;

    @BindView(R.id.empty_view_root)
    LinearLayout emptyView;
    @BindView(R.id.empty_text)
    TextView emptyText;

    private GoodsCheckAdapter adapter;
    private String orderId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_purchase_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("审核单据");
        orderId = getIntent().getStringExtra(ParameterConstant.ORDER_ID);
        getPurchaseDetail();
    }

    private void getPurchaseDetail() {
        ApiServiceManager.orderPurchaseInfo(orderId)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<OrderPurchaseDetailResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        emptyView.setVisibility(View.VISIBLE);
                        emptyText.setText(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<OrderPurchaseDetailResponse> reuslt) {
                        initData(reuslt.getRes());
                    }
                });
    }


    private void initData(OrderPurchaseDetailResponse response) {
        if (response == null) {
            emptyView.setVisibility(View.VISIBLE);
            emptyText.setText("暂无数据");
        } else {
            if (response.getIsCheck() != 0) {
                statusCheckView.setTextColor(Color.parseColor("#ff909399"));
                statusCheckStoreView.setText(response.getIsInStoreText());
                statusCheckStoreView.setTextColor(Color.parseColor(response.getIsInStore() == 2 ? "#3D7EFF" : "#ff909399"));
                statusCheckPayView.setText(response.getIsPayText());
                statusCheckPayView.setTextColor(Color.parseColor(response.getIsPay() == 2 ? "#3D7EFF" : "#ff909399"));
            }
            supplierNameView.setText(response.getSupplierName());
            userNameView.setText(response.getChargePerson());
            userPhoneView.setText(response.getChargePersonPhone());

            if (ListUtil.isEmpty(response.getOtherCost())) {
                costRecyclerView.setVisibility(View.GONE);
            } else {
                costRecyclerView.setVisibility(View.VISIBLE);
                GoodsCostAdapter costAdapter = new GoodsCostAdapter(response.getOtherCost());
                costRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                costRecyclerView.setAdapter(costAdapter);
            }
            adapter = new GoodsCheckAdapter(this, response.getGoodsInfo());
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);

            discountPriceView.setText("-"+response.getDiscountAmount());
            totalMoneyView.setText(response.getRealMoney());

            storeNameView.setText(response.getStoreName());
            numView.setText(response.getOrderNum());
            dateView.setText(DateUtils.longToString(response.getOrderTime()));
            outNumView.setText(response.getOutNum());
            orderUserNameView.setText(response.getOrderMakerUserName());

        }
    }

    @OnClick({R.id.btn_cancel, R.id.btn_delete})
    public void clickView(View view) {
        Flowable<HttpResult> flowable = null;
        switch (view.getId()) {
            case R.id.btn_cancel:
                flowable = ApiServiceManager.orderPurchaseCancel(orderId);
                break;
            case R.id.btn_delete:
                flowable = ApiServiceManager.orderPurchaseDelBatch(orderId);
                break;
        }
        if (flowable != null) {
            flowable
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
                            showToast("操作成功");
                            Intent intent = new Intent();
                            setResult(Activity.RESULT_OK, intent);
                            finish();
                        }
                    });
        }

    }
}
