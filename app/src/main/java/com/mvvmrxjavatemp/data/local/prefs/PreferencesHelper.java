package com.mvvmrxjavatemp.data.local.prefs;

public interface PreferencesHelper {

    String getAuthKey();
    void setAuthKey(String authKey);

    String getName();
    void setName(String name);
}
