package com.mydeerlet.home.fragmentHome;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mydeerlet.home.R;
import com.mydeerlet.home.adapter.home2.Home2Adapter;
import com.mydeerlet.home.api.RetrofitManager;
import com.mydeerlet.home.api.RxConsumer;
import com.mydeerlet.home.base.BaseFragment;
import com.mydeerlet.home.base.HttpResult;
import com.mydeerlet.home.bean.BaseListEntity;
import com.mydeerlet.home.bean.KuaiXunModel;
import com.mydeerlet.home.service.KuaiXunService;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;

public class Home2Fragment extends BaseFragment {


    @BindView(R.id.fragment_kuaixun2_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.fragment_kuaixun2_smartRefreshLayout)
    SmartRefreshLayout mRefreshLayout;
    Unbinder unbinder;
    private Context context;
    private int page = 1;
    private Home2Adapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home2_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        adapter = new Home2Adapter(getContext() ,null);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new StickyRecyclerHeadersDecoration(adapter));


        mRefreshLayout.setRefreshHeader(new ClassicsHeader(context));
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getData();
            }
        });

        // 自动刷新
        mRefreshLayout.autoRefresh();


    }


    @SuppressLint("CheckResult")
    public void getData() {

        RetrofitManager
                .getInstance(context)
                .create(KuaiXunService.class)
                .getKuaiXunList(page)
                .enqueue(new RxConsumer<BaseListEntity<KuaiXunModel>>() {
                    @Override
                    public void onSuccess(BaseListEntity<KuaiXunModel> kuaiXunModelBaseListEntity) {
                        if (page == 1){
                            mRefreshLayout.finishRefresh(true);
                            if (null != kuaiXunModelBaseListEntity.getData() && !kuaiXunModelBaseListEntity.getData().isEmpty()){
                                adapter.setData(kuaiXunModelBaseListEntity.getData());
                            }
                        } else {
                            mRefreshLayout.finishLoadMore(true);
                            if (null != kuaiXunModelBaseListEntity.getData() && !kuaiXunModelBaseListEntity.getData().isEmpty()){
                                adapter.addData(kuaiXunModelBaseListEntity.getData());
                            }
                        }
                        mRefreshLayout.setEnableLoadMore(kuaiXunModelBaseListEntity.hasNextPage());
                        page++;
                    }

                    @Override
                    protected void onError(String msg) {
                        if (page == 1){
                            mRefreshLayout.finishRefresh(false);
                        } else {
                            mRefreshLayout.finishLoadMore(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<HttpResult<BaseListEntity<KuaiXunModel>>> call, Throwable t) {

                    }
                });
        mRefreshLayout.finishRefresh();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }



}
