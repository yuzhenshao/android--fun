package com.mfzn.deepusesSer.activityxm.shgd;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class WorkorderDispatchActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.et_dis_gcs)
    EditText etDisGcs;
    @BindView(R.id.tv_dis_phone)
    TextView tvDisPhone;
    @BindView(R.id.et_dis_remarks)
    EditText etDisRemarks;

    @Override
    public int getLayoutId() {
        return R.layout.activity_workorder_dispatch;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_workorder_dispatch));
    }

    @OnClick({R.id.iv_login_back, R.id.et_dis_gcs, R.id.but_dis_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.et_dis_gcs:
                break;
            case R.id.but_dis_commit:
                break;
        }
    }
}
