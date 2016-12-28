package com.yiqiao.cpmanager.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by liukun on 16/3/5.
 */
public class HttpResult<T> {

    // code 为返回的状态码, message 为返回的消息, 演示的没有这两个字段，考虑到真实的环境中基本包含就在这里写定值

    @SerializedName("returnCode")
    private int code = 0;
    @SerializedName("returnMsg")
    private String message = "OK";
    /**
     * data : [{"ascriptionerId":1523,"bargaining":1,"createTime":1481682210000,"createrId":1523,"creationTimestamp":1482233093302,"customerId":662214,"deleteRemark":false,"deptId":268,"finalAmount":900,"hadConfirmAmount":0,"hadInvoiceAmount":0,"hasEvaluate":"0","id":12540,"invoiceState":1,"isElectronicProtocol":false,"num":1,"orderKind":"2","orderSource":2,"orderSubmitStatus":1,"orderType":0,"ordersNo":"X20161214102329846","ordersState":2,"orgId":748,"payWay":1,"productName":"SZL测试分类SPU-市辖区null","productPrice":900,"productUnit":"元","shopOrgId":749,"skuId":17231,"toBePaid":900,"toBoInvoiceAmount":0,"totalAmount":900}]
     * page : 0
     * pageCount : 1
     * returnCode : 200
     * total : 1
     */

    private int page;
    private int pageCount;
    private int total;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    //用来模仿Data
    @SerializedName(value = "data", alternate = {"subjects"})
    private T data;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
