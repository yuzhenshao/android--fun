package com.mfzn.deepuses.activity.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activityxm.SearchProjectActivity;
import com.mfzn.deepuses.activityxm.staging.ProjectStagingActivity;
import com.mfzn.deepuses.adapter.fg.XiangmuAdapter;
import com.mfzn.deepuses.adapter.project.ProjectManageAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.present.project.ProjectManagePresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class ProjectManageActivity extends BaseMvpActivity<ProjectManagePresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.pr_xrecycleview)
    XRecyclerContentLayout prXrecycleview;
    @BindView(R.id.ll_pr_empty)
    LinearLayout llPrEmpty;
    @BindView(R.id.ll_bass_search)
    LinearLayout ll_bass_search;

    private ProjectManageAdapter adapter;
    private int pages = 1;
    private boolean isSelected;

    @Override
    public int getLayoutId() {
        return R.layout.activity_project_manage;
    }

    @Override
    public ProjectManagePresent newP() {
        return new ProjectManagePresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        isSelected = getIntent().getBooleanExtra(ParameterConstant.IS_SELECTED, false);
        tvBassTitle.setText(isSelected ? "项目选择" : getString(R.string.app_project_manage));
        ll_bass_search.setVisibility(isSelected ? View.GONE : View.VISIBLE);

        adapter = new ProjectManageAdapter(getContext());
        prXrecycleview.getRecyclerView().verticalLayoutManager(getContext());
        prXrecycleview.getRecyclerView().horizontalDivider(R.color.color_f5f7fa, R.dimen.app_10dp);//item之间的分割线
        prXrecycleview.getRecyclerView().setAdapter(adapter);
        prXrecycleview.getRecyclerView().setRefreshEnabled(true);
        prXrecycleview.getRecyclerView().setVerticalScrollBarEnabled(false);//隐藏右侧的线
        prXrecycleview.showLoading();

        adapter.setOnItemClickListener(new ProjectManageAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data, int position) {
                XiangmuModel.DataBean dataBean = adapter.getDataSource().get(position);
                if (isSelected) {
                    Intent projectIntent = new Intent();
                    projectIntent.putExtra("Id", dataBean.getData_id());
                    projectIntent.putExtra("Name", dataBean.getProName());
                    setResult(Activity.RESULT_OK, projectIntent);
                    finish();
                } else {
                    Intent intent = new Intent(ProjectManageActivity.this, ProjectDetailsActivity.class);
                    intent.putExtra(Constants.WORK_ORDER, dataBean);
                    startActivity(intent);
                }
            }
        });

        prXrecycleview.getRecyclerView().

                setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
                    @Override
                    public void onRefresh() {
                        pages = 1;
                        getP().xiangmuList(1);
                    }

                    @Override
                    public void onLoadMore(int page) {
                        pages = page;
                        getP().xiangmuList(page);
                    }
                });
        prXrecycleview.onRefresh();

        RxBus.getInstance().

                toObservable().

                map(new Function<Object, EventMsg>() {
                    @Override
                    public EventMsg apply(Object o) throws Exception {
                        return (EventMsg) o;
                    }
                }).

                subscribe(new Consumer<EventMsg>() {
                    @Override
                    public void accept(EventMsg eventMsg) throws Exception {
                        if (eventMsg != null) {
                            if (eventMsg.getMsg().equals(Constants.FOUNDPROJECT)) {
                                pages = 1;
                                getP().xiangmuList(1);
                            }
                        }
                    }
                });
    }

    @OnClick({R.id.iv_login_back, R.id.ll_bass_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_bass_search:
                startActivity(new Intent(this, ProjectSearchActivity.class));
                break;
        }
    }

    public void xiangmuListSuccess(XiangmuModel model) {
        List<XiangmuModel.DataBean> data = model.getData();
        if (data != null && data.size() != 0) {
            if (pages == 1) {
                llPrEmpty.setVisibility(View.GONE);
                prXrecycleview.setVisibility(View.VISIBLE);
                adapter.setData(data);
            } else {
                adapter.addData(data);
            }
            prXrecycleview.getRecyclerView().setPage(model.getCurrent_page(), model.getLast_page());
        } else {
            if (pages == 1) {
                llPrEmpty.setVisibility(View.VISIBLE);
                prXrecycleview.setVisibility(View.GONE);
            }
        }
    }
}
