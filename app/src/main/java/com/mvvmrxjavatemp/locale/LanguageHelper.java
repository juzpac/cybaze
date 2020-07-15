package com.mvvmrxjavatemp.locale;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import java.util.Locale;


public class LanguageHelper {

    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";
    private static final String IS_LANGUAGE_SELECTED = "is_language_selected";
    public static final String DEFAULT_LANGUAGE = "en";
    public static String APP_LANGUAGE_ARABIC = "ar";
    public static String APP_LANGUAGE_ENGLISH = "en";

    //Attach the custom wrapper to set language
    public static Context onAttach(Context context) {
        String lang = getLanguagePref(context);
        return setLocale(context, lang);
    }

    //Attach the custom wrapper to set language
    public static Context onAttach(Context context, String defaultLanguage) {
        String lang = getLanguagePref(context, defaultLanguage);
        return setLocale(context, lang);
    }

    //Attach the custom wrapper to set language
    public static Context onAttachDefault(Context context, String defaultLanguage) {
        String lang = getLanguagePref(context, defaultLanguage);
        return setLocaleDefault(context, lang);
    }

    //Returns the current language
    public static String getLanguage(Context context) {
        return getLanguagePref(context);
    }


    //Checks whether current language is arabic
    public static boolean isLanguageArabic(Context context) {
        return getLanguagePref(context).equalsIgnoreCase("ar");
//        return true;
    }

    //Set the current locale and returns the context
    private static Context setLocaleDefault(Context context, String language) {
        setLanguagePref(context, language);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, language);
        }
        return updateResourcesLegacy(context, language);
    }


    //Set the current locale and returns the context
    public static Context setLocale(Context context, String language) {
        setLanguagePref(context, language);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, language);
        }
        return updateResourcesLegacy(context, language);
    }

    //Returns the language that stored in local pref
    private static String getLanguagePref(Context context, String defaultLanguage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(SELECTED_LANGUAGE, defaultLanguage);
    }

    //Returns the language that stored in local pref
    private static String getLanguagePref(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(SELECTED_LANGUAGE, DEFAULT_LANGUAGE);
    }

    //Store the language in local pref
    private static void setLanguagePref(Context context, String language) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SELECTED_LANGUAGE, language);
        editor.apply();
    }

    @TargetApi(Build.VERSION_CODES.N)  //Update the configuration in android N and higher devices
    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);
        return context.createConfigurationContext(configuration);
    }

    @SuppressWarnings("deprecation")//Update the configuration in lower devices
    private static Context updateResourcesLegacy(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale);
        }
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return context;
    }

    public static boolean isAlreadyLanguageSelected(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(IS_LANGUAGE_SELECTED, false);
    }

    public static void setAlreadyLanguageSelected(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(IS_LANGUAGE_SELECTED, true);
        editor.apply();
    }


}