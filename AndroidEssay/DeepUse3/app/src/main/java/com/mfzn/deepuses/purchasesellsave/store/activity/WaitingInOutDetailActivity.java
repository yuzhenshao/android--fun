package com.mfzn.deepuses.purchasesellsave.store.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.settings.GoodsInfoResponse;
import com.mfzn.deepuses.bean.response.store.WaitingInDetailResponse;
import com.mfzn.deepuses.bean.response.store.WaitingOutDetailResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.store.adapter.GoodsInOutAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import io.reactivex.Flowable;

public class WaitingInOutDetailActivity extends BasicActivity {

    @BindView(R.id.order_num)
    TextView outNumView;
    @BindView(R.id.adress)
    TextView adressView;
    @BindView(R.id.btn_commit)
    Button commitBtn;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.adress_container)
    LinearLayout addressContainer;

    private List<WaitingInDetailResponse> goodsInfoResponseList = new ArrayList<>();

    private GoodsInOutAdapter adapter;
    private int type;
    private String orderId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_wait_in_out_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        type = getIntent().getIntExtra(ParameterConstant.CAPITAL_TYPE, 0);
        mTitleBar.updateTitleBar(type == 0 ? "待出库" : "待入库");
        commitBtn.setText(type == 0 ? "出库" : "入库");
        outNumView.setText(getIntent().getStringExtra(ParameterConstant.GOODS_ID));
        addressContainer.setVisibility(type == 0 ? View.VISIBLE : View.GONE);
        orderId = getIntent().getStringExtra(ParameterConstant.ORDER_ID);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GoodsInOutAdapter(WaitingInOutDetailActivity.this, goodsInfoResponseList);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int i) {
                int value = goodsInfoResponseList.get(i).getSize();
                if (view.getId() == R.id.subtraction) {
                    if (value > 1) {
                        value += -1;
                        goodsInfoResponseList.get(i).setSize(value);
                        adapter.notifyItemChanged(i);
                    }
                } else if (view.getId() == R.id.plus) {
                    if (value < goodsInfoResponseList.get(i).getNeedDone()) {
                        value += 1;
                        goodsInfoResponseList.get(i).setSize(value);
                        adapter.notifyItemChanged(i);
                    }
                }

            }
        });
        recyclerView.setAdapter(adapter);
        if (type == 0) {
            getWaitOutDetail();
        } else {
            getWaitInDetail();
        }

    }


    private void getWaitInDetail() {
        showDialog();
        ApiServiceManager.waitingInDetail(orderId)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<WaitingInDetailResponse>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        hideDialog();
                        showToast(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<List<WaitingInDetailResponse>> reuslt) {
                        hideDialog();
                        if (reuslt != null && !ListUtil.isEmpty(reuslt.getRes())) {
                            goodsInfoResponseList.clear();
                            goodsInfoResponseList.addAll(reuslt.getRes());
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void getWaitOutDetail() {
        showDialog();
        ApiServiceManager.waitingOutDetail(orderId)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<WaitingOutDetailResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        hideDialog();
                        showToast(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<WaitingOutDetailResponse> reuslt) {
                        hideDialog();
                        WaitingOutDetailResponse mWaitingInOutDetailResponse = reuslt.getRes();
                        WaitingOutDetailResponse.OrderInfoResponse orderInfoResponse = mWaitingInOutDetailResponse.getOrderInfo();
                        adressView.setText(TextUtils.isEmpty(orderInfoResponse.getFullAddress()) ? "无地址" : orderInfoResponse.getFullAddress());
                        goodsInfoResponseList.clear();
                        goodsInfoResponseList.addAll(mWaitingInOutDetailResponse.getGoodsInfo());
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @OnClick({R.id.btn_commit})
    public void commitAction() {
        Flowable<HttpResult> todoFloeable;
        String detailIDs = "";
        String outNums = "";
        if (!ListUtil.isEmpty(goodsInfoResponseList)) {
            for (WaitingInDetailResponse goodsInfoResponse : goodsInfoResponseList) {
                detailIDs = detailIDs + (TextUtils.isEmpty(detailIDs) ? "" : ",") + goodsInfoResponse.getDetailID();
                outNums += (TextUtils.isEmpty(outNums) ? "" : ",") + goodsInfoResponse.getSize();
                Log.d("syz", detailIDs + "/" + outNums);
            }
        }
        if (type == 0) {
            todoFloeable = ApiServiceManager.doPartOut(detailIDs, outNums, orderId);
        } else {
            todoFloeable = ApiServiceManager.doPartIn(detailIDs, outNums, orderId);
        }

        todoFloeable
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
                        showToast(reuslt.getMsg());
                        finish();
                    }
                });
    }
}
