package com.mfzn.deepusesSer.activity.myteam;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.activity.jiagou.InvitationJoinActivity;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.model.myTeam.TeamManageModel;
import com.mfzn.deepusesSer.present.myteam.TeamManagePresent;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class TeamManageActivity extends BaseMvpActivity<TeamManagePresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_team_name)
    TextView tvTeamName;
    @BindView(R.id.iv_team_type)
    ImageView ivTeamType;
    @BindView(R.id.tv_team_copy)
    TextView tvTeamCopy;
    @BindView(R.id.iv_ren_type)
    ImageView ivRenType;

    @Override
    public int getLayoutId() {
        return R.layout.activity_team_manage;
    }

    @Override
    public TeamManagePresent newP() {
        return new TeamManagePresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("企业/团队管理");
        tvTeamName.getPaint().setFakeBoldText(true);

        getP().teamManage();
    }

    @OnClick({R.id.iv_login_back, R.id.iv_team_copy, R.id.ll_team_add, R.id.ll_team_logo, R.id.ll_team_setting,
            R.id.ll_team_renzheng, R.id.ll_team_quanxian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.iv_team_copy:
                ClipboardManager mClipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                mClipboardManager.setText(tvTeamCopy.getText().toString());
                ToastUtil.showToast(this, "复制成功");
                break;
            case R.id.ll_team_add:
                startActivity(new Intent(this, InvitationJoinActivity.class));
                break;
            case R.id.ll_team_logo:
                startActivity(new Intent(this, CompanyLogoActivity.class));
                break;
            case R.id.ll_team_setting:
                startActivityForResult(new Intent(this, CompanyInfoActivity.class), Constants.COMPANY_INFO_SET);
                break;
            case R.id.ll_team_renzheng:
                startActivity(new Intent(this, CompanyHomepageActivity.class));
                break;
            case R.id.ll_team_quanxian:
                startActivity(new Intent(this, ManageSettingActivity.class));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(Constants.COMPANY_INFO_SET == requestCode){
            if(data != null) {
                getP().teamManage();
            }
        }
    }

    public void teamManage(TeamManageModel model) {
        tvTeamName.setText(model.getCompanyName());
        tvTeamCopy.setText(model.getCompanyCode());

        if(model.getIsCheck() == 0) {//0 未认证 1 已认证
            ivTeamType.setImageResource(R.mipmap.team_weirenzheng);
            ivRenType.setImageResource(R.mipmap.team_weirenzheng);
        }else if(model.getIsCheck() == 1) {
            ivTeamType.setImageResource(R.mipmap.usercenter_hasreal);
            ivRenType.setImageResource(R.mipmap.usercenter_hasreal);
        }
    }
}
