package com.mfzn.deepuses.adapter.my;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.company.SelectBranchItemAdapter;
import com.mfzn.deepuses.model.company.SelectCompanyModel;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by sun on 2018/6/12.
 */

public class SwitchCompanyAdapter extends BaseAdapter {

    private Context mContext;
    private List<SelectCompanyModel> models;
    private int positions = -1;

    private OnItemClickLisenter onItemClickLisenter;

    public SwitchCompanyAdapter(Context context, List<SelectCompanyModel> model) {
        this.mContext = context;
        this.models = model;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int position) {
        return models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.listview_switch_company, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SelectCompanyModel model = models.get(position);

        String companyID = model.getCompanyID();
        if (companyID.equals(UserHelper.getCompanyId())) {
            viewHolder.ivSwiItemDh.setImageResource(R.mipmap.regi_xuanzhong);
            viewHolder.tvSwiItemDel.setText("解散");
        } else {
            viewHolder.ivSwiItemDh.setImageResource(R.mipmap.regi_weixuan);
            viewHolder.tvSwiItemDel.setText("删除");
        }

        viewHolder.tvSwiItemName.setText(model.getCompanyName());

        if (model.getType()) {
            viewHolder.llSwiItemDel.setVisibility(View.VISIBLE);
        }else {
            viewHolder.llSwiItemDel.setVisibility(View.GONE);
        }

        if(position == positions) {
            viewHolder.tvSwiItemDel.setVisibility(View.VISIBLE);
        }else {
            viewHolder.tvSwiItemDel.setVisibility(View.GONE);
        }

        viewHolder.tvSwiItemDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickLisenter.OnDeleteLisenter(v,position);
            }
        });

        viewHolder.llSwiItemDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickLisenter.OnLeftLisenter(v,position);
            }
        });

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.ll_swi_item_del)
        LinearLayout llSwiItemDel;
        @BindView(R.id.tv_swi_item_name)
        TextView tvSwiItemName;
        @BindView(R.id.iv_swi_item_dh)
        ImageView ivSwiItemDh;
        @BindView(R.id.tv_swi_item_del)
        TextView tvSwiItemDel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickLisenter{
        void OnLeftLisenter(View v, int position);
        void OnDeleteLisenter(View v, int position);
    }

    public void setOnItemClickLisenter(OnItemClickLisenter onItemClickLisenter){
        this.onItemClickLisenter = onItemClickLisenter;
    }

    public void setPositions(int positions){
        this.positions = positions;
        notifyDataSetChanged();
    }
}
