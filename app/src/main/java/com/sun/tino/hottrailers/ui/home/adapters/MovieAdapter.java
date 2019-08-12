package com.sun.tino.hottrailers.ui.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;

import com.sun.tino.hottrailers.R;
import com.sun.tino.hottrailers.data.model.Movie;
import com.sun.tino.hottrailers.databinding.ItemVerticalMovieBinding;
import com.sun.tino.hottrailers.ui.home.ItemMovieViewModel;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private ObservableList<Movie> mMovies;
    private LayoutInflater mInflater;

    MovieAdapter(Context context) {
        mMovies = new ObservableArrayList<>();
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemVerticalMovieBinding binding = DataBindingUtil.inflate(mInflater,
                R.layout.item_vertical_movie, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mMovies.get(i));
    }

    @Override
    public int getItemCount() {
        return mMovies != null ? mMovies.size() : 0;
    }

    public void update(List<Movie> movies) {
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemVerticalMovieBinding mBinding;

        ViewHolder(ItemVerticalMovieBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bindData(Movie movie) {
            mBinding.setViewModel(new ItemMovieViewModel());
            mBinding.getViewModel().setMovie(movie);
            mBinding.executePendingBindings();
        }
    }
}
