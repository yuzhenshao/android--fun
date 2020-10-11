package com.mfzn.deepuses.purchasesellsave.store.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.response.store.StockWarningResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.store.adapter.StockWarningAdapter;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class StockWarningListActivity extends BasicListActivity<StockWarningResponse.StockWarning> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("库存预警");
    }

    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.getStockWarningList("", "")
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<StockWarningResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<StockWarningResponse> reuslt) {
                        StockWarningResponse response = reuslt.getRes();
                        if (response != null) {
                            if (!ListUtil.isEmpty(response.getData())) {
                                refreshSource(response.getData());
                                return;
                            }
                        }
                        showNoDataView();
                    }
                });
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        StockWarningAdapter mAdapter = new StockWarningAdapter(this, mSourceList);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int i) {
                if (view.getId() == R.id.status) {
                    ApiServiceManager.notifyBuy(mSourceList.get(i).getData_id())
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
                                    showToast("提醒成功");
                                }
                            });
                }
            }
        });
        return mAdapter;
    }
}
