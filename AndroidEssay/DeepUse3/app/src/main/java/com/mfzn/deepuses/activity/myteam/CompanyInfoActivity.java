package com.mfzn.deepuses.activity.myteam;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.company.SelectLableActivity;
import com.mfzn.deepuses.activityxm.MapLocationActivity;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.myTeam.TeamManageModel;
import com.mfzn.deepuses.present.myteam.CompanyInfoPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.ObtainTime;
import com.mfzn.deepuses.utils.OnInputChangeListener;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

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
    @BindView(R.id.iv_com_address)
    ImageView ivComAddress;
    @BindView(R.id.et_com_dizhi)
    EditText etComDizhi;
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

    private String scaleID = "";
    private String yewuID = "";

    private String longitude = "";//经度
    private String latitude = "";//纬度

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

        TeamManageModel model = (TeamManageModel) getIntent().getSerializableExtra(Constants.COMPANY_INFO);

        etComName.setText(model.getCompanyName());
        etComQuancheng.setText(model.getShortName());
        etComDizhi.setText(model.getCompanyAddress());
        longitude = String.valueOf(model.getLongitude());
        latitude = String.valueOf(model.getLatitude());
        etComWangzhi.setText(model.getCompanyWebsite());
        etComYewu.setText(model.getBusinessScopeName());
        yewuID = model.getBusinessScope();
        etComGuimo.setText(model.getScaleName());
        scaleID = model.getScaleID() + "";
    }

    @OnClick({R.id.iv_login_back, R.id.tv_bass_comlate, R.id.iv_com_name, R.id.iv_com_quancheng, R.id.tv_address,
            R.id.iv_com_wangzhi, R.id.yewu, R.id.im_com_guimo,R.id.iv_com_address})
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
            case R.id.iv_com_address:
                etComDizhi.getText().clear();
                break;
            case R.id.tv_address:
                getRxPermissions()
                        .request(Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if (aBoolean) {
                                    startActivityForResult(new Intent(context, MapLocationActivity.class), Constants.MAP_LOCATION);
                                } else {
                                    getvDelegate().toastShort("亲，请先开启定位权限");
                                }
                            }
                        });
                break;
            case R.id.iv_com_wangzhi:
                etComWangzhi.getText().clear();
                break;
            case R.id.yewu:
                startActivityForResult(new Intent(this, SelectLableActivity.class), Constants.SELECT_LAVLE);
                break;
            case R.id.im_com_guimo:
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
        } else if (Constants.MAP_LOCATION == requestCode) {
            if (data != null) {
//                String weizhi = data.getStringExtra(Constants.MAP_LOCATION_WEIZHI);
                longitude = data.getStringExtra(Constants.MAP_LOCATION_JINGDU);
                latitude = data.getStringExtra(Constants.MAP_LOCATION_WEIDU);
                String address = data.getStringExtra(Constants.MAP_LOCATION_ADDRESS);
                etComDizhi.setText(address);
//                etFouDetail.setText(address);
            }
        }
    }

    public void companyInfo() {
        UserHelper.setCompanyNmae(etComName.getText().toString().trim());
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.COMPANYJC);
        RxBus.getInstance().post(eventMsg);
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
//        if(TextUtils.isEmpty(dizhi)) {
//            ToastUtil.showToast(this,getString(R.string.company_info_dizhi));
//            return;
//        }
//        if(TextUtils.isEmpty(wz)) {
//            ToastUtil.showToast(this,getString(R.string.company_info_wz));
//            return;
//        }
        if(TextUtils.isEmpty(yewu)) {
            ToastUtil.showToast(this,getString(R.string.company_info_yewu));
            return;
        }
//        if(TextUtils.isEmpty(guimo)) {
//            ToastUtil.showToast(this,getString(R.string.company_info_guimo));
//            return;
//        }
        getP().companyInfo(name,qc,dizhi,latitude,longitude,wz,yewuID,scaleID);
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
        etComDizhi.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(etComDizhi.getText().toString().trim())){
                    ivComAddress.setVisibility(View.GONE);
                }else {
                    ivComAddress.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
