package com.mfzn.deepuses.activity.myteam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.company.CompanyScaleAdapter;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.myTeam.CompanyScaleModel;
import com.mfzn.deepuses.model.myTeam.TeamManageModel;
import com.mfzn.deepuses.present.myteam.CompanyScalePresent;
import com.mfzn.deepuses.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompanyScaleActivity extends BaseMvpActivity<CompanyScalePresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.sc_listview)
    ListView scListview;

    @Override
    public int getLayoutId() {
        return R.layout.activity_company_scale;
    }

    @Override
    public CompanyScalePresent newP() {
        return new CompanyScalePresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("公司规模");

        getP().companyScale();
    }

    @OnClick(R.id.iv_login_back)
    public void onViewClicked() {
        finish();
    }

    public void companyScale(List<CompanyScaleModel> model) {
        CompanyScaleAdapter adapter = new CompanyScaleAdapter(this,model);
        scListview.setAdapter(adapter);

        scListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CompanyScaleModel companyScaleModel = model.get(position);
                Intent intent = new Intent();
                intent.putExtra(Constants.COMPANY_SCALE_NAME, companyScaleModel.getScale());
                intent.putExtra(Constants.COMPANY_SCALE_ID, companyScaleModel.getScale_id() + "");
                setResult(Constants.COMPANY_SCALE,intent);
                finish();
            }
        });
    }
}
