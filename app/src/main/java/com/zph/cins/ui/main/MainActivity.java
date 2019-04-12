package com.zph.cins.ui.main;

import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.devbrackets.android.exomedia.util.ResourceUtil;
import com.zph.cins.R;
import com.zph.cins.base.MvpActivity;
import com.zph.cins.defind.CONST_QUERY;
import com.zph.cins.frag.FragHome;
import com.zph.cins.utils.FragmentUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView, TabChangeListener {
    private final int TAB_HOME=0;
    private final int TAB_INFO=1;
    private final int TAB_MORE=2;
    @Inject
    MainPresenter mainPresenter;

    @BindView(R.id.fab_search)
    FloatingActionButton mFabSearch;

    @BindView(android.R.id.tabhost)
    TabHost mTabHost;

    private MainFrameTabManager mTabManager;


    private int mSelectIndex;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        initView(savedInstanceState);
    }
    /**
     * 初始化界面
     */
    private void init() {
        mTabHost.setup();

        mTabManager = new MainFrameTabManager(this, mTabHost,
                R.id.tab_real_content);
        mTabManager.setTabChangeListener(this);
        Class<?> homePageFragmentClass = FragHome.class;//HomePageFragment.class;
        Class<?> yztFragmentClass = FragHome.class;//HomePageFragment.class;
        Class<?> InfoFragmentClass = FragHome.class;//RvLkListFragment.class;
        Class<?> EventFragmentClass = FragHome.class;//MatterListMatterFragment.class;
        Class<?> MineFragmentClass = FragHome.class;//MineFragment.class;

        Bundle bundle = new Bundle();
        mTabManager.addTab(
                mTabHost.newTabSpec(getString(R.string.home_home)).setIndicator(
                        createCustomTabView(getString(R.string.home_home), R.drawable.main_homepage_drawable)), homePageFragmentClass,
                bundle);
        mTabManager.addTab(
                mTabHost.newTabSpec(getString(R.string.home_search)).setIndicator(
                        createCustomTabView(getString(R.string.home_search), R.drawable.search_homepage_drawable)), yztFragmentClass,
                bundle);
        mTabManager.addTab(
                mTabHost.newTabSpec(getString(R.string.home_add)).setIndicator(
                        createCustomTabView(getString(R.string.home_add), R.drawable.ic_home_add_svg)), InfoFragmentClass,
                bundle);
        mTabManager.addTab(
                mTabHost.newTabSpec(getString(R.string.home_more)).setIndicator(
                        createCustomTabView(getString(R.string.home_more), R.drawable.xin_homepage_drawable)), EventFragmentClass,
                bundle);


        mTabManager.addTab(
                mTabHost.newTabSpec(getString(R.string.home_me)).setIndicator(
                        createCustomTabView(getString(R.string.home_me), R.drawable.me_homepage_drawable)), MineFragmentClass,
                bundle);

    }
    private void initView(Bundle savedInstanceState) {
        mSelectIndex = getIntent().getIntExtra(CONST_QUERY.KEY_SELECT_INDEX, 0);
        if (savedInstanceState != null) {
            mSelectIndex = savedInstanceState.getInt(CONST_QUERY.KEY_SELECT_INDEX);
        }
        mTabManager.onTabChanged(getString(R.string.home_home));
        mTabHost.setCurrentTab(0);
        mTabHost.invalidate();

        mFabSearch.setOnClickListener(this);
    }



    @NonNull
    @Override
    public MainPresenter createPresenter() {
        getActivityComponent().inject(this);
        return mainPresenter;
    }

    @Override
    public void showLoading(boolean pullToRefresh) {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void showMessage(String msg, int type) {

    }

    @Override
    public void showError(String message) {

    }



    private void showFloatingActionButton(final FloatingActionButton fabSearch) {
        fabSearch.show(new FloatingActionButton.OnVisibilityChangedListener() {
            @Override
            public void onShown(FloatingActionButton fab) {
                fabSearch.requestLayout();
            }
        });
    }
    private void hideFloatingActionButton(FloatingActionButton fabSearch) {
        ViewGroup.LayoutParams layoutParams = fabSearch.getLayoutParams();
        if (layoutParams != null && layoutParams instanceof CoordinatorLayout.LayoutParams) {
            CoordinatorLayout.LayoutParams coLayoutParams = (CoordinatorLayout.LayoutParams) layoutParams;
            FloatingActionButton.Behavior behavior = new FloatingActionButton.Behavior();
            coLayoutParams.setBehavior(behavior);
        }
        fabSearch.hide();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_search:
                doOnFloatingActionButtonClick(mSelectIndex);
                break;
        }
    }

    public void doOnFloatingActionButtonClick(@IntRange(from = 0, to = 2) int position) {
        switch (position) {
            case TAB_HOME:
                break;
            case TAB_INFO:
                break;
            case TAB_MORE:
                break;
            default:
        }
    }

    @Override
    public void tabChange(String tabId) {
        Fragment currentFrag = mTabManager.getCurrentFrag();
        if (currentFrag != null) {
        }
    }

    /**
     * 创建底部tabitem的图片以及内容，其中图片为传入的path对应的图片
     *
     * @param txt 显示的内容
     *            图片地址
     * @return 创建出来的tabview
     */
    public View createCustomTabView(String txt, int id) {
        LinearLayout linearLayout = new LinearLayout(this);
        View view = LayoutInflater.from(this).inflate(R.layout.mainframe_tab_item, linearLayout);
        ImageView tabImg = view.findViewById(R.id.mainframe_tab_item_img);
        tabImg.setImageResource(id);
        return view;
    }

}
