package com.mydeerlet.home.utlis;

import android.content.Context;

public final class Constant {

    public static boolean DEBUG_MODE = true;

    public static boolean DEBUG_MODE_LOG = true;

    public static final String  BASE_URL;
    private Context context;

    private static Constant constant;

    /**
     * DEBUG 是否DEBUG
     */
    static {
        if (Constant.DEBUG_MODE){
            BASE_URL = "http://www.btc789.com/app/";
        } else {
            BASE_URL = "http://www.btc789.com/app/";
        }
    }

    public static Constant getInstance(){
        if (constant == null){
            synchronized (Constant.class){
                if (constant == null){
                    constant = new Constant();
                }
            }
        }
        return constant;
    }


    public Context getContext(){
        return context;
    }
}