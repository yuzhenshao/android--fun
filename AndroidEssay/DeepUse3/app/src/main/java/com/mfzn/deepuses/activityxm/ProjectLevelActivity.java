package com.mfzn.deepuses.activityxm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.xiangmu.ProjectLevelAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.xiangmu.ProjectLevelModel;
import com.mfzn.deepuses.present.foundxm.ProjectLevelPresnet;
import com.mfzn.deepuses.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProjectLevelActivity extends BaseMvpActivity<ProjectLevelPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.dj_listview)
    ListView djListview;

    private int types = -1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_project_level;
    }

    @Override
    public ProjectLevelPresnet newP() {
        return new ProjectLevelPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_project_level));

        types = getIntent().getIntExtra(Constants.PROJECT_LEVEL_POSITION, -1);

        getP().projectLevel();
    }

    @OnClick(R.id.iv_login_back)
    public void onViewClicked() {
        finish();
    }

    public void projectLevelSuccess(List<ProjectLevelModel> models) {
        ProjectLevelAdapter adapter = new ProjectLevelAdapter(this,models,types);
        djListview.setAdapter(adapter);

        djListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra(Constants.PROJECT_LEVEL_POSITION, position);
                intent.putExtra(Constants.PROJECT_LEVEL_ID, models.get(position).getData_id());
                intent.putExtra(Constants.PROJECT_LEVEL_NAME, models.get(position).getLevelName());
                setResult(Constants.FOUND_PROJECT_LEVEL,intent);
                finish();
            }
        });
    }
}
