package com.sun.tino.hottrailers.ui.favorite;

import com.sun.tino.hottrailers.R;
import com.sun.tino.hottrailers.base.BaseFragment;
import com.sun.tino.hottrailers.base.BaseViewModel;

public class FavoriteFragment extends BaseFragment {

    public FavoriteFragment() {
        // Required empty public constructor
    }

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
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
        return R.layout.fragment_favorite;
    }

}
