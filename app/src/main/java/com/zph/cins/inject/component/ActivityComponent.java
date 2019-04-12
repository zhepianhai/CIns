package com.zph.cins.inject.component;
import com.zph.cins.base.frag.BaseMainFragment;
import com.zph.cins.frag.FragHome;
import com.zph.cins.inject.PerActivity;
import com.zph.cins.ui.main.MainActivity;
import com.zph.cins.inject.module.ActivityModule;

import dagger.Component;
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);

    void inject(BaseMainFragment baseMainFragment);
    void inject(FragHome fragHome);
}
