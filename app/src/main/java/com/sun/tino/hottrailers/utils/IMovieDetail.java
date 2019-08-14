package com.sun.tino.hottrailers.utils;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.sun.tino.hottrailers.utils.IMovieDetail.CAST;
import static com.sun.tino.hottrailers.utils.IMovieDetail.INFO;
import static com.sun.tino.hottrailers.utils.IMovieDetail.PRODUCER;
import static com.sun.tino.hottrailers.utils.IMovieDetail.TRAILER;

@IntDef({INFO, TRAILER, CAST, PRODUCER})

@Retention(RetentionPolicy.SOURCE)
public @interface IMovieDetail {
    int INFO = 0;
    int TRAILER = 1;
    int CAST = 2;
    int PRODUCER = 3;
}
