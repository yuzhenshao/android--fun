package com.mfzn.deepuses.common.homecompany;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.home.HomeListAdapter;
import com.mfzn.deepuses.model.company.CompanyRepository;
import com.mfzn.deepuses.model.company.SelectCompanyModel;
import com.mfzn.deepuses.purchasesellsave.setting.activity.BaseArchivesActivity;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.SettingModuleAdapter;
import com.mfzn.deepuses.purchasesellsave.setting.model.ModuleItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeCompanyView extends LinearLayout {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private HomeCompanyAdapter mAdapter;
    private HomeCompanyAdapter.CompanyShopListener mListener;
    private Context context;

    public HomeCompanyView(Context context,HomeCompanyAdapter.CompanyShopListener mListener) {
        super(context);
        this.context = context;
        this.mListener=mListener;
        LayoutInflater.from(context).inflate(R.layout.home_company_view, this);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new HomeCompanyAdapter(context, CompanyRepository.getInstance().getCompanyModels());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setListener(mListener);
    }

    public HomeCompanyAdapter.CompanyShopListener getListener() {
        return mListener;
    }

    public void setListener(HomeCompanyAdapter.CompanyShopListener listener) {
        mListener = listener;
    }

}
