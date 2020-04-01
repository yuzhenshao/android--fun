package com.mfzn.deepusesSer.fragment.xm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.activityxm.FoundProjectActivity;
import com.mfzn.deepusesSer.activityxm.staging.ProjectStagingActivity;
import com.mfzn.deepusesSer.adapter.fg.XiangmuAdapter;
import com.mfzn.deepusesSer.bass.BaseMvpFragment;
import com.mfzn.deepusesSer.model.xiangmu.XiangmuModel;
import com.mfzn.deepusesSer.present.fragment.ShouhouXmPresnet;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.utils.EventMsg;
import com.mfzn.deepusesSer.utils.RxBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


public class ShouhouXmFragment extends BaseMvpFragment<ShouhouXmPresnet> {

    @BindView(R.id.xm_xrecycleview)
    XRecyclerContentLayout xmXrecycleview;
    @BindView(R.id.ll_xm_empty)
    LinearLayout llXmEmpty;
    @BindView(R.id.iv_xm_add)
    ImageView imXmAdd;

    private XiangmuAdapter adapter;
    private int pages = 1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shouhou_xm;
    }

    @Override
    public ShouhouXmPresnet newP() {
        return new ShouhouXmPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        adapter = new XiangmuAdapter(getContext());
        xmXrecycleview.getRecyclerView().verticalLayoutManager(getContext());
        xmXrecycleview.getRecyclerView().horizontalDivider(R.color.color_f5f7fa,R.dimen.app_10dp);//item之间的分割线
        xmXrecycleview.getRecyclerView().setAdapter(adapter);
        xmXrecycleview.getRecyclerView().setRefreshEnabled(true);
        xmXrecycleview.getRecyclerView().setVerticalScrollBarEnabled(false);//隐藏右侧的线
        xmXrecycleview.showLoading();

        adapter.setOnItemClickListener(new XiangmuAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data, int position) {
                XiangmuModel.DataBean dataBean = adapter.getDataSource().get(position);
                Intent intent = new Intent(getActivity(), ProjectStagingActivity.class);
                intent.putExtra(Constants.WORK_ORDER,dataBean);
                startActivity(intent);
            }
        });

        xmXrecycleview.getRecyclerView().setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
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
        xmXrecycleview.onRefresh();

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

    @OnClick({R.id.but_cj_fount,R.id.iv_xm_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.but_cj_fount:
                startActivity(new Intent(getActivity(), FoundProjectActivity.class));
                break;
            case R.id.iv_xm_add:
                startActivity(new Intent(getActivity(), FoundProjectActivity.class));
                break;
        }
    }

    public void xiangmuListSuccess(XiangmuModel model) {
        List<XiangmuModel.DataBean> data = model.getData();
        if(data != null && data.size() != 0) {
            if (pages == 1) {
                llXmEmpty.setVisibility(View.GONE);
                imXmAdd.setVisibility(View.VISIBLE);
                xmXrecycleview.setVisibility(View.VISIBLE);
                adapter.setData(data);
            } else {
                adapter.addData(data);
            }
            xmXrecycleview.getRecyclerView().setPage(model.getCurrent_page(), model.getLast_page());
        }else {
            if (pages == 1) {
                llXmEmpty.setVisibility(View.VISIBLE);
                imXmAdd.setVisibility(View.GONE);
                xmXrecycleview.setVisibility(View.GONE);
            }
        }
    }

}
