package com.mfzn.deepusesSer.activity.news;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.adapter.news.TeamApplyAdapter;
import com.mfzn.deepusesSer.bass.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class TeamApplyActivity extends BaseActivity {

    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;

    @Override
    public int getLayoutId() {
        return R.layout.activity_team_apply;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("团队申请");

        TeamApplyAdapter adapter = new TeamApplyAdapter(this);
        listview.setAdapter(adapter);
        adapter.setOnItemClickLisenter(new TeamApplyAdapter.OnItemClickLisenter() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(TeamApplyActivity.this, QuerenRequestActivity.class);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.iv_login_back)
    public void onViewClicked() {
        finish();
    }
}
