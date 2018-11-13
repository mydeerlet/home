package com.mydeerlet.home.fragmentHome;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.flyco.tablayout.SlidingTabLayout;
import com.mydeerlet.home.R;
import com.mydeerlet.home.api.RetrofitManager;
import com.mydeerlet.home.api.RxConsumer;
import com.mydeerlet.home.base.BaseFragment;
import com.mydeerlet.home.base.HttpResult;
import com.mydeerlet.home.bean.BannerModel;
import com.mydeerlet.home.home1.fragment.FragmentHome1;
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
    @BindView(R.id.fragment_home_tabLayout)
    SlidingTabLayout fragmentHomeTabLayout;
    @BindView(R.id.fragment_home_btn_more)
    ImageButton fragmentHomeBtnMore;
    @BindView(R.id.fragment_home_viewPager)
    ViewPager mViewPager;

    private Context mContext;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home1_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @OnClick(R.id.fragment_home_search)
    public void search() {
        Toast.makeText(getActivity(), "SSSS", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initData();
        getChannel();

    }


    private void initData() {

        RetrofitManager
                .getInstance(getContext())
                .create(Home1Service.class)
                .getBanner()
                .enqueue(new RxConsumer<List<BannerModel>>() {
                    @Override
                    public void onSuccess(List<BannerModel> bannerModels) {

                        mBanner.setData(R.layout.item_banner, bannerModels, null);
                        mBanner.setAdapter(new BGABanner.Adapter<ConstraintLayout, BannerModel>() {
                            @Override
                            public void fillBannerItem(BGABanner banner, ConstraintLayout itemView, @Nullable BannerModel model, int position) {
                                SimpleDraweeView simpleDraweeView = itemView.findViewById(R.id.item_banner_img);
                                simpleDraweeView.setImageURI(model.getThumb());
                                TextView tip = itemView.findViewById(R.id.item_banner_tips);
                                tip.setText(model.getTitle());
                            }
                        });
                    }

                    @Override
                    protected void onError(String msg) {

                    }

                    @Override
                    public void onFailure(Call<HttpResult<List<BannerModel>>> call, Throwable t) {

                    }
                });


    }



    //模拟数据
    private final String[] mTitles = {
            "热门", "iOS", "Android"
            , "前端", "后端", "设计", "工具资源"
    };


    public void getChannel() {


        HomeFragmentPageAdapter  mAdapter = new HomeFragmentPageAdapter(getChildFragmentManager(),mTitles);
        mViewPager.setAdapter(mAdapter);

        //绑定viewPage ，设置数据
        fragmentHomeTabLayout.setViewPager(mViewPager,mTitles);
    }


//    private class MyPagerAdapter extends FragmentPagerAdapter {
//
//        private String[] mTitles;
//
//        public MyPagerAdapter(FragmentManager fm ,String[] mTitles) {
//            super(fm);
//            this.mTitles = mTitles;
//        }
//
//        @Override
//        public int getCount() {
//            return mTitles.length;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return mTitles[position];
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            FragmentHome1 fragment = new FragmentHome1();
//            return fragment;
//        }
//    }




    private static class HomeFragmentPageAdapter extends FragmentStatePagerAdapter {

        private String[] mTitles;

        public HomeFragmentPageAdapter(FragmentManager fm, String[] mTitles) {
            super(fm);
            this.mTitles = mTitles;
        }

        @Override
        public Fragment getItem(int position) {
            FragmentHome1 fragment = new FragmentHome1();
            Bundle bundle = new Bundle();

            if (position==0){
                bundle.putInt("type",0);
            }else {
                bundle.putInt("type",1);
            }

            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return mTitles == null ? 0 : mTitles.length;
        }

    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onAttach(Context mContext) {
        super.onAttach(mContext);
        this.mContext = mContext;
    }

}
