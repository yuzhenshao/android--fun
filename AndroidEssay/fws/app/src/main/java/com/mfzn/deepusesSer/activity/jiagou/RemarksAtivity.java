package com.mfzn.deepusesSer.activity.jiagou;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.present.jiagou.RemarksPresent;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class RemarksAtivity extends BaseMvpActivity<RemarksPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.et_rem_content)
    EditText etRemContent;
    @BindView(R.id.tv_bass_content)
    TextView tvBassContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_remarks_ativity;
    }

    @Override
    public RemarksPresent newP() {
        return new RemarksPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_remarks));
        tvBassContent.setVisibility(View.VISIBLE);
        tvBassContent.setText(getString(R.string.right_preserve));
    }

    @OnClick({R.id.iv_login_back, R.id.tv_bass_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_bass_content:
                String trim = etRemContent.getText().toString().trim();
                if(TextUtils.isEmpty(trim)) {
                    ToastUtil.showToast(this,getString(R.string.zuzhi_remarks));
                }else {
                    String uid = getIntent().getStringExtra(Constants.REMARKS_UID);
                    getP().remarks(trim,uid);
                }
                break;
        }
    }

    public void remarksSuccess() {
        ToastUtil.showToast(this, "设置成功");
        finish();
    }
}
