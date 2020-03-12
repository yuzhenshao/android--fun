package com.mfzn.deepuses.activity.fx;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.faxian.CommentExpandAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.faxian.CommentBean;
import com.mfzn.deepuses.model.faxian.News;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.present.fragment.ZixunDetailPresnet;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.view.CommentExpandableListView;
import com.qmuiteam.qmui.widget.webview.QMUIWebView;
import com.qmuiteam.qmui.widget.webview.QMUIWebViewClient;
import com.weavey.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.nereo.multi_image_selector.utils.ScreenUtils;

public class ZixunDetailActivity extends BaseMvpActivity<ZixunDetailPresnet> {

    @BindView(R.id.wb_view)
    QMUIWebView webView;
    @BindView(R.id.detail_page_lv_comment)
    CommentExpandableListView expandableListView;
    @BindView(R.id.tv_like_num)
    TextView tvLike;
    @BindView(R.id.tv_comment_num)
    TextView tvComment;
    @BindView(R.id.tv_share_num)
    TextView tvShare;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_fbr)
    TextView tvFbr;
    @BindView(R.id.tv_date)
    TextView tvDate;

    private String rowNum = "";

    private BottomSheetDialog dialog;

    private CommentExpandAdapter adapter;
    private List<CommentBean.DataBean> commentsList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_zixun_detail;
    }

    @Override
    public ZixunDetailPresnet newP() {
        return new ZixunDetailPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        News.DataBean dataBean = (News.DataBean) getIntent().getSerializableExtra("content");

        String content = dataBean.getContent();
        rowNum = String.valueOf(dataBean.getNewsID());
        String likeNum = String.valueOf(dataBean.getLikeCount());
        String commentNum = String.valueOf(dataBean.getCommentCount());

        String title = dataBean.getNewsTitle();
        String fbr = dataBean.getSourceName();
        String date = String.valueOf(dataBean.getAddTime());

        tvTitle.setText(title);
        tvFbr.setText(fbr);
        tvDate.setText(DateUtils.stampToDateTime(date));
        tvLike.setText(likeNum);
        tvComment.setText(commentNum);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(false);
        webView.getSettings().setLoadWithOverviewMode(false);
        webView.loadDataWithBaseURL(null, content, "text/html", "UTF-8", null);
        webView.setWebViewClient(new QMUIWebViewClient(true,true){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //修改图片大小
                int screenWidth = ScreenUtils.getScreenSize(ZixunDetailActivity.this).x;
                String width = String.valueOf(screenWidth- UiUtils.dp2px(ZixunDetailActivity.this,50));
                String width2=String.valueOf(UiUtils.px2dip(ZixunDetailActivity.this,screenWidth)-40);

                String javascript = "javascript:function ResizeImages() {" +
                        "var myimg,oldwidth;" +
                        "var maxwidth = document.body.clientWidth;" +
                        "for(i=0;i <document.images.length;i++){" +
                        "myimg = document.images[i];" +
                        "if(myimg.width > "+width2+"){" +
                        "oldwidth = myimg.width;" +
                        "myimg.width ="+width2+";" +
                        "}" +
                        "}" +
                        "}";
                view.loadUrl(javascript);
                view.loadUrl("javascript:ResizeImages();");

            }
        });
        adapter = new CommentExpandAdapter(this, commentsList);
        expandableListView.setAdapter(adapter);

        getP().addhits(rowNum);
        getP().commentList(rowNum);
    }

    /**
     * by moos on 2018/04/20
     * func:弹出评论框
     */
    private void showCommentDialog(){
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_dialog_layout,null);
        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        dialog.setContentView(commentView);
        /**
         * 解决bsd显示不全的情况
         */
        View parent = (View) commentView.getParent();
        BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
        commentView.measure(0,0);
        behavior.setPeekHeight(commentView.getMeasuredHeight());

        bt_comment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String commentContent = commentText.getText().toString().trim();
                if(!TextUtils.isEmpty(commentContent)){

                    getP().addComment(rowNum,commentContent);
                    //commentOnWork(commentContent);
                    dialog.dismiss();

                }else {
                    Toast.makeText(ZixunDetailActivity.this,"评论内容不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence) && charSequence.length()>2){
                    bt_comment.setBackgroundColor(Color.parseColor("#4A90E2"));
                }else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialog.show();
    }

    @OnClick({R.id.iv_login_back, R.id.detail_page_do_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.detail_page_do_comment:
                showCommentDialog();
                break;
        }
    }

    public void addCommentSuccess(HttpResult model) {
        showMessage(model.getMsg());
        getP().commentList(rowNum);
    }

    public void commentListSuccess(HttpResult<CommentBean> model){
        commentsList.clear();
        commentsList.addAll(model.getRes().getData());
        adapter.notifyDataSetChanged();
    }
}
