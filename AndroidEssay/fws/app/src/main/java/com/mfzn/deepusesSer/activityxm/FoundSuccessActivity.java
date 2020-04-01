package com.mfzn.deepusesSer.activityxm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseActivity;
import com.mfzn.deepusesSer.utils.Constants;

import butterknife.BindView;
import butterknife.OnClick;

public class FoundSuccessActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_fou_success)
    TextView tvFouSuccess;

    private String proid;

    @Override
    public int getLayoutId() {
        return R.layout.activity_found_success;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_newly_success));
        tvFouSuccess.getPaint().setFakeBoldText(true);

        proid = getIntent().getStringExtra(Constants.FOUND_PROJECT_PROID);
    }

    @OnClick({R.id.iv_login_back, R.id.but_fou_can, R.id.but_fou_join})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.but_fou_can:
                break;
            case R.id.but_fou_join:
                Intent intent = new Intent(this, ProjectCodeActivity.class);
                intent.putExtra(Constants.FOUND_PROJECT_PROID,proid);
                startActivity(intent);
                break;
        }
    }
}
