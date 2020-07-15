package com.mvvmrxjavatemp.data.model.api.response


import com.google.gson.annotations.SerializedName

data class HomeResponse(
    @SerializedName("data")
    var `data`: Data
):BaseResponse() {
    data class Data(
        @SerializedName("home_data")
        var homeData: HomeData,
        @SerializedName("infos")
        var infos: Infos,
        @SerializedName("products")
        var products: List<Product>
    ) {
        data class HomeData(
            @SerializedName("ads")
            var ads: List<Ad>,
            @SerializedName("banner")
            var banner: List<Banner>
        ) {
            data class Ad(
                @SerializedName("image_url")
                var imageUrl: String
            )

            data class Banner(
                @SerializedName("image_url")
                var imageUrl: String
            )
        }

        data class Infos(
            @SerializedName("cart_count")
            var cartCount: String,
            @SerializedName("cart_price")
            var cartPrice: String,
            @SerializedName("delivery_info")
            var deliveryInfo: DeliveryInfo,
            @SerializedName("rewards_available")
            var rewardsAvailable: Int
        ) {
            data class DeliveryInfo(
                @SerializedName("express_delivery")
                var expressDelivery: String,
                @SerializedName("standard_delivery")
                var standardDelivery: String
            )
        }

        data class Product(
            @SerializedName("image")
            var image: String,
            @SerializedName("name")
            var name: String,
            @SerializedName("product_id")
            var productId: String
        )
    }
}