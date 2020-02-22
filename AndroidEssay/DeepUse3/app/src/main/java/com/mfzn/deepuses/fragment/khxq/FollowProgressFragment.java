package com.mfzn.deepuses.fragment.khxq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.ielse.imagewatcher.ImageWatcherHelper;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.khgl.AddFollowActivity;
import com.mfzn.deepuses.activity.khgl.ProDetailsActivity;
import com.mfzn.deepuses.adapter.CustomDotIndexProvider;
import com.mfzn.deepuses.adapter.CustomLoadingUIProvider;
import com.mfzn.deepuses.adapter.GlideSimpleLoader;
import com.mfzn.deepuses.adapter.khgl.CustomerProAdapter;
import com.mfzn.deepuses.adapter.khgl.FollowProAdapter;
import com.mfzn.deepuses.bass.BaseMvpFragment;
import com.mfzn.deepuses.model.brick.BrickRecordModel;
import com.mfzn.deepuses.model.khgl.FollowProModel;
import com.mfzn.deepuses.present.customer.FollowProgressPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.view.MyRecyclerView;
import com.wevey.selector.dialog.bean.DetailsModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FollowProgressFragment extends BaseMvpFragment<FollowProgressPresnet> {

    @BindView(R.id.fl_recycleview)
    RecyclerView flRecycleview;
    @BindView(R.id.ll_pr_empty)
    LinearLayout ll_pr_empty;

    private String dataid;
    private String name;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_follow_progress;
    }

    @Override
    public FollowProgressPresnet newP() {
        return new FollowProgressPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        flRecycleview.setLayoutManager(layoutManager);

        dataid = getArguments().getString(Constants.CUS_DETA_ID);
        name = getArguments().getString(Constants.CUS_DETA_NAME);
        getP().followPro(dataid);


    }

    @OnClick(R.id.ll_pr_xzgj)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), AddFollowActivity.class);
        intent.putExtra(Constants.ADD_FOLL,dataid);
        intent.putExtra(Constants.ADD_FOLL_NAME,name);
        startActivityForResult(intent, Constants.ADD_FOLLOW);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.ADD_FOLLOW == requestCode) {
            if (data != null) {
                getP().followPro(dataid);
            }
        }
    }

    public void followProSuccess(List<FollowProModel> models) {
        if(models != null && models.size() != 0) {
            ll_pr_empty.setVisibility(View.GONE);
            FollowProAdapter recycleAdapter = new FollowProAdapter(getActivity(),models);
            flRecycleview.setAdapter(recycleAdapter);

            recycleAdapter.setOnSeeItemClickListener(new FollowProAdapter.OnSeeItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                }
            });
        }else {
            ll_pr_empty.setVisibility(View.VISIBLE);
        }
    }
}
