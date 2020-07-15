package com.mvvmrxjavatemp.ui.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mvvmrxjavatemp.data.DataManager
import com.mvvmrxjavatemp.ui.base.BaseActivity
import com.mvvmrxjavatemp.ui.base.BaseViewModel
import com.mvvmrxjavatemp.utils.rx.SchedulerProvider

class CartViewModel(baseActivity: BaseActivity<out BaseViewModel<*>>?, dataManager: DataManager?, schedulerProvider: SchedulerProvider?) : BaseViewModel<CartFragmentNavigator>(baseActivity, dataManager, schedulerProvider) {


    fun getCart(baseActivity: BaseActivity<BaseViewModel<*>>?) {

        compositeDisposable.add(dataManager
                .getCart("xPt1snUQ2uvD3W4BJbKh")
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { baseActivity?.showLoading() }
                .doFinally { baseActivity?.hideLoading() }
                .doOnError { throwable ->
                    showApiCallError(throwable, baseActivity)
                }
                .subscribe { homeResponse ->
                    navigator.listFetched(homeResponse)
                })
    }
}
