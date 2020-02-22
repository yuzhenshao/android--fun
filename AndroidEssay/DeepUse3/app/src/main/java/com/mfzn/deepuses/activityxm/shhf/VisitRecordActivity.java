package com.mfzn.deepuses.activityxm.shhf;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activityxm.shgd.CancalAcceptActivity;
import com.mfzn.deepuses.activityxm.shgd.DaiPaigongActivity;
import com.mfzn.deepuses.activityxm.shgd.EditWorkorderActivity;
import com.mfzn.deepuses.adapter.xiangmu.VisitRecordAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.xmhf.VisitRrcordModel;
import com.mfzn.deepuses.popmune.DropPopMenu;
import com.mfzn.deepuses.popmune.MenuItem;
import com.mfzn.deepuses.present.xmhf.VisitRecordPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.VisitDetailsDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class VisitRecordActivity extends BaseMvpActivity<VisitRecordPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.ll_bass_sh)
    LinearLayout llBassSh;
    @BindView(R.id.ll_re_empty)
    LinearLayout ll_re_empty;
    @BindView(R.id.re_xrecycleview)
    XRecyclerContentLayout reXrecycleview;

    private int pages = 1;
    private VisitRecordAdapter adapter;

    private String pro_id;
    private List<VisitRrcordModel.DataBean> dataSource = new ArrayList<>();
    private int selectType = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_visit_record;
    }

    @Override
    public VisitRecordPresent newP() {
        return new VisitRecordPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_visit_record));
        llBassSh.setVisibility(View.VISIBLE);

        pro_id = getIntent().getStringExtra(Constants.SHOUHOU_PROID);

        adapter = new VisitRecordAdapter(getContext());
        reXrecycleview.getRecyclerView().verticalLayoutManager(getContext());
        reXrecycleview.getRecyclerView().horizontalDivider(R.color.color_f5f7fa,R.dimen.app_6dp);//item之间的分割线
        reXrecycleview.getRecyclerView().setAdapter(adapter);
        reXrecycleview.getRecyclerView().setRefreshEnabled(true);
        reXrecycleview.getRecyclerView().setVerticalScrollBarEnabled(false);
        reXrecycleview.showLoading();

        adapter.setOnItemClickListener(new VisitRecordAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data, int position) {
                new VisitDetailsDialog.Builder(VisitRecordActivity.this)
                        .setHeight(1f)  //屏幕高度*0.23
                        .setWidth(1f)  //屏幕宽度*0.65
                        .setCanceledOnTouchOutside(true)
                        .setOnclickListener(new DialogInterface.OnSingleClickListener<VisitDetailsDialog>() {
                            @Override
                            public void clickSingleButton(VisitDetailsDialog dialog, View view) {
                                dialog.dismiss();
                            }
                        })
                        .build()
                        .show();
            }
        });

        reXrecycleview.getRecyclerView().setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                pages = 1;
                getP().visitRecord(pro_id,pages);
            }

            @Override
            public void onLoadMore(int page) {
                if(selectType == 0) {
                    pages = page;
                }else {
                    pages = 1;
                }
                getP().visitRecord(pro_id,pages);
            }
        });
        reXrecycleview.onRefresh();

        RxBus.getInstance().toObservable().map(new Function<Object, EventMsg>() {
            @Override
            public EventMsg apply(Object o) throws Exception {
                return (EventMsg) o;
            }
        }).subscribe(new Consumer<EventMsg>() {
            @Override
            public void accept(EventMsg eventMsg) throws Exception {
                if (eventMsg != null) {
                    if (eventMsg.getMsg().equals(Constants.ADD_KEFU_SUCC)) {
                        pages = 1;
                        getP().visitRecord(pro_id,pages);
                    }
                }
            }
        });
    }

    @OnClick({R.id.iv_login_back, R.id.iv_bass_select, R.id.iv_bass_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.iv_bass_select:
                onClickPopIcon(view);
                break;
            case R.id.iv_bass_add:
                Intent intent = new Intent(this, AddReturnVisitActivity.class);
                intent.putExtra(Constants.SHOUHOU_PROID,pro_id);
                startActivity(intent);
                break;
        }
    }

    public void visitRecordSuccess(VisitRrcordModel models) {
        List<VisitRrcordModel.DataBean> data = models.getData();
        if(data != null && data.size() != 0) {
            if (pages == 1) {
                ll_re_empty.setVisibility(View.GONE);
                reXrecycleview.setVisibility(View.VISIBLE);
                adapter.setData(data);
            } else {
                adapter.addData(data);
            }
            reXrecycleview.getRecyclerView().setPage(models.getCurrent_page(), models.getLast_page());
        }else {
            if (pages == 1) {
                ll_re_empty.setVisibility(View.VISIBLE);
                reXrecycleview.setVisibility(View.GONE);
            }
        }
        dataSource.clear();
        dataSource.addAll(adapter.getDataSource());
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
                int itemId = menuItem.getItemId();
                if(itemId == 1) {
                    selectType = 0;
                    adapter.setData(dataSource);
                }else if(itemId == 2) {
                    selectType = 1;
                    List<VisitRrcordModel.DataBean> models = new ArrayList<>();
                    for(int i = 0; i < dataSource.size(); i++) {
                        String title = dataSource.get(i).getTitle();
                        if(title.equals("无回访问题")) {
                            models.add(dataSource.get(i));
                        }
                    }
                    adapter.setData(models);
                }else if(itemId == 3) {
                    selectType = 1;
                    List<VisitRrcordModel.DataBean> models = new ArrayList<>();
                    for(int i = 0; i < dataSource.size(); i++) {
                        String title = dataSource.get(i).getTitle();
                        if(title.equals("有回访问题")) {
                            models.add(dataSource.get(i));
                        }
                    }
                    adapter.setData(models);
                }
                pages = 1;
            }
        });
        dropPopMenu.setMenuList(getIconMenuList());
        dropPopMenu.show(view);
    }

    private List<MenuItem> getIconMenuList() {
        List<MenuItem> list = new ArrayList<>();
        list.add(new MenuItem(0, 1, "全部回访"));
        list.add(new MenuItem(0, 2, "无回访问题"));
        list.add(new MenuItem(0, 3, "有回访问题"));
        return list;
    }
}
