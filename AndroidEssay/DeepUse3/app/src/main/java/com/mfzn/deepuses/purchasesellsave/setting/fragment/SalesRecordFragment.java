package com.mfzn.deepuses.purchasesellsave.setting.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseFragment;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.settings.SalesRecordResponse;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.SalesRecordAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * @author yz @date 2020-05-04
 */
public class SalesRecordFragment extends BaseFragment {

    private List<SalesRecordResponse> mSalesRecordList;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        mSalesRecordList = (List<SalesRecordResponse>) getArguments().getSerializable(ParameterConstant.BASIC_ATTR);
        if (ListUtil.isEmpty(mSalesRecordList)) {
            showNoDataView();
        } else {
            SalesRecordAdapter adapter = new SalesRecordAdapter(mSalesRecordList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_goods_sales;
    }
}
