package com.mfzn.deepuses.activity.jiagou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.mfzn.deepuses.AppManager;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.company.ApplySuccessActivity;
import com.mfzn.deepuses.activity.login.LoginActivity;
import com.mfzn.deepuses.adapter.jiagou.ZuzhiDepartmentAdapter;
import com.mfzn.deepuses.adapter.jiagou.ZuzhiPersonalAdapter;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.present.jiagou.ZuzhiJiagouPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.UserHelper;
import com.mfzn.deepuses.view.MyListview;
import com.wevey.selector.dialog.BotomListviewDialog;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.ProjectConfirmDialog;
import com.wevey.selector.dialog.bean.BottomListviewModel;
import com.wevey.selector.dialog.bean.ObtainProcessModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;

public class ZuzhiJiagouActivity extends BaseMvpActivity<ZuzhiJiagouPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_bass_more)
    TextView tvBassMore;
    @BindView(R.id.et_zu_search)
    EditText etZuSearch;
    @BindView(R.id.listview1)
    MyListview listview1;
    @BindView(R.id.listview2)
    MyListview listview2;

    private List<BottomListviewModel> models;

    @Override
    public int getLayoutId() {
        return R.layout.activity_zuzhi_jiagou;
    }

    @Override
    public ZuzhiJiagouPresent newP() {
        return new ZuzhiJiagouPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        tvBassTitle.setText(UserHelper.getCompanyName());
        tvBassMore.setVisibility(View.VISIBLE);

        etZuSearch.setHint("在" + UserHelper.getCompanyName() + "搜索");

        models = new ArrayList<>();

        getP().jiagouList();
    }

    @OnClick({R.id.iv_login_back, R.id.tv_bass_more, R.id.tv_zu_invitation, R.id.et_zu_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_bass_more:
                models.clear();
                BottomListviewModel model = new BottomListviewModel();
                model.setName("退出该企业");
                model.setType(2);
                models.add(model);

                BotomListviewDialog normalAlertDialog = new BotomListviewDialog.Builder(this)
                        .setHeight(1f)  //屏幕高度*0.23
                        .setWidth(1f)  //屏幕宽度*0.65
                        .setListModel(models)
                        .setCanceledOnTouchOutside(true)
                        .setOnclickListener(new DialogInterface.OnTopAndBottomClickListener<BotomListviewDialog>() {
                            @Override
                            public void clickTopButton(BotomListviewDialog dialog, View view, int position) {
                                UserHelper.logout();
                                UserHelper.setOut(true);
                                AppManager.getInstance().finishAllActivity();
                                Router.newIntent(ZuzhiJiagouActivity.this).to(LoginActivity.class).launch();
                                finish();
                            }

                            @Override
                            public void clickBottomButton(BotomListviewDialog dialog, View view) {
                                dialog.dismiss();
                            }
                        })
                        .build();
                normalAlertDialog.show();
                break;
            case R.id.tv_zu_invitation:
                Intent intent = new Intent(this, InvitationJoinActivity.class);
                startActivity(intent);
                break;
            case R.id.et_zu_search:
                startActivity(new Intent(this, SearchKeywordActivity.class));
                break;
        }
    }

    public void jiagouListSuccess(ZuzhiJiagouModel model) {
        if(model.getStaff() != null && model.getStaff().size() != 0) {
            ZuzhiPersonalAdapter zuzhiPersonalAdapter = new ZuzhiPersonalAdapter(this,model.getStaff());
            listview1.setAdapter(zuzhiPersonalAdapter);
        }

        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ZuzhiJiagouActivity.this, PersonalInfoActivity.class);
                intent.putExtra(Constants.PERSONAL_INFO,model);
                intent.putExtra(Constants.PERSONAL_INFO_TYPE,"1");
                intent.putExtra(Constants.EDIT_STAFF_POSITION,position);
                startActivity(intent);
            }
        });
        ZuzhiDepartmentAdapter zuzhiDepartmentAdapter = new ZuzhiDepartmentAdapter(this,model.getSons());
        listview2.setAdapter(zuzhiDepartmentAdapter);

        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ZuzhiJiagouActivity.this, ZuzhiJiagou2Activity.class);
                intent.putExtra(Constants.EDIT_STAFF_BM_POSI,position);
                startActivity(intent);
            }
        });
    }
}
