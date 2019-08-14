package com.sun.tino.hottrailers.ui.favorite;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.sun.tino.hottrailers.R;
import com.sun.tino.hottrailers.base.BaseFragment;
import com.sun.tino.hottrailers.data.model.Movie;
import com.sun.tino.hottrailers.databinding.FragmentFavoriteBinding;
import com.sun.tino.hottrailers.ui.movie_detail.MovieDetailActivity;

import java.util.Objects;

import static com.sun.tino.hottrailers.ui.movie_detail.info.MovieInfoFragment.MSG_REMOVED;

public class FavoriteFragment extends BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>
        implements FavoriteAdapter.FavoriteListener {

    private FavoriteViewModel mFavoriteViewModel;
    private FragmentFavoriteBinding mFavoriteBinding;
    private FavoriteAdapter mFavoriteAdapter;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFavoriteBinding = getViewBinding();
        initAdapter();
    }

    private void initAdapter() {
        mFavoriteAdapter = new FavoriteAdapter(getContext(), this);
        mFavoriteBinding.recyclerFavorite.setAdapter(mFavoriteAdapter);
    }

    @Override
    protected FavoriteViewModel getViewModel() {
        mFavoriteViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance
                (Objects.requireNonNull(getActivity()).getApplication()).create(FavoriteViewModel.class);
        mFavoriteViewModel.initViewModel(getContext());
        mFavoriteViewModel.getMovies().observe(this, movies ->
                mFavoriteAdapter.update(movies));
        return mFavoriteViewModel;
    }

    @Override
    protected int getVariables() {
        return 0;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_favorite;
    }

    @Override
    public void OnMovieClickListener(Movie movie) {
        startActivity(MovieDetailActivity.getIntent(getActivity(), movie.getId(), movie.getTitle()));
    }

    @Override
    public void OnFavoriteClickListener(Movie movie) {
        mFavoriteViewModel.deleteFromFavorite(movie);
        Toast.makeText(getContext(), MSG_REMOVED, Toast.LENGTH_SHORT).show();
    }
}
