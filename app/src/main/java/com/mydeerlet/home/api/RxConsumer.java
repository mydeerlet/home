package com.mydeerlet.home.api;



import com.mydeerlet.home.base.HttpResult;
import com.mydeerlet.home.bean.UpdateModel;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class RxConsumer<T> implements Callback<HttpResult<T>> {


    @Override
    public void onResponse(Call<HttpResult<T>> call, Response<HttpResult<T>> response) {

        if (response.body().SUCCESS()){
            try {
                onSuccess(response.body().getData());
            } catch (Exception e){
                onError(e.getMessage());
                e.printStackTrace();
            }
        } else {
            onError(response.body().getMsg());
        }
    }

    public abstract void onSuccess(T t);

    protected abstract void onError(String msg);
}
