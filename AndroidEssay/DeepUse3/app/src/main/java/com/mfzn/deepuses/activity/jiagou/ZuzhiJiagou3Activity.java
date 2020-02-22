package com.mfzn.deepuses.activity.jiagou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.AppManager;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.login.LoginActivity;
import com.mfzn.deepuses.adapter.jiagou.ManageJiagou3Adapter;
import com.mfzn.deepuses.adapter.jiagou.ZuzhiDepartment2Adapter;
import com.mfzn.deepuses.adapter.jiagou.ZuzhiPersonal2Adapter;
import com.mfzn.deepuses.adapter.jiagou.ZuzhiPersonal3Adapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.present.jiagou.ZuzhiJiagou2Present;
import com.mfzn.deepuses.present.jiagou.ZuzhiJiagou3Present;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.UserHelper;
import com.mfzn.deepuses.view.MyListview;
import com.wevey.selector.dialog.BotomListviewDialog;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.bean.BottomListviewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;

public class ZuzhiJiagou3Activity extends BaseMvpActivity<ZuzhiJiagou3Present> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_bass_more)
    TextView tvBassMore;
    @BindView(R.id.et_zu_search)
    EditText etZuSearch;
    @BindView(R.id.listview1)
    MyListview listview1;
    @BindView(R.id.ll_man_empty)
    LinearLayout llManEmpty;

    private int positions;
    private int positions2;
    private List<BottomListviewModel> models;

    @Override
    public int getLayoutId() {
        return R.layout.activity_zuzhi_jiagou3;
    }

    @Override
    public ZuzhiJiagou3Present newP() {
        return new ZuzhiJiagou3Present();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        tvBassTitle.setText(UserHelper.getCompanyName());
        tvBassMore.setVisibility(View.VISIBLE);

        etZuSearch.setHint("在" + UserHelper.getCompanyName() + "搜索");

        listview1.setEmptyView(llManEmpty);

        positions = getIntent().getIntExtra(Constants.EDIT_STAFF_BM_POSI, 0);
        positions2 = getIntent().getIntExtra(Constants.EDIT_STAFF_BM_POSI2, 0);

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
                                Router.newIntent(ZuzhiJiagou3Activity.this).to(LoginActivity.class).launch();
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

    public void jiagouList2Success(ZuzhiJiagouModel model) {
        ZuzhiPersonal3Adapter zuzhiPersonalAdapter = new ZuzhiPersonal3Adapter(this,model.getSons().get(positions).getSons().get(positions2).getStaff());
        listview1.setAdapter(zuzhiPersonalAdapter);

        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ZuzhiJiagou3Activity.this, PersonalInfoActivity.class);
                intent.putExtra(Constants.PERSONAL_INFO,model);
                intent.putExtra(Constants.PERSONAL_INFO_TYPE,"3");
                intent.putExtra(Constants.EDIT_STAFF_POSITION,positions);
                intent.putExtra(Constants.EDIT_STAFF_POSITION2,positions2);
                intent.putExtra(Constants.EDIT_STAFF_POSITION3,position);
                startActivity(intent);
            }
        });
    }
}
