package com.mydeerlet.home.home1.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mydeerlet.home.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentHome1 extends Fragment {


    @BindView(R.id.fragment_zixun_recyclerView)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    private int type;

    private Context context;

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
        type = getArguments().getInt("type");

        MyAdapter myAdapter =new MyAdapter();
        mRecyclerView.setAdapter(myAdapter);



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.Hoder>{

        @NonNull
        @Override
        public MyAdapter.Hoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


            TextView textView =new TextView(context);
            textView.setText("aaaaaaa");
            View view=textView;
            return new Hoder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.Hoder holder, int position) {

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
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }
}
