package com.mfzn.deepuses.adapter.jiagou;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZuzhiDepartmentAdapter extends BaseAdapter {

    private Context mContext;
    private List<ZuzhiJiagouModel.SonsBeanX> sons;

    public ZuzhiDepartmentAdapter(Context context, List<ZuzhiJiagouModel.SonsBeanX> sons) {
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
            convertView = View.inflate(mContext, R.layout.listview_zuzhi_department, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ZuzhiJiagouModel.SonsBeanX sonsBeanX = sons.get(position);

        viewHolder.tvBumenName.setText(sonsBeanX.getDepartmentName());

        int size = sonsBeanX.getStaff().size();
        List<ZuzhiJiagouModel.SonsBean> sons = sonsBeanX.getSons();
        for(int i = 0; i < sons.size(); i++) {
            int size1 = sons.get(i).getStaff().size();
            size = size + size1;
        }
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

