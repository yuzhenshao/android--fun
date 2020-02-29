package com.mfzn.deepuses.activityxm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.xiangmu.SelectPerson3Adapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.present.foundxm.SelectPerson3Present;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.view.MyListview;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectPerson3Activity extends BaseMvpActivity<SelectPerson3Present> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.perlistview)
    MyListview perlistview;
    @BindView(R.id.ll_per_empty)
    LinearLayout llPerEmpty;

    private int positions;
    private int positions2;

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_person3;
    }

    @Override
    public SelectPerson3Present newP() {
        return new SelectPerson3Present();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_select_person));

        perlistview.setEmptyView(llPerEmpty);

        positions = getIntent().getIntExtra(Constants.EDIT_STAFF_BM_POSI, 0);
        positions2 = getIntent().getIntExtra(Constants.EDIT_STAFF_BM_POSI2, 0);

        getP().jiagouList();
    }

    @OnClick(R.id.iv_login_back)
    public void onViewClicked() {
        finish();
    }

    public void selectPersonSuccess(ZuzhiJiagouModel model) {
        SelectPerson3Adapter zuzhiPersonalAdapter = new SelectPerson3Adapter(this,model.getSons().get(positions).getSons().get(positions2).getStaff());
        perlistview.setAdapter(zuzhiPersonalAdapter);

        perlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ZuzhiJiagouModel.StaffBean staffBean = model.getSons().get(positions).getSons().get(positions2).getStaff().get(position);
                Intent intent = new Intent();
                intent.putExtra(Constants.SELECT_PERSON_ID, staffBean.getUserID());
                intent.putExtra(Constants.SELECT_PERSON_NAME, staffBean.getStaffName());
                setResult(Constants.SELECT_PERSON,intent);
                finish();
            }
        });
    }
}
