package com.mfzn.deepuses.purchasesellsave.capital.activity;

import android.support.v4.app.Fragment;

import com.libcommon.table.TabLabel;
import com.mfzn.deepuses.purchasesellsave.capital.fragment.ShouldGatherePayFragment;

public class ShouldGatherePayActivity extends BaseTabActivity{
    @Override
    protected void initTabLabelList() {
        mTitleBar.updateTitleBar("应收应付");
        mTabLabelList.add(new TabLabel("应收"));
        mTabLabelList.add(new TabLabel("应付"));
    }

    @Override
    protected BaseTabAdapter getAdapter() {
        return new BaseTabAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return ShouldGatherePayFragment.newInstance(i);
            }
        };
    }
}
