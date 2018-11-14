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
import com.mydeerlet.home.bean.ChannelBean;
import com.mydeerlet.home.home1.fragment.FragmentHome1;
import com.mydeerlet.home.service.Home1Service;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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
    @BindView(R.id.fragment_home_smartRefreshLayout)
    SmartRefreshLayout mRefreshLayout;

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


        getData();
        getChannel();

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData();

            }
        });

    }


    private void getData() {

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

        mRefreshLayout.finishRefresh();
    }


    //获取数据
    public void getChannel() {


        RetrofitManager.getInstance(mContext)
                .create(Home1Service.class)
                .getChannel()
                .enqueue(new RxConsumer<List<ChannelBean>>() {
                    @Override
                    public void onSuccess(List<ChannelBean> channelBeans) {

                        //排好序，设置好能否移动，再存到本地
                        List<ChannelBean> channelBeanList = channelBeans;
                        int position = 0;
                        if (!channelBeans.get(0).getColname().equals("头条")){
                            position++;
                            channelBeanList.add(0,new ChannelBean(0,"","头条",null,1,true));
                        }

                        if (!channelBeans.get(1).getColname().equals("视频")){
                            position++;
                            channelBeanList.add(1,new ChannelBean(1,"","视频",null,1,true));
                        }

                        for (ChannelBean channelBean: channelBeanList) {
                            channelBean.setSpanSize(1);
                            channelBean.setPosition(position);
                            channelBean.setLock(position == 0 || position == 1);
                            position++;
                        }

                        //获取标题，填充tab和viewpager
                        String[] mTitles = new String[channelBeans.size()];
                        for (int i = 0; i < mTitles.length; i++){
                            mTitles[i] = channelBeans.get(i).getColname();
                        }

                        mViewPager.setAdapter(new HomeFragmentPageAdapter(getChildFragmentManager(), channelBeans));
                        //绑定viewPage ，设置数据
                        fragmentHomeTabLayout.setViewPager(mViewPager, mTitles);
                    }

                    @Override
                    protected void onError(String msg) {

                    }

                    @Override
                    public void onFailure(Call<HttpResult<List<ChannelBean>>> call, Throwable t) {

                    }
                });







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

        private List<ChannelBean> list;

        public HomeFragmentPageAdapter(FragmentManager fm,  List<ChannelBean> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            FragmentHome1 fragment = new FragmentHome1();
            Bundle bundle = new Bundle();
            bundle.putString("cid",list.get(position).getCatid());
            bundle.putInt("index",position);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
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
