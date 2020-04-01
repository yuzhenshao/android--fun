package com.mfzn.deepuses.home.application.activity;

import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.home.application.present.ApplicationPresent;
import com.mfzn.deepuses.present.foundxm.FoundProjectPresnet;

/**
 * @author yz @date 2020-03-27
 */
public class AllApplicationActivity extends BaseMvpActivity<ApplicationPresent> {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public ApplicationPresent newP() {
        return new ApplicationPresent();
    }
}
