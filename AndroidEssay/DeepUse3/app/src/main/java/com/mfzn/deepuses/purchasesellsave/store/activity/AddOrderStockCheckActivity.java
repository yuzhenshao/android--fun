package com.mfzn.deepuses.purchasesellsave.store.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.request.store.OrderStockCheckRequest;
import com.mfzn.deepuses.bean.response.settings.GoodsInfoResponse;
import com.mfzn.deepuses.bean.response.settings.StoreResponse;
import com.mfzn.deepuses.bean.response.store.OrderStockCheckResponse;
import com.mfzn.deepuses.common.PickerDialogView;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
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

public class AddOrderStockCheckActivity extends BasicActivity {

    @BindView(R.id.store_name)
    EditText storeEdit;
    @BindView(R.id.out_num)
    EditText outNum;
    @BindView(R.id.remark)
    EditText remark;
    @BindView(R.id.goods_recyleview)
    RecyclerView goodsRecyclerView;
    @BindView(R.id.user_name)
    TextView userNameView;
    @BindView(R.id.order_time)
    EditText orderTimeEdit;

    protected List<GoodsInfoResponse> goodsSelectedList = new ArrayList<>();
    protected StoreCheckGoodsAdapter adapter;
    private static int STORE_CODE = 100;
    protected final static int GOODS = 102;
    private OrderStockCheckResponse storeCheckResponse;
    private OrderStockCheckRequest request = new OrderStockCheckRequest();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userNameView.setText(UserHelper.getU_name());
        goodsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StoreCheckGoodsAdapter(this, goodsSelectedList);
        goodsRecyclerView.setAdapter(adapter);
        initView();
        initData();
    }

    private void initView() {
        mTitleBar.updateTitleBar("新建盘点单");
    }

    private void initData() {
        storeCheckResponse = (OrderStockCheckResponse) getIntent().getSerializableExtra(ParameterConstant.STORE);
        mTitleBar.updateTitleBar(storeCheckResponse == null ? "新建盘点单" : "编辑盘点单");
        if (storeCheckResponse != null) {
            List<GoodsInfoResponse> goods = storeCheckResponse.getGoodsInfo();
            if (!ListUtil.isEmpty(goods)) {
                goodsSelectedList.clear();
                goodsSelectedList.addAll(goods);
                adapter.notifyDataSetChanged();
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

    @OnClick({R.id.select_store, R.id.goods_select, R.id.btn_commit, R.id.order_time_select})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_store:
                Intent storeIntent = new Intent();
                storeIntent.setClass(AddOrderStockCheckActivity.this, StoreListActivity.class);
                storeIntent.putExtra(ParameterConstant.IS_SELECTED, true);
                startActivityForResult(storeIntent, STORE_CODE);
                break;
            case R.id.order_time_select:
                PickerDialogView.showTimeSelect(this, new OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date, View v) {
                        request.setOrderTime((int) (date.getTime() / 1000));
                        orderTimeEdit.setText(DateUtils.dateFormat("yyyy/MM/dd", date));
                    }
                });
                break;
            case R.id.goods_select:
                Intent intent = new Intent();
                intent.setClass(this, GoodsSelectListActivity.class);
                intent.putExtra(ParameterConstant.COST_TYPE_NUMBER_SET, true);
                startActivityForResult(intent, GOODS);
                break;
            case R.id.btn_commit:
                commitAction();
                break;
        }

    }

    protected void commitAction() {
        request.setOutNum(outNum.getText().toString());
        request.setRemark(remark.getText().toString());

        request.setOrderGoodsStr(getOrderGoodsInfo());
        if (storeCheckResponse == null) {
            addOrderStockChec();
        } else {
            editOrderStockCheck();
        }
    }

    /*
     * 商品信息：goodsID1（商品ID）,systemStockNum1（系统库存）,checkStockNum（盘点库存）;$goodsID2,....
     * */
    private String getOrderGoodsInfo() {
        StringBuffer stringBuffer = new StringBuffer();
        if (!ListUtil.isEmpty(goodsSelectedList)) {
            for (GoodsInfoResponse goods : goodsSelectedList) {
                stringBuffer.append(goods.getGoodsID()).append(",")
                        .append(goods.getSystemStockNum()).append(",")
                        .append(goods.getCheckStockNum()).append(";");
            }
        }
        return stringBuffer.toString();
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
                         setResult(Activity.RESULT_OK);
                        finish();
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
            } else if (requestCode == GOODS) {
                goodsSelectedList.clear();
                goodsSelectedList.addAll((List<GoodsInfoResponse>) data.getSerializableExtra("data"));
                adapter.notifyDataSetChanged();

            }
        }
    }
}
