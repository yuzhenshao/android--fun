package com.mfzn.deepuses.activityxm.shgd;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.xiangmu.SelectCustomAdapter;
import com.mfzn.deepuses.adapter.xiangmu.WorkorderTujianAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.xiangmu.CustomListModel;
import com.mfzn.deepuses.model.xiangmu.WorkorderListModel;
import com.mfzn.deepuses.model.xiangmu.WorkorderTujianModel;
import com.mfzn.deepuses.present.foundxm.WorkorderAcceptPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WorkorderAcceptActivity extends BaseMvpActivity<WorkorderAcceptPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_wo_slbold)
    TextView tvWoSlbold;
    @BindView(R.id.tv_wo_sl)
    TextView tvWoSl;
    @BindView(R.id.tv_wo_tj)
    TextView tvWoTj;
    @BindView(R.id.wo_listview)
    ListView woListview;

    private List<CustomListModel> models = new ArrayList<>();
    private WorkorderTujianAdapter adapter;

    private int iftype = 1;// 1受理 2不受理
    private String ifcontent = "受理";//受理填受理，不受理填理由
    private String orderNo;

    private String lxrID = "";
    private List<CustomListModel> model2 = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_workorder_accept;
    }

    @Override
    public WorkorderAcceptPresnet newP() {
        return new WorkorderAcceptPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_workorder_accept));
        tvWoSlbold.getPaint().setFakeBoldText(true);
        tvWoTj.getPaint().setFakeBoldText(true);

        orderNo = getIntent().getStringExtra(Constants.SHOUHOU_ORDERNO);

        models = new ArrayList<>();

        getP().customList();
    }

    @OnClick({R.id.iv_login_back, R.id.ll_wo_sl, R.id.ll_wo_tj, R.id.but_wo_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_wo_sl:
                Intent intent = new Intent(this, IfAcceptActivity.class);
                intent.putExtra(Constants.IF_ACCEPT_TYPE,iftype);
                intent.putExtra(Constants.IF_ACCEPT_CONTENT,ifcontent);
                startActivityForResult(intent, Constants.IF_ACCEPT);
                break;
            case R.id.ll_wo_tj:
                if(model2.size() == 0) {
                    lxrID = "";
                }else {
                    lxrID = "";
                    for(int i = 0; i < model2.size(); i++) {
                        if(TextUtils.isEmpty(lxrID)) {
                            lxrID = String.valueOf(models.get(i).getData_id());
                        }else {
                            lxrID = lxrID + "," + models.get(i).getData_id();
                        }
                    }
                }
                Intent intent1 = new Intent(this, SelectCustomActivity.class);
                intent1.putExtra(Constants.SELECT_CUSTOMID,lxrID);
                startActivityForResult(intent1, Constants.SELECT_CUSTOM);
                break;
            case R.id.but_wo_commit:
//                String lxr = "";
//                for(int i = 0; i < models.size(); i++) {
//                    WorkorderTujianModel model = models.get(i);
//                    String type = model.getType();
//                    String name = model.getName();
//                    String phone = model.getPhone();
//                    if(!TextUtils.isEmpty(type) && !TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone)) {
//                        if(i == 0) {
//                            lxr = type + "-" + name + "-" + phone;
//                        }else {
//                            lxr = lxr + "," + type + "-" + name + "-" + phone;
//                        }
//                    }
//                }
                getP().workorderAccept(orderNo,iftype ,ifcontent,lxrID);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.IF_ACCEPT == requestCode) {
            if (data != null) {
                iftype = data.getIntExtra(Constants.IF_ACCEPT_TYPE, 1);
                if(iftype == 1) {
                    tvWoSl.setText("受理");
                    tvWoSl.setTextColor(getResources().getColor(R.color.color_606266));
                    ifcontent = "受理";
                }else  if(iftype == 2) {
                    tvWoSl.setText("不受理");
                    tvWoSl.setTextColor(getResources().getColor(R.color.color_3D7EFF));
                    ifcontent = data.getStringExtra(Constants.IF_ACCEPT_CONTENT);
                }
            }
        }else if (Constants.SELECT_CUSTOM == requestCode) {
            if (data != null) {
                lxrID = data.getStringExtra(Constants.SELECT_CUSTOMID);
                if(!TextUtils.isEmpty(lxrID)) {
                    model2.clear();
                    String[] split = lxrID.split(",");
                    for(int i = 0; i < models.size(); i++) {
                        for(int i1 = 0; i1 < split.length; i1++) {
                            if(String.valueOf(models.get(i).getData_id()).equals(split[i1])) {
                                model2.add(models.get(i));
                            }
                        }
                    }
                    adapter.setData(model2);
                }
            }
        }
    }

    public void workorderAcceptSuccess() {
        ToastUtil.showToast(this,"受理成功");
        Intent intent = new Intent();
        intent.putExtra("sadsa", "Fafas");
        setResult(Constants.ACCEPT_GONGDAN,intent);
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.GONGDAN);
        RxBus.getInstance().post(eventMsg);
        finish();
    }

    public void customListSuccess(List<CustomListModel> model) {
        this.models = model;

        adapter = new WorkorderTujianAdapter(this);
        woListview.setAdapter(adapter);
        adapter.setmOnitemclickLisenter(new WorkorderTujianAdapter.OnitemclickLisenter() {
            @Override
            public void onItemClick(View view, int position) {
                model2.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
