package com.dev.mooohamed.moviesmvp;

import android.app.Application;
import android.content.res.Configuration;
import android.os.Build;
import android.util.DisplayMetrics;

import java.util.Locale;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Locale locale = new Locale("ar");
        Locale.setDefault(locale);
        Configuration configuration = this.getResources().getConfiguration();
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        }
        this.getResources().updateConfiguration(configuration,displayMetrics);
    }
}
