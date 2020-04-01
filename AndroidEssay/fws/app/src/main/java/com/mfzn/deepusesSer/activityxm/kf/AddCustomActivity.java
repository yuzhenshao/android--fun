package com.mfzn.deepusesSer.activityxm.kf;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.present.xmkefu.AddCustomPresnet;
import com.mfzn.deepusesSer.utils.OnInputChangeListener;

import butterknife.BindView;
import butterknife.OnClick;

public class AddCustomActivity extends BaseMvpActivity<AddCustomPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.et_add_type)
    EditText etAddType;
    @BindView(R.id.et_add_name)
    EditText etAddName;
    @BindView(R.id.et_add_phone)
    EditText etAddPhone;
    @BindView(R.id.but_add_qr)
    Button butAddQr;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_custom;
    }

    @Override
    public AddCustomPresnet newP() {
        return new AddCustomPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_add_custom));

        etAddType.addTextChangedListener(mOnInputChangeListener);
        etAddName.addTextChangedListener(mOnInputChangeListener);
        etAddPhone.addTextChangedListener(mOnInputChangeListener);
    }

    @OnClick({R.id.iv_login_back, R.id.but_add_qr})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.but_add_qr:
                break;
        }
    }

    private OnInputChangeListener mOnInputChangeListener = new OnInputChangeListener() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!TextUtils.isEmpty(etAddType.getText().toString().trim()) &&
                    !TextUtils.isEmpty(etAddName.getText().toString().trim()) &&
                    !TextUtils.isEmpty(etAddPhone.getText().toString().trim())) {
                butAddQr.setEnabled(true);
                butAddQr.setBackgroundResource(R.drawable.yuanjiao_4a9012_button_shape);
            } else {
                butAddQr.setEnabled(false);
                butAddQr.setBackgroundResource(R.drawable.yuanjiao_bfc2cc_button_shape);
            }
        }
    };
}
