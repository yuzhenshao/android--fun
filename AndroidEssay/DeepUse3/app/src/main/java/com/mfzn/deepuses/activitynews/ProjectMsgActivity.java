package com.mfzn.deepuses.activitynews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activityxm.shgd.GongdanDetailsActivity;
import com.mfzn.deepuses.adapter.news.TdxxMsgAdapter;
import com.mfzn.deepuses.adapter.news.XmxxMsgAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.xx.MsgTdxxModel;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.present.xx.ProjectMsgPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.WeixinTishiDialog1;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class ProjectMsgActivity extends BaseMvpActivity<ProjectMsgPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.pr_xrecycleview)
    XRecyclerContentLayout prXrecycleview;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;

    private XmxxMsgAdapter adapter;
    private int pages = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_project_msg;
    }

    @Override
    public ProjectMsgPresnet newP() {
        return new ProjectMsgPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("项目消息");
        adapter = new XmxxMsgAdapter(this);
        prXrecycleview.getRecyclerView().verticalLayoutManager(getContext());
        prXrecycleview.getRecyclerView().horizontalDivider(R.color.color_f5f7fa,R.dimen.app_10dp);//item之间的分割线
        prXrecycleview.getRecyclerView().setAdapter(adapter);
        prXrecycleview.getRecyclerView().setRefreshEnabled(true);
        prXrecycleview.getRecyclerView().setVerticalScrollBarEnabled(false);//隐藏右侧的线
        prXrecycleview.showLoading();

        adapter.setOnItemClickListener(new XmxxMsgAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data, int position) {
                if (adapter.getDataSource().get(position).getIsRead() == 0){
                    MsgTdxxModel.DataBean dataBean = adapter.getDataSource().get(position);
                    getP().setRead(String.valueOf(dataBean.getData_id()));
                }
            }
        });
        adapter.setOnItemLongClickListener(new XmxxMsgAdapter.OnRecyclerViewItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, String data, int position) {
                new WeixinTishiDialog1.Builder(ProjectMsgActivity.this)
                        .setHeight(1f)  //屏幕高度*0.23
                        .setWidth(1f)  //屏幕宽度*0.65
                        .setContentText("确定要删除该条消息吗?")
                        .setTitle("提示")
                        .setCanceledOnTouchOutside(false)
                        .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<WeixinTishiDialog1>() {
                            @Override
                            public void clickLeftButton(WeixinTishiDialog1 dialog, View view) {
                                dialog.dismiss();
                            }

                            @Override
                            public void clickRightButton(WeixinTishiDialog1 dialog, View view) {
                                dialog.dismiss();
                                MsgTdxxModel.DataBean dataBean = adapter.getDataSource().get(position);
                                getP().delRead(String.valueOf(dataBean.getData_id()));
                            }
                        })
                        .build()
                        .show();
            }
        });
        adapter.setOnItemLookClickListener(new XmxxMsgAdapter.OnItemLookClickListener() {
            @Override
            public void onItemLookClick(View view, String data, int position) {
                Intent intent = new Intent(ProjectMsgActivity.this, GongdanDetailsActivity.class);
                intent.putExtra(Constants.GD_DETAILS,data);
                startActivity(intent);
            }
        });
        prXrecycleview.getRecyclerView().setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                adapter.clearData();
                pages = 1;
                getP().getMsg(pages);
            }

            @Override
            public void onLoadMore(int page) {
                pages = page;
                getP().getMsg(pages);
            }
        });
        prXrecycleview.onRefresh();
    }

    @OnClick(R.id.iv_login_back)
    public void onViewClicked() {
        finish();
    }

    public void getMsgListSuccess(MsgTdxxModel model) {
        List<MsgTdxxModel.DataBean> data = model.getData();
        if(data != null && data.size() != 0) {
            if (pages == 1) {
                llEmpty.setVisibility(View.GONE);
                adapter.setData(data);
            } else {
                adapter.addData(data);
            }
            prXrecycleview.getRecyclerView().setPage(model.getCurrent_page(), model.getLast_page());
        }else {
            if (pages == 1) {
                llEmpty.setVisibility(View.VISIBLE);
            }
        }
    }

    public void setReadSuccess(HttpResult result) {
        showMessage(result.getMsg());
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.XIAOXI);
        RxBus.getInstance().post(eventMsg);
        pages = 1;
        adapter.clearData();
        getP().getMsg(pages);
    }

    public void delMsgSuccess(HttpResult result) {
        showMessage(result.getMsg());
        pages = 1;
        adapter.clearData();
        getP().getMsg(pages);
    }
}
