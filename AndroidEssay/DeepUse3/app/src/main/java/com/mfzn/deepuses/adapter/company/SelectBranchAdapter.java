package com.mfzn.deepuses.adapter.company;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.jiagou.SelectItemDepartmentAdapter;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.model.myTeam.ManageSettingModel;
import com.mfzn.deepuses.view.MyListview;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SelectBranchAdapter extends BaseAdapter {

    private Context mContext;
    private List<ZuzhiJiagouModel.SonsBeanX> sons;

    private OnItemOnClickLisenter onItemOnClickLisenter;

    public SelectBranchAdapter(Context context, List<ZuzhiJiagouModel.SonsBeanX> sons) {
        this.mContext = context;
        this.sons = sons;
    }

    @Override
    public int getCount() {
        return sons.size();
    }

    @Override
    public Object getItem(int position) {
        return sons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.listview_select_branch, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ZuzhiJiagouModel.SonsBeanX staffBeanX = sons.get(position);

        viewHolder.ivDeItemName.setText(staffBeanX.getDepartmentName());
        List<ZuzhiJiagouModel.SonsBeanX.SonsBean> sonsBeans = staffBeanX.getSons();
        if(sonsBeans != null && sonsBeans.size() != 0) {
            viewHolder.itemListview.setVisibility(View.VISIBLE);
            viewHolder.show.setVisibility(View.VISIBLE);
            SelectBranchItemAdapter adapter = new SelectBranchItemAdapter(mContext,sonsBeans,sons);
            viewHolder.itemListview.setAdapter(adapter);

            adapter.setOnItemClickLisenter(new SelectBranchItemAdapter.OnItemClickLisenter() {
                @Override
                public void OnclickLisenter() {
                    onItemOnClickLisenter.onItemClick();
                }
            });
        }else {
            viewHolder.itemListview.setVisibility(View.GONE);
            viewHolder.show.setVisibility(View.GONE);
        }

//        if(staffBeanX.getMoren()) {
//            viewHolder.ivDeItemSelect.setImageResource(R.mipmap.team_yixuan);
//        }else {
//            if(staffBeanX.getSelectDe()) {
//                viewHolder.ivDeItemSelect.setImageResource(R.mipmap.regi_xuanzhong);
//            }else {
//                viewHolder.ivDeItemSelect.setImageResource(R.mipmap.regi_weixuan);
//            }
//        }

        if(staffBeanX.getSelectDe()) {
            viewHolder.ivDeItemSelect.setImageResource(R.mipmap.regi_xuanzhong);
        }else {
            viewHolder.ivDeItemSelect.setImageResource(R.mipmap.regi_weixuan);
        }


        viewHolder.llDeItemSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean selectDe = staffBeanX.getSelectDe();
                if(selectDe) {
                    staffBeanX.setSelectDe(false);
                    viewHolder.ivDeItemSelect.setImageResource(R.mipmap.regi_weixuan);
                }else {
                    staffBeanX.setSelectDe(true);
                    viewHolder.ivDeItemSelect.setImageResource(R.mipmap.regi_xuanzhong);
                }
                onItemOnClickLisenter.onItemClick();
            }
        });

        return convertView;
    }

    private void setSelect(List<ZuzhiJiagouModel.SonsBeanX> sons) {

    }

    class ViewHolder {
        @BindView(R.id.iv_de_item_select)
        ImageView ivDeItemSelect;
        @BindView(R.id.ll_de_item_select)
        LinearLayout llDeItemSelect;
        @BindView(R.id.iv_de_item_name)
        TextView ivDeItemName;
        @BindView(R.id.iv_de_item_bj)
        ImageView ivDeItemBj;
        @BindView(R.id.item_listview)
        MyListview itemListview;
        @BindView(R.id.show)
        View show;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemOnClickLisenter{
        void onItemClick();
    }

    public void setOnItemOnClickLisenter(OnItemOnClickLisenter onItemOnClickLisenter){
        this.onItemOnClickLisenter = onItemOnClickLisenter;
    }
}
