package com.mydeerlet.home.service;

import com.mydeerlet.home.base.HttpResult;
import com.mydeerlet.home.bean.BaseListEntity;
import com.mydeerlet.home.bean.KuaiXunModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface KuaiXunService {

    /**
     * 快讯列表
     * @param pageNum
     * @return
     */
    @FormUrlEncoded
    @POST("flash.php")
    Call<HttpResult<BaseListEntity<KuaiXunModel>>> getKuaiXunList(@Field("pageNum") int pageNum);


}
