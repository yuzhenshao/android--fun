package com.mfzn.deepuses.activity.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewlySuccessActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_suc_title)
    TextView tvSucTitle;

    @Override
    public int getLayoutId() {
        return R.layout.activity_newly_success;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_newly_success));
        tvSucTitle.getPaint().setFakeBoldText(true);
    }

    @OnClick({R.id.iv_login_back, R.id.but_check, R.id.but_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.but_check:
                startActivity(new Intent(this, FoundTemplateActivity.class));
                break;
            case R.id.but_add:
                Intent intent = new Intent(this, ProjectJoinActivity.class);
                startActivity(intent);
                break;
        }
    }
}
