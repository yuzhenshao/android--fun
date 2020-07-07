package com.mfzn.deepuses.adapter.faxian;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.faxian.News;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.utils.DateUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchZixunAdapter extends BaseAdapter {

    private Context mContext;
    private List<News.DataBean> data;

    private OnitemclickLisenter mOnitemclickLisenter;

    public SearchZixunAdapter(Context context, List<News.DataBean> data) {
        this.mContext = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.listview_search_zixun, null);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        News.DataBean dataBean = data.get(position);

        News.DataBean model = data.get(position);
        holder.tvTitle.setText(model.getNewsTitle());
        holder.tvFbr.setText(model.getSourceName());
        holder.tvFbsj.setText(DateUtils.stampToDate(String.valueOf(model.getAddTime()),"yyyy.MM.dd"));
        holder.tvBiaoqian.setText(model.getNewsCategoryName());
        if (model.getHits() > 999){
            holder.tvYdl.setText("999+");
        }else{
            holder.tvYdl.setText(String.valueOf(model.getHits()));
        }
        holder.tvDes.setText(model.getSummary());
        if (!TextUtils.isEmpty(model.getTitleImage())){
            Glide.with(mContext).load(model.getTitleImage()).into(holder.ivImg);
        }

        return convertView;
    }

    public interface OnitemclickLisenter {
        void onItemClick(View view, int position);
    }

    public void setmOnitemclickLisenter(OnitemclickLisenter mOnitemclickLisenter) {
        this.mOnitemclickLisenter = mOnitemclickLisenter;
    }

    class ViewHolder {
        @BindView(R.id.tv_fbr)
        TextView tvFbr;
        @BindView(R.id.tv_fbsj)
        TextView tvFbsj;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_biaoqian)
        TextView tvBiaoqian;
        @BindView(R.id.tv_ydl)
        TextView tvYdl;
        @BindView(R.id.tv_des)
        TextView tvDes;
        @BindView(R.id.iv_img)
        ImageView ivImg;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
