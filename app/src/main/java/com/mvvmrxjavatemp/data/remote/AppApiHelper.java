package com.mvvmrxjavatemp.data.remote;

import com.google.gson.Gson;
import com.mvvmrxjavatemp.data.model.api.request.AddToCartRequest;
import com.mvvmrxjavatemp.data.model.api.request.GetProductRequest;
import com.mvvmrxjavatemp.data.model.api.response.AddToCartResponse;
import com.mvvmrxjavatemp.data.model.api.response.BaseResponse;
import com.mvvmrxjavatemp.data.model.api.response.GetCartResponse;
import com.mvvmrxjavatemp.data.model.api.response.HomeResponse;
import com.mvvmrxjavatemp.data.model.api.response.ProductResponse;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiInterface apiInterface;
    private Gson gson;
    private static final MediaType API_BODY_REQUEST_TYPE = MediaType.parse("application/json; charset=utf-8");

    @Inject
    public AppApiHelper(ApiInterface apiInterface, Gson gson) {
        this.apiInterface = apiInterface;
        this.gson = gson;
    }

    @Override
    public Observable<HomeResponse> fetchHome(String xAuth) {
        return apiInterface.fetchHomeApi(xAuth);
    }

    @Override
    public Observable<ProductResponse> getProductDetails(String customerTocken, GetProductRequest productRequest) {

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("product_id", productRequest.getProductId())
                .build();

        return apiInterface.getProductDetails(customerTocken,requestBody);
    }

    @Override
    public Observable<AddToCartResponse> addToCart(String customerTocken, AddToCartRequest addToCartRequest) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("product_id", addToCartRequest.getProductId())
                .addFormDataPart("quantity", addToCartRequest.getQuantity())
                .build();

        return apiInterface.addToCart(customerTocken,requestBody);
    }

    @Override
    public Observable<GetCartResponse> getCart(String customerTocken) {
        return apiInterface.getCart(customerTocken);
    }


}
