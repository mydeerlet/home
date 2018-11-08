package com.mydeerlet.home.utlis;

import android.os.Build;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by ray_L_Pain on 2017/7/13.
 */

public class OSUtils {


//    作者：Mariotaku
//    链接：https://www.zhihu.com/question/22102139/answer/24834510
//    来源：知乎
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";

    public static boolean isMIUI() {
        try {
            final BuildProperties prop = BuildProperties.newInstance();
            return prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null
                    || prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null
                    || prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null;
        } catch (final IOException e) {
            return false;
        }
    }

    public static boolean isFlyme() {
        try {
            final Method method = Build.class.getMethod("hasSmartBar");
            return method != null;
        } catch (final Exception e) {
            return false;
        }
    }
}