package com.sun.tino.hottrailers.ui.movie_detail.trailer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.sun.tino.hottrailers.R;
import com.sun.tino.hottrailers.databinding.FragmentTrailerBinding;
import com.sun.tino.hottrailers.ui.movie_detail.MovieDetailViewModel;
import com.sun.tino.hottrailers.ui.movie_detail.OnTrailerListener;

import java.util.ArrayList;

public class TrailerFragment extends Fragment {
    private MovieDetailViewModel mViewModel;
    private FragmentTrailerBinding mBinding;
    private OnTrailerListener mListener;

    public TrailerFragment() {
        // Required empty public constructor
    }

    public static TrailerFragment newInstance() {
        return new TrailerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_trailer, container, false);
        mBinding.setViewModel(mViewModel);
        initRecyclerView();
        return mBinding.getRoot();
    }

    private void initRecyclerView() {
        mBinding.recyclerTrailer.setAdapter(new TrailerAdapter(new ArrayList<>(), mListener));
    }

    public MovieDetailViewModel getViewModel() {
        return mViewModel;
    }

    public void setViewModel(MovieDetailViewModel viewModel) {
        mViewModel = viewModel;
    }

    public void setListener(OnTrailerListener listener) {
        mListener = listener;
    }
}
