package com.yiqiao.cpmanager.http;

import com.yiqiao.cpmanager.entity.ChargeBackVo;
import com.yiqiao.cpmanager.entity.HttpResult;
import com.yiqiao.cpmanager.entity.InvoiceVo;
import com.yiqiao.cpmanager.entity.OrderDetailVo;
import com.yiqiao.cpmanager.entity.OrderVo;
import com.yiqiao.cpmanager.entity.ServiceVo;
import com.yiqiao.cpmanager.entity.TrademarkVo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by liukun on 16/3/9.
 */
public interface XtApiService {
//    public static final String BASE_URL = "http://xtapi.12366.com/";
//    public static final String BASE_URL = "http://apixt.cs.12366.com/";
    public static final String BASE_URL = "http://xtapi.cs.12366.com/";


    @GET("api/findOrderListByCustomerPage.do")
    Observable<HttpResult<List<OrderVo>>> findOrderListByCustomer(@Query("system_code") String systemcode
            , @Query("ts") String ts, @Query("param") String param, @Query("sign") String sign);

    @GET("api/order/orderCancle.do")
    Observable<HttpResult<List<OrderVo>>> orderCancle(@Query("system_code") String systemcode
            , @Query("ts") String ts, @Query("param") String param, @Query("sign") String sign);

    @GET("api/order/findSlbOrderInfo.do")
    Observable<HttpResult<OrderDetailVo>> getOrderInfoById(@Query("system_code") String systemcode
            , @Query("ts") String ts, @Query("param") String param, @Query("sign") String sign);

    @GET("api/order/findSlbOrderReturnsPageListByCustomId.do")
    Observable<HttpResult<List<ChargeBackVo>>> getChargebackList(@Query("system_code") String systemcode
            , @Query("ts") String ts, @Query("param") String param, @Query("sign") String sign);

    @GET("api/order/findSlbOrderReturnsPageListByCustomId.do")
    Observable<HttpResult<InvoiceVo>> getInvoiceList(@Query("system_code") String systemcode
            , @Query("ts") String ts, @Query("param") String param, @Query("sign") String sign);


    @GET("api/serviceProgress/findProgressPageByCustId.do")
    Observable<HttpResult<List<ServiceVo>>> getServiceList(@Query("system_code") String systemcode
            , @Query("ts") String ts, @Query("param") String param, @Query("sign") String sign);


    @GET("api/findNewTrademarkList.do")
    Observable<HttpResult<List<TrademarkVo>>> getTrademarkList(@Query("system_code") String systemcode
            , @Query("ts") String ts, @Query("param") String param, @Query("sign") String sign);




}
