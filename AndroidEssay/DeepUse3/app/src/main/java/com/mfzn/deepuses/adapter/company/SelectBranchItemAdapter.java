package com.mfzn.deepuses.adapter.company;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SelectBranchItemAdapter extends BaseAdapter {

    private Context mContext;
    private List<ZuzhiJiagouModel.SonsBeanX> sons;
    private List<ZuzhiJiagouModel.SonsBeanX> beanXES;

    private OnItemClickLisenter onItemClickLisenter;

    public SelectBranchItemAdapter(Context context, List<ZuzhiJiagouModel.SonsBeanX> sons, List<ZuzhiJiagouModel.SonsBeanX> beanXES) {
        this.mContext = context;
        this.sons = sons;
        this.beanXES = beanXES;
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
            convertView = View.inflate(mContext, R.layout.listview_select_branch_item, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ZuzhiJiagouModel.SonsBeanX staffBeanX = sons.get(position);

        viewHolder.ivDeItemName.setText(staffBeanX.getDepartmentName());

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
                onItemClickLisenter.OnclickLisenter();
            }
        });



        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.iv_de_item_select)
        ImageView ivDeItemSelect;
        @BindView(R.id.ll_de_item_select)
        LinearLayout llDeItemSelect;
        @BindView(R.id.iv_de_item_name)
        TextView ivDeItemName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickLisenter{
        void OnclickLisenter();
    }

    public void setOnItemClickLisenter(OnItemClickLisenter onItemClickLisenter){
        this.onItemClickLisenter = onItemClickLisenter;
    }
}
