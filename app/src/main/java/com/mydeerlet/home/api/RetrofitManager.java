package com.mydeerlet.home.api;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mydeerlet.home.utlis.Constant;

import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ray_L_Pain on 2017/7/25.
 */

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

    public static OkHttpClient getOkHttpClient(Context context){
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
















    /*
            Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.btc789.com/app/btc_app_version.php/") // 设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) // 设置数据解析器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava平台
                .build();
        // 创建 网络请求接口 的实例
        final MainService request = retrofit.create(MainService.class);
        //对 发送请求 进行封装
        Call<HttpResult<UpdateModel>> call = request.getUpdata();

        //发送网络请求(异步)
        call.enqueue(new Callback<HttpResult<UpdateModel>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<HttpResult<UpdateModel>> call, Response<HttpResult<UpdateModel>> response) {
                //请求处理,输出结果
                Log.i("aaaa",response.body().getData().getPresentation());
            }

            //请求失败时候的回调
            @Override
            public void onFailure(Call<HttpResult<UpdateModel>> call, Throwable throwable) {
                Log.i("aaa","链接失败");
            }
        });
     */

}
