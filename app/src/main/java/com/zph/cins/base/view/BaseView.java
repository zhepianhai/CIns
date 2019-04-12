package com.zph.cins.base.view;

import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * @author zph
 */

public interface BaseView extends MvpView {

    void showLoading(boolean pullToRefresh);

    void showContent();

    void showMessage(String msg, int type);

    void showError(String message);
}
