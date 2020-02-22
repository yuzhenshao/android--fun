package com.mfzn.deepuses.activity.khgl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mfzn.deepuses.AppManager;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.khgl.MultipleSelectAdapter;
import com.mfzn.deepuses.adapter.khgl.ShareCusAdapter;
import com.mfzn.deepuses.adapter.khgl.ShareSelCusAdapter;
import com.mfzn.deepuses.adapter.khgl.ShareSelectCusAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.khgl.MyShareModel;
import com.mfzn.deepuses.model.khgl.WholeCustomerModel;
import com.mfzn.deepuses.present.customer.MyShnewPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.view.MyRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyShnewActivity extends BaseMvpActivity<MyShnewPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_bass_content)
    TextView tvBassContent;
    @BindView(R.id.tv_share_name)
    TextView tvShareName;
    @BindView(R.id.iv_share_vip)
    ImageView ivShareVip;
    @BindView(R.id.share_recyleview)
    MyRecyclerView shareRecyleview;
    @BindView(R.id.ba_recycleview)
    RecyclerView baRecycleview;

    private ShareSelectCusAdapter czzxAdapter;
    private List<MyShareModel.DataBean.CustomerInfoBean> customerInfo;

    private String shareID;
    private String comID;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_shnew;
    }

    @Override
    public MyShnewPresnet newP() {
        return new MyShnewPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("共享消息");
        tvBassContent.setVisibility(View.VISIBLE);
        tvBassContent.setText("全选");

        shareID = getIntent().getStringExtra(Constants.GD_DETAILS);
        comID = getIntent().getStringExtra(Constants.GD_DETAILS_ID);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        baRecycleview.setLayoutManager(layoutManager);

        getP().myShnew(comID,shareID);
    }

    @OnClick({R.id.iv_login_back, R.id.tv_ba_move,R.id.tv_bass_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                Intent intent = new Intent();
                intent.putExtra("sadsa", "saf");
                setResult(Constants.LOOK_NEWS,intent);
                finish();
                break;
            case R.id.tv_bass_content:
                if(customerInfo != null && customerInfo.size() != 0) {
                    for(int i = 0; i < customerInfo.size(); i++) {
                        int isAccept = customerInfo.get(i).getIsAccept();
                        if(isAccept != 1) {
                            customerInfo.get(i).setSelectType(true);
                        }
                    }
                    czzxAdapter.notifyDataSetChanged();
                    ShareSelCusAdapter recycleAdapter = new ShareSelCusAdapter(MyShnewActivity.this,customerInfo);
                    baRecycleview.setAdapter(recycleAdapter);
                }
                break;
            case R.id.tv_ba_move:
                if(customerInfo != null && customerInfo.size() != 0) {
                    String sss = null;
                    for(int i = 0; i < customerInfo.size(); i++) {
                        int isAccept = customerInfo.get(i).getIsAccept();
                        boolean selectType = customerInfo.get(i).getSelectType();
                        if(isAccept != 1 && selectType) {
                            if(TextUtils.isEmpty(sss)) {
                                sss = String.valueOf(customerInfo.get(i).getCustomerID());
                            }else {
                                sss = sss + "," + String.valueOf(customerInfo.get(i).getCustomerID());
                            }
                        }
                    }
                    if(!TextUtils.isEmpty(sss)) {
                        getP().setShareSel(comID,shareID,sss);
                    }
                }
                break;
        }
    }

    public void myShnewSuccess(MyShareModel models) {

        MyShareModel.DataBean dataBean = models.getData().get(0);

        tvShareName.setText(dataBean.getCompanyName());

        int customerLevelID = dataBean.getCompanyLevel();
        if (customerLevelID == 1) {
            ivShareVip.setImageResource(R.mipmap.br_vip1);
        } else if (customerLevelID == 2) {
            ivShareVip.setImageResource(R.mipmap.br_vip2);
        } else if (customerLevelID == 3) {
            ivShareVip.setImageResource(R.mipmap.br_vip3);
        } else if (customerLevelID == 4) {
            ivShareVip.setImageResource(R.mipmap.br_vip4);
        } else if (customerLevelID == 5) {
            ivShareVip.setImageResource(R.mipmap.br_vip5);
        } else if (customerLevelID == 6) {
            ivShareVip.setImageResource(R.mipmap.br_vip6);
        } else {
            ivShareVip.setImageResource(0);
        }

        customerInfo = dataBean.getCustomerInfo();
        if(customerInfo != null && customerInfo.size() != 0) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            shareRecyleview.setLayoutManager(layoutManager);
            czzxAdapter = new ShareSelectCusAdapter(MyShnewActivity.this, customerInfo);
            shareRecyleview.setAdapter(czzxAdapter);

            czzxAdapter.setOnItemClickListener(new ShareSelectCusAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    MyShareModel.DataBean.CustomerInfoBean customerInfoBean = customerInfo.get(position);
                    if(customerInfoBean.getIsAccept() != 1) {
                        if (customerInfoBean.getSelectType()) {
                            customerInfoBean.setSelectType(false);
                        } else {
                            customerInfoBean.setSelectType(true);
                        }
                        czzxAdapter.notifyDataSetChanged();
                        ShareSelCusAdapter recycleAdapter = new ShareSelCusAdapter(MyShnewActivity.this,customerInfo);
                        baRecycleview.setAdapter(recycleAdapter);
                    }
                }
            });
        }
    }

    public void setShareSelSuccess() {
        ToastUtil.showToast(this,"共享成功");
        Intent intent = new Intent();
        intent.putExtra("sadsa", "saf");
        setResult(Constants.LOOK_NEWS,intent);
        finish();
    }

    //点击返回键返回到桌面
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.putExtra("sadsa", "saf");
            setResult(Constants.LOOK_NEWS,intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
