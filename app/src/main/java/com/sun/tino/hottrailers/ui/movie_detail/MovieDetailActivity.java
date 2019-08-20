package com.sun.tino.hottrailers.ui.movie_detail;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;

import com.sun.tino.hottrailers.R;
import com.sun.tino.hottrailers.databinding.ActivityMovieDetailBinding;
import com.sun.tino.hottrailers.ui.movie_detail.cast.CastFragment;
import com.sun.tino.hottrailers.ui.movie_detail.info.MovieInfoFragment;
import com.sun.tino.hottrailers.ui.movie_detail.producer.ProducerFragment;
import com.sun.tino.hottrailers.ui.movie_detail.trailer.TrailerFragment;
import com.sun.tino.hottrailers.utils.Constants;

import java.util.Objects;

public class MovieDetailActivity extends AppCompatActivity
        implements OnInternetListener, OnTrailerListener {
    private static final String EXTRA_MOVIE_DETAIL = "EXTRA_MOVIE_DETAIL";
    private static final String BUNDLE_MOVIE_ID = "BUNDLE_MOVIE_ID";
    private static final String BUNDLE_MOVIE_TITLE = "BUNDLE_MOVIE_TITLE";
    private int mMovieId;
    private String mMovieTitle;
    private MovieDetailViewModel mViewModel;
    private ActivityMovieDetailBinding mBinding;
    private YoutubeFragment mYoutubeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        mYoutubeFragment =
                (YoutubeFragment) getFragmentManager().findFragmentById(R.id.fragment_youtube);
        receiveData();
        initViewModel();
        initViewPager();
        initActionBar();
    }

    private void initActionBar() {
        setSupportActionBar(mBinding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(mMovieTitle);
        mBinding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        mBinding.toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void initViewModel() {
        mViewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication()).create(MovieDetailViewModel.class);
        mViewModel.initViewModel(this, this);
        mViewModel.setInternetListener(this);
        mViewModel.loadMovieDetail(mMovieId);
        //Check movie favorite
        mViewModel.getFavoriteMovie(mMovieId).observe(this, movie -> {
            mViewModel.isFavorite.set(movie != null);
        });
    }

    private void initViewPager() {
        MoviePagerAdapter pagerAdapter = new MoviePagerAdapter(getSupportFragmentManager()
                , FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        MovieInfoFragment infoFragment = MovieInfoFragment.newInstance();
        infoFragment.setViewModel(mViewModel);
        TrailerFragment trailerFragment = TrailerFragment.newInstance();
        trailerFragment.setViewModel(mViewModel);
        trailerFragment.setListener(this);
        CastFragment castFragment = CastFragment.newInstance();
        castFragment.setViewModel(mViewModel);
        ProducerFragment producerFragment = ProducerFragment.newInstance();
        producerFragment.setViewModel(mViewModel);

        pagerAdapter.addFragment(infoFragment);
        pagerAdapter.addFragment(trailerFragment);
        pagerAdapter.addFragment(castFragment);
        pagerAdapter.addFragment(producerFragment);
        mBinding.viewPager.setAdapter(pagerAdapter);
        mBinding.tabsMovieDetail.setupWithViewPager(mBinding.viewPager);
    }

    private void receiveData() {
        Bundle bundle = getIntent().getBundleExtra(EXTRA_MOVIE_DETAIL);
        mMovieId = bundle.getInt(BUNDLE_MOVIE_ID);
        mMovieTitle = bundle.getString(BUNDLE_MOVIE_TITLE);
    }

    public static Intent getIntent(Context context, int movieId, String movieTitle) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_MOVIE_ID, movieId);
        bundle.putString(BUNDLE_MOVIE_TITLE, movieTitle);
        intent.putExtra(EXTRA_MOVIE_DETAIL, bundle);
        return intent;
    }

    @Override
    protected void onDestroy() {
        mViewModel.destroy();
        super.onDestroy();
    }

    @Override
    public void onNoInternet() {
        Toast.makeText(this, Constants.NO_INTERNET, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mYoutubeFragment.setFullScreen(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE);
    }

    @Override
    public void onCreateTrailer(String mTrailerKey) {
        mYoutubeFragment.setTrailerId(mTrailerKey);
    }

    @Override
    public void onPlayTrailer(String mTrailerKey) {
        mYoutubeFragment.setTrailerId(mTrailerKey);
        mYoutubeFragment.playTrailer();
    }
}
