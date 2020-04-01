package com.mfzn.deepusesSer.activity.jiagou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.adapter.jiagou.ManageJiagouAdapter;
import com.mfzn.deepusesSer.adapter.jiagou.ZuzhiDepartmentAdapter;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepusesSer.present.jiagou.ManageJiagouPresent;
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

public class ManageJiagouActivity extends BaseMvpActivity<ManageJiagouPresent> {

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

    private int tipType = 1;// 1默认  2管理

    private ManageJiagouAdapter zuzhiPersonalAdapter;
    private List<BottomListviewModel> models;
    private ZuzhiJiagouModel jiagouModel;

    @Override
    public int getLayoutId() {
        return R.layout.activity_manage_jiagou;
    }

    @Override
    public ManageJiagouPresent newP() {
        return new ManageJiagouPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        tvBassTitle.setText(UserHelper.getCompanyName());
        tvBassContent.setVisibility(View.VISIBLE);
        tvBassContent.setText("管理");

        etManSearch.setHint("在" + UserHelper.getCompanyName() + "搜索");
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
                    if (eventMsg.getMsg().equals(Constants.EDITSTAFF)) {
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
        zuzhiPersonalAdapter = new ManageJiagouAdapter(this, model.getStaff());
        listview1.setAdapter(zuzhiPersonalAdapter);
        zuzhiPersonalAdapter.setShow(0);

        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (tipType == 1) {
                    Intent intent = new Intent(ManageJiagouActivity.this, PersonalInfoActivity.class);
                    intent.putExtra(Constants.PERSONAL_INFO, model);
                    intent.putExtra(Constants.PERSONAL_INFO_TYPE, "1");
                    intent.putExtra(Constants.EDIT_STAFF_POSITION, position);
                    startActivity(intent);
                } else if (tipType == 2) {
                    Intent intent = new Intent(ManageJiagouActivity.this, EditStaffActivity.class);
                    intent.putExtra(Constants.EDIT_STAFF, model);
                    intent.putExtra(Constants.EDIT_STAFF_TYPE, "1");
                    intent.putExtra(Constants.EDIT_STAFF_POSITION, position);
                    startActivity(intent);
                }
            }
        });

        ZuzhiDepartmentAdapter zuzhiDepartmentAdapter = new ZuzhiDepartmentAdapter(this, model.getSons());
        listview2.setAdapter(zuzhiDepartmentAdapter);

        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ManageJiagouActivity.this, ManageJiagou2Activity.class);
                intent.putExtra(Constants.EDIT_STAFF_BM_POSI, position);
                startActivity(intent);
            }
        });
    }

    public void modifyBmNameSuccess() {
        ToastUtil.showToast(this,"修改成功");
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.EDITSTAFF);
        RxBus.getInstance().post(eventMsg);
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
                            Intent intent = new Intent(ManageJiagouActivity.this, BatchAddStaffActivity.class);
                            intent.putExtra(Constants.MOVE_STAFF_TYPE1, "1");
                            startActivity(intent);
                            dialog.dismiss();
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
//                            getP().modifyBmName(jiagouModel.getDepartmentID() + "", text.getText().toString().trim());
//                            dialog.dismiss();
//                        }else {
//                            ToastUtil.showToast(ManageJiagouActivity.this,"请重新加载");
//                        }
//                    }
//                })
//                .build()
//                .show();
    }

    private void addSonBmDialog() {
//        new ModifyBmNameDialog.Builder(this)
//                .setHeight(1f)  //屏幕高度*0.23
//                .setWidth(1f)  //屏幕宽度*0.65
//                .setContentHint("输入子部门")
//                .setmTitle("添加子部门")
//                .setLetfText("取消")
//                .setRightText("添加")
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
//                            getP().addSonbm(jiagouModel.getDepartmentID() + "", text.getText().toString().trim());
//                            dialog.dismiss();
//                        }else {
//                            ToastUtil.showToast(ManageJiagouActivity.this,"请重新加载");
//                        }
//                    }
//                })
//                .build()
//                .show();
    }
}
