package com.mydeerlet.home.utlis;

import android.util.Log;

/**
 * Created by ray_L_Pain on 2017/12/6.
 */

public class LogUtils {

    public static void v(String tag, String log){
        if (Constant.DEBUG_MODE_LOG){
            Log.e(tag, log);
        }
    }

    public static void d(String tag, String log){
        if (Constant.DEBUG_MODE_LOG){
            Log.e(tag, log);
        }
    }

    public static void w(String tag, String log){
        if (Constant.DEBUG_MODE_LOG){
            Log.e(tag, log);
        }
    }

    public static void e(String tag, String log){
        if (Constant.DEBUG_MODE_LOG){
            Log.e(tag, log);
        }
    }

    public static void i(String tag, String log){
        if (Constant.DEBUG_MODE_LOG){
            Log.e(tag, log);
        }
    }
}
