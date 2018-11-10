package com.mydeerlet.home.service;

import com.mydeerlet.home.base.HttpResult;
import com.mydeerlet.home.bean.UpdateModel;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 创建描述网络请求的接口——采用 **注解 **描述
 */

public interface MainService {

//    @GET("openapi.do?keyfrom=Yanzhikai&key=2032414398&type=data&doctype=json&version=1.1&q=car")
//    Call<Translation>  getCall();
    // @GET注解的作用:采用Get方法发送网络请求
    // getCall() = 接收网络请求数据的方法
    // 其中返回类型为Call<*>，*是接收数据的类（即上面定义的Translation类）
    // 如果想直接获得Responsebody中的内容，可以定义网络请求返回值为Call<ResponseBody>


    @GET("http://www.btc789.com/app/btc_app_version.php")
    Call<HttpResult<UpdateModel>> getUpdate();
}
