package com.sun.tino.hottrailers.ui.search;

import com.sun.tino.hottrailers.R;
import com.sun.tino.hottrailers.base.BaseFragment;
import com.sun.tino.hottrailers.base.BaseViewModel;

public class SearchFragment extends BaseFragment {

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
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
        return R.layout.fragment_search;
    }
}
