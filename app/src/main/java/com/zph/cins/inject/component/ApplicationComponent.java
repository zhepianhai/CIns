package com.zph.cins.inject.component;

import android.content.Context;

import com.zph.cins.application.MyApplication;
import com.zph.cins.inject.ApplicationContext;
import com.zph.cins.inject.module.ApplicationModule;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MyApplication myApplication);
    @ApplicationContext
    Context getContext();
}
