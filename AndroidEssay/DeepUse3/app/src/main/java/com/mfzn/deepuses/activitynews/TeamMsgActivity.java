package com.mfzn.deepuses.activitynews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.khgl.MyShnewActivity;
import com.mfzn.deepuses.activitymy.setting.SettingActivity;
import com.mfzn.deepuses.activityxm.SelectPersonActivity;
import com.mfzn.deepuses.activityxm.shgd.GongdanDetailsActivity;
import com.mfzn.deepuses.adapter.news.TdxxMsgAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.xx.MsgTdxxModel;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.present.xx.MsgTdxxListPresnet;
import com.mfzn.deepuses.utils.CleanDataUtils;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.WeixinTishiDialog1;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class TeamMsgActivity extends BaseMvpActivity<MsgTdxxListPresnet> {
    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    @BindView(R.id.zx_xrecycleview)
    XRecyclerContentLayout recycleview;

    private TdxxMsgAdapter adapter;
    private int pages = 1;
    private int positions;

    @Override
    public int getLayoutId() {
        return R.layout.activity_team_msg;
    }

    @Override
    public MsgTdxxListPresnet newP() {
        return new MsgTdxxListPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("团队消息");
        adapter = new TdxxMsgAdapter(this);
        recycleview.getRecyclerView().verticalLayoutManager(getContext());
        recycleview.getRecyclerView().horizontalDivider(R.color.color_f5f7fa,R.dimen.app_10dp);//item之间的分割线
        recycleview.getRecyclerView().setAdapter(adapter);
        recycleview.getRecyclerView().setRefreshEnabled(true);
        recycleview.getRecyclerView().setVerticalScrollBarEnabled(false);//隐藏右侧的线
        recycleview.showLoading();

        adapter.setOnItemClickListener(new TdxxMsgAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data, int position) {
                positions = position;
                int pageType = adapter.getDataSource().get(position).getPageType();
                if(pageType == 1) {
                    Intent intent = new Intent(TeamMsgActivity.this, GongdanDetailsActivity.class);
                    intent.putExtra(Constants.GD_DETAILS,data);
                    startActivity(intent);
                    startActivityForResult(intent, Constants.LOOK_NEWS);
                }else if(pageType == 3) {
                    Intent intent = new Intent(TeamMsgActivity.this, MyShnewActivity.class);
                    intent.putExtra(Constants.GD_DETAILS,adapter.getDataSource().get(position).getExtra().getShareRecordID());
                    intent.putExtra(Constants.GD_DETAILS_ID,String.valueOf(adapter.getDataSource().get(position).getExtra().getCompanyID()));
                    startActivityForResult(intent, Constants.LOOK_NEWS);
                }else {
                    if (adapter.getDataSource().get(position).getIsRead() == 0){
                        MsgTdxxModel.DataBean dataBean = adapter.getDataSource().get(position);
                        getP().setRead(String.valueOf(dataBean.getData_id()));
                    }
                }
            }
        });
        adapter.setOnItemLongClickListener(new TdxxMsgAdapter.OnRecyclerViewItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, String data, int position) {
                new WeixinTishiDialog1.Builder(TeamMsgActivity.this)
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
//        adapter.setOnItemLookClickListener(new TdxxMsgAdapter.OnItemLookClickListener() {
//            @Override
//            public void onItemLookClick(View view, String data, int position) {
//                int pageType = adapter.getDataSource().get(position).getPageType();
//                if(pageType == 1) {
//                    Intent intent = new Intent(TeamMsgActivity.this, GongdanDetailsActivity.class);
//                    intent.putExtra(Constants.GD_DETAILS,data);
//                    startActivity(intent);
//                }else if(pageType == 3) {
//                    Intent intent = new Intent(TeamMsgActivity.this, MyShnewActivity.class);
//                    intent.putExtra(Constants.GD_DETAILS,adapter.getDataSource().get(position).getExtra().getShareRecordID());
//                    intent.putExtra(Constants.GD_DETAILS_ID,String.valueOf(adapter.getDataSource().get(position).getExtra().getCompanyID()));
//                    startActivity(intent);
//                }
//            }
//        });
        recycleview.getRecyclerView().setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {

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
        recycleview.onRefresh();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.LOOK_NEWS == requestCode) {
            if (data != null) {
                if (adapter.getDataSource().get(positions).getIsRead() == 0){
                    MsgTdxxModel.DataBean dataBean = adapter.getDataSource().get(positions);
                    getP().setRead(String.valueOf(dataBean.getData_id()));
                }
            }
        }
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
            recycleview.getRecyclerView().setPage(model.getCurrent_page(), model.getLast_page());
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

    @OnClick(R.id.iv_login_back)
    public void onViewClicked() {
        finish();
    }
}
