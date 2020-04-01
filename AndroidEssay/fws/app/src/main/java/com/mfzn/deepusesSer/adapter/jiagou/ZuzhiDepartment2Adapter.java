package com.mfzn.deepusesSer.adapter.jiagou;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.model.jiagou.ZuzhiJiagouModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZuzhiDepartment2Adapter extends BaseAdapter {

    private Context mContext;
    private List<ZuzhiJiagouModel.SonsBeanX.SonsBean> sons;

    public ZuzhiDepartment2Adapter(Context context, List<ZuzhiJiagouModel.SonsBeanX.SonsBean> sons) {
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
            convertView = View.inflate(mContext, R.layout.listview_zuzhi_department2, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ZuzhiJiagouModel.SonsBeanX.SonsBean sonsBeanX = sons.get(position);

        viewHolder.tvBumenName.setText(sonsBeanX.getDepartmentName());

        int size = sonsBeanX.getStaff().size();
        if(size != 0) {
            viewHolder.tvBumenNumber.setText("(" + size + ")");
        }else {
            viewHolder.tvBumenNumber.setText("");
        }

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.tv_bumen_name)
        TextView tvBumenName;
        @BindView(R.id.tv_bumen_number)
        TextView tvBumenNumber;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

