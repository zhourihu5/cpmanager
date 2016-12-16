package com.yiqiao.cpmanager.http;

import com.yiqiao.cpmanager.entity.ContentBean;
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

    @GET("top250")
    Observable<HttpResult<List<ContentBean>>> getTopMovie(@Query("start") int start, @Query("count") int count);

    @GET("user/login")
    Observable<HttpResult<LoginVo>> login(@Query("username") String name, @Query("password") String pwd);


}
