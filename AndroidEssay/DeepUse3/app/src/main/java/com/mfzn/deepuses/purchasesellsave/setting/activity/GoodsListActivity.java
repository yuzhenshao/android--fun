package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
        super.onCreate(savedInstanceState);
        serachEdit.setHint("搜索客户");
        mTitleBar.updateTitleBar("商品中心", R.mipmap.icon_titlebar_add);
        shopId = getIntent().getStringExtra(ParameterConstant.SHOP_ID);
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
                            sumStock.setText(goodsListResponse.getSumStockNum());
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
        return mAdapter;
    }

    @OnClick(R.id.search_container)
    public void turnToSearch() {

    }

    protected void rightPressed() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.goods_popupwindow, null, false);
        TextView goodsPhotoEntry = contentView.findViewById(R.id.goods_photo_entry);
        goodsPhotoEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GoodsListActivity.this, CommodityPhotoCreateActivity.class));
            }
        });
        TextView goodsFormEntry = contentView.findViewById(R.id.goods_form_entry);
        goodsFormEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GoodsListActivity.this, CommodityCreateActivity.class));
            }
        });

        PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.popup_window_anim_style);
        popupWindow.showAtLocation(mTitleBar, Gravity.TOP, 0,
                mTitleBar.getHeight());
    }
}
