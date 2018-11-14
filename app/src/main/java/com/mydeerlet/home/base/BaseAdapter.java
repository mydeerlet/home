package com.mydeerlet.home.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T,R extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<R>{

    protected Context context;
    protected List<T> data;
    protected OnItemClickListener<T> listener;

    public BaseAdapter(Context context, OnItemClickListener<T> listener) {
        this.context = context;
        this.listener = listener;
        this.data = new ArrayList<>();
    }

    public void setData(List<T> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public void addData(List<T> data){
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void replaceData(T data, int position){
        this.data.remove(position);
        this.data.add(position,data);
        notifyItemChanged(position);
    }

    public void clearData(){
        this.data.clear();
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return null == data ? 0 : data.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final R holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener){
                    listener.onClick(v,holder.getAdapterPosition(),data.get(holder.getAdapterPosition()));
                }
            }
        });
    }
}
