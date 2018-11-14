package com.mydeerlet.home.home1.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mydeerlet.home.R;
import com.mydeerlet.home.adapter.home1.ZiXunAdapter;
import com.mydeerlet.home.api.RetrofitManager;
import com.mydeerlet.home.api.RxConsumer;
import com.mydeerlet.home.base.HttpResult;
import com.mydeerlet.home.base.OnItemClickListener;
import com.mydeerlet.home.bean.ArticleModel;
import com.mydeerlet.home.bean.BaseListEntity;
import com.mydeerlet.home.service.Home1Service;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;

public class FragmentHome1 extends Fragment {


    @BindView(R.id.fragment_zixun_recyclerView)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    @BindView(R.id.fragment_zixun_smartRefreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private String cid;

    private int page=1;

    private Context context;
    private ZiXunAdapter ziXunAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home1, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cid = getArguments().getString("cid");

        /**
         * 点击事件
         */
        ziXunAdapter = new ZiXunAdapter(context, new OnItemClickListener<ArticleModel>() {
            @Override
            public void onClick(View view, int position, ArticleModel data) {
                Toast.makeText(context, "item"+position, Toast.LENGTH_SHORT).show();
            }
        });
        AAA aaa = new AAA();

        if (getArguments().getInt("index")==0)
            mRecyclerView.setAdapter(ziXunAdapter);
        if (getArguments().getInt("index")==1)
            mRecyclerView.setAdapter(aaa);


        mRefreshLayout.setRefreshHeader(new ClassicsHeader(context));
        getData();


        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> getData());

    }

    public void getData() {

            if (TextUtils.isEmpty(cid)){
                if (getArguments().getInt("index") == 0){
                    //头条
                    RetrofitManager
                            .getInstance(context)
                            .create(Home1Service.class)
                            .getHeaderLine(page)
                            .enqueue(new RxConsumer<BaseListEntity<ArticleModel>>() {
                                @Override
                                public void onSuccess(BaseListEntity<ArticleModel> articleModelBaseListEntity) {

                                    if (page==1){

                                        mRefreshLayout.finishLoadMore();
                                        if (null != articleModelBaseListEntity.getData() && !articleModelBaseListEntity.getData().isEmpty())
                                            ziXunAdapter.setData(articleModelBaseListEntity.getData());
                                    }else {
                                        mRefreshLayout.finishRefresh(true);
                                        if (null != articleModelBaseListEntity.getData() && !articleModelBaseListEntity.getData().isEmpty())
                                            ziXunAdapter.addData(articleModelBaseListEntity.getData());
                                    }


                                    mRefreshLayout.setEnableLoadMore(articleModelBaseListEntity.hasNextPage());
                                    mRefreshLayout.finishLoadMore();//传入false表示加载失败
                                    page++;

                                }

                                @Override
                                protected void onError(String msg) {
                                    if (page == 1){
//                                    mRefreshLayout.finishRefresh(false);
                                    } else {
                                        mRefreshLayout.finishLoadMore(false);
                                    }
                                }

                                @Override
                                public void onFailure(Call<HttpResult<BaseListEntity<ArticleModel>>> call, Throwable t) {
                                    if (page == 1){
//                                    mRefreshLayout.finishRefresh(false);
                                    } else {
                                        mRefreshLayout.finishLoadMore(false);
                                    }
                                }
                            });
                }else {

                    mRefreshLayout.finishLoadMore();



                }

            }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(){
        page = 1;
        getData();
    }


    public class AAA extends RecyclerView.Adapter<AAA.Hoder>{


        @NonNull
        @Override
        public AAA.Hoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.item_fragment_home1, parent ,false);
            return new Hoder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AAA.Hoder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 20;
        }

        public class Hoder extends RecyclerView.ViewHolder {
            public Hoder(View itemView) {
                super(itemView);
            }
        }
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
