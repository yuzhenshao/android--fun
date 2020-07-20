package com.mfzn.deepuses.purchasesellsave.setting.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseFragment;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.settings.CustomerDetailResponse;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.CustomerPlayLogAdapter;

import java.util.List;

import butterknife.BindView;

public class CustomerPayLogFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private List<CustomerDetailResponse.PayLogListBean> mPayListBeanList;

    private CustomerPlayLogAdapter mAdapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        mPayListBeanList = (List<CustomerDetailResponse.PayLogListBean>) getArguments().getSerializable(ParameterConstant.SUPPLIER_PAY_LOG_LIST);
        if (ListUtil.isEmpty(mPayListBeanList)) {
            showNoDataView();
        } else {
            mAdapter = new CustomerPlayLogAdapter(mPayListBeanList);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
    }

    protected int getLayoutId() {
        return R.layout.fragment_customer_pay_log_list;
    }
}
