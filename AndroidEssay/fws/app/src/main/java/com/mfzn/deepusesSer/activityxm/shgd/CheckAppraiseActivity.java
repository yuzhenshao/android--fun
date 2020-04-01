package com.mfzn.deepusesSer.activityxm.shgd;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class CheckAppraiseActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.iv_ch_icon)
    ImageView ivChIcon;
    @BindView(R.id.tv_ch_name)
    TextView tvChName;
    @BindView(R.id.tv_ch_time)
    TextView tvChTime;
    @BindView(R.id.iv_ch_xx1)
    ImageView ivChXx1;
    @BindView(R.id.iv_ch_xx2)
    ImageView ivChXx2;
    @BindView(R.id.iv_ch_xx3)
    ImageView ivChXx3;
    @BindView(R.id.iv_ch_xx4)
    ImageView ivChXx4;
    @BindView(R.id.iv_ch_xx5)
    ImageView ivChXx5;
    @BindView(R.id.tv_ch_type)
    TextView tvChType;
    @BindView(R.id.tv_ch_content)
    TextView tvChContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_check_appraise;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_check_appraise));
    }

    @OnClick(R.id.iv_login_back)
    public void onViewClicked() {
        finish();
    }
}
