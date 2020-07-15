package com.mvvmrxjavatemp.data.model.api.request


import com.google.gson.annotations.SerializedName

data class AddToCartRequest(
    @SerializedName("product_id")
    var productId: String,
    @SerializedName("quantity")
    var quantity: String
)