package com.sun.tino.hottrailers.utils;

import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.sun.tino.hottrailers.R;

public class BindingUtils {
    @BindingAdapter("setSplashAnimation")
    public static void setSplashAnimation(ImageView imageView, boolean isSet){
        if(isSet){
            imageView.setAnimation(
                    AnimationUtils.loadAnimation(imageView.getContext(), R.anim.animation));
        }
    }

    @BindingAdapter("bindImage")
    public static void bindImage(ImageView imageView, String url){
        Glide.with(imageView.getContext())
                .load(StringUtils.getImageUrl(url))
                .centerCrop()
                .fallback(R.drawable.image_loading)
                .error(R.drawable.no_image)
                .into(imageView);
    }
}
