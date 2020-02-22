package com.mfzn.deepuses.activity.project;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.project.TemplateSelectAdapter;
import com.mfzn.deepuses.bass.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TemplateSelectActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.et_te_search)
    EditText etTeSearch;
    @BindView(R.id.te_listview)
    ListView teListview;
    @BindView(R.id.ll_bass_kefu)
    LinearLayout llBassKefu;

    @Override
    public int getLayoutId() {
        return R.layout.activity_template_select;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_template_select));
        llBassKefu.setVisibility(View.VISIBLE);

        TemplateSelectAdapter adapter = new TemplateSelectAdapter(context);
        teListview.setAdapter(adapter);

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
