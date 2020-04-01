package com.mfzn.deepuses.activityxm.shgd;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.ielse.imagewatcher.ImageWatcherHelper;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.CustomDotIndexProvider;
import com.mfzn.deepuses.adapter.CustomLoadingUIProvider;
import com.mfzn.deepuses.adapter.GlideSimpleLoader;
import com.mfzn.deepuses.adapter.xiangmu.ShouliPhotoAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.xiangmu.ClzGongdanDetailModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.present.xmgd.ClzHuidanDetailPresnet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FwzHuizhiDetailActivity extends BaseMvpActivity<ClzHuidanDetailPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_orderno)
    TextView tvOrderNo;
    @BindView(R.id.tv_orderno1)
    TextView tvOrderNo1;

    @BindView(R.id.tv_huidan_title)
    TextView tvTitle;

    @BindView(R.id.tv_huidan_title1)
    TextView tvTitle1;

    @BindView(R.id.tv_gzfx)
    TextView tvGzfx;
    @BindView(R.id.gzfx_recycleview)
    RecyclerView gzfx_xgtp;

    @BindView(R.id.tv_jjfa)
    TextView tvJjfa;
    @BindView(R.id.jjfa_recycleview)
    RecyclerView jjfa_xgtp;

    @BindView(R.id.tv_clxx)
    TextView tvClxx;
    @BindView(R.id.clxx_recycleview)
    RecyclerView clxx_xgtp;

    @BindView(R.id.ll_clz)
    LinearLayout llClz;

    @BindView(R.id.ll_ywg)
    LinearLayout llYwg;

    @BindView(R.id.iv_qm)
    ImageView ivQm;

    private String receiptId = "";
    private ImageWatcherHelper iwHelper;
    private ImageWatcherHelper iwHelper1;
    private ImageWatcherHelper iwHelper2;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fwz_huizhi_detail;
    }

    @Override
    public ClzHuidanDetailPresnet newP() {
        return new ClzHuidanDetailPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        receiptId = getIntent().getStringExtra("receiptId");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        gzfx_xgtp.setLayoutManager(layoutManager);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        jjfa_xgtp.setLayoutManager(layoutManager1);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        clxx_xgtp.setLayoutManager(layoutManager2);

        iwHelper = ImageWatcherHelper.with(this, new GlideSimpleLoader()) // 一般来讲， ImageWatcher 需要占据全屏的位置
                .setIndexProvider(new CustomDotIndexProvider()) // 自定义index
                .setLoadingUIProvider(new CustomLoadingUIProvider()); // 骰子loading

        iwHelper1 = ImageWatcherHelper.with(this, new GlideSimpleLoader()) // 一般来讲， ImageWatcher 需要占据全屏的位置
                .setIndexProvider(new CustomDotIndexProvider()) // 自定义index
                .setLoadingUIProvider(new CustomLoadingUIProvider()); // 骰子loading

        iwHelper2 = ImageWatcherHelper.with(this, new GlideSimpleLoader()) // 一般来讲， ImageWatcher 需要占据全屏的位置
                .setIndexProvider(new CustomDotIndexProvider()) // 自定义index
                .setLoadingUIProvider(new CustomLoadingUIProvider()); // 骰子loading

        getP().huidanDetail(receiptId);

    }

    @OnClick({R.id.iv_login_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
        }
    }

    public void huidanDetailSuccess(ClzGongdanDetailModel detailModel){
        if (TextUtils.isEmpty(detailModel.getSign())){
            llClz.setVisibility(View.VISIBLE);
            llYwg.setVisibility(View.GONE);
            tvBassTitle.setText("处理中回单");
            tvTitle.setText("处理中回单");
            tvOrderNo.setText(detailModel.getOrderNo());
            tvGzfx.setText(detailModel.getFaultText());
            ArrayList<Uri> dataList = detailModel.getFaultFileIdUrl();
            if(dataList.size() > 0){
                ShouliPhotoAdapter recycleAdapter = new ShouliPhotoAdapter(FwzHuizhiDetailActivity.this,dataList);
                gzfx_xgtp.setAdapter(recycleAdapter);

                recycleAdapter.setOnClickListener(new ShouliPhotoAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        iwHelper.show(dataList, position);
                    }
                });

            }
            tvJjfa.setText(detailModel.getContent());
            ArrayList<Uri> dataList3 = detailModel.getContentFileIdUrl();
            if(dataList3.size() > 0){
                ShouliPhotoAdapter recycleAdapter1 = new ShouliPhotoAdapter(FwzHuizhiDetailActivity.this,dataList3);
                jjfa_xgtp.setAdapter(recycleAdapter1);

                recycleAdapter1.setOnClickListener(new ShouliPhotoAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        iwHelper1.show(dataList3, position);
                    }
                });

            }
        }else{
            llClz.setVisibility(View.GONE);
            llYwg.setVisibility(View.VISIBLE);
            tvBassTitle.setText("完工回单");
            tvTitle1.setText("完工回单");
            tvOrderNo1.setText(detailModel.getOrderNo());
            Glide.with(context).load(ApiHelper.BASE_URL + detailModel.getSign()).into(ivQm);
            tvClxx.setText(detailModel.getContent());
            ArrayList<Uri> dataList3 = detailModel.getContentFileIdUrl();
            if(dataList3.size() > 0){
                ShouliPhotoAdapter recycleAdapter1 = new ShouliPhotoAdapter(FwzHuizhiDetailActivity.this,dataList3);
                clxx_xgtp.setAdapter(recycleAdapter1);

                recycleAdapter1.setOnClickListener(new ShouliPhotoAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        iwHelper2.show(dataList3, position);
                    }
                });

            }
        }


    }
}
