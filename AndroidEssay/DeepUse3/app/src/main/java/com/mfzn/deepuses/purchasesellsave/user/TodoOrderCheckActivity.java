package com.mfzn.deepuses.purchasesellsave.user;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.WaitingCheckResponse;
import com.mfzn.deepuses.bean.response.user.WaitingCheckListResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import io.reactivex.Flowable;

public class TodoOrderCheckActivity extends BasicActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.empty_view_root)
    LinearLayout emptyView;
    @BindView(R.id.empty_img)
    ImageView emptyImg;
    @BindView(R.id.empty_text)
    TextView emptyText;
    @BindView(R.id.menu_title)
    TextView menuTitleView;


    @BindView(R.id.order_purchase_number)
    TextView purchaseNumberView;
    @BindView(R.id.order_purchase_back_number)
    TextView purchaseBackNumberView;
    @BindView(R.id.order_offer_number)
    TextView offerNumberView;
    @BindView(R.id.order_sales_number)
    TextView salesNumberView;
    @BindView(R.id.order_sales_back_number)
    TextView salesBackNumberView;
    @BindView(R.id.order_take_goods_number)
    TextView takeGoodsNumberView;
    @BindView(R.id.order_waiting_out_number)
    TextView waitingOutNumberView;
    @BindView(R.id.order_allot_number)
    TextView allotNumberView;
    @BindView(R.id.order_store_check_number)
    TextView storeCheckNumberView;
    @BindView(R.id.order_retail_back_number)
    TextView retailBackNumberView;
    @BindView(R.id.order_income_expense_number)
    TextView incomeExpenseNumberView;
    @BindView(R.id.order_store_check_all_number)
    TextView storeCheckAllNumberView;

    @BindView(R.id.order_purchase)
    TextView orderPurchaseView;

    private View curSelectedView;

    private TodoOrderAdapter adapter;
    private List<WaitingCheckListResponse.WaitingCheckOrderPurchaseResponse> resourceList = new ArrayList<>();
    Flowable<HttpResult<WaitingCheckListResponse>> todoFloeable = ApiServiceManager.waitingCheckOrderPurchase();
    private static int REFRESH_TAG = 101;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        curSelectedView = orderPurchaseView;
        mTitleBar.updateTitleBar("待审核");
        adapter = new TodoOrderAdapter(this, resourceList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int i) {
                Intent intent = new Intent(TodoOrderCheckActivity.this, TodoOrderCheckDetailActivity.class);
                intent.putExtra(ParameterConstant.ORDER_PURCHASE_RESPONSE, resourceList.get(i));
                startActivityForResult(intent, REFRESH_TAG);
            }
        });

        getResource();
        initNumber();
    }

    private void initNumber() {
        WaitingCheckResponse waitingCheckResponse = new WaitingCheckResponse();//作为参数传递，活着调用接口
        purchaseNumberView.setText(waitingCheckResponse.getOrderPurchase() + "");
        purchaseBackNumberView.setText(waitingCheckResponse.getOrderPurchaseBack() + "");
        offerNumberView.setText(waitingCheckResponse.getOrderOffer() + "");
        salesNumberView.setText(waitingCheckResponse.getOrderSales() + "");
        salesBackNumberView.setText(waitingCheckResponse.getOrderSalesBack() + "");
        takeGoodsNumberView.setText(waitingCheckResponse.getOrderTakeGoods() + "");
        waitingOutNumberView.setText(waitingCheckResponse.getOrderWaitingOut() + "");
        allotNumberView.setText(waitingCheckResponse.getOrderAllot() + "");
        storeCheckNumberView.setText(waitingCheckResponse.getOrderStoreCheck() + "");
        retailBackNumberView.setText(waitingCheckResponse.getOrderRetailBack() + "");
        incomeExpenseNumberView.setText(waitingCheckResponse.getOrderIncomeExpense() + "");
        storeCheckAllNumberView.setText(waitingCheckResponse.getOrderStoreCheckAll() + "");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_todo_order_check;
    }

    @OnClick({R.id.order_purchase, R.id.order_purchase_back, R.id.order_offer, R.id.order_sales,
            R.id.order_sales_back, R.id.order_take_goods, R.id.order_waiting_out, R.id.order_allot,
            R.id.order_store_check, R.id.order_retail_back, R.id.order_income_expense, R.id.order_store_check_all})
    public void clickView(View view) {
        curSelectedView.setBackgroundColor(Color.parseColor("#F5F7FA"));
        view.setBackgroundColor(Color.parseColor("#FFFFFF"));
        curSelectedView = view;
        menuTitleView.setText(((TextView) view).getText());

        switch (view.getId()) {
            case R.id.order_purchase:
                todoFloeable = ApiServiceManager.waitingCheckOrderPurchase();
                break;
            case R.id.order_purchase_back:
                todoFloeable = ApiServiceManager.waitingCheckOrderPurchaseBack();
                break;
            case R.id.order_offer:
                todoFloeable = ApiServiceManager.waitingCheckOrderOffer();
                break;
            case R.id.order_sales:
                todoFloeable = ApiServiceManager.waitingCheckOrderSales();
                break;
            case R.id.order_sales_back:
                todoFloeable = ApiServiceManager.waitingCheckOrderSalesBack();
                break;
            case R.id.order_take_goods:
                todoFloeable = ApiServiceManager.waitingCheckOrderTakeGoods();
                break;
            case R.id.order_waiting_out:
                todoFloeable = ApiServiceManager.waitingCheckOrderOtherOut();
                break;
            case R.id.order_allot:
                todoFloeable = ApiServiceManager.waitingCheckOrderAllot();
                break;
            case R.id.order_store_check:
                todoFloeable = ApiServiceManager.waitingCheckOrderStoreCheck();
                break;
            case R.id.order_retail_back:
                todoFloeable = ApiServiceManager.waitingCheckOrderRetailBack();
                break;
            case R.id.order_income_expense:
                todoFloeable = ApiServiceManager.waitingCheckIncomeExpense();
                break;
            case R.id.order_store_check_all:
                todoFloeable = ApiServiceManager.waitingCheckOrderStoreCheckAll();
                break;
        }
        getResource();
    }

    private void getResource() {
        if (todoFloeable != null) {
            showDialog();
            todoFloeable.compose(XApi.getApiTransformer())
                    .compose(XApi.getScheduler())
                    .compose(bindToLifecycle())
                    .subscribe(new ApiSubscriber<HttpResult<WaitingCheckListResponse>>() {
                        @Override
                        protected void onFail(NetError error) {
                            hideDialog();
                            emptyView.setVisibility(View.VISIBLE);
                            emptyText.setText(error.getMessage());
                        }

                        @Override
                        public void onNext(HttpResult<WaitingCheckListResponse> reuslt) {
                            hideDialog();
                            WaitingCheckListResponse response = reuslt.getRes();
                            if (response != null && !ListUtil.isEmpty(response.getData())) {
                                emptyView.setVisibility(View.GONE);
                                resourceList.clear();
                                resourceList.addAll(response.getData());
                                adapter.notifyDataSetChanged();
                                return;
                            }
                            emptyView.setVisibility(View.VISIBLE);
                            emptyText.setText("暂无数据");
                        }
                    });
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REFRESH_TAG) {
            getResource();
        }
    }
}
