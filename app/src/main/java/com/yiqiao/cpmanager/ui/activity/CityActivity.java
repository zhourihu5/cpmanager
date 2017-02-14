package com.yiqiao.cpmanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;
import com.baidu.location.service.LocationService;
import com.blankj.utilcode.utils.SPUtils;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.utils.Log;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.app.App;
import com.yiqiao.cpmanager.app.Constants;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.entity.CityVo;
import com.yiqiao.cpmanager.entity.HttpResult;
import com.yiqiao.cpmanager.entity.SkuListVo;
import com.yiqiao.cpmanager.http.RetrofitHelper;
import com.yiqiao.cpmanager.http.exception.ApiException;
import com.yiqiao.cpmanager.subscribers.RxSubscriber;
import com.yiqiao.cpmanager.transformer.PageTransformer;
import com.yiqiao.cpmanager.ui.adapter.CityAdapter;
import com.yiqiao.cpmanager.util.LogUtils;
import com.yiqiao.cpmanager.util.SharedPreferenceUtil;
import com.yiqiao.cpmanager.util.SystemUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;

import static com.yiqiao.cpmanager.R.id.recyclerView;

/**
 * Created by Xu on 2016/11/23.
 * 城市选择
 */

public class CityActivity extends BaseActivity {

    @BindView(R.id.tvSelected)
    TextView tvSelected;
    @BindView(R.id.tvDesc)
    TextView tvDesc;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;


    CityAdapter cityAdapter;
    private LocationService locationService;
    @Override
    protected int getLayout() {
        return R.layout.activity_city;
    }

    @Override
    protected void initEventAndData() {
        tvTitle.setText("城市选择");

        recycleView.setLayoutManager(new GridLayoutManager(mContext,4));
        ArrayList<CityVo>arrayList=new ArrayList<>();
//        for(int i=0;i<20;i++){
//            CityVo cityVo=  new CityVo();
//            cityVo.setName("城市"+i);
//            arrayList.add(cityVo);
//        }

        cityAdapter=new CityAdapter(mContext,arrayList);
        recycleView.setAdapter(cityAdapter);
        SPUtils spUtils= SharedPreferenceUtil.getAppSp();
        String currentCityId= spUtils .getString(Constants.CURRENT_CITY_ID);
        String currentCityName= spUtils .getString(Constants.CURRENT_CITY_NAME,"北京");
        cityAdapter.setCurrentCityId(currentCityId);
        setSelectedCityText(currentCityName);
        cityAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                int previous=cityAdapter.getSelected();
                if(previous>=0&&previous<cityAdapter.getCount()){
                    CityVo previousCityVo=cityAdapter.getItem(previous);
                    previousCityVo.setCurrentCity(String.valueOf(false));
                }
                CityVo cityVo= cityAdapter.getItem(position);
                cityVo.setCurrentCity(String.valueOf(true));
                String name=cityVo.getAreaDesc();
                setSelectedCityText(name);
                cityAdapter.setSelected(position);
                Intent data=new Intent();
                data.putExtra(CityVo.class.getCanonicalName(),cityVo);
                setResult(RESULT_OK, data);
                SPUtils spUtils= SharedPreferenceUtil.getAppSp();
                spUtils .putString(Constants.CURRENT_CITY_ID,cityVo.getId());
                spUtils .putString(Constants.CURRENT_CITY_NAME,cityVo.getAreaDesc());
                finish();
            }
        });
        loadData();
    }

    private void setSelectedCityText(String name) {
        tvSelected.setText(name);
        tvDesc.setText(String.format("当前正为您推荐%s的服务，您可以切换城市站查看其它城市的服务",name));
    }

    private void loadData() {
        String sysCode = "111";
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String param ="";
        String sign = SystemUtil.getSign(sysCode, timeStamp, param);

        Subscription rxSubscription = RetrofitHelper.getCpMgrApiService()
                .getOpenCity(sysCode, timeStamp, param, sign)
                .compose(new PageTransformer<HttpResult<List<CityVo>>>())
                .subscribe(new RxSubscriber<HttpResult<List<CityVo>>>(mContext) {
                    // 必须重写
                    @Override
                    public void onNext(HttpResult<List<CityVo>> contentBeen) {
                        LogUtils.e("searchspu onNext");
                        cityAdapter.addAll(contentBeen.getData());
                        int previous=cityAdapter.getSelected();
                        if(previous>=0&&previous<cityAdapter.getCount()){
                            CityVo cityVo=cityAdapter.getItem(previous);
                            setSelectedCityText(cityVo.getAreaDesc());
                        }
                    }


                    // 无需设置可以不用重写
                    // !!!!注意参数为ApiException 类型，要不要写在Throwable那个了
                    @Override
                    protected void onError(ApiException ex) {
                        LogUtils.e("searchspu onError");
                        ex.printStackTrace();
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // -----------location config ------------
        locationService = ((App) getApplication()).locationService;
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        //注册监听
        int type = getIntent().getIntExtra("from", 0);
        if (type == 0) {
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        } else if (type == 1) {
            locationService.setLocationOption(locationService.getOption());
        }


    }
    /***
     * Stop location service
     */
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
    }

    @OnClick(R.id.ivBack)
    public void onClick() {
        onBackPressedSupport();
    }


    /*****
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     *
     */
    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            Logger.e("onReceiveLocation");
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                StringBuffer sb = new StringBuffer(256);
                sb.append("time : ");
                /**
                 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
                 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
                 */
                sb.append(location.getTime());
                sb.append("\nlocType : ");// 定位类型
                sb.append(location.getLocType());
                sb.append("\nlocType description : ");// *****对应的定位类型说明*****
                sb.append(location.getLocTypeDescription());
                sb.append("\nlatitude : ");// 纬度
                sb.append(location.getLatitude());
                sb.append("\nlontitude : ");// 经度
                sb.append(location.getLongitude());
                sb.append("\nradius : ");// 半径
                sb.append(location.getRadius());
                sb.append("\nCountryCode : ");// 国家码
                sb.append(location.getCountryCode());
                sb.append("\nCountry : ");// 国家名称
                sb.append(location.getCountry());
                sb.append("\ncitycode : ");// 城市编码
                sb.append(location.getCityCode());
                sb.append("\ncity : ");// 城市
                sb.append(location.getCity());
                sb.append("\nDistrict : ");// 区
                sb.append(location.getDistrict());
                sb.append("\nStreet : ");// 街道
                sb.append(location.getStreet());
                sb.append("\naddr : ");// 地址信息
                sb.append(location.getAddrStr());
                sb.append("\nUserIndoorState: ");// *****返回用户室内外判断结果*****
                sb.append(location.getUserIndoorState());
                sb.append("\nDirection(not all devices have value): ");
                sb.append(location.getDirection());// 方向
                sb.append("\nlocationdescribe: ");
                sb.append(location.getLocationDescribe());// 位置语义化信息
                sb.append("\nPoi: ");// POI信息
                if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
                    for (int i = 0; i < location.getPoiList().size(); i++) {
                        Poi poi = (Poi) location.getPoiList().get(i);
                        sb.append(poi.getName() + ";");
                    }
                }
                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                    sb.append("\nspeed : ");
                    sb.append(location.getSpeed());// 速度 单位：km/h
                    sb.append("\nsatellite : ");
                    sb.append(location.getSatelliteNumber());// 卫星数目
                    sb.append("\nheight : ");
                    sb.append(location.getAltitude());// 海拔高度 单位：米
                    sb.append("\ngps status : ");
                    sb.append(location.getGpsAccuracyStatus());// *****gps质量判断*****
                    sb.append("\ndescribe : ");
                    sb.append("gps定位成功");
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                    // 运营商信息
                    if (location.hasAltitude()) {// *****如果有海拔高度*****
                        sb.append("\nheight : ");
                        sb.append(location.getAltitude());// 单位：米
                    }
                    sb.append("\noperationers : ");// 运营商信息
                    sb.append(location.getOperators());
                    sb.append("\ndescribe : ");
                    sb.append("网络定位成功");
                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                    sb.append("\ndescribe : ");
                    sb.append("离线定位成功，离线定位结果也是有效的");
                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    sb.append("\ndescribe : ");
                    sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    sb.append("\ndescribe : ");
                    sb.append("网络不同导致定位失败，请检查网络是否通畅");
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    sb.append("\ndescribe : ");
                    sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                }
                Logger.d(sb.toString());
            }
        }

    };
}
