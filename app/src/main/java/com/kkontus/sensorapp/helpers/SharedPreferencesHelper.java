package com.kkontus.sensorapp.helpers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kristijan on 09/02/2017.
 */

public class SharedPreferencesHelper {

    private static final String LANGUAGE_LOCALE = "languageSelectedLocale";

    public static String getLanguageSelectedLocale(Context context) {
        SharedPreferences settings = context.getSharedPreferences("PREFS", 0);
        return settings.getString(LANGUAGE_LOCALE, "hr");
    }

    public static void setLanguageSelectedLocale(Context context, String languageSelectedLocale) {
        SharedPreferences settings = context.getSharedPreferences("PREFS", 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString(LANGUAGE_LOCALE, languageSelectedLocale);
        editor.apply();
    }

}