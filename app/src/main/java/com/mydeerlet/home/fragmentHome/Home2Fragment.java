package com.mydeerlet.home.fragmentHome;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mydeerlet.home.R;
import com.mydeerlet.home.base.BaseFragment;

public class Home2Fragment extends BaseFragment{


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home2_fragment, container, false);
    }
}
