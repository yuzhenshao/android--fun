package com.mfzn.deepuses.activitynews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.project.ProjectNewsAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.xiangmu.ProjectNewsModel;
import com.mfzn.deepuses.present.xx.ProjectApplyPresent;
import com.mfzn.deepuses.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class ProjectApplyActivity extends BaseMvpActivity<ProjectApplyPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.ll_pr_empty)
    LinearLayout llPrEmpty;
    @BindView(R.id.pr_xrecycleview)
    XRecyclerContentLayout prXrecycleview;

    private ProjectNewsAdapter adapter;
    private int pages = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_project_apply;
    }

    @Override
    public ProjectApplyPresent newP() {
        return new ProjectApplyPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_project_news));

        adapter = new ProjectNewsAdapter(getContext());
        prXrecycleview.getRecyclerView().verticalLayoutManager(getContext());
        prXrecycleview.getRecyclerView().horizontalDivider(R.color.color_f5f7fa,R.dimen.app_10dp);//item之间的分割线
        prXrecycleview.getRecyclerView().setAdapter(adapter);
        prXrecycleview.getRecyclerView().setRefreshEnabled(true);
        prXrecycleview.getRecyclerView().setVerticalScrollBarEnabled(false);//隐藏右侧的线
        prXrecycleview.showLoading();

        adapter.setOnYesItemClickListener(new ProjectNewsAdapter.OnYesItemClickListener() {
            @Override
            public void onYesItemClick(View view, int position) {
                getP().joinProject(adapter.getDataSource().get(position).getData_id() + "","1","同意");
            }
        });
        adapter.setOnNoItemClickListener(new ProjectNewsAdapter.OnNoItemClickListener() {
            @Override
            public void onNoItemClick(View view, int position) {
                getP().joinProject(adapter.getDataSource().get(position).getData_id() + "","2","拒绝");
            }
        });

        prXrecycleview.getRecyclerView().setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                pages = 1;
                getP().projectNews(pages);
            }

            @Override
            public void onLoadMore(int page) {
                pages = page;
                getP().projectNews(pages);
            }
        });
        prXrecycleview.onRefresh();
    }

    @OnClick(R.id.iv_login_back)
    public void onViewClicked() {
        finish();
    }

    public void projectNewsSuccess(ProjectNewsModel model) {
        List<ProjectNewsModel.DataBean> data = model.getData();
        if(data != null && data.size() != 0) {
            if (pages == 1) {
                llPrEmpty.setVisibility(View.GONE);
                prXrecycleview.setVisibility(View.VISIBLE);
                adapter.setData(data);
            } else {
                adapter.addData(data);
            }
            prXrecycleview.getRecyclerView().setPage(model.getCurrent_page(), model.getLast_page());
        }else {
            if (pages == 1) {
                llPrEmpty.setVisibility(View.VISIBLE);
                prXrecycleview.setVisibility(View.GONE);
            }
        }
    }

    public void joinProjectSuccess() {
        pages = 1;
        getP().projectNews(pages);
    }
}
