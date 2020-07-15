package com.mvvmrxjavatemp;

import android.app.Application;
import android.content.Context;
import com.mvvmrxjavatemp.di.component.ApplicationComponent;
import com.mvvmrxjavatemp.di.component.DaggerApplicationComponent;
import com.mvvmrxjavatemp.di.module.ApplicationModule;
import com.mvvmrxjavatemp.locale.LanguageHelper;


public class AppClass extends Application {

    private static ApplicationComponent applicationComponent;
    public static AppClass appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        applicationComponent =
                DaggerApplicationComponent
                        .builder()
                        .applicationModule(new ApplicationModule(this))
                        .build();
        applicationComponent.inject(this);
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LanguageHelper.onAttachDefault(base, LanguageHelper.DEFAULT_LANGUAGE));
    }
}