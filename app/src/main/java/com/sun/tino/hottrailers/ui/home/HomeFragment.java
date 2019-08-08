package com.sun.tino.hottrailers.ui.home;

import com.sun.tino.hottrailers.R;
import com.sun.tino.hottrailers.base.BaseFragment;
import com.sun.tino.hottrailers.base.BaseViewModel;

public class HomeFragment extends BaseFragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected BaseViewModel getViewModel() {
        return null;
    }

    @Override
    protected int getVariables() {
        return 0;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }
}
