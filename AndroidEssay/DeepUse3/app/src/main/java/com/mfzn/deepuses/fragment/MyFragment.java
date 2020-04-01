package com.mfzn.deepuses.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activitymy.EnginerMaillistActivity;
import com.mfzn.deepuses.activitymy.FeedbackActivity;
import com.mfzn.deepuses.activitymy.MyPromotionActivity;
import com.mfzn.deepuses.activitymy.OperationGuideActivity;
import com.mfzn.deepuses.activitymy.brick.BrickRecordActivity;
import com.mfzn.deepuses.activitymy.setting.SettingActivity;
import com.mfzn.deepuses.bass.BaseMvpFragment;
import com.mfzn.deepuses.bean.response.UserResponse;
import com.mfzn.deepuses.mine.activity.MyCardActivity;
import com.mfzn.deepuses.model.LookQuanxian2Model;
import com.mfzn.deepuses.model.company.SelectCompanyModel;
import com.mfzn.deepuses.model.my.UserInfoModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.present.fragment.MyPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.PhoneUtils;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MyFragment extends BaseMvpFragment<MyPresnet> {

    @BindView(R.id.tv_my_name)
    TextView tvMyName;
    @BindView(R.id.tv_my_compaly)
    TextView tvMyCompaly;
    @BindView(R.id.iv_my_icon)
    ImageView ivMyIcon;
    @BindView(R.id.tv_my_qx)
    TextView tv_my_qx;

    private String u_phone;
    private int leftDays;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public MyPresnet newP() {
        return new MyPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvMyName.getPaint().setFakeBoldText(true);
        getP().userInfo();
        getP().quanxian();

        tvMyCompaly.setText(UserHelper.getCompanyName());
        getP().companyList();
        tvMyName.setText(UserHelper.getU_name());

        RxBus.getInstance().toObservable().map(new Function<Object, EventMsg>() {
            @Override
            public EventMsg apply(Object o) throws Exception {
                return (EventMsg) o;
            }
        }).subscribe(new Consumer<EventMsg>() {
            @Override
            public void accept(EventMsg eventMsg) throws Exception {
                if (eventMsg != null) {
                    if (eventMsg.getMsg().equals(Constants.MODIFY_ICON)) {
                        getP().userInfo();
                    }else if (eventMsg.getMsg().equals(Constants.MODIFY_NAME)) {
                        tvMyName.setText(UserHelper.getU_name());
                    }else if (eventMsg.getMsg().equals(Constants.COMPANY_NAME)) {
                        tvMyCompaly.setText(UserHelper.getCompanyName());
                        getP().companyList();
                    }
                }
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            tvMyCompaly.setText(UserHelper.getCompanyName());
            getP().companyList();
        }
    }

    @OnClick({R.id.ll_my_tg, R.id.ll_my_setting, R.id.ll_my_tc, R.id.ll_my_gcs, R.id.ll_my_kf, R.id.ll_my_czzn,
                R.id.ll_my_qx, R.id.ll_my_zhuan,R.id.ll_my_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_my_card:
                startActivity(new Intent(context, MyCardActivity.class));
                break;
            case R.id.ll_my_tg:
                startActivity(new Intent(context, MyPromotionActivity.class));
                break;
            case R.id.ll_my_zhuan:
                startActivity(new Intent(context, BrickRecordActivity.class));
                break;
            case R.id.ll_my_setting:
                startActivity(new Intent(context, SettingActivity.class));
                break;
            case R.id.ll_my_czzn:
                Intent intent = new Intent(context, OperationGuideActivity.class);
                intent.putExtra("czzn",2);
                startActivity(intent);
                break;
//            case R.id.ll_my_help:
//                Intent intent = new Intent(context, WebviewX5Activity.class);
//                intent.putExtra(Constants.WEBVIEW_URL, "file:///android_asset/index.html");
//                intent.putExtra(Constants.WEBVIEW_NAME, "帮助中心");
//                startActivity(intent);
//                break;
            case R.id.ll_my_tc:
                startActivity(new Intent(context, FeedbackActivity.class));
                break;
            case R.id.ll_my_gcs:
                startActivity(new Intent(context, EnginerMaillistActivity.class));
                break;
            case R.id.ll_my_kf:
                PhoneUtils.dialogPhone2(getActivity(), "拨打","400-055-2011","4000552011");
                break;
            case R.id.ll_my_qx:
                if(leftDays > 0) {
                    PhoneUtils.dialogPhone2(getActivity(), "提示",
                            "售后板块试用期还剩" + leftDays + "天，到期后\n" +
                                    "如需继续使用，请拨打客服电话\n" +
                                    "400-055-2011","4000552011");
                }else {
                    PhoneUtils.dialogPhone2(getActivity(), "提示",
                            "对不起,您的售后板块试用期已结束\n，如需继续使用，请拨打客服电话\n400-055-2011","4000552011");
                }
                break;
        }
    }

    //用户信息成功返回
    public void userInfoSuccess(UserResponse result) {
        if (!TextUtils.isEmpty(result.getUserAvatar())){
            Glide.with(context).load(ApiHelper.BASE_URL + result.getUserAvatar()).into(ivMyIcon);
        }
        u_phone = result.getUserPhone();
    }

    public void lookQxSuccess(LookQuanxian2Model model) {
        leftDays = model.getCompanyModule().getAfterSale().getLeftDays();
        if(leftDays > 0) {
            tv_my_qx.setText("售后试用期还剩" + leftDays + "天");
        }else {
            tv_my_qx.setText("已过期");
        }
    }

    public void companyListSuccess(List<SelectCompanyModel> models) {
        for(int i = 0; i < models.size(); i++) {
            if(UserHelper.getCompanyName().equals(models.get(i).getCompanyName())) {
                int companyLevel = models.get(i).getCompanyLevel();
                if(companyLevel == 1) {
                    Drawable drawable = getResources().getDrawable(R.mipmap.vip1);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//                    tvMyCompaly.setCompoundDrawablePadding(5);// 设置喇叭与textview的距离
                    tvMyCompaly.setCompoundDrawables(null, null, drawable, null);
                }else if(companyLevel == 2) {
                    Drawable drawable = getResources().getDrawable(R.mipmap.vip2);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tvMyCompaly.setCompoundDrawables(null, null, drawable, null);
                }else if(companyLevel == 3) {
                    Drawable drawable = getResources().getDrawable(R.mipmap.vip3);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tvMyCompaly.setCompoundDrawables(null, null, drawable, null);
                }else if(companyLevel == 4) {
                    Drawable drawable = getResources().getDrawable(R.mipmap.vip4);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tvMyCompaly.setCompoundDrawables(null, null, drawable, null);
                }else if(companyLevel == 5) {
                    Drawable drawable = getResources().getDrawable(R.mipmap.vip5);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tvMyCompaly.setCompoundDrawables(null, null, drawable, null);
                }else if(companyLevel == 6) {
                    Drawable drawable = getResources().getDrawable(R.mipmap.vip6);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tvMyCompaly.setCompoundDrawables(null, null, drawable, null);
                }
            }
        }
    }
}
