package com.sun.tino.hottrailers.ui.movie_detail.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import com.sun.tino.hottrailers.R;
import com.sun.tino.hottrailers.data.model.Genre;
import com.sun.tino.hottrailers.databinding.FragmentMovieInfoBinding;
import com.sun.tino.hottrailers.ui.home.adapters.GenreAdapter;
import com.sun.tino.hottrailers.ui.movie_detail.MovieDetailViewModel;

public class MovieInfoFragment extends Fragment implements GenreAdapter.GenreListener {
    private FragmentMovieInfoBinding mBinding;
    private MovieDetailViewModel mViewModel;

    public MovieInfoFragment() {
        // Required empty public constructor
    }

    public static MovieInfoFragment newInstance() {
        return new MovieInfoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater
            , @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate
                (inflater, R.layout.fragment_movie_info, container, false);
        mBinding.setViewModel(mViewModel);
        initGenresView();
        return mBinding.getRoot();
    }

    private void initGenresView() {
        mBinding.recyclerGenre.setAdapter(new GenreAdapter(getContext(), this));
    }

    public MovieDetailViewModel getViewModel() {
        return mViewModel;
    }

    public void setViewModel(MovieDetailViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void OnGenreClickListener(Genre genre) {
        Toast.makeText(getContext(), genre.getName(), Toast.LENGTH_SHORT).show();
    }
}
