package com.mfzn.deepuses.activityfx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.fragment.fx.VideoFragmentNew;
import com.mfzn.deepuses.fragment.fx.ZixunFragmentNew;

public class ChangjingListActivity extends BasicActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("场景");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fl_content, new VideoFragmentNew());
        transaction.commit();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_faxian_zixun;
    }
}
