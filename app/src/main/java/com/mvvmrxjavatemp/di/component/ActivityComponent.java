package com.mvvmrxjavatemp.di.component;

import com.mvvmrxjavatemp.di.PerActivity;
import com.mvvmrxjavatemp.di.module.ActivityModule;
import com.mvvmrxjavatemp.ui.main.MainActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);
}
