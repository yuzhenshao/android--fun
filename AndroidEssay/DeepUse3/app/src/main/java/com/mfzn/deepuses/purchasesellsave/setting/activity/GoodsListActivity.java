package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.dialog.DialogUtils;
import com.libcommon.dialog.fragment.BaseDialogFragment;
import com.libcommon.dialog.fragment.CustomDialog;
import com.libcommon.dialog.listener.OnBindViewListener;
import com.libcommon.dialog.listener.OnViewClickListener;
import com.libcommon.dialog.view.BindViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.request.CommodityRequest;
import com.mfzn.deepuses.bean.response.settings.GoodsInfoResponse;
import com.mfzn.deepuses.bean.response.settings.GoodsListResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsAdapter;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * @author yz @date 2020-05-03
 */
public class GoodsListActivity extends BasicListActivity<GoodsInfoResponse> {

    @BindView(R.id.search_container)
    LinearLayout searchContainer;
    @BindView(R.id.sum_stock)
    TextView sumStock;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("商品中心", R.mipmap.icon_titlebar_add);
        initSearch("搜索产品名称、条码、货号");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_list;
    }


    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.goodsList()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<GoodsListResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<GoodsListResponse> reuslt) {
                        GoodsListResponse goodsListResponse = reuslt.getRes();
                        if (goodsListResponse != null) {
                            sumStock.setText("库存总数量：" + goodsListResponse.getSumStockNum());
                            if (goodsListResponse.getList() != null) {
                                refreshSource(goodsListResponse.getList().getData());
                                return;
                            }
                        }
                        showNoDataView();
                    }
                });
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        GoodsAdapter mAdapter = new GoodsAdapter(this, mSourceList);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                GoodsInfoResponse request = mSourceList.get(i);
                if (request != null) {
                    Intent intent = new Intent(GoodsListActivity.this, GoodsDetailActivity.class);
                    intent.putExtra(ParameterConstant.GOODS_ID, request.getGoodsID());
                    startActivity(intent);
                }

            }
        });
        return mAdapter;
    }

    protected void searchAction(String keyword) {
        showDialog();
        ApiServiceManager.searchGoodsList(keyword)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<GoodsListResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<GoodsListResponse> reuslt) {
                        GoodsListResponse goodsListResponse = reuslt.getRes();
                        if (goodsListResponse != null) {
                            sumStock.setText("库存总数量：" + goodsListResponse.getSumStockNum());
                            if (goodsListResponse.getList() != null) {
                                refreshSearchSource(goodsListResponse.getList().getData());
                                return;
                            }
                        }
                        showNoDataView();
                    }
                });
    }


    @Override
    protected void rightPressedAction() {
        startActivity(new Intent(GoodsListActivity.this, CommodityCreateActivity.class));
    }
}
