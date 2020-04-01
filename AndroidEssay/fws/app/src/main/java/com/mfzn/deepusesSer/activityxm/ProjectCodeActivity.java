package com.mfzn.deepusesSer.activityxm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.present.foundxm.ProjectCodePresnet;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.utils.EventMsg;
import com.mfzn.deepusesSer.utils.RxBus;

import butterknife.BindView;
import butterknife.OnClick;

public class ProjectCodeActivity extends BaseMvpActivity<ProjectCodePresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_code_jiacu)
    TextView tvCodeJiacu;
    @BindView(R.id.tv_code_name)
    TextView tvCodeName;
    @BindView(R.id.iv_code_icon)
    ImageView ivCodeIcon;
    @BindView(R.id.iv_code)
    ImageView ivCode;

    @Override
    public int getLayoutId() {
        return R.layout.activity_project_code;
    }

    @Override
    public ProjectCodePresnet newP() {
        return new ProjectCodePresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_project_code));
        tvCodeJiacu.getPaint().setFakeBoldText(true);

        String proid = getIntent().getStringExtra(Constants.FOUND_PROJECT_PROID);

        getP().projectCode(proid);
    }

    @OnClick({R.id.iv_login_back, R.id.ll_code_xiazai, R.id.ll_code_qq, R.id.ll_code_weixin, R.id.ll_code_ali})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_code_xiazai:
                break;
            case R.id.ll_code_qq:
                break;
            case R.id.ll_code_weixin:
                break;
            case R.id.ll_code_ali:
                break;
        }
    }

    public void projectCodeSuccess() {
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.FOUNDPROJECT);
        RxBus.getInstance().post(eventMsg);
        Intent intent = new Intent(this, FoundSuccessActivity.class);
        startActivity(intent);
    }
}
