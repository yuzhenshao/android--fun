package com.mfzn.deepusesSer.activity.project;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ProjectSurveyActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_sur_number)
    TextView tvSurNumber;
    @BindView(R.id.tv_sur_xname)
    TextView tvSurXname;
    @BindView(R.id.tv_sur_name)
    TextView tvSurName;
    @BindView(R.id.tv_sur_phone)
    TextView tvSurPhone;
    @BindView(R.id.tv_sur_address)
    TextView tvSurAddress;
    @BindView(R.id.tv_sur_time)
    TextView tvSurTime;
    @BindView(R.id.tv_sur_type)
    TextView tvSurType;

    @Override
    public int getLayoutId() {
        return R.layout.activity_project_survey;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_project_survey));
    }

    @OnClick({R.id.iv_login_back, R.id.iv_sur_number, R.id.iv_sur_phone, R.id.iv_sur_dw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.iv_sur_number:
                break;
            case R.id.iv_sur_phone:
                break;
            case R.id.iv_sur_dw:
                break;
        }
    }
}
