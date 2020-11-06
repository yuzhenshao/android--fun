package com.mfzn.deepuses.purchasesellsave.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.shop.ShopUserListResponse.ShopUserResponse;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.utils.PhoneUtils;
import com.mfzn.deepuses.utils.UserHelper;
import com.mfzn.deepuses.view.RoundImageView;

public class ShopStaffInfoActivity extends BasicActivity {

    private ShopUserResponse mShopUserResponse;
    private RoundImageView mUserAvatar;
    private TextView mStaffName;
    private TextView mCompanyName;
    private TextView mUserName;
    private TextView mUserPhone;
    private TextView mDepartmentName;
    private RelativeLayout mAuth;
    private RelativeLayout mBeiZhu;
    private TextView mCall;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_staff_info;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("成员信息");
        initView();
        initData();
    }

    private void initView() {
        mUserAvatar = (RoundImageView) findViewById(R.id.user_avatar);
        mStaffName = (TextView) findViewById(R.id.staff_name);
        mCompanyName = (TextView) findViewById(R.id.company_name);
        mUserName = (TextView) findViewById(R.id.user_name);
        mUserPhone = (TextView) findViewById(R.id.user_phone);
        mDepartmentName = (TextView) findViewById(R.id.department_name);
        mAuth = (RelativeLayout) findViewById(R.id.auth);
        mBeiZhu = (RelativeLayout) findViewById(R.id.bei_zhu);
        mCall = (TextView) findViewById(R.id.call);
    }

    private void initData() {
        mShopUserResponse = (ShopUserResponse) getIntent().getSerializableExtra(ParameterConstant.SHOP_STAFF);
        if (mShopUserResponse == null) {
            finish();
        }
        if(TextUtils.isEmpty(mShopUserResponse.getUserAvatar())){
            mUserAvatar.setImageResource(R.mipmap.moren_touxiang);
        }else {
            Glide.with(this).load(ApiHelper.BASE_URL + mShopUserResponse.getUserAvatar()).into(mUserAvatar);
        }
        mStaffName.setText(mShopUserResponse.getStaffName());
        mCompanyName.setText(UserHelper.getCompanyName());
        mUserName.setText(mShopUserResponse.getStaffName());
        mUserPhone.setText(mShopUserResponse.getUserPhone());
        mDepartmentName.setText(mShopUserResponse.getDepartmentName());
        mAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopStaffInfoActivity.this, ShopStaffAuthActivity.class);
                intent.putExtra(ParameterConstant.SHOP_STAFF_ID, mShopUserResponse.getUserID());
                startActivity(intent);
            }
        });
        mBeiZhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
        mCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = mShopUserResponse.getUserPhone();
                if (TextUtils.isEmpty(phone)) {
                    showToast("号码为空");
                } else {
                    PhoneUtils.dialogPhone2(ShopStaffInfoActivity.this, "拨打", phone, phone);
                }
            }
        });
    }
}
