package com.mfzn.deepuses.purchasesellsave.capital.activity;

import android.support.v4.app.Fragment;

import com.libcommon.table.TabLabel;
import com.mfzn.deepuses.bean.response.capital.IncomeExpenseListResponse;
import com.mfzn.deepuses.purchasesellsave.capital.fragment.IncomeExpenseFragment;


public class IncomeExpenseListActivity extends BaseTabActivity{
    @Override
    protected void initTabLabelList() {
        mTitleBar.updateTitleBar("收支单据");
        mTabLabelList.add(new TabLabel("收入"));
        mTabLabelList.add(new TabLabel("支出"));
    }

    @Override
    protected BaseTabAdapter getAdapter() {
        return new BaseTabAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return IncomeExpenseFragment.newInstance(i==0? IncomeExpenseListResponse.INCOME: IncomeExpenseListResponse.EXPENSE);
            }
        };
    }
}