package com.sun.tino.hottrailers.ui.home.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.viewpager.widget.PagerAdapter;

import com.sun.tino.hottrailers.R;
import com.sun.tino.hottrailers.data.model.Movie;
import com.sun.tino.hottrailers.databinding.ItemSlideBinding;
import com.sun.tino.hottrailers.ui.home.ItemMovieViewModel;

import java.util.List;

public class SlideAdapter extends PagerAdapter {
    private ObservableList<Movie> mMovies;
    private ItemSlideBinding mBinding;
    private int mCurrentSlide;

    public SlideAdapter() {
        mMovies = new ObservableArrayList<>();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        mBinding = DataBindingUtil.inflate(inflater, R.layout.item_slide, container, true);
        if (mBinding.getViewModel() == null) {
            mBinding.setViewModel(new ItemMovieViewModel());
        }
        mBinding.getViewModel().setMovie(mMovies.get(position));
        mCurrentSlide = position;
        mBinding.executePendingBindings();
        return mBinding.getRoot();
    }

    public int getCurrentSlide() {
        return mCurrentSlide;
    }

    public void setCurrentSlide(int currentSlide) {
        mCurrentSlide = currentSlide;
    }

    public void update(List<Movie> movies) {
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    @Override
    public int getCount() {
        return mMovies != null ? mMovies.size() : 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
