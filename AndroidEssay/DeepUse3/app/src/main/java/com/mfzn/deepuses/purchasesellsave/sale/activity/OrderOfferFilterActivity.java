package com.mfzn.deepuses.purchasesellsave.sale.activity;

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

public class OrderOfferFilterActivity extends BasicActivity {

    @BindView(R.id.recyleview)
    MyRecyclerView isCheckRecyleview;

    private FilterModule curFilterModule;

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
        NoScrollGridLayoutManager dlLayoutManager = new NoScrollGridLayoutManager(this,
                3, GridLayoutManager.VERTICAL, false);
        isCheckRecyleview.setLayoutManager(dlLayoutManager);
        initData();
    }

    private void initData() {
        List<FilterModule> filterModuleList = new ArrayList<>();
        filterModuleList.add(new FilterModule("", "全部"));
        filterModuleList.add(new FilterModule("0", "待审核"));
        filterModuleList.add(new FilterModule("1", "通过"));
        filterModuleList.add(new FilterModule("2", "拒绝"));

        FilterAdapter mAdapter = new FilterAdapter(filterModuleList);
        isCheckRecyleview.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                curFilterModule = filterModuleList.get(i);
                mAdapter.setCurFilterModule(curFilterModule.getTypeId());
                adapter.notifyDataSetChanged();
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
                intent.putExtra(ParameterConstant.IS_SELECTED, curFilterModule.getTypeId());
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
        }
    }
}
