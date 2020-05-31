package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.dialog.fragment.CustomDialog;
import com.libcommon.dialog.listener.OnBindViewListener;
import com.libcommon.dialog.view.BindViewHolder;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.request.CommodityRequest;
import com.mfzn.deepuses.bean.response.settings.GoodsListResponse;
import com.mfzn.deepuses.bean.response.store.StoreCheckGoodsResponse;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsAdapter;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsSelectedAdapter;
import com.mfzn.deepuses.purchasesellsave.setting.manager.StoreWarnManager;
import com.mfzn.deepuses.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class GoodsSelectListActivity extends BasicListActivity<CommodityRequest> {

    @BindView(R.id.search_container)
    LinearLayout searchContainer;
    @BindView(R.id.sum_stock)
    TextView sumStock;
    @BindView(R.id.recycler_view)
    RecyclerView goodsRecyclerView;
    @BindView(R.id.select_goods_container)
    RelativeLayout selectGoodsContainer;
    @BindView(R.id.goods_size)
    TextView goodsSize;
    @BindView(R.id.goods_price)
    TextView goodsPrice;

    private List<CommodityRequest> goodsSelectedList = new ArrayList<>();
    private List<StoreCheckGoodsResponse> storeCheckGoodsResponseList = new ArrayList<>();
    private GoodsSelectedAdapter goodsSelectedAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("商品中心");
        goodsSelectedAdapter = new GoodsSelectedAdapter(this, goodsSelectedList);
        goodsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        goodsRecyclerView.setAdapter(adapter);
        initSearch("搜索产品名称、条码、货号");
        getResourceList();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_select_list;
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
    protected void searchAction(String keyword) {
        ApiServiceManager.searchGoodsList(keyword)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<GoodsListResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showToast(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<GoodsListResponse> reuslt) {
                        GoodsListResponse goodsListResponse = reuslt.getRes();
                        if (goodsListResponse != null && goodsListResponse.getList() != null
                                && !ListUtil.isEmpty(goodsListResponse.getList().getData())) {
                            refreshSearchSource(goodsListResponse.getList().getData());
                        }
                    }
                });
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        GoodsAdapter mAdapter = new GoodsAdapter(this, mSourceList);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                showGoodsStoreSetDialog(mSourceList.get(i));
            }
        });
        return mAdapter;
    }

    private void showGoodsStoreSetDialog(CommodityRequest item) {
        new CustomDialog.Builder().setLayoutRes(R.layout.dialog_goods_store_set)
                .setWidth(WindowManager.LayoutParams.MATCH_PARENT)
                .setHeight(WindowManager.LayoutParams.WRAP_CONTENT)
                .setGravity(Gravity.BOTTOM)
                .setDialogAnimationRes(R.style.ActionSheetDialogAnimation)
                .addOnClickListener(R.id.btn_commit)
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(BindViewHolder helper) {

                        if (!TextUtils.isEmpty(item.getGoodsMainImage())) {
                            Glide.with(context).load(ApiHelper.BASE_URL + item.getGoodsMainImage()).into((ImageView) helper.getView(R.id.icon_goods));
                        }
                        helper.setText(R.id.name, item.getGoodsName())
                                .setText(R.id.price, context.getResources().getString(R.string.goods_sale_price, item.getSalePrice()))
                                .setText(R.id.goods_stock_num, context.getResources().getString(R.string.goods_sum_stock, item.getGoodsSumStockNum()));
                    }
                })
                .setOnViewClickListener((customDialog, bindViewHolder, view) -> {
                    EditText sEditText = bindViewHolder.getView(R.id.system_stock_num);
                    EditText cEditText = bindViewHolder.getView(R.id.check_stock_num);
                    if (TextUtils.isEmpty(sEditText.getText()) || TextUtils.isEmpty(cEditText.getText())) {
                        ToastUtil.showToast(GoodsSelectListActivity.this, "请设置库存");
                    } else {
                        StoreCheckGoodsResponse store = new StoreCheckGoodsResponse(item.getGoodsID(), Integer.parseInt(sEditText.getText().toString()), Integer.parseInt(cEditText.getText().toString()));
                        int index = storeCheckGoodsResponseList.indexOf(store);
                        if (index > -1) {
                            storeCheckGoodsResponseList.remove(index);
                        } else {
                            goodsSelectedList.add(item);
                        }
                        storeCheckGoodsResponseList.add(store);
                        setGoodsSelected();
                        if (customDialog != null) {
                            customDialog.dismiss();
                        }
                    }

                }).create().show(getSupportFragmentManager(), getClass().getName());
    }


    private void setGoodsSelected() {
        int totalPrice = 0;
        goodsSize.setText("数量：" + goodsSelectedList.size());
        if (!ListUtil.isEmpty(goodsSelectedList)) {
            for (CommodityRequest store : goodsSelectedList) {
                totalPrice += Integer.parseInt(store.getCostPrice());
            }
        }
        goodsPrice.setText("总价：" + totalPrice);
    }

    @OnClick({R.id.search_container, R.id.select_btn, R.id.goods_size})
    public void viewClick(View v) {
        switch (v.getId()) {
            case R.id.search_container:

                break;
            case R.id.select_btn:
                if (ListUtil.isEmpty(storeCheckGoodsResponseList)) {
                    showToast("您还没有选择商品");
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("data", getData());
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
            case R.id.goods_size:
                if (selectGoodsContainer.getVisibility() == View.VISIBLE) {
                    selectGoodsContainer.setVisibility(View.GONE);
                } else {
                    selectGoodsContainer.setVisibility(View.VISIBLE);
                    goodsSelectedAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    private String getData() {
        StringBuffer stringBuffer = new StringBuffer();
        if (!ListUtil.isEmpty(storeCheckGoodsResponseList)) {
            for (StoreCheckGoodsResponse store : storeCheckGoodsResponseList) {
                stringBuffer.append(store.getGoodsID()).append(",")
                        .append(store.getSystemStockNum()).append(",")
                        .append(store.getCheckStockNum()).append(";");
            }
        }
        return stringBuffer.toString();
    }
}
