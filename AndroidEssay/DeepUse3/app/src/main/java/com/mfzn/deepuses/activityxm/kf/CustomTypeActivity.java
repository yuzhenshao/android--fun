package com.mfzn.deepuses.activityxm.kf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.xiangmu.CustomSettingAdapter;
import com.mfzn.deepuses.adapter.xiangmu.CustomTypeAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.xiangmu.CustomListModel;
import com.mfzn.deepuses.model.xiangmu.CustomTypeModel;
import com.mfzn.deepuses.present.xmkefu.AddCustomPresnet;
import com.mfzn.deepuses.present.xmkefu.CustomTypePresnet;
import com.mfzn.deepuses.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomTypeActivity extends BaseMvpActivity<CustomTypePresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.ty_listview)
    ListView tyListview;

    private int positions = -1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_custom_type;
    }

    @Override
    public CustomTypePresnet newP() {
        return new CustomTypePresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_custom_setting));

        positions = getIntent().getIntExtra(Constants.KEFU_TYPE_POSI, -1);

        getP().customType();
    }

    @OnClick(R.id.iv_login_back)
    public void onViewClicked() {
        finish();
    }

    public void customTypeSuccess(List<CustomTypeModel> models) {
        CustomTypeAdapter adapter = new CustomTypeAdapter(this,models,positions);
        tyListview.setAdapter(adapter);

        tyListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomTypeModel customTypeModel = models.get(position);
                Intent intent = new Intent();
                intent.putExtra(Constants.KEFU_TYPE_ID, customTypeModel.getData_id() + "");
                intent.putExtra(Constants.KEFU_TYPE_NAME, customTypeModel.getName());
                intent.putExtra(Constants.KEFU_TYPE_POSI, position);
                setResult(Constants.KEFU_TYPE,intent);
                finish();
            }
        });
    }
}
