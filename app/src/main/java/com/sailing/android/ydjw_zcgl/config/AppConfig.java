package com.sailing.android.ydjw_zcgl.config;

import android.os.Environment;


public class AppConfig {

    /**
     * @Fields PERMISSION_REQUEST_CODE:请求权限的返回CODE
     */
    public static final int PERMISSION_REQUEST_CODE = 10001;

    /**
     * @Fields APP_FOLDER_NAME:程序目录
     */
    public static final String APP_FOLDER_NAME = "ZCGL";
    /**
     * @Fields IMAGE_FOLDER_NAME:程序图片存储文件夹目录
     */
    public static final String IMAGE_FOLDER_NAME = "IMAGE";
    /**
     * @Fields DATABASE_FOLDER_NAME:数据库存储文件夹目录
     */
    public static final String DATABASE_FOLDER_NAME = "DATABASE";
    /**
     * @Fields DOC_FOLDER_NAME:文档存储文件夹目录
     */
    public static final String DOC_FOLDER_NAME = "REPORTWORD";
    /**
     * @Fields TEMP_FOLDER_NAME:临时文件文件夹目录
     */
    public static final String TEMP_FOLDER_NAME = "temp";
    /**
     * @Fields APP_DATEBASE_NAME:应用数据库名称
     */
    public static final String APP_DATABASE_NAME = "APP_DATA.db";
    /**
     * @Fields APP_DATABASE_VERSION_CODE:存放数据库版本名称的文件
     */
    public static final String APP_DATABASE_VERSION_CODE = "db_version.txt";
    /**
     * @Fields VEHICLE_BRAND_DATABASE_NAME:车辆品牌数据库名称
     */
    public static final String VEHICLE_BRAND_DATABASE_NAME = "APP_DATA_CLPP.db";
    /**
     * @Fields BASE_URL:Web根路径
     */
    public static final String BASE_URL = "http://10.111.222.216:8088/";
//    public static final String BASE_URL = "http://172.20.25.54:8088/";
//    public static final String BASE_URL = "http://172.20.22.16:8088/";


    /**
     * @Fields APK_DOWN_URL:Web根路径
     */
    public static final String APK_DOWN_URL = BASE_URL+"appFile/appPackage/download";
    /**
     * @Fields JSON_PARAM:参数名
     */
    public static final String REQUEST_PARAM = "jsonParam";

    public static final String PACKAGE_NAME = "com.sailing.android.ydjw_zcgl";

    //缓存字段


    /**
     * @Fields KEY_PASSWORD:AES加密密码
     */
    public static final String KEY_PASSWORD = "abcdefgabcdefg12";

    /**
     * @Fields APPLICATION_APP_NAME:应用app名字
     */
    public static final String APPLICATION_APP_NAME = "zcgl.apk";

    /**
     * @Fields ROOT_PATH : 设备存储根路径
     */
    public static final String ROOT_PATH = Environment
            .getExternalStorageDirectory().getPath();

    public static final String getImagePath() {
        return ROOT_PATH.concat("/").concat(APP_FOLDER_NAME).concat("/").concat(IMAGE_FOLDER_NAME);
    }

    public static final String getDatebasePath() {
        return ROOT_PATH.concat("/").concat(APP_FOLDER_NAME).concat("/").concat(DATABASE_FOLDER_NAME);
    }

    public static final String getTempPath() {
        return ROOT_PATH.concat("/").concat(APP_FOLDER_NAME).concat("/").concat(TEMP_FOLDER_NAME);
    }
    public static final String getDocPath() {
//        return ROOT_PATH;
//        return ROOT_PATH.concat("/").concat(APP_FOLDER_NAME);
        return ROOT_PATH.concat("/").concat(DOC_FOLDER_NAME);
//        return ROOT_PATH.concat("/").concat(APP_FOLDER_NAME).concat("/").concat(DOC_FOLDER_NAME);
//        return ROOT_PATH.concat("/").concat(APP_FOLDER_NAME).concat("/").concat(TEMP_FOLDER_NAME);
    }
}
