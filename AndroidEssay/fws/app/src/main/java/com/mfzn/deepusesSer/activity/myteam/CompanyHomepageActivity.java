package com.mfzn.deepusesSer.activity.myteam;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.model.myTeam.TeamManageModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.present.myteam.CompanyHomepagePresent;
import com.mfzn.deepusesSer.utils.PhoneUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class CompanyHomepageActivity extends BaseMvpActivity<CompanyHomepagePresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_co_name)
    TextView tvCoName;
    @BindView(R.id.iv_co_type)
    ImageView ivCoType;
    @BindView(R.id.iv_co_icon)
    ImageView ivCoIcon;
    @BindView(R.id.tv_co_wangzhi)
    TextView tvCoWangzhi;
    @BindView(R.id.tv_co_yewu)
    TextView tvCoYewu;

    private String phone;

    @Override
    public int getLayoutId() {
        return R.layout.activity_company_homepage;
    }

    @Override
    public CompanyHomepagePresent newP() {
        return new CompanyHomepagePresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        tvCoName.getPaint().setFakeBoldText(true);
    }

    @OnClick({R.id.iv_login_back, R.id.but_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.but_phone:
                if (!TextUtils.isEmpty(phone)) {
                    PhoneUtils.dialogPhone(this, phone);
                }
                break;
        }
    }

    public void companyHomepage(TeamManageModel model) {
        phone = model.getCompanyTel();
        tvBassTitle.setText(model.getCompanyName());
        tvCoName.setText(model.getCompanyName());

        Glide.with(this).load(ApiHelper.BASE_URL + model.getLogo()).into(ivCoIcon);
        tvCoWangzhi.setText(model.getCompanyWebsite());

        tvCoYewu.setText(model.getBusinessScope());

        if(model.getIsCheck() == 0) {//0 未认证 1 已认证
            ivCoType.setImageResource(R.mipmap.team_weirenzheng);
            ivCoType.setImageResource(R.mipmap.team_weirenzheng);
        }else if(model.getIsCheck() == 1) {
            ivCoType.setImageResource(R.mipmap.usercenter_hasreal);
            ivCoType.setImageResource(R.mipmap.usercenter_hasreal);
        }
    }
}
