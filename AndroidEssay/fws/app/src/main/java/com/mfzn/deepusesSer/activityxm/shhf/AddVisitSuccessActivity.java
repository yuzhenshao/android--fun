package com.mfzn.deepusesSer.activityxm.shhf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AddVisitSuccessActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_bass_content)
    TextView tvBassContent;
    @BindView(R.id.tv_vis_success)
    TextView tvVisSuccess;
    @BindView(R.id.tv_vis_content)
    TextView tvVisContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_visit_success;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_add_return_visit));
        tvBassContent.setVisibility(View.VISIBLE);
        tvBassContent.setText("回访记录");
        tvVisSuccess.getPaint().setFakeBoldText(true);
    }

    @OnClick({R.id.iv_login_back, R.id.tv_bass_content, R.id.but_vis_can})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_bass_content:
                startActivity(new Intent(this, VisitRecordActivity.class));
                break;
            case R.id.but_vis_can:
                startActivity(new Intent(this, VisitRecordActivity.class));
                break;
        }
    }
}
