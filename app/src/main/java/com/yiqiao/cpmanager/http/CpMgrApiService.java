package com.yiqiao.cpmanager.http;

import com.yiqiao.cpmanager.entity.ContentBean;
import com.yiqiao.cpmanager.entity.HttpResult;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by liukun on 16/3/9.
 */
public interface CpMgrApiService {
//    public static final String BASE_URL = "http://xtapi.12366.com/";
    public static final String BASE_URL = "http://xtapi.12366.com/testAPI.jsp";

    @GET("top250")
    Observable<HttpResult<List<ContentBean>>> getTopMovie(@Query("start") int start, @Query("count") int count);

}
