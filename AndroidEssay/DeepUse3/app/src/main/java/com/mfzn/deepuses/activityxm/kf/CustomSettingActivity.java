package com.mfzn.deepuses.activityxm.kf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.xiangmu.CustomSettingAdapter;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.xiangmu.CustomListModel;
import com.mfzn.deepuses.model.xiangmu.WorkorderListModel;
import com.mfzn.deepuses.present.xmkefu.CustomSettingPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomSettingActivity extends BaseMvpActivity<CustomSettingPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.cus_listview)
    ListView cusListview;
    @BindView(R.id.ll_cus_data)
    LinearLayout llCusData;
    @BindView(R.id.tv_cus_bold)
    TextView tvCusBold;
    @BindView(R.id.ll_cus_nodata)
    LinearLayout llCusNodata;

    @Override
    public int getLayoutId() {
        return R.layout.activity_custom_setting;
    }

    @Override
    public CustomSettingPresnet newP() {
        return new CustomSettingPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_custom_setting));
        tvCusBold.getPaint().setFakeBoldText(true);

//        cusListview.setEmptyView(llCusNodata);

        getP().customList();
    }

    @OnClick({R.id.iv_login_back, R.id.tv_cus_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_cus_add:
                startActivityForResult(new Intent(this, AddCustomActivity.class), Constants.ADD_KEFU);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.ADD_KEFU == requestCode) {
            if (data != null) {
                getP().customList();
            }
        }
    }

    public void customListSuccess(List<CustomListModel> models) {
        if(models != null && models.size() != 0) {
            llCusNodata.setVisibility(View.GONE);
            CustomSettingAdapter adapter = new CustomSettingAdapter(this,models);
            cusListview.setAdapter(adapter);

            adapter.setmOnitemclickLisenter(new CustomSettingAdapter.OnitemclickLisenter() {
                @Override
                public void onItemClick(View view, int position) {
                    getP().deleteCustom(models.get(position).getData_id() + "");
                }
            });

            cusListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    CustomListModel customListModel = models.get(position);
                    Intent intent = new Intent(CustomSettingActivity.this, EditCustomActivity.class);
                    intent.putExtra(Constants.EDIT_KEFU_MODEL,customListModel);
                    startActivityForResult(intent, Constants.ADD_KEFU);
                }
            });
        }else {
            llCusNodata.setVisibility(View.VISIBLE);
        }
    }

    public void deleteCustomSuccess(String models) {
        ToastUtil.showToast(this,models);
        getP().customList();
    }
}
