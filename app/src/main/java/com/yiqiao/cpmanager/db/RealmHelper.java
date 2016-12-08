package com.yiqiao.cpmanager.db;

import android.content.Context;

import com.yiqiao.cpmanager.app.App;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by codeest on 16/8/16.
 */

public class RealmHelper {

    private static final String DB_NAME = "cpmanager.realm";

    private Realm mRealm;

    private RealmHelper(Context mContext) {
        mRealm = Realm.getInstance(new RealmConfiguration.Builder(mContext)
                .deleteRealmIfMigrationNeeded()
                .name(DB_NAME)
                .build());
    }
    static RealmHelper instance;
    public static RealmHelper getInstance() {
        if(instance==null){
            instance=new RealmHelper(App.getInstance());
        }
        return instance;
    }

    /**
     * 增加 查询商标记录
     * @param bean
     */
    public void save(SearchTradeMarkVo bean) {
        mRealm.beginTransaction();
        mRealm.copyToRealm(bean);
        mRealm.commitTransaction();
    }
    public List<SearchTradeMarkVo> getSearchTradeMarkVoList() {
        //使用findAllSort ,先findAll再result.sort无效
        RealmResults<SearchTradeMarkVo> results = mRealm.where(SearchTradeMarkVo.class).findAllSorted("time");
        return mRealm.copyFromRealm(results);
    }
}
