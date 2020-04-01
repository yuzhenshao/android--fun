package com.mfzn.deepusesSer.activity.jiagou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.adapter.jiagou.ManageJiagou3Adapter;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepusesSer.present.jiagou.ManageJiagou3Present;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.utils.EventMsg;
import com.mfzn.deepusesSer.utils.RxBus;
import com.mfzn.deepusesSer.utils.ToastUtil;
import com.mfzn.deepusesSer.utils.UserHelper;
import com.mfzn.deepusesSer.view.MyListview;
import com.wevey.selector.dialog.BotomListviewDialog;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.bean.BottomListviewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class ManageJiagou3Activity extends BaseMvpActivity<ManageJiagou3Present> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_bass_close)
    TextView tvBassClose;
    @BindView(R.id.tv_bass_content)
    TextView tvBassContent;
    @BindView(R.id.et_man_search3)
    EditText etManSearch3;
    @BindView(R.id.malistview)
    MyListview malistview;
    @BindView(R.id.ll_manage3)
    LinearLayout llManage3;
    @BindView(R.id.ll_man_empty)
    LinearLayout llManEmpty;

    private int tipType = 1;// 1默认  2管理

    private ManageJiagou3Adapter zuzhiPersonalAdapter;

    private int positions;
    private int positions2;
    private List<BottomListviewModel> models;
    private ZuzhiJiagouModel jiagouModel;

    @Override
    public int getLayoutId() {
        return R.layout.activity_manage_jiagou3;
    }

    @Override
    public ManageJiagou3Present newP() {
        return new ManageJiagou3Present();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(UserHelper.getCompanyName());
        tvBassContent.setVisibility(View.VISIBLE);
        tvBassContent.setText("管理");

        etManSearch3.setHint("在" + UserHelper.getCompanyName() + "搜索");

        malistview.setEmptyView(llManEmpty);

        positions = getIntent().getIntExtra(Constants.EDIT_STAFF_BM_POSI, 0);
        positions2 = getIntent().getIntExtra(Constants.EDIT_STAFF_BM_POSI2, 0);
        models = new ArrayList<>();

        getP().jiagouList();

        RxBus.getInstance().toObservable().map(new Function<Object, EventMsg>() {
            @Override
            public EventMsg apply(Object o) throws Exception {
                return (EventMsg) o;
            }
        }).subscribe(new Consumer<EventMsg>() {
            @Override
            public void accept(EventMsg eventMsg) throws Exception {
                if (eventMsg != null) {
                    if(eventMsg.getMsg().equals(Constants.EDITSTAFF)){
                        tipType = 1;
                        getP().jiagouList();
                    }
                }
            }
        });
    }

    @OnClick({R.id.iv_login_back, R.id.tv_bass_close, R.id.tv_bass_content, R.id.et_man_search3, R.id.tv_man_yg3, R.id.tv_man_more3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_bass_close:
                tipType = 1;
                tvBassContent.setVisibility(View.VISIBLE);
                tvBassClose.setVisibility(View.GONE);
                llManage3.setVisibility(View.GONE);
                zuzhiPersonalAdapter.setShow(0);
                break;
            case R.id.tv_bass_content:
                tipType = 2;
                tvBassContent.setVisibility(View.GONE);
                tvBassClose.setVisibility(View.VISIBLE);
                llManage3.setVisibility(View.VISIBLE);
                zuzhiPersonalAdapter.setShow(1);
                break;
            case R.id.et_man_search3:
                startActivity(new Intent(this, SearchKeywordActivity.class));
                break;
            case R.id.tv_man_yg3:
                startActivity(new Intent(this, InvitationJoinActivity.class));
                break;
            case R.id.tv_man_more3:
                moreDialog();
                break;
        }
    }

    public void jiagouListSuccess(ZuzhiJiagouModel model) {
        this.jiagouModel = model;
        zuzhiPersonalAdapter = new ManageJiagou3Adapter(this,model.getSons().get(positions).getSons().get(positions2).getStaff());
        malistview.setAdapter(zuzhiPersonalAdapter);
        zuzhiPersonalAdapter.setShow(0);

        malistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(tipType == 1) {
                    Intent intent = new Intent(ManageJiagou3Activity.this, PersonalInfoActivity.class);
                    intent.putExtra(Constants.PERSONAL_INFO,model);
                    intent.putExtra(Constants.PERSONAL_INFO_TYPE,"3");
                    intent.putExtra(Constants.EDIT_STAFF_POSITION,positions);
                    intent.putExtra(Constants.EDIT_STAFF_POSITION2,positions2);
                    intent.putExtra(Constants.EDIT_STAFF_POSITION3,position);
                    startActivity(intent);
                }else if(tipType == 2) {
                    Intent intent = new Intent(ManageJiagou3Activity.this, EditStaffActivity.class);
                    intent.putExtra(Constants.EDIT_STAFF,model);
                    intent.putExtra(Constants.EDIT_STAFF_TYPE,"3");
                    intent.putExtra(Constants.EDIT_STAFF_POSITION,positions);
                    intent.putExtra(Constants.EDIT_STAFF_POSITION2,positions2);
                    intent.putExtra(Constants.EDIT_STAFF_POSITION3,position);
                    startActivity(intent);
                }
            }
        });
    }

    public void deleteBmSuccess() {
        ToastUtil.showToast(this,"删除成功");
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.EDITSTAFF);
        RxBus.getInstance().post(eventMsg);
        finish();
    }

    public void modifyBmNameSuccess() {
        ToastUtil.showToast(this,"修改成功");
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.EDITSTAFF);
        RxBus.getInstance().post(eventMsg);
        finish();
    }

    private void moreDialog() {
        models.clear();
        BottomListviewModel model = new BottomListviewModel(1, "修改当前部门名称");
        BottomListviewModel model2 = new BottomListviewModel(1, "批量移动成员");
        models.add(model);
        models.add(model2);
        if(models != null) {
            if(jiagouModel.getSons().get(positions).getSons().get(positions2).getStaff().size() == 0) {
                BottomListviewModel model3 = new BottomListviewModel(2, "删除部门");
                models.add(model3);
            }
        }
        new BotomListviewDialog.Builder(this)
                .setHeight(1f)  //屏幕高度*0.23
                .setWidth(1f)  //屏幕宽度*0.65
                .setListModel(models)
                .setCanceledOnTouchOutside(true)
                .setOnclickListener(new DialogInterface.OnTopAndBottomClickListener<BotomListviewDialog>() {
                    @Override
                    public void clickTopButton(BotomListviewDialog dialog, View view, int position) {
                        if(position == 0) {
                            addDialog();
                            dialog.dismiss();
                        }else if(position == 1) {
                            Intent intent = new Intent(ManageJiagou3Activity.this, BatchAddStaffActivity.class);
                            intent.putExtra(Constants.MOVE_STAFF_TYPE1, "3");
                            intent.putExtra(Constants.MOVE_STAFF_POSITION2, positions);
                            intent.putExtra(Constants.MOVE_STAFF_POSITION3, positions2);
                            startActivity(intent);
                            dialog.dismiss();
                        }else if(position == 2) {
                            getP().deleteBm(jiagouModel.getSons().get(positions).getSons().get(positions2).getDepartmentID() + "");
                        }
                    }

                    @Override
                    public void clickBottomButton(BotomListviewDialog dialog, View view) {
                        dialog.dismiss();
                    }
                })
                .build()
                .show();
    }

    private void addDialog() {
//        new ModifyBmNameDialog.Builder(this)
//                .setHeight(1f)  //屏幕高度*0.23
//                .setWidth(1f)  //屏幕宽度*0.65
//                .setContentHint("输入部门名称")
//                .setmTitle("修改部门名称")
//                .setLetfText("取消")
//                .setRightText("修改")
//                .setCanceledOnTouchOutside(true)
//                .setSingleListener(new DialogInterface.OnLeftAndRightClick2Listener<ModifyBmNameDialog>() {
//                    @Override
//                    public void clickLeftButton(ModifyBmNameDialog dialog, View view) {
//                        dialog.dismiss();
//                    }
//
//                    @Override
//                    public void clickRightButton(ModifyBmNameDialog dialog, View view, EditText text) {
//                        if(jiagouModel != null) {
//                            getP().modifyBmName(jiagouModel.getSons().get(positions).getSons().get(positions2).getDepartmentID() + "",
//                                    text.getText().toString().trim());
//                        }else {
//                            ToastUtil.showToast(ManageJiagou3Activity.this,"请重新加载");
//                        }
//                    }
//                })
//                .build()
//                .show();
    }
}
