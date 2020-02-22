package com.mfzn.deepuses.activity.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppointPersonActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_app_cbf)
    TextView tvAppCbf;
    @BindView(R.id.et_app_jl)
    EditText etAppJl;
    @BindView(R.id.et_app_ctime)
    EditText etAppCtime;
    @BindView(R.id.tv_app_fs)
    TextView tvAppFs;
    @BindView(R.id.et_app_type)
    EditText etAppType;
    @BindView(R.id.et_app_money)
    EditText etAppMoney;
    @BindView(R.id.et_app_bili)
    EditText etAppBili;
    @BindView(R.id.tv_app_xinfo)
    TextView tvAppXinfo;
    @BindView(R.id.et_app_xname)
    EditText etAppXname;
    @BindView(R.id.et_app_gtime)
    EditText etAppGtime;
    @BindView(R.id.tv_app_binfo)
    TextView tvAppBinfo;
    @BindView(R.id.et_app_remarks)
    EditText etAppRemarks;
    @BindView(R.id.but_app_confirm)
    Button butAppConfirm;

    @Override
    public int getLayoutId() {
        return R.layout.activity_appoint_person;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_appoint_person));
        tvAppCbf.getPaint().setFakeBoldText(true);
        tvAppFs.getPaint().setFakeBoldText(true);
        tvAppXinfo.getPaint().setFakeBoldText(true);
        tvAppBinfo.getPaint().setFakeBoldText(true);

        butAppConfirm.setEnabled(true);
    }

    @OnClick({R.id.iv_login_back, R.id.et_app_jl, R.id.et_app_ctime, R.id.et_app_type, R.id.but_app_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.et_app_jl:
                break;
            case R.id.et_app_ctime:
                break;
            case R.id.et_app_type:
                break;
            case R.id.but_app_confirm:
                Intent intent = new Intent(this, ProjectSurveyActivity.class);
                startActivity(intent);
                break;
        }
    }
}
