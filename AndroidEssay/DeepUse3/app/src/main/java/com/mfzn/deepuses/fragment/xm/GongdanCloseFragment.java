package com.mfzn.deepuses.fragment.xm;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.github.ielse.imagewatcher.ImageWatcherHelper;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activityxm.shgd.CheckAppraiseActivity;
import com.mfzn.deepuses.activityxm.shgd.SettingNexttimeActivity;
import com.mfzn.deepuses.activityxm.shgd.WorkorderListActivity;
import com.mfzn.deepuses.adapter.CustomDotIndexProvider;
import com.mfzn.deepuses.adapter.CustomLoadingUIProvider;
import com.mfzn.deepuses.adapter.GlideSimpleLoader;
import com.mfzn.deepuses.adapter.xiangmu.ShouliPhotoAdapter;
import com.mfzn.deepuses.bass.BaseMvpFragment;
import com.mfzn.deepuses.model.xiangmu.GongdanShuxingModel;
import com.mfzn.deepuses.model.xiangmu.WorkorderListModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.present.fragment.GongdanClosePresnet;
import com.mfzn.deepuses.present.fragment.GongdanFuwuPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.utils.PhoneUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class GongdanCloseFragment extends BaseMvpFragment<GongdanClosePresnet> {

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
    @BindView(R.id.tv_ser_gcs)
    TextView tvserGcs;
    @BindView(R.id.tv_ser_gcsphone)
    TextView tvserGcsphone;
    @BindView(R.id.tv_ser_remarks)
    TextView tvserRemarks;
    @BindView(R.id.tv_bao_type)
    TextView tv_bao_type;

    private ImageWatcherHelper iwHelper;
    private String u_phone;
    private String orderno;
    private GongdanShuxingModel mGongdanShuxingModel;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_gongdan_close;
    }

    @Override
    public GongdanClosePresnet newP() {
        return new GongdanClosePresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        tvserGcsphone.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线

        orderno = getArguments().getString(Constants.SHOUHOU_ORDERNO);

        WorkorderListModel.DataBean dataBean = (WorkorderListModel.DataBean) getArguments().getSerializable(Constants.SHOUHOU_DETAILS);
        tvserGcs.setText(dataBean.getEngineerInfo().getUserName());
        u_phone = dataBean.getEngineerInfo().getUserPhone();
        tvserGcsphone.setText(u_phone);
        tvserRemarks.setText(dataBean.getEngineerInfo().getNote());

        getP().gongdanShuxing(orderno);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        accRecycleview.setLayoutManager(layoutManager);

        iwHelper = ImageWatcherHelper.with(getActivity(), new GlideSimpleLoader()) // 一般来讲， ImageWatcher 需要占据全屏的位置
                .setIndexProvider(new CustomDotIndexProvider()) // 自定义index
                .setLoadingUIProvider(new CustomLoadingUIProvider()); // 骰子loading
    }

    @OnClick(R.id.tv_ser_gcsphone)
    public void onViewClicked() {
        if (!TextUtils.isEmpty(u_phone)) {
            PhoneUtils.dialogPhone(getActivity(), u_phone);
        }
    }

    @OnClick({R.id.tv_ser_gcsphone, R.id.but_close_can})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_ser_gcsphone:
                if (!TextUtils.isEmpty(u_phone)) {
                    PhoneUtils.dialogPhone(getActivity(), u_phone);
                }
                break;
            case R.id.but_close_can:
                Intent intent = new Intent(new Intent(getActivity(), CheckAppraiseActivity.class));
                intent.putExtra(Constants.SHOUHOU_ORDERNO, orderno);
                startActivity(intent);
                break;
//            case R.id.but_close_time:
//                startActivity(new Intent(getActivity(), SettingNexttimeActivity.class));
//                break;
        }
    }

    public void gongdanShuxingSuccess(GongdanShuxingModel model) {
        tvAccKhname.setText(model.getCustomName());
        tvAccTime.setText(DateUtils.stampToDateTime(model.getAddTime() + ""));
        tvAccWhtime.setText(model.getWishTime());
        tvAccMs.setText(model.getContent());

        tv_bao_type.setText(setbx(model.getQualityIsGB(), model.getYbIsGB()));

        ArrayList<Uri> dataList = model.getFileInfo();
        if (dataList != null && dataList.size() != 0) {
            ShouliPhotoAdapter recycleAdapter = new ShouliPhotoAdapter(getActivity(), dataList);
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
        mGongdanShuxingModel = model;
    }

    public String setbx(int zhib, int yanb) {
        if (zhib == 1) {
            return "质保期内";
        }
        if (zhib == 2 && yanb == 0) {
            return "已过质保期";
        }
        if (zhib == 2 && yanb == 1) {
            return "延保期内";
        }
        if (yanb == 2) {
            return "已过延保期";
        }
        return "未设置";
    }

    public GongdanShuxingModel getGongdanShuxingModel() {
        return mGongdanShuxingModel;
    }
}
