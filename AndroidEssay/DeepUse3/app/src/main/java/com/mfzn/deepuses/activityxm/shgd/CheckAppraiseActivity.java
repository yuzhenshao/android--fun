package com.mfzn.deepuses.activityxm.shgd;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.ielse.imagewatcher.ImageWatcherHelper;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.CustomDotIndexProvider;
import com.mfzn.deepuses.adapter.CustomLoadingUIProvider;
import com.mfzn.deepuses.adapter.GlideSimpleLoader;
import com.mfzn.deepuses.adapter.xiangmu.CheckAppraiseAdapter;
import com.mfzn.deepuses.adapter.xiangmu.ProjectStagingAdapter;
import com.mfzn.deepuses.adapter.xiangmu.ShouliPhotoAdapter;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.xiangmu.CheckAppraiseModel;
import com.mfzn.deepuses.model.xiangmu.WorkorderListModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.present.project.CheckAppraisePresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.view.MyRecyclerView;
import com.mfzn.deepuses.view.NoScrollGridLayoutManager;
import com.mfzn.deepuses.view.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckAppraiseActivity extends BaseMvpActivity<CheckAppraisePresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.iv_ch_icon)
    RoundImageView ivChIcon;
    @BindView(R.id.tv_ch_name)
    TextView tvChName;
    @BindView(R.id.tv_ch_time)
    TextView tvChTime;
    @BindView(R.id.iv_ch_xx1)
    ImageView ivChXx1;
    @BindView(R.id.iv_ch_xx2)
    ImageView ivChXx2;
    @BindView(R.id.iv_ch_xx3)
    ImageView ivChXx3;
    @BindView(R.id.iv_ch_xx4)
    ImageView ivChXx4;
    @BindView(R.id.iv_ch_xx5)
    ImageView ivChXx5;
    @BindView(R.id.tv_ch_type)
    TextView tvChType;
    @BindView(R.id.tv_ch_content)
    TextView tvChContent;
    @BindView(R.id.ch_recyleview)
    MyRecyclerView ch_recyleview;

    private ImageWatcherHelper iwHelper;

    @Override
    public int getLayoutId() {
        return R.layout.activity_check_appraise;
    }

    @Override
    public CheckAppraisePresent newP() {
        return new CheckAppraisePresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_check_appraise));

        String orderno = getIntent().getStringExtra(Constants.SHOUHOU_ORDERNO);

        getP().checkAppraise(orderno);

        iwHelper = ImageWatcherHelper.with(this, new GlideSimpleLoader()) // 一般来讲， ImageWatcher 需要占据全屏的位置
                .setIndexProvider(new CustomDotIndexProvider()) // 自定义index
                .setLoadingUIProvider(new CustomLoadingUIProvider()); // 骰子loading

        NoScrollGridLayoutManager appLayoutManager = new NoScrollGridLayoutManager(this,
                3, GridLayoutManager.VERTICAL, false);
        ch_recyleview.setLayoutManager(appLayoutManager);
    }

    @OnClick(R.id.iv_login_back)
    public void onViewClicked() {
        finish();
    }

    public void checkAppraiseSuccess(CheckAppraiseModel model) {
        String u_head = model.getUserInfo().getU_head();
        if(!TextUtils.isEmpty(u_head)) {
            Glide.with(this).load(ApiHelper.BASE_URL + u_head).into(ivChIcon);
        }
        tvChName.setText(model.getUserInfo().getU_name());
        tvChTime.setText(DateUtils.stampToDate(model.getAddTime() + ""));

        String stars = model.getStars();
        hidePhoto();
        if(stars.equals("1")) {
            ivChXx1.setImageResource(R.mipmap.xx_xuanzhong);
            tvChType.setText("非常不满意");
        }else if(stars.equals("2")) {
            ivChXx1.setImageResource(R.mipmap.xx_xuanzhong);
            ivChXx2.setImageResource(R.mipmap.xx_xuanzhong);
            tvChType.setText("不满意");
        }else if(stars.equals("3")) {
            ivChXx1.setImageResource(R.mipmap.xx_xuanzhong);
            ivChXx2.setImageResource(R.mipmap.xx_xuanzhong);
            ivChXx3.setImageResource(R.mipmap.xx_xuanzhong);
            tvChType.setText("一般");
        }else if(stars.equals("4")) {
            ivChXx1.setImageResource(R.mipmap.xx_xuanzhong);
            ivChXx2.setImageResource(R.mipmap.xx_xuanzhong);
            ivChXx3.setImageResource(R.mipmap.xx_xuanzhong);
            ivChXx4.setImageResource(R.mipmap.xx_xuanzhong);
            tvChType.setText("满意");
        }else if(stars.equals("5")) {
            ivChXx1.setImageResource(R.mipmap.xx_xuanzhong);
            ivChXx2.setImageResource(R.mipmap.xx_xuanzhong);
            ivChXx3.setImageResource(R.mipmap.xx_xuanzhong);
            ivChXx4.setImageResource(R.mipmap.xx_xuanzhong);
            ivChXx5.setImageResource(R.mipmap.xx_xuanzhong);
            tvChType.setText("非常满意");
        }
        tvChContent.setText(model.getContent());

        List<String> fileUrl = model.getFileUrl();

        List<Uri> dataList = new ArrayList<>();
        for (int i = 0 ; i < fileUrl.size() ; i++){
            dataList.add(Uri.parse(ApiHelper.BASE_URL + fileUrl.get(i)));
        }

        CheckAppraiseAdapter adapter= new CheckAppraiseAdapter(this,fileUrl);
        ch_recyleview.setAdapter(adapter);

        adapter.setOnClickListener(new ShouliPhotoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                iwHelper.show(dataList, position);
            }
        });
    }

    private void hidePhoto() {
        ivChXx1.setImageResource(R.mipmap.xx_weixuan);
        ivChXx2.setImageResource(R.mipmap.xx_weixuan);
        ivChXx3.setImageResource(R.mipmap.xx_weixuan);
        ivChXx4.setImageResource(R.mipmap.xx_weixuan);
        ivChXx5.setImageResource(R.mipmap.xx_weixuan);
    }
}
