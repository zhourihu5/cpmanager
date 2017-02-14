package com.yiqiao.cpmanager.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.StringUtils;
import com.google.gson.Gson;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.app.Constants;
import com.yiqiao.cpmanager.base.BaseFragment;
import com.yiqiao.cpmanager.entity.CreateOrderRequestVo;
import com.yiqiao.cpmanager.entity.CreateOrderVo;
import com.yiqiao.cpmanager.entity.GoodsDetailRequesstVo;
import com.yiqiao.cpmanager.entity.HttpResult;
import com.yiqiao.cpmanager.entity.SetmealVo;
import com.yiqiao.cpmanager.entity.SkuDetailVo;
import com.yiqiao.cpmanager.http.CpMgrApiService;
import com.yiqiao.cpmanager.http.RetrofitHelper;
import com.yiqiao.cpmanager.http.exception.ApiException;
import com.yiqiao.cpmanager.subscribers.RxSubscriber;
import com.yiqiao.cpmanager.transformer.PageTransformer;
import com.yiqiao.cpmanager.ui.activity.ConfirmOrderActivity;
import com.yiqiao.cpmanager.ui.activity.GoodsDetailActivity;
import com.yiqiao.cpmanager.ui.activity.MainActivity;
import com.yiqiao.cpmanager.ui.activity.OrderDetailActivity;
import com.yiqiao.cpmanager.ui.adapter.GoodsDetailApendAdapter;
import com.yiqiao.cpmanager.ui.adapter.GoodsDetailAttrAdapter;
import com.yiqiao.cpmanager.ui.adapter.GoodsDetailDonateAdapter;
import com.yiqiao.cpmanager.ui.adapter.GoodsDetailSetmealAdapter;
import com.yiqiao.cpmanager.util.LogUtils;
import com.yiqiao.cpmanager.util.SharedPreferenceUtil;
import com.yiqiao.cpmanager.util.SystemUtil;
import com.yiqiao.cpmanager.util.ToastUtil;
import com.yiqiao.cpmanager.widget.FullyHeightRecycleview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import chihane.jdaddressselector.AddressProvider;
import chihane.jdaddressselector.BottomDialog;
import chihane.jdaddressselector.OnAddressSelectedListener;
import chihane.jdaddressselector.model.Province;
import cn.xiaoneng.coreapi.ChatParamsBody;
import cn.xiaoneng.uiapi.Ntalker;
import cn.xiaoneng.uiapi.XNSDKListener;
import cn.xiaoneng.utils.CoreData;
import ezy.ui.layout.LoadingLayout;
import rx.Subscription;

/**
 * Created by codeest on 2016/8/11.
 * 商品详情页
 */
public class GoodsDetailFragment extends BaseFragment implements XNSDKListener {

    GoodsDetailApendAdapter apendAdapter;
    GoodsDetailDonateAdapter donateAdapter;
    GoodsDetailAttrAdapter goodsDetailAttrAdapter;
//    OptionsPickerView pvOptions;

    //    BottomSheetDialog bottomSheetDialog;//choose address
    BottomDialog dialog;
    @BindView(R.id.tvLuckyDown)
    TextView tvLuckyDown;
    @BindView(R.id.tvDesc)
    TextView tvDesc;
    @BindView(R.id.rvDonate)
    FullyHeightRecycleview rvDonate;
    @BindView(R.id.rvSetmeal)
    FullyHeightRecycleview rvSetmeal;
    @BindView(R.id.llCity)
    LinearLayout llCity;
    @BindView(R.id.rvAttr)
    FullyHeightRecycleview rvAttr;
    @BindView(R.id.rvAppend)
    FullyHeightRecycleview rvAppend;
    @BindView(R.id.ivMinus)
    ImageView ivMinus;
    @BindView(R.id.llMinus)
    LinearLayout llMinus;
    @BindView(R.id.tvNum)
    TextView tvNum;
    @BindView(R.id.llAdd)
    LinearLayout llAdd;
    @BindView(R.id.llOnline)
    LinearLayout llOnline;
    @BindView(R.id.llPhone)
    LinearLayout llPhone;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.llPriceLeft)
    LinearLayout llPriceLeft;
    @BindView(R.id.tvPay)
    TextView tvPay;
    @BindView(R.id.tvLuckyMoney)
    TextView tvLuckyMoney;
    @BindView(R.id.tvCoupon)
    TextView tvCoupon;
    @BindView(R.id.tvCredit)
    TextView tvCredit;
    @BindView(R.id.tvCity)
    TextView tvCity;
    @BindView(R.id.llSetmeal)
    LinearLayout llSetmeal;
    @BindView(R.id.llApend)
    LinearLayout llApend;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvUnit)
    TextView tvUnit;
    @BindView(R.id.loading)
    LoadingLayout loading;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvTotalNum)
    TextView tvTotalNum;
    private String id;
    private String type;//1,商品，2，套餐
    private String cityId="";

    @Override
    protected int getLayoutId() {
        return R.layout.frag_goods_detail;
    }

    @Override
    protected void initEventAndData() {
        ivMinus.setEnabled(false);
        tvCity.setText(null);
        //选项选择器
//        pvOptions = new OptionsPickerView(mActivity);

        goodsDetailAttrAdapter = new GoodsDetailAttrAdapter(mActivity, new ArrayList<SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean>());
        rvAttr.setLayoutManager(new LinearLayoutManager(mActivity));
        rvAttr.setAdapter(goodsDetailAttrAdapter);
        loading.setRetryListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading.showLoading();
//                loadData();
                lazyLoadData();
            }
        });
    }

    @Override
    protected void lazyLoadData() {
        super.lazyLoadData();
        //todo test
//        type="2";
//        id="44";
        if (StringUtils.isEmpty(type)) {
            type = "1";
        }
        loading.showLoading();
        switch (type) {
            case "1"://商品

                loadData();
                break;
            default://套餐
                loadSetmealData();
                break;
        }

    }
    SkuDetailVo skuDetailVo;
    String price;
    private void loadData() {//商品详情接口 套餐详情单独

        LogUtils.e("loadData");
//        id="75";
        GoodsDetailRequesstVo goodsDetailRequesstVo = new GoodsDetailRequesstVo();
        goodsDetailRequesstVo.setSkuId(id);

        String sysCode = "111";
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String param = new Gson().toJson(goodsDetailRequesstVo);
        String sign = SystemUtil.getSign(sysCode, timeStamp, param);

        Subscription rxSubscription = RetrofitHelper.getCpMgrApiService()
                .getProductDetail(sysCode, timeStamp, param, sign)
                .compose(new PageTransformer<HttpResult<SkuDetailVo>>())
                .subscribe(new RxSubscriber<HttpResult<SkuDetailVo>>(GoodsDetailFragment.this) {
                    // 必须重写
                    @Override
                    public void onNext(HttpResult<SkuDetailVo> contentBeen) {
                        loading.showContent();
                        skuDetailVo = contentBeen.getData();
                        if(skuDetailVo==null){
                            loading.showError();
                            return;
                        }
                        SkuDetailVo.SpuPoBean spuPoBean = skuDetailVo.getSpuPo();
                        SkuDetailVo.SkuInfoVoBean skuInfoVoBean = skuDetailVo.getSkuInfoVo();

                        List<SkuDetailVo.GiveawayBean> giveawayBeanList = skuDetailVo.getGiveaway();
                        rvDonate.setLayoutManager(new LinearLayoutManager(mContext));
                        donateAdapter = new GoodsDetailDonateAdapter(mContext, giveawayBeanList);
                        rvDonate.setAdapter(donateAdapter);


                        String name = null;
                        price = null;
                        tvCredit.setVisibility(View.GONE);
                        if (skuInfoVoBean != null) {
                            cityId = skuInfoVoBean.getAreaLevel3();
                            if(StringUtils.isEmpty(cityId)){
                                cityId=skuInfoVoBean.getAreaLevel2();
                            }
                            if(StringUtils.isEmpty(cityId)){
                                cityId=skuInfoVoBean.getAreaLevel1();
                            }
                            name = skuInfoVoBean.getProductSkuName();
                            price = skuInfoVoBean.getSalesPrice();
                            startPageTitle = name;
                            tvDesc.setText(skuInfoVoBean.getDesc());
                            try {
                                if(Float.valueOf(skuInfoVoBean.getIntegral())>0){
                                    tvCredit.setVisibility(View.VISIBLE);
                                }else {
                                    tvCredit.setVisibility(View.GONE);
                                }
                            } catch (Exception e) {
                                tvCredit.setVisibility(View.VISIBLE);
                                e.printStackTrace();
                            }
                            tvCredit.setText(String.format("%s积分", skuInfoVoBean.getIntegral()));
//                            tvLuckyMoney.setText(String.format("%s元红包",skuInfoVoBean.get));
//                            tvCoupon.setText(String.format("%s元",skuInfoVoBean.get));
                            try {
                                if(Float.valueOf(skuInfoVoBean.getRedbagMostdedutionMoney())>0){
                                    tvLuckyDown.setText(String.format("使用优惠券可省%s元", skuInfoVoBean.getRedbagMostdedutionMoney()));
                                    tvLuckyDown.setVisibility(View.VISIBLE);
                                }else {
                                    tvLuckyDown.setVisibility(View.GONE);
                                }
                            } catch (Exception e) {
                                tvLuckyDown.setVisibility(View.GONE);
                                e.printStackTrace();
                            }

                            webView.getSettings().setDefaultTextEncodingName("utf-8");
                            webView.loadDataWithBaseURL(null, skuInfoVoBean.getServiceDetails(), "text/html", "utf-8", null);
                            goodsDetailAttrAdapter.clear();
                            goodsDetailAttrAdapter.addAll(skuInfoVoBean.getAttribbuteInfoVoList());
                            goodsDetailAttrAdapter.setSelecteListner(new GoodsDetailAttrAdapter.SelecteListner() {
                                @Override
                                public void select(String selectedIds) {
                                    LogUtils.e("selectedIds==" + selectedIds);
                                    selectSku(selectedIds);
                                }
                            });

                        }
                        if (spuPoBean != null) {
                            if (StringUtils.isEmpty(name)) {
                                name = spuPoBean.getProductName();
                            }
                        }
                        tvName.setText(name);
                        tvPrice.setText(price);
//                        String url = "http://www.cs.12366.com/h5/slb/buy/detail?skuId=" + id;
                        String url = "http://www.cs.12366.com/mobile/slb/buy/detail?skuId=" + id;
                        startPageUrl = url;
                        String title = skuInfoVoBean.getProductSkuName();
                        String content = skuInfoVoBean.getDesc();
                        String img = null;
                        if (spuPoBean != null) {
//                            img = String.format("%s%s", skuDetailVo.getOssImagePrefix(), spuPoBean.getLogoImage());
                            img=String.format("%s", spuPoBean.getLogoImage());
                            tvUnit.setText(spuPoBean.getUnit());
                        }

                        GoodsDetailActivity goodsDetailActivity = (GoodsDetailActivity) mActivity;
                        goodsDetailActivity.setShare(title, url, img, content);


                        List<SkuDetailVo.ServiceProductsBean> serviceProductsBeanList = skuDetailVo.getServiceProducts();
                        rvAppend.setLayoutManager(new LinearLayoutManager(mContext));
                        apendAdapter = new GoodsDetailApendAdapter(mContext, serviceProductsBeanList);
                        apendAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                final SkuDetailVo.ServiceProductsBean data = apendAdapter.getItem(position);
                                if (!"1".equals(data.getIsMust())) {
                                    boolean selected = data.isSelected();
                                    data.setSelected(!selected);
                                    apendAdapter.notifyDataSetChanged();
                                    calculatePrice();

                                }
                            }
                        });
                        rvAppend.setAdapter(apendAdapter);
                        if (apendAdapter.getCount() <= 0) {
                            llApend.setVisibility(View.GONE);
                        }else {
                            llApend.setVisibility(View.VISIBLE);
                        }
                        calculatePrice();
                        llSetmeal.setVisibility(View.GONE);
                        tvLuckyMoney.setVisibility(View.GONE);
                        tvCoupon.setVisibility(View.GONE);
                        SkuDetailVo.HuodongBean huodongBean = skuDetailVo.getHuodong();
                        if (huodongBean != null) {
                            SkuDetailVo.HuodongBean.RedPacketBean redPacketBean = huodongBean.getRedPacket();
                            if (redPacketBean != null) {
//                               tvLuckyMoney.setText(String.format("%s%s元",redPacketBean.getRedName(),redPacketBean.getRedAmount()));
                                tvLuckyMoney.setText(String.format("%s元", redPacketBean.getRedAmount()));
                                try {
                                    if(Float.valueOf(redPacketBean.getRedAmount())>0){
                                        tvLuckyMoney.setVisibility(View.VISIBLE);
                                    }else {
                                        tvLuckyMoney.setVisibility(View.GONE);
                                    }
                                } catch (Exception e) {
                                    tvLuckyMoney.setVisibility(View.VISIBLE);
                                    e.printStackTrace();
                                }
                            }
                            List<SkuDetailVo.HuodongBean.ListCouponBean> listCouponBeen = huodongBean.getListCoupon();
                            if (listCouponBeen != null && listCouponBeen.size() > 0) {
                                SkuDetailVo.HuodongBean.ListCouponBean listCouponBean = listCouponBeen.get(0);
                                tvCoupon.setText(String.format("%s元", listCouponBean.getAmount()));
                                try {
                                    if(Float.valueOf(listCouponBean.getAmount())>0){
                                        tvCoupon.setVisibility(View.VISIBLE);
                                    }else {
                                        tvCoupon.setVisibility(View.GONE);
                                    }
                                } catch (Exception e) {
                                    tvCoupon.setVisibility(View.VISIBLE);
                                    e.printStackTrace();
                                }
                            }

                        }
                        try {
                            final ArrayList<SkuDetailVo.AreaListsBean> listsBeanList = skuDetailVo.getAreaLists();
                            if (listsBeanList != null && listsBeanList.size() > 0) {

                                llCity.setVisibility(View.VISIBLE);
                                dialog= new BottomDialog(mActivity);
                                dialog.setAddressProvider(new AddressProvider<SkuDetailVo.AreaListsBean
                                        ,SkuDetailVo.AreaListsBean.NodesBeanX
                                        ,SkuDetailVo.AreaListsBean.NodesBeanX.NodesBean>() {
                                    @Override
                                    public void provideProvinces(AddressReceiver<SkuDetailVo.AreaListsBean> addressReceiver) {
                                        addressReceiver.send(listsBeanList);
                                    }

                                    @Override
                                    public void provideCitiesWith(SkuDetailVo.AreaListsBean areaListsBean, AddressReceiver<SkuDetailVo.AreaListsBean.NodesBeanX> addressReceiver) {

                                        addressReceiver.send(areaListsBean.getNodes());
                                    }

                                    @Override
                                    public void provideCountiesWith(SkuDetailVo.AreaListsBean.NodesBeanX nodesBeanX, AddressReceiver<SkuDetailVo.AreaListsBean.NodesBeanX.NodesBean> addressReceiver) {
                                        addressReceiver.send(nodesBeanX.getNodes());
                                    }

                                    @Override
                                    public void provideStreetsWith(SkuDetailVo.AreaListsBean.NodesBeanX.NodesBean nodesBean, AddressReceiver<Province> addressReceiver) {
                                        addressReceiver.send(new ArrayList<Province>());
                                    }
                                });
                                dialog.setOnAddressSelectedListener(new OnAddressSelectedListener() {
                                    @Override
                                    public void onAddressSelected(Province province0, Province province1, Province province2, Province province3) {
                                        dialog.dismiss();
                                        String previousCityId = cityId;
                                        String province="";
                                        String city="";
                                        String zone="";
                                        SkuDetailVo.AreaListsBean areaListsBean= (SkuDetailVo.AreaListsBean) province0;
                                        SkuDetailVo.AreaListsBean.NodesBeanX nodesBeanX= (SkuDetailVo.AreaListsBean.NodesBeanX) province1;
                                        SkuDetailVo.AreaListsBean.NodesBeanX.NodesBean nodesBean= (SkuDetailVo.AreaListsBean.NodesBeanX.NodesBean) province2;

                                        if(province1==null){

                                            cityId = areaListsBean.getId();
                                            province = areaListsBean.getName();
//                                            city = listArrayList.get(options1).get(option2).getPickerViewText();
//                                            zone = listListList.get(options1).get(option2).get(options3).getPickerViewText();

                                            tvCity.setText(String.format("%s", province));
                                        }else if(province2==null){

                                            cityId =nodesBeanX.getId();
                                            province = areaListsBean.getName();
                                            city = nodesBeanX.getName();
//                                          zone = listListList.get(options1).get(option2).get(options3).getPickerViewText();

                                            tvCity.setText(String.format("%s-%s", province, city));
                                        }else {
                                            cityId = nodesBean.getId();
                                            province =areaListsBean.getName();
                                            city = nodesBeanX.getName();
                                            zone = nodesBean.getName();

                                            tvCity.setText(String.format("%s-%s-%s", province, city, zone));
                                        }

                                        if (cityId.equals(previousCityId)) {
                                            return;
                                        }
                                        selectSku(null);

                                    }
                                });

//                                boolean is1Levl=false;
//                                boolean is2Level=false;
//                                llCity.setVisibility(View.VISIBLE);
//                                final ArrayList<ArrayList<SkuDetailVo.AreaListsBean.NodesBeanX>> listArrayList = new ArrayList<>();
//                                final ArrayList<ArrayList<ArrayList<SkuDetailVo.AreaListsBean.NodesBeanX.NodesBean>>> listListList = new ArrayList<>();
//                                for (SkuDetailVo.AreaListsBean areaListsBean : listsBeanList) {
//                                    ArrayList<SkuDetailVo.AreaListsBean.NodesBeanX> nodesBeanXList = areaListsBean.getNodes();
//                                    listArrayList.add(nodesBeanXList);
//                                    if (nodesBeanXList != null) {
//                                        ArrayList<ArrayList<SkuDetailVo.AreaListsBean.NodesBeanX.NodesBean>> listList = new ArrayList<>();
//                                        for (SkuDetailVo.AreaListsBean.NodesBeanX nodesBeanX : nodesBeanXList) {
//                                            ArrayList<SkuDetailVo.AreaListsBean.NodesBeanX.NodesBean> list = nodesBeanX.getNodes();
//                                            if(list!=null){
//                                                listList.add(list);
//                                            }else {
//                                                is2Level=true;
//                                            }
//                                        }
//                                        listListList.add(listList);
//                                    }else {
//                                        is1Levl=true;
//                                    }
//                                }
                                //三级联动效果
//                                if(is1Levl){
//                                    pvOptions.setPicker(listsBeanList);
//                                }else if(is2Level){
//                                    pvOptions.setPicker(listsBeanList, listArrayList,  true);
//                                }else {
//                                    pvOptions.setPicker(listsBeanList, listArrayList, listListList, true);
//
//                                }
//                                pvOptions.setPicker(listsBeanList, listArrayList, listListList, true);
//                                //                            pvOptions.setTitle("选择城市");
//                                pvOptions.setCyclic(false, false, false);
//                                final boolean finalIs1Levl = is1Levl;
//                                final boolean finalIs2Level = is2Level;
//                                pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
//
//                                    @Override
//                                    public void onOptionsSelect(int options1, int option2, int options3) {
//                                        String previousCityId = cityId;
//                                        String province="";
//                                        String city="";
//                                        String zone="";
//
//                                        if(finalIs1Levl){
//                                            cityId = listsBeanList.get(options1).getId();
//                                            province = listsBeanList.get(options1).getPickerViewText();
////                                            city = listArrayList.get(options1).get(option2).getPickerViewText();
////                                            zone = listListList.get(options1).get(option2).get(options3).getPickerViewText();
//
//                                            tvCity.setText(String.format("%s", province));
//                                        }else if(finalIs2Level){
//                                            cityId = listArrayList.get(options1).get(option2).getId();
//                                            province = listsBeanList.get(options1).getPickerViewText();
//                                            city = listArrayList.get(options1).get(option2).getPickerViewText();
////                                          zone = listListList.get(options1).get(option2).get(options3).getPickerViewText();
//
//                                            tvCity.setText(String.format("%s-%s", province, city));
//                                        }else {
//                                            cityId = listListList.get(options1).get(option2).get(options3).getId();
//                                            province = listsBeanList.get(options1).getPickerViewText();
//                                            city = listArrayList.get(options1).get(option2).getPickerViewText();
//                                            zone = listListList.get(options1).get(option2).get(options3).getPickerViewText();
//
//                                            tvCity.setText(String.format("%s-%s-%s", province, city, zone));
//                                        }
//
//                                        if (cityId.equals(previousCityId)) {
//                                            return;
//                                        }
//                                        List<SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean> listBeen = new ArrayList<SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean>();
//                                        listBeen.addAll(goodsDetailAttrAdapter.getAllData());
//
//                                        Collections.sort(listBeen, new Comparator<SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean>() {
//                                            @Override
//                                            public int compare(SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean o1, SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean o2) {
//                                                return Integer.valueOf(o2.getId()) - Integer.valueOf(o1.getId());
//                                            }
//                                        });
//                                        //todo
//                                        StringBuilder sb = new StringBuilder();
//                                        for (SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean bean : listBeen) {
//                                            sb.append(bean.getCheckedItem()).append(",");
//                                        }
//
//                                        String selectedIds = "";
//                                        if (sb.length() > 0) {
//                                            selectedIds = sb.deleteCharAt(sb.lastIndexOf(",")).toString();
//                                        }
//                                        LogUtils.e("selectedIds==" + selectedIds);
//                                        List<SkuDetailVo.SkuInfoVoBean> list = skuDetailVo.getAllListSku();
//                                        String strCityId = cityId;
//                                        boolean found = false;
//                                        for (SkuDetailVo.SkuInfoVoBean skuInfoVoBean1 : list) {
//                                            if (StringUtils.isEmpty(selectedIds) && StringUtils.isEmpty(skuInfoVoBean1.getProductAttribute())
//                                                    && strCityId.equals(skuInfoVoBean1.getAreaLevel3())) {
//                                                id = skuInfoVoBean1.getProductSkuId();
//                                                loadData();
//                                                found = true;
//                                                break;
//                                            }
//                                            if (selectedIds.equals(skuInfoVoBean1.getProductAttribute())
//                                                    && strCityId.equals(skuInfoVoBean1.getAreaLevel3())) {
//                                                id = skuInfoVoBean1.getProductSkuId();
//                                                loadData();
//                                                found = true;
//                                                break;
//                                            }
//                                        }
//                                        if (!found) {//根据城市id和skuid查询
//
//                                        }
//
//                                    }
//                                });
                            } else {
                                llCity.setVisibility(View.GONE);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }


                    // 无需设置可以不用重写
                    // !!!!注意参数为ApiException 类型，要不要写在Throwable那个了
                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        skuDetailVo=null;
                        price=null;
                        isFirst = true;
                        loading.showError();

//                        if(adapter.getCount()<=0){
//                            recyclerView.showError();
//                        }else {
//                            recyclerView.showRecycler();
//                            recyclerView.setRefreshing(false);
//                            adapter.pauseMore();
//                        }
                    }

                    // 无需设置可以不用重写
                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }
                });
        addSubscrebe(rxSubscription);
    }

    String packageCode;
    private void loadSetmealData() {//商品详情接口 套餐详情单独

        LogUtils.e("loadSetmealData");
//        id="75";
        GoodsDetailRequesstVo goodsDetailRequesstVo = new GoodsDetailRequesstVo();
        goodsDetailRequesstVo.setPackageId(id);
        goodsDetailRequesstVo.setAreaId(cityId);
        goodsDetailRequesstVo.setPackageCode(packageCode);

        String sysCode = "111";
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String param = new Gson().toJson(goodsDetailRequesstVo);
        String sign = SystemUtil.getSign(sysCode, timeStamp, param);

        Subscription rxSubscription = RetrofitHelper.getCpMgrApiService()
                .getProductPackageDetail(sysCode, timeStamp, param, sign)
                .compose(new PageTransformer<HttpResult<SetmealVo>>())
                .subscribe(new RxSubscriber<HttpResult<SetmealVo>>(GoodsDetailFragment.this) {
                    // 必须重写
                    @Override
                    public void onNext(HttpResult<SetmealVo> contentBeen) {
                        loading.showContent();
                         SetmealVo  skuDetailVo = contentBeen.getData();
                        if(skuDetailVo==null){
                            loading.showError();
                            return;
                        }
                        final SetmealVo.PackagePoBean spuPoBean = skuDetailVo.getPackagePo();
                        SetmealVo.PackagePirceBean skuInfoVoBean = skuDetailVo.getPackagePirce();

//                        List<SkuDetailVo.GiveawayBean> giveawayBeanList = skuDetailVo.getGiveaway();
//                        rvDonate.setLayoutManager(new LinearLayoutManager(mContext));
//                        donateAdapter = new GoodsDetailDonateAdapter(mContext, giveawayBeanList);
//                        rvDonate.setAdapter(donateAdapter);

                        List<SetmealVo.ListSkuBean>listSkuBeanList=  skuDetailVo.getListSku();
                        GoodsDetailSetmealAdapter goodsDetailSetmealAdapter=new GoodsDetailSetmealAdapter(mActivity,listSkuBeanList);
                        rvSetmeal.setLayoutManager(new LinearLayoutManager(mActivity));
                        rvSetmeal.setAdapter(goodsDetailSetmealAdapter);
                        llSetmeal.setVisibility(View.VISIBLE);

                        String name = null;
                        price = null;
                        tvCredit.setVisibility(View.GONE);
                        if (skuInfoVoBean != null) {
                            cityId = skuInfoVoBean.getAreaLevel3();
                            if(StringUtils.isEmpty(cityId)){
                                cityId=skuInfoVoBean.getAreaLevel2();
                            }
                            if(StringUtils.isEmpty(cityId)){
                                cityId=skuInfoVoBean.getAreaLevel1();
                            }
                            name = spuPoBean.getPackageName();
                            price = skuInfoVoBean.getPrice();
                            startPageTitle = name;
                            tvDesc.setText(spuPoBean.getRemark());
                            try {
                                if(Float.valueOf(skuDetailVo.getAllIntegrial())>0){
                                    tvCredit.setVisibility(View.VISIBLE);
                                }else {
                                    tvCredit.setVisibility(View.GONE);
                                }
                            } catch (Exception e) {
                                tvCredit.setVisibility(View.VISIBLE);
                                e.printStackTrace();
                            }
                            tvCredit.setText(String.format("%s积分", skuDetailVo.getAllIntegrial()));
//                            tvLuckyMoney.setText(String.format("%s元红包",skuInfoVoBean.get));
//                            tvCoupon.setText(String.format("%s元",skuInfoVoBean.get));
//                            try {
//                                if(Float.valueOf(skuInfoVoBean.getRedbagMostdedutionMoney())>0){
//                                    tvLuckyDown.setText(String.format("使用优惠券可省%s元", skuInfoVoBean.getRedbagMostdedutionMoney()));
//                                    tvLuckyDown.setVisibility(View.VISIBLE);
//                                }else {
//                                    tvLuckyDown.setVisibility(View.GONE);
//                                }
//                            } catch (Exception e) {
//                                tvLuckyDown.setVisibility(View.GONE);
//                                e.printStackTrace();
//                            }
                            tvLuckyDown.setVisibility(View.GONE);

                            webView.getSettings().setDefaultTextEncodingName("utf-8");
                            webView.loadDataWithBaseURL(null, spuPoBean.getPackageServiceDetails(), "text/html", "utf-8", null);
                            goodsDetailAttrAdapter.clear();
//                            goodsDetailAttrAdapter.addAll(skuInfoVoBean.getAttribbuteInfoVoList());
//                            goodsDetailAttrAdapter.setSelecteListner(new GoodsDetailAttrAdapter.SelecteListner() {
//                                @Override
//                                public void select(String selectedIds) {
//                                    LogUtils.e("selectedIds==" + selectedIds);
//                                    selectSku(selectedIds);
//                                }
//                            });

                        }
                        if (spuPoBean != null) {
                            if (StringUtils.isEmpty(name)) {
                                name = spuPoBean.getPackageName();
                            }
                        }
                        tvName.setText(name);
                        tvPrice.setText(price);
//                        String url = "http://www.cs.12366.com/h5/slb/buy/detail?skuId=" + id;
                        String url = "http://www.cs.12366.com/mobile/slb/buy/detail?skuId=" + id;
                        startPageUrl = url;
                        String title = name;
                        String content = spuPoBean.getRemark();
                        String img = null;
                        if (spuPoBean != null) {
//                            img = String.format("%s%s", skuDetailVo.getOssImagePrefix(), spuPoBean.getLogoImage());
                            img=String.format("%s", spuPoBean.getImgUrl());
//                            tvUnit.setText(spuPoBean.getUnit());
                        }

                        GoodsDetailActivity goodsDetailActivity = (GoodsDetailActivity) mActivity;
                        goodsDetailActivity.setShare(title, url, img, content);


//                        List<SkuDetailVo.ServiceProductsBean> serviceProductsBeanList = skuDetailVo.getServiceProducts();
//                        rvAppend.setLayoutManager(new LinearLayoutManager(mContext));
//                        apendAdapter = new GoodsDetailApendAdapter(mContext, serviceProductsBeanList);
//                        apendAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(int position) {
//                                final SkuDetailVo.ServiceProductsBean data = apendAdapter.getItem(position);
//                                if (!"1".equals(data.getIsMust())) {
//                                    boolean selected = data.isSelected();
//                                    data.setSelected(!selected);
//                                    apendAdapter.notifyDataSetChanged();
//                                    calculatePrice();
//
//                                }
//                            }
//                        });
//                        rvAppend.setAdapter(apendAdapter);
//                        if (apendAdapter.getCount() <= 0) {
//                            llApend.setVisibility(View.GONE);
//                        }else {
//                            llApend.setVisibility(View.VISIBLE);
//                        }
                        llApend.setVisibility(View.GONE);

                        calculatePrice();
                        llSetmeal.setVisibility(View.GONE);
                        tvLuckyMoney.setVisibility(View.GONE);
                        tvCoupon.setVisibility(View.GONE);
//                        SkuDetailVo.HuodongBean huodongBean = skuDetailVo.getHuodong();
//                        if (huodongBean != null) {
//                            SkuDetailVo.HuodongBean.RedPacketBean redPacketBean = huodongBean.getRedPacket();
//                            if (redPacketBean != null) {
//                                tvLuckyMoney.setText(String.format("%s元", redPacketBean.getRedAmount()));
//                                try {
//                                    if(Float.valueOf(redPacketBean.getRedAmount())>0){
//                                        tvLuckyMoney.setVisibility(View.VISIBLE);
//                                    }else {
//                                        tvLuckyMoney.setVisibility(View.GONE);
//                                    }
//                                } catch (Exception e) {
//                                    tvLuckyMoney.setVisibility(View.VISIBLE);
//                                    e.printStackTrace();
//                                }
//                            }
//                            List<SkuDetailVo.HuodongBean.ListCouponBean> listCouponBeen = huodongBean.getListCoupon();
//                            if (listCouponBeen != null && listCouponBeen.size() > 0) {
//                                SkuDetailVo.HuodongBean.ListCouponBean listCouponBean = listCouponBeen.get(0);
//                                tvCoupon.setText(String.format("%s元", listCouponBean.getAmount()));
//                                try {
//                                    if(Float.valueOf(listCouponBean.getAmount())>0){
//                                        tvCoupon.setVisibility(View.VISIBLE);
//                                    }else {
//                                        tvCoupon.setVisibility(View.GONE);
//                                    }
//                                } catch (Exception e) {
//                                    tvCoupon.setVisibility(View.VISIBLE);
//                                    e.printStackTrace();
//                                }
//                            }
//
//                        }
                        tvLuckyMoney.setVisibility(View.GONE);
                        tvCoupon.setVisibility(View.GONE);

                        try {
                            final ArrayList<SkuDetailVo.AreaListsBean> listsBeanList = skuDetailVo.getAreaTree();
                            if (listsBeanList != null && listsBeanList.size() > 0) {

                                llCity.setVisibility(View.VISIBLE);
                                dialog= new BottomDialog(mActivity);
                                dialog.setAddressProvider(new AddressProvider<SkuDetailVo.AreaListsBean
                                        ,SkuDetailVo.AreaListsBean.NodesBeanX
                                        ,SkuDetailVo.AreaListsBean.NodesBeanX.NodesBean>() {
                                    @Override
                                    public void provideProvinces(AddressReceiver<SkuDetailVo.AreaListsBean> addressReceiver) {
                                        addressReceiver.send(listsBeanList);
                                    }

                                    @Override
                                    public void provideCitiesWith(SkuDetailVo.AreaListsBean areaListsBean, AddressReceiver<SkuDetailVo.AreaListsBean.NodesBeanX> addressReceiver) {

                                        addressReceiver.send(areaListsBean.getNodes());
                                    }

                                    @Override
                                    public void provideCountiesWith(SkuDetailVo.AreaListsBean.NodesBeanX nodesBeanX, AddressReceiver<SkuDetailVo.AreaListsBean.NodesBeanX.NodesBean> addressReceiver) {
                                        addressReceiver.send(nodesBeanX.getNodes());
                                    }

                                    @Override
                                    public void provideStreetsWith(SkuDetailVo.AreaListsBean.NodesBeanX.NodesBean nodesBean, AddressReceiver<Province> addressReceiver) {
                                        addressReceiver.send(new ArrayList<Province>());
                                    }
                                });
                                dialog.setOnAddressSelectedListener(new OnAddressSelectedListener() {
                                    @Override
                                    public void onAddressSelected(Province province0, Province province1, Province province2, Province province3) {
                                        dialog.dismiss();
                                        String previousCityId = cityId;
                                        String province="";
                                        String city="";
                                        String zone="";
                                        SkuDetailVo.AreaListsBean areaListsBean= (SkuDetailVo.AreaListsBean) province0;
                                        SkuDetailVo.AreaListsBean.NodesBeanX nodesBeanX= (SkuDetailVo.AreaListsBean.NodesBeanX) province1;
                                        SkuDetailVo.AreaListsBean.NodesBeanX.NodesBean nodesBean= (SkuDetailVo.AreaListsBean.NodesBeanX.NodesBean) province2;

                                        if(province1==null){

                                            cityId = areaListsBean.getId();
                                            province = areaListsBean.getName();
//                                            city = listArrayList.get(options1).get(option2).getPickerViewText();
//                                            zone = listListList.get(options1).get(option2).get(options3).getPickerViewText();

                                            tvCity.setText(String.format("%s", province));
                                        }else if(province2==null){

                                            cityId =nodesBeanX.getId();
                                            province = areaListsBean.getName();
                                            city = nodesBeanX.getName();
//                                          zone = listListList.get(options1).get(option2).get(options3).getPickerViewText();

                                            tvCity.setText(String.format("%s-%s", province, city));
                                        }else {
                                            cityId = nodesBean.getId();
                                            province =areaListsBean.getName();
                                            city = nodesBeanX.getName();
                                            zone = nodesBean.getName();

                                            tvCity.setText(String.format("%s-%s-%s", province, city, zone));
                                        }

                                        if (cityId.equals(previousCityId)) {
                                            return;
                                        }
//                                        selectSku(null);
                                        id=null;
                                        packageCode=spuPoBean.getPackageCode();
                                        loadSetmealData();
                                    }
                                });

//                                boolean is1Levl=false;
//                                boolean is2Level=false;
//                                llCity.setVisibility(View.VISIBLE);
//                                final ArrayList<ArrayList<SkuDetailVo.AreaListsBean.NodesBeanX>> listArrayList = new ArrayList<>();
//                                final ArrayList<ArrayList<ArrayList<SkuDetailVo.AreaListsBean.NodesBeanX.NodesBean>>> listListList = new ArrayList<>();
//                                for (SkuDetailVo.AreaListsBean areaListsBean : listsBeanList) {
//                                    ArrayList<SkuDetailVo.AreaListsBean.NodesBeanX> nodesBeanXList = areaListsBean.getNodes();
//                                    listArrayList.add(nodesBeanXList);
//                                    if (nodesBeanXList != null) {
//                                        ArrayList<ArrayList<SkuDetailVo.AreaListsBean.NodesBeanX.NodesBean>> listList = new ArrayList<>();
//                                        for (SkuDetailVo.AreaListsBean.NodesBeanX nodesBeanX : nodesBeanXList) {
//                                            ArrayList<SkuDetailVo.AreaListsBean.NodesBeanX.NodesBean> list = nodesBeanX.getNodes();
//                                            if(list!=null){
//                                                listList.add(list);
//                                            }else {
//                                                is2Level=true;
//                                            }
//                                        }
//                                        listListList.add(listList);
//                                    }else {
//                                        is1Levl=true;
//                                    }
//                                }
                                //三级联动效果
//                                if(is1Levl){
//                                    pvOptions.setPicker(listsBeanList);
//                                }else if(is2Level){
//                                    pvOptions.setPicker(listsBeanList, listArrayList,  true);
//                                }else {
//                                    pvOptions.setPicker(listsBeanList, listArrayList, listListList, true);
//
//                                }
//                                pvOptions.setPicker(listsBeanList, listArrayList, listListList, true);
//                                //                            pvOptions.setTitle("选择城市");
//                                pvOptions.setCyclic(false, false, false);
//                                final boolean finalIs1Levl = is1Levl;
//                                final boolean finalIs2Level = is2Level;
//                                pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
//
//                                    @Override
//                                    public void onOptionsSelect(int options1, int option2, int options3) {
//                                        String previousCityId = cityId;
//                                        String province="";
//                                        String city="";
//                                        String zone="";
//
//                                        if(finalIs1Levl){
//                                            cityId = listsBeanList.get(options1).getId();
//                                            province = listsBeanList.get(options1).getPickerViewText();
////                                            city = listArrayList.get(options1).get(option2).getPickerViewText();
////                                            zone = listListList.get(options1).get(option2).get(options3).getPickerViewText();
//
//                                            tvCity.setText(String.format("%s", province));
//                                        }else if(finalIs2Level){
//                                            cityId = listArrayList.get(options1).get(option2).getId();
//                                            province = listsBeanList.get(options1).getPickerViewText();
//                                            city = listArrayList.get(options1).get(option2).getPickerViewText();
////                                          zone = listListList.get(options1).get(option2).get(options3).getPickerViewText();
//
//                                            tvCity.setText(String.format("%s-%s", province, city));
//                                        }else {
//                                            cityId = listListList.get(options1).get(option2).get(options3).getId();
//                                            province = listsBeanList.get(options1).getPickerViewText();
//                                            city = listArrayList.get(options1).get(option2).getPickerViewText();
//                                            zone = listListList.get(options1).get(option2).get(options3).getPickerViewText();
//
//                                            tvCity.setText(String.format("%s-%s-%s", province, city, zone));
//                                        }
//
//                                        if (cityId.equals(previousCityId)) {
//                                            return;
//                                        }
//                                        List<SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean> listBeen = new ArrayList<SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean>();
//                                        listBeen.addAll(goodsDetailAttrAdapter.getAllData());
//
//                                        Collections.sort(listBeen, new Comparator<SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean>() {
//                                            @Override
//                                            public int compare(SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean o1, SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean o2) {
//                                                return Integer.valueOf(o2.getId()) - Integer.valueOf(o1.getId());
//                                            }
//                                        });
//                                        //todo
//                                        StringBuilder sb = new StringBuilder();
//                                        for (SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean bean : listBeen) {
//                                            sb.append(bean.getCheckedItem()).append(",");
//                                        }
//
//                                        String selectedIds = "";
//                                        if (sb.length() > 0) {
//                                            selectedIds = sb.deleteCharAt(sb.lastIndexOf(",")).toString();
//                                        }
//                                        LogUtils.e("selectedIds==" + selectedIds);
//                                        List<SkuDetailVo.SkuInfoVoBean> list = skuDetailVo.getAllListSku();
//                                        String strCityId = cityId;
//                                        boolean found = false;
//                                        for (SkuDetailVo.SkuInfoVoBean skuInfoVoBean1 : list) {
//                                            if (StringUtils.isEmpty(selectedIds) && StringUtils.isEmpty(skuInfoVoBean1.getProductAttribute())
//                                                    && strCityId.equals(skuInfoVoBean1.getAreaLevel3())) {
//                                                id = skuInfoVoBean1.getProductSkuId();
//                                                loadData();
//                                                found = true;
//                                                break;
//                                            }
//                                            if (selectedIds.equals(skuInfoVoBean1.getProductAttribute())
//                                                    && strCityId.equals(skuInfoVoBean1.getAreaLevel3())) {
//                                                id = skuInfoVoBean1.getProductSkuId();
//                                                loadData();
//                                                found = true;
//                                                break;
//                                            }
//                                        }
//                                        if (!found) {//根据城市id和skuid查询
//
//                                        }
//
//                                    }
//                                });
                            } else {
                                llCity.setVisibility(View.GONE);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }


                    // 无需设置可以不用重写
                    // !!!!注意参数为ApiException 类型，要不要写在Throwable那个了
                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        skuDetailVo=null;
                        price=null;
                        isFirst = true;
                        loading.showError();

//                        if(adapter.getCount()<=0){
//                            recyclerView.showError();
//                        }else {
//                            recyclerView.showRecycler();
//                            recyclerView.setRefreshing(false);
//                            adapter.pauseMore();
//                        }
                    }

                    // 无需设置可以不用重写
                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }
                });
        addSubscrebe(rxSubscription);
    }

    private boolean selectSku(String currentAttrId) {
        List<SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean> listBeen = new ArrayList<SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean>();
        listBeen.addAll(goodsDetailAttrAdapter.getAllData());
        //todo
        Collections.sort(listBeen, new Comparator<SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean>() {
            @Override
            public int compare(SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean o1, SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean o2) {
                return Integer.valueOf(o2.getId()) - Integer.valueOf(o1.getId());
            }
        });
        //todo
        StringBuilder sb = new StringBuilder();
        HashSet<String> attrIdSet=new HashSet<>();
        for (SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean bean : listBeen) {
            sb.append(bean.getCheckedItem()).append(",");
            attrIdSet.add(bean.getCheckedItem());
        }

        String selectedIds = "";
        if (sb.length() > 0) {
            selectedIds = sb.deleteCharAt(sb.lastIndexOf(",")).toString();
            if(currentAttrId==null){
                currentAttrId=listBeen.get(0).getId();
            }
        }
        attrIdSet.remove(currentAttrId);
        LogUtils.e("selectedIds==" + selectedIds);
        List<SkuDetailVo.SkuInfoVoBean> list = skuDetailVo.getAllListSku();
//                                        String strCityId = cityId;

        boolean found = false;
        HashSet<String>attSetCp=new HashSet<>();
        int lastMatchSize=Integer.MAX_VALUE;
        for (SkuDetailVo.SkuInfoVoBean skuInfoVoBean1 : list) {
            boolean isSameCity=cityId.equals(skuInfoVoBean1.getAreaLevel3())
                    ||cityId.equals(skuInfoVoBean1.getAreaLevel2())
                    ||cityId.equals(skuInfoVoBean1.getAreaLevel1());
            //TODO sort
            String prodAttribute=skuInfoVoBean1.getProductAttribute();
            if (StringUtils.isEmpty(selectedIds) && StringUtils.isEmpty(prodAttribute)
                    && isSameCity) {
                id = skuInfoVoBean1.getProductSkuId();
                found = true;
                break;
            }
            if(prodAttribute!=null){
                String[]attrArr=prodAttribute.split(",");
                List<String>stringList=new ArrayList<String>();
                for(int i=0;i<attrArr.length;i++){
                    stringList.add(attrArr[i]);
                }
                Collections.sort(stringList, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        try {
                            return Integer.valueOf(o2)-Integer.valueOf(o1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return 0;
                    }
                });
                sb = new StringBuilder();

                for (String str : stringList) {
                    sb.append(str).append(",");
                }
                prodAttribute = "";
                if (sb.length() > 0) {
                    prodAttribute = sb.deleteCharAt(sb.lastIndexOf(",")).toString();
                }
                if (selectedIds.equals(prodAttribute)
                        && isSameCity) {
                    id = skuInfoVoBean1.getProductSkuId();

                    found = true;
                    break;
                }
                attSetCp.clear();
                attSetCp.addAll(attrIdSet);
                attSetCp.removeAll(stringList);
                if(isSameCity&&!found){
                    if(prodAttribute.contains(currentAttrId)){
                        if(attSetCp.size()<lastMatchSize){
                            id = skuInfoVoBean1.getProductSkuId();
                            lastMatchSize=attSetCp.size();
                        }
                    }
                }
            }
        }
        loadData();

       return found;
    }

    private void createOrder() {//商品详情接口 套餐详情单独
        isFirst=false;
        LogUtils.e("createOrder");
//        id="75";
        String customId= SharedPreferenceUtil.getAppSp().getInt(Constants.USER_ID)+"";
        CreateOrderRequestVo goodsDetailRequesstVo = new CreateOrderRequestVo();
        goodsDetailRequesstVo.setSkuId(id);
        goodsDetailRequesstVo.setCustomerId(customId);
        goodsDetailRequesstVo.setBuyNum(tvNum.getText().toString());
        List<SkuDetailVo.ServiceProductsBean> list = apendAdapter.getAllData();
        if (list != null) {
            StringBuilder sb=new StringBuilder();
            for (SkuDetailVo.ServiceProductsBean productsBean : list) {
                if ("1".equals(productsBean.getIsMust())||productsBean.isSelected()) {
                   sb.append(productsBean.getId()).append(",");
                }
            }
            if(sb.length()>0){
                sb.deleteCharAt(sb.lastIndexOf(","));
                goodsDetailRequesstVo.setServCom(sb.toString());
            }

        }
        String sysCode = "111";
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String param = new Gson().toJson(goodsDetailRequesstVo);
        String sign = SystemUtil.getSign(sysCode, timeStamp, param);

        Subscription rxSubscription = RetrofitHelper.getCpMgrApiService()
                .createOrder(sysCode, timeStamp, param, sign)
                .compose(new PageTransformer<HttpResult<CreateOrderVo>>())
                .subscribe(new RxSubscriber<HttpResult<CreateOrderVo>>(GoodsDetailFragment.this) {
                    // 必须重写
                    @Override
                    public void onNext(HttpResult<CreateOrderVo> contentBeen) {
                        try {
                            Intent intent=new Intent(mActivity,ConfirmOrderActivity.class);
                            intent.putExtra(OrderDetailActivity.ORDER_NO,contentBeen.getData().getOrderCd());
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                    // 无需设置可以不用重写
                    // !!!!注意参数为ApiException 类型，要不要写在Throwable那个了
                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                    }

                    // 无需设置可以不用重写
                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }
                });
        addSubscrebe(rxSubscription);
    }
    private void calculatePrice() {
        //todo calculate price
        try {
            float priceTotal = 0;
            priceTotal = Float.valueOf(price);
            if(apendAdapter!=null){
                List<SkuDetailVo.ServiceProductsBean> list = apendAdapter.getAllData();
                if (list != null) {
                    for (SkuDetailVo.ServiceProductsBean productsBean : list) {
                        if ("1".equals(productsBean.getIsMust())||productsBean.isSelected()) {
                            priceTotal += Float.valueOf(productsBean.getServiceSalesPrice());
                        }
                    }

                }
            }
            priceTotal *= Integer.valueOf(tvNum.getText().toString());
            int priceInt = (int) priceTotal;
            tvTotalNum.setText(String.format("%s", priceInt));
        } catch (Exception e) {
            tvTotalNum.setText(String.format("%s", "?"));
            e.printStackTrace();
        }
    }

    public static GoodsDetailFragment getInstance(String id) {
        GoodsDetailFragment goodsDetailFragment = new GoodsDetailFragment();
        goodsDetailFragment.id = id;
        return goodsDetailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onRequestStart() {
        super.onRequestStart();
        if (isFirst) {
            return;
        }
        initProgressDialog();
        progressDialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Ntalker.getInstance().setSDKListener(null);// 小能监听接口
    }

    public void setType(String type) {
        this.type = type;
    }

    @OnClick({R.id.llMinus, R.id.llAdd, R.id.llOnline, R.id.llPhone, R.id.tvPay, R.id.llCity})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llCity:
//                bottomSheetDialog.show();
                if (dialog != null) {
                    LogUtils.e("pvOptions.show()");
//                    pvOptions.show();
                    dialog.show();
                }
                break;
            case R.id.tvPay:
                if(mActivity.isLogin()){
                    if(llCity.getVisibility()==View.VISIBLE&&StringUtils.isEmpty(tvCity.getText().toString())){
                        ToastUtil.shortShow("请选择服务地区");
                        return;
                    }
                    createOrder();
                }

                break;
            case R.id.llMinus:
                int num = 0;
                try {
                    num = Integer.valueOf(tvNum.getText().toString());
                    if (num > 1) {
                        num--;
                    }
                    if(num<=1) {
                        ivMinus.setEnabled(false);
                    }
                    tvNum.setText(String.valueOf(num));
                    calculatePrice();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.llAdd:
                try {
                    ivMinus.setEnabled(true);
                    num = Integer.valueOf(tvNum.getText().toString());
                    num++;
                    tvNum.setText(String.valueOf(num));
                    calculatePrice();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.llOnline://todo 小能
                startChat();
                break;
            case R.id.llPhone://
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:4006865658"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }


    /**
     * @params settingid: 接待组ID,该组内必须有客服存在,建议使用非管理员客服【必传】
     * @params groupName: 客服组名称，默认的企业客服名称,在异常情况下显示(如网络异常)【建议】
     * @params kefuid: 指定客服id，默认写空字符串或null，不指定客服. 指定客服id示例：kf_9979_ISME9754_T2D_admin
     * @params kefuname: 指定客服名称，默认写空字符串或null
     * @params chatParams: 聊天参数体（带子参数，参数如下）（与多个功能有关）
     * @return int 0 表示正常,其他参见错误码
     */
    String startPageTitle;//todo 怎么取值
    String startPageUrl;//todo 怎么取值
    String matchstr = CpMgrApiService.BASE_URL;

    void startChat() {
        String settingid = "kf_9328_1468918893409";
        boolean isRoot = false;
        if (isRoot) {
            settingid = "kf_9328_1468918893409";
        } else {
            settingid = "kf_9328_1468918916508";
        }
        String groupName = "在线咨询";

      /*  <ul class="w_zx_list">
        <li><a href="###" id="dtwf0" onclick="NTKF.im_openInPageChat('kf_9328_1468313441356')">工商服务</a></li>
        <li><a href="###" id="dtwf1" onclick="NTKF.im_openInPageChat('kf_9328_1468918543552')">财税服务</a></li>
        <li><a href="###" id="dtwf2" onclick="NTKF.im_openInPageChat('kf_9328_1468918637558')">知识产权</a></li>
        <li><a href="###" id="dtwf3" onclick="NTKF.im_openInPageChat('kf_9328_1468918916508')">行政审批</a></li>
        <li><a href="###" id="dtwf4" onclick="NTKF.im_openInPageChat('kf_9328_1468918586614')">人事社保</a></li>
        <li><a href="###" id="dtwf5" onclick="NTKF.im_openInPageChat('kf_9328_1468918698960')">加盟合作</a></li>
        <li><a href="###" id="dtwf6" onclick="NTKF.im_openInPageChat('kf_9328_1468918916508')">其他服务 </a></li>
        </ul>*/
        String kefuid = null;
        String kefuname = null;

        ChatParamsBody chatparams = new ChatParamsBody();

//**** 咨询发起页，用于PC客服端显示用户发起咨询的位置***********

        chatparams.startPageTitle = startPageTitle;  // 咨询发起页标题(必填)
        chatparams.startPageUrl = startPageUrl;//咨询发起页URL，必须以"http://"开头 （必填）

//**** 域名匹配,企业特殊需求********
        chatparams.matchstr = matchstr;//默认传空

//****erp参数, 被用参数,小能只负责经由SDK传到客服端,不做任何修改,处理*******
        chatparams.erpParam = "";

//***** 商品详情,如果页面含有商品,建议传入,用于sdk访客端和PC客服显示当前商品******

      /*  **以下三种模式只能选择其一,切记勿同时传入三种模式, 例如,选择id模式,其他模式参数不用写入,也不用传空***/

        chatparams.itemparams.appgoodsinfo_type = CoreData.SHOW_GOODS_BY_ID; //sdk显示商品信息模式. 建议使用id模式
        //  值0: 设置在SDK端不显示商品(默认)
        //  值1: 设置在SDK端以商品ID显示商品
        //  值2: 设置在SDK端以商品URL显示商品
        //  值3: 设置在SDK端以独立控件显示商品
//        chatparams.itemparams.clientgoodsinfo_type = CoreData.SHOW_GOODS_BY_ID; // pc端显示商品信息模式
//        // 值0: 设置在PC客服端不显示商品
//        // 值1: 设置在PC客服端以商品ID显示商品
//        // 值2: 设置在PC客服端以商品URL显示商品
//        chatparams.itemparams.clicktoshow_type = CoreData.CLICK_TO_APP_COMPONENT;// 点击SDK商品详情,
//        // 值0: 点击商品详情在SDK内打开(默认)
//        // 值1: 点击商品详情在APP内打开URL

//使用id模式
        chatparams.itemparams.itemparam = "";//用于区分PC和移动端商品存在差价问题,其他模式不支持
        chatparams.itemparams.goods_id = id;//  商品ID ,贵公司提供

//// 使用widget模式(控件模式)
//        chatparams.itemparams.goods_name = "2015年最新潮流时尚T恤偶像同款一二线城市包邮 速度抢购有超级大礼包等你来拿";//商品名称
//        chatparams.itemparams.goods_price = "￥：188";//商品价格
//        chatparams.itemparams.goods_image = "http://img.meicicdn.com/p/51/050010/h-050010-1.jpg";//商品图片  URL必须以"http://"开头
//        chatparams.itemparams.goods_url = "http://www.baidu.com";//商品详情  URL必须以"http://"开头
//
////使用url模式(webview显示于sdk内)
//        chatparams.itemparams.goods_showurl = "http://pic.shopex.cn/pictures/goodsdetail/29b.jpg?rnd=111111"; //URL必须以"http://"开头
///////////////////////////////
        int startChat = Ntalker.getInstance().startChat(mActivity, settingid, groupName, kefuid, kefuname, chatparams);
        if (0 == startChat) {
            LogUtils.e("打开聊窗成功");
        } else {
            LogUtils.e("打开聊窗失败，错误码:" + startChat);
        }
        Ntalker.getInstance().setSDKListener(this);// 小能监听接口


        /********************************* 动态申请小能SDK所需权限（与图片显示有关的存储权限；与语音消息有关的录音权限和电话权限） *************************************/
        String[] permissions = {
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
        };
        Ntalker.getInstance().getPermissions(mActivity, 200, permissions);
        /*******************************************************************************************************/
    }


    @Override
    public void onChatMsg(boolean b, String s, String s1, String s2, long l, boolean b1) {

    }

    @Override
    public void onUnReadMsg(String s, String s1, String s2, int i) {

    }

    @Override
    public void onClickMatchedStr(String s, String s1) {

    }

    @Override
    public void onClickUrlorEmailorNumber(int i, String s) {

    }

    @Override
    public void onClickShowGoods(int i, int i1, String s, String s1, String s2, String s3, String s4, String s5) {

    }

    @Override
    public void onError(int i) {

    }
}
