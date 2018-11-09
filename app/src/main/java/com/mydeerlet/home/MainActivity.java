package com.mydeerlet.home;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jaeger.library.StatusBarUtil;
import com.mydeerlet.home.base.BaseActivity;
import com.mydeerlet.home.bean.UpdateModel;
import com.mydeerlet.home.fragmentHome.Home1Fragment;
import com.mydeerlet.home.fragmentHome.Home2Fragment;
import com.mydeerlet.home.fragmentHome.Home3Fragment;
import com.mydeerlet.home.fragmentHome.Home4Fragment;
import com.mydeerlet.home.router.RouterUtil;
import com.mydeerlet.home.service.MainService;
import com.mydeerlet.home.utlis.BottomNavigationViewHelper;
import com.mydeerlet.home.utlis.OSUtils;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Route(path = RouterUtil.Main)
public class MainActivity extends BaseActivity {

    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    /**
     * 当前Fragment
     */
    private Fragment currentFragment;

    /**
     * 当前版本号Code
     */
    private int appVersion;

    /**
     * 设置状态栏颜色
     */
    @Override
    protected void setStatusBar() {
//        super.setStatusBar();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M && !OSUtils.isMIUI() && !OSUtils.isFlyme()){
            StatusBarUtil.setTranslucentForImageView(this,needOffSet());
        } else {
            StatusBarUtil.setTranslucentForImageView(this,0,needOffSet());
            StatusBarUtil.setLightMode(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //去掉BottomNavigationView 动画
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setItemIconTintList(null);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setOnNavigationItemReselectedListener(mOnNavigationItemReselectedListener);

        //显示相应的fragment
        if (savedInstanceState != null){
            String tag = savedInstanceState.getString("tag");
            currentFragment = getSupportFragmentManager().findFragmentByTag(tag);
            switch (savedInstanceState.getInt("index")){
                case 0:
                    navigation.setSelectedItemId(R.id.navigation_home);
                    break;
                case 1:
                    navigation.setSelectedItemId(R.id.navigation_dashboard);
                    break;
                case 2:
                    navigation.setSelectedItemId(R.id.navigation_notifications);
                    break;
                case 3:
                    navigation.setSelectedItemId(R.id.navigation_notifications3);
                    break;
            }
        } else {
            replaceFragment("home1");
        }

        initUpgrade(); //版本更新
    }

    /**
     * 版本更新检查
     */
    @SuppressLint("CheckResult")
    private void initUpgrade() {


        //获取当前版本号
        try {
            appVersion = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            appVersion = 1;
        }


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.btc789.com/app/btc_app_version.php/") // 设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) // 设置数据解析器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava平台
                .build();
        // 创建 网络请求接口 的实例
        final MainService request = retrofit.create(MainService.class);
        //对 发送请求 进行封装
        Call<UpdateModel> call = request.getUpdata();

        //发送网络请求(异步)
        call.enqueue(new Callback<UpdateModel>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<UpdateModel> call, Response<UpdateModel> response) {
                //请求处理,输出结果

                Log.i("aaa",response.body().toString());
                Log.i("aaa",call.toString());
            }

            //请求失败时候的回调
            @Override
            public void onFailure(Call<UpdateModel> call, Throwable throwable) {
                Log.i("aaa","链接失败");
            }
        });


    }




    //底部图标切换
    private BottomNavigationView.OnNavigationItemReselectedListener mOnNavigationItemReselectedListener
            = new BottomNavigationView.OnNavigationItemReselectedListener() {
        @Override
        public void onNavigationItemReselected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigation_home:
                    break;
                case R.id.navigation_dashboard:
                    break;
                case R.id.navigation_notifications:
                    break;
                case R.id.navigation_notifications3:
                    break;
            }
        }
    };


    //底部导航按钮切换
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    replaceFragment("home1");
                    setStatusBar();
                    return true;
                case R.id.navigation_dashboard:
                    replaceFragment("home2");
                    setStatusBar();
                    return true;
                case R.id.navigation_notifications:
                    replaceFragment("home3");
                    setStatusBar();
                    return true;
                case R.id.navigation_notifications3:
                    replaceFragment("home4");
//                    设置任务栏颜色
//                    StatusBarUtil.setDarkMode(MainActivity.this);
//                    StatusBarUtil.setColor(MainActivity.this, ContextCompat.getColor(MainActivity.this,R.color.colorAccent),0);
                    return true;
            }
            return false;
        }
    };


    //显示哪一个fragment
    private void replaceFragment(String tag) {
        if (currentFragment != null) {
            getSupportFragmentManager().beginTransaction().hide(currentFragment).commit();
        }
        currentFragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (currentFragment == null) {
            switch (tag) {
                case "home1":
                    currentFragment = new Home1Fragment();
                    break;
                case "home2":
                    currentFragment = new Home2Fragment();
                    break;
                case "home3":
                    currentFragment = new Home3Fragment();
                    break;
                case "home4":
                    currentFragment = new Home4Fragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().add(R.id.container, currentFragment, tag).commit();
        }else {
            getSupportFragmentManager().beginTransaction().show(currentFragment).commit();
        }
    }


    /**
     * 预留状态栏空间
     * @return
     */
    @Override
    public View needOffSet() {
        return findViewById(R.id.container);
    }




}
