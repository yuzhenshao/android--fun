package com.mfzn.deepuses.activity.myteam;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mfzn.deepuses.MainActivity;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.company.SelectCompanyActivity;
import com.mfzn.deepuses.activity.jiagou.InvitationJoinActivity;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.login.UserModel;
import com.mfzn.deepuses.model.myTeam.TeamManageModel;
import com.mfzn.deepuses.present.myteam.TeamManagePresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class TeamManageActivity extends BaseMvpActivity<TeamManagePresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_team_name)
    TextView tvTeamName;
    @BindView(R.id.iv_team_type)
    ImageView ivTeamType;
    @BindView(R.id.tv_team_copy)
    TextView tvTeamCopy;
//    @BindView(R.id.iv_ren_type)
//    ImageView ivRenType;

    private String logo;

    private TeamManageModel model;

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

        RxBus.getInstance().toObservable().map(new Function<Object, EventMsg>() {
            @Override
            public EventMsg apply(Object o) throws Exception {
                return (EventMsg) o;
            }
        }).subscribe(new Consumer<EventMsg>() {
            @Override
            public void accept(EventMsg eventMsg) throws Exception {
                if (eventMsg != null) {
                    if (eventMsg.getMsg().equals(Constants.COMLOGO)) {
                        getP().teamManage();
                    }
                }
            }
        });
    }

    @OnClick({R.id.iv_login_back, R.id.iv_team_copy, R.id.ll_team_add, R.id.ll_team_logo, R.id.ll_team_setting,
             R.id.ll_team_quanxian})
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
                Intent intent = new Intent(this, CompanyLogoActivity.class);
                intent.putExtra(Constants.COMPANY_LOGO,logo);
                startActivity(intent);
                break;
            case R.id.ll_team_setting:
                Intent intent1 = new Intent(this, CompanyInfoActivity.class);
                intent1.putExtra(Constants.COMPANY_INFO,model);
                startActivityForResult(intent1, Constants.COMPANY_INFO_SET);
                break;
//            case R.id.ll_team_renzheng:
//                startActivity(new Intent(this, CompanyHomepageActivity.class));
//                break;
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
        this.model = model;

        tvTeamName.setText(model.getCompanyName());
        tvTeamCopy.setText(model.getCompanyCode());

        logo = model.getLogo();

        if(model.getIsCheck() == 0) {//0 未认证 1 已认证
            ivTeamType.setImageResource(R.mipmap.team_weirenzheng);
//            ivRenType.setImageResource(R.mipmap.team_weirenzheng);
        }else if(model.getIsCheck() == 1) {
            ivTeamType.setImageResource(R.mipmap.usercenter_hasreal);
//            ivRenType.setImageResource(R.mipmap.usercenter_hasreal);
        }
    }
}
