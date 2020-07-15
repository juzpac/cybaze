package com.mvvmrxjavatemp.ui.main

import androidx.lifecycle.MutableLiveData
import com.mvvmrxjavatemp.data.DataManager
import com.mvvmrxjavatemp.ui.base.BaseActivity
import com.mvvmrxjavatemp.ui.base.BaseViewModel
import com.mvvmrxjavatemp.utils.rx.SchedulerProvider


class MainViewModel(baseActivity: BaseActivity<*>, dataManager: DataManager, schedulerProvider: SchedulerProvider) : BaseViewModel<MainNavigator>(baseActivity, dataManager, schedulerProvider) {

    var liveCartCount = MutableLiveData<Int>()
    init {
        liveCartCount.value=0
    }
}