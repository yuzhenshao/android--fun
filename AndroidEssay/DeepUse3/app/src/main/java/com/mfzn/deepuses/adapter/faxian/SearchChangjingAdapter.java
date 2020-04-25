package com.mfzn.deepuses.adapter.faxian;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.listener.VideoStateListenerAdapter;
import com.mfzn.deepuses.model.faxian.News;
import com.mfzn.deepuses.model.faxian.Videos;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.utils.MyJZVideoPlayerStandard;
import com.mfzn.deepuses.utils.UIUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JzvdStd;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class SearchChangjingAdapter extends BaseAdapter {

    private Context mContext;
    private List<Videos.DataBean> data;

    private OnitemclickLisenter mOnitemclickLisenter;

    public SearchChangjingAdapter(Context context, List<Videos.DataBean> data) {
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
            convertView = View.inflate(mContext, R.layout.listview_search_changjing, null);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Videos.DataBean model = data.get(position);

        holder.llTitle.setVisibility(VISIBLE);
        holder.tvTitle.setText(model.getTitle());
        holder.tvAuthor.setText(model.getSource());
        holder.tvLike.setText(String.valueOf(model.getLikeNums()));
        holder.tvComment.setText(String.valueOf(model.getCommentsNums()));
        holder.tvShare.setText(String.valueOf(model.getTransNums()));

        holder.videoPlayer.setAllControlsVisiblity(GONE, GONE, VISIBLE, GONE, VISIBLE, GONE, GONE);
        holder.videoPlayer.tinyBackImageView.setVisibility(GONE);
        holder.videoPlayer.setThumb(model.getTitleImage());
        holder.videoPlayer.titleTextView.setText("");//清除标题,防止复用的时候出现
        holder.videoPlayer.setVideoStateListener(new VideoStateListenerAdapter() {

            boolean isVideoParsing = false; //视频是否在解析的标识
            String videoUrl = ApiHelper.BASE_URL + model.getContent();

            @Override
            public void onStartClick() {

                if (!TextUtils.isEmpty(videoUrl)) {
                    //如果已经解析过
                    holder.videoPlayer.setUp(videoUrl, model.getTitle(), JzvdStd.SCREEN_WINDOW_LIST);
                    holder.videoPlayer.startVideo();
                    return;
                }

                //解析视频
                parseVideo();
            }

            private void parseVideo() {

                if (isVideoParsing) {
                    return;
                } else {
                    isVideoParsing = true;
                }

                //隐藏开始按钮 显示加载中
                holder.videoPlayer.setAllControlsVisiblity(GONE, GONE, GONE, VISIBLE, VISIBLE, GONE, GONE);
                holder.llTitle.setVisibility(GONE);

                UIUtils.postTaskSafely(new Runnable() {
                    @Override
                    public void run() {
                        //更改视频是否在解析的标识
                        isVideoParsing = false;

                        //准备播放
                        holder.videoPlayer.setUp(videoUrl, model.getTitle(), JzvdStd.SCREEN_WINDOW_LIST);

                        //开始播放
                        holder.videoPlayer.startVideo();
                    }
                });
            }
        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mOnItemClickListener != null) {
//                    //注意这里使用getTag方法获取数据
//                    mOnItemClickListener.onItemClick(v, v.getTag().toString(), position);
//                }
//            }
//        });

        return convertView;
    }

    public interface OnitemclickLisenter {
        void onItemClick(View view, int position);
    }

    public void setmOnitemclickLisenter(OnitemclickLisenter mOnitemclickLisenter) {
        this.mOnitemclickLisenter = mOnitemclickLisenter;
    }

    class ViewHolder {
        @BindView(R.id.ll_title)
        LinearLayout llTitle;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_author)
        TextView tvAuthor;
        @BindView(R.id.tv_like)
        TextView tvLike;
        @BindView(R.id.tv_comment)
        TextView tvComment;
        @BindView(R.id.tv_share)
        TextView tvShare;
        @BindView(R.id.video_player)
        MyJZVideoPlayerStandard videoPlayer;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
