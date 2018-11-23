package com.mydeerlet.home;

import android.app.Application;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.PlatformConfig;

import static com.mydeerlet.home.utlis.Constant.DEBUG_MODE;

public class HomeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (DEBUG_MODE) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，


        Fresco.initialize(this); //始化Fresco类

        /**友盟
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        UMConfigure.setLogEnabled(true);
        /**
         * 设置日志加密
         * 参数：boolean 默认为false（不加密）
         */
        UMConfigure.setEncryptEnabled(true);

        /**
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:【友盟+】 AppKey
         * 参数3:【友盟+】 Channel
         * 参数4:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数5:Push推送业务的secret
         */
        UMConfigure.init(this, "5bf4c97cf1f556cd700001dd",
                "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "5f8475ab41563da99d4681bb37effb01");


        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.i("aaa",deviceToken+"成功");
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.i("aaa",s+s1+"失败");
            }
        });


        PlatformConfig.setQQZone("1107796005","6F00aO3BvLGuJGm4");


    }
}
