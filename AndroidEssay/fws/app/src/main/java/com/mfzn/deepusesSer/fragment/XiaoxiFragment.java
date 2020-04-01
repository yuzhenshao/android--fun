package com.mfzn.deepusesSer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.activityxm.shgd.WaitReceiveFromMsgActivity;
import com.mfzn.deepusesSer.adapter.msg.MessageAdapter;
import com.mfzn.deepusesSer.bass.BaseMvpFragment;
import com.mfzn.deepusesSer.model.msg.Message;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.present.fragment.XiaoxiPresnet;
import com.mfzn.deepusesSer.utils.Constants;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import q.rorbin.badgeview.QBadgeView;

public class XiaoxiFragment extends BaseMvpFragment<XiaoxiPresnet> {

    @BindView(R.id.ll_shgd_empty)
    LinearLayout llEmpty;
    @BindView(R.id.shgd_xrecycleview)
    XRecyclerContentLayout recycleview;

    private MessageAdapter adapter;
    private int pages = 1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_xiaoxi;
    }

    @Override
    public XiaoxiPresnet newP() {
        return new XiaoxiPresnet();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.clearData();
        pages = 1;
        getP().getMsg(pages);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        adapter = new MessageAdapter(getActivity());
        recycleview.getRecyclerView().verticalLayoutManager(getContext());
        recycleview.getRecyclerView().horizontalDivider(R.color.color_f5f7fa,R.dimen.app_10dp);//item之间的分割线
        recycleview.getRecyclerView().setAdapter(adapter);
        recycleview.getRecyclerView().setRefreshEnabled(true);
        recycleview.getRecyclerView().setVerticalScrollBarEnabled(false);//隐藏右侧的线
        recycleview.showLoading();

        adapter.setOnItemClickListener(new MessageAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data, int position) {
                Message.DataBeanX.DataBean dataBean = adapter.getDataSource().get(position);
                if (dataBean.getOrderInfo() != null && !TextUtils.isEmpty(dataBean.getOrderInfo().getOrderNo())){
                    Intent intent = new Intent(getActivity(), WaitReceiveFromMsgActivity.class);
                    intent.putExtra(Constants.SHOUHOU_DETAILS,dataBean.getOrderInfo());
                    intent.putExtra("msgId",String.valueOf(dataBean.getId()));
                    startActivity(intent);
                }
            }
        });

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

    public void getMsgListSuccess(Message model) {
        List<Message.DataBeanX.DataBean> data = model.getData().getData();
        new QBadgeView(getActivity()).bindTarget(getActivity().findViewById(R.id.iv_main_xiaoxi)).setBadgeNumber(model.getNotReadCount()).setShowShadow(false);

        if(data != null && data.size() != 0) {
            if (pages == 1) {
                llEmpty.setVisibility(View.GONE);
                adapter.setData(data);
            } else {
                adapter.addData(data);
            }
            recycleview.getRecyclerView().setPage(model.getData().getCurrent_page(), model.getData().getLast_page());
        }else {
            if (pages == 1) {
                llEmpty.setVisibility(View.VISIBLE);
            }
        }
    }

    public void setReadSuccess(HttpResult result) {
        pages = 1;
        adapter.clearData();
        getP().getMsg(pages);
    }

}
