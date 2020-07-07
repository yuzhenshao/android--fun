package com.mfzn.deepuses.activity.myteam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.bean.request.UpdateManagerRequest;
import com.mfzn.deepuses.model.myTeam.ManageSettingModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.present.myteam.AuthoritySettingPresent;
import com.mfzn.deepuses.present.myteam.ManageSettingPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.http.Field;

public class AuthoritySettingActivity extends BaseMvpActivity<AuthoritySettingPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.iv_auth_xmcj)
    ImageView ivAuthXmcj;
    @BindView(R.id.iv_auth_xmgl)
    ImageView ivAuthXmgl;
    @BindView(R.id.iv_auth_bmgl)
    ImageView ivAuthBmgl;
    @BindView(R.id.iv_auth_hysz)
    ImageView ivAuthHysz;
    @BindView(R.id.iv_auth_khgl)
    ImageView ivAuthKhgl;
    @BindView(R.id.tv_auth_gxbm)
    TextView tvAuthGxbm;
    @BindView(R.id.ll_auth_gxbm)
    LinearLayout llAuthGxbm;
    private String bmglID = "";
    private boolean isBmglVisible = false;

    private ManageSettingModel model;

    @Override
    public int getLayoutId() {
        return R.layout.activity_authority_setting;
    }

    @Override
    public AuthoritySettingPresent newP() {
        return new AuthoritySettingPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_authority_set));

        model = (ManageSettingModel) getIntent().getSerializableExtra(Constants.QX_SET_MODEL);
        if (model == null) {
            ToastUtil.showToast(this, "权限设置出错了，请稍后重试");
            return;
        }
        ivAuthXmcj.setImageResource(model.getProCreateAuth() == 1 ? R.mipmap.shou_open : R.mipmap.shou_close);
        ivAuthXmgl.setImageResource(model.getAdminCreateAuth() == 1 ? R.mipmap.shou_open : R.mipmap.shou_close);
        ivAuthHysz.setImageResource(model.getRechargeAuth() == 1 ? R.mipmap.shou_open : R.mipmap.shou_close);
        ivAuthKhgl.setImageResource(model.getCrmAuth() == 1 ? R.mipmap.shou_open : R.mipmap.shou_close);
        ivAuthBmgl.setImageResource(R.mipmap.shou_close);
    }

    @OnClick({R.id.iv_login_back, R.id.iv_auth_xmcj, R.id.iv_auth_xmgl, R.id.iv_auth_bmgl,
            R.id.ll_auth_gxbm, R.id.but_auth_qr, R.id.iv_auth_hysz, R.id.iv_auth_khgl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.iv_auth_xmcj:
                model.setProCreateAuth(model.getProCreateAuth()==0?1:0);
                ivAuthXmcj.setImageResource(model.getProCreateAuth() == 1 ? R.mipmap.shou_open : R.mipmap.shou_close);
                break;
            case R.id.iv_auth_xmgl:
                model.setAdminCreateAuth(model.getAdminCreateAuth()==0?1:0);
                ivAuthXmgl.setImageResource(model.getAdminCreateAuth() == 1 ? R.mipmap.shou_open : R.mipmap.shou_close);
                break;
            case R.id.iv_auth_bmgl:
                isBmglVisible = !isBmglVisible;
                ivAuthBmgl.setImageResource(isBmglVisible ? R.mipmap.shou_open : R.mipmap.shou_close);
                //llAuthGxbm.setVisibility(isBmglVisible?View.VISIBLE:View.GONE);
                break;
            case R.id.iv_auth_hysz:
                model.setRechargeAuth(model.getRechargeAuth()==0?1:0);
                ivAuthHysz.setImageResource(model.getRechargeAuth() == 1 ? R.mipmap.shou_open : R.mipmap.shou_close);
                break;
            case R.id.iv_auth_khgl:
                model.setCrmAuth(model.getCrmAuth()==0?1:0);
                ivAuthKhgl.setImageResource(model.getCrmAuth() == 1 ? R.mipmap.shou_open : R.mipmap.shou_close);
                break;
            case R.id.ll_auth_gxbm:
                Intent intent = new Intent(this, SelectBranchActivity.class);
                intent.putExtra(Constants.QX_SET_TEXT, bmglID);
                startActivityForResult(intent, Constants.SELECT_BU);
                break;
            case R.id.but_auth_qr:
                getP().authoritySetting(model.getUserID() + "", model);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.SELECT_BU == requestCode) {
            if (data != null) {
                bmglID = data.getStringExtra(Constants.SELECT_BU_TEXT);
                String name = data.getStringExtra(Constants.SELECT_BU_NAME);
                tvAuthGxbm.setText(name);
            }
        }
    }

    public void authoritySetting(String models) {
        ToastUtil.showToast(this, models);
        Intent intent = new Intent();
        intent.putExtra("fda", "xvc");
        setResult(Constants.QUANXIAN_SET, intent);
        finish();
    }
}
