package com.mfzn.deepuses.activitymy.brick;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activitymy.WebviewX5Activity;
import com.mfzn.deepuses.adapter.brick.RechargeComboAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.brick.CompanyInfoModel;
import com.mfzn.deepuses.model.brick.RechargeComboModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.present.brick.RechargePresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.view.MyRecyclerView;
import com.mfzn.deepuses.view.NoScrollGridLayoutManager;
import com.mfzn.deepuses.view.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RechargeActivity extends BaseMvpActivity<RechargePresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.iv_rech_icon)
    RoundImageView ivRechIcon;
    @BindView(R.id.tv_rech_company)
    TextView tvRechCompany;
    @BindView(R.id.tv_rech_brick)
    TextView tvRechBrick;
    @BindView(R.id.iv_rech_type)
    ImageView iv_rech_type;
    @BindView(R.id.but_rach_m)
    Button but_rach_m;
    @BindView(R.id.mz_recyleview)
    MyRecyclerView mz_recyleview;
    @BindView(R.id.iv_rech_vip1)
    ImageView iv_rech_vip1;
    @BindView(R.id.iv_rech_vip2)
    ImageView iv_rech_vip2;
    @BindView(R.id.pb_rech_jd)
    ProgressBar pb_rech_jd;
    @BindView(R.id.tv_rech_money)
    TextView tv_rech_money;
    @BindView(R.id.tv_rech_hx)
    TextView tv_rech_hx;

    private List<RechargeComboModel> models = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_recharge;
    }

    @Override
    public RechargePresnet newP() {
        return new RechargePresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_recharge));

        NoScrollGridLayoutManager czLayoutManager = new NoScrollGridLayoutManager(this,
                3, GridLayoutManager.VERTICAL, false);
        mz_recyleview.setLayoutManager(czLayoutManager);

        getP().getCompanyInfo();
        getP().rechargeCombo();

        RxBus.getInstance().toObservable().map(new Function<Object, EventMsg>() {
            @Override
            public EventMsg apply(Object o) throws Exception {
                return (EventMsg) o;
            }
        }).subscribe(new Consumer<EventMsg>() {
            @Override
            public void accept(EventMsg eventMsg) throws Exception {
                if (eventMsg != null) {
                    if(eventMsg.getMsg().equals(Constants.PAYMENT)){
                        getP().getCompanyInfo();
                        getP().rechargeCombo();
                    }
                }
            }
        });
    }

    @OnClick({R.id.iv_login_back, R.id.tv_rech_look, R.id.but_rach_m})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_rech_look:
                Intent intent1 = new Intent(context, WebviewX5Activity.class);
                intent1.putExtra(Constants.WEBVIEW_URL, "https://yzs.mfzn.com.cn/mp/level/");
                intent1.putExtra(Constants.WEBVIEW_NAME, "会员规则");
                startActivity(intent1);
                break;
            case R.id.but_rach_m:
                String money = "";
                String payType = "";
                for(int i = 0; i < models.size(); i++) {
                    if(models.get(i).getClickType()) {
                        money = doubleTrans1(Double.parseDouble(models.get(i).getPrice()));
                        payType = String.valueOf(models.get(i).getData_id());
                    }
                }
                Intent intent = new Intent(this, PaymentModeActivity.class);
                intent.putExtra(Constants.PAY_MONEY,money);
                intent.putExtra(Constants.PAY_TYPE,payType);
                startActivity(intent);
                break;
        }
    }

    public void getCompanySuccess(CompanyInfoModel model) {
        tvRechCompany.setText(model.getCompanyName());
        int zhuan = (int) Double.parseDouble(model.getZhuan());
        int zhuan2 = (int) Double.parseDouble(model.getGiftZhuan());
        int sumZhuan = zhuan + zhuan2;
        tvRechBrick.setText("当前砖数：" + sumZhuan + " 砖");
        if(!TextUtils.isEmpty(model.getLogo())) {
            Glide.with(this).load(ApiHelper.BASE_URL + model.getLogo()).into(ivRechIcon);
        }
        int companyLevel = model.getCompanyLevel();
        if(companyLevel == 1) {
            iv_rech_vip1.setImageResource(R.mipmap.br_lvip1);
            iv_rech_vip2.setImageResource(R.mipmap.br_hvip2);
            iv_rech_type.setImageResource(R.mipmap.vip1);
        }else if(companyLevel == 2) {
            iv_rech_vip1.setImageResource(R.mipmap.br_lvip2);
            iv_rech_vip2.setImageResource(R.mipmap.br_hvip3);
            iv_rech_type.setImageResource(R.mipmap.vip2);
        }else if(companyLevel == 3) {
            iv_rech_vip1.setImageResource(R.mipmap.br_lvip3);
            iv_rech_vip2.setImageResource(R.mipmap.br_hvip4);
            iv_rech_type.setImageResource(R.mipmap.vip3);
        }else if(companyLevel == 4) {
            iv_rech_vip1.setImageResource(R.mipmap.br_lvip4);
            iv_rech_vip2.setImageResource(R.mipmap.br_hvip5);
            iv_rech_type.setImageResource(R.mipmap.vip4);
        }else if(companyLevel == 5) {
            iv_rech_vip1.setImageResource(R.mipmap.br_lvip5);
            iv_rech_vip2.setImageResource(R.mipmap.br_hvip6);
            iv_rech_type.setImageResource(R.mipmap.vip5);
        }else if(companyLevel == 6) {
            iv_rech_vip1.setImageResource(R.mipmap.br_lvip6);
            iv_rech_vip2.setVisibility(View.GONE);
            pb_rech_jd.setVisibility(View.INVISIBLE);
            iv_rech_type.setImageResource(R.mipmap.vip6);
        }

        double sumRecharge = model.getSumRecharge();
        double leftMoney = model.getLeftMoney();
        int i = (int) (sumRecharge / (sumRecharge + leftMoney));
        pb_rech_jd.setProgress(i);
        tv_rech_money.setText(doubleTrans1(sumRecharge) + "/" + doubleTrans1(sumRecharge + leftMoney));
        tv_rech_hx.setText(doubleTrans1(leftMoney));
    }

    public void rechargeComboSuccess(List<RechargeComboModel> model) {
        List<Integer> gb = new ArrayList<>();
        for(int i = 0; i < model.size(); i++) {
            if(model.get(i).getIsBuy() == 1 && model.get(i).getOnlyOnce() == 1) {
                gb.add(i);
            }

        }
        for(int i = 0; i < gb.size(); i++) {
            model.add(model.get(gb.get(i) - i));
            model.remove(gb.get(i) - i);
        }
        models = model;
        model.get(0).setClickType(true);
        but_rach_m.setText("立即支付 " + doubleTrans1(Double.parseDouble(model.get(0).getPrice())) + " 元");
        RechargeComboAdapter czzxAdapter = new RechargeComboAdapter(this,model);
        mz_recyleview.setAdapter(czzxAdapter);

        czzxAdapter.setOnItemClickListener(new RechargeComboAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                for(int i = 0; i < model.size(); i++) {
                    model.get(i).setClickType(false);
                }
                model.get(position).setClickType(true);
                but_rach_m.setText("立即支付 " + doubleTrans1(Double.parseDouble(model.get(position).getPrice())) + " 元");

                czzxAdapter.notifyDataSetChanged();
            }
        });
    }

    private String doubleTrans1(double num){
        if(num % 1.0 == 0){
            return String.valueOf((long)num);
        }
        return String.valueOf(num);
    }
}
