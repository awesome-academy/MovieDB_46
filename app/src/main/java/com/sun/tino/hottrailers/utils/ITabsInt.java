package com.sun.tino.hottrailers.utils;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.sun.tino.hottrailers.utils.ITabsInt.CAST;
import static com.sun.tino.hottrailers.utils.ITabsInt.INFO;
import static com.sun.tino.hottrailers.utils.ITabsInt.PRODUCER;
import static com.sun.tino.hottrailers.utils.ITabsInt.TRAILER;

@IntDef({INFO, TRAILER, CAST, PRODUCER})
@Retention(RetentionPolicy.SOURCE)
public @interface ITabsInt {
    int INFO = 0;
    int TRAILER = 1;
    int CAST = 2;
    int PRODUCER = 3;
}
