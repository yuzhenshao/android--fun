package com.mfzn.deepuses.activity.company;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.xiangmu.ProjectDetailsModel;
import com.mfzn.deepuses.present.company.ApplyJoinPresent;
import com.mfzn.deepuses.present.company.JoinXiangmuPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JoinXiangmuActivity extends BaseMvpActivity<JoinXiangmuPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_app_company)
    TextView tvAppCompany;
    @BindView(R.id.et_app_reason)
    EditText etAppReason;

    private String proid;

    @Override
    public int getLayoutId() {
        return R.layout.activity_join_xiangmu;
    }

    @Override
    public JoinXiangmuPresent newP() {
        return new JoinXiangmuPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_join_project));
        tvAppCompany.getPaint().setFakeBoldText(true);

        proid = getIntent().getStringExtra(Constants.SCAN_CODE);
        getP().xmDetails(proid);
    }

    @OnClick({R.id.iv_login_back, R.id.but_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.but_commit:
                String trim = etAppReason.getText().toString().trim();
                if(TextUtils.isEmpty(trim)) {
                    getP().joinXiangmu(proid,"1","");
                }else {
                    getP().joinXiangmu(proid,"1",trim);
                }
                break;
        }
    }

    public void joinXiangmu(String msg) {
        ToastUtil.showToast(this,msg);
        startActivity(new Intent(this, ApplySuccessActivity.class));
        finish();
    }

    public void xmDetails(ProjectDetailsModel msg) {
        tvAppCompany.setText(msg.getPro_name());
    }
}
