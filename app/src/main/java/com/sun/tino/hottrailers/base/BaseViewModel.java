package com.sun.tino.hottrailers.base;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {
    private BaseRepository mBaseRepository;
    private CompositeDisposable mCompositeDisposable;

    public BaseViewModel(BaseRepository baseRepository) {
        mBaseRepository = baseRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.dispose();
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }
}
