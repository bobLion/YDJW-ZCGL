package com.sailing.android.ydjw_zcgl.http;


import com.sailing.android.ydjw_zcgl.config.AppConfig;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

/**
 * Created by eagle on 2017-10-9 15:06
 */

public interface RestService {

    /**
     * 用户登录
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @POST("/app/login/userLogin")
    Call<ResponseResult> login(@Field(AppConfig.REQUEST_PARAM) String jsonParam);


    /**
     * 关键字搜索
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @POST("/app/search/listVehicleSimpleSearch")
    Call<ResponseResult> listVehicleSimpleSearch(@Field(AppConfig.REQUEST_PARAM) String jsonParam);


    /**
     * 高级搜索
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @POST("/app/search/listVehicleDetailSearch")
    Call<ResponseResult> listVehicleDetailSearch(@Field(AppConfig.REQUEST_PARAM) String jsonParam);
    /**
     * 请求应用APK版本号
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @POST("/app/version/getVersion")
    Call<ResponseResult> getVersion(@Field(AppConfig.REQUEST_PARAM) String jsonParam);
    /**
     * 下载应用apk
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @POST("/appFile/appPackage/download")
    Call<ResponseBody> downloadApp(@Field(AppConfig.REQUEST_PARAM) String jsonParam);
    /**
      * 下载doc文档
     * @param jsonParam
     * @return
             */
    @FormUrlEncoded
    @POST("/appFile/document/download")
    Call<ResponseBody> downloadDoc(@Field(AppConfig.REQUEST_PARAM) String jsonParam);


    /**
     * 修改密码
     * **/
    @FormUrlEncoded
    @POST("/app/login/updatePassWord")
    Call<ResponseResult> updatePassWord(@Field(AppConfig.REQUEST_PARAM) String jsonParam);

    /**
     * 请求数据库版本号
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @POST("/app/version/getEquipmentDBVersion")
    Call<ResponseResult> getEquipmentDBVersion(@Field(AppConfig.REQUEST_PARAM) String jsonParam);

    /**
     * 下载应用数据库
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @POST("/appFile/appEquipment/download")
    Call<ResponseBody> downloadDB(@Field(AppConfig.REQUEST_PARAM) String jsonParam);


    /**
     * 消息推送
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @POST("/app/message/listMessgePush")
    Call<ResponseResult> listMessgePush(@Field(AppConfig.REQUEST_PARAM) String jsonParam);

    /**
     * 获取特征库
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @POST("/app/queryImage/listRepositoryId")
    Call<ResponseResult> listRepositoryId(@Field(AppConfig.REQUEST_PARAM) String jsonParam);


}
