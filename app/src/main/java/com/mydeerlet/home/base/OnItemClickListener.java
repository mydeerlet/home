package com.mydeerlet.home.base;

import android.view.View;

public interface OnItemClickListener<T> {

    void onClick(View view, int position, T data);
}
