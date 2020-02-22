package com.mfzn.deepuses.activity.project;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProjectJoinActivity extends BaseActivity {


    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_pjo_name)
    TextView tvPjoName;
    @BindView(R.id.tv_pjo_proname)
    TextView tvPjoProname;

    @Override
    public int getLayoutId() {
        return R.layout.activity_project_join;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_project_join));
        tvPjoProname.getPaint().setFakeBoldText(true);
    }

    @OnClick({R.id.iv_login_back, R.id.but_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.but_add:
                finish();
                break;
        }
    }
}
