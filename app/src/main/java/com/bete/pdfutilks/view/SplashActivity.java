package com.bete.pdfutilks.view;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.bete.pdfutilks.Canstant;
import com.bete.pdfutilks.R;
import com.bete.pdfutilks.utils.AppUtils;
import com.bete.pdfutilks.utils.FileCommonUtil;
import com.bete.pdfutilks.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class  SplashActivity extends BaseActivity {
    private Context context;
    private static final int REQUEST_PERMISSION_CODE = 0;
    private boolean animationStarted = false;
    int process = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isTaskRoot()) {
            finish();
            return;
        }
        context = this;
        new Thread() {
            @Override
            public void run() {
                super.run();
                // 判断
                requestPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);//, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.WRITE_SECURE_SETTINGS, Manifest.permission.CAMERA
            }
        }.start();
    }

    protected void initView() {
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
    /*
      权限相关的操作
     */
    public void permissionSuccess(int requestCode) {
        if (requestCode == REQUEST_PERMISSION_CODE) {
            startNextActivity();
        } else {
            throw new IllegalStateException("Unexpected value: " + requestCode);
        }
    }



    /**
     * 判断是否第一次登录
     */
    private void startNextActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


    /**
     * 请求权限
     *
     * @param permissions           请求的权限
     * @param requestPermissionCode 请求码
     */
    private int REQUEST_CODE_PERMISSION = 0x00099;

    public void requestPermission(String[] permissions, int requestPermissionCode) {
        REQUEST_CODE_PERMISSION = requestPermissionCode;
        if (checkPermissions(permissions)) {
            // 同意权限
            permissionSuccess(requestPermissionCode);
        } else {
            List<String> needPermissions = getDeniedPermissions(permissions);
            ActivityCompat.requestPermissions(this, needPermissions.toArray(new String[needPermissions.size()]), REQUEST_CODE_PERMISSION);
        }

    }

    /**
     * 检测所有的权限是否都已授权
     *
     * @param permissions
     * @return
     */
    private boolean checkPermissions(String[] permissions) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) !=
                    PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (verifyPermissions(grantResults)) {
                permissionSuccess(REQUEST_CODE_PERMISSION);
            } else {
                permissionFail(REQUEST_CODE_PERMISSION);
                showTipsDialog();
            }
        }
    }

    /**
     * 显示提示对话框
     */
    private void showTipsDialog() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.tipInfo))
                .setMessage(getString(R.string.splashTipInfo))
                .setNegativeButton(getString(R.string.cancel), (dialog, which) -> AppUtils.exitApp())
                .setPositiveButton(getString(R.string.queding), (dialog, which) -> startAppSettings()).show();
    }

    /**
     * 启动当前应用设置页面
     */
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    /**
     * 权限获取失败
     *
     * @param requestCode
     */
    public void permissionFail(int requestCode) {
        LogUtils.d("获取权限失败:" + requestCode);
    }

    /**
     * 确认所有的权限是否都已授权
     *
     * @param grantResults
     * @return
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     */
    private List<String> getDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) !=
                    PERMISSION_GRANTED ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                // log 日志目录
                FileCommonUtil.createOrExistsDir(Canstant.SD_CARD + Canstant.INNER_LOG_DIR);
                needRequestPermissionList.add(permission);
            }
        }
        return needRequestPermissionList;
    }

}
