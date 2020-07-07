package com.mfzn.deepuses.purchasesellsave.setting.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseFragment;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.settings.SupplierCustomerInfoResponse;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.SupplierOrderAdapter;

import java.util.List;

import butterknife.BindView;

public class SupplierOrderFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private List<SupplierCustomerInfoResponse.OrderListBean> mOrderListBeanList;

    private SupplierOrderAdapter mAdapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        mOrderListBeanList = (List<SupplierCustomerInfoResponse.OrderListBean>) getArguments().getSerializable(ParameterConstant.SUPPLIER_ORDER_LIST);
        if (ListUtil.isEmpty(mOrderListBeanList)) {
            showNoDataView();
        } else {
            mAdapter = new SupplierOrderAdapter(mOrderListBeanList);
        }
    }

    protected int getLayoutId() {
        return R.layout.fragment_order_list;
    }
}
