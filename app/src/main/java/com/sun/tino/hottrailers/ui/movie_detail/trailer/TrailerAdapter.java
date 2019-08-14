package com.sun.tino.hottrailers.ui.movie_detail.trailer;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sun.tino.hottrailers.R;
import com.sun.tino.hottrailers.data.model.Video;
import com.sun.tino.hottrailers.databinding.ItemTrailerBinding;

import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {
    private List<Video> mVideos;

    public TrailerAdapter(List<Video> videos) {
        mVideos = videos;
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
        return new ViewHolder(binding);
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

        ViewHolder(ItemTrailerBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = new ItemTrailerViewModel();
            mBinding.setViewModel(mViewModel);
        }

        void bindData(Video video) {
            mViewModel.video.set(video);
        }
    }
}
