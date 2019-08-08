package com.sun.tino.hottrailers.sevice;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;

import com.sun.tino.hottrailers.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    private static final String QUERY_PARAMETER_API_KEY = "api_key";
    private static final String API_KEY = BuildConfig.API_KEY;
    //API BASE URL
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    //Set timeout
    private static final int READ_TIMEOUT = 5000;
    private static final int WRITE_TIMEOUT = 5000;
    private static final int CONNECT_TIMEOUT = 5000;

    //Set cache {size: 10MB, time cache online: 1 min, time cache offline: 1 day}
    private static String CACHE_CONTROL = "Cache-Control";
    private static final long CACHE_SIZE = 10 * 1024 * 1024;
    private static final String TIME_CACHE_ONLINE = "public, max-age = 60";
    private static final String TIME_CACHE_OFFLINE = "public, only-if-cached, max-stale = 86400";

    private static Retrofit sRetrofit;

    public static Retrofit getInstance(Context context) {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .client(initClient(context))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }

    //Check for network available
    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    //Client
    private static OkHttpClient initClient(final Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .cache(new Cache(context.getCacheDir(), CACHE_SIZE))
                .addInterceptor(new Interceptor() {
                    @NonNull
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request request = chain.request();
                        if (isNetworkAvailable(context)) {
                            request = request.newBuilder()
                                    .header(CACHE_CONTROL, TIME_CACHE_ONLINE).build();
                        } else {
                            request = request.newBuilder()
                                    .header(CACHE_CONTROL, TIME_CACHE_OFFLINE).build();
                        }
                        HttpUrl httpUrl = request.url()
                                .newBuilder()
                                .addQueryParameter(QUERY_PARAMETER_API_KEY, API_KEY)
                                .build();
                        Request.Builder requestBuilder = request
                                .newBuilder()
                                .url(httpUrl);
                        return chain.proceed(requestBuilder.build());
                    }
                });
        //Debug API
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        return builder.build();
    }
}
