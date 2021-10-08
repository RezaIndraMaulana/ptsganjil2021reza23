package com.example.ptsganjil2021reza23;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .allowWritesOnUiThread(true)
                .name("sportdb")
                .schemaVersion(0)
                .allowQueriesOnUiThread(true)
                .build();
        Realm.setDefaultConfiguration(configuration);
    }


}
