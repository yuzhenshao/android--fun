package com.mfzn.deepuses.purchasesellsave.capital.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.capital.BorrowInfoResponse;
import com.mfzn.deepuses.purchasesellsave.capital.adapter.BorrowDetailLogAdapter;

import java.util.ArrayList;
import java.util.List;

public class HandleLogListActivity extends BasicListActivity<BorrowInfoResponse.HandleLogResponse> {

    private List<BorrowInfoResponse.HandleLogResponse> mHandleLogResponseList=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("结算历史");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_handle_list_view;
    }


    @Override
    protected void getResourceList() {
        List<BorrowInfoResponse.HandleLogResponse> logList=(List<BorrowInfoResponse.HandleLogResponse>)getIntent().getSerializableExtra(ParameterConstant.HANDLE_LOGS);
        if(ListUtil.isEmpty(logList)){
            showNoDataView();
        }else{
            mHandleLogResponseList.clear();
            mHandleLogResponseList.addAll(logList);
            refreshSource(mHandleLogResponseList);
        }
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new BorrowDetailLogAdapter(mHandleLogResponseList);
    }
}
