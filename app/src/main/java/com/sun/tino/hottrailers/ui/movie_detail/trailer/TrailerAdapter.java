package com.sun.tino.hottrailers.ui.movie_detail.trailer;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sun.tino.hottrailers.R;
import com.sun.tino.hottrailers.data.model.Video;
import com.sun.tino.hottrailers.databinding.ItemTrailerBinding;
import com.sun.tino.hottrailers.ui.movie_detail.OnTrailerListener;

import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {
    private List<Video> mVideos;
    private OnTrailerListener mListener;

    TrailerAdapter(List<Video> videos, OnTrailerListener listener) {
        mVideos = videos;
        mListener = listener;
    }

    public void setVideos(List<Video> videos) {
        mVideos = videos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemTrailerBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.item_trailer, viewGroup, false);
        return new ViewHolder(binding, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mVideos.get(i));
    }

    @Override
    public int getItemCount() {
        return mVideos == null ? 0 : mVideos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemTrailerViewModel mViewModel;
        private ItemTrailerBinding mBinding;
        private OnTrailerListener mListener;

        ViewHolder(ItemTrailerBinding binding, OnTrailerListener listener) {
            super(binding.getRoot());
            mBinding = binding;
            mListener = listener;
            mViewModel = new ItemTrailerViewModel();
            mBinding.setViewModel(mViewModel);
            mBinding.getRoot().setOnClickListener(v -> mListener
                    .onPlayTrailer(mBinding.getViewModel().video.get().getKey()));
        }

        void bindData(Video video) {
            mViewModel.video.set(video);
        }
    }
}
