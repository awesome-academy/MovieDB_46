package com.sun.tino.hottrailers.ui.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
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
    private MovieListener mListener;


    MovieAdapter(Context context, MovieListener listener) {
        mMovies = new ObservableArrayList<>();
        mInflater = LayoutInflater.from(context);
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemVerticalMovieBinding binding = DataBindingUtil.inflate(mInflater,
                R.layout.item_vertical_movie, viewGroup, false);
        return new ViewHolder(binding, mListener);
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

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemVerticalMovieBinding mBinding;
        private MovieListener mListener;

        ViewHolder(ItemVerticalMovieBinding binding, MovieListener listener) {
            super(binding.getRoot());
            mListener = listener;
            mBinding = binding;
        }

        void bindData(Movie movie) {
            mBinding.setViewModel(new ItemMovieViewModel());
            if (mBinding.getViewModel() != null) {
                mBinding.getViewModel().setMovie(movie);
            }
            mBinding.itemMovie.setOnClickListener(this);
            mBinding.executePendingBindings();
        }

        @Override
        public void onClick(View view) {
            if (mBinding.getViewModel() != null) {
                mListener.OnMovieClickListener(mBinding.getViewModel().getMovie());
            }
        }
    }

    public interface MovieListener{
        void OnMovieClickListener(Movie movie);
    }
}
