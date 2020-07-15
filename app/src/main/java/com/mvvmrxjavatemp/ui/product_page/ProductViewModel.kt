package com.mvvmrxjavatemp.ui.product_page

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvmrxjavatemp.data.DataManager
import com.mvvmrxjavatemp.data.model.api.request.AddToCartRequest
import com.mvvmrxjavatemp.data.model.api.request.GetProductRequest
import com.mvvmrxjavatemp.ui.base.BaseActivity
import com.mvvmrxjavatemp.ui.base.BaseViewModel
import com.mvvmrxjavatemp.utils.rx.SchedulerProvider

class ProductViewModel(baseActivity: BaseActivity<out BaseViewModel<*>>?, dataManager: DataManager?, schedulerProvider: SchedulerProvider?) : BaseViewModel<ProductNavigator>(baseActivity, dataManager, schedulerProvider) {


    var quantity= MutableLiveData<Int>()
    init {
        quantity.value=1
    }

    fun getProduct(baseActivity: BaseActivity<BaseViewModel<*>>?,id:String) {

        compositeDisposable.add(dataManager
                .getProductDetails("xPt1snUQ2uvD3W4BJbKh", GetProductRequest(id))
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { baseActivity?.showLoading() }
                .doFinally { baseActivity?.hideLoading() }
                .doOnError { throwable ->
                    showApiCallError(throwable, baseActivity)
                }
                .subscribe { homeResponse ->
                    Log.i("kkk",homeResponse.messageEn)
                    navigator.productDetailsFetched(homeResponse)
                })
    }

    fun decreaseQty() {
        var qty=quantity.value!!
        if (qty!=1){
            qty--
            quantity.value=qty
        }
    }

    fun inCreaseQty() {
        var qty=quantity.value!!
        qty++
        quantity.value=qty
    }

    fun addToCart(baseActivity: BaseActivity<BaseViewModel<*>>?, addToCartRequest: AddToCartRequest) {
        compositeDisposable.add(dataManager
                .addToCart("xPt1snUQ2uvD3W4BJbKh", addToCartRequest)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { baseActivity?.showLoading() }
                .doFinally { baseActivity?.hideLoading() }
                .doOnError { throwable ->
                    showApiCallError(throwable, baseActivity)
                }
                .subscribe { homeResponse ->
                    if (!homeResponse.isSuccess){
                        baseActivity?.showToast(homeResponse.messageEn)
                        navigator.updateCart(homeResponse.data.cartCount)
                    }
                })
    }
}
