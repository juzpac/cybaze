package com.mvvmrxjavatemp.data.remote;


import com.mvvmrxjavatemp.data.model.api.response.AddToCartResponse;
import com.mvvmrxjavatemp.data.model.api.response.BaseResponse;
import com.mvvmrxjavatemp.data.model.api.response.GetCartResponse;
import com.mvvmrxjavatemp.data.model.api.response.HomeResponse;
import com.mvvmrxjavatemp.data.model.api.response.ProductResponse;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("get_home_page_data")
    Observable<HomeResponse> fetchHomeApi(@Header("header") String samZAuth);

    //course details
    @POST("get_product")
    Observable<ProductResponse> getProductDetails(@Header("Authorization") String customerTocken,@Body RequestBody body);

    //course details
    @POST("add_to_cart")
    Observable<AddToCartResponse> addToCart(@Header("Authorization") String customerTocken, @Body RequestBody body);

    //course details
    @POST("get_cart")
    Observable<GetCartResponse> getCart(@Header("Authorization") String customerTocken);
}
