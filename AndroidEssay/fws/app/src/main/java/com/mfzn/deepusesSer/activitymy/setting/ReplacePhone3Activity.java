package com.mfzn.deepusesSer.activitymy.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ReplacePhone3Activity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_rep_success)
    TextView tvRepSuccess;
    @BindView(R.id.tv_rep_phone)
    TextView tvRepPhone;

    @Override
    public int getLayoutId() {
        return R.layout.activity_replace_phone3;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_replace_phone));
        tvRepSuccess.getPaint().setFakeBoldText(true);
    }

    @OnClick({R.id.iv_login_back, R.id.but_rep_deter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.but_rep_deter:
                finish();
                break;
        }
    }
}
