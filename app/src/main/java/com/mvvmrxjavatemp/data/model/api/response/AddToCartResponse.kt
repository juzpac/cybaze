package com.mvvmrxjavatemp.data.model.api.response


import com.google.gson.annotations.SerializedName

data class AddToCartResponse(
    @SerializedName("data")
    var `data`: Data
): BaseResponse() {
    data class Data(
        @SerializedName("cart_count")
        var cartCount: String,
        @SerializedName("cart_price")
        var cartPrice: String
    )
}