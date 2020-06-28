package com.mfzn.deepuses.purchasesellsave.store.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.request.CommodityRequest;
import com.mfzn.deepuses.bean.request.store.OrderStockCheckAddResponse;
import com.mfzn.deepuses.bean.response.store.StoreCheckGoodsResponse;
import com.mfzn.deepuses.bean.response.store.StoreCheckResponse;
import com.mfzn.deepuses.common.PickerDialogView;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.activity.GoodsListActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.GoodsSelectListActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.StoreListActivity;
import com.mfzn.deepuses.purchasesellsave.store.adapter.StoreCheckGoodsAdapter;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class OrderStockCheckAddActivity extends BasicActivity {

    @BindView(R.id.store_name)
    EditText storeName;
    @BindView(R.id.select_store)
    ImageView selectStore;
    @BindView(R.id.select_goods)
    ImageView selectGoods;
    //    @BindView(R.id.goods_container)
//    RelativeLayout goodsContainer;
    @BindView(R.id.goods_recyleview)
    RecyclerView goodsRecyclerView;
    @BindView(R.id.line_space)
    View lineSpace;

    @BindView(R.id.order_time)
    EditText orderTime;
    @BindView(R.id.out_num)
    EditText outNum;
    @BindView(R.id.order_maker_user_name)
    TextView orderMakerUserName;
    @BindView(R.id.remark)
    EditText remark;

    private static int STORE_CODE = 100;
    private static int GOODS_CODE = 101;

    private StoreCheckResponse storeCheckResponse;
    private List<StoreCheckGoodsResponse> goodsInfo = new ArrayList<>();

    private StoreCheckGoodsAdapter storeCheckGoodsAdapter;

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
            goodsInfo = storeCheckResponse.getGoodsInfo();
            storeName.setText(storeCheckResponse.getStoreName());
            orderTime.setText(DateUtils.getDateFromMillsec(storeCheckResponse.getAddTime()));
            outNum.setText(storeCheckResponse.getOutNum());
            orderMakerUserName.setText(storeCheckResponse.getOrderMakerUserName());
            remark.setText(storeCheckResponse.getRemark());
        } else {
            orderTime.setText(DateUtils.longToString(System.currentTimeMillis()));
            orderMakerUserName.setText(UserHelper.getU_name());
        }
        setSpaceLineVisibility();
        storeCheckGoodsAdapter = new StoreCheckGoodsAdapter(OrderStockCheckAddActivity.this, goodsInfo);
        goodsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        goodsRecyclerView.setAdapter(storeCheckGoodsAdapter);
        storeCheckGoodsAdapter.notifyDataSetChanged();
    }

    private void setSpaceLineVisibility() {
        lineSpace.setVisibility(ListUtil.isEmpty(goodsInfo) ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == STORE_CODE) {

            } else if (requestCode == GOODS_CODE) {

            }
        }
    }

    @OnClick({R.id.btn_commit, R.id.select_store, R.id.select_goods, R.id.select_order_time})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_commit:
                if (storeCheckResponse == null) {
                    addOrderStockChec();
                } else {
                    editOrderStockCheck();
                }
                break;
            case R.id.select_order_time:
                PickerDialogView.showTimeSelect(this, new OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date, View v) {
                        orderTime.setText(DateUtils.dateFormat("yyyy/MM/dd", date));
                    }
                });
                break;
            case R.id.select_store:
                Intent storeIntent = new Intent();
                storeIntent.setClass(OrderStockCheckAddActivity.this, StoreListActivity.class);
                startActivityForResult(storeIntent, STORE_CODE);
                break;
            case R.id.select_goods:
                Intent intent = new Intent();
                intent.setClass(OrderStockCheckAddActivity.this, GoodsSelectListActivity.class);
                startActivityForResult(intent, GOODS_CODE);
                break;
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_stock_check_create;
    }

    private void addOrderStockChec() {
        OrderStockCheckAddResponse response = new OrderStockCheckAddResponse();
        ApiServiceManager.addOrderStockChec(response)
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
        OrderStockCheckAddResponse response = new OrderStockCheckAddResponse();
        ApiServiceManager.editOrderStockCheck(response)
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

}
