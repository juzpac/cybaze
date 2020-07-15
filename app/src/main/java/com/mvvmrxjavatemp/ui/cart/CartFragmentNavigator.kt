package com.mvvmrxjavatemp.ui.cart

import com.mvvmrxjavatemp.data.model.api.response.GetCartResponse

interface CartFragmentNavigator {
    fun listFetched(homeResponse: GetCartResponse)
}