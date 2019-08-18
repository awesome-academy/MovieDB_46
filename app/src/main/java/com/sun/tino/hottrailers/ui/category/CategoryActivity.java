package com.sun.tino.hottrailers.ui.category;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.sun.tino.hottrailers.base.BaseMoviesActivity;

import java.util.ArrayList;

public class CategoryActivity extends BaseMoviesActivity<MovieHorizontalViewModel, MovieHorizontalAdapter> {
    private static final String BUNDLE_CATEGORY_KEY = "BUNDLE_CATEGORY_KEY";
    public static final String STR_MOVIES = " Movies";

    @Override
    protected void initViewModel() {
        Bundle bundle = getIntent().getBundleExtra(EXTRA_ARGS);
        String categoryKey = bundle.getString(BUNDLE_CATEGORY_KEY);
        mActionBarTitle = bundle.getString(BUNDLE_ACTION_BAR_TITLE) + STR_MOVIES;
        mViewModel = new MovieHorizontalViewModel(this, this);
        mViewModel.setCategoryKey(categoryKey);
        mBinding.setViewModel(mViewModel);
        mViewModel.loadMoviesCategory(mViewModel.getPage());
    }

    @Override
    protected void initRecyclerAdapter() {
        mAdapter = new MovieHorizontalAdapter(this, new ArrayList<>(), this);
    }

    @Override
    protected void loadMoreMovies() {
        OnHideLoadMore(false);
        int nextPage = mViewModel.getPage();
        ++nextPage;
        mViewModel.loadMoviesCategory(nextPage);
    }

    public static Intent getIntent(Context context, String categoryKey, String categoryTitle) {
        Intent intent = new Intent(context, CategoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_CATEGORY_KEY, categoryKey);
        bundle.putString(BUNDLE_ACTION_BAR_TITLE, categoryTitle);
        intent.putExtra(EXTRA_ARGS, bundle);
        return intent;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.despose();
    }
}
