package com.sun.tino.hottrailers.ui.movie_detail.cast;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.sun.tino.hottrailers.R;
import com.sun.tino.hottrailers.databinding.FragmentCastBinding;
import com.sun.tino.hottrailers.ui.movie_detail.MovieDetailViewModel;

import java.util.ArrayList;

public class CastFragment extends Fragment {
    private MovieDetailViewModel mViewModel;
    private FragmentCastBinding mBinding;

    public CastFragment() {
        // Required empty public constructor
    }

    public static CastFragment newInstance() {
        return new CastFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_cast, container, false);
        mBinding.setViewModel(mViewModel);
        initRecyclerView();
        return mBinding.getRoot();
    }

    private void initRecyclerView() {
        mBinding.recyclerCasts.setAdapter(new CastAdapter(new ArrayList<>()));
    }

    public void setViewModel(MovieDetailViewModel viewModel) {
        mViewModel = viewModel;
    }
}
