package com.mvvmrxjavatemp.ui.product_page

import com.mvvmrxjavatemp.data.model.api.response.ProductResponse

interface ProductNavigator {
    fun productDetailsFetched(homeResponse: ProductResponse)
    fun updateCart(cartCount: String)
}