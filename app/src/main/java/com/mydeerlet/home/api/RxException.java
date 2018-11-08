package com.mydeerlet.home.api;

import com.mydeerlet.home.utlis.Constant;
import com.mydeerlet.home.utlis.LogUtils;
import com.mydeerlet.home.utlis.ToastFactory;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.functions.Consumer;
import retrofit2.HttpException;

public class RxException<T extends Throwable> implements Consumer<T> {

    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    private static final String SOCKETTIMEOUTEXCEPTION = "网络连接超时，请稍后重试";
    private static final String StringCONNECTEXCEPTION = "网络连接异常，请检查您的网络状态";
    private static final String StringUNKNOWNHOSTEXCEPTION = "网络不可用";

    private static final String error401 = "401 请求要求身份验证";
    private static final String error403 = "403 服务器拒绝请求";
    private static final String error404 = "404 页面找不到";
    private static final String error408 = "408 请求超时";
    private static final String error500 = "500 服务器内部错误";
    private static final String error502 = "502 错误网关";
    private static final String error503 = "503 服务不可用";
    private static final String error504 = "504 网关超时";


    @Override
    public void accept(T t) throws Exception {
        String error;
        if (t instanceof SocketTimeoutException) {
            error = SOCKETTIMEOUTEXCEPTION;
        } else if (t instanceof ConnectException) {
            error = StringCONNECTEXCEPTION;
        } else if (t instanceof UnknownHostException) {
            error = StringUNKNOWNHOSTEXCEPTION;
        } else if (t instanceof HttpException){
            switch (((HttpException) t).code()){
                case UNAUTHORIZED:
                    error = error401;
                    break;
                case FORBIDDEN:
                    error = error403;
                    break;
                case NOT_FOUND:
                    error = error404;
                    break;
                case REQUEST_TIMEOUT:
                    error = error408;
                    break;
                case GATEWAY_TIMEOUT:
                    error = error500;
                    break;
                case INTERNAL_SERVER_ERROR:
                    error = error502;
                    break;
                case BAD_GATEWAY:
                    error = error503;
                    break;
                case SERVICE_UNAVAILABLE:
                    error = error504;
                    break;
                default:
                    error = "操作失败";
                    break;
            }
        } else {
            error = "操作异常";
            if (t != null){
                t.printStackTrace();
            }
        }
        onShowMessage(error);
    }

    public void onShowMessage(String msg){
        LogUtils.e("RxException",msg);
        ToastFactory.getToast(Constant.getInstance().getContext(),msg).show();
    }
}
