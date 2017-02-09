package com.kkontus.sensorapp.helpers;

import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;

/**
 * Created by kristijan on 09/02/2017.
 */

public class LanguageHelper {

    public static void changeLanguageLocale(Context context, String localeToLoad) {
        String languageToLoad = localeToLoad; //"hr"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;

        //getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

}
