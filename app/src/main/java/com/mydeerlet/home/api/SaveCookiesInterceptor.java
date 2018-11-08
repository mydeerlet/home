package com.mydeerlet.home.api;

import android.content.Context;

import com.mydeerlet.home.utlis.SPUtils;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

public class SaveCookiesInterceptor implements Interceptor {
    private Context context;
    public SaveCookiesInterceptor(Context context){
        this.context = context;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }

            SPUtils.getInstance(context).edit()
                    .putStringSet(SPUtils.PREF_COOKIES, cookies)
                    .apply();
        }

        return originalResponse;
    }
}
