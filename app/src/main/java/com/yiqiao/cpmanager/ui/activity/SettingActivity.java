package com.yiqiao.cpmanager.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.AppUtils;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.app.App;
import com.yiqiao.cpmanager.app.Constants;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.transformer.SchedulerTransformer;
import com.yiqiao.cpmanager.util.GlideCacheUtil;
import com.yiqiao.cpmanager.util.SharedPreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Xu on 2016/11/23.
 */

public class SettingActivity extends BaseActivity {

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
    @BindView(R.id.tvVersionName)
    TextView tvVersionName;
    @BindView(R.id.tvCache)
    TextView tvCache;
    @BindView(R.id.btLogout)
    Button btLogout;
    @BindView(R.id.llVersion)
    LinearLayout llVersion;
    @BindView(R.id.llClearCache)
    LinearLayout llClearCache;

    @Override
    protected int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initEventAndData() {
        tvTitle.setText("设置");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        getDacheSize();
        getApkVersion();
    }

    private void getApkVersion() {
        Subscription subs=  Observable.create(new Observable.OnSubscribe<String>() {                         //  (2)
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String json = AppUtils.getAppVersionName(App.getInstance());                //  (4)
                setData(subscriber, json);                                                      //  (5)
            }
            void  setData(Subscriber<? super String> subscriber, String json) {
                if (TextUtils.isEmpty(json)) {                          //  (6)
                    subscriber.onError(new Throwable("not data"));
                    return;
                }
                subscriber.onNext(json);                                //  (7)
                subscriber.onCompleted();
            }
        }).compose(SchedulerTransformer.<String>getInstance())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        tvVersionName.setText(String.format("V_%s",s));
                    }
                });
        addSubscrebe(subs);
    }

    private void getDacheSize() {
        Subscription subs=  Observable.create(new Observable.OnSubscribe<String>() {                         //  (2)
             @Override
             public void call(Subscriber<? super String> subscriber) {
                 String json = GlideCacheUtil.getCacheSize();                //  (4)
                 setData(subscriber, json);                                                      //  (5)
             }
            void  setData(Subscriber<? super String> subscriber, String json) {
                 if (TextUtils.isEmpty(json)) {                          //  (6)
                     subscriber.onError(new Throwable("not data"));
                     return;
                 }
                 subscriber.onNext(json);                                //  (7)
                 subscriber.onCompleted();
             }
         }).compose(SchedulerTransformer.<String>getInstance())
                 .subscribe(new Subscriber<String>() {
                     @Override
                     public void onCompleted() {

                     }

                     @Override
                     public void onError(Throwable e) {

                     }

                     @Override
                     public void onNext(String s) {
                         tvCache.setText(s);
                     }
                 });
        addSubscrebe(subs);
    }

    @OnClick({R.id.ivBack, R.id.llVersion, R.id.llClearCache, R.id.btLogout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                onBackPressedSupport();
                break;
            case R.id.llVersion:

                break;
            case R.id.llClearCache:
                doClearCache();
                break;
            case R.id.btLogout:
//                spUtils .putInt(Constants.USER_ID,contentBeen.getCustomerId());
                SharedPreferenceUtil.getAppSp().putInt(Constants.USER_ID,0);
                finish();
                break;
        }
    }

    private void doClearCache() {
        Subscription subs=  Observable.create(new Observable.OnSubscribe<Boolean>() {

            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                try {
                    boolean result= GlideCacheUtil.clearCacheDiskSelf();                                                 //  (5)
                    subscriber.onNext(result);
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        }).compose(SchedulerTransformer.<Boolean>getInstance())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        tvCache.setText("清理中...");
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        tvCache.setText("已清除");//
                    }

                    @Override
                    public void onNext(Boolean s) {
                        tvCache.setText("已清除");
                    }
                });
        addSubscrebe(subs);
    }
}
