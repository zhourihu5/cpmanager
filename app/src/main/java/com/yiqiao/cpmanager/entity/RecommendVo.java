package com.yiqiao.cpmanager.entity;

import java.io.Serializable;

/**
 * Created by Xu on 2017/1/5.
 * {
 "status": 200,
 "data": [
 {
 "id":主键,
 "title": 推荐标题,
 "platformType": 所属平台,
 "href": 链接地址(如果是商品的话为SKUID),
 "remark": 备注,
 "position1": 位置1,
 "position2": 位置2,
 "imageHref": 图片,
 "sort": 排序,
 "price": 价格,
 "createCustomerId": 创建人,
 "deleteStatus": 是否删除 0未删除 1已删除
 }
 ]
 }

 */

public class RecommendVo implements Serializable{
    private String id;
    private String title;
    private String href;
    private String remark;
    private String imageHref;
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
