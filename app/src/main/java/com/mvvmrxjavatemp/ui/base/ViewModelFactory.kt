package com.mvvmrxjavatemp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvvmrxjavatemp.data.DataManager
import com.mvvmrxjavatemp.ui.cart.CartViewModel
import com.mvvmrxjavatemp.ui.home_fragment.HomeViewModel
import com.mvvmrxjavatemp.ui.main.MainViewModel
import com.mvvmrxjavatemp.ui.product_page.ProductViewModel
import com.mvvmrxjavatemp.utils.rx.SchedulerProvider

class ViewModelFactory(private val baseActivity: BaseActivity<*>,
                       private val dataManager: DataManager,
                       private val schedulerProvider: SchedulerProvider) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(MainViewModel::class.java) ->
                        MainViewModel(baseActivity, dataManager, schedulerProvider)
                    isAssignableFrom(HomeViewModel::class.java) ->
                        HomeViewModel(baseActivity, dataManager, schedulerProvider)
                    isAssignableFrom(ProductViewModel::class.java) ->
                        ProductViewModel(baseActivity, dataManager, schedulerProvider)
                    isAssignableFrom(CartViewModel::class.java) ->
                        CartViewModel(baseActivity, dataManager, schedulerProvider)
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T
}