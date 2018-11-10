package com.mydeerlet.home.api;

import android.util.Log;

import com.mydeerlet.home.base.HttpResult;
import com.mydeerlet.home.utlis.Constant;
import com.mydeerlet.home.utlis.ToastFactory;

import io.reactivex.functions.Consumer;

public abstract class RxConsumer<T> implements Consumer<HttpResult<T>> {

    @Override
    public void accept(HttpResult<T> tHttpResult) {


        Log.i("aaa",tHttpResult.getCode()+"");

        if (tHttpResult.SUCCESS()){
            try {
                onSuccess(tHttpResult.getData());
            } catch (Exception e){
                onError(e.getMessage());
                e.printStackTrace();
            }
        } else {
            onError(tHttpResult.getMsg());
        }
    }

    abstract void onSuccess(T t);

    private void onError(String msg){
        ToastFactory.getToast(Constant.getInstance().getContext(),msg).show();
    }
}
