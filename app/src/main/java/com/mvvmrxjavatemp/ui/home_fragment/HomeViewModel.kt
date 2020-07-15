package com.mvvmrxjavatemp.ui.home_fragment

import android.util.Log
import com.mvvmrxjavatemp.data.DataManager
import com.mvvmrxjavatemp.ui.base.BaseActivity
import com.mvvmrxjavatemp.ui.base.BaseViewModel
import com.mvvmrxjavatemp.utils.rx.SchedulerProvider

class HomeViewModel(baseActivity: BaseActivity<out BaseViewModel<*>>?, dataManager: DataManager?, schedulerProvider: SchedulerProvider?) : BaseViewModel<HomeNavigator>(baseActivity, dataManager, schedulerProvider) {
    fun getHome(baseActivity: BaseActivity<BaseViewModel<*>>?) {

        compositeDisposable.add(dataManager
                .fetchHome("xPt1snUQ2uvD3W4BJbKh")
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { baseActivity?.showLoading() }
                .doFinally { baseActivity?.hideLoading() }
                .doOnError { throwable ->
                    showApiCallError(throwable, baseActivity)
                }
                .subscribe { homeResponse ->
                    navigator.dataFetched(homeResponse)
                })
    }
}