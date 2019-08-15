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
import com.sun.tino.hottrailers.databinding.ItemCategoryBinding;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private ObservableList<ObservableList<Movie>> mMovies;
    private ObservableList<String> mCategories;
    private LayoutInflater mInflater;

    public CategoryAdapter(Context context) {
        mMovies = new ObservableArrayList<>();
        mCategories = new ObservableArrayList<>();
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate
                (mInflater, R.layout.item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(mMovies.get(position), mCategories.get(position));
    }

    public void update(ObservableList<ObservableList<Movie>> movies,
                       ObservableList<String> categories) {
        mMovies = movies;
        mCategories = categories;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mMovies != null ? mMovies.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemCategoryBinding mBinding;

        ViewHolder(ItemCategoryBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.recyclerMovie.setAdapter(new MovieAdapter(mBinding.getRoot().getContext()));
            mBinding.recyclerMovie.setNestedScrollingEnabled(false);
        }

        void bindData(ObservableList<Movie> movies, String category) {
            mBinding.textCategory.setText(category);
            mBinding.setCategoryMovies(movies);
        }
    }
}
