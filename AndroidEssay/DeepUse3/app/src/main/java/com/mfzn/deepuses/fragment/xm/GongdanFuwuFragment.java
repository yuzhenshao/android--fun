package com.mfzn.deepuses.fragment.xm;

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
import com.mfzn.deepuses.adapter.CustomDotIndexProvider;
import com.mfzn.deepuses.adapter.CustomLoadingUIProvider;
import com.mfzn.deepuses.adapter.GlideSimpleLoader;
import com.mfzn.deepuses.adapter.xiangmu.ShouliPhotoAdapter;
import com.mfzn.deepuses.bass.BaseMvpFragment;
import com.mfzn.deepuses.model.xiangmu.GongdanShuxingModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.present.fragment.GongdanFuwuPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.utils.PhoneUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class GongdanFuwuFragment extends BaseMvpFragment<GongdanFuwuPresnet> {

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

    @Override
    public int getLayoutId() {
        return R.layout.fragment_gongdan_fuwu;
    }

    @Override
    public GongdanFuwuPresnet newP() {
        return new GongdanFuwuPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        tvserGcsphone.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线

        String orderno = getArguments().getString(Constants.SHOUHOU_ORDERNO);

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

    public void gongdanShuxingSuccess(GongdanShuxingModel model) {

        tvserGcs.setText(model.getEnginerInfo().getUserName());
        tvserGcs.setText(model.getEnginerInfo().getUserName());
        u_phone = model.getEnginerInfo().getUserPhone();
        tvserGcsphone.setText(u_phone);
        tvserRemarks.setText(model.getEnginerInfo().getNote());

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
}
