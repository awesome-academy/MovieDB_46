package com.sun.tino.hottrailers.ui.movie_detail.producer;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sun.tino.hottrailers.R;
import com.sun.tino.hottrailers.data.model.Company;
import com.sun.tino.hottrailers.databinding.ItemProducerBinding;

import java.util.List;

public class ProducerAdapter extends RecyclerView.Adapter<ProducerAdapter.ViewHolder> {
    private List<Company> mCompanies;

    ProducerAdapter(List<Company> companies) {
        mCompanies = companies;
    }

    public void setCompanies(List<Company> companies) {
        mCompanies = companies;
        notifyItemRangeChanged(0, mCompanies.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemProducerBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.item_producer, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mCompanies.get(i));
    }

    @Override
    public int getItemCount() {
        return mCompanies == null ? 0 : mCompanies.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemProducerViewModel mViewModel;
        private ItemProducerBinding mBinding;

        ViewHolder(ItemProducerBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = new ItemProducerViewModel();
            mBinding.setViewModel(mViewModel);
        }

        void bindData(Company company) {
            mViewModel.company.set(company);
        }
    }
}
