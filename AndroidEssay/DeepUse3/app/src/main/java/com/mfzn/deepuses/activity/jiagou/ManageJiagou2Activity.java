package com.mfzn.deepuses.activity.jiagou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.jiagou.ManageJiagou2Adapter;
import com.mfzn.deepuses.adapter.jiagou.ZuzhiDepartment2Adapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.present.jiagou.ManageJiagou2Present;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;
import com.mfzn.deepuses.view.MyListview;
import com.wevey.selector.dialog.BotomListviewDialog;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.ModifyBmNameDialog;
import com.wevey.selector.dialog.bean.BottomListviewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class ManageJiagou2Activity extends BaseMvpActivity<ManageJiagou2Present> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_bass_close)
    TextView tvBassClose;
    @BindView(R.id.tv_bass_content)
    TextView tvBassContent;
    @BindView(R.id.et_man_search)
    EditText etManSearch;
    @BindView(R.id.listview1)
    MyListview listview1;
    @BindView(R.id.listview2)
    MyListview listview2;
    @BindView(R.id.ll_manage)
    LinearLayout llManage;
    @BindView(R.id.ll_man_empty)
    LinearLayout llManEmpty;

    private int tipType = 1;// 1默认  2管理

    private ManageJiagou2Adapter zuzhiPersonalAdapter;

    private int positions;
    private List<BottomListviewModel> models;
    private ZuzhiJiagouModel jiagouModel;

    @Override
    public int getLayoutId() {
        return R.layout.activity_manage_jiagou2;
    }

    @Override
    public ManageJiagou2Present newP() {
        return new ManageJiagou2Present();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
//        tvBassTitle.setText(UserHelper.getCompanyName());
        tvBassContent.setVisibility(View.VISIBLE);
        tvBassContent.setText("管理");

        etManSearch.setHint("在" + UserHelper.getCompanyName() + "搜索");

        positions = getIntent().getIntExtra(Constants.EDIT_STAFF_BM_POSI, 0);
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

    @OnClick({R.id.iv_login_back, R.id.tv_bass_close, R.id.tv_bass_content, R.id.et_man_search,
            R.id.tv_man_yg, R.id.tv_man_bm, R.id.tv_man_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_bass_close:
                tipType = 1;
                tvBassContent.setVisibility(View.VISIBLE);
                tvBassClose.setVisibility(View.GONE);
                llManage.setVisibility(View.GONE);
                zuzhiPersonalAdapter.setShow(0);
                break;
            case R.id.tv_bass_content:
                tipType = 2;
                tvBassContent.setVisibility(View.GONE);
                tvBassClose.setVisibility(View.VISIBLE);
                llManage.setVisibility(View.VISIBLE);
                zuzhiPersonalAdapter.setShow(1);
                break;
            case R.id.et_man_search:
                startActivity(new Intent(this, SearchKeywordActivity.class));
                break;
            case R.id.tv_man_yg:
                startActivity(new Intent(this, InvitationJoinActivity.class));
                break;
            case R.id.tv_man_bm:
                addSonBmDialog();
                break;
            case R.id.tv_man_more:
                moreDialog();
                break;
        }
    }

    public void jiagouListSuccess(ZuzhiJiagouModel model) {
        this.jiagouModel = model;
        tvBassTitle.setText(model.getSons().get(positions).getDepartmentName());

        if(model.getSons().get(positions).getStaff().size() == 0 && model.getSons().get(positions).getSons().size() == 0) {
            llManEmpty.setVisibility(View.VISIBLE);
        }else {
            llManEmpty.setVisibility(View.GONE);
        }

        List<ZuzhiJiagouModel.StaffBeanXX> staff = model.getSons().get(positions).getStaff();
        zuzhiPersonalAdapter = new ManageJiagou2Adapter(this,staff);
        listview1.setAdapter(zuzhiPersonalAdapter);
        zuzhiPersonalAdapter.setShow(0);

        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(tipType == 1) {
                    Intent intent = new Intent(ManageJiagou2Activity.this, PersonalInfoActivity.class);
                    intent.putExtra(Constants.PERSONAL_INFO,model);
                    intent.putExtra(Constants.PERSONAL_INFO_TYPE,"2");
                    intent.putExtra(Constants.EDIT_STAFF_POSITION,positions);
                    intent.putExtra(Constants.EDIT_STAFF_POSITION2,position);
                    startActivity(intent);
                }else if(tipType == 2) {
                    Intent intent = new Intent(ManageJiagou2Activity.this, EditStaffActivity.class);
                    intent.putExtra(Constants.EDIT_STAFF,model);
                    intent.putExtra(Constants.EDIT_STAFF_TYPE,"2");
                    intent.putExtra(Constants.EDIT_STAFF_POSITION,positions);
                    intent.putExtra(Constants.EDIT_STAFF_POSITION2,position);
                    startActivity(intent);
                }
            }
        });
        ZuzhiDepartment2Adapter zuzhiDepartmentAdapter = new ZuzhiDepartment2Adapter(this,model.getSons().get(positions).getSons());
        listview2.setAdapter(zuzhiDepartmentAdapter);

        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ManageJiagou2Activity.this, ManageJiagou3Activity.class);
                intent.putExtra(Constants.EDIT_STAFF_BM_POSI,positions);
                intent.putExtra(Constants.EDIT_STAFF_BM_POSI2,position);
                startActivity(intent);
            }
        });
    }

    public void modifyBmNameSuccess() {
        ToastUtil.showToast(this,"修改成功");
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.EDITSTAFF);
        RxBus.getInstance().post(eventMsg);
        finish();
    }

    public void deleteBmSuccess() {
        ToastUtil.showToast(this,"删除成功");
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.EDITSTAFF);
        RxBus.getInstance().post(eventMsg);
        finish();
    }

    public void addSonbmSuccess() {
        ToastUtil.showToast(this,"添加成功");
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.EDITSTAFF);
        RxBus.getInstance().post(eventMsg);
    }

    private void moreDialog() {
        models.clear();
        BottomListviewModel model = new BottomListviewModel(1, "修改当前部门名称");
        BottomListviewModel model2 = new BottomListviewModel(1, "批量移动成员");
        models.add(model);
        models.add(model2);
        if(models != null) {
            if(jiagouModel.getSons().get(positions).getStaff().size() == 0 && jiagouModel.getSons().get(positions).getSons().size() == 0) {
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
                            Intent intent = new Intent(ManageJiagou2Activity.this, BatchAddStaffActivity.class);
                            intent.putExtra(Constants.MOVE_STAFF_TYPE1, "2");
                            intent.putExtra(Constants.MOVE_STAFF_POSITION2, positions);
                            startActivity(intent);
                            dialog.dismiss();
                        }else if(position == 2) {
                            getP().deleteBm(jiagouModel.getSons().get(positions).getDepartmentID() + "");
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
        new ModifyBmNameDialog.Builder(this)
                .setHeight(1f)  //屏幕高度*0.23
                .setWidth(1f)  //屏幕宽度*0.65
                .setContentHint("输入部门名称")
                .setmTitle("修改部门名称")
                .setLetfText("取消")
                .setRightText("修改")
                .setCanceledOnTouchOutside(true)
                .setSingleListener(new DialogInterface.OnLeftAndRightClick2Listener<ModifyBmNameDialog>() {
                    @Override
                    public void clickLeftButton(ModifyBmNameDialog dialog, View view) {
                        dialog.dismiss();
                    }

                    @Override
                    public void clickRightButton(ModifyBmNameDialog dialog, View view, EditText text) {
                        if(jiagouModel != null) {
                            getP().modifyBmName(jiagouModel.getSons().get(positions).getDepartmentID() + "", text.getText().toString().trim());
                        }else {
                            ToastUtil.showToast(ManageJiagou2Activity.this,"请重新加载");
                        }
                    }
                })
                .build()
                .show();
    }

    private void addSonBmDialog() {
        new ModifyBmNameDialog.Builder(this)
                .setHeight(1f)  //屏幕高度*0.23
                .setWidth(1f)  //屏幕宽度*0.65
                .setContentHint("输入子部门")
                .setmTitle("添加子部门")
                .setLetfText("取消")
                .setRightText("添加")
                .setCanceledOnTouchOutside(true)
                .setSingleListener(new DialogInterface.OnLeftAndRightClick2Listener<ModifyBmNameDialog>() {
                    @Override
                    public void clickLeftButton(ModifyBmNameDialog dialog, View view) {
                        dialog.dismiss();
                    }

                    @Override
                    public void clickRightButton(ModifyBmNameDialog dialog, View view, EditText text) {
                        if(jiagouModel != null) {
                            getP().addSonbm(jiagouModel.getSons().get(positions).getDepartmentID() + "", text.getText().toString().trim());
                            dialog.dismiss();
                        }else {
                            ToastUtil.showToast(ManageJiagou2Activity.this,"请重新加载");
                        }
                    }
                })
                .build()
                .show();
    }
}
