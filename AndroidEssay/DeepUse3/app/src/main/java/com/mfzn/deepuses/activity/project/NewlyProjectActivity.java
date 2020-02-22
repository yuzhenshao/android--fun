package com.mfzn.deepuses.activity.project;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.utils.Constants;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.ProjectConfirmDialog;

import butterknife.BindView;
import butterknife.OnClick;

public class NewlyProjectActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.ll_newly_tips)
    LinearLayout llNewlyTips;
    @BindView(R.id.tv_newly_jiben)
    TextView tvNewlyJiben;
    @BindView(R.id.et_newly_proname)
    EditText etNewlyProname;
    @BindView(R.id.et_newly_address)
    EditText etNewlyAddress;
    @BindView(R.id.et_newly_name)
    EditText etNewlyName;
    @BindView(R.id.et_newly_phone)
    EditText etNewlyPhone;
    @BindView(R.id.et_newly_level)
    EditText etNewlyLevel;
    @BindView(R.id.tv_newly_ht)
    TextView tvNewlyHt;
    @BindView(R.id.et_newly_guwen)
    EditText etNewlyGuwen;
    @BindView(R.id.et_newly_money)
    EditText etNewlyMoney;
    @BindView(R.id.et_newly_time)
    EditText etNewlyTime;
    @BindView(R.id.but_commit)
    Button butCommit;

    private String level;

    @Override
    public int getLayoutId() {
        return R.layout.activity_newly_project;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        tvBassTitle.setText(getString(R.string.app_newly_project));
        tvNewlyJiben.getPaint().setFakeBoldText(true);
        tvNewlyHt.getPaint().setFakeBoldText(true);
    }

    @OnClick({R.id.iv_login_back, R.id.iv_newly_delete, R.id.et_newly_address, R.id.et_newly_level, R.id.et_newly_time, R.id.but_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.iv_newly_delete:
                llNewlyTips.setVisibility(View.GONE);
                break;
            case R.id.et_newly_address:
                break;
            case R.id.et_newly_level:
                Intent intent = new Intent(this, CustomerLevelActivity.class);
                intent.putExtra(Constants.CUSTOMER_LEVEL,level);
                startActivityForResult(intent, Constants.PROJECT_LEVEL);
                break;
            case R.id.et_newly_time:
                break;
            case R.id.but_commit:
                ProjectConfirmDialog normalAlertDialog = new ProjectConfirmDialog.Builder(this)
                        .setHeight(1f)  //屏幕高度*0.23
                        .setWidth(1f)  //屏幕宽度*0.65
                        .setCanceledOnTouchOutside(true)
                        .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<ProjectConfirmDialog>() {
                            @Override
                            public void clickLeftButton(ProjectConfirmDialog dialog, View view) {
                                dialog.dismiss();
                            }

                            @Override
                            public void clickRightButton(ProjectConfirmDialog dialog, View view) {
                            }
                        })
                        .build();
                normalAlertDialog.show();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(Constants.PROJECT_LEVEL == requestCode){
            if(data != null) {
                level = data.getStringExtra(Constants.CUSTOMER_LEVEL_RETURN);
                etNewlyLevel.setText(level);
            }
        }
    }
}
