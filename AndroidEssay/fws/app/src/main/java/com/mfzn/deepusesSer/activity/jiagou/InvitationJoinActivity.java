package com.mfzn.deepusesSer.activity.jiagou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class InvitationJoinActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_team_sousuo)
    TextView tvTeamSousuo;
    @BindView(R.id.tv_team_code)
    TextView tvTeamCode;

    @Override
    public int getLayoutId() {
        return R.layout.activity_invitation_join;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("邀请加入公司/团队");

        tvTeamSousuo.getPaint().setFakeBoldText(true);
        tvTeamCode.getPaint().setFakeBoldText(true);
    }

    @OnClick({R.id.iv_login_back, R.id.ll_join_weixin, R.id.ll_join_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_join_weixin:
                break;
            case R.id.ll_join_code:
                startActivity(new Intent(this, ShareCodeActivity.class));
                break;
        }
    }
}
