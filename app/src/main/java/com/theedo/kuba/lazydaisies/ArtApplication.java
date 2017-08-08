package com.theedo.kuba.lazydaisies;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.google.firebase.database.FirebaseDatabase;

public class ArtApplication extends MultiDexApplication {

    private static ArtApplication mainApplication;


    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mainApplication = this;



    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }


    public static synchronized ArtApplication getInstance() {
        return mainApplication;
    }
}



