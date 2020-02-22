package com.mfzn.deepuses.fragment.fx;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.faxian.VideoListAdapter;
import com.mfzn.deepuses.bass.BaseMvpFragment;
import com.mfzn.deepuses.model.faxian.Videos;
import com.mfzn.deepuses.present.faxian.VideoPresnet;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class VideoFragmentNew extends BaseMvpFragment<VideoPresnet> {

    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    @BindView(R.id.zx_xrecycleview)
    XRecyclerContentLayout zxXrecycleview;

    private VideoListAdapter adapter;
    private int pages = 1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_zixun_new;
    }

    @Override
    public VideoPresnet newP() {
        return new VideoPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        adapter = new VideoListAdapter(getContext());
        zxXrecycleview.getRecyclerView().verticalLayoutManager(getContext());
        zxXrecycleview.getRecyclerView().horizontalDivider(R.color.color_f5f7fa,R.dimen.app_10dp);//item之间的分割线
        zxXrecycleview.getRecyclerView().setAdapter(adapter);
        zxXrecycleview.getRecyclerView().setRefreshEnabled(true);
        zxXrecycleview.getRecyclerView().setVerticalScrollBarEnabled(false);//隐藏右侧的线
        zxXrecycleview.showLoading();

        adapter.setOnItemClickListener(new VideoListAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data, int position) {
//                News.DataBean dataBean = adapter.getDataSource().get(position);
//                Intent intent = new Intent(getActivity(), WaitReceiveActivity.class);
//                intent.putExtra(Constants.SHOUHOU_DETAILS,dataBean);
//                startActivity(intent);
            }
        });

        zxXrecycleview.getRecyclerView().setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                adapter.clearData();
                pages = 1;
                getP().videoList(pages);
            }

            @Override
            public void onLoadMore(int page) {
                pages = page;
                getP().videoList(pages);
            }
        });
        zxXrecycleview.onRefresh();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.clearData();
        pages = 1;
        getP().videoList(pages);
    }

    public void newsListSuccess(Videos model) {
        List<Videos.DataBean> data = model.getData();
        if(data != null && data.size() != 0) {
            if (pages == 1) {
                llEmpty.setVisibility(View.GONE);
                adapter.setData(data);
            } else {
                adapter.addData(data);
            }
            zxXrecycleview.getRecyclerView().setPage(model.getCurrent_page(), model.getLast_page());
        }else {
            if (pages == 1) {
                llEmpty.setVisibility(View.VISIBLE);
            }
        }
    }
}
