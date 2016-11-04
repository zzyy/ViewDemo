package com.zy.md.base.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Simon on 2016/11/3.
 */

public class NetRequest {
    private static GankApi sGankApi;
    private static OkHttpClient sOkHttpClient;

    public static GankApi getGankApi() {
        if (sGankApi == null) {
            sGankApi = getRetrofit(GankApi.BASE_URL).create(GankApi.class);
        }

        return sGankApi;
    }

    private static Retrofit getRetrofit(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .callFactory(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


        return retrofit;
    }

    private static OkHttpClient getOkHttpClient() {
        if (sOkHttpClient == null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

            sOkHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();
        }

        return sOkHttpClient;
    }


    static class HeadIntercept implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request request = originalRequest.newBuilder()
                    .addHeader("", "")
                    .build();

            return chain.proceed(request);
        }
    }
}
