package com.mfzn.deepusesSer.activitymy.setting;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.activity.login.RegisterCodeActivity;
import com.mfzn.deepusesSer.bass.BaseActivity;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.utils.OnInputChangeListener;
import com.mfzn.deepusesSer.utils.ToastUtil;
import com.mfzn.deepusesSer.utils.UserHelper;

import butterknife.BindView;
import butterknife.OnClick;

public class ReplacePhoneActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_rep_phone)
    TextView tvRepPhone;
    @BindView(R.id.et_rep_phone)
    EditText etRepPhone;
    @BindView(R.id.but_rep_next)
    Button butRepNext;

    @Override
    public int getLayoutId() {
        return R.layout.activity_replace_phone;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_replace_phone));
        tvRepPhone.setText("更换手机号后，下次登录可使用新手机号登录，当前手机号将作废。当前手机号："+ UserHelper.getU_phone());
        etRepPhone.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etRepPhone.getText().toString().trim().length() >= 11) {
                    butRepNext.setEnabled(true);
                    butRepNext.setBackgroundResource(R.drawable.yuanjiao_4a9012_button_shape);
                } else {
                    butRepNext.setEnabled(false);
                    butRepNext.setBackgroundResource(R.drawable.yuanjiao_bfc2cc_button_shape);
                }
            }
        });
    }

    @OnClick({R.id.iv_login_back, R.id.but_rep_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.but_rep_next:
                if (etRepPhone.getText().toString().equals(UserHelper.getU_phone())){
                    ToastUtil.showToast(ReplacePhoneActivity.this,"新号码不能与旧号码一致");
                }else{
                    Intent intent = new Intent(this, ReplacePhone2Activity.class);
                    intent.putExtra(Constants.REGISTER_PHONE,etRepPhone.getText().toString().trim());
                    startActivity(intent);
                }
                break;
        }
    }
}
