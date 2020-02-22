package com.mfzn.deepuses.activity.khgl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.khgl.MyTaskAdapter;
import com.mfzn.deepuses.adapter.khgl.WholeCustomerAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.khgl.MyTaskModel;
import com.mfzn.deepuses.present.customer.MyTaskPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class MyTaskActivity extends BaseMvpActivity<MyTaskPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.task_xrecycleview)
    XRecyclerContentLayout taskXrecycleview;
    @BindView(R.id.ll_task_rc)
    LinearLayout llTaskRc;
    @BindView(R.id.ll_task_empty)
    LinearLayout llTaskEmpty;

    private MyTaskAdapter adapter;
    private int pages = 1;
    private int positions;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_task;
    }

    @Override
    public MyTaskPresnet newP() {
        return new MyTaskPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("我的任务");

        adapter = new MyTaskAdapter(getContext());
        taskXrecycleview.getRecyclerView().verticalLayoutManager(getContext());
        taskXrecycleview.getRecyclerView().horizontalDivider(R.color.color_f5f7fa,R.dimen.app_10dp);//item之间的分割线
        taskXrecycleview.getRecyclerView().setAdapter(adapter);
        taskXrecycleview.getRecyclerView().setRefreshEnabled(true);
        taskXrecycleview.getRecyclerView().setVerticalScrollBarEnabled(false);//隐藏右侧的线
        taskXrecycleview.showLoading();

        adapter.setOnItemClickListener(new MyTaskAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                MyTaskModel.DataBean dataBean = adapter.getDataSource().get(position);
                Intent intent = new Intent(MyTaskActivity.this, EditTaskActivity.class);
                intent.putExtra(Constants.EDIT_TASK,dataBean);
                startActivityForResult(intent, Constants.EDIT_TASKS);
            }
        });
        adapter.setOnDelItemClickListener(new MyTaskAdapter.OnDelItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                positions = position;
                getP().delTask(String.valueOf(adapter.getDataSource().get(position).getData_id()));
            }
        });

        taskXrecycleview.getRecyclerView().setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                pages = 1;
                getP().myTask(pages);
            }

            @Override
            public void onLoadMore(int page) {
                pages = page;
                getP().myTask(pages);
            }
        });
        taskXrecycleview.onRefresh();
    }

    @OnClick({R.id.iv_login_back, R.id.iv_task_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.iv_task_add:
                startActivityForResult(new Intent(this, AddTaskActivity.class), Constants.ADD_TASK);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.ADD_TASK == requestCode) {
            if (data != null) {
                pages = 1;
                getP().myTask(pages);
            }
        }else if (Constants.EDIT_TASKS == requestCode) {
            if (data != null) {
                pages = 1;
                getP().myTask(pages);
            }
        }
    }

    public void myTaskSuccess(MyTaskModel models) {
        List<MyTaskModel.DataBean> data = models.getData();
        if(data != null && data.size() != 0) {
            if (pages == 1) {
                llTaskEmpty.setVisibility(View.GONE);
                llTaskRc.setVisibility(View.VISIBLE);
                adapter.setData(data);
            } else {
                adapter.addData(data);
            }
            taskXrecycleview.getRecyclerView().setPage(models.getCurrent_page(), models.getLast_page());
        }else {
            if (pages == 1) {
                llTaskEmpty.setVisibility(View.VISIBLE);
                llTaskRc.setVisibility(View.GONE);
            }
        }
    }

    public void delTaskSuccess() {
        ToastUtil.showToast(this,"删除成功");
        adapter.getDataSource().remove(positions);
        adapter.notifyDataSetChanged();
    }
}
