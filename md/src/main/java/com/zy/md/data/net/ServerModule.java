package com.zy.md.data.net;

import com.zy.md.data.net.GankApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Simon on 2016/11/22.
 */
@Module
public class ServerModule {

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        return okHttpClient;
    }



    @Singleton
    @Provides
    public GankApi provideGankApi( OkHttpClient okHttpClient ){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( GankApi.BASE_URL )
                .callFactory( okHttpClient )
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create( GankApi.class );
    }


    @Singleton
    @Provides
    public DouBanGirlApi provideDouBanGirlApi(OkHttpClient okHttpClient){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( DouBanGirlApi.BASE_URL )
                .callFactory( okHttpClient )
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create( DouBanGirlApi.class );
    }

}
