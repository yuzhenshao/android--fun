package com.mfzn.deepuses.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activitynews.ProjectApplyActivity;
import com.mfzn.deepuses.activitynews.ProjectMsgActivity;
import com.mfzn.deepuses.activitynews.SystemMsgActivity;
import com.mfzn.deepuses.activitynews.TeamApplyActivity;
import com.mfzn.deepuses.activitynews.TeamMsgActivity;
import com.mfzn.deepuses.bass.BaseMvpFragment;
import com.mfzn.deepuses.model.xx.MsgMainModel;
import com.mfzn.deepuses.present.fragment.XiaoxiPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import q.rorbin.badgeview.QBadgeView;

public class XiaoxiFragment extends BaseMvpFragment<XiaoxiPresnet> {

    @BindView(R.id.tv_news_crude)
    TextView tvNewsCrude;
    @BindView(R.id.tv_tdsq_time)
    TextView tvTdsqTime;
    @BindView(R.id.tv_tdsq_num)
    TextView tvTdsqNum;
    @BindView(R.id.tv_tdsq)
    TextView tvTdsq;
    @BindView(R.id.tv_xmsq_time)
    TextView tvXmsqTime;
    @BindView(R.id.tv_xmsq_num)
    TextView tvXmsqNum;
    @BindView(R.id.tv_xmsq)
    TextView tvXmsq;
    @BindView(R.id.tv_xtxx_time)
    TextView tvXtxxTime;
    @BindView(R.id.tv_xtxx_num)
    TextView tvXtxxNum;
    @BindView(R.id.tv_xtxx)
    TextView tvXtxx;
    @BindView(R.id.tv_tdxx_time)
    TextView tvTdxxTime;
    @BindView(R.id.tv_tdxx_num)
    TextView tvTdxxNum;
    @BindView(R.id.tv_tdxx)
    TextView tvTdxx;
    @BindView(R.id.tv_xmxx_time)
    TextView tvXmxxTime;
    @BindView(R.id.tv_xmxx_num)
    TextView tvXmxxNum;
    @BindView(R.id.tv_xmxx)
    TextView tvXmxx;

    @BindView(R.id.rl_txsq_tip)
    RelativeLayout rl_txsq_tip;
    @BindView(R.id.rl_xmsq_tip)
    RelativeLayout rl_xmsq_tip;
    @BindView(R.id.rl_xtxx_tip)
    RelativeLayout rl_xtxx_tip;
    @BindView(R.id.rl_tdxx_tip)
    RelativeLayout rl_tdxx_tip;
    @BindView(R.id.rl_xmxx_tip)
    RelativeLayout rl_xmxx_tip;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_xiaoxi;
    }

    @Override
    public XiaoxiPresnet newP() {
        return new XiaoxiPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvNewsCrude.getPaint().setFakeBoldText(true);
        getP().getMsg();

        RxBus.getInstance().toObservable().map(new Function<Object, EventMsg>() {
            @Override
            public EventMsg apply(Object o) throws Exception {
                return (EventMsg) o;
            }
        }).subscribe(new Consumer<EventMsg>() {
            @Override
            public void accept(EventMsg eventMsg) throws Exception {
                if (eventMsg != null) {
                    if (eventMsg.getMsg().equals(Constants.XIAOXI)) {
                        getP().getMsg();
                    }
                }
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            getP().getMsg();
        }
    }

    @OnClick({R.id.ll_tdsq, R.id.ll_xmsq, R.id.ll_xtxx, R.id.ll_tdxx, R.id.ll_xmxx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_tdsq:
                startActivity(new Intent(getActivity(), TeamApplyActivity.class));
                break;
            case R.id.ll_xmsq:
                startActivity(new Intent(getActivity(), ProjectApplyActivity.class));
                break;
            case R.id.ll_xtxx:
                startActivity(new Intent(getActivity(), SystemMsgActivity.class));
                break;
            case R.id.ll_tdxx:
                startActivity(new Intent(getActivity(), TeamMsgActivity.class));
                break;
            case R.id.ll_xmxx:
                startActivity(new Intent(getActivity(), ProjectMsgActivity.class));
                break;
        }
    }

    public void getMsgSuccess(MsgMainModel model){
        List<MsgMainModel.DataBean> data = model.getData();
        for (int i = 0; i < data.size(); i++){
            MsgMainModel.DataBean msg = data.get(i);
            if (msg.getType() == 1){
                if (msg.getNotReadCount() == 0){
                    rl_xmxx_tip.setVisibility(View.GONE);
                    tvXmxx.setText(msg.getText());
//                    tvXmxxTime.setText(DateUtils.stampDate(msg.getTime()));
                }else{
                    rl_xmxx_tip.setVisibility(View.VISIBLE);
                    tvXmxxNum.setText(String.valueOf(msg.getNotReadCount()));
                    tvXmxx.setText(msg.getText());
                    tvXmxxTime.setText(DateUtils.stampDate(msg.getTime()));
                }
            }else if (msg.getType() == 2){
                if (msg.getNotReadCount() == 0){
                    rl_tdxx_tip.setVisibility(View.GONE);
                    tvTdxx.setText(msg.getText());
//                    tvTdxxTime.setText(DateUtils.stampDate(msg.getTime()));
                }else{
                    rl_tdxx_tip.setVisibility(View.VISIBLE);
                    tvTdxxNum.setText(String.valueOf(msg.getNotReadCount()));
                    tvTdxx.setText(msg.getText());
                    tvTdxxTime.setText(DateUtils.stampDate(msg.getTime()));
                }
            }else if (msg.getType() == 3){
                if (msg.getNotReadCount() == 0){
                    rl_xtxx_tip.setVisibility(View.GONE);
                    tvXtxx.setText(msg.getText());
//                    tvXtxxTime.setText(DateUtils.stampDate(msg.getTime()));
                }else{
                    rl_xtxx_tip.setVisibility(View.VISIBLE);
                    tvXtxxNum.setText(String.valueOf(msg.getNotReadCount()));
                    tvXtxx.setText(msg.getText());
                    tvXtxxTime.setText(DateUtils.stampDate(msg.getTime()));
                }
            }else if (msg.getType() == 4){
                if (msg.getNotReadCount() == 0){
                    rl_xmsq_tip.setVisibility(View.GONE);
                    tvXmsq.setText(msg.getText());
//                    tvXmsqTime.setText("");
                }else{
                    rl_xmsq_tip.setVisibility(View.VISIBLE);
                    tvXmsqNum.setText(String.valueOf(msg.getNotReadCount()));
                    tvXmsq.setText(msg.getText());
                    tvXmsqTime.setText(DateUtils.stampDate(msg.getTime()));
                }
            }else if (msg.getType() == 5){
                if (msg.getNotReadCount() == 0){
                    rl_txsq_tip.setVisibility(View.GONE);
                    tvTdsq.setText(msg.getText());
//                    tvTdsqTime.setText("");
                }else{
                    rl_txsq_tip.setVisibility(View.VISIBLE);
                    tvTdsqNum.setText(String.valueOf(msg.getNotReadCount()));
                    tvTdsq.setText(msg.getText());
                    tvTdsqTime.setText(DateUtils.stampDate(msg.getTime()));
                }
            }
        }
    }
}
