package com.zph.cins.inject.module;

import android.app.Application;
import android.content.Context;

import com.zph.cins.inject.ApplicationContext;

import java.io.File;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * @author zph
 * @date 2018/3/21
 */
@Module
public class ApplicationModule {
    private final Application mApplication;

    public ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    public Application providesApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    public Context providesContext() {
        return mApplication;
    }

}
