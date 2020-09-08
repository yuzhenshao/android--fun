package com.mfzn.deepuses.purchasesellsave.capital.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mfzn.deepuses.bass.BasicListFragment;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.capital.IncomeExpenseListResponse;
import com.mfzn.deepuses.bean.response.capital.PayerPayeeListResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.capital.activity.IncomeExpenseDetailActivity;
import com.mfzn.deepuses.purchasesellsave.capital.adapter.IncomeExpenseAdapter;
import com.mfzn.deepuses.purchasesellsave.capital.adapter.ShouldGatherePayAdapter;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import io.reactivex.Flowable;

public class IncomeExpenseFragment extends BasicListFragment<IncomeExpenseListResponse.ListBean.IncomeExpenseResponse> {

    private int type;

    public static IncomeExpenseFragment newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt(ParameterConstant.CAPITAL_TYPE, type);
        IncomeExpenseFragment fragment = new IncomeExpenseFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void getResourceList() {
        showDialog();
        type = getArguments().getInt(ParameterConstant.CAPITAL_TYPE);
        ApiServiceManager.incomeExpenseList(type)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<IncomeExpenseListResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        hideDialog();
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<IncomeExpenseListResponse> reuslt) {
                        IncomeExpenseListResponse response = reuslt.getRes();
                        if (response != null && response.getList() != null) {
                            refreshSource(response.getList().getData());
                            return;
                        }
                        showNoDataView();
                    }
                });

    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        IncomeExpenseAdapter adapter=new IncomeExpenseAdapter(getActivity(), mSourceList);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                Intent intent=new Intent(getActivity(), IncomeExpenseDetailActivity.class);
                intent.putExtra(ParameterConstant.INCOME_EXPENSE_RESPONSE,mSourceList.get(i));
                startActivity(intent);
            }
        });


        return adapter;
    }
}
