package com.mfzn.deepusesSer.activity.myteam;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.activity.company.SelectLableActivity;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.present.myteam.CompanyInfoPresent;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.utils.ObtainTime;
import com.mfzn.deepusesSer.utils.OnInputChangeListener;
import com.mfzn.deepusesSer.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class CompanyInfoActivity extends BaseMvpActivity<CompanyInfoPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_bass_comlate)
    TextView tvBassComlate;
    @BindView(R.id.et_com_name)
    EditText etComName;
    @BindView(R.id.iv_com_name)
    ImageView ivComName;
    @BindView(R.id.et_com_quancheng)
    EditText etComQuancheng;
    @BindView(R.id.iv_com_quancheng)
    ImageView ivComQuancheng;
    @BindView(R.id.et_com_dizhi)
    EditText etComDizhi;
    @BindView(R.id.iv_com_dizhi)
    ImageView ivComDizhi;
    @BindView(R.id.et_com_wangzhi)
    EditText etComWangzhi;
    @BindView(R.id.iv_com_wangzhi)
    ImageView ivComWangzhi;
    @BindView(R.id.et_com_yewu)
    EditText etComYewu;
    @BindView(R.id.et_com_guimo)
    EditText etComGuimo;
    @BindView(R.id.tv_com_time)
    TextView tvComTime;

    private String scaleID;
    private String yewuID;


    @Override
    public int getLayoutId() {
        return R.layout.activity_company_info;
    }

    @Override
    public CompanyInfoPresent newP() {
        return new CompanyInfoPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        tvBassTitle.setText("企业信息设置");
        tvBassComlate.setVisibility(View.VISIBLE);

        tvComTime.setText(ObtainTime.endTime());

        isShow();
    }

    @OnClick({R.id.iv_login_back, R.id.tv_bass_comlate, R.id.iv_com_name, R.id.iv_com_quancheng, R.id.iv_com_dizhi,
            R.id.iv_com_wangzhi, R.id.et_com_yewu, R.id.et_com_guimo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_bass_comlate:
                comlate();
                break;
            case R.id.iv_com_name:
                etComName.getText().clear();
                break;
            case R.id.iv_com_quancheng:
                etComQuancheng.getText().clear();
                break;
            case R.id.iv_com_dizhi:
                etComDizhi.getText().clear();
                break;
            case R.id.iv_com_wangzhi:
                etComWangzhi.getText().clear();
                break;
            case R.id.et_com_yewu:
                startActivityForResult(new Intent(this, SelectLableActivity.class), Constants.SELECT_LAVLE);
                break;
            case R.id.et_com_guimo:
                startActivityForResult(new Intent(this, CompanyScaleActivity.class), Constants.COMPANY_SCALE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(Constants.COMPANY_SCALE == requestCode){
            if(data != null) {
                String name = data.getStringExtra(Constants.COMPANY_SCALE_NAME);
                scaleID = data.getStringExtra(Constants.COMPANY_SCALE_ID);
                etComGuimo.setText(name);
            }
        }else if(Constants.SELECT_LAVLE == requestCode){
            if(data != null) {
                String name = data.getStringExtra(Constants.SELECT_LAVLE_NAME);
                yewuID = data.getStringExtra(Constants.SELECT_LAVLE_ID);
                etComYewu.setText(name);
            }
        }
    }

    public void companyInfo() {
        Intent intent = new Intent();
        intent.putExtra("gdfdg", "xddfd");
        setResult(Constants.COMPANY_INFO_SET,intent);
        finish();
    }

    private void comlate() {
        String name = etComName.getText().toString().trim();
        String qc = etComQuancheng.getText().toString().trim();
        String dizhi = etComDizhi.getText().toString().trim();
        String wz = etComWangzhi.getText().toString().trim();
        String yewu = etComYewu.getText().toString().trim();
        String guimo = etComGuimo.getText().toString().trim();
        if(TextUtils.isEmpty(name)) {
            ToastUtil.showToast(this,getString(R.string.company_info_name));
            return;
        }
        if(TextUtils.isEmpty(qc)) {
            ToastUtil.showToast(this,getString(R.string.company_info_qc));
            return;
        }
        if(TextUtils.isEmpty(dizhi)) {
            ToastUtil.showToast(this,getString(R.string.company_info_dizhi));
            return;
        }
        if(TextUtils.isEmpty(wz)) {
            ToastUtil.showToast(this,getString(R.string.company_info_wz));
            return;
        }
        if(TextUtils.isEmpty(yewu)) {
            ToastUtil.showToast(this,getString(R.string.company_info_yewu));
            return;
        }
        if(TextUtils.isEmpty(guimo)) {
            ToastUtil.showToast(this,getString(R.string.company_info_guimo));
            return;
        }
        getP().companyInfo(name,qc,dizhi,wz,yewuID,scaleID);
    }

    private void isShow() {
        etComName.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(etComName.getText().toString().trim())){
                    ivComName.setVisibility(View.GONE);
                }else {
                    ivComName.setVisibility(View.VISIBLE);
                }
            }
        });
        etComQuancheng.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(etComQuancheng.getText().toString().trim())){
                    ivComQuancheng.setVisibility(View.GONE);
                }else {
                    ivComQuancheng.setVisibility(View.VISIBLE);
                }
            }
        });
        etComDizhi.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(etComDizhi.getText().toString().trim())){
                    ivComDizhi.setVisibility(View.GONE);
                }else {
                    ivComDizhi.setVisibility(View.VISIBLE);
                }
            }
        });
        etComWangzhi.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(etComWangzhi.getText().toString().trim())){
                    ivComWangzhi.setVisibility(View.GONE);
                }else {
                    ivComWangzhi.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
