package com.google.secondproject.IntroSlider;

import android.content.Context;
import android.content.SharedPreferences;

public class SliderPrefManager {

    private Context context;
    private SharedPreferences pref;

    public static final String PREF_NAME = "slider_pref";
    public static final String KEY_START = "startSlider";
    public static final String KEY_SKIP = "skipSlider";

    public SliderPrefManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public Boolean getStartSlider() {
        return pref.getBoolean(KEY_START, true);
    }

    public void setStartSlider(Boolean start) {
        pref.edit().putBoolean(KEY_START, start).apply();
    }

    public Boolean getSkipSlider() {
        return pref.getBoolean(KEY_SKIP, false);
    }

    public void setSkipSlider(Boolean skip) {
        pref.edit().putBoolean(KEY_SKIP, skip).apply();
    }
}
