package com.sun.tino.hottrailers.ui.movie_detail.producer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.sun.tino.hottrailers.R;
import com.sun.tino.hottrailers.databinding.FragmentProducerBinding;
import com.sun.tino.hottrailers.ui.movie_detail.MovieDetailViewModel;

import java.util.ArrayList;

public class ProducerFragment extends Fragment {
    private FragmentProducerBinding mBinding;
    private MovieDetailViewModel mViewModel;

    public ProducerFragment() {
        // Required empty public constructor
    }

    public static ProducerFragment newInstance() {
        return new ProducerFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_producer, container, false);
        mBinding.setViewModel(mViewModel);
        initRecyclerView();
        return mBinding.getRoot();
    }

    private void initRecyclerView() {
        mBinding.recyclerProducer.setAdapter(new ProducerAdapter(new ArrayList<>()));
    }

    public void setViewModel(MovieDetailViewModel viewModel) {
        mViewModel = viewModel;
    }
}
