package com.zph.cins.adpter;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.zph.cins.defind.Constants;
import com.zph.cins.frag.FragHome;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author zph
 * @date 2018/3/22
 */

public class BaseMainFragmentAdapter extends FragmentPagerAdapter {

    private static final String TAG = BaseMainFragmentAdapter.class.getSimpleName();
    private ArrayList<HashMap<String, String>> categoryList;
    private int categoryType;
    private FragmentTransaction mCurTransaction;
    private FragmentManager mFragmentManager;

    private boolean isDestroy = false;

    public BaseMainFragmentAdapter(FragmentManager fm, ArrayList<HashMap<String, String>> categoryList, int categoryType) {
        super(fm);
        mFragmentManager = fm;
        this.categoryList = categoryList;
        this.categoryType = categoryType;
    }

    public boolean isDestroy() {
        return isDestroy;
    }

    public void setDestroy(boolean destroy) {
        isDestroy = destroy;
    }

    @Override
    public Fragment getItem(int position) {
        return buildFragmentItem(categoryType, position);
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return categoryList.get(position).get(Constants.CATGORY_TITLE);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private Fragment buildFragmentItem(int type, int position) {
        HashMap<String, String> category = categoryList.get(position);
        int typeId = Integer.valueOf(category.get(Constants.CATGORY_TYPE));
        if (type == Constants.HOME) {
            return new FragHome();
        } else if (type == Constants.INFO) {

        }
        return new Fragment();
    }

    @SuppressLint("CommitTransaction")
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (isDestroy) {
            if (mCurTransaction == null) {
                mCurTransaction = mFragmentManager.beginTransaction();
            }
            mCurTransaction.remove((Fragment) object);
        } else {
            super.destroyItem(container, position, object);
        }

    }

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);
        if (isDestroy) {
            if (mCurTransaction != null) {
                mCurTransaction.commitNowAllowingStateLoss();
                mCurTransaction = null;
            }
        }
    }
}
