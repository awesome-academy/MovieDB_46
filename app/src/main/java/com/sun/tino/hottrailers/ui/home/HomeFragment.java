package com.sun.tino.hottrailers.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.appbar.AppBarLayout;
import com.sun.tino.hottrailers.BR;
import com.sun.tino.hottrailers.R;
import com.sun.tino.hottrailers.base.BaseFragment;
import com.sun.tino.hottrailers.databinding.FragmentHomeBinding;
import com.sun.tino.hottrailers.ui.home.adapters.CategoryAdapter;
import com.sun.tino.hottrailers.ui.home.adapters.GenreAdapter;
import com.sun.tino.hottrailers.ui.home.adapters.SlideAdapter;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {

    private static final CharSequence TITTLE_SPACE = " ";
    private static final int DEFAULT_SCROLL_RANGE = -1;
    private static final int NUM_SLIDE = 5;
    private static final long PERIOD_TIME_SLIDE = 5000;
    private static final long DELAY_TIME_SLIDE = 100;

    private FragmentHomeBinding mHomeBinding;
    private HomeViewModel mHomeViewModel;
    private SlideAdapter mSlideAdapter;
    private GenreAdapter mGenreAdapter;
    private int mCurrentSlide;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHomeBinding = getViewBinding();
        hideExpandedTittle();
        initAdapters();
    }

    @Override
    protected HomeViewModel getViewModel() {
        mHomeViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance
                (Objects.requireNonNull(getActivity()).getApplication()).create(HomeViewModel.class);
        mHomeViewModel.initViewModel(getContext());
        observeMoviesAdapter();
        return mHomeViewModel;
    }

    private void observeMoviesAdapter() {
        mHomeViewModel.getTopTrendingMovies()
                .observe(this, movies -> mSlideAdapter.update(movies));
        mHomeViewModel.getGenres().observe(this, genres -> mGenreAdapter.update(genres));
    }

    private void initAdapters() {
        mSlideAdapter = new SlideAdapter(getContext());
        mGenreAdapter = new GenreAdapter(getContext());
        mHomeBinding.viewPager.setAdapter(mSlideAdapter);
        mCurrentSlide = mSlideAdapter.getCurrentSlide();
        mHomeBinding.tabLayout.setupWithViewPager(mHomeBinding.viewPager, true);
        mHomeBinding.recyclerGenre.setAdapter(mGenreAdapter);
        mHomeBinding.recyclerGenre.setNestedScrollingEnabled(false);
        mHomeBinding.recyclerCategory.setAdapter(new CategoryAdapter(getContext()));
        mHomeBinding.recyclerCategory.setNestedScrollingEnabled(false);
        initTimerChangeSlide();
    }

    private void initTimerChangeSlide() {
        Handler handler = new Handler();
        Runnable update = () -> {
            if (mCurrentSlide == NUM_SLIDE) {
                mCurrentSlide = 0;
            }
            mHomeBinding.viewPager.setCurrentItem(mCurrentSlide++, true);
        };
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_TIME_SLIDE, PERIOD_TIME_SLIDE);
    }

    private void hideExpandedTittle() {
        mHomeBinding.appBar.addOnOffsetChangedListener(getAppBarListener());
    }

    private AppBarLayout.OnOffsetChangedListener getAppBarListener() {
        return new AppBarLayout.OnOffsetChangedListener() {
            int scrollRange = DEFAULT_SCROLL_RANGE;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == DEFAULT_SCROLL_RANGE) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    mHomeBinding.collapsingToolbar.setTitle(getString(R.string.app_name));
                } else {
                    mHomeBinding.collapsingToolbar.setTitle(TITTLE_SPACE);
                }
            }
        };
    }

    @Override
    protected int getVariables() {
        return BR.viewModel;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHomeViewModel.dispose();
    }
}
