package com.mfzn.deepusesSer.activity.company;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mfzn.deepusesSer.MainActivity;
import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.adapter.company.SelectCompanyAdapter;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.model.company.SelectCompanyModel;
import com.mfzn.deepusesSer.present.company.SelectCompanyPresent;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.utils.EventMsg;
import com.mfzn.deepusesSer.utils.RxBus;
import com.mfzn.deepusesSer.utils.UserHelper;

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
                startActivity(new Intent(context, EstablishJoinActivity.class));
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
                    Router.newIntent(context).to(MainActivity.class).launch();
                    finish();
                }
            });
        }else {
            startActivity(new Intent(context, EstablishJoinActivity.class));
        }
    }
}
