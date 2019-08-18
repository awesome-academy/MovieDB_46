package com.sun.tino.hottrailers.base;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sun.tino.hottrailers.R;
import com.sun.tino.hottrailers.data.model.Movie;
import com.sun.tino.hottrailers.databinding.ActivityCategoryBinding;
import com.sun.tino.hottrailers.ui.category.MovieHorizontalViewModel;
import com.sun.tino.hottrailers.ui.home.adapters.MovieAdapter;
import com.sun.tino.hottrailers.ui.movie_detail.MovieDetailActivity;
import com.sun.tino.hottrailers.ui.movie_detail.OnInternetListener;
import com.sun.tino.hottrailers.utils.Constants;

import java.util.Objects;

public abstract class BaseMoviesActivity<T, V extends RecyclerView.Adapter>
        extends AppCompatActivity implements MovieHorizontalViewModel.MovieHorizontalListener,
        OnInternetListener, MovieAdapter.MovieListener {
    public static final String EXTRA_ARGS = "EXTRA_ARGS";
    public static final String BUNDLE_ACTION_BAR_TITLE = "BUNDLE_ACTION_BAR_TITLE";
    protected String mActionBarTitle;
    protected T mViewModel;
    protected ActivityCategoryBinding mBinding;
    protected V mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_category);
        initViewModel();
        if (mViewModel instanceof MovieHorizontalViewModel) {
            ((MovieHorizontalViewModel) mViewModel).setOnInternetListener(this);
        }
        initActionBar();
        setUpRecycler();
    }

    protected abstract void initViewModel();

    protected void setUpRecycler() {
        initRecyclerAdapter();
        mBinding.recyclerMovies.setAdapter(mAdapter);

        mBinding.recyclerMovies.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager manager
                        = (LinearLayoutManager) mBinding.recyclerMovies.getLayoutManager();
                if (manager != null && manager
                        .findLastCompletelyVisibleItemPosition() == mAdapter.getItemCount() - 1) {
                    loadMoreMovies();
                }
            }
        });
    }

    protected abstract void initRecyclerAdapter();

    protected abstract void loadMoreMovies();

    private void initActionBar() {
        setSupportActionBar(mBinding.toolbarCategory);
        Objects.requireNonNull(getSupportActionBar()).setTitle(mActionBarTitle);
        mBinding.toolbarCategory.setNavigationIcon(R.drawable.ic_arrow_back);
        mBinding.toolbarCategory.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onNoInternet() {
        Toast.makeText(this, Constants.NO_INTERNET, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnMovieClickListener(Movie movie) {
        startActivity(MovieDetailActivity.getIntent(this, movie.getId(), movie.getTitle()));
    }

    @Override
    public void OnHideLoadAllData(boolean isHide) {
        if (isHide) mBinding.progressLoadData.setVisibility(View.GONE);
        else mBinding.progressLoadData.setVisibility(View.VISIBLE);
    }

    @Override
    public void OnHideLoadMore(boolean isHide) {
        if (isHide) mBinding.progressLoadMore.setVisibility(View.GONE);
        else mBinding.progressLoadMore.setVisibility(View.VISIBLE);
    }
}
