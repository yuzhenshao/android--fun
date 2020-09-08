package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.settings.IncomeExpenseTypeResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.IncomeExpenseTypeSelectedAdapter;

import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * @author syz @date 2020-05-03
 */
public class IncomeExpenseTypeSelectedActivity extends BasicListActivity<IncomeExpenseTypeResponse> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("收支类别");
    }

    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.getIncomeExpenseTypeList(getIntent().getIntExtra(ParameterConstant.CAPITAL_TYPE, 0))
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<List<IncomeExpenseTypeResponse>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<List<IncomeExpenseTypeResponse>> reuslt) {
                        refreshSource(reuslt.getRes());
                    }
                });
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        IncomeExpenseTypeSelectedAdapter mAdapter = new IncomeExpenseTypeSelectedAdapter(mSourceList);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                IncomeExpenseTypeResponse response = mSourceList.get(i);
                Intent intent = new Intent();
                intent.putExtra("Id", response.getTypeID());
                intent.putExtra("Name", response.getTypeName());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        return mAdapter;
    }
}
