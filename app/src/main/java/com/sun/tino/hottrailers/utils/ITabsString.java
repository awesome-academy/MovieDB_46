package com.sun.tino.hottrailers.utils;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.sun.tino.hottrailers.utils.ITabsString.TITLE_CAST;
import static com.sun.tino.hottrailers.utils.ITabsString.TITLE_INFO;
import static com.sun.tino.hottrailers.utils.ITabsString.TITLE_PRODUCER;
import static com.sun.tino.hottrailers.utils.ITabsString.TITLE_TRAILER;

@StringDef({TITLE_INFO, TITLE_TRAILER, TITLE_CAST, TITLE_PRODUCER})
@Retention(RetentionPolicy.SOURCE)
public @interface ITabsString {

    String TITLE_INFO = "Information";
    String TITLE_TRAILER = "Trailer";
    String TITLE_CAST = "Casts";
    String TITLE_PRODUCER = "Producer";
}
