package com.mvvmrxjavatemp.data.model.api.response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductResponse(
        @Expose
        @SerializedName("data")
        var `data`: Data
) : BaseResponse() {
    data class Data(
            @Expose
            @SerializedName("product")
            var product: Product
    ) {

        data class Product(
                @Expose
                @SerializedName("description")
                var description: String,
                @Expose
                @SerializedName("image_url")
                var imageUrl: String,
                @Expose
                @SerializedName("price")
                var price: String,
                @Expose
                @SerializedName("spl_price")
                var splPrice: String,
                @Expose
                @SerializedName("title")
                var title: String
        )
    }
}