package com.mfzn.deepusesSer.activity.myteam;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ManageSettingActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;

    @Override
    public int getLayoutId() {
        return R.layout.activity_manage_setting;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("管理权限设置");
    }

    @OnClick({R.id.iv_login_back, R.id.tv_set_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_set_add:
                break;
        }
    }
}
