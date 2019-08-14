package com.sun.tino.hottrailers.ui.main;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sun.tino.hottrailers.R;
import com.sun.tino.hottrailers.utils.IPager;

import static com.sun.tino.hottrailers.utils.IPager.FRAGMENT_FAVORITE;
import static com.sun.tino.hottrailers.utils.IPager.FRAGMENT_HOME;
import static com.sun.tino.hottrailers.utils.IPager.FRAGMENT_SEARCH;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.
        OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    private ViewPager mViewPager;
    private BottomNavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.TransparentStatusTheme);
        setContentView(R.layout.activity_main);
        initViews();
        initViewPager();
        registerEvents();
    }

    private void initViews() {
        mViewPager = findViewById(R.id.view_pager);
        mNavigationView = findViewById(R.id.bottom_navigation);
    }

    private void initViewPager() {
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager()
                , PagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.addOnPageChangeListener(this);
    }

    private void registerEvents() {
        mNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public void onPageSelected(@IPager int position) {
        switch (position) {
            case FRAGMENT_HOME:
                mNavigationView.setSelectedItemId(R.id.home_fragment);
                break;
            case FRAGMENT_FAVORITE:
                mNavigationView.setSelectedItemId(R.id.favorite_fragment);
                break;
            case FRAGMENT_SEARCH:
                mNavigationView.setSelectedItemId(R.id.search_fragment);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.home_fragment:
                mViewPager.setCurrentItem(FRAGMENT_HOME);
                return true;

            case R.id.favorite_fragment:
                mViewPager.setCurrentItem(FRAGMENT_FAVORITE);
                return true;

            case R.id.search_fragment:
                mViewPager.setCurrentItem(FRAGMENT_SEARCH);
                return true;

            default:
                return false;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }
}
