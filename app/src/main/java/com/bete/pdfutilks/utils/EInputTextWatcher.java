package com.bete.pdfutilks.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.regex.Pattern;

/**
 * 功能描述：小数输入文本观察类
 *
 * @author (作者) edward（冯丰枫）
 * @link http://www.jianshu.com/u/f7176d6d53d2
 * 创建时间： 2018/3/12
 */

public class EInputTextWatcher implements TextWatcher {
    private Pattern mPattern;
    private static final int MODE_INSERT = 0;
    private static final int MODE_DELETE = 1;
    private static final int MODE_SET_TEXT = 2;
    /**
     * 当前的输入模式,默认
     */
    private int inputMode;
    /**
     * 当前的光标所在位置
     */
    private int curIndex;
    /**
     * 输入手机号码的editText
     */
    private EditText etPhone;


    /**
     * 不限制整数位数和小数位数
     */
    public EInputTextWatcher() {
    }

    /**
     * 限制整数位数或着限制小数位数
     *
     * @param type   限制类型
     * @param number 限制位数
     */
    public EInputTextWatcher(Type type, int number) {
        if (type == Type.decimal) {
            mPattern = Pattern.compile("^[0-9]+(\\.[0-9]{0," + number + "})?$");
        } else if (type == Type.integer) {
            mPattern = Pattern.compile("^[0-9]{0," + number + "}+(\\.[0-9]{0,})?$");
        }
    }

    /**
     * 既限制整数位数又限制小数位数
     *
     * @param integers 整数位数
     * @param decimals 小数位数
     */

    public EInputTextWatcher(int integers, int decimals, int E) {
        mPattern = Pattern.compile("^[0-9]{0," + integers + "}+(\\.[0-9]{0," + decimals + "})?+(E[0-9]{0," + E + "})?$");
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //LogUtils.d("start:"+start+" count:"+count+" after:"+after);
         if (s.length() == count && start == 0 && count != 0) {
            //inputMode = MODE_SET_TEXT;
            if (curIndex == 2 && after > 2 || (curIndex == 5 && after > 5)){
                curIndex += 1;
            }
        } else if (after == 0) {
            //inputMode = MODE_DELETE;
            curIndex = start;
            if ((curIndex == 2 && s.length() >= 2) || (curIndex == 5 && s.length() >= 5)){
                curIndex -= 1;
            }
        } else {
            //inputMode = MODE_INSERT;
            curIndex = after + start;
            if ((curIndex == 2 && s.length() >= 2) || (curIndex == 5 && s.length() >= 5)){
                curIndex += 1;
            }
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        String contents = s.toString();
//        int length = contents.length();
//        if(length == 2){
//            if(contents.substring(1).equals(new String("."))){ // -
//                contents = contents.substring(0, 1);
//                etPhone.setText(contents);
//                etPhone.setSelection(contents.length());
//            }else{ // +
//                contents = contents.substring(0, 1) + "." + contents.substring(1);
//                etPhone.setText(contents);
//                etPhone.setSelection(contents.length());
//            }
//        }
//        else if(length == 5){
//            if(contents.substring(4).equals(new String("E"))){ // -
//                contents = contents.substring(0, 4);
//                etPhone.setText(contents);
//                etPhone.setSelection(contents.length());
//            }else{// +
//                contents = contents.substring(0, 4) + "E" + contents.substring(4);
//                etPhone.setText(contents);
//                etPhone.setSelection(contents.length());
//            }
//        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
//        String text = editable.toString();
//        if (TextUtils.isEmpty(text)) return;
//        if ((editable.length() > 1) && (editable.charAt(0) == '0') && editable.charAt(1) != '.') {   //删除整数首位的“0”
//            editable.delete(0, 1);
//            return;
//        }
//        if (text.equals(".")) {                                    //首位是“.”自动补“0”
//            editable.insert(0, "0");
//            return;
//        }
//
//        if (mPattern != null && !mPattern.matcher(text).matches() && editable.length() > 0) {
//            editable.delete(editable.length() - 1, editable.length());
//            return;
//        }
//        text = editable.toString();
//        if(text.length()==1)
//        {
//            editable.append(".");
//            return;
//        }
//
//        if((text.length()==2)&&(!text.contains(".")))
//        {
//            editable.insert(1, ".");
//            return;
//        }
//
//        if(text.length()==4)
//        {
//            editable.append( "E");
//            return;
//        }
//
//        if((text.length()==5)&&(!text.contains("E")))
//        {
//            editable.insert(4, "E");
//            return;
//        }

//        String curString = editable.toString();
//        String curString1 = curString.replace(".", "");
//        String realString = curString1.replace("E", "");
//        int length = realString.length();
//        StringBuilder sb = new StringBuilder();
//        if ((inputMode == MODE_INSERT)) {
//            if (length < 1) {
//                return;
//            }
//            sb.append(realString.substring(0, 1));
//            sb.append(".");
//            if (length < 3) {
//                sb.append(realString.substring(1, length));
//            } else {
//                sb.append(realString.substring(1, 3));
//                sb.append("E");
//                sb.append(realString.substring(3, length));
//            }
//        }
//        else if ((inputMode == MODE_DELETE)) {
//            if (length <= 1) {
//                sb.append(realString);
//            } else if (length <= 3) {
//                sb.append(realString.substring(0, 1));
//                sb.append(".");
//                sb.append(realString.substring(1, length));
//            } else {
//                sb.append(realString.substring(0, 1));
//                sb.append(".");
//                sb.append(realString.substring(1, 3));
//                sb.append("E");
//                sb.append(realString.substring(3, length));
//            }
//        }
//        if (sb.length() != 0 && !sb.toString().equals(curString)) {
//            if (etPhone != null) {
//                etPhone.setText(sb);
//            }
//        }
//        if (length != 0 && etPhone != null) {
//            etPhone.setSelection(curIndex);
//        }

        String curString = editable.toString();
        String curString1 = curString.replace(".", "");
        String realString = curString1.replace("E", "");
        StringBuilder sb = new StringBuilder();
        int length = realString.length();
        //LogUtils.d(length);
        if (length <= 1) {
            sb.append(realString.substring(0, length));
        } else if (length <= 3) {
            sb.append(realString.substring(0, 1));
            sb.append(".");
            sb.append(realString.substring(1, length));
        } else {
            length = length > 5 ? 5 : length;
            sb.append(realString.substring(0, 1));
            sb.append(".");
            sb.append(realString.substring(1, 3));
            sb.append("E");
            sb.append(realString.substring(3, length));
        }
        if (sb.length() != 0 && !sb.toString().equals(curString)) {
            if (etPhone != null) {
                etPhone.setText(sb);
            }
        }
        if (length != 0 && etPhone != null) {
            curIndex = curIndex>sb.toString().length() ? sb.toString().length():curIndex;
            etPhone.setSelection(curIndex);
        }
//        if (!sb.toString().equals(curString)) {
//            if (etPhone != null) {
//                etPhone.setText(sb);
//            }
//        }
//        if (etPhone != null) {
//            etPhone.setSelection(curIndex);
//        }
        //TODO：可在此处额外添加代码
    }

    public void setEditText(EditText etPhone) {
        this.etPhone = etPhone;
        this.etPhone.addTextChangedListener(this);
    }

    public enum Type {
        integer, decimal
    }
}
