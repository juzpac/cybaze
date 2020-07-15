package com.mvvmrxjavatemp.data;

import android.content.Context;

import com.mvvmrxjavatemp.data.local.prefs.PreferencesHelper;
import com.mvvmrxjavatemp.data.model.api.request.AddToCartRequest;
import com.mvvmrxjavatemp.data.model.api.request.GetProductRequest;
import com.mvvmrxjavatemp.data.model.api.response.AddToCartResponse;
import com.mvvmrxjavatemp.data.model.api.response.BaseResponse;
import com.mvvmrxjavatemp.data.model.api.response.GetCartResponse;
import com.mvvmrxjavatemp.data.model.api.response.HomeResponse;
import com.mvvmrxjavatemp.data.model.api.response.ProductResponse;
import com.mvvmrxjavatemp.data.remote.ApiHelper;
import com.mvvmrxjavatemp.di.ApplicationContext;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDataManager implements DataManager {

    private final ApiHelper mApiHelper;

    private final Context mContext;


    private final Gson mGson;

    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context, PreferencesHelper preferencesHelper, ApiHelper apiHelper, Gson gson) {
        mContext = context;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
        mGson = gson;
    }

    @Override
    public String getAuthKey() {
        return mPreferencesHelper.getAuthKey();
    }

    @Override
    public void setAuthKey(String authKey) {
        mPreferencesHelper.setAuthKey(authKey);
    }

    @Override
    public String getName() {
        return mPreferencesHelper.getName();
    }

    @Override
    public void setName(String name) {
        mPreferencesHelper.setName(name);
    }

    @Override
    public Observable<HomeResponse> fetchHome(String xAuth) {
        return mApiHelper.fetchHome(xAuth);
    }

    @Override
    public Observable<ProductResponse> getProductDetails(String customerTocken, GetProductRequest productRequest) {
        return mApiHelper.getProductDetails(customerTocken, productRequest);
    }

    @Override
    public Observable<AddToCartResponse> addToCart(String customerTocken, AddToCartRequest addToCartRequest) {
        return mApiHelper.addToCart(customerTocken, addToCartRequest);
    }

    @Override
    public Observable<GetCartResponse> getCart(String customerTocken) {
        return mApiHelper.getCart(customerTocken);
    }

}
