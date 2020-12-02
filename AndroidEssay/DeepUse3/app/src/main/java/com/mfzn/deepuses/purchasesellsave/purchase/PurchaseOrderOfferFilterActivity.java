package com.mfzn.deepuses.purchasesellsave.purchase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.WindowManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.purchasesellsave.sale.Module.FilterModule;
import com.mfzn.deepuses.purchasesellsave.sale.adapter.FilterAdapter;
import com.mfzn.deepuses.view.MyRecyclerView;
import com.mfzn.deepuses.view.NoScrollGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PurchaseOrderOfferFilterActivity extends BasicActivity {

    @BindView(R.id.recyleview)
    MyRecyclerView isCheckRecyleview;
    @BindView(R.id.order_type_recyleview)
    MyRecyclerView orderTypeRecyleview;
    @BindView(R.id.cancel_recyleview)
    MyRecyclerView cancelRecyleview;

    private int isCheck;
    private int isCanceled;
    private int orderType;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_offer_filter;
    }

    protected void initTitleBar() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        isCheckRecyleview.setLayoutManager(new NoScrollGridLayoutManager(this,
                3, GridLayoutManager.VERTICAL, false));
        orderTypeRecyleview.setLayoutManager(new NoScrollGridLayoutManager(this,
                3, GridLayoutManager.VERTICAL, false));
        cancelRecyleview.setLayoutManager(new NoScrollGridLayoutManager(this,
                3, GridLayoutManager.VERTICAL, false));
        initData();
    }

    private void initData() {
        isCheck=getIntent().getIntExtra(ParameterConstant.ORDER_CHECK,-1);
        isCanceled=getIntent().getIntExtra(ParameterConstant.ORDER_CACNEL,0);
        orderType=getIntent().getIntExtra(ParameterConstant.ORDER_TYPE,1);
        List<FilterModule> orderTypeFilterModules = new ArrayList<>();
        orderTypeFilterModules.add(new FilterModule(1, "采购单"));
        orderTypeFilterModules.add(new FilterModule(2, "采购退货单"));
        FilterAdapter orderTypeAdapter = new FilterAdapter(orderTypeFilterModules);
        orderTypeAdapter.setCurFilterModule(orderType);
        orderTypeRecyleview.setAdapter(orderTypeAdapter);
        orderTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                orderType = orderTypeFilterModules.get(i).getTypeId();
                orderTypeAdapter.setCurFilterModule(orderTypeFilterModules.get(i).getTypeId());
                orderTypeAdapter.notifyDataSetChanged();
            }
        });


        List<FilterModule> filterModuleList = new ArrayList<>();
        filterModuleList.add(new FilterModule(-1, "全部"));
        filterModuleList.add(new FilterModule(0, "待审核"));
        filterModuleList.add(new FilterModule(1, "通过"));
        filterModuleList.add(new FilterModule(2, "拒绝"));

        FilterAdapter mAdapter = new FilterAdapter(filterModuleList);
        mAdapter.setCurFilterModule(isCheck);
        isCheckRecyleview.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                isCheck = filterModuleList.get(i).getTypeId();
                mAdapter.setCurFilterModule(filterModuleList.get(i).getTypeId());
                adapter.notifyDataSetChanged();
            }
        });

        List<FilterModule> cancelFilterModules = new ArrayList<>();
       cancelFilterModules.add(new FilterModule(0, "未作废"));
       cancelFilterModules.add(new FilterModule(1, "作废中"));
       cancelFilterModules.add(new FilterModule(2, "已作废"));
        FilterAdapter cancelAdapter = new FilterAdapter(cancelFilterModules);
        cancelAdapter.setCurFilterModule(isCanceled);
        cancelRecyleview.setAdapter(cancelAdapter);
        cancelAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                isCanceled = cancelFilterModules.get(i).getTypeId();
                cancelAdapter.setCurFilterModule(cancelFilterModules.get(i).getTypeId());
                cancelAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick({R.id.delete_btn, R.id.confirm_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.delete_btn:
                finish();
                break;
            case R.id.confirm_btn:
                Intent intent = new Intent();
                intent.putExtra(ParameterConstant.ORDER_TYPE, orderType);
                intent.putExtra(ParameterConstant.ORDER_CACNEL, isCanceled);
                intent.putExtra(ParameterConstant.ORDER_CHECK, isCheck);
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
        }
    }
}
