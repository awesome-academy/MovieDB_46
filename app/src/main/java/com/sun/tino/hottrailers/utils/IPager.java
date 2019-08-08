package com.sun.tino.hottrailers.utils;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.sun.tino.hottrailers.utils.IPager.FRAGMENT_FAVORITE;
import static com.sun.tino.hottrailers.utils.IPager.FRAGMENT_HOME;
import static com.sun.tino.hottrailers.utils.IPager.FRAGMENT_SEARCH;

@IntDef({FRAGMENT_HOME, FRAGMENT_FAVORITE, FRAGMENT_SEARCH})

@Retention(RetentionPolicy.SOURCE)
public @interface IPager {
    int FRAGMENT_HOME = 0;
    int FRAGMENT_FAVORITE = 1;
    int FRAGMENT_SEARCH = 2;
}
