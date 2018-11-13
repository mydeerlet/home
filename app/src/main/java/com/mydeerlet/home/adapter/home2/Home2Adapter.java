package com.mydeerlet.home.adapter.home2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mydeerlet.home.R;
import com.mydeerlet.home.bean.KuaiXunModel;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.List;

public class Home2Adapter extends RecyclerView.Adapter<Home2Adapter.MyViewHolder> implements StickyRecyclerHeadersAdapter {

    protected Context context;

    protected List<KuaiXunModel> data;


    public Home2Adapter(Context context,List<KuaiXunModel> data) {
        this.context = context;
        this.data = data;

    }

    public void setData(List<KuaiXunModel> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public void addData(List<KuaiXunModel> data){
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void replaceData(KuaiXunModel data, int position){
        this.data.remove(position);
        this.data.add(position,data);
        notifyItemChanged(position);
    }



    @NonNull
    @Override
    public Home2Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_home2_content, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Home2Adapter.MyViewHolder holder, int position) {

    }




    @Override
    public long getHeaderId(int position) {

        Log.i("aaaa",data.get(position).getDate().hashCode()+"---");

        return data.get(position).getDate().hashCode();

    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_home2_hoder_adapter, parent, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView = (TextView) holder.itemView;
        textView.setText(data.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return null == data ? 0 : data.size();
    }






    public class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }



}
