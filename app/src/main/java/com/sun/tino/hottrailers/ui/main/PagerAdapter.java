package com.sun.tino.hottrailers.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.sun.tino.hottrailers.ui.favorite.FavoriteFragment;
import com.sun.tino.hottrailers.ui.home.HomeFragment;
import com.sun.tino.hottrailers.ui.search.SearchFragment;
import com.sun.tino.hottrailers.utils.IPager;

import static com.sun.tino.hottrailers.utils.IPager.FRAGMENT_FAVORITE;
import static com.sun.tino.hottrailers.utils.IPager.FRAGMENT_HOME;
import static com.sun.tino.hottrailers.utils.IPager.FRAGMENT_SEARCH;

public class PagerAdapter extends FragmentPagerAdapter {
    private static final int SUM_FRAGMENT = 3;

    PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(@IPager int position) {
        switch (position) {
            case FRAGMENT_HOME:
                return HomeFragment.newInstance();
            case FRAGMENT_FAVORITE:
                return FavoriteFragment.newInstance();
            case FRAGMENT_SEARCH:
                return SearchFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return SUM_FRAGMENT;
    }
}
