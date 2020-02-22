package com.mfzn.deepuses.activity.xmgl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.project.ProjectDetailsActivity;
import com.mfzn.deepuses.activity.project.ProjectManageActivity;
import com.mfzn.deepuses.activityxm.SearchProjectActivity;
import com.mfzn.deepuses.activityxm.staging.ProjectStagingActivity;
import com.mfzn.deepuses.adapter.project.ProjectManageAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.present.xmgl.MyProjectPresent;
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

public class MyProjectActivity extends BaseMvpActivity<MyProjectPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.ll_bass_search)
    LinearLayout llBassSearch;
    @BindView(R.id.my_xrecycleview)
    XRecyclerContentLayout myXrecycleview;
    @BindView(R.id.ll_my_empty)
    LinearLayout llMyEmpty;

    private ProjectManageAdapter adapter;
    private int pages = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_project;
    }

    @Override
    public MyProjectPresent newP() {
        return new MyProjectPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("我的项目");
        llBassSearch.setVisibility(View.VISIBLE);

        adapter = new ProjectManageAdapter(getContext());
        myXrecycleview.getRecyclerView().verticalLayoutManager(getContext());
        myXrecycleview.getRecyclerView().horizontalDivider(R.color.color_f5f7fa,R.dimen.app_10dp);//item之间的分割线
        myXrecycleview.getRecyclerView().setAdapter(adapter);
        myXrecycleview.getRecyclerView().setRefreshEnabled(true);
        myXrecycleview.getRecyclerView().setVerticalScrollBarEnabled(false);//隐藏右侧的线
        myXrecycleview.showLoading();

        adapter.setOnItemClickListener(new ProjectManageAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data, int position) {
                XiangmuModel.DataBean dataBean = adapter.getDataSource().get(position);
                Intent intent = new Intent(MyProjectActivity.this, ProjectStagingActivity.class);
                intent.putExtra(Constants.WORK_ORDER,dataBean);
                startActivity(intent);
            }
        });

        myXrecycleview.getRecyclerView().setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
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
        myXrecycleview.onRefresh();

        RxBus.getInstance().toObservable().map(new Function<Object, EventMsg>() {
            @Override
            public EventMsg apply(Object o) throws Exception {
                return (EventMsg) o;
            }
        }).subscribe(new Consumer<EventMsg>() {
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
                startActivity(new Intent(this, SearchProjectActivity.class));
                break;
        }
    }

    public void xiangmuListSuccess(XiangmuModel model) {
        List<XiangmuModel.DataBean> data = model.getData();
        if(data != null && data.size() != 0) {
            if (pages == 1) {
                llMyEmpty.setVisibility(View.GONE);
                myXrecycleview.setVisibility(View.VISIBLE);
                adapter.setData(data);
            } else {
                adapter.addData(data);
            }
            myXrecycleview.getRecyclerView().setPage(model.getCurrent_page(), model.getLast_page());
        }else {
            if (pages == 1) {
                llMyEmpty.setVisibility(View.VISIBLE);
                myXrecycleview.setVisibility(View.GONE);
            }
        }
    }
}
