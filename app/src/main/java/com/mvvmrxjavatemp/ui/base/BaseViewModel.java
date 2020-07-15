package com.mvvmrxjavatemp.ui.base;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.mvvmrxjavatemp.data.DataManager;
import com.mvvmrxjavatemp.data.model.api.response.BaseResponse;
import com.mvvmrxjavatemp.utils.NoInternetException;
import com.mvvmrxjavatemp.utils.rx.SchedulerProvider;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;


public abstract class BaseViewModel<N> extends ViewModel {

    private final DataManager mDataManager;

    private final SchedulerProvider mSchedulerProvider;

    private CompositeDisposable mCompositeDisposable;

    private WeakReference<N> mNavigator;
    private WeakReference<BaseActivity> baseActivity;

    public BaseViewModel(BaseActivity baseActivity, DataManager dataManager,
                         SchedulerProvider schedulerProvider) {
        this.baseActivity = new WeakReference<>(baseActivity);
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public N getNavigator() {
        return mNavigator.get();
    }

    public void setNavigator(N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }

    public BaseActivity getBaseActivity() {
        return baseActivity != null ? baseActivity.get() : null;
    }


    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    protected void showApiCallError(Throwable throwable, BaseActivity activity) {
        Log.i("apierror", throwable.getLocalizedMessage());
        if (throwable instanceof NoInternetException) {
            activity.onInternetConnectionFailure();
        } else {
            activity.onApiFailure();
        }
    }

    protected boolean isResponseSuccess(BaseActivity activity, BaseResponse response) {
        if (response.isSuccess()) {
            return true;
        } else {
            activity.showToast(response.getMessageEn());
            return false;
        }
    }

}
