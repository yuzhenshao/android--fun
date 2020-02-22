package com.mfzn.deepuses.activity.login;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.GuideViewPagerAdapter;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.utils.FullScreen;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.droidlover.xdroidmvp.router.Router;

public class GuideActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.vp_guide)
    ViewPager vpGuide;

    // 引导页图片资源
    private static final int[] pics = { R.layout.guide_view1,
            R.layout.guide_view2, R.layout.guide_view3};

    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        FullScreen.setStatusBarMode(this);

        List<View> views = new ArrayList<View>();

        // 初始化引导页视图列表
        for (int i = 0; i < pics.length; i++) {
            View view = LayoutInflater.from(this).inflate(pics[i], null);
            if (i == 0) {
                ImageView tv_guide_skip1 = view.findViewById(R.id.tv_guide_skip1);
                tv_guide_skip1.setOnClickListener(this);
            }else if (i == 1) {
                ImageView tv_guide_skip2 = view.findViewById(R.id.tv_guide_skip2);
                tv_guide_skip2.setOnClickListener(this);
            }else if (i == 2) {
                ImageView tv_guide_login = view.findViewById(R.id.tv_guide_login);
                tv_guide_login.setOnClickListener(this);
            }
            views.add(view);
        }

        GuideViewPagerAdapter adapter = new GuideViewPagerAdapter(views);
        vpGuide.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        SharedPref.getInstance(this).putBoolean("isFirstUse", false);
        Router.newIntent(this).to(LoginActivity.class).launch();
        finish();
    }
}
