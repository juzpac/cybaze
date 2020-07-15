package com.mvvmrxjavatemp.utils;

import android.content.Context;
import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Mohammed Shafeeq on 26/08/18.
 */
public class ConnectivityInterceptor implements Interceptor {


    private Context context;

    public ConnectivityInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        if (!NetworkUtils.isNetworkConnected(context)) {
            throw new NoInternetException();
        } else {
            return chain.proceed(chain.request());
        }
    }
}