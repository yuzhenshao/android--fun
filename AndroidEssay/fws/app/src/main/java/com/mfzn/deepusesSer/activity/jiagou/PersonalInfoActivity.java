package com.mfzn.deepusesSer.activity.jiagou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseActivity;
import com.mfzn.deepusesSer.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.utils.UserHelper;
import com.mfzn.deepusesSer.view.RoundImageView;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalInfoActivity extends BaseActivity {

//    @BindView(R.id.tv_bass_title)
//    TextView tvBassTitle;
//    @BindView(R.id.iv_per_icon)
//    RoundImageView ivPerIcon;
//    @BindView(R.id.tv_per_name)
//    TextView tvPerName;
//    @BindView(R.id.tv_per_team)
//    TextView tvPerTeam;
//    @BindView(R.id.tv_per_company)
//    TextView tvPerCompany;
//    @BindView(R.id.tv_per_phone)
//    TextView tvPerPhone;
//    @BindView(R.id.tv_per_bumen)
//    TextView tvPerBumen;

    private String u_phone;
    private String u_uid;

    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_info;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }
//        tvBassTitle.setText(getString(R.string.app_personal_info));
//
//        Intent intent = getIntent();
//        String type = intent.getStringExtra(Constants.PERSONAL_INFO_TYPE);
//        if(type.equals("1")) {
//            ZuzhiJiagouModel model = (ZuzhiJiagouModel) intent.getSerializableExtra(Constants.PERSONAL_INFO);
//            int extra = intent.getIntExtra(Constants.EDIT_STAFF_POSITION, 0);
//            ZuzhiJiagouModel.StaffBeanXX staffBeanXX = model.getStaff().get(extra);
//            Glide.with(this).load(ApiHelper.BASE_URL + staffBeanXX.getU_head()).into(ivPerIcon);
//            tvPerName.setText(staffBeanXX.getU_name());
//            tvPerTeam.setText(UserHelper.getCompanyName());
//            tvPerCompany.setText(staffBeanXX.getU_name());
//            u_phone = staffBeanXX.getU_phone();
//            tvPerPhone.setText(u_phone);
//            tvPerBumen.setText(model.getDepartmentName());
//            u_uid = staffBeanXX.getUid();
//        }else if(type.equals("2")) {
//            ZuzhiJiagouModel model = (ZuzhiJiagouModel) intent.getSerializableExtra(Constants.PERSONAL_INFO);
//            int extra = intent.getIntExtra(Constants.EDIT_STAFF_POSITION, 0);
//            int extra2 = intent.getIntExtra(Constants.EDIT_STAFF_POSITION2, 0);
//            ZuzhiJiagouModel.SonsBeanX.StaffBeanX staffBeanX = model.getSons().get(extra).getStaff().get(extra2);
//
//            Glide.with(this).load(ApiHelper.BASE_URL + staffBeanX.getU_head()).into(ivPerIcon);
//            tvPerName.setText(staffBeanX.getU_name());
//            tvPerTeam.setText(UserHelper.getCompanyName());
//            tvPerCompany.setText(staffBeanX.getU_name());
//            u_phone = staffBeanX.getU_phone();
//            tvPerPhone.setText(u_phone);
//            tvPerBumen.setText(model.getSons().get(extra).getDepartmentName());
//            u_uid = staffBeanX.getUid();
//        }else if(type.equals("3")) {
//            ZuzhiJiagouModel model = (ZuzhiJiagouModel) intent.getSerializableExtra(Constants.PERSONAL_INFO);
//            int extra = intent.getIntExtra(Constants.EDIT_STAFF_POSITION, 0);
//            int extra2 = intent.getIntExtra(Constants.EDIT_STAFF_POSITION2, 0);
//            int extra3 = intent.getIntExtra(Constants.EDIT_STAFF_POSITION3, 0);
//            ZuzhiJiagouModel.SonsBeanX.SonsBean.StaffBean staffBeanX = model.getSons().get(extra).getSons().get(extra2).getStaff().get(extra3);
//            Glide.with(this).load(ApiHelper.BASE_URL + staffBeanX.getU_head()).into(ivPerIcon);
//            tvPerName.setText(staffBeanX.getU_name());
//            tvPerTeam.setText(UserHelper.getCompanyName());
//            tvPerCompany.setText(staffBeanX.getU_name());
//            u_phone = staffBeanX.getU_phone();
//            tvPerPhone.setText(u_phone);
//            tvPerBumen.setText(model.getSons().get(extra).getSons().get(extra2).getDepartmentName());
//            u_uid = staffBeanX.getUid();
//        }
//    }
//
//    @OnClick({R.id.iv_login_back, R.id.ll_per_remarks})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.iv_login_back:
//                finish();
//                break;
////            case R.id.but_per_phone:
////                if (!TextUtils.isEmpty(u_phone)) {
////                    PhoneUtils.dialogPhone(this, u_phone);
////                }
////                break;
//            case R.id.ll_per_remarks:
//                Intent intent = new Intent(this, RemarksAtivity.class);
//                intent.putExtra(Constants.REMARKS_UID,u_uid);
//                startActivity(intent);
//                break;
//        }
//    }
}
