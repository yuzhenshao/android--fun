package com.mfzn.deepuses.activityxm.kf;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activityxm.shgd.EnginerListActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.present.xmkefu.AddCustomPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.OnInputChangeListener;
import com.mfzn.deepuses.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    private String typeID;
    private int positions = -1;

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

    @OnClick({R.id.iv_login_back, R.id.et_add_type, R.id.but_add_qr})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.et_add_type:
                Intent intent = new Intent(this, CustomTypeActivity.class);
                intent.putExtra(Constants.KEFU_TYPE_POSI,positions);
                startActivityForResult(intent, Constants.KEFU_TYPE);
                break;
            case R.id.but_add_qr:
                String name = etAddName.getText().toString().trim();
                String phone = etAddPhone.getText().toString().trim();
                if (phone.length() == 11){
                    getP().addCustom(typeID,name,phone);
                }else {
                    ToastUtil.showToast(this,"请输入正确手机号");
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.KEFU_TYPE == requestCode) {
            if (data != null) {
                positions = data.getIntExtra(Constants.KEFU_TYPE_POSI,-1);
                String name = data.getStringExtra(Constants.KEFU_TYPE_NAME);
                typeID = data.getStringExtra(Constants.KEFU_TYPE_ID);
                etAddType.setText(name);
            }
        }
    }

    public void addCustomSuccess(String models) {
        ToastUtil.showToast(this,models);
        Intent intent = new Intent();
        intent.putExtra("bvbv", "nmnm");
        setResult(Constants.ADD_KEFU,intent);
        finish();
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
