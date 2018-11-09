package com.mydeerlet.home.service;

import com.mydeerlet.home.bean.UpdateModel;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * 创建描述网络请求的接口——采用 **注解 **描述
 */

public interface MainService {

//    @GET("http://www.btc789.com/app/btc_app_version.php/")
//    Call<UpdateModel> getUpdata ();
//    二，配合RxJava 使用：
//    更改定义的接口，返回值不再是一个 Call ,而是返回的一个 Observble

    @GET("http://www.btc789.com/app/btc_app_version.php/")
    Single<UpdateModel> getUpdata ();
}
