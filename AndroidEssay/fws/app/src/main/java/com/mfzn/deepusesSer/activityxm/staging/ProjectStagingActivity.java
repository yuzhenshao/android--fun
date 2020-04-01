package com.mfzn.deepusesSer.activityxm.staging;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.activityxm.kf.CustomSettingActivity;
import com.mfzn.deepusesSer.activityxm.shgd.AddWorkorderActivity;
import com.mfzn.deepusesSer.activityxm.shgd.ShouhuSettingActivity;
import com.mfzn.deepusesSer.activityxm.shgd.WorkorderListActivity;
import com.mfzn.deepusesSer.activityxm.shhf.AddReturnVisitActivity;
import com.mfzn.deepusesSer.adapter.xiangmu.ProjectStagingAdapter;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.model.xiangmu.ProjectStagingModel;
import com.mfzn.deepusesSer.model.xiangmu.StagingListModel;
import com.mfzn.deepusesSer.model.xiangmu.XiangmuModel;
import com.mfzn.deepusesSer.present.foundxm.ProjectStagingPresnet;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.view.MyRecyclerView;
import com.mfzn.deepusesSer.view.NoScrollGridLayoutManager;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ProjectStagingActivity extends BaseMvpActivity<ProjectStagingPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_stag_time_bold1)
    TextView tvStagTimeBold1;
    @BindView(R.id.tv_stag_time)
    TextView tvStagTime;
    @BindView(R.id.tv_stag_gzbx)
    TextView tvStagGzbx;
    @BindView(R.id.tv_stag_wbsj)
    TextView tvStagWbsj;
    @BindView(R.id.tv_stag_hfjl)
    TextView tvStagHfjl;
    @BindView(R.id.tv_stag_shgl)
    TextView tvStagShgl;
    @BindView(R.id.tv_stag_xmcy)
    TextView tvStagXmcy;
    @BindView(R.id.stag_recyleview)
    MyRecyclerView stagRecyleview;

    private String pro_uid;

    @Override
    public int getLayoutId() {
        return R.layout.activity_project_staging;
    }

    @Override
    public ProjectStagingPresnet newP() {
        return new ProjectStagingPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        XiangmuModel.DataBean dataBean = (XiangmuModel.DataBean) getIntent().getSerializableExtra(Constants.WORK_ORDER);

        tvBassTitle.setText(dataBean.getPro_name());
        tvStagTime.setText(dataBean.getStart_time() + "~" + dataBean.getEnd_time());

        tvStagTimeBold1.getPaint().setFakeBoldText(true);
        tvStagGzbx.getPaint().setFakeBoldText(true);
        tvStagWbsj.getPaint().setFakeBoldText(true);
        tvStagHfjl.getPaint().setFakeBoldText(true);
        tvStagShgl.getPaint().setFakeBoldText(true);
        tvStagXmcy.getPaint().setFakeBoldText(true);

        pro_uid = dataBean.getData_id() + "";
        getP().projectStaging(pro_uid);
        getP().stagingList(pro_uid);

        NoScrollGridLayoutManager appLayoutManager = new NoScrollGridLayoutManager(this,
                4, GridLayoutManager.VERTICAL, false);
        stagRecyleview.setLayoutManager(appLayoutManager);
    }

    @OnClick({R.id.iv_login_back, R.id.ll_stag_shgd, R.id.ll_stag_xjgd, R.id.ll_stag_shsz,
            R.id.ll_stag_shhf, R.id.ll_stag_shkf, R.id.ll_stag_shda})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_stag_shgd:
                Intent intent = new Intent(this, WorkorderListActivity.class);
                intent.putExtra(Constants.SHOUHOU_PROID,pro_uid);
                startActivity(intent);
                break;
            case R.id.ll_stag_xjgd:
                startActivity(new Intent(this, AddWorkorderActivity.class));
                break;
            case R.id.ll_stag_shsz:
                startActivity(new Intent(this, ShouhuSettingActivity.class));
                break;
            case R.id.ll_stag_shhf:
                startActivity(new Intent(this, AddReturnVisitActivity.class));
                break;
            case R.id.ll_stag_shkf:
                startActivity(new Intent(this, CustomSettingActivity.class));
                break;
            case R.id.ll_stag_shda:
                break;
        }
    }

    public void projectStagingSuccess(ProjectStagingModel model) {
        tvStagGzbx.setText(model.getGzNum() + "");
        tvStagWbsj.setText(model.getWbNum() + "");
        tvStagHfjl.setText(model.getHfNum() + "");
    }

    public void stagingListSuccess(StagingListModel model) {
        List<StagingListModel.OthersBean> others = model.getOthers();
        ProjectStagingAdapter adapter= new ProjectStagingAdapter(this,model.getOthers());
        stagRecyleview.setAdapter(adapter);
    }
}
