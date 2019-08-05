package com.sun.tino.hottrailers.sevice;

import android.content.Context;

public class NetworkService {
    private static RequestApi sRequestApi;

    public static RequestApi getInstance(Context context) {
        if (sRequestApi == null) {
            sRequestApi = RetrofitBuilder.getInstance(context).create(RequestApi.class);
        }
        return sRequestApi;
    }
}
