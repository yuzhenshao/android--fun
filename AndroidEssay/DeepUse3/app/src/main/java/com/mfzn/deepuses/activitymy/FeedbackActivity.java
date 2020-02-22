package com.mfzn.deepuses.activitymy;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.present.my.FeedbackPresent;
import com.mfzn.deepuses.utils.OnInputChangeListener;
import com.mfzn.deepuses.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackActivity extends BaseMvpActivity<FeedbackPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.et_feed_content)
    EditText etFeedContent;
    @BindView(R.id.but_feed_commit)
    Button butFeedCommit;

    @Override
    public int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    public FeedbackPresent newP() {
        return new FeedbackPresent();
    }

    public void feedbackSuccess() {
        ToastUtil.showToast(this,"吐槽成功");
        finish();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_feedback));

        etFeedContent.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(etFeedContent.getText().toString().trim())){
                    butFeedCommit.setEnabled(true);
                    butFeedCommit.setBackgroundResource(R.drawable.yuanjiao_4a9012_button_shape);
                } else {
                    butFeedCommit.setEnabled(false);
                    butFeedCommit.setBackgroundResource(R.drawable.yuanjiao_bfc2cc_button_shape);
                }
            }
        });
    }

    @OnClick({R.id.iv_login_back, R.id.but_feed_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.but_feed_commit:
                feedback();
                break;
        }
    }

    private void feedback() {
        String send = etFeedContent.getText().toString().trim();
        if(TextUtils.isEmpty(send)){
            ToastUtil.showToast(this,"请输入吐槽内容");
            return;
        }
        getP().feedBack(send);
    }
}
