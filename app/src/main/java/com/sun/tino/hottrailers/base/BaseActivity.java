package com.sun.tino.hottrailers.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseActivity<VB extends ViewDataBinding, VM extends BaseViewModel>
        extends AppCompatActivity {
    private VB mViewDataBinding;
    private VM mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = (mViewModel == null) ? getViewModel() : mViewModel;
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutResource());
        mViewDataBinding.setVariable(getVariables(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }

    @LayoutRes
    protected abstract int getLayoutResource();

    protected abstract int getVariables();

    protected abstract VM getViewModel();

    public VB getViewDataBinding() {
        return mViewDataBinding;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.onCleared();
    }
}
