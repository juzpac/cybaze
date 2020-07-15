package com.mvvmrxjavatemp.data.model.api.request


import com.google.gson.annotations.SerializedName

data class GetProductRequest(
    @SerializedName("product_id")
    var productId: String
)