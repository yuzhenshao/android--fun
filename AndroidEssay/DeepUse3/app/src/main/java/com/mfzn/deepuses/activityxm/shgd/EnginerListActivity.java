package com.mfzn.deepuses.activityxm.shgd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.xiangmu.EnginerListAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.xiangmu.EnginerListModel;
import com.mfzn.deepuses.model.xiangmu.WorkorderListModel;
import com.mfzn.deepuses.present.xmgd.EnginerListPresent;
import com.mfzn.deepuses.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnginerListActivity extends BaseMvpActivity<EnginerListPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.en_listview)
    ListView enListview;
    @BindView(R.id.ll_bass_add)
    LinearLayout llBassAdd;

    @Override
    public int getLayoutId() {
        return R.layout.activity_enginer_list;
    }

    @Override
    public EnginerListPresent newP() {
        return new EnginerListPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_enginer_list));
        llBassAdd.setVisibility(View.VISIBLE);

        getP().enginerList();
    }

    @OnClick({R.id.iv_login_back, R.id.ll_bass_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_bass_add:
                startActivityForResult(new Intent(this, SelectEnginerActivity.class),Constants.SELECT_ENGINER);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.SELECT_ENGINER == requestCode) {
            if (data != null) {
                getP().enginerList();
            }
        }
    }

    public void enginerListSuccess(List<EnginerListModel> model) {
        EnginerListAdapter adapter = new EnginerListAdapter(this,model);
        enListview.setAdapter(adapter);

        enListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EnginerListModel models = model.get(position);
                Intent intent = new Intent();
                intent.putExtra(Constants.ENGINER_ID, models.getEnginerID() + "");
                intent.putExtra(Constants.ENGINER_NAME, models.getU_name());
                intent.putExtra(Constants.ENGINER_PHONE, models.getU_phone());
                setResult(Constants.ENGINER_LIST,intent);
                finish();
            }
        });
    }
}
