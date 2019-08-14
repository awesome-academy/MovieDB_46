package com.sun.tino.hottrailers.ui.movie_detail.cast;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sun.tino.hottrailers.R;
import com.sun.tino.hottrailers.data.model.Actor;
import com.sun.tino.hottrailers.databinding.ItemCastBinding;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder> {
    private List<Actor> mActors;

    public CastAdapter(List<Actor> actors) {
        mActors = actors;
    }

    public void setActors(List<Actor> actors) {
        mActors = actors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemCastBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.item_cast, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mActors.get(i));
    }

    @Override
    public int getItemCount() {
        return mActors == null ? 0 : mActors.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemCastBinding mBinding;
        private ItemCastViewModel mViewModel;

        ViewHolder(ItemCastBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = new ItemCastViewModel();
            mBinding.setViewModel(mViewModel);
        }

        void bindData(Actor actor) {
            mViewModel.actor.set(actor);
        }
    }
}
