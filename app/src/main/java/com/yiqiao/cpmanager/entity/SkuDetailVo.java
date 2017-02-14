package com.yiqiao.cpmanager.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import chihane.jdaddressselector.model.Province;

/**
 * Created by Xu on 2016/12/30.
 */

public class SkuDetailVo implements Serializable{

    /**
     * skuInfoVo : {"version":0,"createTime":1483081552000,"productSkuId":212,"productSkuName":"SZL社保代理SKU","productSpuId":25,"areaLevel1":130000,"areaLevel2":130800,"areaLevel3":130801,"redbagMostdedutionMoney":100,"marketPrice":900,"salesPrice":800,"storePrice":880,"companyPrice":850,"productAttribute":"21","bargaining":1,"integral":20,"isContainService":0,"isPutSale":2,"serviceDetails":"<p>社保代理<\/p>","dotType":"0,1","saleType":"0,1","deleteFlag":0,"description":"社保代理","keyWord":"社保代理","terraceType":"0,1,2","groupCode":"2016123016844","desc":"社保代理","businessType":151,"attribbuteInfoVoList":[{"id":13,"version":0,"name":"代理周期","sort":0,"checkedItem":21,"attributeValuePoList":[{"id":21,"version":0,"productAttrId":13,"attrValueName":"1个月","sort":1}]}]}
     * listSku : [{"areaLevel1":130000,"areaLevel2":130800,"areaLevel3":130801,"bargaining":1,"businessType":0,"clickNum":0,"companyPrice":850,"couponMostdedutionMoney":0,"createTime":null,"deleteFlag":0,"desc":"","description":"社保代理","dotType":"0,1","groupCode":"","id":0,"integral":20,"isContainService":0,"isPutSale":2,"keyWord":"","marketPrice":900,"modifyUser":"","productAttribute":"21","productSkuId":212,"productSkuName":"SZL社保代理SKU","productSpuId":25,"publishUser":"","redbagMostdedutionMoney":100,"saleType":"0,1","salesPrice":800,"serviceArrVal":"","serviceDetails":"","serviceStyle":0,"skuName":"","storePrice":880,"terraceType":"0,1,2","updateTime":null,"version":0}]
     * areaLists : [{"levelarea3":[{"areaCode":"130801","createTime":{"date":20,"day":3,"hours":15,"minutes":55,"month":3,"seconds":45,"time":1461138945000,"timezoneOffset":-480,"year":116},"fullName":"河北省承德市市辖区","id":130801,"isLeaf":1,"name":"市辖区","path":",130000,130800,","pid":130800,"sort":0,"updateTime":{"date":20,"day":3,"hours":15,"minutes":55,"month":3,"seconds":45,"time":1461138945000,"timezoneOffset":-480,"year":116},"version":0}],"levelarea2":[{"areaCode":"130800","createTime":{"date":20,"day":3,"hours":15,"minutes":55,"month":3,"seconds":45,"time":1461138945000,"timezoneOffset":-480,"year":116},"fullName":"河北省承德市","id":130800,"isLeaf":0,"name":"承德市","path":",130000,","pid":130000,"sort":0,"updateTime":{"date":20,"day":3,"hours":15,"minutes":55,"month":3,"seconds":45,"time":1461138945000,"timezoneOffset":-480,"year":116},"version":0}],"levelarea1":[{"areaCode":"130000","createTime":{"date":20,"day":3,"hours":15,"minutes":55,"month":3,"seconds":45,"time":1461138945000,"timezoneOffset":-480,"year":116},"fullName":"河北省","id":130000,"isLeaf":0,"name":"河北省","path":",","pid":0,"sort":0,"updateTime":{"date":20,"day":3,"hours":15,"minutes":55,"month":3,"seconds":45,"time":1461138945000,"timezoneOffset":-480,"year":116},"version":0}]}]
     * allListSku : [{"areaLevel1":130000,"areaLevel2":130800,"areaLevel3":130801,"bargaining":1,"businessType":0,"clickNum":0,"companyPrice":850,"couponMostdedutionMoney":0,"createTime":null,"deleteFlag":0,"desc":"","description":"社保代理","dotType":"0,1","groupCode":"","id":0,"integral":20,"isContainService":0,"isPutSale":2,"keyWord":"","marketPrice":900,"modifyUser":"","productAttribute":"21","productSkuId":212,"productSkuName":"SZL社保代理SKU","productSpuId":25,"publishUser":"","redbagMostdedutionMoney":100,"saleType":"0,1","salesPrice":800,"serviceArrVal":"","serviceDetails":"","serviceStyle":0,"skuName":"","storePrice":880,"terraceType":"0,1,2","updateTime":null,"version":0}]
     * spuPo : {"version":0,"productSpuId":25,"productName":"SZL社保开户SPU","productLabel":"质量保证,百年老店","productType1Id":26,"productType2Id":45,"productType3Id":46,"businessType":151,"logoImage":"cpsupload/pic/20161230150100256318.jpg","serviceDescrible":"<p style=\"\">社保开户<br/><\/p>","isPutSale":2,"sort":1,"unit":"元/件","maxQuantity":10,"minQuantity":1,"remark":"社保开户","deleteFlag":0,"createDate":1483081363000}
     * serviceProducts : []
     * giveaway : []
     */

    private SkuInfoVoBean skuInfoVo;
    private ArrayList<SkuInfoVoBean> listSku;
    private List<SkuInfoVoBean> allListSku;
    private SpuPoBean spuPo;
    private List<ServiceProductsBean> serviceProducts;
    private List<GiveawayBean> giveaway;
    private HuodongBean huodong;
    private String  ossImagePrefix="http://imagescs.oss-cn-beijing.aliyuncs.com/";

    public String getOssImagePrefix() {
        return ossImagePrefix;
    }

    public void setOssImagePrefix(String ossImagePrefix) {
        this.ossImagePrefix = ossImagePrefix;
    }

    public SkuInfoVoBean getSkuInfoVo() {
        return skuInfoVo;
    }

    public void setSkuInfoVo(SkuInfoVoBean skuInfoVo) {
        this.skuInfoVo = skuInfoVo;
    }

    public ArrayList<SkuInfoVoBean> getListSku() {
        return listSku;
    }

    public void setListSku(ArrayList<SkuInfoVoBean> listSku) {
        this.listSku = listSku;
    }
    public List<SkuInfoVoBean> getAllListSku() {
        return allListSku;
    }


    public void setAllListSku(List<SkuInfoVoBean> allListSku) {
        this.allListSku = allListSku;
    }

    private ArrayList<AreaListsBean> areaLists;

    public void setAreaLists(ArrayList<AreaListsBean> areaLists) {
        this.areaLists = areaLists;
    }

    public ArrayList<AreaListsBean> getAreaLists() {
        return areaLists;
    }

    public static class AreaListsBean implements Province {
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
        private String pid;
        private String sort;
        private String isLeaf;
        private long updateTime;
        private String id;
        private long createTime;
        private ArrayList<AreaListsBean.NodesBeanX> nodes;

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

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getIsLeaf() {
            return isLeaf;
        }

        public void setIsLeaf(String isLeaf) {
            this.isLeaf = isLeaf;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public ArrayList<AreaListsBean.NodesBeanX> getNodes() {
            return nodes;
        }

        public void setNodes(ArrayList<SkuDetailVo.AreaListsBean.NodesBeanX> nodes) {
            this.nodes = nodes;
        }


        public static class NodesBeanX implements Province {
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
            private String pid;
            private String sort;
            private String isLeaf;
            private long updateTime;
            private String id;
            private long createTime;
            private ArrayList<SkuDetailVo.AreaListsBean.NodesBeanX.NodesBean> nodes;

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

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getIsLeaf() {
                return isLeaf;
            }

            public void setIsLeaf(String isLeaf) {
                this.isLeaf = isLeaf;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public ArrayList<NodesBean> getNodes() {
                return nodes;
            }

            public void setNodes(ArrayList<NodesBean> nodes) {
                this.nodes = nodes;
            }


            public static class NodesBean  implements Province {
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
                private String pid;
                private String sort;
                private String isLeaf;
                private long updateTime;
                private String id;
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

                public String getPid() {
                    return pid;
                }

                public void setPid(String pid) {
                    this.pid = pid;
                }

                public String getSort() {
                    return sort;
                }

                public void setSort(String sort) {
                    this.sort = sort;
                }

                public String getIsLeaf() {
                    return isLeaf;
                }

                public void setIsLeaf(String isLeaf) {
                    this.isLeaf = isLeaf;
                }

                public long getUpdateTime() {
                    return updateTime;
                }

                public void setUpdateTime(long updateTime) {
                    this.updateTime = updateTime;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
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
    public SpuPoBean getSpuPo() {
        return spuPo;
    }

    public void setSpuPo(SpuPoBean spuPo) {
        this.spuPo = spuPo;
    }

    public List<ServiceProductsBean> getServiceProducts() {
        return serviceProducts;
    }

    public void setServiceProducts(List<ServiceProductsBean> serviceProducts) {
        this.serviceProducts = serviceProducts;
    }
    public static class ServiceProductsBean{
        private String id;
        private String name;
        private String serviceSalesPrice;
        private String isMust;
        private boolean isSelected;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getServiceSalesPrice() {
            return serviceSalesPrice;
        }

        public void setServiceSalesPrice(String serviceSalesPrice) {
            this.serviceSalesPrice = serviceSalesPrice;
        }

        public String getIsMust() {
            return isMust;
        }

        public void setIsMust(String isMust) {
            this.isMust = isMust;
        }
    }

    public SkuDetailVo.HuodongBean getHuodong() {
        return huodong;
    }

    public void setHuodong(SkuDetailVo.HuodongBean huodong) {
        this.huodong = huodong;
    }
    public static class HuodongBean {
        /**
         * redPacket : {"id":2,"version":0,"redName":"测试红包","redNum":1,"redAmount":2,"startTime":1481446071000,"endTime":1482569275000,"redNo":"23232332","deleteFlag":0,"issueStatus":1,"goods":0}
         * listAllActivity : []
         * listCoupon : [{"id":394,"version":0,"createTime":1481697977000,"name":"15元优惠券","amount":15,"expiryDateType":0,"startDate":1481558400000,"endDate":1483113600000,"expiryDateNum":0,"num":25,"sendedNum":25,"shopType":"0","goods":0,"zscqGoods":0,"conditions":8,"enabled":1,"remark":"描述15","exchange":0,"point":0,"channels":2,"code":"201612148200333","deleteFlag":0,"issueStatus":1,"useRule":2,"updateTime":1481698023000}]
         */

        private SkuDetailVo.HuodongBean.RedPacketBean redPacket;
        private List<?> listAllActivity;
        private List<SkuDetailVo.HuodongBean.ListCouponBean> listCoupon;

        public SkuDetailVo.HuodongBean.RedPacketBean getRedPacket() {
            return redPacket;
        }

        public void setRedPacket(SkuDetailVo.HuodongBean.RedPacketBean redPacket) {
            this.redPacket = redPacket;
        }

        public List<?> getListAllActivity() {
            return listAllActivity;
        }

        public void setListAllActivity(List<?> listAllActivity) {
            this.listAllActivity = listAllActivity;
        }

        public List<SkuDetailVo.HuodongBean.ListCouponBean> getListCoupon() {
            return listCoupon;
        }

        public void setListCoupon(List<SkuDetailVo.HuodongBean.ListCouponBean> listCoupon) {
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

            private String id;
            private String version;
            private String redName;
            private String redNum;
            private String redAmount;
            private long startTime;
            private long endTime;
            private String redNo;
            private String deleteFlag;
            private String issueStatus;
            private String goods;

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

            public String getRedName() {
                return redName;
            }

            public void setRedName(String redName) {
                this.redName = redName;
            }

            public String getRedNum() {
                return redNum;
            }

            public void setRedNum(String redNum) {
                this.redNum = redNum;
            }

            public String getRedAmount() {
                return redAmount;
            }

            public void setRedAmount(String redAmount) {
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

            public String getDeleteFlag() {
                return deleteFlag;
            }

            public void setDeleteFlag(String deleteFlag) {
                this.deleteFlag = deleteFlag;
            }

            public String getIssueStatus() {
                return issueStatus;
            }

            public void setIssueStatus(String issueStatus) {
                this.issueStatus = issueStatus;
            }

            public String getGoods() {
                return goods;
            }

            public void setGoods(String goods) {
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
             * poString : 0
             * channels : 2
             * code : 201612148200333
             * deleteFlag : 0
             * issueStatus : 1
             * useRule : 2
             * updateTime : 1481698023000
             */

            private String id;
            private String version;
            private long createTime;
            private String name;
            private String amount;
            private String expiryDateType;
            private long startDate;
            private long endDate;
            private String expiryDateNum;
            private String num;
            private String sendedNum;
            private String shopType;
            private String goods;
            private String zscqGoods;
            private String conditions;
            private String enabled;
            private String remark;
            private String exchange;
            private String point;
            private String channels;
            private String code;
            private String deleteFlag;
            private String issueStatus;
            private String useRule;
            private long updateTime;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getExpiryDateType() {
                return expiryDateType;
            }

            public void setExpiryDateType(String expiryDateType) {
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

            public String getExpiryDateNum() {
                return expiryDateNum;
            }

            public void setExpiryDateNum(String expiryDateNum) {
                this.expiryDateNum = expiryDateNum;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getSendedNum() {
                return sendedNum;
            }

            public void setSendedNum(String sendedNum) {
                this.sendedNum = sendedNum;
            }

            public String getShopType() {
                return shopType;
            }

            public void setShopType(String shopType) {
                this.shopType = shopType;
            }

            public String getGoods() {
                return goods;
            }

            public void setGoods(String goods) {
                this.goods = goods;
            }

            public String getZscqGoods() {
                return zscqGoods;
            }

            public void setZscqGoods(String zscqGoods) {
                this.zscqGoods = zscqGoods;
            }

            public String getConditions() {
                return conditions;
            }

            public void setConditions(String conditions) {
                this.conditions = conditions;
            }

            public String getEnabled() {
                return enabled;
            }

            public void setEnabled(String enabled) {
                this.enabled = enabled;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getExchange() {
                return exchange;
            }

            public void setExchange(String exchange) {
                this.exchange = exchange;
            }

            public String getPoint() {
                return point;
            }

            public void setPoint(String point) {
                this.point = point;
            }

            public String getChannels() {
                return channels;
            }

            public void setChannels(String channels) {
                this.channels = channels;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getDeleteFlag() {
                return deleteFlag;
            }

            public void setDeleteFlag(String deleteFlag) {
                this.deleteFlag = deleteFlag;
            }

            public String getIssueStatus() {
                return issueStatus;
            }

            public void setIssueStatus(String issueStatus) {
                this.issueStatus = issueStatus;
            }

            public String getUseRule() {
                return useRule;
            }

            public void setUseRule(String useRule) {
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
    public List<GiveawayBean> getGiveaway() {
        return giveaway;
    }

    public void setGiveaway(List<GiveawayBean> giveaway) {
        this.giveaway = giveaway;
    }
    public static class GiveawayBean{
        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class SkuInfoVoBean {
        /**
         * version : 0
         * createTime : 1483081552000
         * productSkuId : 212
         * productSkuName : SZL社保代理SKU
         * productSpuId : 25
         * areaLevel1 : 130000
         * areaLevel2 : 130800
         * areaLevel3 : 130801
         * redbagMostdedutionMoney : 100
         * marketPrice : 900
         * salesPrice : 800
         * storePrice : 880
         * companyPrice : 850
         * productAttribute : 21
         * bargaining : 1
         * integral : 20
         * isContainService : 0
         * isPutSale : 2
         * serviceDetails : <p>社保代理</p>
         * dotType : 0,1
         * saleType : 0,1
         * deleteFlag : 0
         * description : 社保代理
         * keyWord : 社保代理
         * terraceType : 0,1,2
         * groupCode : 2016123016844
         * desc : 社保代理
         * businessType : 151
         * attribbuteInfoVoList : [{"id":13,"version":0,"name":"代理周期","sort":0,"checkedItem":21,"attributeValuePoList":[{"id":21,"version":0,"productAttrId":13,"attrValueName":"1个月","sort":1}]}]
         */

        private String version;
        private long createTime;
        private String productSkuId;
        private String productSkuName;
        private String productSpuId;
        private String areaLevel1;
        private String areaLevel2;
        private String areaLevel3;
        private String redbagMostdedutionMoney;
        private String marketPrice;
        private String salesPrice;
        private String storePrice;
        private String companyPrice;
        private String productAttribute;
        private String bargaining;
        private String integral;
        private String isContainService;
        private String isPutSale;
        private String serviceDetails;
        private String dotType;
        private String saleType;
        private String deleteFlag;
        private String description;
        private String keyWord;
        private String terraceType;
        private String groupCode;
        private String desc;
        private String businessType;
        private List<AttribbuteInfoVoListBean> attribbuteInfoVoList;

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

        public String getRedbagMostdedutionMoney() {
            return redbagMostdedutionMoney;
        }

        public void setRedbagMostdedutionMoney(String redbagMostdedutionMoney) {
            this.redbagMostdedutionMoney = redbagMostdedutionMoney;
        }

        public String getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(String marketPrice) {
            this.marketPrice = marketPrice;
        }

        public String getSalesPrice() {
            return salesPrice;
        }

        public void setSalesPrice(String salesPrice) {
            this.salesPrice = salesPrice;
        }

        public String getStorePrice() {
            return storePrice;
        }

        public void setStorePrice(String storePrice) {
            this.storePrice = storePrice;
        }

        public String getCompanyPrice() {
            return companyPrice;
        }

        public void setCompanyPrice(String companyPrice) {
            this.companyPrice = companyPrice;
        }

        public String getProductAttribute() {
            return productAttribute;
        }

        public void setProductAttribute(String productAttribute) {
            this.productAttribute = productAttribute;
        }

        public String getBargaining() {
            return bargaining;
        }

        public void setBargaining(String bargaining) {
            this.bargaining = bargaining;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getIsContainService() {
            return isContainService;
        }

        public void setIsContainService(String isContainService) {
            this.isContainService = isContainService;
        }

        public String getIsPutSale() {
            return isPutSale;
        }

        public void setIsPutSale(String isPutSale) {
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

        public String getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(String deleteFlag) {
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

        public String getBusinessType() {
            return businessType;
        }

        public void setBusinessType(String businessType) {
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
             * id : 13
             * version : 0
             * name : 代理周期
             * sort : 0
             * checkedItem : 21
             * attributeValuePoList : [{"id":21,"version":0,"productAttrId":13,"attrValueName":"1个月","sort":1}]
             */

            private String id;
            private String version;
            private String name;
            private String sort;
            private String checkedItem;
            private List<AttributeValuePoListBean> attributeValuePoList;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getCheckedItem() {
                return checkedItem;
            }

            public void setCheckedItem(String checkedItem) {
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
                 * id : 21
                 * version : 0
                 * productAttrId : 13
                 * attrValueName : 1个月
                 * sort : 1
                 */

                private String id;
                private String version;
                private String productAttrId;
                private String attrValueName;
                private String sort;

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

                public String getProductAttrId() {
                    return productAttrId;
                }

                public void setProductAttrId(String productAttrId) {
                    this.productAttrId = productAttrId;
                }

                public String getAttrValueName() {
                    return attrValueName;
                }

                public void setAttrValueName(String attrValueName) {
                    this.attrValueName = attrValueName;
                }

                public String getSort() {
                    return sort;
                }

                public void setSort(String sort) {
                    this.sort = sort;
                }
            }
        }
    }

    public static class SpuPoBean {
        /**
         * version : 0
         * productSpuId : 25
         * productName : SZL社保开户SPU
         * productLabel : 质量保证,百年老店
         * productType1Id : 26
         * productType2Id : 45
         * productType3Id : 46
         * businessType : 151
         * logoImage : cpsupload/pic/20161230150100256318.jpg
         * serviceDescrible : <p style="">社保开户<br/></p>
         * isPutSale : 2
         * sort : 1
         * unit : 元/件
         * maxQuantity : 10
         * minQuantity : 1
         * remark : 社保开户
         * deleteFlag : 0
         * createDate : 1483081363000
         */

        private String version;
        private String productSpuId;
        private String productName;
        private String productLabel;
        private String productType1Id;
        private String productType2Id;
        private String productType3Id;
        private String businessType;
        private String logoImage;
        private String serviceDescrible;
        private String isPutSale;
        private String sort;
        private String unit;
        private String maxQuantity;
        private String minQuantity;
        private String remark;
        private String deleteFlag;
        private long createDate;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getProductSpuId() {
            return productSpuId;
        }

        public void setProductSpuId(String productSpuId) {
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

        public String getProductType1Id() {
            return productType1Id;
        }

        public void setProductType1Id(String productType1Id) {
            this.productType1Id = productType1Id;
        }

        public String getProductType2Id() {
            return productType2Id;
        }

        public void setProductType2Id(String productType2Id) {
            this.productType2Id = productType2Id;
        }

        public String getProductType3Id() {
            return productType3Id;
        }

        public void setProductType3Id(String productType3Id) {
            this.productType3Id = productType3Id;
        }

        public String getBusinessType() {
            return businessType;
        }

        public void setBusinessType(String businessType) {
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

        public String getIsPutSale() {
            return isPutSale;
        }

        public void setIsPutSale(String isPutSale) {
            this.isPutSale = isPutSale;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getMaxQuantity() {
            return maxQuantity;
        }

        public void setMaxQuantity(String maxQuantity) {
            this.maxQuantity = maxQuantity;
        }

        public String getMinQuantity() {
            return minQuantity;
        }

        public void setMinQuantity(String minQuantity) {
            this.minQuantity = minQuantity;
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

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }
    }

}
