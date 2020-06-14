package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.request.CommodityRequest;
import com.mfzn.deepuses.bean.response.settings.GoodsDetailResponse;
import com.mfzn.deepuses.bean.response.shop.ShopListResponse;
import com.mfzn.deepuses.common.PickerDialogView;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsShopAdapter;
import com.mfzn.deepuses.purchasesellsave.setting.manager.StoreWarnManager;
import com.mfzn.deepuses.purchasesellsave.setting.view.AddImageView;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.ToastUtil;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * @author yz @date 2020-03-26
 */
public class CommodityCreateActivity extends CommodityPhotoCreateActivity {


    @BindView(R.id.goods_shop_recyleview)
    RecyclerView mRecyclerView;

    @BindView(R.id.goods_supplier)
    EditText supplierEdit;
    @BindView(R.id.category_et)
    EditText categoryEdit;
    @BindView(R.id.goods_num)
    EditText goodsNum;
    @BindView(R.id.goods_bar_code)
    EditText goodsBarCode;
    @BindView(R.id.goods_brand)
    EditText goodsBrand;
    @BindView(R.id.goods_attr)
    EditText goodsAttr;
    @BindView(R.id.goods_position)
    EditText goodsPosition;
    @BindView(R.id.cost_price)
    EditText costPrice;
    @BindView(R.id.sale_price)
    EditText salePrice;
    @BindView(R.id.store_warning_set_container)
    TextView storeWarnSet;

    private ArrayMap<String, String> shopPriceMap = new ArrayMap<>();
    private String[] goodsPositions = {"高端产品", "中低端产品"};
    private static int CATEGORY_CODE = 100;
    private static int SUPPLIER_CODE = 104;
    private GoodsDetailResponse.GoodsInfoResponse mGoodsInfoResponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        showDialog();
        mGoodsInfoResponse = (GoodsDetailResponse.GoodsInfoResponse) getIntent().getSerializableExtra(Constants.GOODS);
        mTitleBar.updateTitleBar(mGoodsInfoResponse == null ? "商品录入" : "编辑商品");
        if (mGoodsInfoResponse != null) {
            goodsName.setText(mGoodsInfoResponse.getGoodsName());
            goodsNum.setText(mGoodsInfoResponse.getGoodsNum());
            goodsBarCode.setText(mGoodsInfoResponse.getGoodsBarCode());
            goodsBrand.setText(mGoodsInfoResponse.getGoodsBrand());
            categoryEdit.setText(mGoodsInfoResponse.getCatName());
            goodsAttr.setText(mGoodsInfoResponse.getGoodsAttr());
            unitEdit.setText(mGoodsInfoResponse.getUnitName());
            goodsPosition.setText(mGoodsInfoResponse.getPositionName());
            costPrice.setText(mGoodsInfoResponse.getCostPrice());
            salePrice.setText(mGoodsInfoResponse.getSalePrice());

            List<GoodsDetailResponse.GoodsInfoResponse.SuppliersBean> suppliersBeans = mGoodsInfoResponse.getSuppliers();
            if (!ListUtil.isEmpty(suppliersBeans)) {
                mRequest.setSupplierIDs(suppliersBeans.get(0).getSupplierID());
                supplierEdit.setText(suppliersBeans.get(0).getSupplierName());
            }
            mRequest.setGoodsID(mGoodsInfoResponse.getGoodsID());
            mRequest.setGoodsPosition(mGoodsInfoResponse.getGoodsPosition() + "");
            mRequest.setGoodsCatID(mGoodsInfoResponse.getGoodsCatID());
            mRequest.setGoodsUnitID(mGoodsInfoResponse.getGoodsUnitID());
            mRequest.setStoreStockNum(mGoodsInfoResponse.getGoodsSumStockNum() + "");
        }
        ApiServiceManager.getShopList()
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<ShopListResponse>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        hideDialog();
                    }

                    @Override
                    public void onNext(HttpResult<List<ShopListResponse>> result) {
                        hideDialog();
                        if (result != null && !ListUtil.isEmpty(result.getRes())) {
                            mRecyclerView.setVisibility(View.VISIBLE);
                            GoodsShopAdapter adapter = new GoodsShopAdapter(CommodityCreateActivity.this, result.getRes());
                            adapter.setShopPriceListener((item, price) -> shopPriceMap.put(item.getShopID(), price));
                            mRecyclerView.setLayoutManager(new LinearLayoutManager(CommodityCreateActivity.this));
                            mRecyclerView.setAdapter(adapter);
                        } else {
                            mRecyclerView.setVisibility(View.GONE);
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        storeWarnSet.setText(ListUtil.isEmpty(StoreWarnManager.getInstance().getStoreWarnModels()) ? "去设置" : "已设置");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CATEGORY_CODE) {
                mRequest.setGoodsCatID(data.getStringExtra("Id"));
                categoryEdit.setText(data.getStringExtra("Name"));
            } else if (requestCode == SUPPLIER_CODE) {
                mRequest.setSupplierIDs(data.getStringExtra("Id"));
                supplierEdit.setText(data.getStringExtra("Name"));
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_commodity_create;
    }

    @OnClick({R.id.categoty_contaienr, R.id.unit_container, R.id.supplier_container, R.id.store_container,
            R.id.store_warning_set_container, R.id.position_container, R.id.btn_commit})
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.categoty_contaienr:
                intent.setClass(CommodityCreateActivity.this, GoodsCategoryActivity.class);
                startActivityForResult(intent, CATEGORY_CODE);
                return;
            case R.id.supplier_container:
                intent.setClass(CommodityCreateActivity.this, SupplierListActivity.class);
                startActivityForResult(intent, SUPPLIER_CODE);
                return;
            case R.id.store_warning_set_container:
                intent.setClass(CommodityCreateActivity.this, InventoryWarnSetActivity.class);
                startActivity(intent);
                return;
            case R.id.position_container:
                PickerDialogView.showGoodSPosition(CommodityCreateActivity.this,
                        Arrays.asList(goodsPositions), new OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int options2, int options3, View v1) {
                                goodsPosition.setText(goodsPositions[options1]);
                                mRequest.setGoodsPosition(options1 + 1 + "");
                            }
                        });
                break;
        }
        super.onClick(v);
    }

    @Override
    protected void initGoodsRequest() {
        mRequest.setGoodsName(goodsName.getText().toString());
        mRequest.setGoodsNum(goodsNum.getText().toString());
        mRequest.setGoodsBarCode(goodsBarCode.getText().toString());
        mRequest.setGoodsBrand(goodsBrand.getText().toString());
        mRequest.setCatName(categoryEdit.getText().toString());
        mRequest.setGoodsAttr(goodsAttr.getText().toString());
        mRequest.setUnitName(unitEdit.getText().toString());

        mRequest.setCostPrice(costPrice.getText().toString());
        mRequest.setSalePrice(salePrice.getText().toString());
        if (shopPriceMap != null && shopPriceMap.size() > 0) {
            StringBuilder otherShopWaringPrice = new StringBuilder();
            for (String key : shopPriceMap.keySet()) {
                otherShopWaringPrice.append(key).append("-").append(shopPriceMap.get(key)).append(";");
            }
            String wringPrice = otherShopWaringPrice.toString();
            if (!TextUtils.isEmpty(wringPrice)) {
                mRequest.setOtherShopWaringPrice(wringPrice.substring(0, wringPrice.length() - 1));
            }
        }
        if (!ListUtil.isEmpty(StoreWarnManager.getInstance().getStoreModels())) {
            mRequest.setStoreStockNum(StoreWarnManager.getInstance().getStoreStockNum());
        }
        if (!ListUtil.isEmpty(StoreWarnManager.getInstance().getStoreWarnModels())) {
            mRequest.setStoreWarningStockNum(StoreWarnManager.getInstance().getStoreWarningStockNum());
        }
    }

    protected void submit() {
        if (mGoodsInfoResponse == null) {
            ApiServiceManager.addGoods(mRequest)
                    .compose(XApi.getApiTransformer())
                    .compose(XApi.getScheduler())
                    .compose(bindToLifecycle())
                    .subscribe(new ApiSubscriber<HttpResult>() {
                        @Override
                        protected void onFail(NetError error) {
                            ToastUtil.showToast(CommodityCreateActivity.this, error.getMessage());
                        }

                        @Override
                        public void onNext(HttpResult reuslt) {
                            ToastUtil.showToast(CommodityCreateActivity.this, "成功");
                            finish();
                        }
                    });
        } else {
            ApiServiceManager.editGoods(mRequest)
                    .compose(XApi.getApiTransformer())
                    .compose(XApi.getScheduler())
                    .compose(bindToLifecycle())
                    .subscribe(new ApiSubscriber<HttpResult>() {
                        @Override
                        protected void onFail(NetError error) {
                            ToastUtil.showToast(CommodityCreateActivity.this, error.getMessage());
                        }

                        @Override
                        public void onNext(HttpResult reuslt) {
                            ToastUtil.showToast(CommodityCreateActivity.this, "成功");
                            setResult(Activity.RESULT_OK);
                            finish();
                        }
                    });
        }
    }
}
