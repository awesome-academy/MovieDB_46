package com.sun.tino.hottrailers.utils;

import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sun.tino.hottrailers.R;
import com.sun.tino.hottrailers.data.model.Actor;
import com.sun.tino.hottrailers.data.model.Company;
import com.sun.tino.hottrailers.data.model.Genre;
import com.sun.tino.hottrailers.data.model.Movie;
import com.sun.tino.hottrailers.data.model.Video;
import com.sun.tino.hottrailers.ui.home.adapters.CategoryAdapter;
import com.sun.tino.hottrailers.ui.home.adapters.GenreAdapter;
import com.sun.tino.hottrailers.ui.home.adapters.MovieAdapter;
import com.sun.tino.hottrailers.ui.movie_detail.cast.CastAdapter;
import com.sun.tino.hottrailers.ui.movie_detail.producer.ProducerAdapter;
import com.sun.tino.hottrailers.ui.movie_detail.trailer.TrailerAdapter;

import java.util.List;

public class BindingUtils {
    @BindingAdapter("setSplashAnimation")
    public static void setSplashAnimation(ImageView imageView, boolean isSet) {
        if (isSet) {
            imageView.setAnimation(
                    AnimationUtils.loadAnimation(imageView.getContext(), R.anim.animation));
        }
    }

    @BindingAdapter("bindImage")
    public static void bindImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(StringUtils.getImageUrl(url))
                .centerCrop()
                .fallback(R.drawable.image_loading)
                .error(R.drawable.no_image)
                .into(imageView);
    }

    @BindingAdapter("bindSmallImage")
    public static void bindImageSmall(ImageView imageView, String image_path) {
        Glide.with(imageView)
                .load(StringUtils.getSmallImage(image_path))
                .thumbnail(Glide.with(imageView).load(R.drawable.image_loading))
                .error(R.drawable.no_image)
                .into(imageView);
    }

    @BindingAdapter("bindCategoryMovies")
    public static void bindCategoryMovies(RecyclerView recycler, ObservableList<Movie> movies) {
        MovieAdapter adapter = (MovieAdapter) recycler.getAdapter();
        if (adapter != null) {
            adapter.update(movies);
        }
    }

    @BindingAdapter(value = {"bindCategoryMovie", "bindCategoryTitle"}, requireAll = false)
    public static void bindRecyclerCategories(RecyclerView recycler,
                                              ObservableList<ObservableList<Movie>> movies,
                                              ObservableList<String> categories) {
        CategoryAdapter adapter = (CategoryAdapter) recycler.getAdapter();
        if (adapter != null) {
            adapter.update(movies, categories);
        }
    }

    @BindingAdapter("bindTrailers")
    public static void bindTrailers(RecyclerView recyclerView, List<Video> videos) {
        TrailerAdapter adapter = (TrailerAdapter) recyclerView.getAdapter();
        if (adapter != null) adapter.setVideos(videos);
    }

    @BindingAdapter("youTubeThumbnailView")
    public static void setYouTubeThumbnailView(ImageView thumbnailView,
                                               String trailerKey) {
        Glide.with(thumbnailView)
                .load(StringUtils.getThumbnail(trailerKey))
                .thumbnail(Glide.with(thumbnailView).load(R.drawable.image_prepare))
                .error(R.drawable.no_image)
                .into(thumbnailView);
    }

    @BindingAdapter("bindGenres")
    public static void bindGenres(RecyclerView recyclerView, List<Genre> genres) {
        GenreAdapter adapter = (GenreAdapter) recyclerView.getAdapter();
        if (adapter != null) adapter.update(genres);
    }

    @BindingAdapter("bindCasts")
    public static void setAdapterRecyclerCasts(RecyclerView recyclerView, List<Actor> actors) {
        CastAdapter adapter = (CastAdapter) recyclerView.getAdapter();
        if (adapter != null) adapter.setActors(actors);
    }

    @BindingAdapter("bindProducers")
    public static void setAdapterRecyclerProducers(RecyclerView recyclerView, List<Company> companies) {
        ProducerAdapter adapter = (ProducerAdapter) recyclerView.getAdapter();
        if (adapter != null) adapter.setCompanies(companies);
    }
}
