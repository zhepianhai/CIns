package com.zph.cins.ui.main;


import com.zph.cins.base.MvpBasePresenter;
import com.zph.cins.inject.PerActivity;

import javax.inject.Inject;

/**
 * @author zph
 * @date 2018/3/20
 */
@PerActivity
public class MainPresenter extends MvpBasePresenter<MainView> implements IMain {
    @Inject
    public MainPresenter() {
    }
}
