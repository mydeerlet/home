package com.mydeerlet.home.utlis;

public final class Constant {

    public static boolean DEBUG_MODE = false;

    public static boolean DEBUG_MODE_LOG = false;

    public static final String  BASE_URL;

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

}
