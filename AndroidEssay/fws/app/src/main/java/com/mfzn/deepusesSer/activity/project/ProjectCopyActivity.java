package com.mfzn.deepusesSer.activity.project;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.adapter.project.ProjectCopyAdapter;
import com.mfzn.deepusesSer.bass.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ProjectCopyActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.ll_bass_kefu)
    LinearLayout llBassKefu;
    @BindView(R.id.et_pr_search)
    EditText etPrSearch;
    @BindView(R.id.pr_listview)
    ListView prListview;

    @Override
    public int getLayoutId() {
        return R.layout.activity_project_copy;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_project_copy));
        llBassKefu.setVisibility(View.VISIBLE);

        ProjectCopyAdapter adapter = new ProjectCopyAdapter(context);
        prListview.setAdapter(adapter);
    }

    @OnClick({R.id.iv_login_back, R.id.ll_bass_kefu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_bass_kefu:
                break;
        }
    }
}
