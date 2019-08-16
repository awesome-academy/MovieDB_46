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
import com.sun.tino.hottrailers.data.model.Genre;
import com.sun.tino.hottrailers.databinding.ItemGenreBinding;
import com.sun.tino.hottrailers.ui.home.ItemGenreViewModel;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {
    private ObservableList<Genre> mGenres;
    private LayoutInflater mInflater;
    private GenreListener mListener;


    public GenreAdapter(Context context, GenreListener listener) {
        mGenres = new ObservableArrayList<>();
        mInflater = LayoutInflater.from(context);
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemGenreBinding binding = DataBindingUtil
                .inflate(mInflater, R.layout.item_genre, viewGroup, false);
        return new ViewHolder(binding, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mGenres.get(i));
    }

    @Override
    public int getItemCount() {
        return mGenres == null ? 0 : mGenres.size();
    }

    public void update(List<Genre> genres) {
        mGenres.clear();
        mGenres.addAll(genres);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemGenreBinding mBinding;
        private ItemGenreViewModel mViewModel;
        private GenreListener mListener;

        ViewHolder(ItemGenreBinding binding, GenreListener listener) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = new ItemGenreViewModel();
            mBinding.setViewModel(mViewModel);
            mListener = listener;
            mBinding.itemGenre.setOnClickListener(this);
        }

        void bindData(Genre genre) {
            mViewModel.genre.set(genre);
        }

        @Override
        public void onClick(View view) {
            mListener.OnGenreClickListener(mBinding.getViewModel().genre.get());
        }
    }

    public interface GenreListener{
        void OnGenreClickListener(Genre genre);
    }
}
