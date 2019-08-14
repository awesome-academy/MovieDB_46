package com.sun.tino.hottrailers.ui.favorite;

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
import com.sun.tino.hottrailers.databinding.ItemFavoriteBinding;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private FavoriteListener mListener;
    private ObservableList<Movie> mMovies;

    public FavoriteAdapter(Context context, FavoriteListener listener) {
        mInflater = LayoutInflater.from(context);
        mListener = listener;
        mMovies = new ObservableArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFavoriteBinding binding = DataBindingUtil.inflate(mInflater, R.layout.item_favorite, parent, false);
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

    public void update(List<Movie> movies){
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemFavoriteBinding mBinding;
        private FavoriteListener mListener;
        private ItemFavoriteViewModel mViewModel;

        ViewHolder(ItemFavoriteBinding binding, FavoriteListener listener) {
            super(binding.getRoot());
            mViewModel = new ItemFavoriteViewModel();
            mBinding = binding;
            mBinding.setViewModel(mViewModel);
            mListener = listener;
            mBinding.imageClose.setOnClickListener(this);
            mBinding.itemFavorite.setOnClickListener(this);
        }

        void bindData(Movie movie) {
            mViewModel.movieFavorite.set(movie);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.image_close: mListener.OnFavoriteClickListener(mViewModel.movieFavorite.get());
                    break;
                case R.id.item_favorite: mListener.OnMovieClickListener(mViewModel.movieFavorite.get());
                    break;
                default:
                    break;
            }
        }
    }

    public interface FavoriteListener {
        void OnMovieClickListener(Movie movie);

        void OnFavoriteClickListener(Movie movie);
    }
}
