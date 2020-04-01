package com.mfzn.deepusesSer.activityxm.shgd;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class CancalAcceptActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_can_bold)
    TextView tvCanBold;
    @BindView(R.id.et_can_content)
    EditText etCanContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_cancal_accept;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_cancal_accept));
        tvCanBold.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.iv_login_back, R.id.but_can_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.but_can_commit:
                finish();
                break;
        }
    }
}
