package com.mfzn.deepusesSer.activityxm.shgd;

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
import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.adapter.CustomDotIndexProvider;
import com.mfzn.deepusesSer.adapter.CustomLoadingUIProvider;
import com.mfzn.deepusesSer.adapter.GlideSimpleLoader;
import com.mfzn.deepusesSer.adapter.xiangmu.ShouliPhotoAdapter;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.model.shouhou.ClzGongdanDetailModel;
import com.mfzn.deepusesSer.model.shouhou.PingjiaDetailModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.present.shouhou.ClzHuidanDetailPresnet;
import com.mfzn.deepusesSer.present.shouhou.PingjiaDetailPresnet;
import com.mfzn.deepusesSer.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PingjiaDetailActivity extends BaseMvpActivity<PingjiaDetailPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;

    @BindView(R.id.iv_per_icon)
    ImageView ivIcon;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_date)
    TextView tvDate;

    @BindView(R.id.recycleview)
    RecyclerView recycleview;

    @BindView(R.id.iv_star1)
    ImageView iv_star1;

    @BindView(R.id.iv_star2)
    ImageView iv_star2;

    @BindView(R.id.iv_star3)
    ImageView iv_star3;

    @BindView(R.id.iv_star4)
    ImageView iv_star4;

    @BindView(R.id.iv_star5)
    ImageView iv_star5;

    @BindView(R.id.tv_pingjia)
    TextView tv_pingjia;

    @BindView(R.id.tv_pingjia_des)
    TextView tv_pingjia_des;
    private ImageWatcherHelper iwHelper;

    private String orderNo = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_pingjia_detail;
    }

    @Override
    public PingjiaDetailPresnet newP() {
        return new PingjiaDetailPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("评价详情");
        orderNo = getIntent().getStringExtra("orderNo");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleview.setLayoutManager(layoutManager);

        iwHelper = ImageWatcherHelper.with(this, new GlideSimpleLoader()) // 一般来讲， ImageWatcher 需要占据全屏的位置
                .setIndexProvider(new CustomDotIndexProvider()) // 自定义index
                .setLoadingUIProvider(new CustomLoadingUIProvider()); // 骰子loading


        getP().pingjiaDetail(orderNo);

    }

    @OnClick({R.id.iv_login_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
        }
    }

    public void pingjiaDetailSuccess(PingjiaDetailModel detailModel){
        if (!TextUtils.isEmpty(detailModel.getUserInfo().getUserAvatar())){
            Glide.with(context).load(ApiHelper.BASE_URL + detailModel.getUserInfo().getUserAvatar()).into(ivIcon);
        }
        tvName.setText(detailModel.getUserInfo().getUserName());
        tvDate.setText(DateUtils.stampToDateTime(String.valueOf(detailModel.getAddTime())));
        int stars = Integer.parseInt( detailModel.getStars());
        if (stars == 1){
            iv_star1.setImageResource(R.mipmap.star_selected);
            tv_pingjia_des.setText("非常不满意");
        }else if (stars == 2){
            iv_star1.setImageResource(R.mipmap.star_selected);
            iv_star2.setImageResource(R.mipmap.star_selected);
            tv_pingjia_des.setText("不满意");
        }else if (stars == 3){
            iv_star1.setImageResource(R.mipmap.star_selected);
            iv_star2.setImageResource(R.mipmap.star_selected);
            iv_star3.setImageResource(R.mipmap.star_selected);
            tv_pingjia_des.setText("一般");
        }else if (stars == 4){
            iv_star1.setImageResource(R.mipmap.star_selected);
            iv_star2.setImageResource(R.mipmap.star_selected);
            iv_star3.setImageResource(R.mipmap.star_selected);
            iv_star4.setImageResource(R.mipmap.star_selected);
            tv_pingjia_des.setText("满意");
        }else if (stars == 5){
            iv_star1.setImageResource(R.mipmap.star_selected);
            iv_star2.setImageResource(R.mipmap.star_selected);
            iv_star3.setImageResource(R.mipmap.star_selected);
            iv_star4.setImageResource(R.mipmap.star_selected);
            iv_star5.setImageResource(R.mipmap.star_selected);
            tv_pingjia_des.setText("非常满意");
        }
        tv_pingjia.setText(detailModel.getContent());
        List<String> gzdxInfo = detailModel.getFileUrls();
        ArrayList<String> dataList1 = new ArrayList<>();
        ArrayList<Uri> dataList = new ArrayList<>();
        if(gzdxInfo.size() > 0){
            for (int i = 0; i < gzdxInfo.size(); i++){
                dataList1.add(gzdxInfo.get(i));
                dataList.add(Uri.parse(ApiHelper.BASE_URL + gzdxInfo.get(i)));
            }
            ShouliPhotoAdapter recycleAdapter = new ShouliPhotoAdapter(PingjiaDetailActivity.this,dataList);
            recycleview.setAdapter(recycleAdapter);

            recycleAdapter.setOnClickListener(new ShouliPhotoAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    iwHelper.show(dataList, position);
                }
            });

        }

    }
}
