package com.mfzn.deepuses.activityxm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.jiagou.EditStaffActivity;
import com.mfzn.deepuses.activity.jiagou.ManageJiagou2Activity;
import com.mfzn.deepuses.activity.jiagou.ManageJiagouActivity;
import com.mfzn.deepuses.activity.jiagou.PersonalInfoActivity;
import com.mfzn.deepuses.adapter.jiagou.ManageJiagouAdapter;
import com.mfzn.deepuses.adapter.jiagou.ZuzhiDepartmentAdapter;
import com.mfzn.deepuses.adapter.xiangmu.SelectPersonAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.present.foundxm.SelectPersonPresent;
import com.mfzn.deepuses.present.jiagou.ManageJiagouPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.UserHelper;
import com.mfzn.deepuses.view.MyListview;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectPersonActivity extends BaseMvpActivity<SelectPersonPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.listview1)
    MyListview listview1;
    @BindView(R.id.listview2)
    MyListview listview2;

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_person;
    }

    @Override
    public SelectPersonPresent newP() {
        return new SelectPersonPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        String name = UserHelper.getSelectNmae();
        if(name.equals("1")) {
            tvBassTitle.setText(getString(R.string.app_select_person));
        }else if(name.equals("2")) {
            tvBassTitle.setText("选择成员");
        }

        getP().jiagouList();
    }

    @OnClick(R.id.iv_login_back)
    public void onViewClicked() {
        finish();
    }

    public void selectPersonSuccess(ZuzhiJiagouModel model) {
        SelectPersonAdapter adapter = new SelectPersonAdapter(this, model.getStaff());
        listview1.setAdapter(adapter);

        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra(Constants.SELECT_PERSON_ID, model.getStaff().get(position).getUid());
                intent.putExtra(Constants.SELECT_PERSON_NAME, model.getStaff().get(position).getStaffName());
                setResult(Constants.SELECT_PERSON,intent);
                finish();
            }
        });

        ZuzhiDepartmentAdapter zuzhiDepartmentAdapter = new ZuzhiDepartmentAdapter(this, model.getSons());
        listview2.setAdapter(zuzhiDepartmentAdapter);

        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SelectPersonActivity.this, SelectPerson2Activity.class);
                intent.putExtra(Constants.EDIT_STAFF_BM_POSI, position);
//                startActivity(intent);
                startActivityForResult(intent, Constants.SELECT_PERSON);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(Constants.SELECT_PERSON == requestCode){
            if(data != null) {
                String gwID = data.getStringExtra(Constants.SELECT_PERSON_ID);
                String name = data.getStringExtra(Constants.SELECT_PERSON_NAME);
                Intent intent = new Intent();
                intent.putExtra(Constants.SELECT_PERSON_ID, gwID);
                intent.putExtra(Constants.SELECT_PERSON_NAME, name);
                setResult(Constants.SELECT_PERSON,intent);
                finish();
            }
        }
    }
}
