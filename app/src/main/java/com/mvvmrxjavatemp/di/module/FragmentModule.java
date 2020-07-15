package com.mvvmrxjavatemp.di.module;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.mvvmrxjavatemp.data.DataManager;
import com.mvvmrxjavatemp.ui.base.BaseActivity;
import com.mvvmrxjavatemp.ui.base.ViewModelFactory;
import com.mvvmrxjavatemp.utils.rx.AppSchedulerProvider;
import com.mvvmrxjavatemp.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;


@Module
public class FragmentModule {

    private BaseActivity baseActivity;

    public FragmentModule(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }


    @Provides
    Context providesContext() {
        return baseActivity;
    }

    @Provides
    CompositeDisposable providesCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider providesSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    BaseActivity provideBaseActivity() {
        return baseActivity;
    }

    @Provides
    ViewModelFactory provideViewModelFactory(BaseActivity baseActivity, DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new ViewModelFactory(baseActivity,dataManager, schedulerProvider);
    }
    @Provides
    LinearLayoutManager provideLinearLayoutManager(Context context){
        return new LinearLayoutManager(context);
    }
}
