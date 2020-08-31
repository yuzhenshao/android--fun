package com.mfzn.deepuses.purchasesellsave.capital.activity;

import android.support.v4.app.Fragment;

import com.libcommon.table.TabLabel;
import com.mfzn.deepuses.bean.response.capital.IncomeExpenseListResponse;
import com.mfzn.deepuses.purchasesellsave.capital.fragment.BorrowFragment;
import com.mfzn.deepuses.purchasesellsave.capital.fragment.IncomeExpenseFragment;


public class BorrowListActivity extends BaseTabActivity {
    @Override
    protected void initTabLabelList() {
        mTitleBar.updateTitleBar("借入借出");
        mTabLabelList.add(new TabLabel("借入"));
        mTabLabelList.add(new TabLabel("借出"));
    }

    @Override
    protected BaseTabAdapter getAdapter() {
        return new BaseTabAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return BorrowFragment.newInstance(i+1);
            }
        };
    }
}