package com.selflearning.chemistree;

import android.app.Application;
import android.content.Context;

public class ChemistreeApplication extends Application {

    private static ChemistreeApplication instance;
    private static Context appContext;

    public static ChemistreeApplication getInstance(){
        return instance;
    }

    public static Context getAppContext() {
        return appContext;
    }

    public void setAppContext(Context appContext) {
        this.appContext = appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        this.setAppContext(getApplicationContext());
    }
}
