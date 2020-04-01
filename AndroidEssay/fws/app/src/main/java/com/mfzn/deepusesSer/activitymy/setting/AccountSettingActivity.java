package com.mfzn.deepusesSer.activitymy.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AccountSettingActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_acc_phone)
    TextView tvAccPhone;
    @BindView(R.id.tv_acc_login)
    TextView tvAccLogin;
    @BindView(R.id.tv_acc_wb)
    TextView tvAccWb;
    @BindView(R.id.tv_acc_qq)
    TextView tvAccQq;
    @BindView(R.id.tv_acc_ali)
    TextView tvAccAli;

    @Override
    public int getLayoutId() {
        return R.layout.activity_account_setting;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_account_setting));
        tvAccLogin.getPaint().setFakeBoldText(true);
    }

    @OnClick({R.id.iv_login_back, R.id.ll_acc_phone, R.id.ll_acc_wb, R.id.ll_acc_qq, R.id.ll_acc_ali})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_acc_phone:
                startActivity(new Intent(this, ReplacePhoneActivity.class));
                break;
            case R.id.ll_acc_wb:
                break;
            case R.id.ll_acc_qq:
                break;
            case R.id.ll_acc_ali:
                break;
        }
    }
}
