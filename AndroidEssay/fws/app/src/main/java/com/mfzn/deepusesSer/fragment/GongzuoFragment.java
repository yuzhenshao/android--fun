package com.mfzn.deepusesSer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.activity.jiagou.ManageJiagouActivity;
import com.mfzn.deepusesSer.activity.jiagou.ZuzhiJiagouActivity;
import com.mfzn.deepusesSer.activity.myteam.TeamManageActivity;
import com.mfzn.deepusesSer.activityxm.FoundProjectActivity;
import com.mfzn.deepusesSer.adapter.fg.WorkRecommendAdapter;
import com.mfzn.deepusesSer.bass.BaseMvpFragment;
import com.mfzn.deepusesSer.model.home.JudgeLevelModel;
import com.mfzn.deepusesSer.present.fragment.GongzuoPresnet;
import com.mfzn.deepusesSer.view.MyListview;

import butterknife.BindView;
import butterknife.OnClick;


public class GongzuoFragment extends BaseMvpFragment<GongzuoPresnet> {

    @BindView(R.id.iv_work_icon)
    ImageView ivWorkIcon;
    @BindView(R.id.tv_work_name)
    TextView tvWorkName;
    @BindView(R.id.tv_work_company)
    TextView tvWorkCompany;
    @BindView(R.id.tv_work_kanban)
    TextView tvWorkKanban;
    @BindView(R.id.tv_kanban_project)
    TextView tvKanbanProject;
    @BindView(R.id.tv_kanban_money)
    TextView tvKanbanMoney;
    @BindView(R.id.tv_kanban_number)
    TextView tvKanbanNumber;
    @BindView(R.id.tv_work_shenpi)
    TextView tvWorkShenpi;
    @BindView(R.id.tv_work_guanli)
    TextView tvWorkGuanli;
    @BindView(R.id.work_listview)
    MyListview workListview;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_gongzuo;
    }

    @Override
    public GongzuoPresnet newP() {
        return new GongzuoPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvWorkName.getPaint().setFakeBoldText(true);
        tvWorkKanban.getPaint().setFakeBoldText(true);
        tvWorkShenpi.getPaint().setFakeBoldText(true);
        tvWorkGuanli.getPaint().setFakeBoldText(true);

        workListview.setFocusable(false);

        WorkRecommendAdapter adapter = new WorkRecommendAdapter(context);
        workListview.setAdapter(adapter);
    }

    @OnClick({R.id.tv_work_more, R.id.ll_shen_kb, R.id.ll_shen_pp, R.id.ll_guan_shezhi, R.id.ll_guan_jiagou,
            R.id.ll_kanban_project, R.id.ll_kanban_money, R.id.ll_kanban_number, R.id.ll_shen_cjxm, R.id.ll_shen_xmgl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_work_more:
                break;
            case R.id.ll_kanban_project:
                break;
            case R.id.ll_kanban_money:
                break;
            case R.id.ll_kanban_number:
                break;
            case R.id.ll_shen_kb:
                break;
            case R.id.ll_shen_pp:
                break;
            case R.id.ll_guan_shezhi:
                startActivity(new Intent(context, TeamManageActivity.class));
                break;
            case R.id.ll_guan_jiagou:
                getP().judgeLevel();
                break;
            case R.id.ll_shen_cjxm:
                startActivity(new Intent(getActivity(), FoundProjectActivity.class));
                break;
            case R.id.ll_shen_xmgl:
                break;
        }
    }

    public void judgeLevelSuccess(JudgeLevelModel model) {
        int roleID = model.getRoleID();
        if(roleID == 0 || roleID == 2) {
            startActivity(new Intent(context, ManageJiagouActivity.class));
        }else {
            startActivity(new Intent(context, ZuzhiJiagouActivity.class));
        }
    }
}
