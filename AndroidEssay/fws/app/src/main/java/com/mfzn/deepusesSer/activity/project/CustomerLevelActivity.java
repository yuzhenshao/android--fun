package com.mfzn.deepusesSer.activity.project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseActivity;
import com.mfzn.deepusesSer.utils.Constants;

import butterknife.BindView;
import butterknife.OnClick;

public class CustomerLevelActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.iv_cus_pt)
    ImageView ivCusPt;
    @BindView(R.id.iv_cus_bj)
    ImageView ivCusBj;
    @BindView(R.id.iv_cus_hj)
    ImageView ivCusHj;

    private boolean ptType = false;
    private boolean bjType = false;
    private boolean hjType = false;
    private Intent intent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_customer_level;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_customer_level));

        intent = new Intent();

        String level = intent.getStringExtra(Constants.CUSTOMER_LEVEL);
        if(!TextUtils.isEmpty(level)) {
            if(level.equals("普通会员")) {
                ivCusPt.setImageResource(R.mipmap.regi_xuanzhong);
                ptType = true;
            }else if(level.equals("白金会员")) {
                ivCusBj.setImageResource(R.mipmap.regi_xuanzhong);
                bjType = true;
            }else if(level.equals("黄金会员")) {
                ivCusHj.setImageResource(R.mipmap.regi_xuanzhong);
                hjType = true;
            }
        }
    }

    @OnClick({R.id.iv_login_back, R.id.iv_cus_pt, R.id.iv_cus_bj, R.id.iv_cus_hj})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.iv_cus_pt:
                if(ptType) {
                    ivCusPt.setImageResource(R.mipmap.regi_weixuan);
                    ptType = false;
                }else {
                    ivCusPt.setImageResource(R.mipmap.regi_xuanzhong);
                    ptType = true;
                    intent.putExtra(Constants.CUSTOMER_LEVEL_RETURN, "普通会员");
                    setResult(Constants.PROJECT_LEVEL, intent);
                    finish();
                }
                break;
            case R.id.iv_cus_bj:
                if(bjType) {
                    ivCusBj.setImageResource(R.mipmap.regi_weixuan);
                    bjType = false;
                }else {
                    ivCusBj.setImageResource(R.mipmap.regi_xuanzhong);
                    bjType = true;
                    intent.putExtra(Constants.CUSTOMER_LEVEL_RETURN, "白金会员");
                    setResult(Constants.PROJECT_LEVEL, intent);
                    finish();
                }
                break;
            case R.id.iv_cus_hj:
                if(hjType) {
                    ivCusHj.setImageResource(R.mipmap.regi_weixuan);
                    hjType = false;
                }else {
                    ivCusHj.setImageResource(R.mipmap.regi_xuanzhong);
                    hjType = true;
                    intent.putExtra(Constants.CUSTOMER_LEVEL_RETURN, "黄金会员");
                    setResult(Constants.PROJECT_LEVEL, intent);
                    finish();
                }
                break;
        }
    }
}
