package com.mvvmrxjavatemp.di.component;

import android.app.Application;
import android.content.Context;

import com.mvvmrxjavatemp.AppClass;
import com.mvvmrxjavatemp.data.DataManager;
import com.mvvmrxjavatemp.data.local.prefs.PreferencesHelper;
import com.mvvmrxjavatemp.data.remote.ApiHelper;
import com.mvvmrxjavatemp.di.ApplicationContext;
import com.mvvmrxjavatemp.di.module.ApplicationModule;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(AppClass application);

    @ApplicationContext
    Context context();

    Application application();

    PreferencesHelper preferencesHelper();

    ApiHelper apiHelper();

    Gson gson();

    DataManager dataManager();
}
