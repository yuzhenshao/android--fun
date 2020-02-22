package com.mfzn.deepuses.activity.company;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepuses.MainActivity;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.login.LoginActivity;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.myTeam.TeamManageModel;
import com.mfzn.deepuses.model.xiangmu.ProjectDetailsModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.present.company.ApplyJoinPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.OnInputChangeListener;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.UserHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;
import io.reactivex.functions.Consumer;

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

    private String proid;

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
        tvBassTitle.setText("申请加入");
        tvAppCompany.getPaint().setFakeBoldText(true);

        proid = getIntent().getStringExtra(Constants.SCAN_CODE);

        getP().teamManage(proid);

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
                if(TextUtils.isEmpty(reason)) {
                    getP().applyJoin(proid,name,"");
                }else {
                    getP().applyJoin(proid,name,reason);
                }
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

//    public void xmDetails(ProjectDetailsModel msg) {
//        tvAppCompany.setText(msg.getPro_name());
//    }

    public void companyHomepage(TeamManageModel model) {
        tvAppCompany.setText(model.getCompanyName());
    }
}
