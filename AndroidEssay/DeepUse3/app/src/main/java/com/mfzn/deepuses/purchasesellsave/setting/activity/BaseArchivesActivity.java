package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.titlebar.TitleBar;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.SettingModuleAdapter;
import com.mfzn.deepuses.purchasesellsave.setting.model.ModuleItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author syz @date 2020-03-24
 */
public class BaseArchivesActivity extends BaseActivity {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.titlebar)
    TitleBar mTitleBar;

    private SettingModuleAdapter mAdapter;
    private List<ModuleItem> mSourceList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    private void initData() {
        mTitleBar.updateTitleBar("基础档案");
        mSourceList = new ArrayList<>();
        mSourceList.add(new ModuleItem("商品", "创建商品数据", R.mipmap.icon_commodity, CommodityCreateActivity.class));
        mSourceList.add(new ModuleItem("商品分类", "创建商品分类数据", R.mipmap.icon_categories, BaseArchivesActivity.class));
        mSourceList.add(new ModuleItem("商品单位", "创建商品常用单位（如：件，台，个等）", R.mipmap.icon_commodity_unit, BaseArchivesActivity.class));
        mSourceList.add(new ModuleItem("仓库", "创建仓库名称，负责人，地址等", R.mipmap.icon_warehouse, BaseArchivesActivity.class));
        mSourceList.add(new ModuleItem("供应商", "商品的进货供应商名称，负责人，电话，地址等", R.mipmap.icon_supplier, BaseArchivesActivity.class));
        mSourceList.add(new ModuleItem("客户", "创建名称，负责人，电话，地址等", R.mipmap.icon_client, BaseArchivesActivity.class));
        mSourceList.add(new ModuleItem("其他费用", "创建单据时除了商品费用外的其他费用，如安装费", R.mipmap.icon_cost, BaseArchivesActivity.class));
        mSourceList.add(new ModuleItem("收支类别", "财务记账时的其他收入与支出类别，如租金，水电", R.mipmap.icon_income_categories, BaseArchivesActivity.class));

    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SettingModuleAdapter(mSourceList);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ModuleItem moduleItem = mSourceList.get(position);
                startActivity(new Intent(BaseArchivesActivity.this, moduleItem.getTurnToActivity()));
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_base_list;
    }
}
