package com.mfzn.deepusesSer.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.fragment.shouhou.EndFragment;
import com.mfzn.deepusesSer.fragment.shouhou.ServingFragment;
import com.mfzn.deepusesSer.fragment.shouhou.WaitJiedanFragment;
import com.mfzn.deepusesSer.fragment.shouhou.WaitPingjiaFragment;
import com.mfzn.deepusesSer.popmune.DropPopMenu;
import com.mfzn.deepusesSer.popmune.MenuItem;
import com.mfzn.deepusesSer.view.MyDuiZhangPagerAdapter;
import com.mfzn.deepusesSer.view.MyViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class XiangmuFragment extends Fragment {

    protected View mRootView;
    Unbinder unbinder;

    @BindView(R.id.tv_fa_title)
    TextView tvFaTitle;
    @BindView(R.id.tv_wait_progress)
    TextView tvWaitProgress;
    @BindView(R.id.tv_serving)
    TextView tvServing;
    @BindView(R.id.tv_wait_pingjia)
    TextView tvWaitPingjia;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.fa_viewpager)
    MyViewPager faViewpager;

    private List<Fragment> list = new ArrayList<Fragment>();

    public static String shType = "0";

    private int page_index = 0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_xiangmu, null);
        unbinder = ButterKnife.bind(this, mRootView);
        initView();
        return mRootView;
    }

    private void initView() {

        tvFaTitle.getPaint().setFakeBoldText(true);
        tvWaitProgress.getPaint().setFakeBoldText(true);

        WaitJiedanFragment settlement = new WaitJiedanFragment();
        ServingFragment settlement1 = new ServingFragment();
        WaitPingjiaFragment settlement11 = new WaitPingjiaFragment();
        EndFragment settlement111 = new EndFragment();
        list.add(settlement);
        list.add(settlement1);
        list.add(settlement11);
        list.add(settlement111);

        faViewpager.setAdapter(new MyDuiZhangPagerAdapter(getActivity().getSupportFragmentManager(), list));
    }

    @OnClick({R.id.tv_wait_progress, R.id.tv_serving, R.id.tv_wait_pingjia, R.id.tv_end, R.id.ll_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_wait_progress:
                setShow();
                tvWaitProgress.getPaint().setFakeBoldText(true);
                tvWaitProgress.setTextColor(getActivity().getResources().getColor(R.color.color_303133));
                faViewpager.setCurrentItem(0);
                page_index = 0;
                break;
            case R.id.tv_serving:
                setShow();
                tvServing.getPaint().setFakeBoldText(true);
                tvServing.setTextColor(getActivity().getResources().getColor(R.color.color_303133));
                faViewpager.setCurrentItem(1);
                page_index = 1;
                break;
            case R.id.tv_wait_pingjia:
                setShow();
                tvWaitPingjia.getPaint().setFakeBoldText(true);
                tvWaitPingjia.setTextColor(getActivity().getResources().getColor(R.color.color_303133));
                faViewpager.setCurrentItem(2);
                page_index = 2;
                break;
            case R.id.tv_end:
                setShow();
                tvEnd.getPaint().setFakeBoldText(true);
                tvEnd.setTextColor(getActivity().getResources().getColor(R.color.color_303133));
                faViewpager.setCurrentItem(3);
                page_index = 3;
                break;
            case R.id.ll_select:
                onClickPopIcon(view);
                break;
        }
    }

    private void setShow(){
        tvWaitProgress.getPaint().setFakeBoldText(false);
        tvWaitProgress.setTextColor(getActivity().getResources().getColor(R.color.color_909399));
        tvServing.getPaint().setFakeBoldText(false);
        tvServing.setTextColor(getActivity().getResources().getColor(R.color.color_909399));
        tvWaitPingjia.getPaint().setFakeBoldText(false);
        tvWaitPingjia.setTextColor(getActivity().getResources().getColor(R.color.color_909399));
        tvEnd.getPaint().setFakeBoldText(false);
        tvEnd.setTextColor(getActivity().getResources().getColor(R.color.color_909399));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void onClickPopIcon(View view) {
        DropPopMenu dropPopMenu = new DropPopMenu(getActivity());
        dropPopMenu.setTriangleIndicatorViewColor(Color.WHITE);
        dropPopMenu.setBackgroundResource(R.drawable.yuanjiao_ffffff_bg_shape);
        dropPopMenu.setItemTextColor(Color.BLACK);
        dropPopMenu.setIsShowIcon(true);
        dropPopMenu.setOnItemClickListener(new DropPopMenu.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id, MenuItem menuItem) {
                if (position == 0){
                    shType = "0";
                }else if (position == 1){
                    shType = "1";
                }else{
                    shType = "2";
                }
                if (page_index == 0){
                    WaitJiedanFragment fragment =  (WaitJiedanFragment)getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:"+faViewpager.getId()+":"+faViewpager.getCurrentItem());
                    fragment.refreshData(shType);
                }else if (page_index == 1){
                    ServingFragment fragment =  (ServingFragment)getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:"+faViewpager.getId()+":"+faViewpager.getCurrentItem());
                    fragment.refreshData(shType);
                }else if (page_index == 2){
                    WaitPingjiaFragment fragment =  (WaitPingjiaFragment)getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:"+faViewpager.getId()+":"+faViewpager.getCurrentItem());
                    fragment.refreshData(shType);
                }else if (page_index == 3){
                    EndFragment fragment =  (EndFragment)getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:"+faViewpager.getId()+":"+faViewpager.getCurrentItem());
                    fragment.refreshData(shType);
                }

            }
        });
        dropPopMenu.setMenuList(getIconMenuList());
        dropPopMenu.show(view);
    }

    private List<MenuItem> getIconMenuList() {
        List<MenuItem> list = new ArrayList<>();
        list.add(new MenuItem(R.mipmap.select_all_icon, 0, "全部工单"));
        list.add(new MenuItem(R.mipmap.select_gzbx_icon, 1, "故障报修"));
        list.add(new MenuItem(R.mipmap.select_whsj_icon, 2, "维护升级"));
        return list;
    }
}
