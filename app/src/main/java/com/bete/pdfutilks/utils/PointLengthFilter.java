package com.bete.pdfutilks.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

/**
 * 小数位数
 */
public class PointLengthFilter implements InputFilter {
    public PointLengthFilter(int pointLength){
        mPointLength = pointLength;
    }

    public void setPointLength(int pointLength){
        mPointLength = pointLength;
    }


    /** 输入框小数的位数  默认保留3位小数*/
    private int mPointLength = 3;

    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend) {
        // 删除等特殊字符，直接返回
        if ("".equals(source.toString())) {
            return null;
        }

        String dValue = dest.toString();
        if(TextUtils.isEmpty(dValue)){
            return source;
        }


        String[] splitArray = dValue.split("\\.");
        if(splitArray != null && splitArray.length > 1){
            int cursorIndex = dValue.indexOf(".");

            if(dend > cursorIndex){
                String dotValue = splitArray[1];
                int diff = dotValue.length() + 1 - mPointLength;
                if(diff > 0){
                    return "";
                }
            }
        }

        return source;
    }
}