package com.mfzn.deepuses.activitymy.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mfzn.deepuses.AppManager;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.company.EstablishJoinActivity;
import com.mfzn.deepuses.activity.jiagou.BatchAddStaffActivity;
import com.mfzn.deepuses.activity.jiagou.InvitationJoinActivity;
import com.mfzn.deepuses.activity.jiagou.ManageJiagouActivity;
import com.mfzn.deepuses.activity.login.LoginActivity;
import com.mfzn.deepuses.adapter.home.HomeListAdapter;
import com.mfzn.deepuses.adapter.home.HomeTdglAdapter;
import com.mfzn.deepuses.adapter.my.SwitchCompanyAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.company.SelectCompanyModel;
import com.mfzn.deepuses.present.myset.SwitchCompanyPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;
import com.wevey.selector.dialog.BotomListviewDialog;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.bean.BottomListviewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static com.mfzn.deepuses.mine.activity.MyCardEditActivity.ONLY_SELECTED;

public class SwitchCompanyActivity extends BaseMvpActivity<SwitchCompanyPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_bass_comlate)
    TextView tvBassComlate;
    @BindView(R.id.ll_bass_more)
    LinearLayout llBassMore;
    @BindView(R.id.swi_list)
    ListView swiList;

    private List<BottomListviewModel> models = new ArrayList<>();
    private List<SelectCompanyModel> companyModels = new ArrayList<>();
    private SwitchCompanyAdapter adapter;

    private int posis;

    @Override
    public int getLayoutId() {
        return R.layout.activity_switch_company;
    }

    @Override
    public SwitchCompanyPresnet newP() {
        return new SwitchCompanyPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_switch_company));
        llBassMore.setVisibility(getIntent().getBooleanExtra(ONLY_SELECTED,false)?View.GONE:View.VISIBLE);

        getP().companyList();

        RxBus.getInstance().toObservable().map(new Function<Object, EventMsg>() {
            @Override
            public EventMsg apply(Object o) throws Exception {
                return (EventMsg) o;
            }
        }).subscribe(new Consumer<EventMsg>() {
            @Override
            public void accept(EventMsg eventMsg) throws Exception {
                if (eventMsg != null) {
                    if(eventMsg.getMsg().equals(Constants.ESTABLSISH)){
                        getP().companyList();
                    }
                }
            }
        });
    }

    @OnClick({R.id.iv_login_back, R.id.tv_bass_comlate, R.id.ll_bass_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_bass_comlate:
                for(int i = 0; i < companyModels.size(); i++) {
                    companyModels.get(i).setType(false);
                }
                adapter.setPositions(-1);
                llBassMore.setVisibility(View.VISIBLE);
                tvBassComlate.setVisibility(View.GONE);
                break;
            case R.id.ll_bass_more:
                moreDialog();
                break;
        }
    }

    public void delCompanySuccess(String models) {
        if (companyModels.get(posis).getCompanyID().equals(UserHelper.getCompanyId())) {
            UserHelper.logout();
            UserHelper.setOut(true);
            AppManager.getInstance().finishAllActivity();
            Router.newIntent(this).to(LoginActivity.class).launch();
        } else {
            ToastUtil.showToast(this,models);
            companyModels.remove(posis);
            adapter.setPositions(-1);
        }
    }

    public void companyListSuccess(List<SelectCompanyModel> models) {
        companyModels = models;
        adapter = new SwitchCompanyAdapter(this,models);
        swiList.setAdapter(adapter);

        swiList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserHelper.setCompany(models.get(position).getCompanyID(),models.get(position).getCompanyName());
                adapter.notifyDataSetChanged();
                EventMsg eventMsg = new EventMsg();
                eventMsg.setMsg(Constants.COMPANY_NAME);
                RxBus.getInstance().post(eventMsg);
            }
        });

        adapter.setOnItemClickLisenter(new SwitchCompanyAdapter.OnItemClickLisenter() {
            @Override
            public void OnLeftLisenter(View v, int position) {
                for(int i = 0; i < companyModels.size(); i++) {
                    if(position == i) {
                        companyModels.get(i).setType(false);
                    }else {
                        companyModels.get(i).setType(true);
                    }
                }
                adapter.setPositions(position);
            }

            @Override
            public void OnDeleteLisenter(View v, int position) {
                posis = position;
                getP().delCompany(companyModels.get(position).getCompanyID());
            }
        });
    }

    private void moreDialog() {
        models.clear();
        BottomListviewModel model = new BottomListviewModel(1, "创建或加入");
        BottomListviewModel model2 = new BottomListviewModel(1, "退出企业");
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
                            Intent intent = new Intent(SwitchCompanyActivity.this, EstablishJoinActivity.class);
                            intent.putExtra("EstablishJoin",2);
                            startActivity(intent);
                            dialog.dismiss();
                        }else if(position == 1) {
                            for(int i = 0; i < companyModels.size(); i++) {
                                companyModels.get(i).setType(true);
                            }
                            adapter.notifyDataSetChanged();
                            llBassMore.setVisibility(View.GONE);
                            tvBassComlate.setVisibility(View.VISIBLE);
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
}
