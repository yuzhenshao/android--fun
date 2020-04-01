package com.mfzn.deepusesSer.fragment.fx;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.activity.fx.ZixunDetailActivity;
import com.mfzn.deepusesSer.adapter.faxian.ZxItemAdapter;
import com.mfzn.deepusesSer.bass.BaseMvpFragment;
import com.mfzn.deepusesSer.model.faxian.News;
import com.mfzn.deepusesSer.present.fragment.ZixunPresnet;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class ZixunFragmentNew extends BaseMvpFragment<ZixunPresnet> {

    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    @BindView(R.id.zx_xrecycleview)
    XRecyclerContentLayout zxXrecycleview;

    private ZxItemAdapter adapter;
    private int pages = 1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_zixun_new;
    }

    @Override
    public ZixunPresnet newP() {
        return new ZixunPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        adapter = new ZxItemAdapter(getContext());
        zxXrecycleview.getRecyclerView().verticalLayoutManager(getContext());
        zxXrecycleview.getRecyclerView().horizontalDivider(R.color.color_f5f7fa,R.dimen.app_10dp);//item之间的分割线
        zxXrecycleview.getRecyclerView().setAdapter(adapter);
        zxXrecycleview.getRecyclerView().setRefreshEnabled(true);
        zxXrecycleview.getRecyclerView().setVerticalScrollBarEnabled(false);//隐藏右侧的线
        zxXrecycleview.showLoading();

        adapter.setOnItemClickListener(new ZxItemAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data, int position) {
                News.DataBean dataBean = adapter.getDataSource().get(position);
                Intent intent = new Intent(getActivity(), ZixunDetailActivity.class);
                intent.putExtra("content",dataBean.getContent());
                intent.putExtra("likeNum",String.valueOf(dataBean.getLikeCount()));
                intent.putExtra("commentNum",String.valueOf(dataBean.getCommentCount()));
                intent.putExtra("title",dataBean.getNewsTitle());
                intent.putExtra("fbr",dataBean.getSourceName());
                intent.putExtra("date",String.valueOf(dataBean.getAddTime()));

                intent.putExtra("rowNum",String.valueOf(dataBean.getNewsID()));
                startActivity(intent);
            }
        });

        zxXrecycleview.getRecyclerView().setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                adapter.clearData();
                pages = 1;
                getP().newsList(pages);
            }

            @Override
            public void onLoadMore(int page) {
                pages = page;
                getP().newsList(pages);
            }
        });
        zxXrecycleview.onRefresh();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.clearData();
        pages = 1;
        getP().newsList(pages);
    }

    public void newsListSuccess(News model) {
        List<News.DataBean> data = model.getData();
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
