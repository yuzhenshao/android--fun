package com.mfzn.deepuses.activity.company;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mfzn.deepuses.MainActivity;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activitymy.OperationGuideActivity;
import com.mfzn.deepuses.adapter.company.SelectCompanyAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.company.SelectCompanyModel;
import com.mfzn.deepuses.present.company.SelectCompanyPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class SelectCompanyActivity extends BaseMvpActivity<SelectCompanyPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.ll_bass_add)
    LinearLayout llBassAdd;
    @BindView(R.id.tv_sel_title)
    TextView tvSelTitle;
    @BindView(R.id.sel_listview)
    ListView selListview;
    @BindView(R.id.iv_login_back)
    ImageButton iv_login_back;

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_company;
    }

    @Override
    public SelectCompanyPresent newP() {
        return new SelectCompanyPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_select_company));
        llBassAdd.setVisibility(View.VISIBLE);
        tvSelTitle.getPaint().setFakeBoldText(true);

        iv_login_back.setVisibility(View.GONE);

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

    @OnClick({R.id.iv_login_back, R.id.ll_bass_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_bass_add:
                Intent intent = new Intent(this, EstablishJoinActivity.class);
                intent.putExtra("EstablishJoin",1);
                startActivity(intent);
                break;
        }
    }

    public void companyListSuccess(List<SelectCompanyModel> models) {
        if(models != null && models.size() != 0) {
            SelectCompanyAdapter adapter = new SelectCompanyAdapter(this,models);
            selListview.setAdapter(adapter);

            selListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    UserHelper.setCompany(models.get(position).getCompanyID(),models.get(position).getCompanyName());
                    if(UserHelper.getCzzn()) {
                        Router.newIntent(SelectCompanyActivity.this).to(MainActivity.class).launch();
                    }else {
                        Intent intent = new Intent(context, OperationGuideActivity.class);
                        intent.putExtra("czzn",1);
                        startActivity(intent);
                    }
                    finish();
                }
            });
        }else {
            Intent intent = new Intent(this, EstablishJoinActivity.class);
            intent.putExtra("EstablishJoin",1);
            startActivity(intent);
        }
    }
}
