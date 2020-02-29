package com.mfzn.deepuses.adapter.jiagou;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.view.MyListview;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SelectDepartmentAdapter extends BaseAdapter {

    private Context mContext;
    private List<ZuzhiJiagouModel.SonsBeanX> sons;

    private OnItemOnClickLisenter onItemOnClickLisenter;

    public SelectDepartmentAdapter(Context context, List<ZuzhiJiagouModel.SonsBeanX> sons) {
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
            convertView = View.inflate(mContext, R.layout.listview_select_department, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ZuzhiJiagouModel.SonsBeanX staffBeanX = sons.get(position);

        viewHolder.ivDeItemName.setText(staffBeanX.getDepartmentName());
        List<ZuzhiJiagouModel.SonsBeanX> sonsBeans = staffBeanX.getSons();
        if(sonsBeans != null && sonsBeans.size() != 0) {
//            viewHolder.ivDeItemBj.setVisibility(View.VISIBLE);
            viewHolder.itemListview.setVisibility(View.VISIBLE);
            viewHolder.show.setVisibility(View.VISIBLE);
            SelectItemDepartmentAdapter adapter = new SelectItemDepartmentAdapter(mContext,sonsBeans,sons);
            viewHolder.itemListview.setAdapter(adapter);

            adapter.setOnItemClickLisenter(new SelectItemDepartmentAdapter.OnItemClickLisenter() {
                @Override
                public void OnclickLisenter() {
                    notifyDataSetChanged();
                }
            });
        }else {
//            viewHolder.ivDeItemBj.setVisibility(View.GONE);
            viewHolder.itemListview.setVisibility(View.GONE);
            viewHolder.show.setVisibility(View.GONE);
        }

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
                    for (int i = 0 ; i < sons.size() ; i++){
                        if(sons.get(i).getSelectDe()) {
                            sons.get(i).setSelectDe(false);
                        }
                        List<ZuzhiJiagouModel.SonsBeanX> sonsBeans = sons.get(i).getSons();
                        if(sonsBeans != null && sonsBeans.size() != 0) {
                            for (int j = 0 ; j < sonsBeans.size() ; j++){
                                if(sonsBeans.get(j).getSelectDe()) {
                                    sonsBeans.get(j).setSelectDe(false);
                                }
                            }
                        }
                    }
                    staffBeanX.setSelectDe(true);
                    viewHolder.ivDeItemSelect.setImageResource(R.mipmap.regi_xuanzhong);
                    notifyDataSetChanged();
                }
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
