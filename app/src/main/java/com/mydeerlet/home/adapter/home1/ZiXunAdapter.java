package com.mydeerlet.home.adapter.home1;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mydeerlet.home.R;
import com.mydeerlet.home.base.BaseAdapter;
import com.mydeerlet.home.base.OnItemClickListener;
import com.mydeerlet.home.bean.ArticleModel;

public class ZiXunAdapter extends BaseAdapter<ArticleModel,ZiXunAdapter.ZixunViewHolder>{


    public ZiXunAdapter(Context context, OnItemClickListener<ArticleModel> listener) {
        super(context, listener);
    }

    @NonNull
    @Override
    public ZixunViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ZixunViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_home1,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull ZixunViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ArticleModel model = data.get(position);
        holder.title.setText(model.getTitle());
        holder.author.setText(model.getNickname());
        holder.thumb.setImageURI(model.getThumb());
        holder.source.setText(model.getCatname());
    }

    public class ZixunViewHolder extends RecyclerView.ViewHolder {

        public TextView title,source,author,time;
        public SimpleDraweeView thumb;

        public ZixunViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_zixun_title);
            source = itemView.findViewById(R.id.item_zixun_source);
            author = itemView.findViewById(R.id.item_zixun_author);
            time = itemView.findViewById(R.id.item_zixun_time);
            thumb = itemView.findViewById(R.id.item_zixun_image);
        }
    }
}
