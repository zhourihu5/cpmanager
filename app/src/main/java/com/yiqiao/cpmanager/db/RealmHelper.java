package com.yiqiao.cpmanager.db;

import android.content.Context;

import com.yiqiao.cpmanager.app.App;

import java.util.ArrayList;
import java.util.List;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmResults;
import io.realm.RealmSchema;
import io.realm.Sort;

/**
 * Created by codeest on 16/8/16.
 */

public class RealmHelper {

    private static final String DB_NAME = "cpmanager.realm";

    private Realm mRealm;

    private RealmHelper(Context mContext) {
        mRealm = Realm.getInstance(new RealmConfiguration.Builder(mContext)
                .schemaVersion(1)
                .migration(new MyMigration())
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
    public void save(final SearchTradeMarkVo bean) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                bean.setTime(System.currentTimeMillis());
                mRealm.copyToRealmOrUpdate(bean);
            }
        });


    }
    /**
     * 增加 查询商品记录
     * @param bean
     */
    public void save(final SearchSkuVo bean) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                bean.setTime(System.currentTimeMillis());
                mRealm.copyToRealmOrUpdate(bean);
            }
        });


    }

    public List<SearchTradeMarkVo> getSearchTradeMarkVoList() {
        //使用findAllSort ,先findAll再result.sort无效
        final RealmResults<SearchTradeMarkVo> results = mRealm.where(SearchTradeMarkVo.class).findAll().sort("time", Sort.DESCENDING);
        final List<SearchTradeMarkVo>list= mRealm.copyFromRealm(results);
        final List<SearchTradeMarkVo>resultList=new ArrayList<>();
        if(list.size()>8){
            mRealm.beginTransaction();
            results.deleteAllFromRealm();
            for(int i=0;i<8;i++){
                mRealm.copyToRealmOrUpdate(list.get(i));
                resultList.add(list.get(i));
            }
            mRealm.commitTransaction();
        }else {
            resultList.addAll(list);
        }
        return resultList;
    }
    public List<SearchSkuVo> getSearchSkuVoList() {
        //使用findAllSort ,先findAll再result.sort无效
        final RealmResults<SearchSkuVo> results = mRealm.where(SearchSkuVo.class).findAll().sort("time", Sort.DESCENDING);
        final List<SearchSkuVo>list= mRealm.copyFromRealm(results);
        final List<SearchSkuVo>resultList=new ArrayList<>();
        if(list.size()>8){
            mRealm.beginTransaction();
            results.deleteAllFromRealm();
            for(int i=0;i<8;i++){
                mRealm.copyToRealmOrUpdate(list.get(i));
                resultList.add(list.get(i));
            }
            mRealm.commitTransaction();
        }else {
            resultList.addAll(list);
        }
        return resultList;
    }
    public void clearSearchTradeMarkVos(){
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<SearchTradeMarkVo> results = mRealm.where(SearchTradeMarkVo.class).findAll();
                results.deleteAllFromRealm();
            }
        });

    }
    public void clearSearchSkuVos(){
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<SearchSkuVo> results = mRealm.where(SearchSkuVo.class).findAll();
                results.deleteAllFromRealm();
            }
        });

    }

    private class MyMigration implements RealmMigration {
        @Override
        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
            RealmSchema schema = realm.getSchema();

            if (newVersion == 2) {
//                schema.get("User")
//                        .addField("desc", String.class);
                //todo data structure changed
            }

        }
        @Override
        public boolean equals(Object obj) {
            return obj instanceof MyMigration;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

    }
}
