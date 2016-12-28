package com.yiqiao.cpmanager.http;

import com.yiqiao.cpmanager.entity.HttpResult;
import com.yiqiao.cpmanager.entity.LoginVo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by liukun on 16/3/9.
 */
public interface CpMgrApiService {
//    public static final String BASE_URL = "http://xtapi.12366.com/";
//    public static final String BASE_URL = "http://xtapi.12366.com/testAPI.jsp";
    public static final String BASE_URL = "http://192.168.80.60:8060/api/";


    @GET("user/login")
    Observable<HttpResult<LoginVo>> login(@Query("systemcode") String systemcode
            , @Query("ts") String ts,@Query("param") String param,@Query("sign") String sign);

    @GET("user/login")
    Observable<HttpResult<String>> regist(@Query("system_code") String system_code
            , @Query("ts") String ts,@Query("param") String param,@Query("sign") String sign);


}
