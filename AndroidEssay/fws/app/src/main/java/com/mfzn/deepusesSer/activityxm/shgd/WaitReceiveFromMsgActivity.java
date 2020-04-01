package com.mfzn.deepusesSer.activityxm.shgd;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.bean.request.AcceptSendOrderRequest;
import com.mfzn.deepusesSer.fragment.shouhou.ChuliGuochengFragment;
import com.mfzn.deepusesSer.fragment.shouhou.GongdanShuxingFragment;
import com.mfzn.deepusesSer.model.msg.Message;
import com.mfzn.deepusesSer.model.xiangmu.WorkorderListModel;
import com.mfzn.deepusesSer.net.HttpResult;
import com.mfzn.deepusesSer.popmune.DropPopMenu;
import com.mfzn.deepusesSer.popmune.MenuItem;
import com.mfzn.deepusesSer.present.shouhou.JiedanFromMsgPresnet;
import com.mfzn.deepusesSer.present.shouhou.JiedanPresnet;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.utils.PhoneUtils;
import com.mfzn.deepusesSer.view.MyDuiZhangPagerAdapter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class WaitReceiveFromMsgActivity extends BaseMvpActivity<JiedanFromMsgPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.ll_bass_select)
    LinearLayout llBassSelect;
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

    @BindView(R.id.ll_bottom)
    LinearLayout ll_bottom;
    @BindView(R.id.ll_bottom2)
    LinearLayout ll_bottom2;

    private String contactPhone;
    private String orderNo;
    private String msgId= "";

    PopupWindow mPopupWindow;
    @Override
    public int getLayoutId() {
        return R.layout.activity_wait_receive_from_msg;
    }

    @Override
    public JiedanFromMsgPresnet newP() {
        return new JiedanFromMsgPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_stay_accept));
        llBassSelect.setVisibility(View.GONE);
        tvAccTypename.getPaint().setFakeBoldText(true);

        Message.DataBeanX.DataBean.OrderInfoBean dataBean = (Message.DataBeanX.DataBean.OrderInfoBean) getIntent().getSerializableExtra(Constants.SHOUHOU_DETAILS);
        msgId = getIntent().getStringExtra("msgId");

        if (dataBean.getStatus() == 3){
            ll_bottom.setVisibility(View.VISIBLE);
            ll_bottom2.setVisibility(View.GONE);
        }else if (dataBean.getStatus() == 6){
            ll_bottom.setVisibility(View.GONE);
            ll_bottom2.setVisibility(View.VISIBLE);
        }else{
            ll_bottom.setVisibility(View.GONE);
        }

        if (dataBean.getStatus() == 1){
            tvAccSl.setText("待受理");
        }else if (dataBean.getStatus() == 2){
            tvAccSl.setText("待派工");
        }else if (dataBean.getStatus() == 3){
            tvAccSl.setText("待接单");
        }else if (dataBean.getStatus() == 4){
            tvAccSl.setText("服务中");
        }else if (dataBean.getStatus() == 5){
            tvAccSl.setText("待评价");
        }else if (dataBean.getStatus() == 6){
            tvAccSl.setText("已评价");
        }else if (dataBean.getStatus() == 7){
            tvAccSl.setText("已取消");
        }else if (dataBean.getStatus() == 8){
            tvAccSl.setText("已关闭");
        }
        getP().setRead(String.valueOf(msgId));
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

    @OnClick({R.id.iv_login_back, R.id.ll_bass_select, R.id.ll_acc_phone, R.id.but_refuse,R.id.but_accept,R.id.but_look})
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
            case R.id.but_refuse:
                showPopWindow(getWindow().getDecorView());
                break;
            case R.id.but_accept:
                Intent intent = new Intent(WaitReceiveFromMsgActivity.this,JiedanHuizhiActivity.class);
                intent.putExtra("orderNo",orderNo);
                startActivityForResult(intent,999);
                break;
            case R.id.but_look:
                Intent intent2 = new Intent(WaitReceiveFromMsgActivity.this,PingjiaDetailActivity.class);
                intent2.putExtra("orderNo",orderNo);
                startActivity(intent2);
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999) {
            if (resultCode == 0){
                finish();
            }
        }
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
                startActivity(new Intent(WaitReceiveFromMsgActivity.this, EditWorkorderActivity.class));
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



    /*
     * 弹出选择直播方式的弹框
     * View v ：显示在那个父view内
     * int convertViewResource ：要填充到popupWindow中的布局文件id
     * int drawbelResource ：int drawbelResource
     * */
    private void showPopWindow(View parentView) {
        //创建一个popUpWindow
        View popLayout = LayoutInflater.from(this).inflate(R.layout.pop_refuse_jiedan, null);
//        //给popUpWindow内的空间设置点击事件
//        popLayout.findViewById(R.id.tv_pop_web).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mPopupWindow.isShowing()) {
//                    mPopupWindow.dismiss();
//                }
//                loadHtml(mQqSportWebUrl);
//            }
//        });
        EditText etReason = (EditText) popLayout.findViewById(R.id.et_reaon);
        popLayout.findViewById(R.id.but_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }

                AcceptSendOrderRequest request=new AcceptSendOrderRequest();
                request.setOrderNo(orderNo);
                request.setIsAccept("2");
                request.setAcceptNote(etReason.getText().toString());
                getP().jiedan(request);
            }
        });
        if (mPopupWindow == null) {
            //实例化一个popupWindow
            mPopupWindow =
                    new PopupWindow(popLayout, WindowManager.LayoutParams.MATCH_PARENT, 430);
            //产生背景变暗效果
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.alpha = 0.4f;
            getWindow().setAttributes(lp);
            //点击外面popupWindow消失
            mPopupWindow.setOutsideTouchable(true);
            //popupWindow获取焦点
            mPopupWindow.setFocusable(true);
            //popupWindow设置开场动画风格
            //popupWindow.setAnimationStyle(R.style.popupWindow_anim);
            //刷新popupWindow
            //popupWindow.update();

            //设置popupWindow消失时的监听
            mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                //在dismiss中恢复透明度
                public void onDismiss() {
                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                    lp.alpha = 1f;
                    getWindow().setAttributes(lp);
                }
            });
            mPopupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
        } else {
            //如果popupWindow正在显示，接下来隐藏
            if (mPopupWindow.isShowing()) {
                mPopupWindow.dismiss();
            } else {
                //产生背景变暗效果
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 0.4f;
                getWindow().setAttributes(lp);
                mPopupWindow.showAtLocation(parentView, Gravity.CENTER, 0, 0);
            }
        }
    }

    public void jiedanResult(HttpResult result){
        showMessage(result.getMsg());
        finish();
    }

    public void setReadSuccess(HttpResult result) {

    }
}
