package com.mfzn.deepusesSer.activity.jiagou;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.model.jiagou.ShareCodeModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.present.jiagou.ShareCodePresent;

import butterknife.BindView;
import butterknife.OnClick;

public class ShareCodeActivity extends BaseMvpActivity<ShareCodePresent> {

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
        return R.layout.activity_share_code;
    }

    @Override
    public ShareCodePresent newP() {
        return new ShareCodePresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("团队二维码");
        tvCodeJiacu.getPaint().setFakeBoldText(true);

        getP().shareCode();
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

    public void shareCodeSuccess(ShareCodeModel model) {
        tvCodeName.setText(model.getCompanyName());
        String u_head = model.getU_head();
        if(!TextUtils.isEmpty(u_head)) {
            Glide.with(this).load(ApiHelper.BASE_URL + u_head).into(ivCodeIcon);
        }
        Glide.with(this).load(ApiHelper.BASE_URL + model.getVcodeUrl()).into(ivCodeIcon);
    }
}
