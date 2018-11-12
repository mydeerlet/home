package com.mydeerlet.home.fragmentHome;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mydeerlet.home.R;
import com.mydeerlet.home.api.RetrofitManager;
import com.mydeerlet.home.api.RxConsumer;
import com.mydeerlet.home.base.BaseFragment;
import com.mydeerlet.home.base.HttpResult;
import com.mydeerlet.home.bean.BannerModel;
import com.mydeerlet.home.service.Home1Service;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.bgabanner.BGABanner;
import retrofit2.Call;

public class Home1Fragment extends BaseFragment {


    @BindView(R.id.fragment_home_search)
    TextView fragmentHomeSearch;

    Unbinder unbinder;
    @BindView(R.id.fragment_home_banner)
    BGABanner mBanner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home1_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @OnClick(R.id.fragment_home_search)
    public void search(View view) {
        Toast.makeText(getActivity(), "SSSS", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();

    }

    private void initData() {

        RetrofitManager
                .getInstance(getContext())
                .create(Home1Service.class)
                .getBanner()
                .enqueue(new RxConsumer<List<BannerModel>>() {
                    @Override
                    public void onSuccess(List<BannerModel> bannerModels) {

                        for (BannerModel b:bannerModels){
                            Log.i("aaa",b.getThumb()) ;
                        }
                    }

                    @Override
                    protected void onError(String msg) {

                    }

                    @Override
                    public void onFailure(Call<HttpResult<List<BannerModel>>> call, Throwable t) {

                    }
                });


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
