package com.mfzn.deepusesSer.utils;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by sun on 2018/3/30.
 * <p>
 * edittext 输入文本的改变监听，可以只实现改变后的监听
 */

public abstract class OnInputChangeListener implements TextWatcher {

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }
}
