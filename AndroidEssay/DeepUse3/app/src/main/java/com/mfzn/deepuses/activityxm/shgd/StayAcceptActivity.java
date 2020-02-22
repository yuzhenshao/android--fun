package com.mfzn.deepuses.activityxm.shgd;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mfzn.deepuses.MainActivity;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.jiagou.ManageJiagou2Activity;
import com.mfzn.deepuses.activitynews.ProjectMsgActivity;
import com.mfzn.deepuses.adapter.jiagou.ZuzhiPersonalAdapter;
import com.mfzn.deepuses.adapter.xiangmu.AddPhotoAdapter;
import com.mfzn.deepuses.adapter.xiangmu.ShouliListviewAdapter;
import com.mfzn.deepuses.adapter.xiangmu.ShouliPhotoAdapter;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.fragment.fx.HangyeFragment;
import com.mfzn.deepuses.fragment.fx.JishuFragment;
import com.mfzn.deepuses.fragment.fx.XinpinFragment;
import com.mfzn.deepuses.fragment.fx.YunyingFragment;
import com.mfzn.deepuses.fragment.xm.ChuliGuochengFragment;
import com.mfzn.deepuses.fragment.xm.GongdanShuxingFragment;
import com.mfzn.deepuses.model.LookQuanxianModel;
import com.mfzn.deepuses.model.xiangmu.WorkorderListModel;
import com.mfzn.deepuses.model.xx.MsgTdxxModel;
import com.mfzn.deepuses.popmune.DropPopMenu;
import com.mfzn.deepuses.popmune.MenuItem;
import com.mfzn.deepuses.present.xmgd.DeleteWorkorderPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.PhoneUtils;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.view.MyDuiZhangPagerAdapter;
import com.mfzn.deepuses.view.MyListview;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.ModifyBmNameDialog;
import com.wevey.selector.dialog.WeixinTishiDialog1;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StayAcceptActivity extends BaseMvpActivity<DeleteWorkorderPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.ll_bass_select)
    LinearLayout llBassSelect;
    @BindView(R.id.ll_bass_detele)
    LinearLayout llBassDelect;
    @BindView(R.id.tv_acc_type)
    TextView tvAccType;
    @BindView(R.id.tv_acc_typename)
    TextView tvAccTypename;
    @BindView(R.id.tv_acc_sl)
    TextView tvAccSl;
    @BindView(R.id.tv_acc_address)
    TextView tvAccAddress;
    @BindView(R.id.tv_acc_name)
    TextView tvAccName;
    @BindView(R.id.tv_acc_phone)
    TextView tvAccPhone;

    @BindView(R.id.gd_indicator)
    MagicIndicator gdIndicator;
    @BindView(R.id.gd_viewpager)
    ViewPager gdViewpager;

    private String contactPhone;
    private String orderNo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_stay_accept;
    }

    @Override
    public DeleteWorkorderPresent newP() {
        return new DeleteWorkorderPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_stay_accept));
//        llBassSelect.setVisibility(View.VISIBLE);
        llBassDelect.setVisibility(View.VISIBLE);
        tvAccTypename.getPaint().setFakeBoldText(true);

        WorkorderListModel.DataBean dataBean = (WorkorderListModel.DataBean) getIntent().getSerializableExtra(Constants.SHOUHOU_DETAILS);

        orderNo = dataBean.getOrderNo();
        tvAccType.setText(orderNo);
        int shType = dataBean.getShType();
        if(shType == 1) {//0全部  1故障保修  2维护升级
            tvAccTypename.setTextColor(getResources().getColor(R.color.color_3D7EFF));
        }else if(shType == 2) {
            tvAccTypename.setTextColor(getResources().getColor(R.color.color_62C33A));
        }
        tvAccTypename.setText(dataBean.getShTypeName());
        tvAccAddress.setText(dataBean.getDetailAddress());
        tvAccName.setText(dataBean.getContactName());
        contactPhone = dataBean.getContactPhone();
        tvAccPhone.setText(contactPhone);

        List<String> mDataList = new ArrayList<>();
        mDataList.add("工单属性");
        mDataList.add("处理过程");

        List<Fragment> list = new ArrayList<>();

        GongdanShuxingFragment shuxingFragment = new GongdanShuxingFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.SHOUHOU_ORDERNO, orderNo);
        shuxingFragment.setArguments(bundle);//数据传递到fragment中
        list.add(shuxingFragment);

        ChuliGuochengFragment guochengFragment = new ChuliGuochengFragment();
        bundle.putString(Constants.SHOUHOU_ORDERNO, orderNo);
        guochengFragment.setArguments(bundle);//数据传递到fragment中
        list.add(guochengFragment);

        gdViewpager.setAdapter(new MyDuiZhangPagerAdapter(getSupportFragmentManager(), list));

        initMagicIndicator(mDataList);
    }

    @OnClick({R.id.iv_login_back, R.id.ll_bass_select, R.id.ll_acc_phone, R.id.but_acc_sl, R.id.ll_bass_detele})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_bass_select:
                onClickPopIcon(view);
                break;
            case R.id.ll_acc_phone:
                if (!TextUtils.isEmpty(contactPhone)) {
                    PhoneUtils.dialogPhone(this, contactPhone);
                }
                break;
            case R.id.but_acc_sl:
                Intent intent = new Intent(this, WorkorderAcceptActivity.class);
                intent.putExtra(Constants.SHOUHOU_ORDERNO, orderNo);
                startActivityForResult(intent,Constants.ACCEPT_GONGDAN);
                break;
            case R.id.ll_bass_detele:
                new WeixinTishiDialog1.Builder(this)
                        .setHeight(1f)  //屏幕高度*0.23
                        .setWidth(1f)  //屏幕宽度*0.65
                        .setContentText("确定要删除该工单吗?")
                        .setTitle("提示")
                        .setCanceledOnTouchOutside(false)
                        .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<WeixinTishiDialog1>() {
                            @Override
                            public void clickLeftButton(WeixinTishiDialog1 dialog, View view) {
                                dialog.dismiss();
                            }

                            @Override
                            public void clickRightButton(WeixinTishiDialog1 dialog, View view) {
                                dialog.dismiss();
                                setDelete();
                            }
                        })
                        .build()
                        .show();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.ACCEPT_GONGDAN == requestCode) {
            if (data != null) {
                finish();
            }
        }
    }

    private void setDelete() {
        new ModifyBmNameDialog.Builder(this)
                .setHeight(1f)  //屏幕高度*0.23
                .setWidth(1f)  //屏幕宽度*0.65
                .setContentHint("请输入删除原因")
                .setmTitle("修改工单")
                .setLetfText("取消")
                .setRightText("删除")
                .setCanceledOnTouchOutside(true)
                .setSingleListener(new DialogInterface.OnLeftAndRightClick2Listener<ModifyBmNameDialog>() {
                    @Override
                    public void clickLeftButton(ModifyBmNameDialog dialog, View view) {
                        dialog.dismiss();
                    }

                    @Override
                    public void clickRightButton(ModifyBmNameDialog dialog, View view, EditText text) {
                        if(TextUtils.isEmpty(text.getText().toString().trim())) {
                            ToastUtil.showToast(StayAcceptActivity.this,"请输入删除原因");
                        }else {
                            getP().deleteWorkorder(orderNo, text.getText().toString().trim());
                        }
                    }
                })
                .build()
                .show();
    }

    public void deleteWorkorderSuccess() {
        ToastUtil.showToast(this,"删除成功");
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.GONGDAN);
        RxBus.getInstance().post(eventMsg);
        finish();
    }

    public void onClickPopIcon(View view) {
        DropPopMenu dropPopMenu = new DropPopMenu(this);
        dropPopMenu.setTriangleIndicatorViewColor(Color.WHITE);
        dropPopMenu.setBackgroundResource(R.drawable.yuanjiao_ffffff_bg_shape);
        dropPopMenu.setItemTextColor(Color.BLACK);
        dropPopMenu.setIsShowIcon(true);
        dropPopMenu.setOnItemClickListener(new DropPopMenu.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id, MenuItem menuItem) {
                Intent intent = new Intent(StayAcceptActivity.this, EditWorkorderActivity.class);
                intent.putExtra(Constants.SHOUHOU_ORDERNO, orderNo);
                startActivityForResult(intent,Constants.ACCEPT_GONGDAN);
            }
        });
        dropPopMenu.setMenuList(getIconMenuList());
        dropPopMenu.show(view);
    }

    private List<MenuItem> getIconMenuList() {
        List<MenuItem> list = new ArrayList<>();
        list.add(new MenuItem(R.mipmap.jiagou_bianji, 1, "编辑工单"));
        return list;
    }

    private void initMagicIndicator(List<String> mDataList) {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);//设置为充满屏幕
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(context);
                View customLayout = LayoutInflater.from(context).inflate(R.layout.simple_pager_title_layout, null);
                final TextView titleText = (TextView) customLayout.findViewById(R.id.title_text);
                titleText.setText(mDataList.get(index));
                commonPagerTitleView.setContentView(customLayout);

                commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {

                    @Override
                    public void onSelected(int index, int totalCount) {
                        titleText.postInvalidate();
                        titleText.getPaint().setFakeBoldText(true);
                    }

                    @Override
                    public void onDeselected(int index, int totalCount) {
                        titleText.postInvalidate();
                        titleText.getPaint().setFakeBoldText(false);
                    }

                    @Override
                    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
                    }

                    @Override
                    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
                    }
                });

                commonPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gdViewpager.setCurrentItem(index);
                    }
                });
                return commonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(2);//自己设置线的宽度
                linePagerIndicator.setLineWidth(180);
                linePagerIndicator.setLineHeight(5);
                linePagerIndicator.setColors(getResources().getColor(R.color.color_3D7EFF));
                return linePagerIndicator;
            }
        });
        gdIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(gdIndicator, gdViewpager);
    }
}
