package com.yiqiao.cpmanager.http;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import com.yiqiao.cpmanager.entity.ContentBean;
import com.yiqiao.cpmanager.entity.HttpResult;

/**
 * Created by liukun on 16/3/9.
 */
public interface CpMgrApiService {
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";

    @GET("top250")
    Observable<HttpResult<List<ContentBean>>> getTopMovie(@Query("start") int start, @Query("count") int count);

}
