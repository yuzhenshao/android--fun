package com.mfzn.deepusesSer.activity.company;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.present.company.ApplyJoinPresent;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.utils.OnInputChangeListener;

import butterknife.BindView;
import butterknife.OnClick;

public class ApplyJoinActivity extends BaseMvpActivity<ApplyJoinPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_app_company)
    TextView tvAppCompany;
    @BindView(R.id.et_app_name)
    EditText etAppName;
    @BindView(R.id.et_app_reason)
    EditText etAppReason;
    @BindView(R.id.but_commit)
    Button butCommit;

    private String code;

    @Override
    public int getLayoutId() {
        return R.layout.activity_apply_join;
    }

    @Override
    public ApplyJoinPresent newP() {
        return new ApplyJoinPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("邀请加入");
        tvAppCompany.getPaint().setFakeBoldText(true);

        code = getIntent().getStringExtra(Constants.SCAN_CODE);

        etAppName.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(etAppName.getText().toString().trim())){
                    butCommit.setEnabled(true);
                    butCommit.setBackgroundResource(R.drawable.yuanjiao_4a9012_button_shape);
                }else {
                    butCommit.setEnabled(false);
                    butCommit.setBackgroundResource(R.drawable.yuanjiao_bfc2cc_button_shape);
                }
            }
        });
    }

    @OnClick({R.id.iv_login_back, R.id.but_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.but_commit:
                String name = etAppName.getText().toString().trim();
                String reason = etAppReason.getText().toString().trim();
                getP().applyJoin(code,name,reason);
                break;
        }
    }

    public void applyJoin() {
//        EventMsg eventMsg = new EventMsg();
//        eventMsg.setMsg(Constants.ESTABLSISH);
//        RxBus.getInstance().post(eventMsg);
        startActivity(new Intent(this, ApplySuccessActivity.class));
        finish();
    }
}
