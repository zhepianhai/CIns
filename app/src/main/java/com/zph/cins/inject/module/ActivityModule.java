package com.zph.cins.inject.module;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.zph.cins.inject.ActivityContext;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @author zph
 * @date 2018/3/21
 */
@Module
public class ActivityModule {
    private AppCompatActivity mAppCompatActivity;

    public ActivityModule(AppCompatActivity mAppCompatActivity) {
        this.mAppCompatActivity = mAppCompatActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mAppCompatActivity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return mAppCompatActivity;
    }



    @Provides
    List<Fragment> providesFragmentList() {
        return new ArrayList<>();
    }

    @Provides
    FragmentManager providesSupportFragmentManager(AppCompatActivity mAppCompatActivity) {
        return mAppCompatActivity.getSupportFragmentManager();
    }


}
