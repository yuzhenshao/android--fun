package com.mfzn.deepusesSer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.activitymy.FeedbackActivity;
import com.mfzn.deepusesSer.activitymy.MyCardActivity;
import com.mfzn.deepusesSer.activitymy.MyPromotionActivity;
import com.mfzn.deepusesSer.activitymy.setting.SettingActivity;
import com.mfzn.deepusesSer.bass.BaseMvpFragment;
import com.mfzn.deepusesSer.model.login.UserModel;
import com.mfzn.deepusesSer.model.person.UserInfoModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.present.fragment.MyPresnet;
import com.mfzn.deepusesSer.utils.PhoneUtils;
import com.mfzn.deepusesSer.view.RoundImageView;

import butterknife.BindView;
import butterknife.OnClick;

public class MyFragment extends BaseMvpFragment<MyPresnet> {
    @BindView(R.id.iv_per_icon)
    RoundImageView ivIcon;
    @BindView(R.id.tv_per_name)
    TextView tvName;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public MyPresnet newP() {
        return new MyPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {
        super.onStart();
        getP().userInfo();
    }

    @OnClick({R.id.ll_my_card,R.id.ll_shezhi,R.id.ll_tucao,R.id.ll_kefu,R.id.ll_my_tg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_my_card:
                startActivity(new Intent(context, MyCardActivity.class));
                break;
            case R.id.ll_shezhi:
                startActivity(new Intent(context, SettingActivity.class));
                break;
            case R.id.ll_my_help:
                break;
            case R.id.ll_tucao:
                startActivity(new Intent(context, FeedbackActivity.class));
                break;
            case R.id.ll_kefu:
                PhoneUtils.dialogPhone(getActivity(), "400-055-2011");
                break;
            case R.id.ll_my_tg:
                startActivity(new Intent(context, MyPromotionActivity.class));
                break;
        }
    }

    //用户信息成功返回
    public void userInfoSuccess(UserModel result) {
        if (!TextUtils.isEmpty(result.getUserAvatar())){
            Glide.with(context).load(ApiHelper.BASE_URL + result.getUserAvatar()).into(ivIcon);
        }
        tvName.setText(result.getUserName());
    }
}
