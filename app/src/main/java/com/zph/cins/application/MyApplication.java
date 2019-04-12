package com.zph.cins.application;

import android.app.Application;

import com.zph.cins.inject.component.ApplicationComponent;
import com.zph.cins.inject.component.DaggerApplicationComponent;
import com.zph.cins.inject.module.ApplicationModule;

public class MyApplication extends Application {
    private static final String TAG = MyApplication.class.getSimpleName();
    private static MyApplication myApplication;
    private ApplicationComponent applicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        applicationComponent = DaggerApplicationComponent.builder().
                applicationModule(new ApplicationModule(this)).build();
        applicationComponent.inject(this);
    }

    public static MyApplication getInstance() {
        return myApplication;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
