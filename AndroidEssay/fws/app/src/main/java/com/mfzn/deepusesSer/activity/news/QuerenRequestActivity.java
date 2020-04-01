package com.mfzn.deepusesSer.activity.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class QuerenRequestActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_bass_comlate)
    TextView tvBassComlate;
    @BindView(R.id.tv_qu_name)
    TextView tvQuName;
    @BindView(R.id.tv_qu_phone)
    TextView tvQuPhone;
    @BindView(R.id.et_qu_bumen)
    EditText etQuBumen;

    @Override
    public int getLayoutId() {
        return R.layout.activity_queren_request;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("确认请求");
        tvBassComlate.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.iv_login_back, R.id.tv_bass_comlate, R.id.et_qu_bumen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_bass_comlate:
                break;
            case R.id.et_qu_bumen:
                Intent intent = new Intent(this, SelectBumenActivity.class);
                startActivity(intent);
                break;
        }
    }
}
