package com.mfzn.deepusesSer.activity.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class FoundTemplateActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.ll_fou_tips)
    LinearLayout llFouTips;
    @BindView(R.id.tv_fou_name)
    TextView tvFouName;
    @BindView(R.id.tv_fou_select)
    TextView tvFouSelect;
    @BindView(R.id.tv_fou_copy)
    TextView tvFouCopy;
    @BindView(R.id.tv_fou_person)
    TextView tvFouPerson;

    @Override
    public int getLayoutId() {
        return R.layout.activity_found_template;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("万达广场");
        tvFouName.getPaint().setFakeBoldText(true);
        tvFouSelect.getPaint().setFakeBoldText(true);
        tvFouCopy.getPaint().setFakeBoldText(true);
        tvFouPerson.getPaint().setFakeBoldText(true);
    }

    @OnClick({R.id.iv_login_back, R.id.iv_fou_delete, R.id.ll_fou_select, R.id.ll_fou_copy, R.id.ll_fou_person})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.iv_fou_delete:
                llFouTips.setVisibility(View.GONE);
                break;
            case R.id.ll_fou_select:
                startActivity(new Intent(this,TemplateSelectActivity.class));
                break;
            case R.id.ll_fou_copy:
                startActivity(new Intent(this,ProjectCopyActivity.class));
                break;
            case R.id.ll_fou_person:
                break;
        }
    }
}
