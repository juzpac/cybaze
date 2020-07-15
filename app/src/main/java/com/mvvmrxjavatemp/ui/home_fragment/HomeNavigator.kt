package com.mvvmrxjavatemp.ui.home_fragment

import com.mvvmrxjavatemp.data.model.api.response.HomeResponse

interface HomeNavigator {
    fun dataFetched(homeResponse: HomeResponse)
}