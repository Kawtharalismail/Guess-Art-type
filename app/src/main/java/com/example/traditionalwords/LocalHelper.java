package com.example.traditionalwords;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LocalHelper {
    public static Context setLocale(Context context,String language){
        return  updateResourceslegacy(context,language);

    }

    private static Context updateResourceslegacy(Context context, String language) {
        Locale locale=new Locale(language);
        Locale.setDefault(locale);
        Resources resources=context.getResources();
        Configuration configuration=resources.getConfiguration();
        configuration.locale=locale;
        resources.updateConfiguration(configuration,resources.getDisplayMetrics());
        return context;
    }

}
