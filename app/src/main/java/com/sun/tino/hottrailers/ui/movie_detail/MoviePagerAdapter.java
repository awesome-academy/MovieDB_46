package com.sun.tino.hottrailers.ui.movie_detail;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.sun.tino.hottrailers.utils.ITabsInt;
import com.sun.tino.hottrailers.utils.ITabsString;
import java.util.ArrayList;
import java.util.List;

public class MoviePagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;

    public MoviePagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        mFragments = new ArrayList<>();
    }

    public void addFragment(Fragment fragment) {
        mFragments.add(fragment);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(@ITabsInt int position) {
        switch (position) {
            case ITabsInt.INFO:
                return ITabsString.TITLE_INFO;
            case ITabsInt.CAST:
                return ITabsString.TITLE_CAST;
            case ITabsInt.PRODUCER:
                return ITabsString.TITLE_PRODUCER;
            case ITabsInt.TRAILER:
                return ITabsString.TITLE_TRAILER;
            default:
                return super.getPageTitle(position);
        }
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
