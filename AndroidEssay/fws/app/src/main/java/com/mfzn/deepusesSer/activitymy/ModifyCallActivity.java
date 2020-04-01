package com.mfzn.deepusesSer.activitymy;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseActivity;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.present.person.UserInfoModifyNamePresent;
import com.mfzn.deepusesSer.present.person.UserInfoPresent;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.utils.OnInputChangeListener;
import com.mfzn.deepusesSer.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ModifyCallActivity extends BaseMvpActivity<UserInfoModifyNamePresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_bass_comlate)
    TextView tvBassComlate;
    @BindView(R.id.et_mod_name)
    EditText etModName;
    @BindView(R.id.ll_mod_delete)
    LinearLayout llModDelete;

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_call;
    }

    @Override
    public UserInfoModifyNamePresent newP() {
        return new UserInfoModifyNamePresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_modify_call));
        tvBassComlate.setVisibility(View.VISIBLE);

        String name = getIntent().getStringExtra(Constants.MODIFU_NICK);
        etModName.setText(name);

        if(!TextUtils.isEmpty(name)) {
            llModDelete.setVisibility(View.VISIBLE);
        }

        etModName.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(etModName.getText().toString().trim())){
                    llModDelete.setVisibility(View.GONE);
                }else {
                    llModDelete.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @OnClick({R.id.iv_login_back, R.id.tv_bass_comlate, R.id.ll_mod_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_bass_comlate:

                if(!TextUtils.isEmpty(etModName.getText().toString().trim())) {
                    getP().modifyName(etModName.getText().toString());
                }else {
                    ToastUtil.showToast(this,"请输入昵称");
                }
                break;
            case R.id.ll_mod_delete:
                etModName.getText().clear();
                break;
        }
    }

    public void modifyName(String msg){
        ToastUtil.showToast(this,msg);
        Intent intent = new Intent();
        intent.putExtra(Constants.MODIFU_NICK_RETURN, etModName.getText().toString().trim());
        setResult(Constants.MODIFU_NICHENG,intent);
        finish();
    }

}
