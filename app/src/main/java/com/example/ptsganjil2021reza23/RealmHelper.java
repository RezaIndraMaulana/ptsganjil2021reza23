package com.example.ptsganjil2021reza23;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    public static void save(final SportsModel SportModel){
        Realm.getDefaultInstance().executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null) {
                    Log.e("Created", "Database was created");
                    realm.copyToRealm(SportModel);
                } else {
                    Log.e("ppppp", "execute: Database not Exist");
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.e("Suksess", "Data telah berhasil");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e("Gagal", "Data tidak berhasil");
            }
        });
    }

    public static SportsModel getOneModel(final String strSport) {
        return Realm.getDefaultInstance().where(SportsModel.class).equalTo("strSport", strSport).findFirst();
    }

    public List<SportsModel> getAllModel() {
        RealmResults<SportsModel> models = Realm.getDefaultInstance().where(SportsModel.class).findAll();
        return models;
    }

//    public void delete(final String strSport){
//        RealmResults<SportsModel> model = realm.where(SportsModel.class).equalTo("strSport", strSport).findAll();
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                model.deleteFromRealm(0);
//            }
////        });
//    }


}
