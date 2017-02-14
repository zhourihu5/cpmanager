package com.yiqiao.cpmanager.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Xu on 2016/12/30.
 */

public class SkuDetailVo2 implements Serializable{


    /**
     * skuInfoVo : {"version":0,"createTime":1482466513000,"productSkuId":75,"productSkuName":"刘宝专用SPU-SKU","productSpuId":14,"areaLevel1":110000,"areaLevel2":110200,"areaLevel3":110228,"redbagMostdedutionMoney":100,"couponMostdedutionMoney":200,"marketPrice":30,"salesPrice":10,"storePrice":20,"companyPrice":40,"productAttribute":"5,3,2","bargaining":1,"integral":50,"isContainService":0,"isPutSale":2,"serviceDetails":"<p>231312<\/p>","dotType":"0,1","saleType":"0,1,3","deleteFlag":0,"description":"刘宝专用SPU-SKU简介","keyWord":"lb","terraceType":"0,1,2","groupCode":"2016122380378","desc":"刘宝专用SPU-SKU简介","businessType":153,"attribbuteInfoVoList":[{"id":3,"version":0,"name":"区域","sort":3,"checkedItem":5,"attributeValuePoList":[{"id":5,"version":0,"productAttrId":3,"attrValueName":"不限","sort":1}]},{"id":2,"version":0,"name":"服务年限","sort":2,"checkedItem":3,"attributeValuePoList":[{"id":3,"version":0,"productAttrId":2,"attrValueName":"1年","sort":1}]}]}
     * listSku : [{"version":0,"productSkuId":75,"productSkuName":"刘宝专用SPU-SKU","productSpuId":14,"areaLevel1":110000,"areaLevel2":110200,"areaLevel3":110228,"redbagMostdedutionMoney":100,"couponMostdedutionMoney":200,"marketPrice":30,"salesPrice":10,"storePrice":20,"companyPrice":40,"productAttribute":"5,3,2","bargaining":1,"integral":50,"isContainService":0,"isPutSale":2,"dotType":"0,1","saleType":"0,1,3","deleteFlag":0,"description":"刘宝专用SPU-SKU简介","terraceType":"0,1,2"},{"version":0,"productSkuId":74,"productSkuName":"刘宝专用SPU-SKU","productSpuId":14,"areaLevel1":110000,"areaLevel2":110200,"areaLevel3":110228,"redbagMostdedutionMoney":100,"couponMostdedutionMoney":200,"marketPrice":30,"salesPrice":5,"storePrice":20,"companyPrice":40,"productAttribute":"","bargaining":2,"integral":50,"isContainService":0,"isPutSale":2,"dotType":"0,1","saleType":"0,1,3","deleteFlag":0,"description":"刘宝专用SPU-SKU简介","terraceType":"0,1,2"}]
     * areaLists : [{"fullName":"北京市","name":"北京市","path":",","areaCode":"110000","pid":0,"sort":0,"isLeaf":0,"updateTime":1461138945000,"nodes":[{"fullName":"北京市县","name":"县","path":",110000,","areaCode":"110200","pid":110000,"sort":0,"isLeaf":0,"updateTime":1461138945000,"nodes":[{"fullName":"北京市县密云县","name":"密云县","path":",110000,110200,","areaCode":"110228","pid":110200,"sort":0,"isLeaf":1,"updateTime":1461138945000,"id":110228,"createTime":1461138945000}],"id":110200,"createTime":1461138945000}],"id":110000,"createTime":1461138945000}]
     * allListSku : [{"version":0,"productSkuId":75,"productSkuName":"刘宝专用SPU-SKU","productSpuId":14,"areaLevel1":110000,"areaLevel2":110200,"areaLevel3":110228,"redbagMostdedutionMoney":100,"couponMostdedutionMoney":200,"marketPrice":30,"salesPrice":10,"storePrice":20,"companyPrice":40,"productAttribute":"5,3,2","bargaining":1,"integral":50,"isContainService":0,"isPutSale":2,"dotType":"0,1","saleType":"0,1,3","deleteFlag":0,"description":"刘宝专用SPU-SKU简介","terraceType":"0,1,2"},{"version":0,"productSkuId":74,"productSkuName":"刘宝专用SPU-SKU","productSpuId":14,"areaLevel1":110000,"areaLevel2":110200,"areaLevel3":110228,"redbagMostdedutionMoney":100,"couponMostdedutionMoney":200,"marketPrice":30,"salesPrice":5,"storePrice":20,"companyPrice":40,"productAttribute":"","bargaining":2,"integral":50,"isContainService":0,"isPutSale":2,"dotType":"0,1","saleType":"0,1,3","deleteFlag":0,"description":"刘宝专用SPU-SKU简介","terraceType":"0,1,2"}]
     * huodong : {"redPacket":{"id":2,"version":0,"redName":"测试红包","redNum":1,"redAmount":2,"startTime":1481446071000,"endTime":1482569275000,"redNo":"23232332","deleteFlag":0,"issueStatus":1,"goods":0},"listAllActivity":[],"listCoupon":[{"id":394,"version":0,"createTime":1481697977000,"name":"15元优惠券","amount":15,"expiryDateType":0,"startDate":1481558400000,"endDate":1483113600000,"expiryDateNum":0,"num":25,"sendedNum":25,"shopType":"0","goods":0,"zscqGoods":0,"conditions":8,"enabled":1,"remark":"描述15","exchange":0,"point":0,"channels":2,"code":"201612148200333","deleteFlag":0,"issueStatus":1,"useRule":2,"updateTime":1481698023000}]}
     * spuPo : {"version":0,"productSpuId":14,"productName":"刘宝专用SPU","productLabel":"x,y,z,o","productType1Id":1,"productType2Id":2,"productType3Id":3,"businessType":150,"logoImage":"cpsupload/pic/20161223120802115941.png","serviceDescrible":"刘宝专用SPU-服务简介","isPutSale":2,"sort":6,"unit":"kg","maxQuantity":20,"minQuantity":10,"remark":"<p style=\"\">刘宝专用SPU-服务详情<\/p>","deleteFlag":0,"createDate":1482466354000}
     * serviceProducts : []
     * giveaway : []
     */

    private SkuInfoVoBean skuInfoVo;
    private HuodongBean huodong;
    private SpuPoBean spuPo;
    private List<ListSkuBean> listSku;
    private List<AreaListsBean> areaLists;
    private List<AllListSkuBean> allListSku;
    private List<?> serviceProducts;
    private List<?> giveaway;

    public SkuInfoVoBean getSkuInfoVo() {
        return skuInfoVo;
    }

    public void setSkuInfoVo(SkuInfoVoBean skuInfoVo) {
        this.skuInfoVo = skuInfoVo;
    }

    public HuodongBean getHuodong() {
        return huodong;
    }

    public void setHuodong(HuodongBean huodong) {
        this.huodong = huodong;
    }

    public SpuPoBean getSpuPo() {
        return spuPo;
    }

    public void setSpuPo(SpuPoBean spuPo) {
        this.spuPo = spuPo;
    }

    public List<ListSkuBean> getListSku() {
        return listSku;
    }

    public void setListSku(List<ListSkuBean> listSku) {
        this.listSku = listSku;
    }

    public List<AreaListsBean> getAreaLists() {
        return areaLists;
    }

    public void setAreaLists(List<AreaListsBean> areaLists) {
        this.areaLists = areaLists;
    }

    public List<AllListSkuBean> getAllListSku() {
        return allListSku;
    }

    public void setAllListSku(List<AllListSkuBean> allListSku) {
        this.allListSku = allListSku;
    }

    public List<?> getServiceProducts() {
        return serviceProducts;
    }

    public void setServiceProducts(List<?> serviceProducts) {
        this.serviceProducts = serviceProducts;
    }

    public List<?> getGiveaway() {
        return giveaway;
    }

    public void setGiveaway(List<?> giveaway) {
        this.giveaway = giveaway;
    }

    public static class SkuInfoVoBean {
        /**
         * version : 0
         * createTime : 1482466513000
         * productSkuId : 75
         * productSkuName : 刘宝专用SPU-SKU
         * productSpuId : 14
         * areaLevel1 : 110000
         * areaLevel2 : 110200
         * areaLevel3 : 110228
         * redbagMostdedutionMoney : 100
         * couponMostdedutionMoney : 200
         * marketPrice : 30
         * salesPrice : 10
         * storePrice : 20
         * companyPrice : 40
         * productAttribute : 5,3,2
         * bargaining : 1
         * integral : 50
         * isContainService : 0
         * isPutSale : 2
         * serviceDetails : <p>231312</p>
         * dotType : 0,1
         * saleType : 0,1,3
         * deleteFlag : 0
         * description : 刘宝专用SPU-SKU简介
         * keyWord : lb
         * terraceType : 0,1,2
         * groupCode : 2016122380378
         * desc : 刘宝专用SPU-SKU简介
         * businessType : 153
         * attribbuteInfoVoList : [{"id":3,"version":0,"name":"区域","sort":3,"checkedItem":5,"attributeValuePoList":[{"id":5,"version":0,"productAttrId":3,"attrValueName":"不限","sort":1}]},{"id":2,"version":0,"name":"服务年限","sort":2,"checkedItem":3,"attributeValuePoList":[{"id":3,"version":0,"productAttrId":2,"attrValueName":"1年","sort":1}]}]
         */

        private int version;
        private long createTime;
        private int productSkuId;
        private String productSkuName;
        private int productSpuId;
        private int areaLevel1;
        private int areaLevel2;
        private int areaLevel3;
        private int redbagMostdedutionMoney;
        private int couponMostdedutionMoney;
        private int marketPrice;
        private int salesPrice;
        private int storePrice;
        private int companyPrice;
        private String productAttribute;
        private int bargaining;
        private int integral;
        private int isContainService;
        private int isPutSale;
        private String serviceDetails;
        private String dotType;
        private String saleType;
        private int deleteFlag;
        private String description;
        private String keyWord;
        private String terraceType;
        private String groupCode;
        private String desc;
        private int businessType;
        private List<AttribbuteInfoVoListBean> attribbuteInfoVoList;

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getProductSkuId() {
            return productSkuId;
        }

        public void setProductSkuId(int productSkuId) {
            this.productSkuId = productSkuId;
        }

        public String getProductSkuName() {
            return productSkuName;
        }

        public void setProductSkuName(String productSkuName) {
            this.productSkuName = productSkuName;
        }

        public int getProductSpuId() {
            return productSpuId;
        }

        public void setProductSpuId(int productSpuId) {
            this.productSpuId = productSpuId;
        }

        public int getAreaLevel1() {
            return areaLevel1;
        }

        public void setAreaLevel1(int areaLevel1) {
            this.areaLevel1 = areaLevel1;
        }

        public int getAreaLevel2() {
            return areaLevel2;
        }

        public void setAreaLevel2(int areaLevel2) {
            this.areaLevel2 = areaLevel2;
        }

        public int getAreaLevel3() {
            return areaLevel3;
        }

        public void setAreaLevel3(int areaLevel3) {
            this.areaLevel3 = areaLevel3;
        }

        public int getRedbagMostdedutionMoney() {
            return redbagMostdedutionMoney;
        }

        public void setRedbagMostdedutionMoney(int redbagMostdedutionMoney) {
            this.redbagMostdedutionMoney = redbagMostdedutionMoney;
        }

        public int getCouponMostdedutionMoney() {
            return couponMostdedutionMoney;
        }

        public void setCouponMostdedutionMoney(int couponMostdedutionMoney) {
            this.couponMostdedutionMoney = couponMostdedutionMoney;
        }

        public int getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(int marketPrice) {
            this.marketPrice = marketPrice;
        }

        public int getSalesPrice() {
            return salesPrice;
        }

        public void setSalesPrice(int salesPrice) {
            this.salesPrice = salesPrice;
        }

        public int getStorePrice() {
            return storePrice;
        }

        public void setStorePrice(int storePrice) {
            this.storePrice = storePrice;
        }

        public int getCompanyPrice() {
            return companyPrice;
        }

        public void setCompanyPrice(int companyPrice) {
            this.companyPrice = companyPrice;
        }

        public String getProductAttribute() {
            return productAttribute;
        }

        public void setProductAttribute(String productAttribute) {
            this.productAttribute = productAttribute;
        }

        public int getBargaining() {
            return bargaining;
        }

        public void setBargaining(int bargaining) {
            this.bargaining = bargaining;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public int getIsContainService() {
            return isContainService;
        }

        public void setIsContainService(int isContainService) {
            this.isContainService = isContainService;
        }

        public int getIsPutSale() {
            return isPutSale;
        }

        public void setIsPutSale(int isPutSale) {
            this.isPutSale = isPutSale;
        }

        public String getServiceDetails() {
            return serviceDetails;
        }

        public void setServiceDetails(String serviceDetails) {
            this.serviceDetails = serviceDetails;
        }

        public String getDotType() {
            return dotType;
        }

        public void setDotType(String dotType) {
            this.dotType = dotType;
        }

        public String getSaleType() {
            return saleType;
        }

        public void setSaleType(String saleType) {
            this.saleType = saleType;
        }

        public int getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(int deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getKeyWord() {
            return keyWord;
        }

        public void setKeyWord(String keyWord) {
            this.keyWord = keyWord;
        }

        public String getTerraceType() {
            return terraceType;
        }

        public void setTerraceType(String terraceType) {
            this.terraceType = terraceType;
        }

        public String getGroupCode() {
            return groupCode;
        }

        public void setGroupCode(String groupCode) {
            this.groupCode = groupCode;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getBusinessType() {
            return businessType;
        }

        public void setBusinessType(int businessType) {
            this.businessType = businessType;
        }

        public List<AttribbuteInfoVoListBean> getAttribbuteInfoVoList() {
            return attribbuteInfoVoList;
        }

        public void setAttribbuteInfoVoList(List<AttribbuteInfoVoListBean> attribbuteInfoVoList) {
            this.attribbuteInfoVoList = attribbuteInfoVoList;
        }

        public static class AttribbuteInfoVoListBean {
            /**
             * id : 3
             * version : 0
             * name : 区域
             * sort : 3
             * checkedItem : 5
             * attributeValuePoList : [{"id":5,"version":0,"productAttrId":3,"attrValueName":"不限","sort":1}]
             */

            private int id;
            private int version;
            private String name;
            private int sort;
            private int checkedItem;
            private List<AttributeValuePoListBean> attributeValuePoList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getCheckedItem() {
                return checkedItem;
            }

            public void setCheckedItem(int checkedItem) {
                this.checkedItem = checkedItem;
            }

            public List<AttributeValuePoListBean> getAttributeValuePoList() {
                return attributeValuePoList;
            }

            public void setAttributeValuePoList(List<AttributeValuePoListBean> attributeValuePoList) {
                this.attributeValuePoList = attributeValuePoList;
            }

            public static class AttributeValuePoListBean {
                /**
                 * id : 5
                 * version : 0
                 * productAttrId : 3
                 * attrValueName : 不限
                 * sort : 1
                 */

                private int id;
                private int version;
                private int productAttrId;
                private String attrValueName;
                private int sort;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getVersion() {
                    return version;
                }

                public void setVersion(int version) {
                    this.version = version;
                }

                public int getProductAttrId() {
                    return productAttrId;
                }

                public void setProductAttrId(int productAttrId) {
                    this.productAttrId = productAttrId;
                }

                public String getAttrValueName() {
                    return attrValueName;
                }

                public void setAttrValueName(String attrValueName) {
                    this.attrValueName = attrValueName;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }
            }
        }
    }

    public static class HuodongBean {
        /**
         * redPacket : {"id":2,"version":0,"redName":"测试红包","redNum":1,"redAmount":2,"startTime":1481446071000,"endTime":1482569275000,"redNo":"23232332","deleteFlag":0,"issueStatus":1,"goods":0}
         * listAllActivity : []
         * listCoupon : [{"id":394,"version":0,"createTime":1481697977000,"name":"15元优惠券","amount":15,"expiryDateType":0,"startDate":1481558400000,"endDate":1483113600000,"expiryDateNum":0,"num":25,"sendedNum":25,"shopType":"0","goods":0,"zscqGoods":0,"conditions":8,"enabled":1,"remark":"描述15","exchange":0,"point":0,"channels":2,"code":"201612148200333","deleteFlag":0,"issueStatus":1,"useRule":2,"updateTime":1481698023000}]
         */

        private RedPacketBean redPacket;
        private List<?> listAllActivity;
        private List<ListCouponBean> listCoupon;

        public RedPacketBean getRedPacket() {
            return redPacket;
        }

        public void setRedPacket(RedPacketBean redPacket) {
            this.redPacket = redPacket;
        }

        public List<?> getListAllActivity() {
            return listAllActivity;
        }

        public void setListAllActivity(List<?> listAllActivity) {
            this.listAllActivity = listAllActivity;
        }

        public List<ListCouponBean> getListCoupon() {
            return listCoupon;
        }

        public void setListCoupon(List<ListCouponBean> listCoupon) {
            this.listCoupon = listCoupon;
        }

        public static class RedPacketBean {
            /**
             * id : 2
             * version : 0
             * redName : 测试红包
             * redNum : 1
             * redAmount : 2
             * startTime : 1481446071000
             * endTime : 1482569275000
             * redNo : 23232332
             * deleteFlag : 0
             * issueStatus : 1
             * goods : 0
             */

            private int id;
            private int version;
            private String redName;
            private int redNum;
            private int redAmount;
            private long startTime;
            private long endTime;
            private String redNo;
            private int deleteFlag;
            private int issueStatus;
            private int goods;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public String getRedName() {
                return redName;
            }

            public void setRedName(String redName) {
                this.redName = redName;
            }

            public int getRedNum() {
                return redNum;
            }

            public void setRedNum(int redNum) {
                this.redNum = redNum;
            }

            public int getRedAmount() {
                return redAmount;
            }

            public void setRedAmount(int redAmount) {
                this.redAmount = redAmount;
            }

            public long getStartTime() {
                return startTime;
            }

            public void setStartTime(long startTime) {
                this.startTime = startTime;
            }

            public long getEndTime() {
                return endTime;
            }

            public void setEndTime(long endTime) {
                this.endTime = endTime;
            }

            public String getRedNo() {
                return redNo;
            }

            public void setRedNo(String redNo) {
                this.redNo = redNo;
            }

            public int getDeleteFlag() {
                return deleteFlag;
            }

            public void setDeleteFlag(int deleteFlag) {
                this.deleteFlag = deleteFlag;
            }

            public int getIssueStatus() {
                return issueStatus;
            }

            public void setIssueStatus(int issueStatus) {
                this.issueStatus = issueStatus;
            }

            public int getGoods() {
                return goods;
            }

            public void setGoods(int goods) {
                this.goods = goods;
            }
        }

        public static class ListCouponBean {
            /**
             * id : 394
             * version : 0
             * createTime : 1481697977000
             * name : 15元优惠券
             * amount : 15
             * expiryDateType : 0
             * startDate : 1481558400000
             * endDate : 1483113600000
             * expiryDateNum : 0
             * num : 25
             * sendedNum : 25
             * shopType : 0
             * goods : 0
             * zscqGoods : 0
             * conditions : 8
             * enabled : 1
             * remark : 描述15
             * exchange : 0
             * point : 0
             * channels : 2
             * code : 201612148200333
             * deleteFlag : 0
             * issueStatus : 1
             * useRule : 2
             * updateTime : 1481698023000
             */

            private int id;
            private int version;
            private long createTime;
            private String name;
            private int amount;
            private int expiryDateType;
            private long startDate;
            private long endDate;
            private int expiryDateNum;
            private int num;
            private int sendedNum;
            private String shopType;
            private int goods;
            private int zscqGoods;
            private int conditions;
            private int enabled;
            private String remark;
            private int exchange;
            private int point;
            private int channels;
            private String code;
            private int deleteFlag;
            private int issueStatus;
            private int useRule;
            private long updateTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public int getExpiryDateType() {
                return expiryDateType;
            }

            public void setExpiryDateType(int expiryDateType) {
                this.expiryDateType = expiryDateType;
            }

            public long getStartDate() {
                return startDate;
            }

            public void setStartDate(long startDate) {
                this.startDate = startDate;
            }

            public long getEndDate() {
                return endDate;
            }

            public void setEndDate(long endDate) {
                this.endDate = endDate;
            }

            public int getExpiryDateNum() {
                return expiryDateNum;
            }

            public void setExpiryDateNum(int expiryDateNum) {
                this.expiryDateNum = expiryDateNum;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public int getSendedNum() {
                return sendedNum;
            }

            public void setSendedNum(int sendedNum) {
                this.sendedNum = sendedNum;
            }

            public String getShopType() {
                return shopType;
            }

            public void setShopType(String shopType) {
                this.shopType = shopType;
            }

            public int getGoods() {
                return goods;
            }

            public void setGoods(int goods) {
                this.goods = goods;
            }

            public int getZscqGoods() {
                return zscqGoods;
            }

            public void setZscqGoods(int zscqGoods) {
                this.zscqGoods = zscqGoods;
            }

            public int getConditions() {
                return conditions;
            }

            public void setConditions(int conditions) {
                this.conditions = conditions;
            }

            public int getEnabled() {
                return enabled;
            }

            public void setEnabled(int enabled) {
                this.enabled = enabled;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getExchange() {
                return exchange;
            }

            public void setExchange(int exchange) {
                this.exchange = exchange;
            }

            public int getPoint() {
                return point;
            }

            public void setPoint(int point) {
                this.point = point;
            }

            public int getChannels() {
                return channels;
            }

            public void setChannels(int channels) {
                this.channels = channels;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public int getDeleteFlag() {
                return deleteFlag;
            }

            public void setDeleteFlag(int deleteFlag) {
                this.deleteFlag = deleteFlag;
            }

            public int getIssueStatus() {
                return issueStatus;
            }

            public void setIssueStatus(int issueStatus) {
                this.issueStatus = issueStatus;
            }

            public int getUseRule() {
                return useRule;
            }

            public void setUseRule(int useRule) {
                this.useRule = useRule;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }
        }
    }

    public static class SpuPoBean {
        /**
         * version : 0
         * productSpuId : 14
         * productName : 刘宝专用SPU
         * productLabel : x,y,z,o
         * productType1Id : 1
         * productType2Id : 2
         * productType3Id : 3
         * businessType : 150
         * logoImage : cpsupload/pic/20161223120802115941.png
         * serviceDescrible : 刘宝专用SPU-服务简介
         * isPutSale : 2
         * sort : 6
         * unit : kg
         * maxQuantity : 20
         * minQuantity : 10
         * remark : <p style="">刘宝专用SPU-服务详情</p>
         * deleteFlag : 0
         * createDate : 1482466354000
         */

        private int version;
        private int productSpuId;
        private String productName;
        private String productLabel;
        private int productType1Id;
        private int productType2Id;
        private int productType3Id;
        private int businessType;
        private String logoImage;
        private String serviceDescrible;
        private int isPutSale;
        private int sort;
        private String unit;
        private int maxQuantity;
        private int minQuantity;
        private String remark;
        private int deleteFlag;
        private long createDate;

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public int getProductSpuId() {
            return productSpuId;
        }

        public void setProductSpuId(int productSpuId) {
            this.productSpuId = productSpuId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductLabel() {
            return productLabel;
        }

        public void setProductLabel(String productLabel) {
            this.productLabel = productLabel;
        }

        public int getProductType1Id() {
            return productType1Id;
        }

        public void setProductType1Id(int productType1Id) {
            this.productType1Id = productType1Id;
        }

        public int getProductType2Id() {
            return productType2Id;
        }

        public void setProductType2Id(int productType2Id) {
            this.productType2Id = productType2Id;
        }

        public int getProductType3Id() {
            return productType3Id;
        }

        public void setProductType3Id(int productType3Id) {
            this.productType3Id = productType3Id;
        }

        public int getBusinessType() {
            return businessType;
        }

        public void setBusinessType(int businessType) {
            this.businessType = businessType;
        }

        public String getLogoImage() {
            return logoImage;
        }

        public void setLogoImage(String logoImage) {
            this.logoImage = logoImage;
        }

        public String getServiceDescrible() {
            return serviceDescrible;
        }

        public void setServiceDescrible(String serviceDescrible) {
            this.serviceDescrible = serviceDescrible;
        }

        public int getIsPutSale() {
            return isPutSale;
        }

        public void setIsPutSale(int isPutSale) {
            this.isPutSale = isPutSale;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getMaxQuantity() {
            return maxQuantity;
        }

        public void setMaxQuantity(int maxQuantity) {
            this.maxQuantity = maxQuantity;
        }

        public int getMinQuantity() {
            return minQuantity;
        }

        public void setMinQuantity(int minQuantity) {
            this.minQuantity = minQuantity;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(int deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }
    }

    public static class ListSkuBean {
        /**
         * version : 0
         * productSkuId : 75
         * productSkuName : 刘宝专用SPU-SKU
         * productSpuId : 14
         * areaLevel1 : 110000
         * areaLevel2 : 110200
         * areaLevel3 : 110228
         * redbagMostdedutionMoney : 100
         * couponMostdedutionMoney : 200
         * marketPrice : 30
         * salesPrice : 10
         * storePrice : 20
         * companyPrice : 40
         * productAttribute : 5,3,2
         * bargaining : 1
         * integral : 50
         * isContainService : 0
         * isPutSale : 2
         * dotType : 0,1
         * saleType : 0,1,3
         * deleteFlag : 0
         * description : 刘宝专用SPU-SKU简介
         * terraceType : 0,1,2
         */

        private int version;
        private int productSkuId;
        private String productSkuName;
        private int productSpuId;
        private int areaLevel1;
        private int areaLevel2;
        private int areaLevel3;
        private int redbagMostdedutionMoney;
        private int couponMostdedutionMoney;
        private int marketPrice;
        private int salesPrice;
        private int storePrice;
        private int companyPrice;
        private String productAttribute;
        private int bargaining;
        private int integral;
        private int isContainService;
        private int isPutSale;
        private String dotType;
        private String saleType;
        private int deleteFlag;
        private String description;
        private String terraceType;

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public int getProductSkuId() {
            return productSkuId;
        }

        public void setProductSkuId(int productSkuId) {
            this.productSkuId = productSkuId;
        }

        public String getProductSkuName() {
            return productSkuName;
        }

        public void setProductSkuName(String productSkuName) {
            this.productSkuName = productSkuName;
        }

        public int getProductSpuId() {
            return productSpuId;
        }

        public void setProductSpuId(int productSpuId) {
            this.productSpuId = productSpuId;
        }

        public int getAreaLevel1() {
            return areaLevel1;
        }

        public void setAreaLevel1(int areaLevel1) {
            this.areaLevel1 = areaLevel1;
        }

        public int getAreaLevel2() {
            return areaLevel2;
        }

        public void setAreaLevel2(int areaLevel2) {
            this.areaLevel2 = areaLevel2;
        }

        public int getAreaLevel3() {
            return areaLevel3;
        }

        public void setAreaLevel3(int areaLevel3) {
            this.areaLevel3 = areaLevel3;
        }

        public int getRedbagMostdedutionMoney() {
            return redbagMostdedutionMoney;
        }

        public void setRedbagMostdedutionMoney(int redbagMostdedutionMoney) {
            this.redbagMostdedutionMoney = redbagMostdedutionMoney;
        }

        public int getCouponMostdedutionMoney() {
            return couponMostdedutionMoney;
        }

        public void setCouponMostdedutionMoney(int couponMostdedutionMoney) {
            this.couponMostdedutionMoney = couponMostdedutionMoney;
        }

        public int getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(int marketPrice) {
            this.marketPrice = marketPrice;
        }

        public int getSalesPrice() {
            return salesPrice;
        }

        public void setSalesPrice(int salesPrice) {
            this.salesPrice = salesPrice;
        }

        public int getStorePrice() {
            return storePrice;
        }

        public void setStorePrice(int storePrice) {
            this.storePrice = storePrice;
        }

        public int getCompanyPrice() {
            return companyPrice;
        }

        public void setCompanyPrice(int companyPrice) {
            this.companyPrice = companyPrice;
        }

        public String getProductAttribute() {
            return productAttribute;
        }

        public void setProductAttribute(String productAttribute) {
            this.productAttribute = productAttribute;
        }

        public int getBargaining() {
            return bargaining;
        }

        public void setBargaining(int bargaining) {
            this.bargaining = bargaining;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public int getIsContainService() {
            return isContainService;
        }

        public void setIsContainService(int isContainService) {
            this.isContainService = isContainService;
        }

        public int getIsPutSale() {
            return isPutSale;
        }

        public void setIsPutSale(int isPutSale) {
            this.isPutSale = isPutSale;
        }

        public String getDotType() {
            return dotType;
        }

        public void setDotType(String dotType) {
            this.dotType = dotType;
        }

        public String getSaleType() {
            return saleType;
        }

        public void setSaleType(String saleType) {
            this.saleType = saleType;
        }

        public int getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(int deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTerraceType() {
            return terraceType;
        }

        public void setTerraceType(String terraceType) {
            this.terraceType = terraceType;
        }
    }

    public static class AreaListsBean {
        /**
         * fullName : 北京市
         * name : 北京市
         * path : ,
         * areaCode : 110000
         * pid : 0
         * sort : 0
         * isLeaf : 0
         * updateTime : 1461138945000
         * nodes : [{"fullName":"北京市县","name":"县","path":",110000,","areaCode":"110200","pid":110000,"sort":0,"isLeaf":0,"updateTime":1461138945000,"nodes":[{"fullName":"北京市县密云县","name":"密云县","path":",110000,110200,","areaCode":"110228","pid":110200,"sort":0,"isLeaf":1,"updateTime":1461138945000,"id":110228,"createTime":1461138945000}],"id":110200,"createTime":1461138945000}]
         * id : 110000
         * createTime : 1461138945000
         */

        private String fullName;
        private String name;
        private String path;
        private String areaCode;
        private int pid;
        private int sort;
        private int isLeaf;
        private long updateTime;
        private int id;
        private long createTime;
        private List<NodesBeanX> nodes;

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getIsLeaf() {
            return isLeaf;
        }

        public void setIsLeaf(int isLeaf) {
            this.isLeaf = isLeaf;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public List<NodesBeanX> getNodes() {
            return nodes;
        }

        public void setNodes(List<NodesBeanX> nodes) {
            this.nodes = nodes;
        }

        public static class NodesBeanX {
            /**
             * fullName : 北京市县
             * name : 县
             * path : ,110000,
             * areaCode : 110200
             * pid : 110000
             * sort : 0
             * isLeaf : 0
             * updateTime : 1461138945000
             * nodes : [{"fullName":"北京市县密云县","name":"密云县","path":",110000,110200,","areaCode":"110228","pid":110200,"sort":0,"isLeaf":1,"updateTime":1461138945000,"id":110228,"createTime":1461138945000}]
             * id : 110200
             * createTime : 1461138945000
             */

            private String fullName;
            private String name;
            private String path;
            private String areaCode;
            private int pid;
            private int sort;
            private int isLeaf;
            private long updateTime;
            private int id;
            private long createTime;
            private List<NodesBean> nodes;

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getAreaCode() {
                return areaCode;
            }

            public void setAreaCode(String areaCode) {
                this.areaCode = areaCode;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getIsLeaf() {
                return isLeaf;
            }

            public void setIsLeaf(int isLeaf) {
                this.isLeaf = isLeaf;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public List<NodesBean> getNodes() {
                return nodes;
            }

            public void setNodes(List<NodesBean> nodes) {
                this.nodes = nodes;
            }

            public static class NodesBean {
                /**
                 * fullName : 北京市县密云县
                 * name : 密云县
                 * path : ,110000,110200,
                 * areaCode : 110228
                 * pid : 110200
                 * sort : 0
                 * isLeaf : 1
                 * updateTime : 1461138945000
                 * id : 110228
                 * createTime : 1461138945000
                 */

                private String fullName;
                private String name;
                private String path;
                private String areaCode;
                private int pid;
                private int sort;
                private int isLeaf;
                private long updateTime;
                private int id;
                private long createTime;

                public String getFullName() {
                    return fullName;
                }

                public void setFullName(String fullName) {
                    this.fullName = fullName;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPath() {
                    return path;
                }

                public void setPath(String path) {
                    this.path = path;
                }

                public String getAreaCode() {
                    return areaCode;
                }

                public void setAreaCode(String areaCode) {
                    this.areaCode = areaCode;
                }

                public int getPid() {
                    return pid;
                }

                public void setPid(int pid) {
                    this.pid = pid;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

                public int getIsLeaf() {
                    return isLeaf;
                }

                public void setIsLeaf(int isLeaf) {
                    this.isLeaf = isLeaf;
                }

                public long getUpdateTime() {
                    return updateTime;
                }

                public void setUpdateTime(long updateTime) {
                    this.updateTime = updateTime;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public long getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
                }
            }
        }
    }

    public static class AllListSkuBean {
        /**
         * version : 0
         * productSkuId : 75
         * productSkuName : 刘宝专用SPU-SKU
         * productSpuId : 14
         * areaLevel1 : 110000
         * areaLevel2 : 110200
         * areaLevel3 : 110228
         * redbagMostdedutionMoney : 100
         * couponMostdedutionMoney : 200
         * marketPrice : 30
         * salesPrice : 10
         * storePrice : 20
         * companyPrice : 40
         * productAttribute : 5,3,2
         * bargaining : 1
         * integral : 50
         * isContainService : 0
         * isPutSale : 2
         * dotType : 0,1
         * saleType : 0,1,3
         * deleteFlag : 0
         * description : 刘宝专用SPU-SKU简介
         * terraceType : 0,1,2
         */

        private int version;
        private int productSkuId;
        private String productSkuName;
        private int productSpuId;
        private int areaLevel1;
        private int areaLevel2;
        private int areaLevel3;
        private int redbagMostdedutionMoney;
        private int couponMostdedutionMoney;
        private int marketPrice;
        private int salesPrice;
        private int storePrice;
        private int companyPrice;
        private String productAttribute;
        private int bargaining;
        private int integral;
        private int isContainService;
        private int isPutSale;
        private String dotType;
        private String saleType;
        private int deleteFlag;
        private String description;
        private String terraceType;

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public int getProductSkuId() {
            return productSkuId;
        }

        public void setProductSkuId(int productSkuId) {
            this.productSkuId = productSkuId;
        }

        public String getProductSkuName() {
            return productSkuName;
        }

        public void setProductSkuName(String productSkuName) {
            this.productSkuName = productSkuName;
        }

        public int getProductSpuId() {
            return productSpuId;
        }

        public void setProductSpuId(int productSpuId) {
            this.productSpuId = productSpuId;
        }

        public int getAreaLevel1() {
            return areaLevel1;
        }

        public void setAreaLevel1(int areaLevel1) {
            this.areaLevel1 = areaLevel1;
        }

        public int getAreaLevel2() {
            return areaLevel2;
        }

        public void setAreaLevel2(int areaLevel2) {
            this.areaLevel2 = areaLevel2;
        }

        public int getAreaLevel3() {
            return areaLevel3;
        }

        public void setAreaLevel3(int areaLevel3) {
            this.areaLevel3 = areaLevel3;
        }

        public int getRedbagMostdedutionMoney() {
            return redbagMostdedutionMoney;
        }

        public void setRedbagMostdedutionMoney(int redbagMostdedutionMoney) {
            this.redbagMostdedutionMoney = redbagMostdedutionMoney;
        }

        public int getCouponMostdedutionMoney() {
            return couponMostdedutionMoney;
        }

        public void setCouponMostdedutionMoney(int couponMostdedutionMoney) {
            this.couponMostdedutionMoney = couponMostdedutionMoney;
        }

        public int getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(int marketPrice) {
            this.marketPrice = marketPrice;
        }

        public int getSalesPrice() {
            return salesPrice;
        }

        public void setSalesPrice(int salesPrice) {
            this.salesPrice = salesPrice;
        }

        public int getStorePrice() {
            return storePrice;
        }

        public void setStorePrice(int storePrice) {
            this.storePrice = storePrice;
        }

        public int getCompanyPrice() {
            return companyPrice;
        }

        public void setCompanyPrice(int companyPrice) {
            this.companyPrice = companyPrice;
        }

        public String getProductAttribute() {
            return productAttribute;
        }

        public void setProductAttribute(String productAttribute) {
            this.productAttribute = productAttribute;
        }

        public int getBargaining() {
            return bargaining;
        }

        public void setBargaining(int bargaining) {
            this.bargaining = bargaining;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public int getIsContainService() {
            return isContainService;
        }

        public void setIsContainService(int isContainService) {
            this.isContainService = isContainService;
        }

        public int getIsPutSale() {
            return isPutSale;
        }

        public void setIsPutSale(int isPutSale) {
            this.isPutSale = isPutSale;
        }

        public String getDotType() {
            return dotType;
        }

        public void setDotType(String dotType) {
            this.dotType = dotType;
        }

        public String getSaleType() {
            return saleType;
        }

        public void setSaleType(String saleType) {
            this.saleType = saleType;
        }

        public int getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(int deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTerraceType() {
            return terraceType;
        }

        public void setTerraceType(String terraceType) {
            this.terraceType = terraceType;
        }
    }
}
