package com.mfzn.deepuses.purchasesellsave.store.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.response.store.GoodsStockResponse;
import com.mfzn.deepuses.bean.response.store.StockWarningResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.store.adapter.GoodsStockAdapter;
import com.mfzn.deepuses.purchasesellsave.store.adapter.StockWarningAdapter;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class StockListActivity extends BasicListActivity<GoodsStockResponse.ListBean.StockResponse> {

    @BindView(R.id.sum_stock)
    TextView sumStock;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("库存查询");
        initSearch("搜索产品名称、条码、货号");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_list;
    }


    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.getGoodsStock(getSearchKeyword())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<GoodsStockResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<GoodsStockResponse> reuslt) {
                        GoodsStockResponse response = reuslt.getRes();
                        if (response != null) {
                            sumStock.setText("库存总数量：" + response.getSumStockNum());
                            if (response.getList() != null) {
                                if (!ListUtil.isEmpty(response.getList().getData())) {
                                    if (TextUtils.isEmpty(getSearchKeyword())) {
                                        refreshSource(response.getList().getData());
                                    } else {
                                        refreshSearchSource(response.getList().getData());
                                    }
                                    return;
                                }
                            }
                        }
                        showNoDataView();
                    }
                });
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        GoodsStockAdapter mAdapter = new GoodsStockAdapter(this, mSourceList);
        return mAdapter;
    }

    @Override
    protected void searchAction(String keyword) {
        getResourceList();
    }
}
