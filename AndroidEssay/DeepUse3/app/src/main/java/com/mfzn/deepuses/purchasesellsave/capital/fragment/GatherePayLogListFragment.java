package com.mfzn.deepuses.purchasesellsave.capital.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseFragment;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.capital.PayerPayeeDetailResponse.PayLogResponse;
import com.mfzn.deepuses.purchasesellsave.capital.adapter.SalesPayAdapter;

import java.util.List;

import butterknife.BindView;

public class GatherePayLogListFragment extends BaseFragment {
    private List<PayLogResponse> mOrderLogList;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private  SalesPayAdapter adapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        mOrderLogList = (List<PayLogResponse>) getArguments().getSerializable(ParameterConstant.SUPPLIER_PAY_LOG_LIST);
        if (ListUtil.isEmpty(mOrderLogList)) {
            showNoDataView();
        } else {
            adapter = new SalesPayAdapter(mOrderLogList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_goods_sales;
    }

    public void refresh(List<PayLogResponse> list) {
        mOrderLogList.clear();
        mOrderLogList.addAll(list);
        adapter.notifyDataSetChanged();
    }
}