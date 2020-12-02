package com.mfzn.deepuses.purchasesellsave.setting.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseFragment;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.SupplierDetailResponse;
import com.mfzn.deepuses.purchasesellsave.capital.activity.GatherePayActivity;
import com.mfzn.deepuses.purchasesellsave.capital.activity.GatherePayDetailActivity;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.SupplierLogAdapter;


import java.util.List;

import butterknife.BindView;

public class SupplierOrderFragment extends BaseFragment {
    private List<SupplierDetailResponse.OrderListBean> mOrderLogList;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private SupplierLogAdapter adapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        mOrderLogList = (List<SupplierDetailResponse.OrderListBean>) getArguments().getSerializable(ParameterConstant.SUPPLIER_ORDER_LIST);
        if (ListUtil.isEmpty(mOrderLogList)) {
            showNoDataView();
        } else {
            adapter = new SupplierLogAdapter(mOrderLogList);
            adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int i) {
                    if (view.getId() == R.id.pay_btn) {
                        SupplierDetailResponse.OrderListBean orderResponse=mOrderLogList.get(i);

                        Intent intent = new Intent(getActivity(), GatherePayActivity.class);
                        intent.putExtra(ParameterConstant.ORDER_ID, orderResponse.getPayID());
                        intent.putExtra(ParameterConstant.ORDER_NUM, orderResponse.getOrderNum());
                        intent.putExtra(ParameterConstant.MONEY, orderResponse.getRealMoney());
                        intent.putExtra(ParameterConstant.HAS_DOWN_MONEY, orderResponse.getHasDoneMoney());
                        intent.putExtra(ParameterConstant.CAPITAL_TYPE, 1);
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

    public void refresh(List<SupplierDetailResponse.OrderListBean> list) {
        mOrderLogList.clear();
        mOrderLogList.addAll(list);
        adapter.notifyDataSetChanged();
    }
}
