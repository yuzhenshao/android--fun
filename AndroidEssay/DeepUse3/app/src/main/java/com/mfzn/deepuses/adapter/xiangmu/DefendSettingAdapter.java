package com.mfzn.deepuses.adapter.xiangmu;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.xiangmu.DefendSettingModel;
import com.mfzn.deepuses.model.xiangmu.SettingInfoModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DefendSettingAdapter extends BaseAdapter {

    private Context mContext;
    private List<SettingInfoModel.WbsetBean> models;
    private OnDeleteitemclickLisenter onDeleteitemclickLisenter;

    public DefendSettingAdapter(Context context, List<SettingInfoModel.WbsetBean> models) {
        this.mContext = context;
        this.models = models;
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
            convertView = View.inflate(mContext, R.layout.listview_defenf_setting, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SettingInfoModel.WbsetBean defendSettingModel = models.get(position);

        viewHolder.etDefItemType.setText(defendSettingModel.getTitle());
        viewHolder.etDefItemTime.setText(defendSettingModel.getNextDate());
        viewHolder.etDefItemDays.setText(defendSettingModel.getSpaceDate());

        viewHolder.llDefItemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteitemclickLisenter.onDeteleItemClick(v, position);
            }
        });

        return convertView;
    }

    public interface OnDeleteitemclickLisenter {
        void onDeteleItemClick(View view, int position);
    }

    public void setOnDeleteitemclickLisenter(OnDeleteitemclickLisenter onDeleteitemclickLisenter) {
        this.onDeleteitemclickLisenter = onDeleteitemclickLisenter;
    }

    class ViewHolder {
        @BindView(R.id.et_def_item_type)
        TextView etDefItemType;
        @BindView(R.id.et_def_item_time)
        TextView etDefItemTime;
        @BindView(R.id.et_def_item_days)
        TextView etDefItemDays;
        @BindView(R.id.ll_def_item_delete)
        LinearLayout llDefItemDelete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
