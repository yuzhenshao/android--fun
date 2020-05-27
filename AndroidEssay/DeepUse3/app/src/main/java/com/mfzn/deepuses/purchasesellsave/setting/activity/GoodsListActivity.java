package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.request.CommodityRequest;
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
public class GoodsListActivity extends BasicListActivity<CommodityRequest> {

    private String shopId;

    @BindView(R.id.search_container)
    LinearLayout searchContainer;
    @BindView(R.id.sum_stock)
    TextView sumStock;
    @BindView(R.id.serach_edit)
    EditText serachEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        shopId = getIntent().getStringExtra(ParameterConstant.SHOP_ID);
        super.onCreate(savedInstanceState);
        serachEdit.setHint("搜索产品名称、条码、货号");
        mTitleBar.updateTitleBar("商品中心", R.mipmap.icon_titlebar_add);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_list;
    }


    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.goodsList(shopId)
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
                CommodityRequest request = mSourceList.get(i);
                if (request != null) {
                    Intent intent = new Intent(GoodsListActivity.this, GoodsDetailActivity.class);
                    intent.putExtra(ParameterConstant.SHOP_ID, shopId);
                    intent.putExtra(ParameterConstant.GOODS_ID, request.getGoodsID());
                    startActivity(intent);
                }

            }
        });
        return mAdapter;
    }

    @OnClick(R.id.search_container)
    public void turnToSearch() {

    }

    @Override
    protected void rightPressedAction() {
        startActivity(new Intent(GoodsListActivity.this, CommodityPhotoCreateActivity.class));
//        View contentView = LayoutInflater.from(this).inflate(R.layout.goods_popupwindow, null, false);
//        TextView goodsPhotoEntry = contentView.findViewById(R.id.goods_photo_entry);
//        goodsPhotoEntry.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(GoodsListActivity.this, CommodityPhotoCreateActivity.class));
//            }
//        });
//        TextView goodsFormEntry = contentView.findViewById(R.id.goods_form_entry);
//        goodsFormEntry.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(GoodsListActivity.this, CommodityCreateActivity.class));
//            }
//        });
//
//        PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT, true);
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setAnimationStyle(R.style.popup_window_anim_style);
//        popupWindow.showAtLocation(mTitleBar, Gravity.TOP, 0,
//                mTitleBar.getHeight());
    }
}
