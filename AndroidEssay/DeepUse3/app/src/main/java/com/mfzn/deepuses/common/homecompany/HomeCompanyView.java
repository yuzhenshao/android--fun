package com.mfzn.deepuses.common.homecompany;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.company.CompanyRepository;

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
