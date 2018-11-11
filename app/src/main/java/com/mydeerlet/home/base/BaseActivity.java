package com.mydeerlet.home.base;

import android.os.Build;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.jaeger.library.StatusBarUtil;
import com.mydeerlet.home.utlis.Constant;
import com.mydeerlet.home.utlis.OSUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public abstract class BaseActivity extends RxAppCompatActivity {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);


        //初始化黄油刀
        ButterKnife.bind(this);
        setStatusBar();
        initViews();
    }

    protected void setStatusBar(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M && !OSUtils.isMIUI() && !OSUtils.isFlyme()){
            StatusBarUtil.setTranslucentForImageView(this,needOffSet());
        } else {
            StatusBarUtil.setTranslucentForImageView(this,0,needOffSet());
            StatusBarUtil.setLightMode(this);
        }
    }

    public void initViews(){}

    public abstract View needOffSet();

    public <T>SingleTransformer<T,T> scheduleSingle(){
        return new SingleTransformer<T, T>() {
            @Override
            public SingleSource<T> apply(Single<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }





    public <T>ObservableTransformer<T,T> scheduleObservable(){
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN &&
                getCurrentFocus() != null &&
                getCurrentFocus().getWindowToken() != null) {

            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, event)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(event);
    }


    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationOnScreen(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getRawX() > left && event.getRawX() < right
                    && event.getRawY() > top && event.getRawY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (mInputMethodManager != null) {
                mInputMethodManager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
