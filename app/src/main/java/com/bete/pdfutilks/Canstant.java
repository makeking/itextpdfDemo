package com.bete.pdfutilks;

import android.annotation.SuppressLint;

public class Canstant {
    public static boolean DEVICE = false;
    public static final String APP_NAME = "pdfutils";
    /* path 部分 */
    @SuppressLint("SdCardPath")
    public static final String SD_CARD = "/sdcard/";
    public static final String DEFULT_SD_CARD = "sdcard/";
    public static final String DEFULT_nT01_PATH = "sdcard/" + APP_NAME + "/";
    //    public static final String ROOT_nT01_PATH = "/storage/sdcard0/" + APP_NAME + "/";
    public static final String ROOT_nT01_PATH = "sdcard/" + APP_NAME + "/";
    public static final String APP_NAME_WEB = "/" + APP_NAME + "web";
    public static final String EXPORT = APP_NAME_WEB + "/export/";
    public static final String INNER_TEMP_DIR = APP_NAME + "/temp/";
    public static final String INNER_DAOCHU_TEMP_DIR = APP_NAME + "/daochu_temp/";
    public static final String INNER_CRASH_DIR = APP_NAME + "/crash/"; // 错误收集
    public static final String INNER_MANITENANCE = APP_NAME + "/manitenance/";//维护日志
    public static final String INNER_EEPROM_DIR = APP_NAME + "/eeprom/";// 疫方文件
    public static final String INNER_LOG_DIR = APP_NAME + "/log/";//  日志文件
    public static final String INNER_POJECT_PROCESS = APP_NAME + "/project/";//项目目录
    public static final String INNER_INI_DIR = APP_NAME + "/ini/";//配置目录
    public static final String INNER_KBADJUST_DIR = APP_NAME + "/kbadjust/";// kb 值的内容
    public static final String INNER_DOWNLOAD_DIR = APP_NAME + "/download/";// 下载
    public static final String INNER_LIUCHENG_DIR = APP_NAME + "/liucheng/";// 流程
    public static final String INNER_CANKAOFANWEI_DIR = APP_NAME + "/cankaofanwei/";// 参考范围
    public static final String INNER_HEX_DIR = APP_NAME + "/hex";// 硬件升级
    public static final String INNER_DATA_DIRS = APP_NAME + "/data/";// 数据
    public static final String INNER_EXPORT_DIRS = APP_NAME + "/export/";// 数据
    public static final String INNER_CHECKLOG_DIR = APP_NAME + "/checklog/";// 自检 log
    public static final String INNER_HELP_DIR = APP_NAME +"/help/";

    public static final String UPAN_UPDATE_DIR = APP_NAME + "/update/";// 应用更新
    public static final String UPAN_UPDATE_TESTDIR = APP_NAME + "/updatetest/";// 假机更新目录
    public static final String UPAN_EXPORT_BAR_DIR = APP_NAME + "/bar/"; // 二维码文件
    public static final String UPAN_EXPORT_DATA_DIR = APP_NAME + "/data/"; // 数据文件
    public static final String UPAN_EXPORT_LOG_DIR = APP_NAME + "/log/"; // 日志文件
    public static final String UPAN_EXPORT_LIUCHENG_DIR = APP_NAME + "/liucheng/"; // 二维码文件
    public static final String UPAN_EXPORT_CHECKRECORD_DIR = APP_NAME + "/record/";//记录

    public static final String UPDATE_VERSION = "version.json";
    public static int g_log_stackdeep = DEVICE ? 2 : 5;

}
