package com.mvvmrxjavatemp.data.remote;

import com.mvvmrxjavatemp.data.model.api.request.AddToCartRequest;
import com.mvvmrxjavatemp.data.model.api.request.GetProductRequest;
import com.mvvmrxjavatemp.data.model.api.response.AddToCartResponse;
import com.mvvmrxjavatemp.data.model.api.response.BaseResponse;
import com.mvvmrxjavatemp.data.model.api.response.GetCartResponse;
import com.mvvmrxjavatemp.data.model.api.response.HomeResponse;
import com.mvvmrxjavatemp.data.model.api.response.ProductResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiHelper {

    Observable<HomeResponse> fetchHome(String xAuth);
    Observable<ProductResponse> getProductDetails(String customerTocken, GetProductRequest productRequest);
    Observable<AddToCartResponse> addToCart(String customerTocken, AddToCartRequest addToCartRequest);
    Observable<GetCartResponse> getCart(String customerTocken);
}
