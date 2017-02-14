package com.yiqiao.cpmanager.http;

import com.yiqiao.cpmanager.entity.ChargeBackVo;
import com.yiqiao.cpmanager.entity.CityVo;
import com.yiqiao.cpmanager.entity.CouponInfoVo;
import com.yiqiao.cpmanager.entity.CouponVo;
import com.yiqiao.cpmanager.entity.CreateOrderVo;
import com.yiqiao.cpmanager.entity.HttpResult;
import com.yiqiao.cpmanager.entity.LoginVo;
import com.yiqiao.cpmanager.entity.LuckyMoneyVo;
import com.yiqiao.cpmanager.entity.OrderDetailVo;
import com.yiqiao.cpmanager.entity.OrderVo;
import com.yiqiao.cpmanager.entity.RecommendVo;
import com.yiqiao.cpmanager.entity.ServiceTypeLevel1Vo;
import com.yiqiao.cpmanager.entity.ServiceTypeLevel2Vo;
import com.yiqiao.cpmanager.entity.SetmealVo;
import com.yiqiao.cpmanager.entity.SkuDetailVo;
import com.yiqiao.cpmanager.entity.SkuListVo;
import com.yiqiao.cpmanager.entity.StoreVo;
import com.yiqiao.cpmanager.entity.TaskOrProgressVo;
import com.yiqiao.cpmanager.entity.UserIndexInfo;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by liukun on 16/3/9.
 */
public interface CpMgrApiService {
//    public static final String BASE_URL = "http://xtapi.12366.com/";
//    public static final String BASE_URL = "http://xtapi.12366.com/testAPI.jsp";
//    public static final String BASE_URL = "http://192.168.80.60:8060/api/";
    public static final String BASE_URL = "http://www.cs.12366.com/api/";


    @GET("user/login")
    Observable<HttpResult<LoginVo>> login(@Query("systemcode") String systemcode
            , @Query("ts") String ts,@Query("param") String param,@Query("sign") String sign);

    @GET("user/loginSpeedVerificationPhone")
    Observable<HttpResult<LoginVo>> verifyPhone(@Query("systemcode") String systemcode
            , @Query("ts") String ts,@Query("param") String param,@Query("sign") String sign);

    @GET("user/loginSpeedVerificationCode")
    Observable<HttpResult<LoginVo>> verifyCode(@Query("systemcode") String systemcode
            , @Query("ts") String ts,@Query("param") String param,@Query("sign") String sign);

    @GET("user/register")
    Observable<HttpResult<LoginVo>> regist(@Query("system_code") String system_code
            , @Query("ts") String ts,@Query("param") String param,@Query("sign") String sign);

    @GET("user/phoneSMS")
    Observable<HttpResult<String>> getCode(@Query("system_code") String system_code
            , @Query("ts") String ts,@Query("param") String param,@Query("sign") String sign);

    @Headers({
            "Accept: application/json",
    })
    @Multipart
    @POST("http://ucenter5.cs.12366.com/index.php/Userthird/photoAdd")
    Observable<HttpResult<String>> photoAdd(@Part("phoneNum") RequestBody description,
                                            @Part MultipartBody.Part file);
//    Observable<HttpResult<String>> photoAdd(@Query("phoneNum") String phoneNum,
//                                            @Part MultipartBody.Part file);

    @GET("product/getFirstLevel")
    Observable<HttpResult<List<ServiceTypeLevel1Vo>>> getFirstLevel(@Query("system_code") String system_code
            , @Query("ts") String ts, @Query("param") String param, @Query("sign") String sign);

    @GET("product/getChildTypeByFrist")
    Observable<HttpResult<List<ServiceTypeLevel2Vo>>> getChildTypeByFrist(@Query("system_code") String system_code
            , @Query("ts") String ts, @Query("param") String param, @Query("sign") String sign);

    @GET("product/getProductOrPackage")
    Observable<HttpResult<SkuListVo>> getProductOrPackage(@Query("system_code") String system_code
            , @Query("ts") String ts, @Query("param") String param, @Query("sign") String sign);

    @GET("product/detail")
//    @GET("http://192.168.80.134:8080/product/detail")
    Observable<HttpResult<SkuDetailVo>> getProductDetail(@Query("system_code") String system_code
            , @Query("ts") String ts, @Query("param") String param, @Query("sign") String sign);

    @GET("productPackage/details")
    Observable<HttpResult<SetmealVo>> getProductPackageDetail(@Query("system_code") String system_code
            , @Query("ts") String ts, @Query("param") String param, @Query("sign") String sign);

    @GET("product/getStore")
    Observable<HttpResult<StoreVo>> getStore(@Query("system_code") String system_code
            , @Query("ts") String ts, @Query("param") String param, @Query("sign") String sign);

    @GET("placeOrder/createOrder")
    Observable<HttpResult<CreateOrderVo>> createOrder(@Query("system_code") String system_code
            , @Query("ts") String ts, @Query("param") String param, @Query("sign") String sign);

    @GET("user/activity/getUserRedDetail")
    Observable<HttpResult<LuckyMoneyVo>> getUserRedDetail(@Query("system_code") String system_code
            , @Query("ts") String ts, @Query("param") String param, @Query("sign") String sign);

    @GET("user/activity/getUserCouponDetail")
    Observable<HttpResult<CouponVo>> getUserCouponDetail(@Query("system_code") String system_code
            , @Query("ts") String ts, @Query("param") String param, @Query("sign") String sign);

    @GET("user/index/info")
    Observable<HttpResult<UserIndexInfo>> getUserIndexInfo(@Query("system_code") String system_code
            , @Query("ts") String ts, @Query("param") String param, @Query("sign") String sign);

    @GET("recommend/app/2/1/2")
    Observable<HttpResult<List<RecommendVo>>> getRecommendGoods(@Query("system_code") String system_code
            , @Query("ts") String ts, @Query("param") String param, @Query("sign") String sign);

    @GET("task/getTaskOrProgress")
    Observable<HttpResult<TaskOrProgressVo>> getTaskOrProgress(@Query("system_code") String system_code
            , @Query("ts") String ts, @Query("param") String param, @Query("sign") String sign);

    @GET("queryOrder/getOrderListForApp")
    Observable<HttpResult<List<OrderVo>>> getOrderListForApp(@Query("system_code") String system_code
            , @Query("ts") String ts, @Query("param") String param, @Query("sign") String sign);

    @GET("queryRefundOrder/refundOrderListForApp")
    Observable<HttpResult<List<ChargeBackVo>>> getChargebackList(@Query("system_code") String system_code
            , @Query("ts") String ts, @Query("param") String param, @Query("sign") String sign);

    @GET("appHome/openCity")
    Observable<HttpResult<List<CityVo>>> getOpenCity(@Query("system_code") String system_code
            , @Query("ts") String ts, @Query("param") String param, @Query("sign") String sign);

    @GET("placeOrder/orderInfo")
    Observable<HttpResult<OrderVo>> getOrderInfoById(@Query("system_code") String system_code
            , @Query("ts") String ts, @Query("param") String param, @Query("sign") String sign);

    @GET("placeOrder/coupon")
    Observable<HttpResult<CouponInfoVo>> getCouponBySku(@Query("system_code") String system_code
            , @Query("ts") String ts, @Query("param") String param, @Query("sign") String sign);
}
