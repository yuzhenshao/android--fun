package com.mfzn.deepuses.activityxm.shgd;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activityxm.kf.CustomSettingActivity;
import com.mfzn.deepuses.activityxm.kf.EditCustomActivity;
import com.mfzn.deepuses.adapter.xiangmu.CustomSettingAdapter;
import com.mfzn.deepuses.adapter.xiangmu.SelectCustomAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.xiangmu.CustomListModel;
import com.mfzn.deepuses.present.foundxm.SelectCustomPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectCustomActivity extends BaseMvpActivity<SelectCustomPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.se_listview)
    ListView seListview;
    @BindView(R.id.tv_cus_bold)
    TextView tvCusBold;
    @BindView(R.id.ll_e_nodata)
    LinearLayout llENodata;
    @BindView(R.id.tv_se_qd)
    TextView tvSeQd;

    private List<CustomListModel> models = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_custom;
    }

    @Override
    public SelectCustomPresnet newP() {
        return new SelectCustomPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_select_custom));
        tvCusBold.getPaint().setFakeBoldText(true);

        getP().customList();
    }

    @OnClick({R.id.iv_login_back, R.id.tv_se_qd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_se_qd:
                String sss = null;
                for(int i = 0; i < models.size(); i++) {
                    if(models.get(i).getTypeSelect()) {
                        if(TextUtils.isEmpty(sss)) {
                            sss = String.valueOf(models.get(i).getData_id());
                        }else {
                            sss = sss + "," + models.get(i).getData_id();
                        }
                    }
                }
                if(TextUtils.isEmpty(sss)) {
                    ToastUtil.showToast(this,"请选择客服");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra(Constants.SELECT_CUSTOMID, sss);
                setResult(Constants.SELECT_CUSTOM,intent);
                finish();
                break;
        }
    }

    public void customListSuccess(List<CustomListModel> model) {
        if(model != null && model.size() != 0) {
            this.models = model;

            String stringExtra = getIntent().getStringExtra(Constants.SELECT_CUSTOMID);
            if(!TextUtils.isEmpty(stringExtra)) {
                String[] split = stringExtra.split(",");
                for(int i = 0; i < models.size(); i++) {
                    for(int i1 = 0; i1 < split.length; i1++) {
                        if(String.valueOf(models.get(i).getData_id()).equals(split[i1])) {
                            models.get(i).setTypeSelect(true);
                        }
                    }
                }
            }

            llENodata.setVisibility(View.GONE);
            SelectCustomAdapter adapter = new SelectCustomAdapter(this,models);
            seListview.setAdapter(adapter);

            seListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    CustomListModel customListModel = models.get(position);
                    if(customListModel.getTypeSelect()) {
                        customListModel.setTypeSelect(false);
                    }else {
                        customListModel.setTypeSelect(true);
                    }
                    adapter.notifyDataSetChanged();
                }
            });
        }else {
            llENodata.setVisibility(View.VISIBLE);
            tvSeQd.setVisibility(View.GONE);
        }
    }
}
