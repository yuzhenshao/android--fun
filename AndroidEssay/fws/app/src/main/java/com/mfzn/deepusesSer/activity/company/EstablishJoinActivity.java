package com.mfzn.deepusesSer.activity.company;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseActivity;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.utils.EventMsg;
import com.mfzn.deepusesSer.utils.RxBus;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class EstablishJoinActivity extends BaseActivity {

    @BindView(R.id.tv_join_gongsi)
    TextView tvJoinGongsi;
    @BindView(R.id.tv_join_project)
    TextView tvJoinProject;
    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_join_skip)
    TextView tvJoinSkip;

    @Override
    public int getLayoutId() {
        return R.layout.activity_establish_join;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("创建或加入");
        tvJoinSkip.setVisibility(View.VISIBLE);
        tvJoinGongsi.getPaint().setFakeBoldText(true);
        tvJoinProject.getPaint().setFakeBoldText(true);

        RxBus.getInstance().toObservable().map(new Function<Object, EventMsg>() {
            @Override
            public EventMsg apply(Object o) throws Exception {
                return (EventMsg) o;
            }
        }).subscribe(new Consumer<EventMsg>() {
            @Override
            public void accept(EventMsg eventMsg) throws Exception {
                if (eventMsg != null) {
                    if(eventMsg.getMsg().equals(Constants.ESTABLSISH)){
                        finish();
                    }
                }
            }
        });
    }

    @OnClick({R.id.iv_login_back, R.id.ll_join_gongsi, R.id.ll_join_project, R.id.tv_join_skip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_join_skip:
                finish();
                break;
            case R.id.ll_join_gongsi:
                startActivity(new Intent(this, EstablishCompanyActivity.class));
                break;
            case R.id.ll_join_project:
                startActivity(new Intent(this, ScanJoinActivity.class));
                break;
        }
    }
}
