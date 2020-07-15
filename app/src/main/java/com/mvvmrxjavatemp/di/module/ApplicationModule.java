package com.mvvmrxjavatemp.di.module;

import android.app.Application;
import android.content.Context;

import com.mvvmrxjavatemp.data.AppDataManager;
import com.mvvmrxjavatemp.data.DataManager;
import com.mvvmrxjavatemp.data.local.prefs.AppPreferencesHelper;
import com.mvvmrxjavatemp.data.local.prefs.PreferencesHelper;
import com.mvvmrxjavatemp.data.remote.ApiHelper;
import com.mvvmrxjavatemp.data.remote.ApiInterface;
import com.mvvmrxjavatemp.data.remote.AppApiHelper;
import com.mvvmrxjavatemp.di.ApplicationContext;
import com.mvvmrxjavatemp.di.PreferenceInfo;
import com.mvvmrxjavatemp.utils.AppConstants;
import com.mvvmrxjavatemp.utils.ConnectivityInterceptor;
import com.google.gson.Gson;
import com.template.BuildConfig;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }


    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }


    @Provides
    @Singleton
    DataManager provideDataManger(AppDataManager dataManager) {
        return dataManager;
    }

    @Provides
    @Singleton
    ApiInterface provideApiInterface(@ApplicationContext  Context context) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);
        okHttpClientBuilder.addInterceptor(new ConnectivityInterceptor(context));
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build().create(ApiInterface.class);
    }
}
