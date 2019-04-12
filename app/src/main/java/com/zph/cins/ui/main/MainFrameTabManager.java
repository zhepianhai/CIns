package com.zph.cins.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TabHost;

import java.util.HashMap;
import java.util.Map;

public class MainFrameTabManager implements TabHost.OnTabChangeListener {
    private final FragmentActivity mActivity;
    private final TabHost mTabHost;
    private final int mContainerId;
    private final HashMap<String, TabInfo> mTabs = new HashMap<>();
    //记录最后一个显示的Fragment，也就是当前的Fragment。
    private TabInfo mLastTab;
    // refreshMap判断fragment是否可刷新，如果某个Fragment需要刷新，就将tag添加到该Map中。
    private Map<String, Boolean> refreshMap = new HashMap<>();
    private TabChangeListener mTabChangeListener;

    final class TabInfo {
        private final String tag;
        private final Class<?> clss;
        private final Bundle args;
        private Fragment fragment;

        TabInfo(String _tag, Class<?> _class, Bundle _args) {
            tag = _tag;
            clss = _class;
            args = _args;
        }
    }

    class DummyTabFactory implements TabHost.TabContentFactory {
        private final Context mContext;

        public DummyTabFactory(Context context) {
            mContext = context;
        }

        @Override
        public View createTabContent(String tag) {
            View v = new View(mContext);
            v.setMinimumWidth(0);
            v.setMinimumHeight(0);
            return v;
        }
    }

    public MainFrameTabManager(FragmentActivity activity, TabHost tabHost, int containerId) {
        mActivity = activity;
        mTabHost = tabHost;
        mContainerId = containerId;
        mTabHost.setOnTabChangedListener(this);
    }

    public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args) {
        //设置主体的显示内容，其实就是一个高度和宽度都为0的View，是为了屏蔽TabHost自带的Content区域。
        tabSpec.setContent(new DummyTabFactory(mActivity));

        String tag = tabSpec.getTag();
        TabInfo info = new TabInfo(tag, clss, args);

        // Check to see if we already have a fragment for this tab, probably
        // from a previously saved state. If so, deactivate it, because our
        // initial state is that a tab isn't shown.
        info.fragment = mActivity.getSupportFragmentManager().findFragmentByTag(tag);
        if (info.fragment != null && !info.fragment.isDetached()) {
            FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
            ft.detach(info.fragment);
            ft.commit();
        }

        mTabs.put(tag, info);
        mTabHost.addTab(tabSpec);
        // 测试添加的fragment是否需要刷新。
        // addToRefreshMap(tag, true);
    }

    @Override
    public void onTabChanged(String tabId) {
//        if(tabId.equals("河湖")){
//            Intent intent=new Intent(mActivity,SearchStcdActivity.class);
//            mActivity.startActivity(intent);
//            return;
//        }
        TabInfo newTab = mTabs.get(tabId);


        if (mLastTab != newTab) {
            FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
            if (mLastTab != null) {
                if (mLastTab.fragment != null) {
                    // 判断最后显示的fragment是否需要刷新，如果不需要刷新，调用hide方法；如果需要刷新，调用detach方法。
                    if (ifRefresh(mLastTab.tag)) {
                        // 调用detach方法，是为了下次显示刷新该fragment。
                        ft.detach(mLastTab.fragment);
                    } else {
                        // 使用hide方法，为了切换fragment不用刷新。
                        ft.hide(mLastTab.fragment);
                    }
                }
            }
            if (newTab != null) {
                if (newTab.fragment == null) {
                    newTab.fragment = Fragment.instantiate(mActivity,
                            newTab.clss.getName(), newTab.args);
                    ft.add(mContainerId, newTab.fragment, newTab.tag);
                } else {
                    if (ifRefresh(tabId)) {
                        // 判断需要显示的fragment是否需要刷新，如果需要刷新，调用attach方法。
                        ft.attach(newTab.fragment);
                    } else {
                        // 判断需要显示的fragment是否需要刷新，不需要刷新，调用show方法。
                        ft.show(newTab.fragment);
                    }
                }
            }

            mLastTab = newTab;
            ft.commit();
            mActivity.getSupportFragmentManager().executePendingTransactions();

        }

        if (mTabChangeListener != null) {
            mTabChangeListener.tabChange(tabId);
        }
    }

    public void setTabChangeListener(TabChangeListener mTabChangeListener) {
        this.mTabChangeListener = mTabChangeListener;
    }

    public Fragment getCurrentFrag() {
        return mLastTab.fragment;
    }

    public String getCurrentTag() {
        return mLastTab.tag;
    }

    /**
     * 添加Fragment到是否可刷新的Map
     *
     * @param str
     * @param bool
     */
    public void addToRefreshMap(String str, boolean bool) {
        if (str == null) {
            throw new IllegalArgumentException("参数错误");
        }
        refreshMap.put(str, bool);
    }

    /**
     * 判断当前str的Fragment是否可刷新，默认不可刷新
     *
     * @param str
     * @return
     */
    public boolean ifRefresh(String str) {
        boolean flush = false;
        if (refreshMap.containsKey(str)) {
            flush = refreshMap.get(str);
        }
        return flush;
    }

}