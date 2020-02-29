package com.mfzn.deepuses.activity.jiagou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.jiagou.SelectDepartmentAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.present.jiagou.DepartmentPresent;
import com.mfzn.deepuses.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DepartmentActivity extends BaseMvpActivity<DepartmentPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_bass_comlate)
    TextView tvBassComlate;
    @BindView(R.id.deListview)
    ListView deListview;

    private List<ZuzhiJiagouModel.SonsBean> sons;
    private String departmentID;
    private String departmentName;

    @Override
    public int getLayoutId() {
        return R.layout.activity_department;
    }

    @Override
    public DepartmentPresent newP() {
        return new DepartmentPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_department));
        tvBassComlate.setVisibility(View.VISIBLE);

        getP().eepartment();
    }

    @OnClick({R.id.iv_login_back, R.id.tv_bass_comlate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_bass_comlate:
                for (int i = 0 ; i < sons.size() ; i++){
                    if(sons.get(i).getSelectDe()) {
                        departmentID = sons.get(i).getDepartmentID() + "";
                        departmentName = sons.get(i).getDepartmentName();
                        break;
                    }
                    List<ZuzhiJiagouModel.SonsBean> sonsBeans = sons.get(i).getSons();
                    if(sonsBeans != null && sonsBeans.size() != 0) {
                        for (int j = 0 ; j < sonsBeans.size() ; j++){
                            if(sonsBeans.get(j).getSelectDe()) {
                                departmentID = sonsBeans.get(j).getDepartmentID() + "";
                                departmentName = sonsBeans.get(j).getDepartmentName();
                                break;
                            }
                        }
                    }
                }
                Intent intent = new Intent();
                intent.putExtra(Constants.EDIT_STAFF_ID, departmentID);
                intent.putExtra(Constants.EDIT_STAFF_NAME, departmentName);
                setResult(Constants.EDIT_STAFF_BM,intent);
                finish();
                break;
        }
    }

    public void eepartmentSuccess(ZuzhiJiagouModel model) {
        sons = model.getSons();
        SelectDepartmentAdapter adapter = new SelectDepartmentAdapter(this,sons);
        deListview.setAdapter(adapter);
    }
}
