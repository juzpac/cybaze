package com.mvvmrxjavatemp.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.mvvmrxjavatemp.di.ApplicationContext;
import com.mvvmrxjavatemp.di.PreferenceInfo;

import javax.inject.Inject;

public class AppPreferencesHelper implements PreferencesHelper {

    private final SharedPreferences mPrefs;
    private static final String PREF_KEY_AUTH = "PREF_KEY_AUTH";
    private static final String PREF_KEY_NAME = "PREF_KEY_NAME";

    @Inject
    AppPreferencesHelper(@ApplicationContext Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public String getAuthKey() {
        return mPrefs.getString(PREF_KEY_AUTH, "");
    }

    @Override
    public void setAuthKey(String authKey) {
        mPrefs.edit().putString(PREF_KEY_AUTH, authKey).apply();
    }

    @Override
    public String getName() {
        return mPrefs.getString(PREF_KEY_NAME, "");
    }

    @Override
    public void setName(String name) {
        mPrefs.edit().putString(PREF_KEY_NAME, name).apply();
    }


}
