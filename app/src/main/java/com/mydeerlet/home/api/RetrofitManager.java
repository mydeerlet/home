package com.mydeerlet.home.api;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mydeerlet.home.utlis.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {


    private static Retrofit instance;
    private static OkHttpClient okHttpClient;

    public static Retrofit getInstance(Context context){
        if (instance == null){
            synchronized (RetrofitManager.class){
                if (instance == null){
                    instance = new Retrofit.Builder()
                            .baseUrl(Constant.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create(getGson()))
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(getOkHttpClient(context))
                            .build();
                }
            }
        }

        return instance;
    }


    private static OkHttpClient getOkHttpClient(Context context){
        if (okHttpClient == null){
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10,TimeUnit.SECONDS)
                    .readTimeout(15,TimeUnit.SECONDS)
                    .addInterceptor(new ReadCookiesInterceptor(context))
                    .addInterceptor(new SaveCookiesInterceptor(context))
                    .addInterceptor(getInterceptor())
                    .build();

        }
        return okHttpClient;
    }

    private static Gson getGson(){
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }


    private static HttpLoggingInterceptor getInterceptor(){
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                if (Constant.DEBUG_MODE_LOG){
                    Log.e("RetrofitLog",message);
                }
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}
