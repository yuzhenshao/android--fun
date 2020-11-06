package com.mfzn.deepuses.purchasesellsave.capital.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseFragment;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.capital.PayerPayeeDetailResponse.OrderListResponse;
import com.mfzn.deepuses.purchasesellsave.capital.activity.GatherePayActivity;
import com.mfzn.deepuses.purchasesellsave.capital.activity.GatherePayDetailActivity;
import com.mfzn.deepuses.purchasesellsave.capital.adapter.SalesLogAdapter;

import java.util.List;

import butterknife.BindView;

public class GatherePayOrderListFragment extends BaseFragment {
    private List<OrderListResponse> mOrderLogList;
    private int capitalType;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private SalesLogAdapter adapter;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        mOrderLogList = (List<OrderListResponse>) getArguments().getSerializable(ParameterConstant.SUPPLIER_ORDER_LIST);
        capitalType = getArguments().getInt(ParameterConstant.CAPITAL_TYPE);
        if (ListUtil.isEmpty(mOrderLogList)) {
            showNoDataView();
        } else {
            adapter = new SalesLogAdapter(mOrderLogList, capitalType);
            adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int i) {
                    if (view.getId() == R.id.pay_btn) {
                        Intent intent = new Intent(getActivity(), GatherePayActivity.class);
                        intent.putExtra(ParameterConstant.SUPPLIER_ORDER_LIST, mOrderLogList.get(i));
                        intent.putExtra(ParameterConstant.CAPITAL_TYPE, capitalType);
                        intent.putExtra(ParameterConstant.SUPPLIER, getArguments().getString(ParameterConstant.SUPPLIER));
                        getActivity().startActivityForResult(intent, GatherePayDetailActivity.REFRESH);
                    }
                }
            });
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_goods_sales;
    }

    public void refresh(List<OrderListResponse> list){
        mOrderLogList.clear();
        mOrderLogList.addAll(list);
        adapter.notifyDataSetChanged();
    }
}