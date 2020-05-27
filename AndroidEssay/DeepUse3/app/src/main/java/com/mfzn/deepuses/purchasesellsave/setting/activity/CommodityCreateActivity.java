package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.View;
import android.widget.EditText;

import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.request.CommodityRequest;
import com.mfzn.deepuses.bean.response.shop.ShopListResponse;
import com.mfzn.deepuses.common.PickerDialogView;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.GoodsShopAdapter;
import com.mfzn.deepuses.purchasesellsave.setting.view.AddImageView;
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
public class CommodityCreateActivity extends BasicActivity {

    @BindView(R.id.add_image_view)
    AddImageView mAddImageView;
    @BindView(R.id.goods_shop_recyleview)
    RecyclerView mRecyclerView;

    @BindView(R.id.goods_supplier)
    EditText supplierEdit;
    @BindView(R.id.goods_unit)
    EditText unitEdit;
    @BindView(R.id.category_et)
    EditText categoryEdit;

    @BindView(R.id.goods_name)
    EditText goodsName;
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

    private CommodityRequest mRequest;
    private String[] goodsPositions = {"中高端产品", "低端产品"};
    private ArrayMap<String, String> shopPriceMap = new ArrayMap<>();

    private static int CATEGORY_CODE = 100;
    private static int UNIT_CODE = 101;
    private static int SUPPLIER_CODE = 104;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }


    private void initView() {
        mTitleBar.updateTitleBar("商品录入");
        mRequest = new CommodityRequest();
        mAddImageView.init(this, "商品图片");
    }

    private void initData() {
        showDialog();
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mAddImageView.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CATEGORY_CODE) {
                mRequest.setGoodsCatID(data.getStringExtra("Id"));
                categoryEdit.setText(data.getStringExtra("Name"));
            } else if (requestCode == UNIT_CODE) {
                mRequest.setGoodsUnitID(data.getStringExtra("Id"));
                unitEdit.setText(data.getStringExtra("Name"));
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
                break;
            case R.id.unit_container:
                intent.setClass(CommodityCreateActivity.this, GoodsUnitListActivity.class);
                startActivityForResult(intent, UNIT_CODE);
                break;
            case R.id.supplier_container:
                intent.setClass(CommodityCreateActivity.this, SupplierListActivity.class);
                startActivityForResult(intent, SUPPLIER_CODE);
                break;
            case R.id.store_container:
                intent.setClass(CommodityCreateActivity.this, InventorySetActivity.class);
                startActivity(intent);
                break;
            case R.id.store_warning_set_container:
                intent.setClass(CommodityCreateActivity.this, InventoryWarnSetActivity.class);
                startActivity(intent);
                break;
            case R.id.position_container:
                PickerDialogView.showGoodSPosition(CommodityCreateActivity.this,
                        Arrays.asList(goodsPositions), (options1, options2, options3, v1)
                                -> goodsPosition.setText(goodsPositions[options1]));
                break;
            case R.id.btn_commit:
                initGoodsRequest();
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
                            }
                        });

                break;
        }
    }


    private void initGoodsRequest() {
        mRequest.setGoodsName(goodsName.getText().toString());
        mRequest.setGoodsNum(goodsNum.getText().toString());
        mRequest.setGoodsBarCode(goodsBarCode.getText().toString());
        mRequest.setGoodsBrand(goodsBrand.getText().toString());
        mRequest.setGoodsAttr(goodsAttr.getText().toString());
        mRequest.setGoodsPosition(goodsPosition.getText().toString());
        mRequest.setCostPrice(costPrice.getText().toString());
    }
}
