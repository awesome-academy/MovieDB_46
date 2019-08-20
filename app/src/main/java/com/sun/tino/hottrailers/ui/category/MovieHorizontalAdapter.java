package com.sun.tino.hottrailers.ui.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sun.tino.hottrailers.R;
import com.sun.tino.hottrailers.data.model.Movie;
import com.sun.tino.hottrailers.databinding.ItemHorizontalMovieBinding;
import com.sun.tino.hottrailers.ui.home.adapters.MovieAdapter;

import java.util.List;

public class MovieHorizontalAdapter extends RecyclerView.Adapter<MovieHorizontalAdapter.ViewHolder> {
    private List<Movie> mMovies;
    private MovieAdapter.MovieListener mListener;
    private LayoutInflater mInflater;

    public MovieHorizontalAdapter(Context context, List<Movie> movies
            , MovieAdapter.MovieListener listener) {
        mMovies = movies;
        mListener = listener;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHorizontalMovieBinding binding = DataBindingUtil.inflate
                (mInflater, R.layout.item_horizontal_movie, parent, false);
        return new ViewHolder(binding, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies != null ? mMovies.size() : 0;
    }

    public void addMovies(List<Movie> movies) {
        int position = mMovies.size();
        mMovies.addAll(movies);
        notifyItemInserted(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemHorizontalMovieBinding mBinding;
        private Movie mMovie;
        private ItemMovieHorizontalViewModel mViewModel;
        private MovieAdapter.MovieListener mListener;

        ViewHolder(ItemHorizontalMovieBinding binding, MovieAdapter.MovieListener movieListener) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = new ItemMovieHorizontalViewModel();
            mListener = movieListener;
            mBinding.setViewModel(mViewModel);
            mBinding.getRoot().setOnClickListener(v -> mListener.OnMovieClickListener(mMovie));
        }

        private void bindData(Movie movie) {
            mMovie = movie;
            mViewModel.movie.set(movie);
        }
    }
}
