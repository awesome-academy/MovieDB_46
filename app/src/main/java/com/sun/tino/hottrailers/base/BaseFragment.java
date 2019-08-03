package com.sun.tino.hottrailers.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<VB extends ViewDataBinding, VM extends BaseViewModel>
        extends Fragment {
    private VB mViewBinding;
    private VM mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = getViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
            @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewBinding = DataBindingUtil.inflate(inflater, getLayoutResource()
                , container, false);
        return mViewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewBinding.setVariable(getVariables(), mViewModel);
        mViewBinding.setLifecycleOwner(this);
        mViewBinding.executePendingBindings();
    }

    protected abstract VM getViewModel();

    protected abstract int getVariables();

    @LayoutRes
    protected abstract int getLayoutResource();

    public VB getViewBinding() {
        return mViewBinding;
    }
}
