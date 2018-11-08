package com.mydeerlet.home.activity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.mydeerlet.home.R;
import com.mydeerlet.home.base.BaseActivity;
import com.mydeerlet.home.router.RouterUtil;

import java.util.Timer;
import java.util.TimerTask;

public class LaunchActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        animate();
    }

    private void animate() {
        //利用Timer让此界面延迟3秒后再跳转,timer中有一个线程,这个线程不断执行task
        Timer timer=new Timer();
        //timertask实现runnable接口,TimerTask类表示一个在指定时间内执行的task
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                ARouter.getInstance().build(RouterUtil.Main).navigation();
                LaunchActivity.this.finish();
            }
        };
        timer.schedule(task, 2000);//设置这个task在延迟三秒之后自动执行
    }

    @Override
    public View needOffSet() {
        return null;
    }

}
