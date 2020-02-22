package com.mfzn.deepuses.activityxm.shgd;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activityxm.kf.AddWhProjectActivity;
import com.mfzn.deepuses.activityxm.kf.CustomTypeActivity;
import com.mfzn.deepuses.adapter.xiangmu.DefendSettingAdapter;
import com.mfzn.deepuses.adapter.xiangmu.WorkorderTujianAdapter;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.xiangmu.DefendSettingModel;
import com.mfzn.deepuses.model.xiangmu.SettingInfoModel;
import com.mfzn.deepuses.model.xiangmu.WorkorderTujianModel;
import com.mfzn.deepuses.present.xmhf.DefendSettingPresent;
import com.mfzn.deepuses.present.xmhf.ShouhouSettingPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DefendSettingActivity extends BaseMvpActivity<DefendSettingPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.def_listview)
    ListView defListview;

    private DefendSettingAdapter adapter;

    private String pro_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_defend_setting;
    }

    @Override
    public DefendSettingPresent newP() {
        return new DefendSettingPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_defend_setting));

        pro_id = getIntent().getStringExtra(Constants.SHOUHOU_PROID);

        getP().settingInfo(pro_id);
    }

    @OnClick({R.id.iv_login_back, R.id.but_def_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.but_def_commit:
                Intent intent = new Intent(this, AddWhProjectActivity.class);
                intent.putExtra(Constants.SHOUHOU_PROID,pro_id);
                startActivityForResult(intent, Constants.WB_SETTING);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.WB_SETTING == requestCode) {
            if (data != null) {
                getP().settingInfo(pro_id);
            }
        }
    }

    public void deleteWbSuccess(String models) {
        ToastUtil.showToast(this,models);
        getP().settingInfo(pro_id);
    }

    public void settingInfoSuccess(SettingInfoModel models) {
        List<SettingInfoModel.WbsetBean> wbset = models.getWbset();

        adapter = new DefendSettingAdapter(this,wbset);
        defListview.setAdapter(adapter);

        adapter.setOnDeleteitemclickLisenter(new DefendSettingAdapter.OnDeleteitemclickLisenter() {
            @Override
            public void onDeteleItemClick(View view, int position) {
                SettingInfoModel.WbsetBean wbsetBean = wbset.get(position);
                String dataid = wbsetBean.getData_id() + "";
                String title = wbsetBean.getTitle();
                String nextDate = wbsetBean.getNextDate();
                String spaceDate = wbsetBean.getSpaceDate();
                getP().deleteWb(dataid,title,nextDate,spaceDate,"1");
            }
        });
    }
}
