package com.mfzn.deepuses.activity.news;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.news.SelectBumenAdapter;
import com.mfzn.deepuses.bass.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectBumenActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.sel_listview)
    ListView selListview;
    @BindView(R.id.tv_se_bumen)
    TextView tvSeBumen;

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_bumen;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("麦麸智能科技（常州）有");

        SelectBumenAdapter adapter = new SelectBumenAdapter(this);
        selListview.setAdapter(adapter);
    }

    @OnClick(R.id.iv_login_back)
    public void onViewClicked() {
        finish();
    }
}
