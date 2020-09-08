package com.mfzn.deepuses.purchasesellsave.store.activity;

import android.support.v4.app.Fragment;

import com.libcommon.table.TabLabel;
import com.mfzn.deepuses.purchasesellsave.capital.activity.BaseTabActivity;
import com.mfzn.deepuses.purchasesellsave.store.fragment.OrderWaitInOutListFragment;

public class OrderWaitInOutListActivity extends BaseTabActivity {

    @Override
    protected void initTabLabelList() {
        mTitleBar.updateTitleBar("待出入库");
        mTabLabelList.add(new TabLabel("出库"));
        mTabLabelList.add(new TabLabel("入库"));
    }

    @Override
    protected BaseTabAdapter getAdapter() {
        return new BaseTabAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return OrderWaitInOutListFragment.newInstance(i);
            }
        };
    }
}
