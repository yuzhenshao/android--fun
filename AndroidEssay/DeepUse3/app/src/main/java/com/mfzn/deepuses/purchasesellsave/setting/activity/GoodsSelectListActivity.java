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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.dialog.fragment.CustomDialog;
import com.libcommon.dialog.listener.OnBindViewListener;
import com.libcommon.dialog.view.BindViewHolder;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.settings.GoodsInfoResponse;
import com.mfzn.deepuses.bean.response.settings.GoodsListResponse;
import com.mfzn.deepuses.bean.response.settings.RateResponse;
import com.mfzn.deepuses.bean.response.store.StoreCheckGoodsResponse;
import com.mfzn.deepuses.common.PickerDialogView;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsAdapter;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsSelectedAdapter;
import com.mfzn.deepuses.utils.ToastUtil;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class GoodsSelectListActivity extends BasicListActivity<GoodsInfoResponse> {

    @BindView(R.id.search_container)
    LinearLayout searchContainer;
    @BindView(R.id.sum_stock)
    TextView sumStock;
    @BindView(R.id.select_goods_recycler_view)
    RecyclerView goodsRecyclerView;
    @BindView(R.id.select_goods_container)
    RelativeLayout selectGoodsContainer;
    @BindView(R.id.goods_size)
    TextView goodsSize;
    @BindView(R.id.goods_price)
    TextView goodsPrice;
    @BindView(R.id.select_container)
    RelativeLayout selectContainer;

    private List<GoodsInfoResponse> goodsSelectedList = new ArrayList<>();
    private GoodsSelectedAdapter goodsSelectedAdapter;
    private List<RateResponse> mRateResponseList = new ArrayList<>();
    private boolean isNumberSet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("商品中心");
        isNumberSet = getIntent().getBooleanExtra(ParameterConstant.COST_TYPE_NUMBER_SET, false);
        goodsSelectedAdapter = new GoodsSelectedAdapter(this, goodsSelectedList);
        goodsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        goodsRecyclerView.setAdapter(goodsSelectedAdapter);
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
        ApiServiceManager.goodsList(getIntent().getIntExtra(ParameterConstant.IS_PERSONAL_STORE_GOODS, 0))
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
                if (isNumberSet) {
                    showNumberSetDialog(mSourceList.get(i));
                } else {
                    showGoodsStoreSetDialog(mSourceList.get(i));
                }
            }
        });
        return mAdapter;
    }

    private void showNumberSetDialog(GoodsInfoResponse item) {
        new CustomDialog.Builder().setLayoutRes(R.layout.dialog_goods_store_set)
                .setWidth(WindowManager.LayoutParams.MATCH_PARENT)
                .setHeight(WindowManager.LayoutParams.WRAP_CONTENT)
                .setGravity(Gravity.BOTTOM)
                .setDialogAnimationRes(R.style.ActionSheetDialogAnimation)
                .addOnClickListener(R.id.btn_commit)
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(BindViewHolder helper) {//无线路由器（WXLYQ221）
                        if (!TextUtils.isEmpty(item.getGoodsMainImage())) {
                            Glide.with(context).load(ApiHelper.BASE_URL + item.getGoodsMainImage()).into((ImageView) helper.getView(R.id.goods_image));
                        }
                        helper.setText(R.id.name, item.getGoodsName() + "（" + item.getGoodsNum() + ")")
                                .setText(R.id.goods_stock_num, context.getResources().getString(R.string.goods_sum_stock, item.getGoodsSumStockNum()))
                                .setText(R.id.system_stock_num, item.getGoodsSumStockNum());
                    }
                })
                .setOnViewClickListener((customDialog, bindViewHolder, view) -> {

                    switch (view.getId()) {
                        case R.id.btn_commit:
                            TextView systemStockNum = bindViewHolder.getView(R.id.system_stock_num);
                            TextView checkStockNum = bindViewHolder.getView(R.id.check_stock_num);
                            if (TextUtils.isEmpty(systemStockNum.getText())) {
                                showToast("请输入系统库存");
                                return;
                            }
                            if (TextUtils.isEmpty(checkStockNum.getText())) {
                                showToast("请输入盘点库存");
                                return;
                            }
                            item.setSystemStockNum(Integer.parseInt(systemStockNum.getText().toString()));
                            item.setCheckStockNum(Integer.parseInt(checkStockNum.getText().toString()));
                            int index = goodsSelectedList.indexOf(item);
                            if (index != -1) {
                                goodsSelectedList.remove(index);
                            }
                            if (item.getGoodsCount() > 0) {
                                goodsSelectedList.add(item);
                            }
                            setGoodsSelected();
                            if (customDialog != null) {
                                customDialog.dismiss();
                            }
                            break;
                    }

                }).create().show(getSupportFragmentManager(), getClass().getName());
    }

    private void showGoodsStoreSetDialog(GoodsInfoResponse item) {
        new CustomDialog.Builder().setLayoutRes(R.layout.dialog_goods_price)
                .setWidth(WindowManager.LayoutParams.MATCH_PARENT)
                .setHeight(WindowManager.LayoutParams.WRAP_CONTENT)
                .setGravity(Gravity.BOTTOM)
                .setDialogAnimationRes(R.style.ActionSheetDialogAnimation)
                .addOnClickListener(R.id.switch_button, R.id.tax_rate_select, R.id.btn_commit, R.id.plus, R.id.subtraction)
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(BindViewHolder helper) {
                        if (!TextUtils.isEmpty(item.getGoodsMainImage())) {
                            Glide.with(context).load(ApiHelper.BASE_URL + item.getGoodsMainImage()).into((ImageView) helper.getView(R.id.goods_image));
                        }
                        helper.setText(R.id.name, item.getGoodsName() + "  [" + item.getGoodsNum() + "]")
                                .setText(R.id.goods_stock_num, context.getResources().getString(R.string.goods_sum_stock, item.getGoodsSumStockNum()))
                                .setText(R.id.cost_price, item.getSalePrice())
                                .setText(R.id.tax_rate_price, item.getSalePrice())
                                .setText(R.id.number, 1 + "")
                                .setChecked(R.id.switch_button, item.getTaxRate() != 0);
                    }
                })
                .setOnViewClickListener((customDialog, bindViewHolder, view) -> {

                    switch (view.getId()) {
                        case R.id.plus:
                        case R.id.subtraction:
                            TextView numberView = bindViewHolder.getView(R.id.number);
                            int value = Integer.valueOf(numberView.getText().toString());
                            if (view.getId() == R.id.plus) {
                                if (value < item.getGoodsSumStockNum()) {
                                    value += 1;
                                }
                            } else {
                                if (value > 0) {
                                    value += -1;
                                }
                            }
                            numberView.setText(value + "");
                            item.setGoodsCount(value);
                            break;
                        case R.id.switch_button:
                            item.setHasTaxRate(!item.isHasTaxRate());
                            bindViewHolder.setChecked(R.id.switch_button, item.isHasTaxRate());
                            break;
                        case R.id.tax_rate_select:
                            if (item.isHasTaxRate()) {
                                showTaxDialog(item, bindViewHolder);
                            }
                            break;
                        case R.id.btn_commit:
                            int index = goodsSelectedList.indexOf(item);
                            if (index != -1) {
                                goodsSelectedList.remove(index);
                            }
                            if (item.getGoodsCount() > 0) {
                                goodsSelectedList.add(item);
                            }
                            setGoodsSelected();
                            if (customDialog != null) {
                                customDialog.dismiss();
                            }
                            break;
                    }

                }).create().show(getSupportFragmentManager(), getClass().getName());
    }

    private void showTaxDialog(GoodsInfoResponse item, BindViewHolder bindViewHolder) {
        ApiServiceManager.getTaxRateList()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<RateResponse>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showToast(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<List<RateResponse>> reuslt) {
                        mRateResponseList = reuslt.getRes();
                        if (ListUtil.isEmpty(mRateResponseList)) {
                            showToast("没有相关税率");
                        } else {
                            List<String> rateList = new ArrayList<>();
                            for (RateResponse rateResponse : mRateResponseList) {
                                rateList.add(rateResponse.getRate());
                            }
                            PickerDialogView.showGoodSPosition(GoodsSelectListActivity.this,
                                    rateList, new OnOptionsSelectListener() {
                                        @Override
                                        public void onOptionsSelect(int options1, int options2, int options3, View v1) {
                                            double rate = getPrice(rateList.get(options1)) / 100;
                                            double price = (1 + rate) * getPrice(item.getSalePrice());
                                            ((TextView) bindViewHolder.getView(R.id.tax_rate)).setText(rate + "");
                                            ((TextView) bindViewHolder.getView(R.id.tax_rate_price)).setText(price + "");
                                            item.setTaxRate(rate);
                                            item.setSalePriceWithTax(price + "");
                                        }
                                    });
                        }
                    }
                });


    }

    private void setGoodsSelected() {
        selectContainer.setVisibility(View.VISIBLE);
        if (isNumberSet) {
            goodsPrice.setVisibility(View.GONE);
            goodsSize.setText("数量：" + goodsSelectedList.size());
        } else {
            int totalPrice = 0;
            int size = 0;
            if (!ListUtil.isEmpty(goodsSelectedList)) {
                for (GoodsInfoResponse store : goodsSelectedList) {
                    size += store.getGoodsCount();
                    if (store.isHasTaxRate()) {
                        totalPrice += getPrice(store.getSalePriceWithTax()) * store.getGoodsCount();
                    } else {
                        totalPrice += getPrice(store.getSalePrice()) * store.getGoodsCount();
                    }
                }
            }
            goodsSize.setText("数量：" + size);
            goodsPrice.setText("总价：" + totalPrice);
        }
    }

    private double getPrice(String salePrice) {
        try {
            if (!TextUtils.isEmpty(salePrice)) {
                return Double.parseDouble(salePrice);
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    @OnClick({R.id.select_goods_container, R.id.select_btn, R.id.goods_size})
    public void viewClick(View v) {
        switch (v.getId()) {
            case R.id.select_goods_container:
                selectGoodsContainer.setVisibility(View.GONE);
                break;
            case R.id.select_btn:
                if (ListUtil.isEmpty(goodsSelectedList)) {
                    showToast("您还没有选择商品");
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("data", (Serializable) goodsSelectedList);
                    intent.putExtra("totalPrice", goodsPrice.getText());
                    intent.putExtra("goodsSize", goodsSize.getText());
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


//    /*
//     * goodsID1（商品ID）,goodsNum1（商品数量）,uniformSalePrice1（零售价）
//     * ,$salePrice1（销售单价不含税）,$taxRate1（税率）,$salePriceWithTax1（销售价含税）,
//     * $money1（金额：数量*销售单价）;*/
//    private String getData() {
//        StringBuffer stringBuffer = new StringBuffer();
//        if (!ListUtil.isEmpty(goodsSelectedList)) {
//            for (GoodsInfoResponse goods : goodsSelectedList) {
//                stringBuffer.append(goods.getGoodsID()).append(",")
//                        .append(goods.getGoodsCount()).append(",")
//                        .append(goods.getSalePrice()).append(",")
//                        .append(goods.getSalePrice()).append(",")
//                        .append(goods.getTaxRate()).append(",")
//                        .append(goods.getSalePriceWithTax()).append(",")
//                        .append(goods.getGoodsCount() * getPrice(goods.getSalePrice())).append(";");
//            }
//        }
//        return stringBuffer.toString();
//    }
}
