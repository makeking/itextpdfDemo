package com.bete.pdfutilks.utils;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

public class MyTextWatcher implements TextWatcher {

    public MyTextWatcher() {
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String text = editable.toString();
        if (TextUtils.isEmpty(text)) return;
        //TODO：可在此处额外添加代码
    }
}