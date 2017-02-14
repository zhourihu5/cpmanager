package com.yiqiao.cpmanager.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xu on 2017/2/8.
 */

public class SetmealVo implements Serializable{

    /**
     * packagePirce : {"id":15,"version":0,"packageId":44,"areaLevel1":"110000","areaLevel2":"110100","areaLevel3":"110108","price":0,"areaName1":"北京市","areaName2":"北京市","areaName3":"海淀区","storeId":"7,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,36,38,39,41,42,43,44,45,46,47,49,50,53,54,55,56,57,58,59,60,61,68,70,71,72,73,74,79,86,88,89,90,91,92,93,95,96,97,98,99,100,101,102,104,106,119,120,121,123,128,129,131,132,133,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,154,155,157,158,159,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,180,181,182,183,184,185,186,188,189,190,191,192,193,194,195,196,197,198,199,200,201,202,203,204,205,206,208,209,210,211,212,213,215,216,217,218,219,220,221,222,223,224,225,226,227,229,230,231,232,233,234,235,236,237,240,245,246,247,249,250,251,252,253,254,257"}
     * listSku : [{"version":0,"productSkuId":2516,"productSkuName":"内资公司注册","productSpuId":203,"areaLevel1":110000,"areaLevel2":110100,"areaLevel3":110108,"salesPrice":520,"integral":0},{"version":0,"productSkuId":2623,"productSkuName":"国、地税报到","productSpuId":215,"areaLevel1":110000,"areaLevel2":110100,"areaLevel3":110108,"salesPrice":600,"integral":0},{"version":0,"productSkuId":2633,"productSkuName":"开立银行基本账户","productSpuId":216,"areaLevel1":110000,"areaLevel2":110100,"areaLevel3":110108,"salesPrice":300,"integral":0},{"version":0,"productSkuId":7686,"productSkuName":"小规模企业代理记账（内资）","productSpuId":200,"areaLevel1":110000,"areaLevel2":110100,"areaLevel3":110108,"salesPrice":600,"integral":0}]
     * allIntegrial : 0
     * originPrice : 2020
     * areaTree : [{"fullName":"北京市","name":"北京市","path":",","areaCode":"110000","pid":0,"sort":0,"isLeaf":0,"updateTime":1461138945000,"nodes":[{"fullName":"北京市北京市","name":"北京市","path":",110000,","areaCode":"110100","pid":110000,"sort":0,"isLeaf":0,"updateTime":1461138945000,"nodes":[{"fullName":"北京市北京市市辖区东城区","name":"东城区","path":",110000,110100,","areaCode":"110101","pid":110100,"sort":0,"isLeaf":1,"updateTime":1461138945000,"id":110101,"createTime":1461138945000},{"fullName":"北京市北京市市辖区海淀区","name":"海淀区","path":",110000,110100,","areaCode":"110108","pid":110100,"sort":0,"isLeaf":1,"updateTime":1461138945000,"id":110108,"createTime":1461138945000}],"id":110100,"createTime":1461138945000}],"id":110000,"createTime":1461138945000},{"fullName":"广东省","name":"广东省","path":",","areaCode":"440000","pid":0,"sort":0,"isLeaf":0,"updateTime":1461138945000,"nodes":[{"fullName":"广东省东莞市","name":"东莞市","path":",440000,","areaCode":"441900","pid":440000,"sort":0,"isLeaf":0,"updateTime":1461138945000,"nodes":[{"fullName":"广东省东莞市东城区","name":"东城区","path":",440000,441900,","areaCode":"441901","pid":441900,"sort":0,"isLeaf":1,"updateTime":1461138945000,"id":441901,"createTime":1461138945000},{"fullName":"广东省东莞市东城区","name":"东城区","path":",440000,441900,","areaCode":"441901","pid":441900,"sort":0,"isLeaf":1,"updateTime":1461138945000,"id":441901,"createTime":1461138945000},{"fullName":"广东省东莞市东城区","name":"东城区","path":",440000,441900,","areaCode":"441901","pid":441900,"sort":0,"isLeaf":1,"updateTime":1461138945000,"id":441901,"createTime":1461138945000},{"fullName":"广东省东莞市东城区","name":"东城区","path":",440000,441900,","areaCode":"441901","pid":441900,"sort":0,"isLeaf":1,"updateTime":1461138945000,"id":441901,"createTime":1461138945000},{"fullName":"广东省东莞市东城区","name":"东城区","path":",440000,441900,","areaCode":"441901","pid":441900,"sort":0,"isLeaf":1,"updateTime":1461138945000,"id":441901,"createTime":1461138945000},{"fullName":"广东省东莞市东城区","name":"东城区","path":",440000,441900,","areaCode":"441901","pid":441900,"sort":0,"isLeaf":1,"updateTime":1461138945000,"id":441901,"createTime":1461138945000},{"fullName":"广东省东莞市南城区","name":"南城区","path":",440000,441900,","areaCode":"441902","pid":441900,"sort":0,"isLeaf":1,"updateTime":1461416402000,"id":441902,"createTime":1461416398000}],"id":441900,"createTime":1461138945000}],"id":440000,"createTime":1461138945000}]
     * packagePo : {"id":44,"version":0,"createTime":1473431562000,"productId":"[2516][7686][2623][2633]","packageName":"8.8元创业必备套餐","packagePrice":0,"packageCode":"P2016110216451084","deptsId":"7,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,36,38,39,41,42,43,44,45,46,47,49,50,53,54,55,56,57,58,59,60,61,68,70,71,72,73,74,79,86,88,89,90,91,92,93,95,96,97,98,99,100,101,102,104,106,119,120,121,123,128,129,131,132,133,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,154,155,157,158,159,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,180,181,182,183,184,185,186,188,189,190,191,192,193,194,195,196,197,198,199,200,201,202,203,204,205,206,208,209,210,211,212,213,215,216,217,218,219,220,221,222,223,224,225,226,227,229,230,231,232,233,234,235,236,237,240,245,246,247,249,250,251,252,253,254,257","packageServiceDetails":"null","imgUrl":"null","isShow":1,"publishUser":"admin","updateTime":1472459697000,"modifyUser":"null","remark":"null","deleteFlag":0,"saletype":1,"dottype":2}
     */

    private PackagePirceBean packagePirce;
    private String allIntegrial;
    private String originPrice;
    private PackagePoBean packagePo;
    private List<ListSkuBean> listSku;
    private ArrayList<SkuDetailVo.AreaListsBean> areaTree;

    public PackagePirceBean getPackagePirce() {
        return packagePirce;
    }

    public void setPackagePirce(PackagePirceBean packagePirce) {
        this.packagePirce = packagePirce;
    }

    public String getAllIntegrial() {
        return allIntegrial;
    }

    public void setAllIntegrial(String allIntegrial) {
        this.allIntegrial = allIntegrial;
    }

    public String getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(String originPrice) {
        this.originPrice = originPrice;
    }

    public PackagePoBean getPackagePo() {
        return packagePo;
    }

    public void setPackagePo(PackagePoBean packagePo) {
        this.packagePo = packagePo;
    }

    public List<ListSkuBean> getListSku() {
        return listSku;
    }

    public void setListSku(List<ListSkuBean> listSku) {
        this.listSku = listSku;
    }

    public ArrayList<SkuDetailVo.AreaListsBean> getAreaTree() {
        return areaTree;
    }

    public void setAreaTree(ArrayList<SkuDetailVo.AreaListsBean> areaTree) {
        this.areaTree = areaTree;
    }

    public static class PackagePirceBean {
        /**
         * id : 15
         * version : 0
         * packageId : 44
         * areaLevel1 : 110000
         * areaLevel2 : 110100
         * areaLevel3 : 110108
         * price : 0
         * areaName1 : 北京市
         * areaName2 : 北京市
         * areaName3 : 海淀区
         * storeId : 7,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,36,38,39,41,42,43,44,45,46,47,49,50,53,54,55,56,57,58,59,60,61,68,70,71,72,73,74,79,86,88,89,90,91,92,93,95,96,97,98,99,100,101,102,104,106,119,120,121,123,128,129,131,132,133,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,154,155,157,158,159,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,180,181,182,183,184,185,186,188,189,190,191,192,193,194,195,196,197,198,199,200,201,202,203,204,205,206,208,209,210,211,212,213,215,216,217,218,219,220,221,222,223,224,225,226,227,229,230,231,232,233,234,235,236,237,240,245,246,247,249,250,251,252,253,254,257
         */

        private String id;
        private String version;
        private String packageId;
        private String areaLevel1;
        private String areaLevel2;
        private String areaLevel3;
        private String price;
        private String areaName1;
        private String areaName2;
        private String areaName3;
        private String storeId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getPackageId() {
            return packageId;
        }

        public void setPackageId(String packageId) {
            this.packageId = packageId;
        }

        public String getAreaLevel1() {
            return areaLevel1;
        }

        public void setAreaLevel1(String areaLevel1) {
            this.areaLevel1 = areaLevel1;
        }

        public String getAreaLevel2() {
            return areaLevel2;
        }

        public void setAreaLevel2(String areaLevel2) {
            this.areaLevel2 = areaLevel2;
        }

        public String getAreaLevel3() {
            return areaLevel3;
        }

        public void setAreaLevel3(String areaLevel3) {
            this.areaLevel3 = areaLevel3;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getAreaName1() {
            return areaName1;
        }

        public void setAreaName1(String areaName1) {
            this.areaName1 = areaName1;
        }

        public String getAreaName2() {
            return areaName2;
        }

        public void setAreaName2(String areaName2) {
            this.areaName2 = areaName2;
        }

        public String getAreaName3() {
            return areaName3;
        }

        public void setAreaName3(String areaName3) {
            this.areaName3 = areaName3;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }
    }

    public static class PackagePoBean {
        /**
         * id : 44
         * version : 0
         * createTime : 1473431562000
         * productId : [2516][7686][2623][2633]
         * packageName : 8.8元创业必备套餐
         * packagePrice : 0
         * packageCode : P2016110216451084
         * deptsId : 7,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,36,38,39,41,42,43,44,45,46,47,49,50,53,54,55,56,57,58,59,60,61,68,70,71,72,73,74,79,86,88,89,90,91,92,93,95,96,97,98,99,100,101,102,104,106,119,120,121,123,128,129,131,132,133,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,154,155,157,158,159,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,180,181,182,183,184,185,186,188,189,190,191,192,193,194,195,196,197,198,199,200,201,202,203,204,205,206,208,209,210,211,212,213,215,216,217,218,219,220,221,222,223,224,225,226,227,229,230,231,232,233,234,235,236,237,240,245,246,247,249,250,251,252,253,254,257
         * packageServiceDetails : null
         * imgUrl : null
         * isShow : 1
         * publishUser : admin
         * updateTime : 1472459697000
         * modifyUser : null
         * remark : null
         * deleteFlag : 0
         * saletype : 1
         * dottype : 2
         */

        private String id;
        private String version;
        private long createTime;
        private String productId;
        private String packageName;
        private String packagePrice;
        private String packageCode;
        private String deptsId;
        private String packageServiceDetails;
        private String imgUrl;
        private String isShow;
        private String publishUser;
        private long updateTime;
        private String modifyUser;
        private String remark;
        private String deleteFlag;
        private String saletype;
        private String dottype;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getPackagePrice() {
            return packagePrice;
        }

        public void setPackagePrice(String packagePrice) {
            this.packagePrice = packagePrice;
        }

        public String getPackageCode() {
            return packageCode;
        }

        public void setPackageCode(String packageCode) {
            this.packageCode = packageCode;
        }

        public String getDeptsId() {
            return deptsId;
        }

        public void setDeptsId(String deptsId) {
            this.deptsId = deptsId;
        }

        public String getPackageServiceDetails() {
            return packageServiceDetails;
        }

        public void setPackageServiceDetails(String packageServiceDetails) {
            this.packageServiceDetails = packageServiceDetails;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getIsShow() {
            return isShow;
        }

        public void setIsShow(String isShow) {
            this.isShow = isShow;
        }

        public String getPublishUser() {
            return publishUser;
        }

        public void setPublishUser(String publishUser) {
            this.publishUser = publishUser;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getModifyUser() {
            return modifyUser;
        }

        public void setModifyUser(String modifyUser) {
            this.modifyUser = modifyUser;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(String deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public String getSaletype() {
            return saletype;
        }

        public void setSaletype(String saletype) {
            this.saletype = saletype;
        }

        public String getDottype() {
            return dottype;
        }

        public void setDottype(String dottype) {
            this.dottype = dottype;
        }
    }

    public static class ListSkuBean {
        /**
         * version : 0
         * productSkuId : 2516
         * productSkuName : 内资公司注册
         * productSpuId : 203
         * areaLevel1 : 110000
         * areaLevel2 : 110100
         * areaLevel3 : 110108
         * salesPrice : 520
         * integral : 0
         */

        private String version;
        private String productSkuId;
        private String productSkuName;
        private String productSpuId;
        private String areaLevel1;
        private String areaLevel2;
        private String areaLevel3;
        private String salesPrice;
        private String integral;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getProductSkuId() {
            return productSkuId;
        }

        public void setProductSkuId(String productSkuId) {
            this.productSkuId = productSkuId;
        }

        public String getProductSkuName() {
            return productSkuName;
        }

        public void setProductSkuName(String productSkuName) {
            this.productSkuName = productSkuName;
        }

        public String getProductSpuId() {
            return productSpuId;
        }

        public void setProductSpuId(String productSpuId) {
            this.productSpuId = productSpuId;
        }

        public String getAreaLevel1() {
            return areaLevel1;
        }

        public void setAreaLevel1(String areaLevel1) {
            this.areaLevel1 = areaLevel1;
        }

        public String getAreaLevel2() {
            return areaLevel2;
        }

        public void setAreaLevel2(String areaLevel2) {
            this.areaLevel2 = areaLevel2;
        }

        public String getAreaLevel3() {
            return areaLevel3;
        }

        public void setAreaLevel3(String areaLevel3) {
            this.areaLevel3 = areaLevel3;
        }

        public String getSalesPrice() {
            return salesPrice;
        }

        public void setSalesPrice(String salesPrice) {
            this.salesPrice = salesPrice;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }
    }

}
