package com.mfzn.deepuses.activitynews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.news.TeamApplyAdapter;
import com.mfzn.deepuses.adapter.project.ProjectNewsAdapter;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.xiangmu.ProjectNewsModel;
import com.mfzn.deepuses.model.xx.TeamApplyModel;
import com.mfzn.deepuses.present.xx.ProjectApplyPresent;
import com.mfzn.deepuses.present.xx.TeamApplyPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class TeamApplyActivity extends BaseMvpActivity<TeamApplyPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.te_xrecycleview)
    XRecyclerContentLayout teXrecycleview;
    @BindView(R.id.ll_te_empty)
    LinearLayout llTeEmpty;

    private TeamApplyAdapter adapter;
    private int pages = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_team_apply;
    }

    @Override
    public TeamApplyPresent newP() {
        return new TeamApplyPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_Team_Apply));

        adapter = new TeamApplyAdapter(this);
        teXrecycleview.getRecyclerView().verticalLayoutManager(this);
        teXrecycleview.getRecyclerView().horizontalDivider(R.color.color_f5f7fa,R.dimen.app_10dp);//item之间的分割线
        teXrecycleview.getRecyclerView().setAdapter(adapter);
        teXrecycleview.getRecyclerView().setRefreshEnabled(true);
        teXrecycleview.getRecyclerView().setVerticalScrollBarEnabled(false);//隐藏右侧的线
        teXrecycleview.showLoading();

        adapter.setOnYesItemClickListener(new TeamApplyAdapter.OnYesItemClickListener() {
            @Override
            public void onYesItemClick(View view, int position) {
                TeamApplyModel.DataBean dataBean=  adapter.getDataSource().get(position);
                getP().joinTeam(dataBean.getApplyID() + "",dataBean.getCompanyID()+"","1");
            }
        });
        adapter.setOnNoItemClickListener(new TeamApplyAdapter.OnNoItemClickListener() {
            @Override
            public void onNoItemClick(View view, int position) {
                TeamApplyModel.DataBean dataBean=  adapter.getDataSource().get(position);
                getP().joinTeam(dataBean.getApplyID() + "",dataBean.getCompanyID()+"","2");
            }
        });

        teXrecycleview.getRecyclerView().setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                pages = 1;
                getP().teamApply(pages);
            }

            @Override
            public void onLoadMore(int page) {
                pages = page;
                getP().teamApply(pages);
            }
        });
        teXrecycleview.onRefresh();
    }

    @OnClick(R.id.iv_login_back)
    public void onViewClicked() {
        finish();
    }

    public void projectNewsSuccess(TeamApplyModel model) {
        List<TeamApplyModel.DataBean> data = model.getData();
        if(data != null && data.size() != 0) {
            if (pages == 1) {
                llTeEmpty.setVisibility(View.GONE);
                teXrecycleview.setVisibility(View.VISIBLE);
                adapter.setData(data);
            } else {
                adapter.addData(data);
            }
            teXrecycleview.getRecyclerView().setPage(model.getCurrent_page(), model.getLast_page());
        }else {
            if (pages == 1) {
                llTeEmpty.setVisibility(View.VISIBLE);
                teXrecycleview.setVisibility(View.GONE);
            }
        }
    }

    public void joinProjectSuccess() {
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.XIAOXI);
        RxBus.getInstance().post(eventMsg);
        pages = 1;
        getP().teamApply(pages);
    }
}
