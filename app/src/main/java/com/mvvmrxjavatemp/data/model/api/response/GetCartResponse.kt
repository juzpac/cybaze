package com.mvvmrxjavatemp.data.model.api.response


import com.google.gson.annotations.SerializedName

data class GetCartResponse(
    @SerializedName("data")
    var `data`: Data
): BaseResponse() {
    data class Data(
        @SerializedName("cart_count")
        var cartCount: String,
        @SerializedName("cart_price")
        var cartPrice: String,
        @SerializedName("details")
        var details: Details,
        @SerializedName("express")
        var express: Boolean,
        @SerializedName("products")
        var products: List<Product>
    ) {
        data class Details(
            @SerializedName("delivery_charge")
            var deliveryCharge: String,
            @SerializedName("express_delivery_charge")
            var expressDeliveryCharge: String,
            @SerializedName("express_delivery_time")
            var expressDeliveryTime: String,
            @SerializedName("grand_total")
            var grandTotal: String,
            @SerializedName("no_of_items")
            var noOfItems: String,
            @SerializedName("standard_delivery_time")
            var standardDeliveryTime: String,
            @SerializedName("tax_total")
            var taxTotal: String,
            @SerializedName("total_price")
            var totalPrice: String,
            @SerializedName("total_real_price")
            var totalRealPrice: String
        )

        data class Product(
            @SerializedName("availability")
            var availability: String,
            @SerializedName("price")
            var price: String,
            @SerializedName("product_id")
            var productId: String,
            @SerializedName("quantity")
            var quantity: String,
            @SerializedName("real_price")
            var realPrice: String,
            @SerializedName("thumb_url")
            var thumbUrl: String,
            @SerializedName("title")
            var title: String
        )
    }
}