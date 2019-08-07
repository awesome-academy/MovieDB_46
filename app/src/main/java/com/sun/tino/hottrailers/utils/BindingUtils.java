package com.sun.tino.hottrailers.utils;

import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.sun.tino.hottrailers.R;

public class BindingUtils {
    @BindingAdapter("setSplashAnimation")
    public static void setSplashAnimation(ImageView imageView, boolean isSet){
        if(isSet){
            imageView.setAnimation(
                    AnimationUtils.loadAnimation(imageView.getContext(), R.anim.animation));
        }
    }
}
