package com.mydeerlet.home.service;

import com.mydeerlet.home.base.HttpResult;
import com.mydeerlet.home.bean.ArticleModel;
import com.mydeerlet.home.bean.BannerModel;
import com.mydeerlet.home.bean.BaseListEntity;
import com.mydeerlet.home.bean.ChannelBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Home1Service {


    @GET("sowing.php")
    Call<HttpResult<List<BannerModel>>> getBanner();

    /**
     * 头条列表
     * @param pageNum
     * @return
     */
    @FormUrlEncoded
    @POST("headerline.php")
    Call<HttpResult<BaseListEntity<ArticleModel>>> getHeaderLine(@Field("pageNum") int pageNum);


    @GET("nav.php")
    Call<HttpResult<List<ChannelBean>>> getChannel();


}
