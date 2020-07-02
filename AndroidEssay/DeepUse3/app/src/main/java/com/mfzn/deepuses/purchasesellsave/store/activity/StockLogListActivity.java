package com.mfzn.deepuses.purchasesellsave.store.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.response.store.StockLogListResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.store.adapter.StockLogsAdapter;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class StockLogListActivity extends BasicListActivity<StockLogListResponse.ListBean.StockLogResponse> {

    @BindView(R.id.sum_stock)
    TextView sumStock;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("库存流水");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_stock_log_list_view;
    }


    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.getStockLogList()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<StockLogListResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<StockLogListResponse> reuslt) {
                        StockLogListResponse response = reuslt.getRes();
                        sumStock.setText("库存总数量：" + response.getSumStockNum());

                        if (response != null) {
                            if (response.getList() != null && !ListUtil.isEmpty(response.getList().getData())) {
                                refreshSource(response.getList().getData());
                                return;
                            }
                        }
                        showNoDataView();
                    }
                });
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        StockLogsAdapter mAdapter = new StockLogsAdapter(this, mSourceList);
        return mAdapter;
    }

    protected void rightPressedAction() {

    }
}
