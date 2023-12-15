package com.bete.pdfutilks.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

public abstract class BaseActivity extends AppCompatActivity {
    private static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        noTitle();
        // 锁定屏幕为横向显示
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_ACTION_MODE_OVERLAY);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.hide();
        }
        getWindow().setFlags(FLAG_HOMEKEY_DISPATCHED, FLAG_HOMEKEY_DISPATCHED);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //2.  加载界面
        setContentView(getLayoutID());
        initView();
        initData(savedInstanceState);
        initListener();
    }


    /**
     * 按钮的点击操作
     */
    protected void initListener() {

    }

    /**
     * 初始化数据的方法，可以对数据进行操作
     *
     * @param savedInstanceState 保存的 Bundle
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 加载控件，可以在里面做加载控件的操作
     */
    protected void initView() {

    }

    /**
     * 加载界面
     */

    protected abstract int getLayoutID();


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }
}
