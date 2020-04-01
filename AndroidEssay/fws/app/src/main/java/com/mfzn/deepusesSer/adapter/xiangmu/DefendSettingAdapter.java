package com.mfzn.deepusesSer.adapter.xiangmu;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.model.xiangmu.DefendSettingModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DefendSettingAdapter extends BaseAdapter {

    private Context mContext;
    private List<DefendSettingModel> models;
    private OnDeleteitemclickLisenter onDeleteitemclickLisenter;
    private OnTimeitemclickLisenter onTimeitemclickLisenter;

    public DefendSettingAdapter(Context context, List<DefendSettingModel> models) {
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

        DefendSettingModel defendSettingModel = models.get(position);
        String times = defendSettingModel.getTimes();
        if (!TextUtils.isEmpty(times)) {
            viewHolder.etDefItemTime.setText(times);
        }else {
            viewHolder.etDefItemTime.getText().clear();
        }

        viewHolder.llDefItemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteitemclickLisenter.onDeteleItemClick(v, position);
            }
        });
        viewHolder.etDefItemTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTimeitemclickLisenter.onTimeItemClick(v, position);
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

    public interface OnTimeitemclickLisenter {
        void onTimeItemClick(View view, int position);
    }

    public void setOnTimeitemclickLisenter(OnTimeitemclickLisenter onTimeitemclickLisenter) {
        this.onTimeitemclickLisenter = onTimeitemclickLisenter;
    }

    class ViewHolder {
        @BindView(R.id.et_def_item_type)
        EditText etDefItemType;
        @BindView(R.id.et_def_item_time)
        EditText etDefItemTime;
        @BindView(R.id.et_def_item_days)
        EditText etDefItemDays;
        @BindView(R.id.ll_def_item_delete)
        LinearLayout llDefItemDelete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
