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
import com.mfzn.deepuses.model.myTeam.ManageSettingModel;
import com.mfzn.deepuses.present.myteam.AuthoritySettingPresent;
import com.mfzn.deepuses.present.myteam.ManageSettingPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.ToastUtil;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthoritySettingActivity extends BaseMvpActivity<AuthoritySettingPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.iv_auth_xmcj)
    ImageView ivAuthXmcj;
//    @BindView(R.id.iv_auth_sjkb)
//    ImageView ivAuthSjkb;
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

    private int cjxm = 0;// 1 关 2 开
    private int sjkb = 0;
    private int xmgl = 0;
    private int bmgl = 0;
    private int hysz = 0;
    private int khgl = 0;
    private String bmglID = "";

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
        cjxm = model.getAuthCreate();
        if(cjxm == 0) {
            ivAuthXmcj.setImageResource(R.mipmap.shou_close);
        }else if(cjxm == 1){
            ivAuthXmcj.setImageResource(R.mipmap.shou_open);
        }
//        sjkb = model.getAuthData();
//        if(sjkb == 0) {
//            ivAuthSjkb.setImageResource(R.mipmap.shou_close);
//        }else if(sjkb == 1){
//            ivAuthSjkb.setImageResource(R.mipmap.shou_open);
//        }
        xmgl = model.getAuthManage();
        if(xmgl == 0) {
            ivAuthXmgl.setImageResource(R.mipmap.shou_close);
        }else if(xmgl == 1){
            ivAuthXmgl.setImageResource(R.mipmap.shou_open);
        }
        hysz = model.getRechargeAuth();
        if(hysz == 0) {
            ivAuthHysz.setImageResource(R.mipmap.shou_close);
        }else if(hysz == 1){
            ivAuthHysz.setImageResource(R.mipmap.shou_open);
        }
        khgl = model.getCrmAuth();
        if(khgl == 0) {
            ivAuthKhgl.setImageResource(R.mipmap.shou_close);
        }else if(khgl == 1){
            ivAuthKhgl.setImageResource(R.mipmap.shou_open);
        }


        List<ManageSettingModel.DepartNameBean> departName = model.getDepartName();
        if(departName != null && departName.size() != 0) {
            bmgl = 1;
            bmglID = model.getDepartIDs();
            llAuthGxbm.setVisibility(View.VISIBLE);
            ivAuthBmgl.setImageResource(R.mipmap.shou_open);
            String ss = null;
            for(int i = 0; i < departName.size(); i++) {
                if(i == 0) {
                    ss = departName.get(i).getDepartmentName();
                }else {
                    ss = ss + "，" + departName.get(i).getDepartmentName();
                }
            }
            tvAuthGxbm.setText(ss);
        }else {
            bmgl = 0;
            llAuthGxbm.setVisibility(View.GONE);
            ivAuthBmgl.setImageResource(R.mipmap.shou_close);
        }
    }

    @OnClick({R.id.iv_login_back, R.id.iv_auth_xmcj, R.id.iv_auth_xmgl, R.id.iv_auth_bmgl,
            R.id.ll_auth_gxbm, R.id.but_auth_qr,R.id.iv_auth_hysz,R.id.iv_auth_khgl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.iv_auth_xmcj:
                if(cjxm == 0) {
                    ivAuthXmcj.setImageResource(R.mipmap.shou_open);
                    cjxm = 1;
                }else if(cjxm == 1){
                    ivAuthXmcj.setImageResource(R.mipmap.shou_close);
                    cjxm = 0;
                }
                break;
//            case R.id.iv_auth_sjkb:
//                if(sjkb == 0) {
//                    ivAuthSjkb.setImageResource(R.mipmap.shou_open);
//                    sjkb = 1;
//                }else if(sjkb == 1){
//                    ivAuthSjkb.setImageResource(R.mipmap.shou_close);
//                    sjkb = 0;
//                }
//                break;
            case R.id.iv_auth_xmgl:
                if(xmgl == 0) {
                    ivAuthXmgl.setImageResource(R.mipmap.shou_open);
                    xmgl = 1;
                }else if(xmgl == 1){
                    ivAuthXmgl.setImageResource(R.mipmap.shou_close);
                    xmgl = 0;
                }
                break;
            case R.id.iv_auth_bmgl:
                if(bmgl == 0) {
                    ivAuthBmgl.setImageResource(R.mipmap.shou_open);
                    bmgl = 1;
                    llAuthGxbm.setVisibility(View.VISIBLE);
                }else if(bmgl == 1){
                    ivAuthBmgl.setImageResource(R.mipmap.shou_close);
                    bmgl = 0;
                    llAuthGxbm.setVisibility(View.GONE);
                }
                break;
            case R.id.iv_auth_hysz:
                if(hysz == 0) {
                    ivAuthHysz.setImageResource(R.mipmap.shou_open);
                    hysz = 1;
                }else if(hysz == 1){
                    ivAuthHysz.setImageResource(R.mipmap.shou_close);
                    hysz = 0;
                }
                break;
            case R.id.iv_auth_khgl:
                if(khgl == 0) {
                    ivAuthKhgl.setImageResource(R.mipmap.shou_open);
                    khgl = 1;
                }else if(khgl == 1){
                    ivAuthKhgl.setImageResource(R.mipmap.shou_close);
                    khgl = 0;
                }
                break;
            case R.id.ll_auth_gxbm:
                Intent intent = new Intent(this, SelectBranchActivity.class);
                intent.putExtra(Constants.QX_SET_TEXT,bmglID);
                startActivityForResult(intent, Constants.SELECT_BU);
                break;
            case R.id.but_auth_qr:
                getP().authoritySetting(model.getUserID() + "",
                        bmglID,cjxm + "",sjkb + "",xmgl + "",hysz + "",khgl + "");
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
        ToastUtil.showToast(this,models);
        Intent intent = new Intent();
        intent.putExtra("fda", "xvc");
        setResult(Constants.QUANXIAN_SET,intent);
        finish();
    }
}
