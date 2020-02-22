package com.mfzn.deepuses.activity.khgl;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.khgl.SelectCusAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.khgl.SelectCusModel;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.present.customer.AddSharePresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddShareActivity extends BaseMvpActivity<AddSharePresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.ll_add_com)
    LinearLayout llAddCom;
    @BindView(R.id.ll_add_com2)
    LinearLayout llAddCom2;
    @BindView(R.id.tv_add_name)
    TextView tvAddName;
//    @BindView(R.id.iv_add_vip)
//    ImageView ivAddVip;
    @BindView(R.id.ll_add_com3)
    LinearLayout llAddCom3;
    @BindView(R.id.ll_add_cus)
    LinearLayout llAddCus;
    @BindView(R.id.ll_add_cus2)
    LinearLayout llAddCus2;
    @BindView(R.id.add_listview)
    ListView addListview;
    @BindView(R.id.but_add_commit)
    Button butAddCommit;

    private String companyID;
    private List<SelectCusModel> modelList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_share;
    }

    @Override
    public AddSharePresnet newP() {
        return new AddSharePresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("新建共享");
    }

    @OnClick({R.id.iv_login_back, R.id.ll_add_com, R.id.ll_add_com2, R.id.ll_add_cus, R.id.ll_add_cus2, R.id.but_add_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_add_com:
                startActivityForResult(new Intent(this, SelectComActivity.class), Constants.SELECT_COM);
                break;
            case R.id.ll_add_com2:
                startActivityForResult(new Intent(this, SelectComActivity.class), Constants.SELECT_COM);
                break;
            case R.id.ll_add_cus:
                startActivityForResult(new Intent(this, MultipleSelect2Activity.class), Constants.SELECT_CUS);
                break;
            case R.id.ll_add_cus2:
                startActivityForResult(new Intent(this, MultipleSelect2Activity.class), Constants.SELECT_CUS);
                break;
            case R.id.but_add_commit:
                String sss = null;
                for(int i = 0; i < modelList.size(); i++) {
                    if(TextUtils.isEmpty(sss)) {
                        sss = modelList.get(i).getId();
                    }else {
                        sss = sss + "," + modelList.get(i).getId();
                    }
                }
                getP().addShare(sss,companyID);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.SELECT_COM == requestCode) {
            if (data != null) {
                llAddCom.setVisibility(View.GONE);
                llAddCom2.setVisibility(View.VISIBLE);
                llAddCom3.setVisibility(View.VISIBLE);

                companyID = data.getStringExtra(Constants.COM_ID);
                String name = data.getStringExtra(Constants.COM_NAME);
                tvAddName.setText(name);

//                switch (level) {
//                    case "1":
//                        ivAddVip.setImageResource(R.mipmap.br_vip1);
//                        break;
//                    case "2":
//                        ivAddVip.setImageResource(R.mipmap.br_vip2);
//                        break;
//                    case "3":
//                        ivAddVip.setImageResource(R.mipmap.br_vip3);
//                        break;
//                    case "4":
//                        ivAddVip.setImageResource(R.mipmap.br_vip4);
//                        break;
//                    case "5":
//                        ivAddVip.setImageResource(R.mipmap.br_vip5);
//                        break;
//                    case "6":
//                        ivAddVip.setImageResource(R.mipmap.br_vip6);
//                        break;
//                }

                setClick();
            }
        } else if (Constants.SELECT_CUS == requestCode) {
            if (data != null) {
                llAddCus.setVisibility(View.GONE);
                llAddCus2.setVisibility(View.VISIBLE);
                addListview.setVisibility(View.VISIBLE);

                modelList.clear();

                String id = data.getStringExtra(Constants.CUS_ID);
                String name = data.getStringExtra(Constants.CUS_NAME);
                String level = data.getStringExtra(Constants.CUS_LEVEL);
                String pro = data.getStringExtra(Constants.CUS_PRO);
                String[] split = id.split(",");
                String[] split2 = name.split(",");
                String[] split3 = level.split(",");
                String[] split4 = pro.split("/");
                for(int i = 0; i < split.length; i++) {
                    modelList.add(new SelectCusModel(split[i],split2[i],split3[i],split4[i]));
                }

                SelectCusAdapter adapter = new SelectCusAdapter(this,modelList);
                addListview.setAdapter(adapter);

                adapter.setOnDelItemClickListener(new SelectCusAdapter.OnDelItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        modelList.remove(position);
                        adapter.notifyDataSetChanged();
                        if(modelList.size() == 0) {
                            llAddCus.setVisibility(View.VISIBLE);
                            llAddCus2.setVisibility(View.GONE);
                            addListview.setVisibility(View.GONE);
                        }
                    }
                });

                setClick();
            }
        }
    }

    private void setClick() {
        if(!TextUtils.isEmpty(companyID) && modelList.size() != 0) {
            butAddCommit.setEnabled(true);
            butAddCommit.setBackgroundResource(R.drawable.yuanjiao_4a9012_button_shape);
        }else {
            butAddCommit.setEnabled(false);
            butAddCommit.setBackgroundResource(R.drawable.yuanjiao_bfc2cc_button_shape);
        }
    }

    public void addShareSuccess() {
        ToastUtil.showToast(this, "新建成功");
        Intent intent = new Intent();
        intent.putExtra("vcxz", "vzxv");
        setResult(Constants.ADD_SHARE, intent);
        finish();
    }
}
