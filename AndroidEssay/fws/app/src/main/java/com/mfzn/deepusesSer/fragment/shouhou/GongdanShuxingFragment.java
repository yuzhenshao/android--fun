package com.mfzn.deepusesSer.fragment.shouhou;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.github.ielse.imagewatcher.ImageWatcherHelper;
import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.adapter.CustomDotIndexProvider;
import com.mfzn.deepusesSer.adapter.CustomLoadingUIProvider;
import com.mfzn.deepusesSer.adapter.GlideSimpleLoader;
import com.mfzn.deepusesSer.adapter.xiangmu.ShouliPhotoAdapter;
import com.mfzn.deepusesSer.bass.BaseMvpFragment;
import com.mfzn.deepusesSer.common.MapNaviUtils;
import com.mfzn.deepusesSer.model.shouhou.GongdanShuxingModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.present.shouhou.GongdanShuxingPresnet;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GongdanShuxingFragment extends BaseMvpFragment<GongdanShuxingPresnet> {

    @BindView(R.id.tv_acc_khname)
    TextView tvAccKhname;
    @BindView(R.id.tv_acc_time)
    TextView tvAccTime;
    @BindView(R.id.tv_acc_whtime)
    TextView tvAccWhtime;
    @BindView(R.id.tv_acc_ms)
    TextView tvAccMs;
    @BindView(R.id.acc_recycleview)
    RecyclerView accRecycleview;

    private ImageWatcherHelper iwHelper;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_gongdan_shuxing;
    }

    @Override
    public GongdanShuxingPresnet newP() {
        return new GongdanShuxingPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        String orderno = getArguments().getString(Constants.SHOUHOU_ORDERNO);

        getP().gongdanShuxing(orderno);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        accRecycleview.setLayoutManager(layoutManager);

        iwHelper = ImageWatcherHelper.with(getActivity(), new GlideSimpleLoader()) // 一般来讲， ImageWatcher 需要占据全屏的位置
                .setIndexProvider(new CustomDotIndexProvider()) // 自定义index
                .setLoadingUIProvider(new CustomLoadingUIProvider()); // 骰子loading
    }

    public void gongdanShuxingSuccess(GongdanShuxingModel model) {
        tvAccKhname.setText(model.getCustomName());
        tvAccTime.setText(DateUtils.stampToDateTime(model.getAddTime() + ""));
        tvAccWhtime.setText(model.getWishTime());
        tvAccMs.setText(model.getContent());


        ArrayList<Uri> dataList = model.getFileInfo();
        if (dataList != null && dataList.size() != 0) {
            ShouliPhotoAdapter recycleAdapter = new ShouliPhotoAdapter(getActivity(),dataList);
            accRecycleview.setAdapter(recycleAdapter);
            recycleAdapter.setOnClickListener(new ShouliPhotoAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    iwHelper.show(dataList, position);
                }
            });
        } else {
            accRecycleview.setVisibility(View.GONE);
        }
        setAccAddressClick(model.getLatitude(), model.getLongitude(), model.getDetailAddress());
    }

    private void setAccAddressClick(double latitude, double longitude, String address) {
        TextView tvAccAddress = getActivity().findViewById(R.id.tv_acc_address);
        tvAccAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapNaviUtils.goToMapNavi(getActivity(), latitude, longitude, address);
            }
        });
    }
}
