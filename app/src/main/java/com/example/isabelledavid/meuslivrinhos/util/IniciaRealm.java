package com.example.isabelledavid.meuslivrinhos.util;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class IniciaRealm extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        // Realm.deleteRealm(config); // Deleta infos do Realm
        Realm.setDefaultConfiguration(config);
    }

}
