package com.mydeerlet.home.service;

import com.mydeerlet.home.base.HttpResult;
import com.mydeerlet.home.bean.BannerModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Home1Service {


    @GET("sowing.php")
    Call<HttpResult<List<BannerModel>>> getBanner();

}
