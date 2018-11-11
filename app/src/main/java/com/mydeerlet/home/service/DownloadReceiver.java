package com.mydeerlet.home.service;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.os.ResultReceiver;

/**
 * Created by lishy on 2017/10/29.
 */

public class DownloadReceiver extends ResultReceiver {
    /**
     * Create a new ResultReceive to receive results.  Your
     * {@link #onReceiveResult} method will be called from the thread running
     * <var>handler</var> if given, or from an arbitrary thread if null.
     *
     * @param handler
     */

    private Receiver mReceiver;

    @SuppressLint("RestrictedApi")
    public DownloadReceiver(Handler handler) {
        super(handler);

    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if(mReceiver != null){
            mReceiver.onReceiveResult(resultCode, resultData);
        }
    }

    public void setReceiver(Receiver receiver) {
        this.mReceiver = receiver;
    }

    public interface Receiver {
        void onReceiveResult(int resultCode, Bundle resultData);
    }

}
