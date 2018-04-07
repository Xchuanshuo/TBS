package com.legend.tbs.common.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.legend.tbs.fragment.FriendTbsFragment;
import com.legend.tbs.fragment.SelfTbsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Legend
 * @data by on 2018/4/5.
 * @description
 */

public class MainFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;
    private String[] titles = new String[]{"我收到的", "好友收到的"};

    public MainFragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        mFragments = new ArrayList<>();
        SelfTbsFragment selfTbsFragment = new SelfTbsFragment();
        FriendTbsFragment friendTbsFragment = new FriendTbsFragment();
        mFragments.add(selfTbsFragment);
        mFragments.add(friendTbsFragment);
    }
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
